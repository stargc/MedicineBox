package com.zhiyi.medicinebox.api.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author guanchen
 * @version $Id MD5Util.java, v 0.1 2018-08-21 10:05 star Exp $$
 */
@Slf4j
public class MD5Util {

    private MD5Util(){}

    /***
     * Salt MD5加密
     * @param rawPass
     * @param salt
     * @return
     */
    public static String md5BySalt(String rawPass, Object salt){
        String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }

        byte[] digest = messageDigest.digest(UTF8Encode(saltedPass));

            digest = messageDigest.digest(digest);

        return new String(HexEncode(digest));
    }

    private static String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }

        if (strict && salt != null && (salt.toString().lastIndexOf("{") != -1 || salt.toString().lastIndexOf("}") != -1)) {
            throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
        } else {
            return salt != null && !"".equals(salt) ? password + "{" + salt.toString() + "}" : password;
        }
    }

    private static char[] HexEncode(byte[] bytes) {
        char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        final int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];

        int j = 0;
        for (int i = 0; i < nBytes; i++) {
            // Char for top 4 bits
            result[j++] = HEX[(0xF0 & bytes[i]) >>> 4];
            // Bottom 4
            result[j++] = HEX[(0x0F & bytes[i])];
        }

        return result;
    }

    private static byte[] UTF8Encode(CharSequence string) {
        try {
            ByteBuffer bytes = Charset.forName("UTF-8").newEncoder().encode(CharBuffer.wrap(string));
            byte[] bytesCopy = new byte[bytes.limit()];
            System.arraycopy(bytes.array(), 0, bytesCopy, 0, bytes.limit());

            return bytesCopy;
        }
        catch (CharacterCodingException e) {
            throw new IllegalArgumentException("Encoding failed", e);
        }
    }


    /***
     * MD5哈希(32位⼩写)
     * @param msg
     * @return
     * @throws Exception
     */
    public static String md5Encode(String msg) throws Exception{

        byte[] msgBytes = msg.getBytes("utf-8");
        /**
         * 声明使用Md5算法,获得MessaDigest对象
         */
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        /**
         * 使用指定的字节更新摘要
         */
        md5.update(msgBytes);
        /**
         * 完成哈希计算,获得密文
         */
        byte[] digest = md5.digest();
        StringBuffer hexVal=new StringBuffer();
        int val=0;
        for (int i = 0; i < digest.length; i++) {
            //将byte转化为int  如果byte是一个负数就必须要和16进制的0xff做一次与运算
            val=((int)digest[i]) & 0xff;
            if(val<16){
                hexVal.append("0");
            }
            hexVal.append(Integer.toHexString(val));
        }

        return hexVal.toString();
    }


    public static void main(String[] args) {
        log.error(md5BySalt("123","0123456789ABCDEF"));
    }
}
