package typeinfo;//: typeinfo/Position.java


/***
 * 342
 *
 *
 * 1
 *
 * 在你的设计草案中初稿中，应该努力使用最简单且可以工作的事物，
 * 直至程序的某个方面要求你添加额外的特性，而不是一形如就假设它是必需的
 *
 *
 *
 */
class Position {
    private String title;
    private Person person;

    public Position(String jobTitle, Person employee) {
        title = jobTitle;
        person = employee;
        if (person == null)
            person = Person.NULL;
    }

    public Position(String jobTitle) {
        title = jobTitle;
        person = Person.NULL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person newPerson) {
        person = newPerson;
        if (person == null)
            person = Person.NULL;
    }

    public String toString() {
        return "Position: " + title + " " + person;
    }
}



///:~
