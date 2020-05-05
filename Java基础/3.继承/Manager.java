package inheritance;

public class Manager extends Employee{
    private double bonus;

    public Manager(String name, double salary, int year, int month, int day){
        //子类不能够直接访问父类的域，因此调用父类的构造函数进行初始化
        super(name, salary, year, month, day);
        bonus = 0;
    }

    public double getSalary(){
        //子类不能够直接访问父类的域，因此调用父类的函数获取域值
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double b){
        bonus = b;
    }
}
