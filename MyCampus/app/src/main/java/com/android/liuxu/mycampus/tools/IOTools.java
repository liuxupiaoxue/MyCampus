package com.android.liuxu.mycampus.tools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by liuxu on 2016/3/26.
 */
public class IOTools {
    public static String getTextFromStream(InputStream is){
        byte[] b = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while((len = is.read(b)) != -1){
                bos.write(b, 0, len);
            }
            //把字节数组输出流转换成字节数组，然后用字节数组构造一个字符串
            String text = new String(bos.toByteArray());
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
