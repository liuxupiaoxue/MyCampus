package com.android.liuxu.mycampus.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.liuxu.mycampus.R;
import com.android.liuxu.mycampus.activity.AddNoticeActivity;
import com.android.liuxu.mycampus.MainActivity;
import com.android.liuxu.mycampus.model.MyNotice;
import com.android.liuxu.mycampus.thread.GetNoticeThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuxu on 2016/3/19.
 */
public class NoticeFragment extends Fragment {

    Button addButton;

    Handler handler;

    public static List <MyNotice> noticeList=new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        final View messageLayout = inflater.inflate(R.layout.notice_layout, container, false);



        handler=new Handler(){

            @Override
            public void handleMessage(Message msg) {
                for(MyNotice myNotice : noticeList){
                    System.out.println(myNotice.toString());
                }
                ListView listView=(ListView)messageLayout.findViewById(R.id.no_lv);
                //给ListView设置适配器
                listView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return noticeList.size();
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
                        MyNotice message=noticeList.get(position);
                        View view=null;
                        TextView tv_brief=null;
                        TextView tv_content=null;
                        if(convertView==null) {
                            view = View.inflate(getActivity(), R.layout.noticeview, null);

                        }else {
                            view=convertView;
                        }
                        MyNotice myNotice=noticeList.get(position);

                        tv_brief=(TextView)view.findViewById(R.id.noview_bief);
                        tv_brief.setText(myNotice.getBrief());
                        tv_content=(TextView)view.findViewById(R.id.noview_content);
                        tv_content.setText(myNotice.getContent());

                        return view;
                    }
                });
            }
        };

        addButton=(Button)messageLayout.findViewById(R.id.no_button);
        System.out.println(MainActivity.role+"-------------------------------");
        if(MainActivity.role.equals("1"))
            addButton.setVisibility(View.VISIBLE);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.role.equals("1")){
                    Intent intent=new Intent(getActivity(),AddNoticeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "您无权发布公告", Toast.LENGTH_SHORT).show();
                }


            }
        });


        new Thread(new GetNoticeThread(handler,MainActivity.IP)).start();
        return messageLayout;
    }
}
