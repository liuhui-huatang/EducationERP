package com.htcompany.educationerpforgansu.workpart.personnelpart.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.homepart.adapters.MyPunishmentFragmentAdapter;
import com.htcompany.educationerpforgansu.workpart.personnelpart.activitys.PersonPunishmentAddActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.activitys.PersonPunishmentDetailsManageActivity;

/**
 * 惩罚记录
 * Created by wrb on 2016/10/24.
 */
public class PersonManagePunishmentFragment extends Fragment implements View.OnClickListener{
    private View punView;
    private ListView mypunish_fragment_lv;
    private MyPunishmentFragmentAdapter punishmentFragmentAdapter;
    private FloatingActionButton punishmanage_add_fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        punView = inflater.inflate(R.layout.mypunishment_fragment,null);
        initViews();
        initViewValues();
        initOnclcerEvents();
        return punView;
    }
    public void initViews(){
//        mypunish_fragment_lv = (ListView)punView.findViewById(R.id.mypunish_fragment_lv);
//        punishmentFragmentAdapter = new MyPunishmentFragmentAdapter(getActivity());
//        mypunish_fragment_lv.setAdapter(punishmentFragmentAdapter);
//        mypunish_fragment_lv.setOnItemClickListener(punishmentClicer);
//        punishmanage_add_fab = (FloatingActionButton)punView.findViewById(R.id.punishmanage_add_fab);
    }
    public void initViewValues(){
        punishmanage_add_fab.setVisibility(View.VISIBLE);
    }
    public AdapterView.OnItemClickListener punishmentClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(), PersonPunishmentDetailsManageActivity.class);
            startActivity(intent);
        }
    };
    public void initOnclcerEvents(){
        punishmanage_add_fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.punishmanage_add_fab:
                Intent intent = new Intent(getActivity(), PersonPunishmentAddActivity.class);
                startActivity(intent);
                break;
        }
    }
}
