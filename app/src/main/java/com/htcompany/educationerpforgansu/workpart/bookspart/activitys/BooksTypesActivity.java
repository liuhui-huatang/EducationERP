package com.htcompany.educationerpforgansu.workpart.bookspart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.internet.bookmanage.ShoolBooksInternet;
import com.htcompany.educationerpforgansu.internet.bookmanage.ShoolBooksPersener;
import com.htcompany.educationerpforgansu.workpart.bookspart.adapters.BooksTypeAdapter;
import com.htcompany.educationerpforgansu.workpart.bookspart.entity.BooksTypeEntity;

import java.util.List;

/**
 * 图书类别
 * Created by wrb on 2016/11/15.
 */
public class BooksTypesActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private GridView bookstypes_grid;
    private BooksTypeAdapter typeAdapter;
    //网络请求类
    private ShoolBooksInternet booksInternet;
    private ShoolBooksPersener booksPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookstypes_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        booksInternet = new ShoolBooksInternet(this,myHandler);
        booksPersener=new ShoolBooksPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        booksInternet.getBooks_TypeDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        bookstypes_grid = (GridView)this.findViewById(R.id.bookstypes_grid);
        bookstypes_grid.setOnItemClickListener(gridCicler);
    }
    public void initViewValues(){
        title.setText("图书分类");
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
    public AdapterView.OnItemClickListener gridCicler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            BooksTypeEntity entity = (BooksTypeEntity) typeAdapter.getItem(position);
            Intent intent = new Intent();
            intent.putExtra("typeEntity",entity);
            setResult(100,intent);
            finish();
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
                    ToastUtil.showToast("连接超时",BooksTypesActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<BooksTypeEntity> datas = booksPersener.parseSchoolBookTypeData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        typeAdapter = new BooksTypeAdapter(BooksTypesActivity.this,datas);
                        bookstypes_grid.setAdapter(typeAdapter);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",BooksTypesActivity.this);
                    break;
            }
        }
    };
}
