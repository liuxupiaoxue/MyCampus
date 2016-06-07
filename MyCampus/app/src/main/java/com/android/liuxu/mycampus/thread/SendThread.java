package com.android.liuxu.mycampus.thread;

import com.android.liuxu.mycampus.MainActivity;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by liuxu on 2016/3/19.
 */
public class SendThread implements Runnable {
    private String content;

    public SendThread(String content){
        this.content=content;


    }
    @Override
    public void run() {
        try {
            OutputStream outputStream=MainActivity.socket.getOutputStream();
            outputStream.write((MainActivity.MyIP+"~~"+content + "\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
