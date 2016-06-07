package com.android.liuxu.mycampus.thread;

import android.os.Handler;
import android.os.Message;

import com.android.liuxu.mycampus.tools.IOTools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liuxu on 2016/4/3.
 */
public class LoginThread implements Runnable {
    private String IP;
    private String path;
    private Handler handler;

    private String username;
    private String password;


    public LoginThread(String IP, String username, String password,Handler handler) {
        this.IP = IP;
        this.username = username;
        this.password = password;
        this.handler=handler;
    }

    @Override
    public void run() {
        path = "http://"+IP+":8080/Campus/LoginCheck?username=" + username +"&password="+password;
        System.out.println(path);
        try {
            URL url=new URL(path);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            if(conn.getResponseCode()==200){
                InputStream is=conn.getInputStream();
                String text=IOTools.getTextFromStream(is);
                Message message=handler.obtainMessage();
                message.obj=text;
                handler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

