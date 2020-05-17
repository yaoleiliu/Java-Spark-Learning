# 接口、lambda表达式与内部类
## 一、接口
### 1.1 接口概念
接口是对类的一组需求描述，这些类需要遵从接口描述的统一格式进行定义。下面给出一个具体的示例：Arrays类中的sort方法承诺可以对对象数组进行排序，但要求满足下列前提，对象所属的类必须实现了Comparable接口：</br>
```java
public interface Comparable{
    int compareTo(Object other);
}
```
这就是说，任何实现Comparable接口的类都必须包含compareTO方法，并且这个方法的参数必须是一个Object对象，返回一个整型数值。</br>
接口中的所有方法自动地属于public。因此，在接口中声明方法时，不必提供关键字public。此外，接口中还有一个没有明确说明的附加要求：在调用x.compareTo(y)的时候，这个方法必须确实比较两个对象的内容，并返回比较的结果。当x<y时，返回一个负数；当x=y时，返回0；否则，返回一个正数。</br>
接口决不能含有实例域，在Java SE 8之前，在接口中不能实现方法，现在可以在接口中提供简单方法了，当然，仍旧不能引用实例域。</br>
假设希望使用Arrays类的sort方法对Employee对象数据进行排序，Employee类就必须实现Compable接口。为了让类实现一个接口，通常需要下面两个步骤：</be>

1) 将类声明实现给定的接口</br>
2) 对接口中的所有方法进行定义</br>

要将类声明为实现某个接口，需要使用关键字implements。需要注意的是，在实现接口时，必须把方法声明为public；否则，编译器将认为这个方法的访问属性是包可见性，即类的默认访问属性。</br>
```java
class Employee implements Comparable<Employee>{
    public int compareTo<Employee other>{
        return Double.compare(salary, other.salary);
    }
}
```
为什么不能在Employee类中直接提供一个compareTo方法，而必须实现Comparable接口呢？主要原因在于Java程序设计语言是一种`强类型`语言。在调用方法的时候，编译器会检查这个方法是否存在。在sort方法中可能存在下面这样的语句：</br>
```java
if(a[i].compareTo(a[j])>0){
    // rearrange a[i] and a[j]
    ...
}
```

为此，编译器必须确认a[i]一定有compareTo方法。如果a是一个Comparable对象的数组，就可以确保拥有compareTo方法，因为每个实现Comparable接口的类都必须提供这个方法的定义。interface文件夹的程序展示了Employee类的详细情况。</br>
与equals方法一样，在继承过程中有可能会出现问题。这是因为Manager扩展了Employee，而Employee实现的是Comparable<Employee>，而不是Comparable<Manager>。如果Manager覆盖了compareTo，就必须要有经理与雇员进行比较的思想准备，决不能仅仅将雇员转换为经理。</br>
```java
class Manager extends Employee{
    public int compareTo(Employee other){
        Manager otherManager = (Manager)other; //No
    }
}
```
这不符合“反对称”的规则。如果x是一个Employee对象，y是一个Manager对象，调用x.compareTo(y)不会抛出异常，它只是将x和y都作为雇员进行比较。但是反过来，y.compareTo(x)将会抛出一个ClassCastException。</br>
修改方式和equals方法一样，有两种不同的情况：</br>

1) 如果子类之间的比较含义不一样，那就属于不同类对象的非法比较。每个compareTo方法都应该在开始时进行下列检测:
```java
if(getClass()!=other.getClass()) throw new ClassCastException();
```

2) 如果存在这样一种通用算法，他能够对两个不同的子类对象进行比较，则应该在超类中提供一个compareTo方法，并将这个方法声明为final。

### 1.2 接口特性
接口不是类，不能使用new运算符实例化一个接口。然而，却能够声明接口的变量，接口变量必须引用实现了接口的对象：</br>
```java
Comparable x;
x = new Employee(...);
```

可以使用instance检查一个对象是否实现了某个特定的接口：</br>
```
if(anObject instance Comparable){...}
```

与可以建立类的继承关系一样，接口也可以被扩展。这里允许存在多条从具有较高通用性的接口到较高专用性接口的链。例如有一个称为Moveable的接口：
```java
public interface Moveable{
    void move(double x, double y);
}
```

然后，以它为基础扩展一个叫做Powered的接口：</br>
```java
public interface Powered extends Moveable{
    double milesPerGallon();
}
```

虽然在接口中不能包含实例域或静态方法，但却可以包含常量。例如：</br>
```java
public interface Powered extends Moveable{
    double milesPerGallon();
    double SPEED_LIMIT = 95;
}
```

### 1.3 接口与抽象类
有了抽象类，为什么还需要引入接口的概念呢？使用抽象类表示通用属性存在这样一个问题：每个类只能扩展于一个类，但每个类却可以实现多个接口。
```java
class Employee extends Person, Comparable
```
有些程序设计语言允许一个类有多个超类，例如C++。这种特性称作多重继承。而java的设计者选择了不支持多继承，其主要原因是多重继承会让语言变得非常复杂，效率也会降低。

