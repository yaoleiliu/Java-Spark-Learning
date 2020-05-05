package inheritance;

public class ManagerTest {
    public static void main(String[] args){
        Manager boss = new Manager("Carl Cracker", 80000, 1987, 12,15);
        Employee[] staff = new Employee[3];

        staff[0] = boss;
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);

        //多态。一个对象变量可以指示多种实际类型，在运行时能够自动选择调用哪个方法
        for(Employee e: staff){
            System.out.println("name="+e.getName() + ",salary=" + e.getSalary());
        }
    }
}
