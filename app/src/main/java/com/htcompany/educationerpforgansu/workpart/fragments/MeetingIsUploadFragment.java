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
import com.htcompany.educationerpforgansu.workpart.activitys.MyMeetingIsUploadDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.adapters.MeetingIsUploadAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

/**
 * 已提交会议(待审核)
 * Created by wrb on 2016/11/9.
 */
public class MeetingIsUploadFragment extends Fragment{
    private View waitView;
    private ListView meetisupload_lv;
    private  MeetingIsUploadAdapter isUploadAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        waitView = inflater.inflate(R.layout.meetingisupload_fragment,container,false);
        initDatas();
        initViews();
        return waitView;
    }
    public void initDatas(){
    }
    public void initViews(){
        meetisupload_lv = (ListView)waitView.findViewById(R.id.meetisupload_lv);
        isUploadAdapter = new MeetingIsUploadAdapter(getActivity(),FunctionDatas.dshmeetingdatas);
        meetisupload_lv.setAdapter(isUploadAdapter);
        meetisupload_lv.setOnItemClickListener(waitOnclicer);
    }
    public AdapterView.OnItemClickListener waitOnclicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyMeetingEntity entity = (MyMeetingEntity) isUploadAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), MyMeetingIsUploadDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };
}
