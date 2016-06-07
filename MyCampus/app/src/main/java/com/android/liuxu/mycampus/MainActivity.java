package com.android.liuxu.mycampus;

import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.android.liuxu.mycampus.fragment.DownloadFragment;
import com.android.liuxu.mycampus.fragment.ExchangeFragment;
import com.android.liuxu.mycampus.fragment.NoticeFragment;
import com.android.liuxu.mycampus.fragment.SettingFragment;
import com.android.liuxu.mycampus.model.User;

import java.net.Socket;

/**
 *
 * @author liuxu
 *
 */
public class MainActivity extends FragmentActivity {


    public static Socket socket;
    public static String IP;
    public static String MyIP;

    //记录登录的角色
    public static String role;

    /**
     *用户信息
     */
    public  static User user;

    /**
     * FragmentTabhost
     */
    private FragmentTabHost mTabHost;

    /**
     * 布局填充器
     *
     */
    private LayoutInflater mLayoutInflater;

    /**
     * Fragment数组界面
     *
     */
    private Class mFragmentArray[] = { ExchangeFragment.class,NoticeFragment.class,
    DownloadFragment.class, SettingFragment.class, };
    /**
     * 存放图片数组
     *
     */
    private int mImageArray[] = { R.drawable.tab_selfinfo_btn,
            R.drawable.tab_message_btn, R.drawable.tab_home_btn,
            R.drawable.tab_square_btn };


    /**
     * 选修卡文字
     *
     */
    private String mTextArray[] = { "Chat", "Notice", "Download", "Setting" };
    /**
     *
     *
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }
    /**
     * 初始化数据
     */
    private void initData(){

        //获取用户数据
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        user= (User) bundle.getSerializable("user");
        Log.d("main_user",user.toString());
        role=user.getRole()+"";

        IP=getServerIP();
        MyIP=getMyIP();

    }

    /**
     * 初始化组件
     */
    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);

        // 找到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab按钮的背景
//            mTabHost.getTabWidget().getChildAt(i)
//                    .setBackgroundResource(R.drawable.selector_tab_background);
        }
    }

    /**
     *
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);

        return view;
    }
    public  String getMyIP() {
        WifiManager wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        DhcpInfo info=wifiManager.getDhcpInfo();
        return intToIP(info.ipAddress);
    }

    public String getServerIP() {
        WifiManager wifiManager=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        DhcpInfo info=wifiManager.getDhcpInfo();
        return intToIP(info.serverAddress);
    }

    private String intToIP(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }
    public User getUser(){
        return user;
    }

}
