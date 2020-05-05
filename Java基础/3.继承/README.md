# 继承
利用继承，人们可以基于已存在的类构造一个新类。继承已存在的类就是复用这些类的方法和域。在此基础上，还可以添加一些新的方法和域，以满足新的需求。

## 一、类、超类和子类
```java
public class Manager extends Employee{
    添加方法和域
}
```
关键字extends表明正在构造的新类派生于一个已存在的类。已存在的称为`超类`、 `基类`或`父类`；新类称为`子类`、`派生类`或`孩子类`。</br>
在设计类的时候，应该将通用的方法放在超类中，而将巨头特殊用途的方法放在子类中，这种将通用的功能放到超类的做法，在面向对象程序设计中十分普遍。</br>
超类中的有些方法对子类并不一定适用。此时，需要提供一个新的方法来`覆盖`超类中的方法：
```java
public class Manager extends Employee{
    public double getSalary(){
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }
}
```
### 1.1 子类构造器
```java
public Manager(String name, double salary, int year, int month, int day){
    super(name, salary, year, month, day);
    bonus = 0;
}
```
可以通过super实现对超类构造器的调用。使用super调用构造器的语句必须是子类构造器的第一条语句。</br>
如果子类的构造器没有显示地调用超类的构造器，则将自动地调用超类默认的构造器。如果超类没有不带参数的构造器，并且在子类的构造器中又没有显示地调用超类的其它构造器，则Java编译器将报告错误。</br>
一个对象变量可以指示多种实际类型的现象称为`多态`。在运行时能够自动地选择调用哪个方法的现象称为`动态绑定`。ManagerTest.java文件展示了多态的使用方法。</br>

### 1.2 继承层次
在一个公共超类派生出来的所有类的集合被称为`继承层次`。在继承层次中，从某个特定的类到其祖先的路径被称为该类的`继承链`。</br>
在Java程序设计语言中，对象变量是多态的。一个Employee变量既可以引用一个Employee类对象，也可以引用Employee类的任何一个子类的对象。

### 1.3 方法调用
1.编译器查看对象的声明类型和方法名。假设调用x.f(param)，且隐式参数x声明为c类的对象。有可能存在多个名字为f、但参数类型不一样的方法。编译器会一一列举所有c类中名为f的方法和其超类中访问属性为public且名为f的方法。</br>
2.编译器查看调用方法时提供的参数类型。如果在所有名为f的方法中存在一个与提供的参数类型完全匹配，就选择这个方法。这个过程称为`重载解析`。如果在子类中定义了一个与超类签名相同的方法，那么子类中的这个方法就覆盖了超类中这个相同签名的方法。不过返回类型不是签名的一部分，因此，在覆盖方法时，一定要保证返回类型的兼容性。允许子类将覆盖方法的返回类型定义为原返回类型的子类型。</br>
3.如果是private、static、final或者构造器，那么编译器将可以准确地知道应该调用哪个方法，这种调用方式叫做`静态绑定`。</br>
4.当程序运行，并且采用动态绑定调用方法时，虚拟机一定调用与x所引用对象的实际类型最合适的那个类的方法。假设x的实际类型是D，它是C的子类。如果D类定义了方法f(String)，就直接调用它；否则，将在D类的超类中寻找f(String)，以此类推。</br>
</br>
动态绑定有一个非常重要的特性：无需对现存的代码进行修改，就可以对程序进行扩展。假设新增加类Executive，并且变量e有可能引用这个类的对象，不需要对包含调用e.getSalary()的代码进行重新编译。如果e恰好引用一个Excutive对象，就会自动地调用Excutive.getSalary()方法。<br>
注：在覆盖一个方法时，子类方法不能低于超类方法的可见性。特别是，如果超类方法是public，子类方法一定要声明为public。

