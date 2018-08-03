package com.htcompany.educationerpforgansu.workpart.bookspart.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.bookspart.entity.BookReserveEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 借阅详情
 * Created by wrb on 2016/11/15.
 */
public class BooksBorrowRecordDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, right_btn_tv;
    private RelativeLayout reback_btn, right_text_btn;
    private ImageView bookborrowdetails_photo_img;
    private TextView bookborrowdetails_name_tv,bookborrowdetails_auther_tv,bookborrowdetails_type_tv,
            bookborrowdetails_postion_tv,bookborrowdetails_code_tv,bookborrowdetails_gjcode_tv,bookborrowdetails_cbs_tv,bookborrowdetails_price_tv,
            bookborrowdetails_allcount_tv,bookborrowdetails_kccount_tv,bookborrowdetails_isgc_tv,bookborrowdetails_remark_tv;
    private TextView bookborrowdetails_jysj_tv,bookborrowdetails_ghsj_tv;
    private BookReserveEntity bookEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booksborrowdetias_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        bookEntity = (BookReserveEntity) getIntent().getSerializableExtra("bookentity");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        bookborrowdetails_photo_img=(ImageView)this.findViewById(R.id.bookborrowdetails_photo_img);
        bookborrowdetails_name_tv= (TextView)this.findViewById(R.id.bookborrowdetails_name_tv);
        bookborrowdetails_auther_tv= (TextView)this.findViewById(R.id.bookborrowdetails_auther_tv);
        bookborrowdetails_type_tv= (TextView)this.findViewById(R.id.bookborrowdetails_type_tv);
        bookborrowdetails_postion_tv= (TextView)this.findViewById(R.id.bookborrowdetails_postion_tv);
        bookborrowdetails_code_tv= (TextView)this.findViewById(R.id.bookborrowdetails_code_tv);
        bookborrowdetails_gjcode_tv= (TextView)this.findViewById(R.id.bookborrowdetails_gjcode_tv);
        bookborrowdetails_cbs_tv= (TextView)this.findViewById(R.id.bookborrowdetails_cbs_tv);
        bookborrowdetails_price_tv= (TextView)this.findViewById(R.id.bookborrowdetails_price_tv);
        bookborrowdetails_allcount_tv= (TextView)this.findViewById(R.id.bookborrowdetails_allcount_tv);
        bookborrowdetails_kccount_tv= (TextView)this.findViewById(R.id.bookborrowdetails_kccount_tv);
        bookborrowdetails_isgc_tv= (TextView)this.findViewById(R.id.bookborrowdetails_isgc_tv);
        bookborrowdetails_remark_tv= (TextView)this.findViewById(R.id.bookborrowdetails_remark_tv);
        bookborrowdetails_jysj_tv= (TextView)this.findViewById(R.id.bookborrowdetails_jysj_tv);
        bookborrowdetails_ghsj_tv= (TextView)this.findViewById(R.id.bookborrowdetails_ghsj_tv);
    }
    public void initViewValues(){
        title.setText("借阅详情");
        if(bookEntity!=null){
            if(!"".equals(bookEntity.getPhoto())&&bookEntity.getPhoto()!=null){
                ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+bookEntity.getPhoto(),
                        bookborrowdetails_photo_img, MyApp.getOptions());
            }
            bookborrowdetails_name_tv.setText(bookEntity.getBookData().getName());
            bookborrowdetails_auther_tv.setText(bookEntity.getBookData().getZuozhe());
            bookborrowdetails_type_tv.setText("类别:"+bookEntity.getBookData().getBooktype_name());
            bookborrowdetails_postion_tv.setText("书架:"+bookEntity.getBookData().getShelf_name());
            bookborrowdetails_code_tv.setText(bookEntity.getBookData().getCode());
            bookborrowdetails_gjcode_tv.setText(bookEntity.getBookData().getCode());
            bookborrowdetails_cbs_tv.setText(bookEntity.getBookData().getChubanshe());
            bookborrowdetails_price_tv.setText(bookEntity.getBookData().getPrice()+"元");
            bookborrowdetails_allcount_tv.setText(bookEntity.getBookData().getHejishuliang()+"本");
            bookborrowdetails_kccount_tv.setText(bookEntity.getBookData().getKucun()+"本");
            bookborrowdetails_isgc_tv.setText(bookEntity.getBookData().getIsguancang_name());
            bookborrowdetails_remark_tv.setText(bookEntity.getNote());
            bookborrowdetails_jysj_tv.setText("借阅时间："+bookEntity.getJieyue_time());
            bookborrowdetails_ghsj_tv.setText("归还时间："+bookEntity.getGuihuan_time());
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
}
