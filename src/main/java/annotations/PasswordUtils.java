//: annotations/PasswordUtils.java
package annotations;

import java.util.List;

/***
 *
 * 注解的元素在使用时表现为一名一值的形式，并需要置于@UseCase 声明之后的括号内，在encryptPassword() 方法
 * 注解中，并没有给出description元素的值，因此，在UserCase注解处理这个类时会使用该元素的默认值。
 * 你应该能够想象得到如何使用这套工具业 "勾勒" 出将要建造的系统，然后在建造的过程中逐渐实现系统的各项功能
 *
 *
 *
 *
 *
 *
 *
 */
public class PasswordUtils {
    @UseCase(id = 47, description = "Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {

        return (password.matches("\\w*\\d\\w*"));

    }

    @UseCase(id = 48)
    public String encryptPassword(String password) {

        return new StringBuilder(password).reverse().toString();


    }

    @UseCase(id = 49, description = "New passwords can't equal previously used ones")
    public boolean checkForNewPassword(
            List<String> prevPasswords, String password) {
        return !prevPasswords.contains(password);
    }
} ///:~
