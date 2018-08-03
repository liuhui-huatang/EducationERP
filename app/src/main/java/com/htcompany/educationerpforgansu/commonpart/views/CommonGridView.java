package com.htcompany.educationerpforgansu.commonpart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.htcompany.educationerpforgansu.commonpart.views.functionmanageview.GridViewAdapter;
import com.htcompany.educationerpforgansu.workpart.adapters.AllFunctionTwoGridAdapter;

/**
 * 跟scrollview不冲突view
 * Created by wrb on 2016/12/2.
 */
public class CommonGridView extends GridView{
    public CommonGridView(Context context) {
        super(context);
        initView();
    }

    public CommonGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CommonGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    //该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套ListView也是同样的道理，不再赘述。
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
    /**长按选中拖动item*/
    private OnItemLongClickListener onLongClickListener = new OnItemLongClickListener(){

        @Override
        //����item��ʼ�϶�
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            //设置被长按显示添加按钮
            GridViewAdapter.deleteItem=1;
            ((AllFunctionTwoGridAdapter)getAdapter()).showViewAdd(1);
            return true;
        }
    };
    public void initView(){
        setOnItemLongClickListener(onLongClickListener);
    }
}
