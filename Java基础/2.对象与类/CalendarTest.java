package ObjectAndClass;

import java.time.*;

public class CalendarTest {
    public static void main(String[] args){
        LocalDate date = LocalDate.now(); //获取当前日期
        int month = date.getMonthValue(); //获取当前月份
        int today = date.getDayOfMonth(); //获取当前日期是当前月份的第几天

        date = date.minusDays(today-1); //将日期设置为当前月份的第一天
        DayOfWeek weekDay = date.getDayOfWeek(); //获取第一天是星期几
        int value = weekDay.getValue(); //获取星期几的数值

        System.out.println("Mon Tue Wed Thu Fri Sat Sun"); //打印日历的列名
        for(int i=1; i<value; i++){ //打印第一行的空格
            System.out.print(" ");
        }
        while(date.getMonthValue()==month){ //如果月份是当前月份，一直循环下去
            System.out.printf("%3d", date.getDayOfMonth());
            if(date.getDayOfMonth()==today){
                System.out.print("*");
            }
            else {
                System.out.print(" ");
            }
            date = date.plusDays(1);
            if(date.getDayOfWeek().getValue()==1) System.out.println(); //println有自动换行的功能
        }
        if(date.getDayOfWeek().getValue()!=1) System.out.println();
    }
}
