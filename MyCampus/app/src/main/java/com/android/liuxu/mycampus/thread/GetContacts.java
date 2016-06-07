package com.android.liuxu.mycampus.thread;

import android.os.Handler;
import android.util.Log;

import com.android.liuxu.mycampus.fragment.NoticeFragment;
import com.android.liuxu.mycampus.fragment.SettingFragment;
import com.android.liuxu.mycampus.fragment.settingchild.ContactFragment;
import com.android.liuxu.mycampus.model.MyNotice;
import com.android.liuxu.mycampus.model.User;
import com.android.liuxu.mycampus.tools.IOTools;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by liuxu on 2016/4/24.
 */
public class GetContacts implements Runnable {
    String IP;
    Handler handler;
    String path;

    public GetContacts(String IP,Handler handler){
        this.IP=IP;
        this.handler=handler;
    }

    @Override
    public void run() {
        path = "http://"+IP+":8080/Campus/GetContacts";
        try {
            URL url=new URL(path);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            if(conn.getResponseCode()==200){
                InputStream is=conn.getInputStream();
                String text= IOTools.getTextFromStream(is);
                Log.d("getcontacts",text);
                setList(text);
                handler.sendEmptyMessage(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void setList(String text){
        Gson gson=new Gson();
        Type type = new TypeToken<List<User>>(){}.getType();
        List<User> list=gson.fromJson(text,type);

        ContactFragment.userList.clear();
        for (User user: list){
            ContactFragment.userList.add(user);

        }

    }



}
