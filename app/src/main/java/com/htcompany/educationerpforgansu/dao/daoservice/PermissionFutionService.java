package com.htcompany.educationerpforgansu.dao.daoservice;

import android.content.Context;

import com.htcompany.educationerpforgansu.dao.XUtilsDbDao;
import com.htcompany.educationerpforgansu.dao.entity.PermissionFutionEntity;

import java.util.List;

/**
 * 权限功能数据库操作类
 * Created by wrb on 2016/12/28.
 */
public class PermissionFutionService extends XUtilsDbDao<PermissionFutionEntity>{
    private XUtilsDbDao xUtilsDbDao;
    public PermissionFutionService(Context context) {
        super(context);
        xUtilsDbDao = new XUtilsDbDao<PermissionFutionEntity>(context);
    }

    /**
     * 保存实体类
     * @param entity
     */
    public void saveEntity(PermissionFutionEntity entity){
        xUtilsDbDao.save(entity);
    }
    /**
     * 删除所有数据
     */
    public void deleteAllEntity(){
        xUtilsDbDao.deleteAll(PermissionFutionEntity.class);
    }
    /**
     * 删除一个实体
     * @param entity
     */
    public void deleteEntity(PermissionFutionEntity entity){
        xUtilsDbDao.delete(entity);
    }
    /**
     * 查询单个实体类
     * funname 功能名称
     */
    public PermissionFutionEntity searchAllSheng(String funname){
        List<PermissionFutionEntity> datas = xUtilsDbDao.findAllByWhere(PermissionFutionEntity.class, "funname",funname);
        if(datas!=null&&datas.size()>0){
            return datas.get(0);
        }else {
            return null;
        }
    }
}