### 1.4 阻止继承：final类和方法
```java
public final class Excutive extends Manager{
    ....
}
```
不允许扩展的类称为final类。如果在定义类的时候使用了final修饰符就表明这个类是final类。</br>
类的特定方法也可以被声明为final。如果被声明为final，子类就不能覆盖这个方法（final类中的所有方法自动称为final方法）。例如：</br>
```java
public class Employee{
    ...
    public final String getName(){
        return name;
    }
    ...
}
```
将方法或类声明为final的主要目的是：确保它们不会在子类中改变语义。</br>
如果一个方法没有被覆盖并且很短，编译器就能够对它进行优化处理，这个过程称为`内联`。例如，内联调用e.getName()将被替换为e.name域。

### 1.5 强制类型转换
对象引用的转换语法与数值表达式的类型转换类似，仅需要一对圆括号将目标类名括起来，并且放置在转换的对象引用之前就可以了。例如：</br>
```java
Manager boss = (Manager)staff[0];
```
进行类型转换的唯一原因是：在暂时忽视对象的实际类型之后，使用对象的全部功能。</br>
将一个值存入变量时，编译器将检查是否允许该操作。将一个子类的引用赋给一个超类变量，编译器是允许的。但将一个超类的引用赋给一个子类变量，必须进行类型转换才能通过运行时的检查。</br>
应该养成一个良好的程序设计习惯：在进行类型转换之前，先查看一下是否能够成功地转换。这个过程简单地使用instanceof操作符就能够时间。例如：</br>
```java
if(staff[1] instanceof Manager){
    boss = (Manager) staff[1];
}
```
综上：</br>
* 只能在继承层次内进行类型转换</br>
* 在将超类转换成子类之前，应该使用instanceof进行检查</br>

### 1.6抽象类
```java
public abstract class Person{
    ...
    public abstract String getDescription();
}
```
包含一个或多个抽象方法的类必须被声明为抽象的。除了抽象方法之外，抽象类还可以包含具体数据和具体方法。</br>
抽象方法充当着占位的角色，它们的具体实现在子类中。扩展抽象类可以有两种选择。一种是在抽象类中定义部分抽象类方法或不定义抽象类方法，这样就必须将子类也标记为抽象类；另一种是定义全部的抽象方法，这样一来，子类就不是抽象的了。</br>
抽象类不能被实例化。也就是说，如果一个类声明为abstract，就不能创建这个类的对象。但可以定义一个抽象类的`对象变量`，但是它只能引用非抽象子类的对象。</br>
abstractClasses包中的程序展示了抽象类的用法。</br>

### 1.7 受保护访问
有些时候，人们希望超类中的某些方法允许被子类访问，或允许子类的方法访问超类的某个域。为此，需要将这些方法或域声明为protected。</br>
实际应用中，要谨慎使用protected属性。假设需要将设计的类提供给其它程序员使用，而在这个类中设置了一些受保护域，由于其它程序员可以由这个类派生出新类，并访问其中的受保护域。在这种情况下，如果需要对这个类的实现进行修改，就必须通知所有使用这个类的程序员。这违背了OOP提倡的数据封装原则。</br>
受保护的方法更具有实际意义。如果需要限制某个方法的使用，可以将它声明为protected。这表明子类得到信任，可以正确地使用这个方法，而其他类则不行。</br>
</br>
访问修饰符总结：</br>
* private。仅对本类可见</br>
* public。对所有类可见</br>
* protected。对本包和所有子类可见</br>
* 无修饰符。对本包可见

## 二、Object：所有类的超类
Object类是Java中所有类的始祖，在Java中每个类都是由它扩展而来的。可以使用Object类型的变量引用任何类型的对象：</br>
```java
Object obj = new Employee("Harry Hacker", 35000);
```
### 2.1 equals方法
Object类中的equals方法用于检测一个对象是否等于另外一个对象。在Object类中，这个方法将判断两个对象是否具有相同的引用。如果两个对象具有相同引用，它们一定是相等的。</br>
在子类中定义equals方法时，首先调用超类的equals。如果检测失败，对象就不可能相等，如果超类中的域都相等，就需要比较子类中的实例域。</br>
```java
public class Manager extends Employee{
    ...
    public boolean equals(Object otherObject){
        if(!super.equals(otherObject)) return false;
        Manager other = (Manager)otherObject;
        return bonus==other.bonus;
    }
}
```

