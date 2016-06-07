package com.android.liuxu.mycampus.thread;

import android.os.Handler;
import android.os.Message;

import com.android.liuxu.mycampus.tools.IOTools;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by liuxu on 2016/3/26.
 */
public class SubmitNoticeThread implements Runnable {
    private String brief;
    private String content;
    private String path;
    private Handler handler;

    public SubmitNoticeThread(String content, String brief,String path,Handler handler) {
        this.content = content;
        this.brief = brief;
        this.path=path;
        this.handler=handler;
    }

    @Override
    public void run() {
        try {
            System.out.println("进入"+ path);
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setConnectTimeout(80000);
            conn.setReadTimeout(80000);

            //添加post请求头中自动添加的属性
            //流里的数据的mimetype
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String content = "brief=" + URLEncoder.encode(brief) + "&content=" + URLEncoder.encode(this.content);
            System.out.println(content);
            //流里数据的长度
            conn.setRequestProperty("Content-Length", content.length() + "");

            //打开连接对象的输出流
            conn.setDoOutput(true);
            //获取连接对象的输出流
            OutputStream os = conn.getOutputStream();
            //把数据写入输出流中
            os.write(content.getBytes());
            if(conn.getResponseCode() == 200){
                InputStream is = conn.getInputStream();
                String text = IOTools.getTextFromStream(is);

                Message msg = handler.obtainMessage();
                msg.obj = text;
                handler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    }

