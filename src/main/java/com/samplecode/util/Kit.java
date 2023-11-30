package com.samplecode.util;

import com.samplecode.chapter1.Chapter1;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Kit {
    /**
     * Hex转Byte字节
     *
     * @param hex 十六进制字符串
     * @return 字节
     */
    public static byte hexToByte(String hex) {
        return (byte) Integer.parseInt(hex, 16);
    }

    /**
     * Hex转Byte字节数组
     *
     * @param hex 十六进制字符串
     * @return 字节数组
     */
    public static byte[] hexToBytes(String hex) {
        int hexLength = hex.length();
        byte[] result;
        //判断Hex字符串长度，如果为奇数个需要在前边补0以保证长度为偶数
        //因为Hex字符串一般为两个字符，所以我们在截取时也是截取两个为一组来转换为Byte。
        if (hexLength % 2 == 1) {
            //奇数
            hexLength++;
            hex = "0" + hex;
        }
        result = new byte[(hexLength / 2)];
        int j = 0;
        for (int i = 0; i < hexLength; i += 2) {
            result[j] = hexToByte(hex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    /**
     * 字节数组转Hex
     *
     * @param bytes 字节数组
     * @return Hex
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        if (bytes != null && bytes.length > 0) {
            for (int i = 0; i < bytes.length; i++) {
                String hex = byteToHex(bytes[i]);
                sb.append(hex);
            }
        }
        return sb.toString();
    }

    /**
     * Byte字节转Hex
     *
     * @param b 字节
     * @return Hex
     */
    public static String byteToHex(byte b) {
        String hexString = Integer.toHexString(b & 0xFF);
        //由于十六进制是由0~9、A~F来表示1~16，所以如果Byte转换成Hex后如果是<16,就会是一个字符（比如A=10），通常是使用两个字符来表示16进制位的,
        //假如一个字符的话，遇到字符串11，这到底是1个字节，还是1和1两个字节，容易混淆，如果是补0，那么1和1补充后就是0101，11就表示纯粹的11
        if (hexString.length() < 2) {
            hexString = new StringBuilder(String.valueOf(0)).append(hexString).toString();
        }
        return hexString.toUpperCase();
    }

    public static int bytesToInt(byte[] bytes) {
        int i = (bytes[0] & 255) | ((bytes[1] & 255) << 8) | ((bytes[2] & 255) << 16) | ((bytes[3] & 255) << 24);
        return i;
    }

    public static byte[] getBytesFromFile(String filePath) {
        try (FileInputStream fIn = new FileInputStream(filePath)) {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            while (true) {
                byte[] b = new byte[1024];
                int l = fIn.read(b);
                if (l < 0)
                    break;
                bOut.write(b, 0, l);
                bOut.flush();
            }
            return bOut.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