### 2.2 相等测试与继承
Java语言规范要求equals方法具有下面一些特性：</br>
* 自反性。对于任何非空引用x，x.equals(x)应该返回true</br>
* 对称性。对于任何引用x和y，当且仅当y.equals(x)返回true，x.equals(y)也应该返回true</br>
* 传递性。对于任何引用x、y和z，如果x.equals(y)返回true，y.equals(z)返回true，则x.equals(z)也返回true</br>
* 一致性。如果x和y引用的对象没有发生变化，反复调用x.equals(y)应该返回同样的结果</br>
* 对于任意的非空引用x,x.equals(null)应该返回false</br>
</br>
下面给出一些编写equals方法的建议：</br>
1.显示参数命名为otherObject，稍后需要将它转换成另一个叫做other的变量</br>
2.检测this与otherObject是否引用同一个对象：</br>

```java
if(this==otherObject) return true;
```
这条语句只是一个优化，因为计算这个等式要比一个一个地比较类中的域所付出的代价小得多。</br>
3.检测otherObject是否为null，如果为null，返回false。</br>
```java
if(otherObject==null) return false;
```
4.比较this与otherObject是否属于同一个类。如果equals的语义在每个子类中有所改变，就使用getClass检测：</br>
```java
if(getClass()!=otherClass.getClass()) return false;
```
如果所有的子类都拥有统一的语义，就使用instanceof检测：</br>
```java
if(!(otherObject instanceof ClassName)) return false;
```
5.将otherObject转换为相应的类类型变量：</br>
```java
ClassName other = (ClassName)otherObject;
```
6.现在开始对所有需要比较的域进行比较了，使用==比较基本类型域，使用equals比较对象域。如果所有域都匹配，就返回true;否则，返回false。</br>

### 2.4 hashCode方法
`散列码`是由对象导出的一个整型值。散列码是没有规律的。如果x和y是两个不同的对象，x.hashCode()与y.hashCode()基本上不会相同。</br>
如果重新定义equals方法，就必须重新定义hashCode方法，以便用户可以将对象插入到散列表中。</br>
Equals与hashCode的定义必须一致：如果x.equals(y)返回true，那么x.hashCode()就必须与y.hashCode()具有相同的值。例如，如果用定义的Employee.equals比较雇员ID，那么hashCode方法就需要散列ID，而不是雇员的姓名或存储地址。</br>

### 2.5 toString方法
绝大多数（但不是全部）的toString方法都遵循这样的格式：类的名字，随后是一对方括号括起来的阈值。下面是Employee类中的toString方法的实现：</br>
```java
public String toString(){
    return getClass().getName()  
        + "[name=" + name
        + ",salary=" + salary
        + ",hireDay=" + hireDay
        + "]";
}
```
随处可见toString方法的主要原因是：只要对象与一个字符串通过操作符“+”连接起来，Java编译就会自动地调用toString方法，以便获得这个对象的字符串描述。如下所示：</br>
```java
Point p = new Point(10,20);
String message = "The current position is" + p;
```
如果x是任意一个对象，并调用：</br>
```java
System.out.println(x);
```
println方法会直接调用x.toString()，并打印输出得到相应的字符串。</br>
Object类定义了toString方法，用来打印输出对象所属的类名和散列码。</br>
equals包下的程序展示了equals方法的使用。

## 三、泛型数组列表
在许多程序设计语言中，必须在编译时就确定整个数组的大小，程序员对此十分反感，因为这么做将迫使程序员作出一些不情愿的折中。</br>
在Java中，解决这个问题最简单的方法是使用Java中另外一个被称为ArrayList的类。它使用起来有点像数组，但在添加或删除元素时，具有自动调节数组容量的功能，而不需要为此编写任何代码。</br>
使用add方法可以将元素添加到数组列表中。如果调用add且内部数组已经满了，数组列表将自动地创建一个更大的数组，并将所有的对象从较小的数据中拷贝到较大的数组中去。</br>
size()方法返回数组列表中实际包含的元素数目。</br>
一旦能够确认数组列表的大小不再发生变化，就可以使用trimToSize方法。这个方法将存储区域的大小调整为当前元素数量所需要的存储空间数目。垃圾回收器将回收多于的存储空间。</br>

