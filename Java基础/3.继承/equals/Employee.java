package inheritance.equals;

import java.time.*;
import java.util.Objects;

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day){
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }

    public LocalDate getHireDay(){
        return hireDay;
    }

    public void raiseSalary(double byPercent){
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public boolean equals(Object otherObject){
        //若引用同一个对象，则可以直接判断为相等
        if(this==otherObject) return true;

        //若所比较对象为null，则可以直接判断为不相等
        if(otherObject==null) return false;

        //若不属于同一个类，则可以直接判断为不相等
        if(getClass()!=otherObject.getClass()) return false;

        //对所有的域进行比较
        Employee other = (Employee)otherObject;
        return Objects.equals(name, other.name) && salary==other.salary && Objects.equals(hireDay, other.hireDay);
    }

    public int hashCode(){
        return Objects.hash(name, salary, hireDay);
    }

    public String toString(){
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }
}
