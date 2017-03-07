package com.py.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 *
 * @author ganfeng
 */
public class CommonUtil {

    private static Logger log = Logger.getLogger(CommonUtil.class);

    /**
     * 字符串结合
     *
     * @param strs 需要结合的字符串
     * @return
     */
    public static String combineStrs(String... strs) {

        if ((strs == null) || (strs.length == 0)) {
            return "";
        }

        StringBuilder keyBuff = new StringBuilder();

        for (int i = 0; i < strs.length; i++) {
            if (strs[i] != null) {
                keyBuff.append(strs[i]);
            }
        }

        return keyBuff.toString();
    }

    /**
     * 随机获取某一长度的字符串"0-z"
     *
     * @param length 需要的字符串长度
     * @return
     */
    public static String getRandomString(int length) {

        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 随机获取某一长度的数值字符串"0-9"
     *
     * @param length 需要的字符串长度
     * @return
     */
    public static String getRandomNumber(int length) {

        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * MD5加密
     *
     * @param plainText
     * @return
     */
    public static String EncoderByMd5(String plainText) {
        String md5str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            md5str = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5str;
    }

    public static String EncoderPwdByMd5(String str) {
        String newstr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            newstr = Base64
                    .encodeBase64String(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            return null;
        }
        return newstr;
    }


    public static Integer toInteger(Object target) {
        return toInteger(target, null);
    }

    /**
     * @param target
     * @param defaultValue
     * @return
     */
    public static Integer toInteger(Object target, Integer defaultValue) {

        if (isNullOrEmpty(target)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(toString(target).trim());
        } catch (Exception ex) {
            System.out.println();
            return defaultValue;
        }
    }

    /**
     * @param target
     * @return
     */
    public static boolean isNullOrEmpty(Object target) {

        if (isNull(target)) {
            return true;
        }
        return isEmpty(target);
    }

    /**
     * @param target
     * @return
     */
    public static boolean isEmpty(Object target) {

        if (isNull(target)) {
            return false;
        }
        Class<?> clazz = target.getClass();
        if (String.class.isAssignableFrom(clazz)) {
            return isEmpty(toString(target));
        }
        if (Iterable.class.isAssignableFrom(clazz)) {
            return isEmpty((Iterable<?>) target);
        }

        if (Map.class.isAssignableFrom(clazz)) {
            return isEmpty((Map<?, ?>) target);
        }
        if (clazz.isArray()) {
            return Array.getLength(target) < 1;
        }
        return isEmpty(toString(target));
    }

    public static boolean isEmpty(String target) {
        return isEmpty(target, true);
    }

    public static boolean isEmpty(String target, boolean ignoreSpace) {
        if (target == null)
            return false;
        if (ignoreSpace)
            target = trim(target);

        return "".equals(target);
    }

    public static boolean isEmpty(Iterable<?> target) {
        if (isNull(target))
            return false;

        return !target.iterator().hasNext();
    }

    public static String trim(String target) {
        if (target == null)
            return "";

        return target.trim();
    }

    /**
     * @param target
     * @return
     */
    public static boolean isNull(Object target) {
        return target == null;
    }

    /**
     * @param target
     * @return
     */
    public static String toString(Object target) {
        if (isNull(target)) {
            return "";
        }

        return target.toString();
    }

    /**
     * @param arg0
     * @param arg1
     * @return
     */
    public static boolean equal(Object arg0, Object arg1) {
        if (isNull(arg0) && isNull(arg1)) {
            return true;
        }
        if (isNull(arg0) || isNull(arg1)) {
            return false;
        }
        return arg0.equals(arg1);
    }

    /**
     * listת��Ϊjson��ʽ�ַ�
     *
     * @param list list����
     * @param keys �ؼ���
     * @return string
     */
    public static String listToJsonString(List list, String... keys) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("[");
        if (isNullOrEmpty(list)) {
            return "";
        }
        try {
            int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                if (i != listSize - 1) {
                    jsonString.append("{");
                    for (int j = 0; j < keys.length; j++) {
                        if (j != keys.length - 1) {
                            jsonString.append(keys[j]);
                            jsonString.append(":'");
                            jsonString
                                    .append(invokeMethod(list.get(i), keys[j]));
                            jsonString.append("',");
                        } else {
                            jsonString.append(keys[j]);
                            jsonString.append(":'");
                            jsonString
                                    .append(invokeMethod(list.get(i), keys[j]));
                            jsonString.append("'");
                        }
                    }

                    jsonString.append("},");
                } else {
                    jsonString.append("{");
                    for (int j = 0; j < keys.length; j++) {
                        if (j != keys.length - 1) {
                            jsonString.append(keys[j]);
                            jsonString.append(":'");
                            jsonString
                                    .append(invokeMethod(list.get(i), keys[j]));
                            jsonString.append("',");
                        } else {
                            jsonString.append(keys[j]);
                            jsonString.append(":'");
                            jsonString
                                    .append(invokeMethod(list.get(i), keys[j]));
                            jsonString.append("'");
                        }
                    }
                    jsonString.append("}");
                }
            }
        } catch (Throwable e) {
            log.error(" listת��Ϊjson����", e);
        }
        jsonString.append("]");
        return toString(jsonString);
    }