### 3.1 访问数据列表元素
使用get和set方法实现访问或改变数组元素的操作。例如要设置第i个元素：</br>
```java
staff.set(i, harry);
```
除了在数组列表的尾部追加元素之外，还可以在数组列表的中间插入元素，使用带索引参数的add方法。</br>
```java
staff.add(n, e);
```
为了插入一个新元素，位于n之后的所有元素都要向后移动一个位置，如果插入一个新元素后，数组列表的大小超过了容量，数组列表就会被重新分配存储空间。同样地，从数组中删除一个元素：</br>
```java
Employee = staff.remove(n);
```
位于这个位置之后的所有元素都向前移动一个位置，并且数组的大小减1。</br>

## 四、对象包装器与自动装箱
有时需要将int这样的基本类型转换为对象。所有的基本类型都有一个与之对应的类。例如，Integer类对应基本类型int。通常，这些类称为`包装器`。</br>
注：由于每个值分别包装在对象中，所以ArrayList<Integer>的效率远远低于int[]数组，因此，应该用它构造小型集合，其原因是此时程序员操作的方便性要比执行效率更加重要。</br>
幸运的是，有一个很重要的特性，从而更加便于添加int类型的元素到ArrayList<Integer>中。下面这个调用：</br>
```java
list.add(3);
```
将自动转换成：</br>
```java
list.add(Integer.valueOf(3));
```
这种变换称为`自动装箱`。</br>
相反地，当将一个Integer对象赋给一个int值时，将会自动拆箱。如：</br>
```java
int n = list.get(i);
```
翻译成：</br>
```java
int n = list.get(i).intValue();
```
甚至在算数表达式中也能够自动地装箱和拆箱。例如，可以将自增操作符应用于宇哥包装器引用：</br>
```java
Integer n = 3;
n++;
```
编译器将自动地插入一条对象拆箱的指令，然后进行自增计算，最后再讲结果装箱。</br>
包装器对象的比较应该使用equals方法。==运算符也可以应用于包装器对象，只不过检测的对象是否指向同一个存储区域。因此，下面的比较通常不会成立：</br>
```java
Integer a = 1000;
Integer b = 1000;
if(a==b)...
```
原因如下：自动装箱规范要求boolean、byte、char<=127，介于-128~127之间的short和int被包装到固定的对象，例如，上面的例子中将a和b初始化为100，对它们进行比较的结果一定成立。</br>
关于自动装箱还有几点需要说明：</br>
1.包装器类引用可以为null，所以自动装箱有可能会跑出一个NullPointerException异常：</br>
```java
Integer n = null;
System.out.println(2*n); // 抛异常
```
2.如果在一个条件表达式中混合使用Integer和Double类型，Integer值就会拆箱，提升为Double，再装箱为Double。</br>
3.装箱和拆箱是编译器认可的，而不是虚拟机。编译器在生成类的字节码时，插入必要的方法调用。虚拟机只是执行这些字节码。

## 五、参数数量可变的方法
在Java SE 5.0以前的版本中，每个Java方法都有固定数量的参数。然而，现在的版本提供了可以用可变的参数数量调用的方法。如下所示：</br>
```java
public class PrintStream{
    public PrintStream print(String fmt, Object...args){return format(fmt, args);}
}
```
这里的省略号...是java代码的一部分，它表明这个方法可以接受任意数量的对象。</br>
用户也可以自定义可变参数的方法，并将参数指定为任意类型，甚至是基本类型。下面是一个简单示例，其功能为计算若干个数值的最大值：</br>
```java
public static double max(double...values){
    double largest = DOUBLE.NEGATIVE_INFINITY;
    for(double v: values) if(v>largest) largest=v;
    return largest'
}
```

