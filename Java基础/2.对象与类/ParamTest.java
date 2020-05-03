package ObjectAndClass;

/**
 * java程序设计语言总是采用按值调用，该程序展示java参数传递的用法
 */
public class ParamTest {
    public static void main(String[] args){
        /*
        * 函数无法改变基本数据类型的参数
         */
        System.out.println("Testing tripleValue:");
        double percent = 10;
        System.out.println("Before: percent=" + percent);
        tripleValue(percent);
        System.out.println("After: percent=" + percent);

        /*
        * 函数可以改变对象参数的状态
         */
        System.out.println("\nTesting tripleSalary");
        Employee harry = new Employee("Harry", 50000);
        System.out.println("Before: salary="+ harry.getSalery());
        tripleSalary(harry);
        System.out.println("After: salary="+ harry.getSalery());

        /*
        * 函数不能让一个对象参数引用一个新的对象
         */
        System.out.println("\nTesting swap:");
        Employee a = new Employee("Alice", 70000);
        Employee b = new Employee("Bob", 60000);
        System.out.println("Before: a=" + a.getName());
        System.out.println("Before: b=" + b.getName());
        swap(a, b);
        System.out.println("After: a=" + a.getName());
        System.out.println("After: b=" + b.getName());
    }

    public static void tripleValue(double x){
        x = 3 * x;
        System.out.println("End of method: x=" + x);
    }

    public static void tripleSalary(Employee x){
        x.raiseSalary(200);
        System.out.println("End of method: x=" + x);
    }

    public static void swap(Employee x, Employee y){
        Employee tmp = x;
        x = y;
        y = tmp;
        System.out.println("End of method: x=" + x.getName());
        System.out.println("End of method: y=" + y.getName());
    }
}
