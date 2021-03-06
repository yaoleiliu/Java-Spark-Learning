# 对象与类
## 一、面向对象程序设计概述
面向对象程序设计（简称OOP）是当今主流的程序设计范例。Java是完全面向对象的，必须熟悉OOP才能够编写java程序。

## 1.1 类
类是构造对象的模板或蓝图。由类构造对象的过程称为创建类的实例。</br>
`封装`（encapsulation，也称为数据隐藏）是与对象有关的一个重要概念。从形式上来看，封装不过是将数据和行为组合在一个包中，并对对象的使用者隐藏了数据的实现方式。对象中的数据称为`实例域`，操纵数据的过程称为`方法`。对于每个特定类实例都有一组特定的实例域值，这些值的集合就是这个对象的当前`状态`。

## 1.2 对象
要想使用OOP，需要弄清楚对象的三个特性：</br>
* `对象的行为`：可以对对象施加哪些操作，或可以对对象施加哪些方法？</br>
* `对象的状态`：当施加那些方法时，对象如何响应？</br>
* `对象的标识`：如何辨别具有相同行为与状态的不同对象？

## 1.3 类之间的关系
* `依赖`：一个类的方法操作另一个类的对象</br>
* `聚合`：类A的对象包含类B的对象，如一个Order对象包含一些Item对象</br>
* `继承`：用于表示特殊与一般的关系。一般而言，如果类A扩展类B，类A不但包含从类B继承的方法，还会拥有一些额外的功能

## 二、使用预定义类
### 2.1 对象与对象变量
在java中，任何对象变量的值都是对存储在另外一个地方的一个对象的引用。可以显示地将对象变量设置为null，表示这个对象变量目前没有引用任何对象。</br>
* `更改器方法`：调用这个方法后，对象的状态会改变</br>
* `访问器方法`：只访问对象而不修改对象的方法</br>

该文件夹下的CalendarTest.java文件展示了java中两个日期类的使用方法。

### 2.2 用户自定义类
一个复杂的应用程序通常需要各种`主力类`。这些类没有main方法，却有自己的实例域和实例方法。要想创建一个完整的程序，应该将若干类组合在一起，其中只有一个类有main方法。</br>
在一个源文件中，只能有一个公有类，但可以有任意数目的非公有类。</br>
构造器总是伴随着new操作符的执行被调用，而不能对一个已经存在的对象调用构造器达到重新设置实例域的目的。</br>
在每一个方法中，关键字this表示隐士参数，如下面的程序编写方法：</br>
```java
public void raiseSalary(double byPercent){
    double raise = this.salary * byPercent / 100;
    this.salary += raise;
}
```
这样写的好处是可以将实例域与局部变量明显地区分开来。</br>
类的封装主要通过下面三项来实现：</br>
* 一个私有的数据域</br>
* 一个公有的域访问器方法</br>
* 一个公有的域更改器方法</br>
这么做比直接提供一个公有的数据域复杂些，但是却有着下列明显的好处：</br>
* 可以改变内部实现，除了该类的方法外，不会影响其它代码</br>
* 更改器方法可以执行错误检查

### 2.3基于类的访问权限
在实现一个类时，由于公有数据非常危险，所以应该将所有的数据域都设置为私有的。</br>
`final实例域`：可以将实例域定义为final，构建对象时必须初始化这样的域。也就是说，必须确保在每一个构造器执行之后，这个域的值被设置，并且在后面的操作中，不能够再对它进行修改。</br>
`静态域`：静态域属于类，而不属于任何独立的对象。</br>
`静态常量`：</br>
```java
public class Math{
    ...
    public static final double PI = 3.1415926;
    ...
}
```
在程序中，可以通过Math.PI的形式获取这个常量</br>
`静态方法`：是一种不能向对象实施操作的方法，既不需要实例化对象即可使用。但静态方法可以访问自身类中的静态域。在下面两种情况下使用静态方法：</br>
* 一个方法不需要访问对象状态，其所需参数都是通过显示参数提供</br>
* 一个方法只需要访问类的静态域</br>
`工厂方法`：具体用法可参见Factory文件夹·</br>
StaticTest.java文件展示上述几个概念的使用方法

## 三、方法参数
`按值调用`表示方法接收的是调用者提供的值；`按引用调用`表示方法接收的是调用者提供的变量地址。Java程序设计语言总是按值调用：
* 一个方法不能修改一个基本数据类型的参数（即数值型或布尔型）</br>
* 一个方法可以改变一个对象参数的状态</br>
* 一个方法不能让对象参数引用一个新的对象</br>
ParamTest.java文件展示中
StaticTest.java文件展示上述几个概念的使用方法展示了上述三个特定。

## 对象构造
如果多个方法有相同的名字、不同的参数，便产生了重载。</br>
如果在构造器中没有显示地给域赋予初值，那么就会被自动地赋予默认值：数值为0、布尔值为false、对象引用为null。</br>
很多类都包含一个无参数的构造函数，对象由无参数构造函数创建时，其状态会设置为适当的默认值。如果类中提供了至少一个构造器，但是没有提供无参数的构造器，则在构造对象是如果没有提供参数就会被视为不合法。</br>
在一个类的声明中，可以包含多个代码块。只要构造类的对象，这些块儿就会被执行。</br>
```java
public Employee{
    public static int nextId;
    private int id;
    private String name;
    private double salary;
    
    //块儿初始化
    {
        id = nextId;
        nextId++;
    }
    
    public Employee(String n, double s){
        name = n;
        salary = s;
    }
    
    public Employee(){
        name = "";
        salary = 0;
    }
}
```
在这个示例中，无论使用哪个构造器构造对象，id域都在对象初始化块中被初始化。首先运行初始化块，然后才运行构造器的主体部分。</br>
如果对类的静态域进行初始化的代码比较复杂，可以使用静态的初始化块：</br>
```java
static {
    Random generator = new Random();
    nextId = generator.nextInt(10000);
}
```
ConstructotTest.java文件展示了对象构造的几种路径。</br>

## 四、包
一个类可以使用所属包中的所有类，以及其他包中的`公有类`。</br>
标记为public的部分可以被任意的类使用；标记为private的部分职能被定义它的类使用。如果没有指定public货private，这个部分可以被同一个包中的所有方法访问。</br>

## 五、类设计技巧
* 一定要保证数据私有</br>
* 一定要对数据初始化</br>
* 不要在类中使用过多的基本类型</br>
* 不是所有的域都需要独立的域访问器或域更改器</br>
* 将职责过多的类进行分解
