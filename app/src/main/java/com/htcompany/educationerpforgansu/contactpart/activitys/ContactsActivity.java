package com.htcompany.educationerpforgansu.contactpart.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.contactview.CharacterParser;
import com.htcompany.educationerpforgansu.commonpart.views.contactview.ClearEditText;
import com.htcompany.educationerpforgansu.commonpart.views.contactview.PinyinComparator;
import com.htcompany.educationerpforgansu.commonpart.views.contactview.SideBar;
import com.htcompany.educationerpforgansu.commonpart.views.contactview.SortAdapter;
import com.htcompany.educationerpforgansu.commonpart.views.contactview.SortModel;
import com.htcompany.educationerpforgansu.contactpart.entity.ClassTxlEntity;
import com.htcompany.educationerpforgansu.internet.contact.ContactIntent;
import com.htcompany.educationerpforgansu.internet.contact.ContactPersoner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 通讯录
 * Created by wrb on 2016/11/8.
 */
public class ContactsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    /**
     * 网络请求类
     * @param savedInstanceState
     */
    private ContactIntent contactIntent;
    private ContactPersoner contactPersoner;
    private WaitDialog waitDialog=null;
    private List<ClassTxlEntity> txlEntities;
    private String contetnFlag="";
    private SharedPrefUtil sharedPrefUtil=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        sharedPrefUtil = new SharedPrefUtil(this, "login");
        contetnFlag=getIntent().getStringExtra("contetnFlag");
        txlEntities  = new ArrayList<ClassTxlEntity>();
        if(MyApp.contactsEntities.size()>0){
            MyApp.contactsEntities.clear();
        }
        contactIntent = new ContactIntent(this,tableHanler);
        contactPersoner = new ContactPersoner(this);
        waitDialog = new WaitDialog(this,"");
        if("PARTALL".equals(contetnFlag)){
            waitDialog.show();
            contactIntent.getClasstxlDatas("2");
        }else if("CLASSALL".equals(contetnFlag)){
            waitDialog.show();
            contactIntent.getClasstxlDatas("3");
        }else {
            waitDialog.show();
            contactIntent.getClasstxlDatas("");
        }

    }
    private void initViews() {
        title = (TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void initViewValues(){
        if("SCHOOLALL".equals(contetnFlag)){
            title.setText("校内通讯录");
        }else if("PARTALL".equals(contetnFlag)){
            title.setText("部门通讯录");
        }else if("CLASSALL".equals(contetnFlag)){
            title.setText("班级通讯录");
        }

    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.reback_btn:
        this.finish();
        break;
}
    }
    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",ContactsActivity.this);
                    break;
                case 200:
                    //网络请求成功，解析数据
                    List<ClassTxlEntity> tabledatas = contactPersoner.parseClassTxlData((String)msg.obj);
                    if(tabledatas!=null&&tabledatas.size()>0){
                        for(ClassTxlEntity entity:tabledatas){
                            if(entity.getHxuser().equals(sharedPrefUtil.getString("hxuser",""))){
                                tabledatas.remove(entity);
                                break;
                            }
                        }
                        initTXLDatas(tabledatas);
                    }
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",ContactsActivity.this);
                    break;
            }
        }
    };
    public void initTXLDatas(List<ClassTxlEntity> datas){
        for(ClassTxlEntity entity:datas){
            txlEntities.add(entity);
            MyApp.contactsEntities.add(entity);
        }
        //初始化通讯录数据
        SourceDateList = filledData(datas);

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);

    }

    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private List<SortModel> filledData(List<ClassTxlEntity> date){
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for(int i=0; i<date.size(); i++){
            SortModel sortModel = new SortModel();
            sortModel.setName(date.get(i).getTruename());
            sortModel.setPhonenum(date.get(i).getPhone());
            sortModel.setHxzh(date.get(i).getHxuser());
            sortModel.setPhoto(date.get(i).getPhotos());
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i).getTruename());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(SortModel sortModel : SourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 ||
                        characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }


}