## 六、枚举类
```java
public enum Size{SMALL, MEDIUM, LARGE, EXTRA_LARGE};
```
在比较两个枚举类型的值时，永远不需要调用equals，而直接使用“==”就可以了。如果需要的话，可以在枚举类型中添加一些构造器、方法和域。当然，构造器只是在构造枚举常量的时候被调用。</br>
```java
public enum Size{
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");
    private String abbreviation;
    
    private Size(String abbreviation){this.abbreviation=abbreviation;}
    public String getAbbreviation(){return abbreviation;}
}
```
所有的枚举类型都是Enum类的子类。它们继承了这个类的许多方法。其中最有用的是toString，这个方法返回枚举常量名。</br>
enums包下的程序展示了枚举类的使用。

## 七、反射
能够分析类能力的程序称为`反射`，反射机制的功能极其强大，可以用来：</br>
1.在运行时分析类的能力</br>
2.在运行时查看对象，例如，编写一个toString方法供所有类使用</br>
3.实现通用的数组操作代码</br>
4.利用Method对象，这个对象很像C++中的指针</br>
### 7.1 Class类
在程序运行期间，Java运行时系统始终为所有的对象维护一个被称为运行时的类型标识。这个信息跟踪着每个对象所属的类。虚拟机利用运行时类型信息选择相应的方法执行。可以通过专门的Java类访问这些信息。保存这些信息的类被称为Class。Object类中的getClass()方法将会返回一个Class类型的实例.一个Class对象表示一个特定类的属性。</br>
获得Class对象的三种方法：</br>
```java
Class cl = e.getClass();
Class cl = Class.forName(className);
Class cl = int.class;
```
可以利用newInstance()方法动态地创建一个类的实例：</br>
```java
String s = "java.util.Random";
Object m = Class.forName(s).newInstance();
```
反射机制最重要的内容就是检查类的结构。那么它是如何进行的呢？</br>
在java.lang.reflect包中有三个类Field、Mothod和Constructor分别用于描述类的域、方法和构造器。这三个类都有一个叫做getName的方法，用来返回项目的名称。Field类有一个getType方法，用来返回描述域所属类型的Class对象。Method和Constructor类有能够报告参数类型的方法，Method类还有一个可以报告返回类型的方法。这三个类还有一个叫做getModifiers的方法，它将返回一个整型数值，用不同的位开关描述public和static这样的修饰符使用状况。另外，还可以利用java.lang.reflect包中的Modifier类的静态方法分析getModifier返回的整型数值。例如，可以使用Modifier类中isPublic、isPrivate或isFinal判断方法或构造器是否是public、private或final。我们需要做的工作就是调用Modifier类的相应方法，并对返回的整型数值进行分析，另外，还可以利用Modifier.toString方法将修饰符打印出来。</br>
Class类中的getFields、getMethods和getConstructors方法将分别返回类提供的public域、方法和构造器数组，其中包括超类的公有成员。Class类的getDeclareFields、getDeclareMethods和getDeclareConstructors方法将分别返回类中声明的全部域、方法和构造器，其中包括私有和受保护成员，但不包括超类的成员。</br>
reflection包下的程序展示了反射机制的运用。

## 八、继承的设计技巧
1.将公共操作和域放在超类</br>
2.不要使用受保护的域。有些程序员认为，将大多数的实例域定义为protected是一个不错的主意，只有这样，子类才能够在需要的时候直接访问它们。然而，pritected机制并不能带来更好的保护，原因主要有两点。第一，子类集合是无限制的，任何一个人都能够由某个类派生一个子类，并编写代码以直接访问protected的实例域，从而破坏了封装性。第二，在Java程序设计语言中，在同一个包中的所有类都可以访问protected域，而不管它是否为这个类的子类。</br>
3.使用继承实现"is-a"关系</br>
4.除非所有继承的方法否有意义，否则不要使用继承</br>
5.在覆盖方法时，不要改变预期的星星</br>
6.使用多态而非类型信息</br>
7.不要过多地使用反射。反射机制使得程序员可以在运行时查看域和方法，让人们编写出更具有通用性的程序。这种功能对于编写系统程序来说极其实用，但是通常不适合编写应用程序。反射是很脆弱的，即编译器很难帮助人们发现程序中的错误，因此只有在运行时才能够发现错误并导致异常
