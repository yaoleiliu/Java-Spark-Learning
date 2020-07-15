## 异常和日志
对于异常情况，例如，可能造成程序崩溃的错误输入，Java使用一种称为`异常处理`的错误捕获机制处理。</br>
当程序出现错误时，并不总是能够与用户或终端进行沟通。此时，可能希望记录下出现的问题，以备日后进行分析。</br>

## 一、处理错误
如果由于出现错误而使得某些操作没有完成，程序应该：</br>
* 返回到一种安全状态，并能够让用户执行一些其他的命令；或者
* 允许用户保存所有操作的结果，并以妥善的方式终止程序</br>

为了能够在程序中处理异常情况，必须研究程序中可能会出现的错误和问题，以及哪类问题需要关注。</br>
1.用户输入错误</br>
2.设备错误</br>
3.物理限制</br>
4.代码错误</br>

### 1.1 异常分类
在Java程序设计语言中，异常对象都是派生于Throwable类的一个实例。如果内置的异常类不能够满足需求，用户可以创建自己的异常类。</br>
[异常层次](https://www.cnblogs.com/springlight/p/6718115.html)</br>
Error类层次结构描述了Java运行时系统的内部错误和资源耗尽错误。应用程序不应该抛出这种类型的对象。如果出现了这样的内部错误，除了通告给用户，并尽力地使程序安全地终止外，再也无能为力了。这种情况很少出现。</br>
派生于RuntimeException的异常通常包含下面几种情况：</br>
* 错误的类型访问
* 数组访问越界
* 访问null指针</br>
Java语言规范将派生于Error类或RuntimrException类的所有异常称为`非受查异常`，所有其他的异常称为受查异常。</br>

### 1.2 声明受查异常
如果遇到了无法处理的情况，那么Java的方法可以抛出一个异常。这个道理很简单：一个方法不仅需要告诉编译器将要返回什么值，还要告诉编译器有可能发生什么错误。方法应在其首部声明所有可能抛出的异常：</br>
```java
public FileInputStream(String name) throws FileNotFoundException
```
这个声明表示这个构造器将根据给定的String参数产生一个FileInputStream对象，但也有可能抛出一个FileNotFoundException异常。如果发生了这种糟糕情况，构造器将不会初始化一个新的FileInputStream对象，而是抛出一个FileNotFoundException类对象。</br>
在遇到下面四种情况时，应该抛出异常：</br>
* 调用一个抛出受查异常的方法，例如，FileInputStream构造器
* 程序运行过程中发现错误，并且利用throw语句抛出一个受查异常
* 程序出现错误，例如，a[-1]=0会抛出一个ArrayIndexOutOfBoundsException这样的非受查异常
* Java虚拟机和运行时库出现的内部错误</br>
对于那些可能被他人使用的Java方法，应该根据异常规范，在方法的首部声明这个方法可能抛出的异常。</br>
```java
class MyAnimation {
    ...
    public Image loadImage(String s) throws IOException{
        ...
    }
}
```
如果一个方法有可能抛出多个受查异常类型，那么就必须在方法的首部列出所有的异常类。每个异常类之间用逗号隔开：</br>
```java
class MyAnimation {
    ...
    public Image loadImage(String s) throws FileNotFoundException, EOFException {
        ...
    }
}
```
但是，不需要声明从Error和RuntimeException继承的那些非受查异常。</br>
如果在子类中覆盖了超类的一个方法，子类方法声明的受查异常不能比超类方法中声明的异常更通用（也就是说，子类方法中可以抛出更特定的异常，或者根本不抛出任何异常）。需要特别说明的是，如果超类方法没有抛出任何受查异常，子类也不能抛出任何受查异常。</br>

### 1.3 创建异常类
在程序中，可能会遇到任何标准异常类都没有能够充分地描述清楚的问题。在这种情况下，创建自己的异常类就是一件顺理成章的事情了。我们需要做的只是定义一个派生于Exception的类，或者派生于Expection子类的类。定义的类应该包含两个构造器，一个是默认的构造器；另一个是带有详细信息的构造器。</br>
```java 
class FileFormatException extends IOSException {
    public FileFormatException {}
    public FileFormatException (String gripe){
        super(gripe);
    }
}
```

## 二、捕获异常
要想捕获一个异常，必须设置一个try/catch语句块。</br>
```java
try {
    code
    more code
    more code
} catch (ExceptionType e){
    handler for this type 
}
```
如果
