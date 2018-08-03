package com.htcompany.educationerpforgansu.homepart.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.homepart.activitys.MyRewardsDetailsActivity;
import com.htcompany.educationerpforgansu.homepart.adapters.MyRewardsFragmentAdapter;
import com.htcompany.educationerpforgansu.homepart.entity.MyRewardsEntity;
import com.htcompany.educationerpforgansu.internet.MainHome.MainHomeInternet;
import com.htcompany.educationerpforgansu.internet.MainHome.MainHomePersener;

import java.util.ArrayList;
import java.util.List;


/**
 * 奖励记录
 * Created by wrb on 2016/10/24.
 */
public class MyRewardsFragment extends Fragment {
    private View rewardView;
    private ImageView myrewards_wsj_img;
    private ListView myrewards_fragment_lv;
    private MyRewardsFragmentAdapter rewardsFragmentAdapter;
    private List<MyRewardsEntity> rewardsEntities;
    //=========================网络请求类==========================
    private MainHomeInternet homeInternet;
    private MainHomePersener homePersener;
    private WaitDialog waitDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rewardView = inflater.inflate(R.layout.myrewards_fragment,null);
        initDatas();
        initViews();
        return rewardView;
    }
    public void initDatas() {
        rewardsEntities = new ArrayList<MyRewardsEntity>();
        homeInternet = new MainHomeInternet(getActivity(), myHandler);
        homePersener = new MainHomePersener(getActivity(), myHandler);
        waitDialog = new WaitDialog(getActivity(), "");
        waitDialog.show();
        homeInternet.getMyRevardsListMsg();
    }
    public void initViews(){
        myrewards_wsj_img=(ImageView)rewardView.findViewById(R.id.myrewards_wsj_img);
        myrewards_fragment_lv = (ListView)rewardView.findViewById(R.id.myrewards_fragment_lv);
        rewardsFragmentAdapter = new MyRewardsFragmentAdapter(getActivity(),rewardsEntities);
        myrewards_fragment_lv.setAdapter(rewardsFragmentAdapter);
        myrewards_fragment_lv.setOnItemClickListener(rewardsClicer);
    }
    public AdapterView.OnItemClickListener rewardsClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyRewardsEntity entity = (MyRewardsEntity) rewardsFragmentAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), MyRewardsDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",getActivity());
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<MyRewardsEntity> datas = homePersener.parseMyRewards_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        myrewards_wsj_img.setVisibility(View.VISIBLE);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",getActivity());
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<MyRewardsEntity> datas){
        if(rewardsEntities.size()>0){
            rewardsEntities.clear();
        }
        for(MyRewardsEntity entity:datas){
            rewardsEntities.add(entity);
        }
        rewardsFragmentAdapter.notifyDataSetChanged();
    }
}
