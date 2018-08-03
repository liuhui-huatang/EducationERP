package com.htcompany.educationerpforgansu.dao.daoservice;

import android.content.Context;

import com.htcompany.educationerpforgansu.dao.XUtilsDbDao;
import com.htcompany.educationerpforgansu.workpart.entities.WorkFunctionEntity;

import java.util.List;

/**
 * Created by wrb on 2016/12/28.
 */
public class WorkFunctionService extends XUtilsDbDao<WorkFunctionEntity>{
    private XUtilsDbDao xUtilsDbDao;
    public WorkFunctionService(Context context) {
        super(context);
        xUtilsDbDao = new XUtilsDbDao<WorkFunctionEntity>(context);
    }

    /**
     * 保存实体类
     * @param entity
     */
    public void saveEntity(WorkFunctionEntity entity){
        xUtilsDbDao.save(entity);
    }
    /**
     * 删除所有数据
     */
    public void deleteAllEntity(){
        xUtilsDbDao.deleteAll(WorkFunctionEntity.class);
    }
    /**
     * 删除一个实体
     * @param entity
     */
    public void deleteEntity(WorkFunctionEntity entity){
        xUtilsDbDao.delete(entity);
    }
    /**
     * 查询单个实体类
     * funname 功能名称
     */
    public WorkFunctionEntity searchOneEntity(String funname){
        List<WorkFunctionEntity> datas = xUtilsDbDao.findAllByWhere(WorkFunctionEntity.class, "funname",funname);
        if(datas!=null&&datas.size()>0){
            return datas.get(0);
        }else {
            return null;
        }
    }
    /**
     * 查询首页功能实体集合
     * funname 功能名称
     */
    public  List<WorkFunctionEntity> searchAllMainEntity(String ismain){
        List<WorkFunctionEntity> datas = xUtilsDbDao.findAllByWhere(WorkFunctionEntity.class, "ismain",ismain);
       return datas;
    }
    /**
     * 查询首页功能实体集合
     * funname 功能名称
     */
    public  List<WorkFunctionEntity> searchAllPerssionEntity(String ispermission){
        List<WorkFunctionEntity> datas = xUtilsDbDao.findAllByWhere(WorkFunctionEntity.class, "ispermission",ispermission);
        return datas;
    }
}
