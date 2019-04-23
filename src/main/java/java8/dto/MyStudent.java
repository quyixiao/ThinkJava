package java8.dto;


import lombok.Data;

@Data
public class MyStudent {

    private String name;

    private int score;

    private int age;

    public MyStudent(String name, int score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }


}
