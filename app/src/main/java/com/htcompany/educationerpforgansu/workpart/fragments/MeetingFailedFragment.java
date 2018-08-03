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
import com.htcompany.educationerpforgansu.workpart.activitys.MyMeetingFailedDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.adapters.MeetingFailedAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

/**
 * 未通过会议
 * Created by wrb on 2016/11/9.
 */
public class MeetingFailedFragment extends Fragment{
    private View waitView;
    private MeetingFailedAdapter failedAdapter;
    private ListView meetfailed_lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        waitView = inflater.inflate(R.layout.meetingfailed_fragment,container,false);
        initDatas();
        initViews();
        return waitView;
    }
    public void initDatas(){
    }
    public void initViews(){
        meetfailed_lv = (ListView)waitView.findViewById(R.id.meetfailed_lv);
        failedAdapter = new MeetingFailedAdapter(getActivity(),FunctionDatas.ytgmeetingdatas);
        meetfailed_lv.setAdapter(failedAdapter);
        meetfailed_lv.setOnItemClickListener(failedClicer);
    }
    public AdapterView.OnItemClickListener failedClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyMeetingEntity entity = (MyMeetingEntity) failedAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), MyMeetingFailedDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };
}
