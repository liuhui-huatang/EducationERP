package com.htcompany.educationerpforgansu.workpart.bookspart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.xListView.XListView;
import com.htcompany.educationerpforgansu.internet.bookmanage.ShoolBooksInternet;
import com.htcompany.educationerpforgansu.internet.bookmanage.ShoolBooksPersener;
import com.htcompany.educationerpforgansu.workpart.bookspart.adapters.BooksBorrowRecordAdapter;
import com.htcompany.educationerpforgansu.workpart.bookspart.entity.BookReserveEntity;
import com.htcompany.educationerpforgansu.workpart.bookspart.entity.BooksTypeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 借阅管理
 * Created by wrb on 2016/11/15.
 */
public class BooksBorrowRecordActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title, rightthree_btn_tv;
    private RelativeLayout reback_btn, right_three_btn;
    private XListView booksmanage_lv;
    private BooksBorrowRecordAdapter manageAdapter;
    private EditText booksmanage_seach_edt;
    private ImageView booksmanage_wsj_img;
    Intent intent = null;
    //网络请求类
    private ShoolBooksInternet booksInternet;
    private ShoolBooksPersener booksPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;//页数
    private String sea_name="";//图书名称
    private String sea_bookcategory="";//图书分类id
    private List<BookReserveEntity> bookEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booksmanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        bookEntities = new ArrayList<BookReserveEntity>();
        booksInternet = new ShoolBooksInternet(this,myHandler);
        booksPersener=new ShoolBooksPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        booksInternet.getBooks_MyReadsDatas(String.valueOf(pageNum),sea_name,sea_bookcategory);
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        rightthree_btn_tv = (TextView) this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        booksmanage_wsj_img=(ImageView)this.findViewById(R.id.booksmanage_wsj_img);
        right_three_btn = (RelativeLayout) this.findViewById(R.id.right_three_btn);
        booksmanage_seach_edt = (EditText)this.findViewById(R.id.booksmanage_seach_edt);
        booksmanage_lv = (XListView) this.findViewById(R.id.booksmanage_lv);
        booksmanage_lv.setPullRefreshEnable(true);
        booksmanage_lv.setPullLoadEnable(false);
        booksmanage_lv.setXListViewListener(this);
        manageAdapter = new BooksBorrowRecordAdapter(this,bookEntities);
        booksmanage_lv.setAdapter(manageAdapter);
    }

    public void initViewValues() {
        title.setText("借阅记录");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("分类");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        booksmanage_lv.setOnItemClickListener(bookManageClicer);
        booksmanage_seach_edt.addTextChangedListener(searchClicer);
    }
    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到图书详情界面
            BookReserveEntity entity = (BookReserveEntity) manageAdapter.getItem(position-1);
            intent = new Intent(BooksBorrowRecordActivity.this, BooksBorrowRecordDetailsActivity.class);
            intent.putExtra("bookentity",entity);
            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
                //调到图书分类界面
                intent = new Intent(BooksBorrowRecordActivity.this, BooksTypesActivity.class);
                startActivityForResult(intent,100);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 100:
                    BooksTypeEntity typeEntity = (BooksTypeEntity) data.getSerializableExtra("typeEntity");
                    if(typeEntity!=null){
                        sea_bookcategory = typeEntity.getId();
                        waitDialog.show();
                        pageNum=1;
                        booksInternet.getBooks_MyReadsDatas(String.valueOf(pageNum),sea_name,sea_bookcategory);
                    }
                    break;
            }
        }
    }

    public TextWatcher searchClicer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            pageNum=1;
            sea_name =booksmanage_seach_edt.getText().toString().trim();
            if(bookEntities.size()>0){
                bookEntities.clear();
                manageAdapter.notifyDataSetChanged();
            }
            booksInternet.getBooks_MyReadsDatas(String.valueOf(pageNum),sea_name,sea_bookcategory);
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
                    ToastUtil.showToast("连接超时",BooksBorrowRecordActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<BookReserveEntity> datas = booksPersener.parseSchoolbooksReceverData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setLVDatas(datas);
                    }else{
                        booksmanage_lv.setPullLoadEnable(false);
                        stopListView();
                        if(bookEntities.size()==0){
                            booksmanage_wsj_img.setVisibility(View.VISIBLE);
                        }else {
                            booksmanage_wsj_img.setVisibility(View.GONE);
                        }
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",BooksBorrowRecordActivity.this);
                    break;
            }
        }
    };
    /**
     * 更新列表数据
     * @param datas
     */
    public void setLVDatas(List<BookReserveEntity> datas){
        if(pageNum==1){
            if(bookEntities.size()>0){
                bookEntities.clear();
            }
        }
        if(datas.size()>0){
            booksmanage_lv.setPullLoadEnable(true);
        }
        for(BookReserveEntity entity:datas){
            bookEntities.add(entity);
        }
        manageAdapter.notifyDataSetChanged();
        stopListView();
        if(bookEntities.size()==0){
            booksmanage_wsj_img.setVisibility(View.VISIBLE);
        }else {
            booksmanage_wsj_img.setVisibility(View.GONE);
        }
    }
    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNum=1;
        booksInternet.getBooks_MyReadsDatas(String.valueOf(pageNum),sea_name,sea_bookcategory);
}

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMore() {
        pageNum++;
        booksInternet.getBooks_MyReadsDatas(String.valueOf(pageNum),sea_name,sea_bookcategory);
    }

    /**
     * 停止列表刷新
     */
    public void stopListView() {
        booksmanage_lv.stopRefresh();
        booksmanage_lv.stopLoadMore();
        booksmanage_lv.setRefreshTime("刚刚");
    }
}
