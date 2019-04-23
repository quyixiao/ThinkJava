package java8;

import java8.dto.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class PersonTest {


    public static void main(String[] args) {
        Person person1 = new Person("zhangsan", 20);
        Person person2 = new Person("lisi", 30);
        Person person3 = new Person("wangwu", 40);


        List<Person> persons = Arrays.asList(person1, person2, person3);

        List<Person> filerPerson = getPersonByUserName("zhangsan", persons);
        System.out.println(filerPerson);

        List<Person> filerPerson1 = getPersonAge(25, persons);
        System.out.println(filerPerson1);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        List<Person> personResult = PersonTest.getPersonByAge2(25, persons, (age, personList) -> {
            return personList.stream().filter(person -> person.getAge() > age).collect(Collectors.toList());
        });
        personResult.forEach(person -> System.out.println(person.getAge()));
        System.out.println("==========================================");
        List<Person> personResult1 = PersonTest.getPersonByAge2(25, persons, (age, personList) -> {
            return personList.stream().filter(person -> person.getAge() <= age).collect(Collectors.toList());
        });
        personResult1.forEach(person -> System.out.println(person.getAge()));

    }

    public static List<Person> getPersonByUserName(String userName, List<Person> peoples) {
        return peoples.stream().filter(person -> person.getUserName().equals(userName)).collect(Collectors.toList());
    }

    public static List<Person> getPersonAge(Integer age, List<Person> persons) {
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (ageOfperson, personList) ->
                personList.stream().filter(person -> person.getAge() > ageOfperson).collect(Collectors.toList());
        return biFunction.apply(age, persons);
    }

    public static List<Person> getPersonByAge2(int age, List<Person> persons, BiFunction<Integer, List<Person>, List<Person>> biFunction) {
        return biFunction.apply(age, persons);
    }

}
