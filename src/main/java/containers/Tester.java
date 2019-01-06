package containers;//: containers/Tester.java
// Applies Test objects to lists of different containers.

import java.util.List;

/**
 * 501 页
 * 为了使用这个框架，你需要将待测容器以及Test对象传递给方法，这些都是重载的泛型使得方法，它们可以减少在使用它们是所必需
 *
 * @param <C> 0
 */
public class Tester<C> {
    public static int fieldWidth = 8;
    public static TestParam[] defaultParams = TestParam.array(
            10, 5000, 100, 5000, 1000, 5000, 10000, 500);

    // Override this to modify pre-test initialization:
    protected C initialize(int size) {
        return container;
    }

    protected C container;
    private String headline = "";

    private List<Test<C>> tests;

    private static String stringField() {
        return "%" + fieldWidth + "s";
    }

    private static String numberField() {
        return "%" + fieldWidth + "d";
    }

    private static int sizeWidth = 5;
    private static String sizeField = "%" + sizeWidth + "s";
    private TestParam[] paramList = defaultParams;

    public Tester(C container, List<Test<C>> tests) {
        this.container = container;
        this.tests = tests;
        if (container != null)
            headline = container.getClass().getSimpleName();
    }

    public Tester(C container, List<Test<C>> tests,
                  TestParam[] paramList) {
        this(container, tests);
        this.paramList = paramList;
    }

    public void setHeadline(String newHeadline) {
        headline = newHeadline;
    }

    // Generic methods for convenience :
    public static <C> void run(C cntnr, List<Test<C>> tests) {
        new Tester<C>(cntnr, tests).timedTest();
    }

    public static <C> void run(C cntnr,
                               List<Test<C>> tests, TestParam[] paramList) {
        new Tester<C>(cntnr, tests, paramList).timedTest();
    }

    private void displayHeader() {
        // Calculate width and pad with '-':
        int width = fieldWidth * tests.size() + sizeWidth;
        int dashLength = width - headline.length() - 1;
        StringBuilder head = new StringBuilder(width);
        for (int i = 0; i < dashLength / 2; i++)
            head.append('-');
        head.append(' ');
        head.append(headline);
        head.append(' ');
        for (int i = 0; i < dashLength / 2; i++)
            head.append('-');
        System.out.println(head);
        // Print column headers:
        System.out.format(sizeField, "size");
        for (Test test : tests)
            System.out.format(stringField(), test.name);
        System.out.println();
    }

    // Run the tests for this container:
    public void timedTest() {
        displayHeader();
        for (TestParam param : paramList) {
            System.out.format(sizeField, param.size);
            for (Test<C> test : tests) {
                C kontainer = initialize(param.size);
                long start = System.nanoTime();
                // Call the overriden method:
                int reps = test.test(kontainer, param);
                long duration = System.nanoTime() - start;
                long timePerRep = duration / reps; // Nanoseconds
                System.out.format(numberField(), timePerRep);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        System.out.format(numberField(), 10l);
        System.out.println("");
        System.out.format(stringField(), "size");
        System.out.println("");

        for (TestParam param : defaultParams) {
            System.out.println("size:\t" + param.size + "loops :" + param.loops);

        }
    }


} ///:~
