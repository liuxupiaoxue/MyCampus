package com.android.liuxu.mycampus.fragment.settingchild;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.liuxu.mycampus.MainActivity;
import com.android.liuxu.mycampus.R;
import com.android.liuxu.mycampus.model.User;
import com.android.liuxu.mycampus.thread.ChangeInfoThread;

/**
 * Created by liuxu on 2016/4/25.
 */
public class ChangeUserInfoFragment extends Fragment implements View.OnClickListener{
    private EditText et_nickname;
    private EditText et_email;
    private EditText et_phone;
    private TextView btn1;
    private TextView btn2;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    String text= (String) msg.obj;
                    Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View messageLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        messageLayout = inflater.inflate(R.layout.setting_change_layout, container, false);
        initData();

        initUi();
        return messageLayout;
    }

    private void initUi() {
        et_nickname=(EditText)messageLayout.findViewById(R.id.change_nickname);
        et_email=(EditText)messageLayout.findViewById(R.id.change_mail);
        et_phone=(EditText)messageLayout.findViewById(R.id.change_phone);
        btn1=(TextView)messageLayout.findViewById(R.id.change_btn1);
        btn2=(TextView)messageLayout.findViewById(R.id.change_btn2);

        et_nickname.setText(MainActivity.user.getNickname());
        et_email.setText(MainActivity.user.getEmail());
        et_phone.setText(MainActivity.user.getPhoneNumber());

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_btn1:
                User user=new User();
                user.setNickname(et_nickname.getText().toString());
                user.setEmail(et_email.getText().toString());
                user.setPhoneNumber(et_phone.getText().toString());
                user.setUsername(MainActivity.user.getUsername());
                new Thread(new ChangeInfoThread(handler,MainActivity.IP,user)).start();
                break;
            case R.id.change_btn2:

                break;
            default:
                break;
        }
    }
}
