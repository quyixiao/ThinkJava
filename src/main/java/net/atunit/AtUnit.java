//: net/mindview/atunit/AtUnit.java
// An annotation-based unit-test framework.
// {RunByHand}
package net.atunit;

import net.mindview.util.BinaryFile;
import net.mindview.util.ProcessFiles;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;


/****
 *
 * 643页
 *
 *  * 所有测试的保留属性必须是RunTime，因此@Unit系统必须在编译后的代码中查询这些注解。
 *  要实现该系统，并运行测试，我们还需要使用反射机制抽取注解，下面这个程序通过注解中的信息，决定如何构造测试对象，
 *  并在测试对象上运行测试，正是由于注解的帮助，这个程序才如此短小而直接。
 *
 *  AtUnit.java使用了net.mindview.util中的ProcessFiles工具，这个类还实现了ProcessFiles.Stragy接口，该接口
 *  包含Process()方法，如此一来，便可以将一个AtUnit实例传给ProcessFiles构造器，ProcessFile构造器的第二个参数
 *  告诉ProcessFile查找所有的扩展名为class的文件。
 *  如果你没有提供命令行参数，这个程序会遍历当前的目录，你也可以为其提供多个参数，可以是类文件（带有或不带.s扩展名都可以
 *  ）或者一些目录，由于@Unit将会自动找到可测试的类和方法，所以没有套件机制的必要
 *  AtUnit。java必须解决一个问题，就是当它找到类文件时，实际引用的类名，含有包 并非一定就是类文件的名字，为了从
 *  中解读信息，我们必须分析该类文件，这很重要，因为这种名字不一致的情况确实可能出现，所以，当找到一个.class文件时，
 *  第一件事情就是打开该文件，读取其二进制数据，然后将其交给ClassNameFile.thisClass() ,你们这里字节码工程 的领域
 *  ，因为我们实际上是分析一个类文件的内容。
 */
public class AtUnit implements ProcessFiles.Strategy {
	static Class<?> testClass;
	static List<String> failedTests = new ArrayList<String>();
	static long testsRun = 0;
	static long failures = 0;

	public static void main(String[] args) throws Exception {
		ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true); // Enable
																			// asserts
		new ProcessFiles(new AtUnit(), "class").start(args);
		if (failures == 0)
			print("OK (" + testsRun + " tests)");
		else {
			print("(" + testsRun + " tests)");
			print("\n>>> " + failures + " FAILURE" + (failures > 1 ? "S" : "") + " <<<");
			for (String failed : failedTests)
				print("  " + failed);
		}
	}

	public void process(File cFile) {
		try {
			String cName = ClassNameFinder.thisClass(BinaryFile.read(cFile));
			if (!cName.contains("."))
				return; // Ignore unpackaged classes
			testClass = Class.forName(cName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		TestMethods testMethods = new TestMethods();
		Method creator = null;
		Method cleanup = null;
		for (Method m : testClass.getDeclaredMethods()) {
			testMethods.addIfTestMethod(m);
			if (creator == null)
				creator = checkForCreatorMethod(m);
			if (cleanup == null)
				cleanup = checkForCleanupMethod(m);
		}
		if (testMethods.size() > 0) {
			if (creator == null)
				try {
					if (!Modifier.isPublic(testClass.getDeclaredConstructor().getModifiers())) {
						print("Error: " + testClass + " default constructor must be public");
						System.exit(1);
					}
				} catch (NoSuchMethodException e) {
					// Synthesized default constructor; OK
				}
			print(testClass.getName());
		}
		for (Method m : testMethods) {
			printnb("  . " + m.getName() + " ");
			try {
				Object testObject = createTestObject(creator);
				boolean success = false;
				try {
					if (m.getReturnType().equals(boolean.class))
						success = (Boolean) m.invoke(testObject);
					else {
						m.invoke(testObject);
						success = true; // If no assert fails
					}
				} catch (InvocationTargetException e) {
					// Actual exception is inside e:
					print(e.getCause());
				}
				print(success ? "" : "(failed)");
				testsRun++;
				if (!success) {
					failures++;
					failedTests.add(testClass.getName() + ": " + m.getName());
				}
				if (cleanup != null)
					cleanup.invoke(testObject, testObject);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	static class TestMethods extends ArrayList<Method> {
		void addIfTestMethod(Method m) {
			if (m.getAnnotation(Test.class) == null)
				return;
			if (!(m.getReturnType().equals(boolean.class) || m.getReturnType().equals(void.class)))
				throw new RuntimeException("@Test method" + " must return boolean or void");
			m.setAccessible(true); // In case it's private, etc.
			add(m);
		}
	}

	private static Method checkForCreatorMethod(Method m) {
		if (m.getAnnotation(TestObjectCreate.class) == null)
			return null;
		if (!m.getReturnType().equals(testClass))
			throw new RuntimeException("@TestObjectCreate " + "must return instance of Class to be tested");
		if ((m.getModifiers() & java.lang.reflect.Modifier.STATIC) < 1)
			throw new RuntimeException("@TestObjectCreate " + "must be static.");
		m.setAccessible(true);
		return m;
	}

	private static Method checkForCleanupMethod(Method m) {
		if (m.getAnnotation(TestObjectCleanup.class) == null)
			return null;
		if (!m.getReturnType().equals(void.class))
			throw new RuntimeException("@TestObjectCleanup " + "must return void");
		if ((m.getModifiers() & java.lang.reflect.Modifier.STATIC) < 1)
			throw new RuntimeException("@TestObjectCleanup " + "must be static.");
		if (m.getParameterTypes().length == 0 || m.getParameterTypes()[0] != testClass)
			throw new RuntimeException("@TestObjectCleanup " + "must take an argument of the tested type.");
		m.setAccessible(true);
		return m;
	}

	private static Object createTestObject(Method creator) {
		if (creator != null) {
			try {
				return creator.invoke(testClass);
			} catch (Exception e) {
				throw new RuntimeException("Couldn't run " + "@TestObject (creator) method.");
			}
		} else { // Use the default constructor:
			try {
				return testClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException("Couldn't create a " + "test object. Try using a @TestObject method.");
			}
		}
	}
} /// :~
