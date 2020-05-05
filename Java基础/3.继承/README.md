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
6) </br>
