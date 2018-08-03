package com.htcompany.educationerpforgansu.commonpart.views.functionmanageview;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFunctionEntity;

import java.util.Collections;
import java.util.List;
public class GridViewAdapter extends BaseAdapter{
	 private Context context;
	    private List<WorkFunctionEntity> strList;
	    private int hidePosition = AdapterView.INVALID_POSITION;
        private LayoutInflater inflater;
	/**
	 * 需要显示删除标志的项的索引
	 */
    	public static int deleteItem = -1;

	private Handler myHandler;
	    public GridViewAdapter(Context context, List<WorkFunctionEntity> strList,Handler myHandler) {
	        this.context = context;
	        this.strList = strList;
			this.myHandler = myHandler;
	        inflater = LayoutInflater.from(context);
	    }

	    @Override
	    public int getCount() {
	        return strList.size();
	    }

	@Override
	public Object getItem(int position) {
		return strList.get(position);
	}


	@Override
	    public long getItemId(int position) {
	        return position;
	    }

	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	ViewHoder vh;
	        if(convertView == null) {
	        	vh = new ViewHoder();
	        	convertView = inflater.inflate(R.layout.function_gridview_item, null);
	            vh.tv_dgv_item = (TextView)convertView.findViewById(R.id.tv_dgv_item);
	            vh.iv_delete_dgv_item = (ImageView)convertView.findViewById(R.id.iv_delete_dgv_item);
				vh.iv_dgv_item= (ImageView)convertView.findViewById(R.id.iv_dgv_item);
	            convertView.setTag(vh);
	        }
	        else {
	            vh = (ViewHoder) convertView.getTag();
	        }
			vh.iv_dgv_item.setImageResource(strList.get(position).getFunimg());
			//hide时隐藏Text
	        if(position != hidePosition) {
	        	vh.tv_dgv_item.setText(strList.get(position).getFunname());
	        	convertView.setVisibility(View.VISIBLE);
	        }else {
	        	vh.tv_dgv_item.setText("");
	        	convertView.setVisibility(View.GONE);
	        }
//	        if (position == deleteItem) {
//	        	vh.iv_delete_dgv_item.setVisibility(View.VISIBLE);
//	        }else{
//	        	vh.iv_delete_dgv_item.setVisibility(View.GONE);
//	        }
			if(deleteItem==1){
				vh.iv_delete_dgv_item.setVisibility(View.VISIBLE);
			}else{
				vh.iv_delete_dgv_item.setVisibility(View.GONE);
			}
			vh.iv_delete_dgv_item.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					WorkFunctionEntity entity = strList.get(position);
					entity.setIsmain("0");
					entity.setIspermission("1");
					FunctionDatas.otherFunctionDatas.add(entity);
					strList.remove(strList.get(position));
					myHandler.sendEmptyMessage(200);
//					deleteItem=-1;
					notifyDataSetChanged();
				}
			});
	        convertView.setId(position);
			convertView.setBackgroundResource(R.drawable.jwz_selector_dgv_item);
	        return convertView;
	    }
        class ViewHoder{
        	public TextView tv_dgv_item;
        	public ImageView iv_delete_dgv_item,iv_dgv_item;
        }
	    public void hideView(int pos) {
	        hidePosition = pos;
			myHandler.sendEmptyMessage(200);
	        notifyDataSetChanged();
	    }

	    public void showHideView() {
	        hidePosition = AdapterView.INVALID_POSITION;
	        notifyDataSetChanged();
	    }

	    public void removeView(int pos) {
	        strList.remove(pos);
	        notifyDataSetChanged();
	    }

	/**
	 * 当条目位置调换后，对应更换数据源的位置
	 *
	 * @param oldPosition
	 * @param newPosition
	 */
	public void swapView(int oldPosition, int newPosition) {
		if (oldPosition > newPosition) {
			for (int i = oldPosition; i > newPosition; i--) {
				Collections.swap(strList, i, i - 1);
			}
		} else if (oldPosition < newPosition) {
			for (int i = oldPosition; i < newPosition; i++) {
				Collections.swap(strList, i, i + 1);
			}
		}
//		deleteItem=newPosition;
		hidePosition = newPosition;
		notifyDataSetChanged();
	}
	    /**
		 * ��������ʾɾ��ͼ�����Ŀ
		 * 
		 * @param deleteItem
		 */
		public  void setDeleteItem(int deleteItem) {
			this.deleteItem = deleteItem;
			// ���¾Ź���
		    notifyDataSetChanged();
		}

}
