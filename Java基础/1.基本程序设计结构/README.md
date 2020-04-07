# 基本程序设计结构
## 1.一个简单的Java应用程序
```Java
public class FirstSample {
  public static void main(String[ args]){
    System.out.println("we will not use 'Hello World'");
  }
}
```
首先，Java区分大小写。如果出现了大小写拼写错误（例如，将mian拼写成Main）程序将无法运行;</br>
关键字public称为`访问修饰符`,这些修饰符控制程序的其它部分对这段代码的访问级别;</br>
关键字class表明Java程序中的全部内容都包含在类中。这里，只需要将类作为加载程序逻辑的容器，程序逻辑定义了应用程序的行为；</br>
关键字calss后面紧跟类名。Java中定义类名的规则很宽松。名字必须以字母开头，后面可以跟字母和数字的任意组合。标准的命名规范是：类名是以大写字母开头的名词。如果名字是由多个单词组成，每个单词的第一个字母都应该大写；</br>
源代码的文件名必须与公共类的名字相同，并用.Java作为扩展名。</br>
注释一般有以下两种：</br>
* //。其注释内容从//开始到本行结尾
* /* ... */。将一段比较长的注释括起来

## 2.数据类型
在Java中，一共有8种基本类型，其中，有4种整型、2种浮点类型、1种用于表示Unicode编码的字符类型char和一种用于表示真值的boolean类型。</br>

### 2.1整型
类型|存储需求|取值范围
-|-|-
int|4字节|-2147483648~2147483647（正好超过20亿）
short|2字节|-32768~32767
long|8字节|-9223372036854775808~9223372036854775807
byte|1字节|-128~127

在Java中，整型的范围与运行Java代码的机器无关。`这就解决了软件从一个平台移植到另一个平台，或者在一个平台中的不同操作系统之间进行移植给程序员带来的诸多问题`，与此相反C或C++程序需要针对不同的处理器选择最为高效的整型，这样就有可能造成在一个32位处理器上运行很好的C程序在16位操作系统上运行却发生整数溢出。由于Java程序必须保证在所有机器上得到相同的运行结果，所以各种数据类型的取值范围必须固定。

### 2.2浮点类型
类型|存储需求|取值范围
-|-|-
float|4字节|大约正负3.40282347E+38F（有效位数为6~7位）
double|8字节|大约正负1.797693134862315E+308（有效位数为15位）

double类型的数值精度是float类型的两倍，绝大部分应用程序都采用类型。float类型的数值有一个后缀F或f。没有后缀F的浮点数值默认是double类型。</br>
下面是用于表示溢出和出错情况的三个特殊的浮点数值：</br>
* 正无穷大
* 负无穷大
* NaN（不是一个数字）</br>

例如，一个正整数除以0的结果为正无穷大。计算0 / 0 或者负数的平方根结果为NaN。</br>
常量Double.POSITIVE_INFINITY、Double.NEGATIVE_INFINITY和Double.NaN分别表示这三个特殊的值。</br>
`注意：不能这样检测一个特定值是否等于Double.NaN`：</br>
```Java
if(X==Double.NaN) // is never true
```
所有“非数值”的值都认为是不相同的。然而，可以使用Double.isNaNaN方法：</br>
```Java
if(Double.isNaN(X)) // check wheather X is "not a number"
``` 

### 2.3 Unicode和char类型
char类型的字面量值要用单引号括起来。例如：'A'是编码值为65对应的字符常量。它与“A”不同，“A”是一个包含A的字符串。char类型的值还可以表示为十六进制值。</br>
要想弄清楚char类型，必须了解Unicode编码机制。Unicode打破了传统字符编码机制的限制。在Unicode出现之前，已经有许多不同的标准：美国的ASCII、西欧语言中的ISO 8859-1、中国的GB 18030和BIG-5等。这样就产生了下面两个问题：`一个是对于任意的编码值，在不同编码方案下有可能对应不同的字母；二是采用大字符集的语言其编码长度有可能不同。例如，有些常用的字符采用单字节编码，而另一些字符则需要两个或更多的字节。`</br>
`码点`是指与一个编码表中的某个字符对应的代码值。UTF-16编码采用不同长度的编码表示所有的Unicode码点。在基本的多语言级别中，每个字符用16位表示，通常称为代码单元；而辅助字符采用一对连续的代码单元进行编码，这样构成的编码值落入基本的多语言级别中空闲的2048字节内，同城被称为`替代区域`。</br>
在Java中，char类型描述了UTF-16编码中的一个代码单元。</br>
`强烈建议不要在程序中使用char类型，除非特别需要处理UTF-16代码单元。最好将字符串作为抽象数据类型处理`。

### 2.4 boolean类型
boolean（布尔）类型有两个值：false和true，用来判定逻辑条件。整型和布尔值之间不能进行相互转换。

