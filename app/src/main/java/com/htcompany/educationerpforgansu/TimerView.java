package com.htcompany.educationerpforgansu;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by xiaoluo on 2016/1/11.
 */
public class TimerView extends View {

    private int mWidth,mHeight;

    public TimerView(Context context){
        super(context);
    }
    public TimerView(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {

        //设置画布背景
        canvas.drawColor(Color.GRAY);

        //获取屏幕宽度
        mWidth=getMeasuredWidth();
        //获取屏幕高度
        mHeight=getMeasuredHeight();

        //获取系统时间
        //Calendar里面月份的值是从0~11，对应1~12
        //星期几是从星期天开始算的
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int week=calendar.get(Calendar.DAY_OF_WEEK);
        String sYear,sMonth,sDay,sWeek;
        sYear=String.valueOf(year);
        sMonth=String.valueOf(month+1);
        sDay=String.valueOf(day);
        sWeek=String.valueOf(week);
        if (week==1){
            sWeek="日";
        }else if(week==2){
            sWeek="一";
        }else if(week==3){
            sWeek="二";
        }else if(week==4){
            sWeek="三";
        }else if(week==5){
            sWeek="四";
        }else if(week==6){
            sWeek="五";
        }else if(week==7){
            sWeek="六";
        }
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        if (hour>12){
            hour=hour-12;
        }
        int minute=calendar.get(Calendar.MINUTE);
        int second=calendar.get(Calendar.SECOND);

        //显示时间
        Paint paintTime=new Paint();
        paintTime.setColor(Color.BLUE);
        //要先设置画笔颜色后，设置透明度才有效果
        paintTime.setAlpha(100);
        paintTime.setStrokeWidth(25);
        paintTime.setTextSize(50);
        String mHour=String.valueOf(hour);
        canvas.drawText(mHour, mWidth / 2-80, mHeight / 2-130, paintTime);
        canvas.drawText(":", mWidth / 2-20, mHeight / 2-130, paintTime);
        String mMinute=String.valueOf(minute);
        canvas.drawText(mMinute, mWidth / 2, mHeight / 2-130, paintTime);
        canvas.drawText(":", mWidth / 2+60, mHeight / 2-130, paintTime);
        String mSecond=String.valueOf(second);
        canvas.drawText(mSecond,mWidth/2+80,mHeight/2-130,paintTime);

        canvas.drawText(sYear+"."+sMonth+"."+sDay+"-----星期"+sWeek,mWidth/2-200,mHeight/2+180,paintTime);

        //画时间的背景-长方形
        Paint paintBgTime=new Paint();
        paintBgTime.setStyle(Paint.Style.STROKE);//空心
        paintBgTime.setStrokeWidth(5);
        paintBgTime.setColor(Color.BLACK);
        paintBgTime.setAlpha(150);
        //圆角矩形
        //左上右下
        canvas.drawRoundRect(mWidth / 2 - 110, mHeight / 2 - 180, mWidth / 2 + 170, mHeight / 2 - 110, 10, 10, paintBgTime);

        //画外圆
        Paint paintCircle=new Paint();
        //设置画笔的风格（空心或者实心）
        paintCircle.setColor(Color.DKGRAY);
        paintCircle.setStyle(Paint.Style.STROKE);
        //设置画笔的锯齿效果
        paintCircle.setAntiAlias(true);
        //设置空心边框的宽度
        paintCircle.setStrokeWidth(5);

        //cx：圆心的x坐标 cy：圆心的y坐标。 radius：圆的半径。
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/2,paintCircle);

        //画刻度
        Paint paintDegree=new Paint();
        for (int i=0;i<12;i++){

            //区分整点和非整点
            if (i==0||i==3||i==6||i==9){
                //画刻度线
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(30);
                /*
                    * startX：起始端点的X坐标。

                    startY：起始端点的Y坐标。

                    stopX：终止端点的X坐标。

                    stopY：终止端点的Y坐标。*/
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,mWidth/2,(mHeight/2-mWidth/2)+60,paintDegree);
                String degree=String.valueOf(i);
                canvas.drawText(degree,mWidth/2-paintDegree.measureText(degree)/2,mHeight/2-mWidth/2+90,paintDegree);
            }else {
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(30);
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,mWidth/2,mHeight/2-mWidth/2+30,paintDegree);
                String degree=String.valueOf(i);
                canvas.drawText(degree,mWidth/2-paintDegree.measureText(degree)/2,mHeight/2-mWidth/2+60,paintDegree);
            }

            //通过旋转画布简化坐标运算
            canvas.rotate(30,mWidth/2,mHeight/2);

        }

        //画指针
        Paint paintHour=new Paint();
        paintHour.setColor(Color.GREEN);
        paintHour.setStrokeWidth(8);
        Paint paintMinute=new Paint();
        paintMinute.setColor(Color.YELLOW);
        paintMinute.setStrokeWidth(7);
        Paint paintSecond=new Paint();
        paintSecond.setColor(Color.RED);
        paintSecond.setStrokeWidth(6);
        canvas.save();
        float mTimeDegree=0;
        float mMinuteDegree=0;
        float mSecondDegree=0;
        //时针旋转角度
        if (hour<6){
            mMinuteDegree=minute/2;
            mTimeDegree=360-(6-hour)*30+mMinuteDegree;
        }else{
            mMinuteDegree=minute/2;
            mTimeDegree=(hour-6)*30+mMinuteDegree;
        }
        canvas.rotate(mTimeDegree, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2, mWidth / 2, mHeight / 2 + 120, paintHour);
        //还原画布
        canvas.restore();
        canvas.save();
        //分针旋转角度
        if (minute>30){
            mMinuteDegree=(minute-30)*6;
        }else {
            mMinuteDegree=(minute+30)*6;
        }
        canvas.rotate(mMinuteDegree, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2, mWidth / 2, mHeight / 2 + 200, paintMinute);

        canvas.restore();
        canvas.save();
        //秒针旋转角度
        if (second>30){
            mSecondDegree=(second-30)*6;
        }else {
            mSecondDegree=(second+30)*6;
        }
        canvas.rotate(mSecondDegree, mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, mHeight / 2, mWidth / 2, mHeight / 2 + 250, paintSecond);

        Paint paintSmallCircle=new Paint();
        paintSmallCircle.setColor(Color.BLACK);
        paintSmallCircle.setAlpha(200);
        paintSmallCircle.setStyle(Paint.Style.STROKE);//实心
        paintSmallCircle.setStrokeWidth(5);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 5, paintSmallCircle);
        //每隔一秒钟就通知View重绘
        postInvalidateDelayed(100);
    }


}
