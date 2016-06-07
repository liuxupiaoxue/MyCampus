package com.android.liuxu.mycampus.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.liuxu.mycampus.R;
import com.android.liuxu.mycampus.fragment.settingchild.ChangeUserInfoFragment;
import com.android.liuxu.mycampus.fragment.settingchild.ContactFragment;

/**
 * Created by liuxu on 2016/3/19.
 */
public class SettingFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{



    private View messageLayout;

    private android.support.v4.app.FragmentTransaction transaction;

    private ChangeUserInfoFragment changeUserInfoFragment;

    private ContactFragment contactFragment;

    private RadioGroup radioGroup;

    RadioButton btn1;





    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        messageLayout = inflater.inflate(R.layout.setting_layout, container, false);
        initUi();
        return messageLayout;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();


    }

    @Override
    public void onResume() {
        super.onResume();
        btn1.setChecked(true);
    }

    private void initUi() {
        radioGroup= (RadioGroup) messageLayout.findViewById(R.id.set_rg);
        btn1=(RadioButton)messageLayout.findViewById(R.id.set_btn1);
        radioGroup.setOnCheckedChangeListener(this);

        contactFragment=new ContactFragment();
        transaction=getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.set_content,contactFragment);
        transaction.commit();


    }

    private void initData() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.set_btn1:
                if(null == contactFragment){
                    contactFragment=new ContactFragment();
                }
                transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.set_content, contactFragment);
                transaction.commit();
                break;
            case R.id.set_btn2:
                if(null == changeUserInfoFragment){
                    changeUserInfoFragment=new ChangeUserInfoFragment();
                }
                transaction=getActivity().getSupportFragmentManager().beginTransaction();
                if(!changeUserInfoFragment.isAdded()) {
                    transaction.replace(R.id.set_content, changeUserInfoFragment);
                }
                transaction.commit();
                break;
            default:
                break;
        }
    }
}
