package com.android.liuxu.mycampus.activity;

import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.liuxu.mycampus.MainActivity;
import com.android.liuxu.mycampus.R;
import com.android.liuxu.mycampus.model.User;
import com.android.liuxu.mycampus.thread.LoginThread;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    private  EditText et_username;
    private  EditText et_password;

    private String userJson;

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        //刷新界面，以显示回送信息
        handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                String message=(String) msg.obj;
                String[] args=message.split("&");
                String loginState=args[0];
                if(args.length>1){
                    userJson=args[1];
                }

                Toast.makeText(LoginActivity.this, loginState, Toast.LENGTH_SHORT).show();

                if(loginState.equals("登录成功")){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);

                    Gson gson=new Gson();
                    User user=gson.fromJson(userJson,User.class);
                    Log.d("user", user.toString());
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("user",user);

                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }



            }
        };
    }

    public void click(View view){

        et_username = (EditText) findViewById(R.id.login_username);
        et_password = (EditText) findViewById(R.id.login_password);

        String username=et_username.getText().toString();
        String password=et_password.getText().toString();
        if(!username.isEmpty()&&!password.isEmpty()){
            //开启线程，进行登录校验
            new Thread(new LoginThread(getServerIP(),username,password,handler)).start();
        }else {
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }



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
}
