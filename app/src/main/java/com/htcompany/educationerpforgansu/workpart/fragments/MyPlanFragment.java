package com.htcompany.educationerpforgansu.workpart.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 个人计划
 * Created by wrb on 2016/11/10.
 */
public class MyPlanFragment extends Fragment implements View.OnClickListener{
    private View planView;
    private RelativeLayout myplan_day_rel;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        planView = inflater.inflate(R.layout.myplan_fragment,container,false);
        initViews();
        initViewValues();
        initOnclicEvents();
        return planView;
    }
    public void initViews(){
        myplan_day_rel = (RelativeLayout)planView.findViewById(R.id.myplan_day_rel);

    }
    public void initViewValues(){

    }
    public void initOnclicEvents(){
        myplan_day_rel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myplan_day_rel:
                break;
        }
    }
}
