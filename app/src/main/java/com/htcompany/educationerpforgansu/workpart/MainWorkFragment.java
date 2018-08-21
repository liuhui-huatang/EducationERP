package com.htcompany.educationerpforgansu.workpart;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.SPUtil;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.tools.BaseUtils;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.GDGridView;
import com.htcompany.educationerpforgansu.commonpart.views.GDListView;
import com.htcompany.educationerpforgansu.commonpart.views.bannerview.AutoScrollViewPager;
import com.htcompany.educationerpforgansu.commonpart.views.bannerview.EventClick;
import com.htcompany.educationerpforgansu.commonpart.views.bannerview.ImagePagerAdapter;
import com.htcompany.educationerpforgansu.commonpart.views.bannerview.ListUtils;
import com.htcompany.educationerpforgansu.commonpart.views.functionmanageview.FuntionJumpMethod;
import com.htcompany.educationerpforgansu.dao.daoservice.WorkFunctionService;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;
import com.htcompany.educationerpforgansu.workpart.activitys.AllFunctionTwo;
import com.htcompany.educationerpforgansu.workpart.activitys.CommonNoticeActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.MainNewsDetailsActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.MainNewsMoreActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.WorkFlowActivity;
import com.htcompany.educationerpforgansu.workpart.adapters.MainDBSX_Adapter;
import com.htcompany.educationerpforgansu.workpart.adapters.MainQXGG_Adapter;
import com.htcompany.educationerpforgansu.workpart.adapters.MainWorkFunctionGridAdapter;
import com.htcompany.educationerpforgansu.workpart.adapters.MainWorkNewsAdapter;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.entity.WorkNoticeEntity;
import com.htcompany.educationerpforgansu.workpart.entities.DYNewsEntity;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.WaitFlowEntity;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFunctionEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 办公
 * Created by wrb on 2016/11/2.
 */
public class MainWorkFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Intent intent = null;
    //待办事项，学校公告，部门公告
    private LinearLayout mainwork_waitb_rel, mainwork_shoolgg_rel, mainwork_partgg_rel;
    //待办事项数据，学校公告数据，部门公告数据
    private TextView main_dbsxcount_tv, main_qxggcount_tv, main_bmggcount_tv;
    private int viewFlag = 0;//0 待办事项，1全校公告，2部门公告
    //轮播图
    private ArrayList<DYNewsEntity> bannarList = new ArrayList<DYNewsEntity>();
    private AutoScrollViewPager view_pager;
    private LinearLayout main_dot_ll;
    private int oldPosition, index;
    //功能列表
    private GDGridView mainfunction_grid;
    private MainWorkFunctionGridAdapter functionGridAdapter;
    private WorkFunctionService workFunctionService;//所有数据管理类
    private List<WorkFunctionEntity> functionEntities;
    //数据源
    private List<WaitFlowEntity> waitFlowEntities;//工作流
    private List<WorkNoticeEntity> qxNoticeEntities;//全校公告
    private List<WorkNoticeEntity> bmNoticeEntities;//部门公告
    //新闻列表
    private RelativeLayout main_newsmore_rel;
    private GDListView main_tennews_gl;
    private MainWorkNewsAdapter newsAdapter;
    private ArrayList<DYNewsEntity> allnewsList = new ArrayList<DYNewsEntity>();

    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    SharedPrefUtil sharedPrefUtil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mainwork_fragment, container, false);
        initViewPagerView();

        initDatas();
        initViews();
        initOnclicEvents();
