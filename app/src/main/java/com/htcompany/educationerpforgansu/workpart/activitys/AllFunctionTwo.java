package com.htcompany.educationerpforgansu.workpart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.views.CommonGridView;
import com.htcompany.educationerpforgansu.commonpart.views.functionmanageview.DragGridView;
import com.htcompany.educationerpforgansu.commonpart.views.functionmanageview.FuntionJumpMethod;
import com.htcompany.educationerpforgansu.commonpart.views.functionmanageview.GridViewAdapter;
import com.htcompany.educationerpforgansu.dao.daoservice.WorkFunctionService;
import com.htcompany.educationerpforgansu.workpart.adapters.AllFunctionTwoGridAdapter;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFunctionEntity;

import java.util.List;

/**
 * 功能管理
 * Created by wrb on 2016/12/1.
 */
public class AllFunctionTwo extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_btn;
    private int functionFlag=1;
    //首页功能管理
    private DragGridView function_grid;
    private GridViewAdapter gridViewAdapter;
    //其他应用管理
    private CommonGridView otherfunction_grid;
    private AllFunctionTwoGridAdapter twoGridAdapter;
    private Intent intent=null;
    private WorkFunctionService workFunctionService;//所有数据管理类
    private List<WorkFunctionEntity> upFunctionEntities;//上部数据
    private List<WorkFunctionEntity> downFunctionEntities;//下部数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allfunctiontwo);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        workFunctionService = new WorkFunctionService(this);
        upFunctionEntities=workFunctionService.searchAllMainEntity("1");
        downFunctionEntities=workFunctionService.searchAllPerssionEntity("1");
//        FunctionDatas.isAddQb=-1;

        if(FunctionDatas.mainFunctionDatas.size()>0){
//            for(WorkFunctionEntity entity:FunctionDatas.mainFunctionDatas){
//                if("全部".equals(entity.getFunname())){
//                    FunctionDatas.mainFunctionDatas.remove(entity);
//                    break;
//                }
//            }
            FunctionDatas.mainFunctionDatas.clear();
            if(upFunctionEntities!=null&&upFunctionEntities.size()>0){
                for(WorkFunctionEntity entity:upFunctionEntities){
                        FunctionDatas.mainFunctionDatas.add(entity);
                }
            }
        }
        if(FunctionDatas.otherFunctionDatas.size()>0){
            FunctionDatas.otherFunctionDatas.clear();
        }
        if(downFunctionEntities!=null&&downFunctionEntities.size()>0){
            for(WorkFunctionEntity entity:downFunctionEntities){
                    FunctionDatas.otherFunctionDatas.add(entity);
            }
        }
//        FunctionDatas.otherFunctionDatas.addAll(FunctionDatas.getJW_Datas());//教务数据源
//        FunctionDatas.otherFunctionDatas.addAll(FunctionDatas.getBGGL_Datas());//办公管理数据源
//        FunctionDatas.otherFunctionDatas.addAll(FunctionDatas.getKW_Datas());//考务数据源
//        FunctionDatas.otherFunctionDatas.addAll(FunctionDatas.getTSGL_Datas());//图书数据源
//        FunctionDatas.otherFunctionDatas.addAll(FunctionDatas.getPTJS_Datas());//普通教师数据源
//        FunctionDatas.otherFunctionDatas.addAll(FunctionDatas.getBZR_Datas());//班主任数据源
//        FunctionDatas.otherFunctionDatas.addAll(FunctionDatas.getXSGL_Datas());//学生数据源
//          FunctionDatas.otherFunctionDatas.addAll(FunctionDatas.getSSGL_Datas());//宿舍数据源
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        right_btn= (RelativeLayout)this.findViewById(R.id.right_btn);

        function_grid = (DragGridView)this.findViewById(R.id.function_grid);
        gridViewAdapter = new GridViewAdapter(this,FunctionDatas.mainFunctionDatas,handler);
        function_grid.setAdapter(gridViewAdapter);
        function_grid.setOnItemClickListener(upgridOnclcer);

        otherfunction_grid=(CommonGridView)this.findViewById(R.id.otherfunction_grid);
        twoGridAdapter = new AllFunctionTwoGridAdapter(this,FunctionDatas.otherFunctionDatas,handler);
        otherfunction_grid.setAdapter(twoGridAdapter);
        otherfunction_grid.setOnItemClickListener(downgridOnclcer);
    }
    public AdapterView.OnItemClickListener upgridOnclcer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            WorkFunctionEntity entity = (WorkFunctionEntity) gridViewAdapter.getItem(position);
                FuntionJumpMethod.getInstance().startJupActivity(AllFunctionTwo.this,entity.getFunflag());
        }
    };
    public AdapterView.OnItemClickListener downgridOnclcer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            WorkFunctionEntity entity = (WorkFunctionEntity) twoGridAdapter.getItem(position);
            FuntionJumpMethod.getInstance().startJupActivity(AllFunctionTwo.this,entity.getFunflag());
        }
    };
    public void initViewValues(){
       title.setText("功能管理");
        right_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("管理");
   }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                if(AllFunctionTwoGridAdapter.showAdd==1&&GridViewAdapter.deleteItem==1){
                    AllFunctionTwoGridAdapter.showAdd=-1;
                    GridViewAdapter.deleteItem=-1;
                    gridViewAdapter.notifyDataSetChanged();
                    twoGridAdapter.notifyDataSetChanged();
                    right_btn_tv.setText("管理");
                }else{
                    manageDaoDatas();
                    this.finish();
                }
                break;
            case R.id.right_btn:
                setSelect(functionFlag);
                if(functionFlag==0){
                    functionFlag=1;
                    //管理
                }else if(functionFlag==1){
                    functionFlag=0;
//                    完成
                }
                break;
        }
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    //通知我的应用数据刷新
                    right_btn_tv.setText("完成");
                    gridViewAdapter.notifyDataSetChanged();
                    functionFlag=0;
                    break;
                case 200:
                    right_btn_tv.setText("完成");
                    twoGridAdapter.notifyDataSetChanged();
                    functionFlag=0;
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if(AllFunctionTwoGridAdapter.showAdd==1&&GridViewAdapter.deleteItem==1){
                AllFunctionTwoGridAdapter.showAdd=-1;
                GridViewAdapter.deleteItem=-1;
                gridViewAdapter.notifyDataSetChanged();
                twoGridAdapter.notifyDataSetChanged();
                right_btn_tv.setText("管理");
            }else{
                manageDaoDatas();
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    public void setNormal(){
        AllFunctionTwoGridAdapter.showAdd=-1;
        GridViewAdapter.deleteItem=-1;
    }
    public void setSelect(int index){
//        setNormal();
        switch (index){
            case 0:
                //管理
                right_btn_tv.setText("管理");
                AllFunctionTwoGridAdapter.showAdd=-1;
                GridViewAdapter.deleteItem=-1;
                gridViewAdapter.notifyDataSetChanged();
                twoGridAdapter.notifyDataSetChanged();
                break;
            case 1:
                //完成
                right_btn_tv.setText("完成");
                AllFunctionTwoGridAdapter.showAdd=1;
                GridViewAdapter.deleteItem=1;
                gridViewAdapter.notifyDataSetChanged();
                twoGridAdapter.notifyDataSetChanged();
                break;
        }
    }

    public void manageDaoDatas(){

        workFunctionService.deleteAllEntity();
        for(WorkFunctionEntity entity:FunctionDatas.mainFunctionDatas){
            workFunctionService.save(entity);
        }
        for(WorkFunctionEntity entity:FunctionDatas.otherFunctionDatas){
            workFunctionService.save(entity);
        }
    }
}
