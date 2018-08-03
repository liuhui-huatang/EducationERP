package com.htcompany.educationerpforgansu.workpart.bookspart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.htcompany.educationerpforgansu.R;

/**
 * 图书管理
 * Created by wrb on 2016/11/7.
 */
public class BooksPartView extends LinearLayout{
    private Context context;
    private View booksView;
    private LayoutInflater inflater;
    public BooksPartView(Context context) {
        super(context, null);
    }

    public BooksPartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        booksView =  inflater.inflate(R.layout.bookspart_view,null);
        addView(booksView);
    }
}
