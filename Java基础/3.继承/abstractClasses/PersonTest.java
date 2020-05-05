package inheritance.abstractClasses;

public class PersonTest {
    public static void main(String[] args){
        Person[] person = new Person[2];

        person[0] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        person[1] = new Student("Maria Morris", "computer science");

        for(Person p: person){
            System.out.println(p.getName() + ", " + p.getDescription());
        }
    }
}
