package com.android.liuxu.mycampus.thread;

import android.os.Handler;

import com.android.liuxu.mycampus.fragment.NoticeFragment;
import com.android.liuxu.mycampus.model.MyNotice;
import com.android.liuxu.mycampus.tools.IOTools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liuxu on 2016/4/3.
 */
public class GetNoticeThread implements Runnable {
    Handler handler;
    String IP;
    String path;


    public GetNoticeThread(Handler handler,String IP) {
        this.handler=handler;
        this.IP=IP;

    }
    @Override
    public void run(){


        path = "http://"+IP+":8080/Campus/GetNotice";
        try {
            URL url=new URL(path);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            if(conn.getResponseCode()==200){
                InputStream is=conn.getInputStream();
                String text= IOTools.getTextFromStream(is);
                System.out.println(text);
                String[] notices=text.split("##");


                NoticeFragment.noticeList.clear();
                for(String notice : notices){
                    String[] no=notice.split("&&");
                    MyNotice myNotice=new MyNotice(no[0],no[1]);
                    System.out.println(myNotice.toString());
                    NoticeFragment.noticeList.add(myNotice);
                }
                handler.sendEmptyMessage(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