### 1.4 默认方法
可以为接口方法提供一个默认实现，必须用default修饰符标记这样一个方法。</br>
```java
public interface MouseListener{
    default void mouseClicked(MouseEvent event){}
}
```
默认方法一个重要用途是“接口演化”。以Collection接口为例，这个接口作为Java的一部分已经有很多年了。假设很久以前你提供了这样一个类：</br>
```java
public classBag implements Collection
```
后来在Java SE 8中，又为这个接口增加了一个stream方法。假设stream方法不是一个默认方法，那么Bag类将不能编译，因为它没有实现这个新方法。为借口增加一个非默认方法不能保证“源代码兼容”。</br>
假设不重新编译这个类，而只是使用原先的一个包含这个类的JAR文件，这个类仍能够正常加载，尽管没有这个新方法，程序仍然可以正常构造Bag实例，不会有意外发生。不过，如果程序在一个Bag实例上调用了stream方法，就会出现一个AbstractMethodError。</br>
将接口方法实现为一个默认方法就可以解决这两个问题。Bag类又能够正常编译了。另外如果没有重新编译而直接加载这个类，并在一个Bag实例上调用stream方法，将调用Collection.stream方法。

### 1.5 解决默认方法冲突
如果先在一个接口中将一个方法定义为默认方法，然后又在超类或另一个接口中定义了同样的方法，会发生什么情况？Java解决这种二义性的规则如下：</br>
1) 超类优先。如果超类提供了一个具体方法，同名而且具有相同参数类型的默认方法会被忽略。</br>
2) 接口冲突。如果一个超接口提供了一个默认方法，另一个接口提供了一个同名而且参数类型相同的方法，必须覆盖这个方法来解决冲突。</br>

来看一下第二个规则，考虑另一个包含getName方法的接口:</br>
```java
interface Named{
    default String getName() {return getClass().getName() + "_" + hasCode();}
}
```

假设一个类同时实现了着两个接口会怎么样呢？</br>
```java
class Student implements Person, Names{
    ...
}
```

类会继承Person和Named接口提供的两个不一致的getName方法。并不是从中选择一个，Java编译器会报告一个错误，让程序员解决这个二义性。只需要在Student类中提供一个getName方法。在这个方法中。可以选择两个冲突方法中的一个。如下所示：</br>
```java
class Student implements Person, Named{
    public String getName() {return Person.super.getName();}
}
```

## 二、接口示例
### 2.1 接口与回调
`回调`是一种常见的程序设计模式。在这种模式中，可以指出特定事件发生时应该采取的动作，例如，可以指出按下鼠标或选择某个菜单项时应该采取什么行动。timer文件夹下的程序展示了这部分内容的使用。

### 2.2 Comparator接口
String类默认已经实现了Comparable<String>，而且String.compareTo方法可以按字典顺序比较字符串。现在我们假设希望按照长度递增的顺序对字符串进行排序，而不是按照字典顺序进行排序。肯定不能让String类用两种不同的方式实现compareTo方法，更何况，String类也不应该改我们来修改。要处理这种情况，Arrays.sort方法还有第二个版本，有一个数组和一个比较器作为参数，比较器是实现了Comparable接口的类的实例。</br>
```java
public interface Comparator<T>{
    int compare(T first, T second);
}
```
要按长度比较字符串，可以如下定义一个实现Comparator<String>的类：</br>
```java
class LengthComparator implements Comparator<String>{
    public int compare(String first, String second){
        return first.length() - second.length();
    }
}
```

要对一个数组排序，需要为 Arrays.sort方法传入一个LengthComparator对象：</br>
```java
String[] friends = {"Peter", "Paul", "Mary"};
Arrays.sort(friends, new LengthComparator());
```

### 2.3 对象克隆
clone方法是Object的一个Protected方法，这说明你的代码不能直接调用这个方法。只有Employee类可以克隆Employee对象。这个限制是有原因的，对于Object类来说，它对于这个对象一无所知，所以只能逐个域地进行拷贝。如果对象中的所有数据域都是数值或其它基本类型，拷贝这些域没有任何问题。但是如果对象包含子对象的引用，拷贝域就会得到相同子对象的另一个引用，这样一来，原对象与克隆的对象仍然会共享一些信息。</br>
对于每一个类，需要确定：</br>
1) 默认的clone方法是否满足要求;</br>
2) 是否可以在可变的对象上调用clone来修补默认的clone方法;</br>
3) 是否不该使用clone方法。</br>

实际上第3个选项是默认选项。如果选择第1项或第2项，类必须：</br>
1) 实现Cloneable接口;</br>
2) 重新定义clone方法，并指定public访问修饰符。</br>

Cloneable接口是Java提供的一组标记接口之一。标记接口不包含任何方法，它唯一的作用就是允许在类查询中使用instanceof。</br>
必须当心子类的克隆。例如，一旦为Employee类定义了clone方法，任何人都可以用它来克隆Manager对象。Employee克隆方法能完成工作吗？这取决于Manager类的域。在这里是没有问题的，因为bonus域是基本类型。但是Manager可能会有需要深拷贝或不可克隆的域。不能保证子类的实现者一定会修正clone方法让它正常工作。出于个原因，在Object类中clone方法声明为protected。不过，如果你希望类用户调用clone，就不能这么做。</br>
clone文件夹下的程序展示了对象克隆.</br>
