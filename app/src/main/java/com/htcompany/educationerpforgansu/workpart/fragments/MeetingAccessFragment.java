package com.htcompany.educationerpforgansu.workpart.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.activitys.MyMeetingAccessDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.adapters.MeetingAccessAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

/**
 * 已通过会议
 * Created by wrb on 2016/11/9.
 */
public class MeetingAccessFragment extends Fragment{
    private View waitView;
    private ListView meetaccess_lv;
    private MeetingAccessAdapter accessAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        waitView = inflater.inflate(R.layout.meetingaccess_fragment,container,false);
        initDatas();
        initViews();
        return waitView;
    }
    public void initDatas(){
    }
    public void initViews(){
        meetaccess_lv = (ListView)waitView.findViewById(R.id.meetaccess_lv);
        accessAdapter = new MeetingAccessAdapter(getActivity(),FunctionDatas.ytgmeetingdatas);
        meetaccess_lv.setAdapter(accessAdapter);
        meetaccess_lv.setOnItemClickListener(accessClicer);
    }

    public AdapterView.OnItemClickListener accessClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyMeetingEntity entity = (MyMeetingEntity) accessAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), MyMeetingAccessDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };
}
