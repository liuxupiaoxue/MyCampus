package com.android.liuxu.mycampus.model;

/**
 * Created by liuxu on 2016/4/3.
 */
public class MyNotice {

    private String brief;
    private String content;

    @Override
    public String toString() {
        return "MyNotice{" +
                "brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public MyNotice(){

    }

    public MyNotice(String brief, String content) {
        this.brief = brief;
        this.content = content;
    }

    public String getBrief() {
        return brief;
    }

    public String getContent() {
        return content;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
