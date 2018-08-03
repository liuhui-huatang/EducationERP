package com.htcompany.educationerpforgansu.commonpart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 固定高度listview
 * Created by wrb on 2016/12/15.
 */
public class GDListView extends ListView{
    public GDListView(Context context) {
        super(context);
    }

    public GDListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GDListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套ListView也是同样的道理，不再赘述。
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
