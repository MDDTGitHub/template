package com.madd.template.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/***
 * 
 * @author LFM
 *May 2, 2018
 *TimmerUtils.java
 *
 *<p>方法说明：常用操作time 时间进行换算的方法归�?
   </p>
 */
public class TimmerUtils {
	 private static TimmerUtils instance;
	    private TimmerUtils(){
	    	
	    }
	    public static TimmerUtils getInstance(){
	        if(instance==null){
	            instance=new TimmerUtils();
	        }
	        return instance;
	    }
	/**
     * 指定日期加上天数后的日期
     * @param num 为增加的天数
     * @param newDate 创建时间
     * @return
     * @throws ParseException 
     */
    public static String plusDay(int num,String newDate) throws ParseException{
		System.out.println("当前日期是"+newDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
		Date date = sdf.parse(newDate, new ParsePosition(0));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
		calendar.add(Calendar.DATE, num);
		Date date1 = calendar.getTime();
		String out = sdf.format(date1);
		System.out.println("增加了"+num+"天之后的日期是"+out);
		return  out;
    }
    /***
     * 
     * @param bigDate
     * @param smallDate
     * @return 返回两个时间的天数间�?
     */
    public static int timeDifference(Date bigDate,Date smallDate){
            //将两个时间对象转换成Calendard对象
            Calendar can1 = Calendar.getInstance();
            can1.setTime(bigDate);
            Calendar can2 = Calendar.getInstance();
            can2.setTime(smallDate);
            //拿出两个年份
            int year1 = can1.get(Calendar.YEAR);
            int year2 = can2.get(Calendar.YEAR);
            //天数
            int days = 0;
            Calendar can = null;
            //如果can1 < can2
            //减去小的时间在这�?��已经过了的天�?
            //加上大的时间已过的天�?
            if(can1.before(can2)){
                days -= can1.get(Calendar.DAY_OF_YEAR);
                days += can2.get(Calendar.DAY_OF_YEAR);
                can = can1;
            }else{
                days -= can2.get(Calendar.DAY_OF_YEAR);
                days += can1.get(Calendar.DAY_OF_YEAR);
                can = can2;
            }
            for (int i = 0; i < Math.abs(year2-year1); i++) {
                //获取小的时间当前年的总天�?
                days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
                //再计算下�?���?
                can.add(Calendar.YEAR, 1);
            }
            return days;
    }
    /***
     * 获取当前时间
     * @return
     */
    public static String getNowTime(){
    	Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return df.format(day).toString();
    }
    /***
     * 获取当前时间 精确到毫秒
     * @return
     */
    public static String getNowTimeSSS(){
    	Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		return df.format(day).toString();
    }
	/***
	 * 获取当前时间 精确到毫秒 字符串类型 无日期格式
	 * @return
	 */
	public static String getNowTimeSSS_str(){
		Date day=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return df.format(day).toString();
	}
    /**
     * Date转String 里面有三种定义的格式可以根据自己想要得进行再次封�?
     * @param date
     * @return
     */
    public static String dateToString(Date date){
//    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd�?HH:mm:ss");
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	 String dateStr = sdf.format(date);
    	 return dateStr;
    }
    /***
     * 传进的参数和今天做对�? 如果是小于今�?也就是昨�?返回before 如果大于今天是明�?返回after
     * 异常处理 返回null
     * @param myString
     * @return
     */
    public static String checkNowTime(String myString){
    	Date nowdate=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		Date d;
		try {
			d = sdf.parse(myString);
			boolean flag = d.before(nowdate);
			if(flag){
//				System.out.print("早于今天");
				return "before";
			}
			else{
//				System.out.print("晚于今天");
				return "after";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

	public static void main(String[] args) throws ParseException {
		System.out.println(TimmerUtils.getInstance().plusDay(1,"2018-11-03"));
	}
}
