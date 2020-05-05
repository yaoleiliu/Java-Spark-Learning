package inheritance.abstractClasses;

public abstract class Person {

    /**
     * 不可以省略这个抽象方法的定义。原因是：如果仅在Employee和Student子类中定义getDescription方法，
     * 就不能通过变量p调用这个方法。编译器只允许调用在类中声明的方法。
     * @return
     */
    public abstract String getDescription();
    private String name;

    public Person(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