## 3.变量、常量和枚举
变量名必须是一个以字母开头并由字母或数字构成的序列。变量中所有的字符都是有意义的，并且大小写敏感。变量名的长度基本上没有限制。在Java中，变量的声明尽可能靠近变量第一次使用的地方，这是一种良好的程序编写风格。</br>
在Java中，利用关键字final指示常量：</br>
```Java
public class Contants{
  public static void main(String[] args){
    final double CM_PER_INCH = 2.54;
    double paperWidth = 8.5;
    double paperHeight = 11;
    System.out.println("paper size in centimeters:" + paperWidth*CM_PER_INCH + "by" + paperHeight*CM_PER_INCH);
  }
}
```
关键字final表示这个变量只能够被赋值一次。一旦被赋值以后，就不能够再改变了。一般常量用全大写表示。</br>
在Java中，经常希望某个常量可以在一个类中的多个方法中使用，通常将这些常量称为类常量，使用关键字static final表识。</br>
```Java
public class Contants{

  final double CM_PER_INCH = 2.54;

  public static void main(String[] args){
    double paperWidth = 8.5;
    double paperHeight = 11;
    System.out.println("paper size in centimeters:" + paperWidth*CM_PER_INCH + "by" + paperHeight*CM_PER_INCH);
  }
}
```
类常量的定义在main函数的外面。因此，在同一个类的其他方法中也可以使用这个常量。而且，如果一个常量被声明为public，那么其它类的方法也可以使用这个常量。</br>
有时候，变量的取值在一个有限的集合内。针对这种情况，可以自定义枚举类型。枚举类型包括有限个命名的值。例如：
```Java
enum Size {SMALL, MEDIUM, LARGE, EXTRA_LARGE};
```
现在，可以声明这种类型的变量：
```Java
Size s = Size.MEDIUM;
``` 
Size类型的变量只能存储这个类型生命中给定的某个枚举值或者null值，null表示这个变量没有设置任何值。

## 4.字符串
Java没有内置的字符串类型，而是在Java类库中提供了一个预定义类，叫做String。每个用双引号括起来的字符串都是String类的一个实例：</br>
```Java
String e = ""; // an enpty string 
String greeting = "Hello";
```

### 4.1 常用方法
```java
string s = greeting.substring(0,3); // substring可以从一个较大的字符串提取出一个子串Expletive

/* Java允许使用+号连接两个字符串，
当将一个字符串与一个非字符串的值进行拼接时，后者被转换成字符串。
*/
String expletive = "Expletive"; String PG13 = "deleted"; String message = expletive + PG13;

//可以用equals方法检测两个字符串相等，不能使用==来检测两个字符串是否相等
s.equals(t);
"Hello".equals(greeting);
"Hello".equalsIgnoreCase("hello");不区分大小写地检测两个字符串是否相等

//空串是长度为0的字符串，可以用以下代码检测一个字符串是否为空
if(str.length()==0) 
或
if(str.equals(""))

if(str==null) //检测一个字符串是否为null
if(str!=null && str.length()!=0) //检查一个字符串既不是空串也不是null

//如果需要用许多小段的字符串构建一个字符串，应该按照如下步骤进行
1.构建一个空的字符串构建起：
StringBuilder builder = new StringBuilder();

2.当每次需要添加一部分内容时，调用append方法
builder.append(ch);
builder.append(str);

3.需要构建字符串时，就调用toString方法，可以得到一个String对象：
String completedString = builder.toString();

//可以使用静态的String.format方法创建一个格式化的字符串：
String message = String.format("Hello, %s. Next year, you will be %d", name, age);
```

## 5.数组
### 5.1 大数值
如果基本的整数和浮点数精度不能够满足需求，那么可以使用java.math包中的两个很有用的类：BigInteger和BigDecimal。这两个类可以处理包含任意长度数字序列的数值。BigInteger类实现了任意精度的整数运算，BigDecimal实现了任意精度的浮点数运算。</br>
使用静态的valueOf方法可以将普通的数值转换为大数值：</br>
```Java
BigInteger a = BigInteger.valueOf(100);
```
遗憾的是，这里不能使用人们熟悉的算术运算符（如+和\*）处理大数值。而需要使用大数值类中的add和multiply方法：</br>
```Java
BigInteger c = a.add(b);
BigInteger d = c.multiply(b.add(BigInteger.valueOf(2)));
```

### 5.2 数组
创建一个数组：</br>
```Java
int[] a = new int[100];
```
创建一个数字数组时，所有元素都初始化为0。boolean数组的元素会初始化为false。对象数组的元素则初始化为一个特殊值null，表示这些元素还为存放任何对象。</br>

数组拷贝：</br>
在Java中，允许将一个数组变量拷贝给另一个数组变量。这时，`两个变量将引用同一个数组：`</br>
```Java
int[] luckyNumbers = smallPrimes;
luckyNumbers[5] = 12;
```

Java有一种功能很强的循环结构，可以用来依次处理数组中的每个元素而不必为指定下标值而分心。</br>                                             
```Java
for(variable: collection) statement
```
定义一个变量用于暂存集合中的每一个元素，并执行相应的语句。collection这一表达式必须是一个数组或者一个实现了Iterable接口的类对象。</br>

要想对数值型数组进行排序，可以使用Arrays类中的sort方法：</br>
```Java
int[] a = new int[100];
Arrays.sort(a)
```
这个方法使用了优化的快速排序方法。快速排序方法对于大多数数据集合来说都是效率非常高的。
