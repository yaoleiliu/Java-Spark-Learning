package ObjectAndClass;
import java.util.*;

/**
 * 无论使用哪个构造器构造对象，示实例都在对象初始化块中被初始化。
 * 首先运行初始化块，然后才运行构造器的主体部分。
 */
public class ConstructotTest {
    public static void main(String[] args){
        Employees[] staff = new Employees[3];
        staff[0] = new Employees("Harry", 40000);
        staff[1] = new Employees(60000);
        staff[2] = new Employees();

        for(Employees e: staff){
            System.out.println("name="+e.getName() + ",id="+e.getId() +",salary="+e.getSalary());
        }
    }
}

class Employees{
    private static int id;
    private static int nextId;
    private String name = "";
    private double salary;

    //静态初始化块
    static{
        Random generator = new Random();
        nextId = generator.nextInt();
    }

    //对象初始化块
    {
        id = nextId;
        nextId++;
    }

    //实例域初始化
    public Employees(String n, double s){
        name = n;
        salary = s;
    }

    public Employees(double s){
        this("Employee"+ nextId, s);
    }

    //无参数构造器
    public Employees(){};

    public String getName(){return name;};

    public double getSalary(){return salary;};

    public int getId(){return id;};
}