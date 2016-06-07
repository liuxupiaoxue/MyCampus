package com.android.liuxu.mycampus.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.liuxu.mycampus.R;
import com.android.liuxu.mycampus.model.User;

import java.util.List;

/**
 * Created by liuxu on 2016/4/24.
 */
public class SettingListViewAdapter extends BaseAdapter {

    private Context context;

    private List<User> userList;

    LayoutInflater inflater;

    public SettingListViewAdapter(Context context, List<User> userList) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.userList = userList;
    }


    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.contact_layout, null);
            holder.nickname = (TextView) convertView.findViewById(R.id.contact_nickname);
            holder.phone = (TextView) convertView.findViewById(R.id.contact_phone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final User user = userList.get(position);
        holder.nickname.setText(user.getNickname() + "(" + user.getUsername() + ")");
        holder.phone.setText(user.getPhoneNumber());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+user.getPhoneNumber()));
                context.startActivity(intent);

            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView nickname;
        TextView phone;
    }
}