    /**
     * @param methodObject �������ڵĶ���
     * @param methodName   ������
     * @return obj
     * @throws Exception Exception
     */
    private static Object invokeMethod(Object methodObject, String methodName)
            throws Exception {

        methodName = combineStrs("get", methodName.substring(0, 1)
                .toUpperCase(), methodName.substring(1));

        Class ownerClass = methodObject.getClass();

        Method method = ownerClass.getMethod(methodName);
        return method.invoke(methodObject);
    }

    /**
     * ����������������֮���������
     */
//	public static int getDifDays(String date1, String date2) {
//		Date date_1 = dateUtil.getDate(date1);
//		Date date_2 = dateUtil.getDate(date2);
//
//		long time1 = date_1.getTime();
//		long time2 = date_2.getTime();
//		long time3 = Math.abs(time1 - time2);
//
//		int day = getDay(time3);
//		return day;
//	}

    /**
     * ����������֮��ĺ������������
     *
     * @param time
     * @return
     */
    private static int getDay(long time) {
        int day = (int) (time / 1000 / 60 / 60 / 24);
        return day;
    }

    /**
     * 生成token
     *
     * @return token
     */
    public static String createToken() {

        StringBuffer sb = new StringBuffer();

        Random randGen = new Random();
        char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                .toCharArray();

        char[] randBuffer = new char[18];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }

        char[] randBuffer2 = new char[18];
        for (int i = 0; i < randBuffer2.length; i++) {
            randBuffer2[i] = numbersAndLetters[randGen.nextInt(71)];
        }

        sb.append(new String(randBuffer));
        sb.append(System.currentTimeMillis());
        sb.append(new String(randBuffer2));

        return sb.toString();
    }

    /**
     * 获取随机长度为8的字符串
     *
     * @return
     */
    public static String getRomStr() {

        StringBuffer sb = new StringBuffer();

        Random randGen = new Random();
        char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                .toCharArray();

        char[] randBuffer = new char[8];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }

        sb.append(new String(randBuffer));

        return sb.toString();
    }

    /**
     * json转map
     *
     * @param jsonString
     * @return
     */
    public static Map getMapFromJson(String jsonString) {

        ObjectMapper mapper = new ObjectMapper();
        Map<?, ?> map = null;
        try {
            map = mapper.readValue(jsonString, Map.class);
        } catch (Exception e) {

            return null;
        }

        return map;
    }

    /**
     * base64字符串转String
     *
     * @param s
     * @return
     */
    public static String getFromBASE64(String s) {
        if (s == null)
            return null;
        try {
            byte[] encodeBase64 = Base64.decodeBase64(s.getBytes("UTF-8"));
            return new String(encodeBase64);
        } catch (UnsupportedEncodingException e) {
            return null;
        }

    }

    public static void main(String[] args) {
        System.out.println(StringEncrypt.Encrypt("e10adc3949ba59abbe56e057f20f883e", null));

    }
}
