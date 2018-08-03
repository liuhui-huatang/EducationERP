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
import com.htcompany.educationerpforgansu.workpart.activitys.MyMeetingOverDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.adapters.MeetingOverAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

/**
 * 结束会议
 * Created by wrb on 2016/11/9.
 */
public class MeetingOverFragment extends Fragment{
    private View waitView;
    private MeetingOverAdapter overAdapter;
    private ListView meetover_lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        waitView = inflater.inflate(R.layout.meetingover_fragment,container,false);
        initDatas();
        initViews();
        return waitView;
    }
    public void initDatas(){
    }
    public void initViews(){
        meetover_lv = (ListView)waitView.findViewById(R.id.meetover_lv);
        overAdapter = new MeetingOverAdapter(getActivity(),FunctionDatas.ytgmeetingdatas);
        meetover_lv.setAdapter(overAdapter);
        meetover_lv.setOnItemClickListener(overClicer);
    }

    public AdapterView.OnItemClickListener overClicer  = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyMeetingEntity entity = (MyMeetingEntity) overAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), MyMeetingOverDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };
}
