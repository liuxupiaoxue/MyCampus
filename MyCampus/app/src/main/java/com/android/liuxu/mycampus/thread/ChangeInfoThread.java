package com.android.liuxu.mycampus.thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.liuxu.mycampus.fragment.settingchild.ChangeUserInfoFragment;
import com.android.liuxu.mycampus.model.User;
import com.android.liuxu.mycampus.tools.IOTools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liuxu on 2016/5/16.
 */
public class ChangeInfoThread implements Runnable {
    private String IP;
    private Handler handler;
    private String path;
    private User user;

    public ChangeInfoThread(Handler handler,String IP,User user){
        this.handler=handler;
        this.IP=IP;
        this.user=user;

    }
    @Override
    public void run() {
        path = "http://"+IP+":8080/Campus/ChangeInfo?nickname="+user.getNickname()
                +"&email="+user.getEmail()+"&phone="+user.getPhoneNumber()+"&username="+user.getUsername();
        try {
            URL url=new URL(path);
            System.out.println(path);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            if(conn.getResponseCode()==200){
                InputStream is=conn.getInputStream();
                String text= IOTools.getTextFromStream(is);
                Log.d("changeuserinfo", text);
                Message message=handler.obtainMessage();
                message.obj=text;
                message.what=1;
                handler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
