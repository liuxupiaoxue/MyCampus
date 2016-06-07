package com.android.liuxu.mycampus.thread;

import android.os.Handler;

import com.android.liuxu.mycampus.MainActivity;
import com.android.liuxu.mycampus.fragment.ExchangeFragment;
import com.android.liuxu.mycampus.model.MyMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by liuxu on 2016/3/19.
 */
public class ReceiveThread  implements Runnable{
    private Handler handler;
    public ReceiveThread(Handler handler){
        this.handler=handler;

    }

    @Override
    public void run() {
        try {
            MainActivity.socket =new Socket(MainActivity.IP,20000);

            while(true) {
                InputStream inputStream = MainActivity.socket
                        .getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        inputStream, "utf-8"));
                String str = br.readLine();
                String s[]=str.split("~~");
                ExchangeFragment.list.add(new MyMessage(s[0], s[1]));
                for(MyMessage m:ExchangeFragment.list)

                handler.sendEmptyMessage(1);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
