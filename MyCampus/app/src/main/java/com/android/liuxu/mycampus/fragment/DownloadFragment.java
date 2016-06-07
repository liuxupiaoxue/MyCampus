package com.android.liuxu.mycampus.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.liuxu.mycampus.R;

/**
 * Created by liuxu on 2016/3/19.
 */
public class DownloadFragment extends Fragment {
    private TextView tv;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.download_layout, container, false);
        tv=(TextView) messageLayout.findViewById(R.id.do_message);
        tv.setText("我是下载界面");
        return messageLayout;
    }
}
