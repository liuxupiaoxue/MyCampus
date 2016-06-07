package layout.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.liuxu.campus.R;
import com.android.liuxu.campus.fragment.DownloadFragment;
import com.android.liuxu.campus.fragment.ExchangeFragment;
import com.android.liuxu.campus.fragment.NoticeFragment;
import com.android.liuxu.campus.fragment.SettingFragment;

import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    public static Socket socket;

    //用于交流的Fragment
    private ExchangeFragment exchangeFragment;

    //用于显示公告的Fragment
    private NoticeFragment noticeFragment;

    //用于下载的Fragment
    private DownloadFragment downloadFragment;

    //用于设置的Fragment
    private SettingFragment settingFragment;

    //用于Fragment管理的类
    private FragmentManager fragmentManager;

    //交流图标的布局
    private View exchangeLayout;

    //公告图标的布局
    private View noticeLayout;

    //下载图标的布局
    private View downloadLayout;

    //设置图标的布局
    private View settingLayout;

    public static String IP;
    public static String MyIP;

    //记录登录的角色
    public static String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        role=intent.getStringExtra("role");

        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        initFragment();

        IP=getServerIP();
        MyIP=getMyIP();
        System.out.println(IP);
        System.out.println(MyIP);

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

    private void initFragment() {
        exchangeLayout = findViewById(R.id.exchange_layout);
        noticeLayout = findViewById(R.id.notice_layout);
        downloadLayout = findViewById(R.id.download_layout);
        settingLayout = findViewById(R.id.setting_layout);
    }

    public void click(View v){
        switch (v.getId()) {
            case R.id.exchange_layout:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.notice_layout:
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.download_layout:
                // 当点击了动态tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.setting_layout:
                // 当点击了设置tab时，选中第4个tab
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index) {
        System.out.println(index);
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                exchangeLayout.setBackgroundColor(0xff0000ff);

                if (exchangeFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    exchangeFragment = new ExchangeFragment();
                    transaction.add(R.id.content, exchangeFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(exchangeFragment);
                }
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                noticeLayout.setBackgroundColor(0xff0000ff);
                if (noticeFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    noticeFragment = new NoticeFragment();
                    transaction.add(R.id.content, noticeFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(noticeFragment);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                downloadLayout.setBackgroundColor(0xff0000ff);
                if (downloadFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    downloadFragment = new DownloadFragment();
                    transaction.add(R.id.content, downloadFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(downloadFragment);
                }
                break;
            case 3:
            default:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                settingLayout.setBackgroundColor(0xff0000ff);
                if (settingFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.content, settingFragment);
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(settingFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (exchangeFragment != null) {
            transaction.hide(exchangeFragment);
        }
        if (noticeFragment != null) {
            transaction.hide(noticeFragment);
        }
        if (downloadFragment != null) {
            transaction.hide(downloadFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }
    //清楚所有选中状态
    private void clearSelection() {
        exchangeLayout.setBackgroundColor(0xffffffff);
        noticeLayout.setBackgroundColor(0xffffffff);
        downloadLayout.setBackgroundColor(0xffffffff);
        settingLayout.setBackgroundColor(0xffffffff);
    }

}