//        initVieValues();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();


    }

    public void initDatas() {
        functionEntities = new ArrayList<WorkFunctionEntity>();
        workFunctionService = new WorkFunctionService(getActivity());
        waitFlowEntities = new ArrayList<WaitFlowEntity>();
        qxNoticeEntities = new ArrayList<WorkNoticeEntity>();
        bmNoticeEntities = new ArrayList<WorkNoticeEntity>();
        wokrpersonalPersener = new WokrpersonalPersener(getActivity());
        wokrpersonalInternet = new WokrpersonalInternet(getActivity(), myHandler);
        //请求新闻
        wokrpersonalInternet.getMyDyNews_Datas("1", "");
        wokrpersonalInternet.getAllDyNews_Datas("", "");

        wokrpersonalInternet.getWaitClGZL("");
        wokrpersonalInternet.getWorkNoticeMyPartListDatas();
        wokrpersonalInternet.getWorkNoticeSchoolListDatas("2");
        wokrpersonalInternet.getMainMySendClGZL("");
    }

    public void initViews() {
        mainwork_waitb_rel = (LinearLayout) view.findViewById(R.id.mainwork_waitb_rel);
        main_dbsxcount_tv = (TextView) view.findViewById(R.id.main_dbsxcount_tv);
        mainwork_shoolgg_rel = (LinearLayout) view.findViewById(R.id.mainwork_shoolgg_rel);
        main_qxggcount_tv = (TextView) view.findViewById(R.id.main_qxggcount_tv);
        mainwork_partgg_rel = (LinearLayout) view.findViewById(R.id.mainwork_partgg_rel);
        main_bmggcount_tv = (TextView) view.findViewById(R.id.main_bmggcount_tv);
        //功能按钮
        mainfunction_grid = (GDGridView) view.findViewById(R.id.mainfunction_grid);
        functionGridAdapter = new MainWorkFunctionGridAdapter(getActivity(), functionEntities);
        mainfunction_grid.setAdapter(functionGridAdapter);
        mainfunction_grid.setOnItemClickListener(gridOnclcer);
        //=====================================================
        //新闻列表
        main_tennews_gl = (GDListView) view.findViewById(R.id.main_tennews_gl);
        newsAdapter = new MainWorkNewsAdapter(getActivity(), allnewsList);
        main_tennews_gl.setAdapter(newsAdapter);
        main_newsmore_rel = (RelativeLayout) view.findViewById(R.id.main_newsmore_rel);
        main_tennews_gl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DYNewsEntity entity = (DYNewsEntity) newsAdapter.getItem(position);
                startActivity(new Intent(getActivity(), MainNewsDetailsActivity.class)
                        .putExtra("entity", entity));
            }
        });
    }

    public void initOnclicEvents() {
        mainwork_waitb_rel.setOnClickListener(this);
        mainwork_shoolgg_rel.setOnClickListener(this);
        mainwork_partgg_rel.setOnClickListener(this);
        main_newsmore_rel.setOnClickListener(this);
    }

