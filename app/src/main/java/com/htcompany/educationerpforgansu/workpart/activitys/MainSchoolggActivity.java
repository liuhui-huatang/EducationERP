package com.htcompany.educationerpforgansu.workpart.activitys;

import android.os.Bundle;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkNoticeEntity;

import java.util.List;

/**
 * 全校公告
 * Created by wrb on 2017/1/11.
 */
public class MainSchoolggActivity extends BaseActivity{
    private List<WorkNoticeEntity> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainschoolgg_activity);
    }
}
