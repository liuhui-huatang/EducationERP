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
 * w我的阶段总结
 * Created by wrb on 2016/11/10.
 */
public class MySummaryFragment extends Fragment implements View.OnClickListener{
    private View planView;
    private RelativeLayout mysummary_day_rel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        planView = inflater.inflate(R.layout.mysummary_fragment,container,false);
        initViews();
        initViewOnclcerEvents();
        return planView;
    }
    public void initViews(){
        mysummary_day_rel = (RelativeLayout)planView.findViewById(R.id.mysummary_day_rel);
    }
    public void initViewOnclcerEvents(){
        mysummary_day_rel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mysummary_day_rel:
                break;
        }
    }
}
