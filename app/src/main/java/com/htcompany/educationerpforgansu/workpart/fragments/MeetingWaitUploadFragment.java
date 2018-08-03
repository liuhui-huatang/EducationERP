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
import com.htcompany.educationerpforgansu.workpart.activitys.MyMeetingWaitUploadDetialsActivity;
import com.htcompany.educationerpforgansu.workpart.adapters.MeetingWaitUploadAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.MyMeetingEntity;

/**
 * 待提交会议
 * Created by wrb on 2016/11/9.
 */
public class MeetingWaitUploadFragment extends Fragment{
    private View waitView;
    private ListView meetwaitupload_lv;
    private MeetingWaitUploadAdapter waitUploadAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        waitView = inflater.inflate(R.layout.meetingwaitupload_fragment,container,false);
        initDatas();
        initViews();
        return waitView;
    }
     public void initDatas(){
     }
    public void initViews(){
        meetwaitupload_lv = (ListView)waitView.findViewById(R.id.meetwaitupload_lv);
        waitUploadAdapter = new MeetingWaitUploadAdapter(getActivity(),FunctionDatas.waitmeetingdatas);
        meetwaitupload_lv.setAdapter(waitUploadAdapter);
        meetwaitupload_lv.setOnItemClickListener(waitOnclicer);
    }
    public void initViewValues(){

    }
    public AdapterView.OnItemClickListener waitOnclicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyMeetingEntity entity = (MyMeetingEntity) waitUploadAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), MyMeetingWaitUploadDetialsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        waitUploadAdapter.notifyDataSetChanged();
    }
}
