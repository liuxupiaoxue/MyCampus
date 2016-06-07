package com.android.liuxu.mycampus.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.liuxu.mycampus.R;
import com.android.liuxu.mycampus.MainActivity;
import com.android.liuxu.mycampus.model.MyMessage;
import com.android.liuxu.mycampus.thread.ReceiveThread;
import com.android.liuxu.mycampus.thread.SendThread;

import java.util.ArrayList;
import java.util.List;

public class ExchangeFragment extends Fragment {
    public static List<MyMessage> list=new ArrayList<>();
    private TextView tv;
    private EditText editText;
    View messageLayout;
    Handler handler;


    @Override
    public void onResume() {
        super.onResume();
        ListView listView=(ListView)messageLayout.findViewById(R.id.ex_lv);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                MyMessage message=ExchangeFragment.list.get(position);
                View view=null;
                TextView textView=null;
                if(convertView==null) {

                    if(message.getIP().equals(MainActivity.MyIP)) {
                        view = View.inflate(getActivity(), R.layout.chatting_item_msg_text_right, null);

                    }
                    else {
                        view = View.inflate(getActivity(), R.layout.chatting_item_msg_text_left, null);
                    }

                }else {
                    view=convertView;
                }
                System.out.println(message.getContent());
                if(message.getIP().equals(MainActivity.MyIP)){
                    textView=(TextView)view.findViewById(R.id.tv_chatcontent_right);
                    textView.setText(message.getContent());
                }
                else{
                    textView=(TextView)view.findViewById(R.id.tv_chatcontent_left);
                    textView.setText(message.getContent());
                }




                return view;
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        messageLayout= inflater.inflate(R.layout.exchange_layout, container, false);

        editText=(EditText)messageLayout.findViewById(R.id.ex_et);
        Button button=(Button)messageLayout.findViewById(R.id.ex_bt);
        handler=new Handler(){

            @Override
            public void handleMessage(Message msg) {
                ListView listView=(ListView)messageLayout.findViewById(R.id.ex_lv);
                //给ListView设置适配器
                listView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return list.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        MyMessage message=ExchangeFragment.list.get(position);
                        View view=null;
                        TextView textView=null;
                        if(convertView==null) {

                            if(message.getIP().equals(MainActivity.MyIP)) {
                                view = View.inflate(getActivity(), R.layout.chatting_item_msg_text_right, null);

                            }
                            else {
                                view = View.inflate(getActivity(), R.layout.chatting_item_msg_text_left, null);
                            }

                        }else {
                            view=convertView;
                        }
                        System.out.println(message.getContent());
                        if(message.getIP().equals(MainActivity.MyIP)){
                            textView=(TextView)view.findViewById(R.id.tv_chatcontent_right);
                            textView.setText(message.getContent());
                        }
                        else{
                            textView=(TextView)view.findViewById(R.id.tv_chatcontent_left);
                            textView.setText(message.getContent());
                        }




                        return view;
                    }
                });
            }
        };



        //发送按钮设置监听
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                editText.setText("");
                if(!content.isEmpty())
                    new Thread(new SendThread(content)).start();



            }
        });
        //信息监听线程
        new Thread(new ReceiveThread(handler)).start();
        return messageLayout;
    }

}