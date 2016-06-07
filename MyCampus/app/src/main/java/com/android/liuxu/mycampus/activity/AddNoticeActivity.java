package com.android.liuxu.mycampus.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.liuxu.mycampus.MainActivity;
import com.android.liuxu.mycampus.R;
import com.android.liuxu.mycampus.thread.SubmitNoticeThread;

public class AddNoticeActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText et_brief;
    private EditText et_content;
    private Handler handler;
    private String path="http://"+ MainActivity.IP+":8080/Campus/AddNotice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notice_activity);
        //刷新界面，以显示回送信息
        handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                Toast.makeText(AddNoticeActivity.this, (String) msg.obj, Toast.LENGTH_LONG).show();
            }
        };
    }

    public void click(View view){
        et_brief =(EditText)findViewById(R.id.an_brief);
        et_content=(EditText)findViewById(R.id.an_content);
        String brief=et_brief.getText().toString();
        String content=et_content.getText().toString();


        if (brief.isEmpty()||content.isEmpty()){
            Toast.makeText(this,"公告主题和内容不能为空",Toast.LENGTH_SHORT).show();
        }else {
            //开启发送公告的线程
            Thread thread=new Thread(new SubmitNoticeThread(content,brief,path,handler));
            thread.start();

        }



    }
}