//    public void initVieValues() {
//        main_qxggcount_tv.setText(qxNoticeEntities.size() + "");
//        main_bmggcount_tv.setText(bmNoticeEntities.size() + "");
//    }

    public void setFuntions() {
        List<WorkFunctionEntity> datas = workFunctionService.searchAllMainEntity("1");
        if (datas != null && datas.size() > 0) {
            if (functionEntities.size() > 0) {
                functionEntities.clear();
            }
            for (WorkFunctionEntity entity : datas) {
                if(!entity.getFunflag().equals(FunctionDatas.WORK_GRBG_CARUSE)){
                    functionEntities.add(entity);
                }

            }
            int isQbFlag = 0;
            WorkFunctionEntity allFunciotnEntity = new WorkFunctionEntity();
            allFunciotnEntity.setFunname("全部");
            allFunciotnEntity.setFunimg(R.mipmap.fuction_all_icon);
            allFunciotnEntity.setIsmain("1");
            allFunciotnEntity.setIspermission("0");
            if (functionEntities.size() > 0) {
                for (WorkFunctionEntity entity : functionEntities) {
                    if ("全部".equals(entity.getFunname())) {
                        isQbFlag = 1;
                        break;
                    }
                }
            } else {
                functionEntities.add(allFunciotnEntity);
                isQbFlag = 1;
            }
            if (isQbFlag == 0) {
                functionEntities.add(allFunciotnEntity);
            }
            functionGridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainwork_waitb_rel:
                //待办事项
                SPUtil.saveInt("saveDbsxNum",SPUtil.getInt("newDbsxNum",0));
                main_dbsxcount_tv.setText("0");

                intent = new Intent(getActivity(), WorkFlowActivity.class);
                startActivity(intent);
                break;
            case R.id.mainwork_shoolgg_rel:
                //全校公告
                SPUtil.saveInt("saveQxggNum", SPUtil.getInt("newQxggNum",0));
                main_qxggcount_tv.setText("0");

                intent = new Intent(getActivity(), CommonNoticeActivity.class);
                intent.putExtra("noticetype", "2");
                intent.putExtra("ismain", "1");
                startActivity(intent);
                break;
            case R.id.mainwork_partgg_rel:
                //部门公告
                SPUtil.saveInt("saveBmggNum", SPUtil.getInt("newBmggNum",0));
                main_bmggcount_tv.setText("0");

                intent = new Intent(getActivity(), CommonNoticeActivity.class);
                intent.putExtra("noticetype", "1");
                intent.putExtra("ismain", "1");
                startActivity(intent);
                break;
            case R.id.main_newsmore_rel:
                //新闻列表
                intent = new Intent(getActivity(), MainNewsMoreActivity.class);
                startActivity(intent);
                break;
        }
    }


    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 400:
                    if (isAdded()) {
                        ToastUtil.showToast("请求网络失败", getActivity());
                    }
                    break;
                case 200:
                    //处理待办数据

                    int saveDbsxNum = SPUtil.getInt("saveDbsxNum", 0);
                    int newDbsxNum = Integer.parseInt(wokrpersonalPersener.parseWaitWrokFlowCount((String) msg.obj));
                    int dbsxnum = newDbsxNum > saveDbsxNum ? newDbsxNum - saveDbsxNum : 0;
                    SPUtil.saveInt("newDbsxNum",newDbsxNum);
                    main_dbsxcount_tv.setText(dbsxnum + "");

                    Log.e("待办数据==saveDbsxNum",saveDbsxNum+"");
                    Log.e("待办数据==newDbsxNum",newDbsxNum+"");
                    Log.e("待办数据==dbsxnum",dbsxnum+"");
                    break;
                case 205:
                    //获取我发起的办数据
                    MyApp.msendcount = wokrpersonalPersener.parseWaitWrokFlowCount((String) msg.obj);
                    functionGridAdapter.notifyDataSetChanged();
                    break;
                case 201:
                    //处理全校公告数据
                    List<WorkNoticeEntity> qxdatas = wokrpersonalPersener.parseWorknotcie_ListData((String) msg.obj);
                    if (qxdatas != null && qxdatas.size() > 0) {
                        if (qxNoticeEntities.size() > 0) {
                            qxNoticeEntities.clear();
                        }
                        for (WorkNoticeEntity e1 : qxdatas) {
                            qxNoticeEntities.add(e1);
                        }
                    } else {

                    }


                    int saveQxggNum = SPUtil.getInt("saveQxggNum", 0);
                    int newQxggNum = qxNoticeEntities.size();
                    int qxggnum = newQxggNum > saveQxggNum ? newQxggNum - saveQxggNum : 0;
                    SPUtil.saveInt("newQxggNum", newQxggNum);
                    main_qxggcount_tv.setText(qxggnum + "");


                    break;
                case 202:
                    //处理部门公告数据
                    List<WorkNoticeEntity> partdatas = wokrpersonalPersener.parseWorknotcie_ListData((String) msg.obj);
                    if (partdatas != null && partdatas.size() > 0) {
                        if (viewFlag == 2) {
                            //待办事项
                        } else {

                        }
                        if (bmNoticeEntities.size() > 0) {
                            bmNoticeEntities.clear();
                        }
                        for (WorkNoticeEntity e1 : partdatas) {
                            bmNoticeEntities.add(e1);
                        }
                    } else {
                    }


                    int saveBmggNum = SPUtil.getInt("saveBmggNum", 0);
                    int newBmggNum = bmNoticeEntities.size();
                    int bmggnum = newBmggNum > saveBmggNum ? newBmggNum - saveBmggNum : 0;
                    SPUtil.saveInt("newBmggNum", newBmggNum);
                    main_bmggcount_tv.setText(bmggnum + "");

                    break;
                case 203:
                    //处理德育新闻数据
                    List<DYNewsEntity> newsdatas = wokrpersonalPersener.parseDYnewsData((String) msg.obj);
                    if (newsdatas != null && newsdatas.size() > 0) {
                        if (bannarList.size() > 0) {
                            bannarList.clear();
                        }
                        for (DYNewsEntity entity : newsdatas) {
                            bannarList.add(entity);
                        }
                        if (isAdded()) {
                            initViewPagerDatas();
                        }
                    }
                    break;
                case 204:
                    //处理德育新闻数据
                    List<DYNewsEntity> newsAlldatas = wokrpersonalPersener.parseDYnewsData((String) msg.obj);
                    if (newsAlldatas != null && newsAlldatas.size() > 0) {
                        if (allnewsList.size() > 0) {
                            allnewsList.clear();
                        }
                        for (int i = 0; i < newsAlldatas.size(); i++) {
                            if (i > 9) {
                                break;
                            } else {
                                allnewsList.add(newsAlldatas.get(i));
                            }
                        }
                        newsAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };


    @Override
    public void onPause() {
        super.onPause();
        if (isAdded()) {
            view_pager.stopAutoScroll();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isAdded()) {
            view_pager.startAutoScroll();
            setFuntions();
        }
    }

    public AdapterView.OnItemClickListener gridOnclcer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            WorkFunctionEntity entity = (WorkFunctionEntity) functionGridAdapter.getItem(position);
            if ("全部".equals(entity.getFunname())) {
                intent = new Intent(getActivity(), AllFunctionTwo.class);
                startActivity(intent);
            } else {
                FuntionJumpMethod.getInstance().startJupActivity(getActivity(), entity.getFunflag());
            }
        }
    };



    /**
     * 初始化轮播图
     */
    private void initViewPagerView() {
        main_dot_ll = (LinearLayout) view.findViewById(R.id.main_dot_ll);
        view_pager = (AutoScrollViewPager) view.findViewById(R.id.view_pager);
        view_pager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    public void initViewPagerDatas() {
        initDots();
        view_pager.setAdapter(new ImagePagerAdapter(getActivity(), bannarList, new EventClick() {

            @Override
            public void eventClick() {
                // TODO Auto-generated method stub
//                Log.d("TAG", index+"处理点击每张图片的点击事件："+bannarList.get(index).getTitle()+"----:"+bannarList.get(index).getUpdate_time());
                DYNewsEntity entity = bannarList.get(index);
                startActivity(new Intent(getActivity(), MainNewsDetailsActivity.class)
                        .putExtra("entity", entity));
            }
        }).setInfiniteLoop(true));
        view_pager.setInterval(5000);
        view_pager.startAutoScroll();
//        view_pager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % bannarList.size());
        view_pager.setCurrentItem(0);

    }

    private void initDots() {
        if (main_dot_ll != null) {
            main_dot_ll.removeAllViews();
        }
        for (int i = 0; i < bannarList.size(); i++) {
            if (i == 0) {
                main_dot_ll.addView(setDaoHangText(R.drawable.dot_select));
            } else {
                main_dot_ll.addView(setDaoHangText(R.drawable.dot_normal));
            }
        }
    }

    private View setDaoHangText(int id) {
        View text = new View(getActivity());
        LinearLayout.LayoutParams Viewpar = new LinearLayout.LayoutParams(BaseUtils.dip2px(getActivity(), 8), BaseUtils.dip2px(getActivity(), 8));
        Viewpar.setMargins(5, 5, 5, 5);
        text.setLayoutParams(Viewpar);
        text.setBackgroundResource(id);
        return text;
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            if (main_dot_ll != null && main_dot_ll.getChildCount() > 0 && main_dot_ll.getChildAt(oldPosition) != null) {
                index = (position) % ListUtils.getSize(bannarList);
                main_dot_ll.getChildAt(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                main_dot_ll.getChildAt((position) % ListUtils.getSize(bannarList)).setBackgroundResource(R.drawable.dot_select);
                oldPosition = (position) % ListUtils.getSize(bannarList);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    }


}
