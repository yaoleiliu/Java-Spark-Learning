package ObjectAndClass;

/**
 * 创建Employee类，供后续测试程序使用
 */

import java.time.LocalDate;

public class Employee {
    public static int nextId = 1;

    private String name;
    private double salery;
    private int id;

    public Employee(String n, double s){
        name = n;
        salery = s;
        id = 0;
    }

    public String getName(){
        return name;
    }

    public double getSalery(){
        return salery;
    }

    public int getId(){
        return id;
    }

    public void setId(){
        id = nextId;
        nextId++;
    }

    public static int getNextId(){
        return nextId;
    }

    public void raiseSalary(double percent){
        double raise = salery * percent / 100;
        salery += raise;
    }
}

