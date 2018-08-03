package com.htcompany.educationerpforgansu.commonpart.views.contactview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.educationerpforgansu.R;
import com.htcompany.educationerpforgansu.chatpart.ui.ChatActivity;
import com.htcompany.educationerpforgansu.commonpart.MyApp;
import com.htcompany.educationerpforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class SortAdapter extends BaseAdapter implements SectionIndexer{
	private List<SortModel> list = null;
	private Context mContext;
	
	public SortAdapter(Context mContext, List<SortModel> list) {
		this.mContext = mContext;
		this.list = list;
	}
	/**
	 * ��ListView��ݷ���仯ʱ,���ô˷���������ListView
	 * @param list
	 */
	public void updateListView(List<SortModel> list){
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final SortModel mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
			viewHolder.classtxl_photo=(XCRoundAndOvalImageView)view.findViewById(R.id.classtxl_photo);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.classtxt_yy_tv=(TextView)view.findViewById(R.id.classtxt_yy_tv);
			viewHolder.classtxt_callphone_tv=(TextView)view.findViewById(R.id.classtxt_callphone_tv);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		int section = getSectionForPosition(position);
		if(position == getPositionForSection(section)){
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
//		ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+list.get(position).getPhoto(),
//				viewHolder.classtxl_photo, MyApp.getOptions());
		Glide.with(mContext)
				.load(InterfaceManager.siteURLIP+list.get(position).getPhoto())
				.placeholder(R.mipmap.defult_photo_icon)
				.diskCacheStrategy(DiskCacheStrategy.RESULT)
				.into(viewHolder.classtxl_photo);
		viewHolder.tvTitle.setText(this.list.get(position).getName());
		viewHolder.classtxt_yy_tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent chatIntent = new Intent(mContext,ChatActivity.class);
				chatIntent.putExtra("userId",list.get(position).getHxzh());
				chatIntent.putExtra("username",list.get(position).getName());
				chatIntent.putExtra("userphoto",InterfaceManager.siteURLIP+list.get(position).getPhoto());
				mContext.startActivity(chatIntent);
//				mContext.startActivity(new Intent(mContext,ChatActivity.class).putExtra("userId",list.get(position).getHxzh()));
			}
		});
		if("".equals(list.get(position).getPhonenum())||list.get(position).getPhonenum()==null||"null".equals(list.get(position).getPhonenum())){
			viewHolder.classtxt_callphone_tv.setBackgroundColor(mContext.getResources().getColor(R.color.zcbx_contentcolor));
			viewHolder.classtxt_callphone_tv.setEnabled(false);
			viewHolder.classtxt_callphone_tv.setText("暂无电话");
		}else{
			viewHolder.classtxt_callphone_tv.setBackgroundColor(mContext.getResources().getColor(R.color.jwgl_tv10_color));
			viewHolder.classtxt_callphone_tv.setEnabled(true);
			viewHolder.classtxt_callphone_tv.setText("拨打电话");
		}
		viewHolder.classtxt_callphone_tv.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!"".equals(list.get(position).getPhonenum())&&list.get(position).getPhonenum()!=null){
					//意图：想干什么事
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_CALL);
					//url:统一资源定位符
					//uri:统一资源标示符（更广）
					intent.setData(Uri.parse("tel:" + list.get(position).getPhonenum()));
					//开启系统拨号器
					mContext.startActivity(intent);
				}

			}
		});
		return view;

	}

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
		TextView classtxt_yy_tv,classtxt_callphone_tv;
		XCRoundAndOvalImageView classtxl_photo;
	}
	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}
	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}