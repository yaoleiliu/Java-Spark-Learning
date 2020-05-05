package inheritance.reflection;

import java.util.*;
import java.lang.reflect.*;

public class ReflectionTest {

    public static void main(String[] args){
        String name;
        if(args.length>0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name(e.g. java.util.Data): ");
            name = in.next();
        }

        try{
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if(modifiers.length()>0) System.out.println(modifiers + " ");
            System.out.print("class " + name);
            if(supercl!=null && supercl!=Object.class) System.out.print("extends" + supercl.getName());
            System.out.print("\n\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        System.exit(0);
    }

    public static void printConstructors(Class cl){
        Constructor[] constructors = cl.getDeclaredConstructors();

        for(Constructor c: constructors){
            String name = c.getName();
            System.out.println("  ");
            //用于描述修饰符
            System.out.println("构造器修饰符：");
            String modifiers = Modifier.toString(c.getModifiers());
            if(modifiers.length()>0) System.out.println(modifiers + " ");
            System.out.println(name + "(");

            //打印参数类型
            Class[] paramTypes = c.getParameterTypes();
            for(int j=0; j<paramTypes.length; j++){
                if(j>0) System.out.println(", ");
                System.out.println("构造器参数类型：");
                System.out.println(paramTypes[j].getName());
            }

            System.out.println(");");
        }
    }

    public static void printMethods(Class cl){
        Method[] methods = cl.getDeclaredMethods();

        for(Method m: methods){
            Class retType = m.getReturnType();
            String name = m.getName();
            System.out.println("  ");

            String modiffers = Modifier.toString(m.getModifiers());
            System.out.println("方法修饰符：");
            if(modiffers.length()>0) System.out.println(modiffers + " ");
            System.out.println("方法返回类型：");
            System.out.println(retType.getName() + " " + name + "(");

            Class[] paramTypes = m.getParameterTypes();
            for(int j=0; j<paramTypes.length; j++){
                if(j>0) System.out.println(", ");
                System.out.println("方法参数类型：");
                System.out.println(paramTypes[j].getName());
            }

            System.out.println(");");
        }
    }

    public static void printFields(Class cl){
        Field[] fields = cl.getDeclaredFields();

        for(Field f: fields){
            Class type = f.getType();
            String name = f.getName();
            System.out.println("  ");
            String modiffers = Modifier.toString(f.getModifiers());
            System.out.println("域修饰符：");
            if(modiffers.length()>0) System.out.println(modiffers + " ");
            System.out.println("域名类型：");
            System.out.println(type.getName() + " " + name + ";");
        }
    }
}
