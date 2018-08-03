package com.htcompany.educationerpforgansu.workpart.techarepart.dailogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.techarepart.entity.WeeksEntity;

import java.util.List;

/**
 * 周次选择下拉菜单
 * Created by wrb on 2017/1/20.
 */
public class WeekSelectPopWindow extends PopupWindow{
    private View conentView;
    private ListView weekselectpop_lv;
    private List<WeeksEntity> weekdatas;
    private Context context;
    private Handler tableHanler;
    public WeekSelectPopWindow(Activity context, List<WeeksEntity> weekdatas,Handler tableHanler) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.weekselectpopwindow_view, null);
        this.weekdatas = weekdatas;
        this.context = context;
        this.tableHanler = tableHanler;
        initViews();
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
// 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
// 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w / 3-30);
// 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
// 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
// 刷新状态
        this.update();
// 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
// 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
    }

    public void initViews(){
        weekselectpop_lv=(ListView)conentView.findViewById(R.id.weekselectpop_lv);
        weekselectpop_lv.setAdapter(new MyAdapter(context,weekdatas));
        weekselectpop_lv.setOnItemClickListener(itemClickListener);
    }
    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Message message = new Message();
            message.what=1000;
            message.obj = weekdatas.get(position).getValue();
            tableHanler.sendMessage(message);
        }
    };
    class MyAdapter extends BaseAdapter {
        private List<WeeksEntity> datas;
        private LayoutInflater inflater;
        public MyAdapter(Context context,List<WeeksEntity> datas) {
            inflater = LayoutInflater.from(context);
            this.datas = datas;
        }
        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }
        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.weekselectpopwindow_view_item, null);
                holder = new Holder();
                holder.tvItem = (TextView) convertView.findViewById(R.id.weeks_tv);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.tvItem.setText(datas.get(position).getLabel());
            return convertView;
        }
        class Holder {
            TextView tvItem;
        }
    }
    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
// 以下拉方式显示popupwindow
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

}
