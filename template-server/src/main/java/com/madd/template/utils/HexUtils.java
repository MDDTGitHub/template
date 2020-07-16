package com.madd.template.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shuishou on 2018/7/28.
 */
public class HexUtils {

   /* public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }*/

    public static  byte[] command(String command){
        String[] msgArray = StringUtils.split(command, ":");
        byte[] msgByte = new byte[msgArray.length];
        int i = 0;
        for (String hexString : msgArray) {
            byte _byte = (byte) hexStringToBytes(hexString)[0];
            msgByte[i] = _byte;
            i++;
        }
        return  msgByte;
    }

    public static  byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
    private static boolean isCheckBeanExitsPropertyName(Class<?> clazz, String propertyName) {
        boolean retValue = false;
        try {
            Field field = clazz.getDeclaredField(propertyName);
            if (null != field) {
                retValue = true;
            }
        } catch (NoSuchFieldException e) {

        }
        return retValue;

    }


    public static void copyBean(Object source, Object target){

    }


    //charToByte返回在指定字符的第一个发生的字符串中的索引，即返回匹配字符
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
