package com.htcompany.educationerpforgansu.workpart.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.commonpart.BaseActivity;
import com.htcompany.educationerpforgansu.commonpart.tools.ToastUtil;
import com.htcompany.educationerpforgansu.commonpart.views.WaitDialog;
import com.htcompany.educationerpforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.educationerpforgansu.commonpart.views.photoview.Bimp;
import com.htcompany.educationerpforgansu.commonpart.views.photoview.FileUtils;
import com.htcompany.educationerpforgansu.commonpart.views.photoview.GetPhotoPopwindow;
import com.htcompany.educationerpforgansu.commonpart.views.photoview.ImageItem;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalInternet;
import com.htcompany.educationerpforgansu.internet.workgrzx.WokrpersonalPersener;

/**
 * 资产报修
 * Created by wrb on 2016/11/9.
 */
public class AssetRepairsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,rightthree_btn_tv;
    private RelativeLayout reback_btn,right_three_btn;
    private LinearLayout assetrepair_ll;
    //标题，地点，描述，报修人
    private EditText assetreparirs_title_edt,assetreparirs_address_edt,assetreparirs_ms_edt;
    private ImageView assetreparirs_photo_img;//照片
    private DateViewDailog dateViewDailog;
    private GetPhotoPopwindow getPhotoPopwindow;
    //网络请求类
    private WokrpersonalInternet wokrpersonalInternet;
    private WokrpersonalPersener wokrpersonalPersener;
    private WaitDialog waitDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assetrepairs_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
        initOnclicEvents();
    }
    public void initDatas(){
        if( Bimp.tempSelectBitmap.size()>0){
            Bimp.tempSelectBitmap.clear();
        }
        waitDialog = new WaitDialog(this,"");
        wokrpersonalPersener = new WokrpersonalPersener(this);
        wokrpersonalInternet = new WokrpersonalInternet(this,myHandler);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        rightthree_btn_tv= (TextView)this.findViewById(R.id.rightthree_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        assetrepair_ll = (LinearLayout)this.findViewById(R.id.assetrepair_ll);
        right_three_btn =(RelativeLayout)this.findViewById(R.id.right_three_btn);
        assetreparirs_title_edt=(EditText)this.findViewById(R.id.assetreparirs_title_edt);
        assetreparirs_address_edt=(EditText)this.findViewById(R.id.assetreparirs_address_edt);
        assetreparirs_ms_edt=(EditText)this.findViewById(R.id.assetreparirs_ms_edt);

        assetreparirs_photo_img = (ImageView)this.findViewById(R.id.assetreparirs_photo_img);

    }
    public void initViewValues(){
        title.setText("资产维护");
        right_three_btn.setVisibility(View.VISIBLE);
        rightthree_btn_tv.setText("提交");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_three_btn.setOnClickListener(this);
        assetreparirs_photo_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_three_btn:
               //提交资产维护
                if("".equals(assetreparirs_title_edt.getText().toString())){
                    ToastUtil.showToast("标题不能为空",AssetRepairsActivity.this);
                }else if("".equals(assetreparirs_address_edt.getText().toString())){
                    ToastUtil.showToast("地址不能为空",AssetRepairsActivity.this);
                }else if("".equals(assetreparirs_ms_edt.getText().toString())){
                    ToastUtil.showToast("描述不能为空",AssetRepairsActivity.this);
                }else if(Bimp.tempSelectBitmap.size()==0){
                    ToastUtil.showToast("请拍摄照片",AssetRepairsActivity.this);
                }else{
                    waitDialog.show();
                    wokrpersonalInternet.addAsset_RepairListDatas(assetreparirs_title_edt.getText().toString(),
                            assetreparirs_address_edt.getText().toString(),assetreparirs_ms_edt.getText().toString());
                }
                break;
            case R.id.assetreparirs_photo_img:
                getPhotoPopwindow = new GetPhotoPopwindow(AssetRepairsActivity.this,myHandler);
                getPhotoPopwindow.showPopupWindowBottom(assetrepair_ll);
                break;
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 10000:
                    //拍照
                    photo();
                    getPhotoPopwindow.dismiss();
                    break;
                case 20000:
                    //本地相册
                    getPhotoFromXC();
                    getPhotoPopwindow.dismiss();
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",AssetRepairsActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(wokrpersonalPersener.parserAddAssetReapir((String)msg.obj)){
                    Intent intent  = new Intent();
                    setResult(100,intent);
                    finish();
                    }else{
                        ToastUtil.showToast("请重新添加",AssetRepairsActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",AssetRepairsActivity.this);
                    break;
            }
        }
    };
    //=======================================拍照部分===============================================

    protected void onRestart() {
        if(Bimp.tempSelectBitmap.size()>0){
            assetreparirs_photo_img.setImageBitmap(Bimp.tempSelectBitmap.get(0).getBitmap());
        }
        super.onRestart();
    }
    public void photo() {

        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }
    private static final int TAKE_PICTURE = 0x000001;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                //图片名称
                if(data!=null&&data.getExtras()!=null&&data.getExtras().get("data")!=null){
                    if( Bimp.tempSelectBitmap.size()>0){
                        Bimp.tempSelectBitmap.clear();
                    }
                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    String imagepath=FileUtils.saveBitmap(bm, fileName+".png");
                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bm);
                    takePhoto.setImagePath(imagepath);
                    takePhoto.setFilename(fileName+".png");
                    Bimp.tempSelectBitmap.add(takePhoto);
                }else{
                    System.out.print("没有拍摄照片");
                }
                break;
            case Activity.RESULT_CANCELED:
                finish();
                break;
            case 12:
                if (data!= null&&data.getData()!=null) {
                    startPhotoZoom(data.getData());
                }
                break;
            case 3:
                if (data!= null&&data.getExtras()!=null&& data.getExtras().getParcelable("data")!=null) {
                    setPicToView(data);
                }
                break;
        }
    }
    /**
     * 从相册中选取图片
     */
    public void getPhotoFromXC(){
        /**
         * 从相册获取图片
         */
        Intent intent = new Intent();
 /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
	/* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
	/* 取得相片后返回本画面 */
        startActivityForResult(intent, 12);
    }
    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        Bitmap photo = extras.getParcelable("data");
        if( Bimp.tempSelectBitmap.size()>0){
            Bimp.tempSelectBitmap.clear();
        }
        if (extras!= null&&photo!=null) {
            String imagepath= FileUtils.saveBitmap(photo, String.valueOf(System.currentTimeMillis())+".png");
            ImageItem takePhoto = new ImageItem();
            takePhoto.setBitmap(photo);
            takePhoto.setImagePath(imagepath);
            takePhoto.setFilename(String.valueOf(System.currentTimeMillis())+".png");
            Bimp.tempSelectBitmap.add(takePhoto);
        }
    }
    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
}
