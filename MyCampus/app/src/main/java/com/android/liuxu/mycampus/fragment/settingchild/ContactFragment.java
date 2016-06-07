package com.android.liuxu.mycampus.fragment.settingchild;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.liuxu.mycampus.MainActivity;
import com.android.liuxu.mycampus.R;
import com.android.liuxu.mycampus.adapter.SettingListViewAdapter;
import com.android.liuxu.mycampus.model.User;
import com.android.liuxu.mycampus.thread.GetContacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuxu on 2016/4/25.
 */
public class ContactFragment extends Fragment {
    private User user;

    private ListView listView;

    private View messageLayout;

    public static List<User> userList=new ArrayList<>();

    Handler handler;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        messageLayout = inflater.inflate(R.layout.contacts_list_layout, container, false);
        initData();
        initUi();

        Thread thread=new Thread(new GetContacts( MainActivity.IP,handler));
        thread.start();
        return messageLayout;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initUi() {


    }

    private void initData() {
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {

                listView=(ListView)messageLayout.findViewById(R.id.set_contacts_lv);
                listView.setAdapter(new SettingListViewAdapter(getActivity(), userList));
            }
        };

    }
}
