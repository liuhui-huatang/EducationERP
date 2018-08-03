package com.htcompany.educationerpforgansu.workpart.personnelpart.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.homepart.adapters.MyRewardsFragmentAdapter;
import com.htcompany.educationerpforgansu.workpart.personnelpart.activitys.PersonRewardsAddActivity;


/**
 * 奖励记录
 * Created by wrb on 2016/10/24.
 */
public class PersonManageRewardsFragment extends Fragment implements View.OnClickListener{
    private View rewardView;
    private ListView myrewards_fragment_lv;
    private MyRewardsFragmentAdapter rewardsFragmentAdapter;
    private FloatingActionButton rewardsmanage_add_fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rewardView = inflater.inflate(R.layout.myrewards_fragment,null);
//        initViews();
//        initViewValues();
//        initOnclicEvents();
        return rewardView;
    }
    public void initViews(){
//        myrewards_fragment_lv = (ListView)rewardView.findViewById(R.id.myrewards_fragment_lv);
//        rewardsFragmentAdapter = new MyRewardsFragmentAdapter(getActivity());
//        myrewards_fragment_lv.setAdapter(rewardsFragmentAdapter);
//        myrewards_fragment_lv.setOnItemClickListener(rewardsClicer);
//        rewardsmanage_add_fab = (FloatingActionButton)rewardView.findViewById(R.id.rewardsmanage_add_fab);
    }
//    public void initViewValues(){
////        rewardsmanage_add_fab.setVisibility(View.VISIBLE);
//    }
//    public void initOnclicEvents(){
//        rewardsmanage_add_fab.setOnClickListener(this);
//    }
//    public AdapterView.OnItemClickListener rewardsClicer = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Intent intent = new Intent(getActivity(), MyRewardsDetailsActivity.class);
//            startActivity(intent);
//        }
//    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rewardsmanage_add_fab:
                Intent intent2 = new Intent(getActivity(), PersonRewardsAddActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
