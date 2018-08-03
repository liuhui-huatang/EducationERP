package com.htcompany.educationerpforgansu.dao;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.io.Serializable;
import java.util.List;

public class XUtilsDbDao<T extends Serializable> {

	// 数据库名称
		String dbName = "educationerpJZG.db";
		// 第三方数据库操作类
		DbUtils dbUtils = null;
		//上下文
		private Context context;
		public XUtilsDbDao(Context context) {
			this.context = context;
			dbUtils = DbUtils.create(context, dbName);
		}
		
		/**
		 * 
		 * 注释: 保存一个实体
		 * 
		 * @author HLJ 2012-12-20 上午10:12:57 void
		 */
		public void save(Object entity) {
			try {
				dbUtils.save(entity);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 
		 * 注释: 更新一个实体
		 * 
		 * @author HLJ 2012-12-20 上午10:12:57 void
		 */
		public void updata(Object entity) {
			try {
				dbUtils.update(entity);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 public void deleteAll(Class<T> clazz){
			 try {
				dbUtils.deleteAll(clazz);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
		/**
		 * 删除一个实体
		 * @param entity
		 */
		public void delete(Object entity){
			try {
				dbUtils.delete(entity);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 根据条件查询所有
		 * @param clazz
		 * @return
		 * @author: HLJ
		 * @Createtime: 2013-2-20
		 */
		public List<T> findAllByWhere(Class<T> clazz, String name,String value) {
			List<T> tlist = null;
			try {
				tlist = dbUtils.findAll(Selector.from(clazz).where(name,"=",value));
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tlist;
		}
		/**
		 * 根据所有
		 * @param clazz
		 * @return
		 * @author: HLJ
		 * @Createtime: 2013-2-20
		 */
		public List<T> findAll(Class<T> clazz) {
			List<T> tlist = null;
			try {
				tlist = dbUtils.findAll(Selector.from(clazz));
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tlist;
		}
}
