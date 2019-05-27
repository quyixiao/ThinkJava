package java8.optional;

import java.util.Optional;
import java.util.function.Consumer;

/***
 * 解决NullException
 *
 * if(null != person){
 *     Address address = person.getAddress();
 *     if(address != null){
 *         ...
 *     }
 * }
 *
 */
public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> optional = Optional.empty();// Optional.of("hello");
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }

        optional.ifPresent(System.out::println);

        optional.ifPresent(new Consumer<String>() {
            @Override
            public void accept(String item) {
                System.out.println(item);
            }
        });

        System.out.println(optional.orElse("world"));

        System.out.println(optional.orElseGet(() -> "你好"));


    }
}
