package com.android.liuxu.mycampus.model;

/**
 * Created by liuxu on 2016/3/19.
 */
public class MyMessage {
    private String IP;
    private String content;

    public MyMessage(String IP, String content) {
        this.IP = IP;
        this.content = content;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIP() {
        return IP;
    }

    public String getContent() {
        return content;
    }
}
