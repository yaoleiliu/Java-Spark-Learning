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

