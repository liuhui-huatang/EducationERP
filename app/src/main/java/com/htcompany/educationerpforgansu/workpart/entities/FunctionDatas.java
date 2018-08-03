package com.htcompany.educationerpforgansu.workpart.entities;

import com.htcompany.educationerpforgansu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能数据源
 * Created by wrb on 2016/12/1.
 */
public class FunctionDatas {
    public static ArrayList<WorkFunctionEntity> mainFunctionDatas = new ArrayList<WorkFunctionEntity>();
    public static int isAddQb = -1;

    public static void addInitData() {
        WorkFunctionEntity rc = new WorkFunctionEntity();
        rc.setFunname("日程");
        rc.setFunflag(FunctionDatas.WORK_GRBG_RC);
        rc.setFunimg(R.mipmap.fuction_rc_icon);
        rc.setIsadd("0");
        rc.setIsdelete("0");
        rc.setIsmain("1");
        rc.setIspermission("0");
        WorkFunctionEntity zcwh = new WorkFunctionEntity();
        zcwh.setFunname("资产维护");
        zcwh.setFunflag(FunctionDatas.WORK_GRBG_ZCWH);
        zcwh.setFunimg(R.mipmap.fuction_zcwx_icon);
        zcwh.setIsadd("0");
        zcwh.setIsdelete("0");
        zcwh.setIsmain("1");
        zcwh.setIspermission("0");
        WorkFunctionEntity gzl = new WorkFunctionEntity();
        gzl.setFunname("工作流");
        gzl.setFunflag(FunctionDatas.WORK_GRBG_GZL);
        gzl.setFunimg(R.mipmap.fuction_gzl_icon);
        gzl.setIsadd("0");
        gzl.setIsdelete("0");
        gzl.setIsmain("1");
        gzl.setIspermission("0");
        WorkFunctionEntity hy = new WorkFunctionEntity();
        hy.setFunname("会议");
        hy.setFunflag(FunctionDatas.WORK_GRBG_HY);
        hy.setFunimg(R.mipmap.fuction_wdhy_icon);
        hy.setIsadd("0");
        hy.setIsdelete("0");
        hy.setIsmain("1");
        hy.setIspermission("0");
//        WorkFunctionEntity qjsq = new WorkFunctionEntity();
//        qjsq.setFunname("请假申请");
//        qjsq.setFunflag(FunctionDatas.WORK_GRBG_QJSQ);
//        qjsq.setFunimg(R.mipmap.fuction_wdqjsq_icon);
//        qjsq.setIsadd("0");
//        qjsq.setIsdelete("0");
//        qjsq.setIsmain("1");
//        qjsq.setIspermission("0");
        //        mainFunctionDatas.add(qjsq);
        WorkFunctionEntity clsy = new WorkFunctionEntity();
        clsy.setFunname("车辆使用");
        clsy.setFunflag(FunctionDatas.WORK_GRBG_CARUSE);
        clsy.setFunimg(R.mipmap.fuction_wdclsq_icon);
        clsy.setIsadd("0");
        clsy.setIsdelete("0");
        clsy.setIsmain("1");
        clsy.setIspermission("0");
        WorkFunctionEntity zb = new WorkFunctionEntity();
        zb.setFunname("部门值班");
        zb.setFunflag(FunctionDatas.WORK_GRBG_ZB);
        zb.setFunimg(R.mipmap.tb_icon_zhiban);
        zb.setIsadd("0");
        zb.setIsdelete("0");
        zb.setIsmain("1");
        zb.setIspermission("0");
//        WorkFunctionEntity jspj = new WorkFunctionEntity();
//        jspj.setFunname("教师评教");
//        jspj.setFunflag(FunctionDatas.WORK_GRBG_JSPJ);
//        jspj.setFunimg(R.mipmap.tb_icon_jiaoshipingjiao);
//        jspj.setIsadd("0");
//        jspj.setIsdelete("0");
//        jspj.setIsmain("1");
//        jspj.setIspermission("0");
//        WorkFunctionEntity tp = new WorkFunctionEntity();
//        tp.setFunname("投票");
//        tp.setFunflag(FunctionDatas.WORK_GRBG_TP);
//        tp.setFunimg(R.mipmap.tb_icon_toupiao);
//        tp.setIsadd("0");
//        tp.setIsdelete("0");
//        tp.setIsmain("1");
//        tp.setIspermission("0");
        mainFunctionDatas.add(rc);
        mainFunctionDatas.add(zcwh);
        mainFunctionDatas.add(gzl);
        mainFunctionDatas.add(hy);

        mainFunctionDatas.add(clsy);
        mainFunctionDatas.add(zb);
//        mainFunctionDatas.add(jspj);
//        mainFunctionDatas.add(tp);
    }

    public static ArrayList<WorkFunctionEntity> otherFunctionDatas = new ArrayList<WorkFunctionEntity>();
    //=====================================个人办公数据源===========================================
    public final static String WORK_GRBG_RC = "WORK_GRBG_RC";//日程
    public final static String WORK_GRBG_ZCWH = "WORK_GRBG_ZCWH";//资产维护
    public final static String WORK_GRBG_GZL = "WORK_GRBG_GZL";//工作流
    public final static String WORK_GRBG_WJCY = "WORK_GRBG_WJCY";//文件查阅
    public final static String WORK_GRBG_HY = "WORK_GRBG_HY";//会议
    public final static String WORK_GRBG_QJSQ = "WORK_GRBG_QJSQ";//请假申请
    public final static String WORK_GRBG_CARUSE = "WORK_GRBG_CARUSE";//车辆使用
    public final static String WORK_GRBG_JHZJ = "WORK_GRBG_JHZJ";//计划总结
    public final static String WORK_GRBG_TP = "WORK_GRBG_TP";//投票
    public final static String WORK_GRBG_ZB = "WORK_GRBG_ZB";//值班
    public final static String WORK_GRBG_JSPJ = "WORK_GRBG_JSPJ";//教师评教


    //=====================================普通教师数据源===========================================
    public final static String WORK_PTJS_JXJH = "WORK_PTJS_JXJH";//教学计划
    public final static String WORK_PTJS_JXSJ = "WORK_PTJS_JXSJ";//教学设计
    public final static String WORK_PTJS_JSPX = "WORK_PTJS_JSPX";//教师培训
    public final static String WORK_PTJS_SKJL = "WORK_PTJS_SKJL";//授课记录
    public final static String WORK_PTJS_JSKB = "WORK_PTJS_JSKB";//教学课表
    public final static String WORK_PTJS_XXKBJHMC = "WORK_PTJS_XXKBJHMC";//选修课班级化名册
    public final static String WORK_PTJS_JXHD = "WORK_PTJS_JXHD";//教学互动

    /**
     * 普通教师初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getPTJS_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
//        WorkFunctionEntity jxjh = new WorkFunctionEntity();
//        jxjh.setFunname("教学计划");
//        jxjh.setFunflag(FunctionDatas.WORK_PTJS_JXJH);
//        jxjh.setFunimg(R.mipmap.fuction_jxjh_icon);
//        jxjh.setIsadd("0");
//        jxjh.setIsdelete("0");
        WorkFunctionEntity jxsj = new WorkFunctionEntity();
        jxsj.setFunname("代课调课");
        jxsj.setFunflag(FunctionDatas.WORK_PTJS_JXSJ);
        jxsj.setFunimg(R.mipmap.fuction_ptjs_dktk_icon);
        jxsj.setIsadd("0");
        jxsj.setIsdelete("0");
        jxsj.setIsmain("0");
        jxsj.setIspermission("1");
        WorkFunctionEntity jspx = new WorkFunctionEntity();
        jspx.setFunname("教师培训");
        jspx.setFunflag(FunctionDatas.WORK_PTJS_JSPX);
        jspx.setFunimg(R.mipmap.fuction_jspx_icon);
        jspx.setIsadd("0");
        jspx.setIsdelete("0");
        jspx.setIsmain("0");
        jspx.setIspermission("1");
        WorkFunctionEntity skjl = new WorkFunctionEntity();
        skjl.setFunname("授课记录");
        skjl.setFunflag(FunctionDatas.WORK_PTJS_SKJL);
        skjl.setFunimg(R.mipmap.fuction_skjl_icon);
        skjl.setIsadd("0");
        skjl.setIsdelete("0");
        skjl.setIsmain("0");
        skjl.setIspermission("1");
        WorkFunctionEntity jxkb = new WorkFunctionEntity();
        jxkb.setFunname("教学课表");
        jxkb.setFunflag(FunctionDatas.WORK_PTJS_JSKB);
        jxkb.setFunimg(R.mipmap.fuction_jskb_icon);
        jxkb.setIsadd("0");
        jxkb.setIsdelete("0");
        jxkb.setIsmain("0");
        jxkb.setIspermission("1");
//        WorkFunctionEntity xxkbjhmc = new WorkFunctionEntity();
//        xxkbjhmc.setFunname("选修课名单");
//        xxkbjhmc.setFunflag(FunctionDatas.WORK_PTJS_XXKBJHMC);
//        xxkbjhmc.setFunimg(R.mipmap.fuction_xxkhmc_icon);
//        xxkbjhmc.setIsadd("0");
//        xxkbjhmc.setIsdelete("0");
//        xxkbjhmc.setIsmain("0");
//        xxkbjhmc.setIspermission("1");
        WorkFunctionEntity jxhd = new WorkFunctionEntity();
        jxhd.setFunname("教学互动");
        jxhd.setFunflag(FunctionDatas.WORK_PTJS_JXHD);
        jxhd.setFunimg(R.mipmap.fuction_jxhd_icon);
        jxhd.setIsadd("0");
        jxhd.setIsdelete("0");
        jxhd.setIsmain("0");
        jxhd.setIspermission("1");
//        datas.add(jxjh);
        datas.add(jxsj);
        datas.add(jspx);
        datas.add(skjl);
        datas.add(jxkb);
//        datas.add(xxkbjhmc);
        datas.add(jxhd);
        return datas;
    }

    //=====================================班主任数据源===========================================
    public final static String WORK_BZR_BJHMC = "WORK_BZR_BJHMC";//班级花名册
    public final static String WORK_BZR_BJKM = "WORK_BZR_BJKM";//班级课表
    public final static String WORK_BZR_BJCJ = "WORK_BZR_BJCJ";//班级成绩
    public final static String WORK_BZR_BJKH = "WORK_BZR_BJKH";//班级考核
    public final static String WORK_BZR_BJGG = "WORK_BZR_BJGG";//班级公告
    public final static String WORK_BZR_XSQJ = "WORK_BZR_XSQJ";//学生请假
    public final static String WORK_BZR_RCSWJCDJ = "WORK_BZR_RCSWJCDJ";//日常事务检查登记
    public final static String WORK_BZR_XQPY = "WORK_BZR_XQPY";//学期评语
    public final static String WORK_BZR_XSPJ = "WORK_BZR_XSPJ";//学生评教
    public final static String WORK_BZR_XSWJ = "WORK_BZR_XSWJ";//学生违纪
    public final static String WORK_BZR_SDGL = "WORK_BZR_SDGL";//宿舍管理
    public final static String WORK_BZR_BKCJ = "WORK_BZR_BKCJ";//补考成绩

    /**
     * 班主任初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getBZR_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        WorkFunctionEntity bjhmc = new WorkFunctionEntity();
        bjhmc.setFunname("班级名单");
        bjhmc.setFunflag(FunctionDatas.WORK_BZR_BJHMC);
        bjhmc.setFunimg(R.mipmap.fuction_bjhmc_icon);
        bjhmc.setIsadd("0");
        bjhmc.setIsdelete("0");
        bjhmc.setIsmain("0");
        bjhmc.setIspermission("1");
        WorkFunctionEntity bjkb = new WorkFunctionEntity();
        bjkb.setFunname("班级课表");
        bjkb.setFunflag(FunctionDatas.WORK_BZR_BJKM);
        bjkb.setFunimg(R.mipmap.fuction_bjkb_icon);
        bjkb.setIsadd("0");
        bjkb.setIsdelete("0");
        bjkb.setIsmain("0");
        bjkb.setIspermission("1");
        WorkFunctionEntity bjcj = new WorkFunctionEntity();
        bjcj.setFunname("班级成绩");
        bjcj.setFunflag(FunctionDatas.WORK_BZR_BJCJ);
        bjcj.setFunimg(R.mipmap.fuction_bjcj_icon);
        bjcj.setIsadd("0");
        bjcj.setIsdelete("0");
        bjcj.setIsmain("0");
        bjcj.setIspermission("1");
        WorkFunctionEntity bjkh = new WorkFunctionEntity();
        bjkh.setFunname("班级考核");
        bjkh.setFunflag(FunctionDatas.WORK_BZR_BJKH);
        bjkh.setFunimg(R.mipmap.fuction_bjkh_icon);
        bjkh.setIsadd("0");
        bjkh.setIsdelete("0");
        bjkh.setIsmain("0");
        bjkh.setIspermission("1");
        WorkFunctionEntity bjgg = new WorkFunctionEntity();
        bjgg.setFunname("班级公告");
        bjgg.setFunflag(FunctionDatas.WORK_BZR_BJGG);
        bjgg.setFunimg(R.mipmap.fuction_bjgg_icon);
        bjgg.setIsadd("0");
        bjgg.setIsdelete("0");
        bjgg.setIsmain("0");
        bjgg.setIspermission("1");
        WorkFunctionEntity xsqj = new WorkFunctionEntity();
        xsqj.setFunname("学生请假");
        xsqj.setFunflag(FunctionDatas.WORK_BZR_XSQJ);
        xsqj.setFunimg(R.mipmap.fuction_xsqj_icon);
        xsqj.setIsadd("0");
        xsqj.setIsdelete("0");
        xsqj.setIsmain("0");
        xsqj.setIspermission("1");
        WorkFunctionEntity rcswjcdj = new WorkFunctionEntity();
        rcswjcdj.setFunname("日常事务");
        rcswjcdj.setFunflag(FunctionDatas.WORK_BZR_RCSWJCDJ);
        rcswjcdj.setFunimg(R.mipmap.fuction_rcswjc_icon);
        rcswjcdj.setIsadd("0");
        rcswjcdj.setIsdelete("0");
        rcswjcdj.setIsmain("0");
        rcswjcdj.setIspermission("1");
//        WorkFunctionEntity xqpy = new WorkFunctionEntity();
//        xqpy.setFunname("学期评语");
//        xqpy.setFunflag(FunctionDatas.WORK_BZR_XQPY);
//        xqpy.setFunimg(R.mipmap.fuction_xqpy_icon);
//        xqpy.setIsadd("0");
//        xqpy.setIsdelete("0");
//        xqpy.setIsmain("0");
//        xqpy.setIspermission("1");
//        WorkFunctionEntity xspj = new WorkFunctionEntity();
//        xspj.setFunname("学生评教");
//        xspj.setFunflag(FunctionDatas.WORK_BZR_XSPJ);
//        xspj.setFunimg(R.mipmap.fuction_xspj_icon);
//        xspj.setIsadd("0");
//        xspj.setIsdelete("0");
//        xspj.setIsmain("0");
//        xspj.setIspermission("1");
//        WorkFunctionEntity xswj = new WorkFunctionEntity();
//        xswj.setFunname("学生违纪");
//        xswj.setFunflag(FunctionDatas.WORK_BZR_XSWJ);
//        xswj.setFunimg(R.mipmap.fuction_xswj_icon);
//        xswj.setIsadd("0");
//        xswj.setIsdelete("0");
//        xswj.setIsmain("0");
//        xswj.setIspermission("1");
        WorkFunctionEntity ssgl = new WorkFunctionEntity();
        ssgl.setFunname("宿舍管理");
        ssgl.setFunflag(FunctionDatas.WORK_BZR_SDGL);
        ssgl.setFunimg(R.mipmap.fuction_ssgl_icon);
        ssgl.setIsadd("0");
        ssgl.setIsdelete("0");
        ssgl.setIsmain("0");
        ssgl.setIspermission("1");
//        WorkFunctionEntity bkcj = new WorkFunctionEntity();
//        bkcj.setFunname("补考成绩");
//        bkcj.setFunflag(FunctionDatas.WORK_BZR_BKCJ);
//        bkcj.setFunimg(R.mipmap.fuction_bkcj_icon);
//        bkcj.setIsadd("0");
//        bkcj.setIsdelete("0");
//        bkcj.setIsmain("0");
//        bkcj.setIspermission("1");
        datas.add(bjhmc);
        datas.add(bjkb);
        datas.add(bjcj);
        datas.add(bjkh);
        datas.add(bjgg);
        datas.add(xsqj);
        datas.add(rcswjcdj);
//        datas.add(xqpy);
//        datas.add(xspj);
//        datas.add／(xswj);
        datas.add(ssgl);
        return datas;
    }

    //=====================================教务数据源===========================================
    public final static String WORK_JW_XXKXSCX = "WORK_JW_XXKXSCX";//选修课学生查询
    public final static String WORK_JW_BJCJHZ = "WORK_JW_BJCJHZ";//班级成绩汇总
    public final static String WORK_JW_QKCJCX = "WORK_JW_QKCJCX";//清考成绩查询
    public final static String WORK_JW_BKCJCX = "WORK_JW_BKCJCX";//补考成绩查询
    public final static String WORK_JW_JSPXJHGL = "WORK_JW_JSPXJHGL";//教师培训计划管理
    public final static String WORK_JW_XSPJGL = "WORK_JW_XSPJGL";//学生评教管理
    public final static String WORK_JW_JSPJGL = "WORK_JW_JSPJGL";//教师评教管理
    public final static String WORK_JW_JSHDTJ = "WORK_JW_JSHDTJ";//教学互动统计

    /**
     * 教务初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getJW_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        WorkFunctionEntity xxkxscx = new WorkFunctionEntity();
        xxkxscx.setFunname("选修课管理");
        xxkxscx.setFunflag(FunctionDatas.WORK_JW_XXKXSCX);
        xxkxscx.setFunimg(R.mipmap.fuction_jwgl_xxkxscx_icon);
        xxkxscx.setIsadd("0");
        xxkxscx.setIsdelete("0");
        xxkxscx.setIsmain("0");
        xxkxscx.setIspermission("1");
        WorkFunctionEntity bjcjhz = new WorkFunctionEntity();
        bjcjhz.setFunname("班级成绩汇总");
        bjcjhz.setFunflag(FunctionDatas.WORK_JW_BJCJHZ);
        bjcjhz.setFunimg(R.mipmap.fuction_jwgl_bjcjhz_icon);
        bjcjhz.setIsadd("0");
        bjcjhz.setIsdelete("0");
        bjcjhz.setIsmain("0");
        bjcjhz.setIspermission("1");
        WorkFunctionEntity qkcjcx = new WorkFunctionEntity();
        qkcjcx.setFunname("清考成绩查询");
        qkcjcx.setFunflag(FunctionDatas.WORK_JW_QKCJCX);
        qkcjcx.setFunimg(R.mipmap.fuction_jwgl_qkcjcx_icon);
        qkcjcx.setIsadd("0");
        qkcjcx.setIsdelete("0");
        qkcjcx.setIsmain("0");
        qkcjcx.setIspermission("1");
        WorkFunctionEntity bkcjcx = new WorkFunctionEntity();
        bkcjcx.setFunname("补考成绩查询");
        bkcjcx.setFunflag(FunctionDatas.WORK_JW_BKCJCX);
        bkcjcx.setFunimg(R.mipmap.fuction_jwgl_bkcjcx_icon);
        bkcjcx.setIsadd("0");
        bkcjcx.setIsdelete("0");
        bkcjcx.setIsmain("0");
        bkcjcx.setIspermission("1");
        WorkFunctionEntity jspxjhgl = new WorkFunctionEntity();
        jspxjhgl.setFunname("培训计划管理");
        jspxjhgl.setFunflag(FunctionDatas.WORK_JW_JSPXJHGL);
        jspxjhgl.setFunimg(R.mipmap.fuction_jwgl_jspxjhgl_icon);
        jspxjhgl.setIsadd("0");
        jspxjhgl.setIsdelete("0");
        jspxjhgl.setIsmain("0");
        jspxjhgl.setIspermission("1");
//        WorkFunctionEntity xspjgl = new WorkFunctionEntity();
//        xspjgl.setFunname("学生评教管理");
//        xspjgl.setFunflag(FunctionDatas.WORK_JW_XSPJGL);
//        xspjgl.setFunimg(R.mipmap.fuction_jwgl_xspjgl_icon);
//        xspjgl.setIsadd("0");
//        xspjgl.setIsdelete("0");
//        xspjgl.setIsmain("0");
//        xspjgl.setIspermission("1");
//        WorkFunctionEntity jspjgl = new WorkFunctionEntity();
//        jspjgl.setFunname("教师评教管理");
//        jspjgl.setFunflag(FunctionDatas.WORK_JW_JSPJGL);
//        jspjgl.setFunimg(R.mipmap.fuction_jwgl_jspjgl_icon);
//        jspjgl.setIsadd("0");
//        jspjgl.setIsdelete("0");
//        jspjgl.setIsmain("0");
//        jspjgl.setIspermission("1");
        WorkFunctionEntity jshdtj = new WorkFunctionEntity();
        jshdtj.setFunname("教师互动统计");
        jshdtj.setFunflag(FunctionDatas.WORK_JW_JSHDTJ);
        jshdtj.setFunimg(R.mipmap.fuction_jwgl_jxhdtj_icon);
        jshdtj.setIsadd("0");
        jshdtj.setIsdelete("0");
        jshdtj.setIsmain("0");
        jshdtj.setIspermission("1");
        datas.add(xxkxscx);
        datas.add(bjcjhz);
        datas.add(qkcjcx);
        datas.add(bkcjcx);
        datas.add(jspxjhgl);
//        datas.add(xspjgl);
//        datas.add(jspjgl);
        datas.add(jshdtj);
        return datas;
    }

    //=====================================考务数据源===========================================
    public final static String WORK_KW_XSKCXXCX = "WORK_KW_XSKCXXCX";//学生考场信息查询
    public final static String WORK_KW_JKXXCX = "WORK_KW_JKXXCX";//监考信息查询

    /**
     * 考务初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getKW_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        WorkFunctionEntity xskcxxcx = new WorkFunctionEntity();
        xskcxxcx.setFunname("学生考场查询");
        xskcxxcx.setFunflag(FunctionDatas.WORK_KW_XSKCXXCX);
        xskcxxcx.setFunimg(R.mipmap.fuction_kwgl_xskcxxcx_icon);
        xskcxxcx.setIsadd("0");
        xskcxxcx.setIsdelete("0");
        xskcxxcx.setIsmain("0");
        xskcxxcx.setIspermission("1");
        WorkFunctionEntity jkxxcx = new WorkFunctionEntity();
        jkxxcx.setFunname("监考查询");
        jkxxcx.setFunflag(FunctionDatas.WORK_KW_JKXXCX);
        jkxxcx.setFunimg(R.mipmap.fuction_kwgl_jjkxxcx_icon);
        jkxxcx.setIsadd("0");
        jkxxcx.setIsdelete("0");
        jkxxcx.setIsmain("0");
        jkxxcx.setIspermission("1");
        datas.add(xskcxxcx);
        datas.add(jkxxcx);
        return datas;
    }

    //=====================================学生功能数据源===========================================
    public final static String WORK_XSGL_XSCX = "WORK_XSGL_XSCX";//学生查询
    public final static String WORK_XSGL_XSGG = "WORK_XSGL_XSGG";//学生公告
    public final static String WORK_XSGL_JNDS = "WORK_XSGL_JNDS";//技能大赛
    public final static String WORK_XSGL_DYXW = "WORK_XSGL_DYXW";//德育新闻
    public final static String WORK_XSGL_JNJD = "WORK_XSGL_JNJD";//技能鉴定
    public final static String WORK_XSGL_XSJL = "WORK_XSGL_XSJL";//学生奖励
    public final static String WORK_XSGL_XSCC = "WORK_XSGL_XSCC";//学生惩处

    /**
     * 学生初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getXSGL_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        WorkFunctionEntity xscx = new WorkFunctionEntity();
        xscx.setFunname("学生查询");
        xscx.setFunflag(FunctionDatas.WORK_XSGL_XSCX);
        xscx.setFunimg(R.mipmap.fuction_xsgl_xscx_icon);
        xscx.setIsadd("0");
        xscx.setIsdelete("0");
        xscx.setIsmain("0");
        xscx.setIspermission("1");
//        WorkFunctionEntity xsgg = new WorkFunctionEntity();
//        xsgg.setFunname("学生公告");
//        xsgg.setFunflag(FunctionDatas.WORK_XSGL_XSGG);
//        xsgg.setFunimg(R.mipmap.fuction_xsgl_xsgg_icon);
//        xsgg.setIsadd("0");
//        xsgg.setIsdelete("0");
//        xsgg.setIsmain("0");
//        xsgg.setIspermission("1");
//        WorkFunctionEntity jnds = new WorkFunctionEntity();
//        jnds.setFunname("技能大赛");
//        jnds.setFunflag(FunctionDatas.WORK_XSGL_JNDS);
//        jnds.setFunimg(R.mipmap.fuction_xsgl_jnds_icon);
//        jnds.setIsadd("0");
//        jnds.setIsdelete("0");
//        WorkFunctionEntity dyxw = new WorkFunctionEntity();
//        dyxw.setFunname("德育新闻");
//        dyxw.setFunflag(FunctionDatas.WORK_XSGL_DYXW);
//        dyxw.setFunimg(R.mipmap.fuction_xsgl_dyxw_icon);
//        dyxw.setIsadd("0");
//        dyxw.setIsdelete("0");
//        WorkFunctionEntity jnjd = new WorkFunctionEntity();
//        jnjd.setFunname("技能鉴定");
//        jnjd.setFunflag(FunctionDatas.WORK_XSGL_JNJD);
//        jnjd.setFunimg(R.mipmap.fuction_xsgl_jnjd_icon);
//        jnjd.setIsadd("0");
//        jnjd.setIsdelete("0");
//        jnjd.setIsmain("0");
//        jnjd.setIspermission("1");
//        WorkFunctionEntity xsjl = new WorkFunctionEntity();
//        xsjl.setFunname("学生奖励");
//        xsjl.setFunflag(FunctionDatas.WORK_XSGL_XSJL);
//        xsjl.setFunimg(R.mipmap.fuction_xsgl_xsjl_icon);
//        xsjl.setIsadd("0");
//        xsjl.setIsdelete("0");
//        xsjl.setIsmain("0");
//        xsjl.setIspermission("1");
//        WorkFunctionEntity xscc = new WorkFunctionEntity();
//        xscc.setFunname("学生惩处");
//        xscc.setFunflag(FunctionDatas.WORK_XSGL_XSCC);
//        xscc.setFunimg(R.mipmap.fuction_xsgl_xscc_icon);
//        xscc.setIsadd("0");
//        xscc.setIsdelete("0");
//        xscc.setIsmain("0");
//        xscc.setIspermission("1");
        datas.add(xscx);
//        datas.add(xsgg);
//        datas.add(jnds);
//        datas.add(dyxw);
//        datas.add(jnjd);
//        datas.add(xsjl);
//        datas.add(xscc);
        return datas;
    }

    //=====================================宿舍功能数据源===========================================
    public final static String WORK_SSGL_RZHTSCX = "WORK_SSGL_RZHTSCX";//入住和退宿查询
    public final static String WORK_SSGL_SSDHCX = "WORK_SSGL_SSDHCX";//宿舍调换查询
    public final static String WORK_SSGL_CQGL = "WORK_SSGL_CQGL";//查寝管理
    public final static String WORK_SSGL_WSGL = "WORK_SSGL_WSGL";//卫生管理
    public final static String WORK_SSGL_WJGL = "WORK_SSGL_WJGL";//违纪管理
    public final static String WORK_SSGL_WSDFHZ = "WORK_SSGL_WSDFHZ";//卫生得分汇总
    public final static String WORK_SSGL_PYCX = "WORK_SSGL_PYCX";//评优查询

    /**
     * 宿舍初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getSSGL_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        WorkFunctionEntity rzhtscx = new WorkFunctionEntity();
        rzhtscx.setFunname("入住退宿查询");
        rzhtscx.setFunflag(FunctionDatas.WORK_SSGL_RZHTSCX);
        rzhtscx.setFunimg(R.mipmap.fuction_ssgl_zstscx_icon);
        rzhtscx.setIsadd("0");
        rzhtscx.setIsdelete("0");
        rzhtscx.setIsmain("0");
        rzhtscx.setIspermission("1");
        WorkFunctionEntity ssdhcx = new WorkFunctionEntity();
        ssdhcx.setFunname("宿舍调换查询");
        ssdhcx.setFunflag(FunctionDatas.WORK_SSGL_SSDHCX);
        ssdhcx.setFunimg(R.mipmap.fuction_ssgl_ssdhcx_icon);
        ssdhcx.setIsadd("0");
        ssdhcx.setIsdelete("0");
        ssdhcx.setIsmain("0");
        ssdhcx.setIspermission("1");
        WorkFunctionEntity cqgl = new WorkFunctionEntity();
        cqgl.setFunname("查寝管理");
        cqgl.setFunflag(FunctionDatas.WORK_SSGL_CQGL);
        cqgl.setFunimg(R.mipmap.fuction_ssgl_cqgl_icon);
        cqgl.setIsadd("0");
        cqgl.setIsdelete("0");
        cqgl.setIsmain("0");
        cqgl.setIspermission("1");
        WorkFunctionEntity wsgl = new WorkFunctionEntity();
        wsgl.setFunname("卫生检查");
        wsgl.setFunflag(FunctionDatas.WORK_SSGL_WSGL);
        wsgl.setFunimg(R.mipmap.fuction_ssgl_wsgl_icon);
        wsgl.setIsadd("0");
        wsgl.setIsdelete("0");
        wsgl.setIsmain("0");
        wsgl.setIspermission("1");
        WorkFunctionEntity wjgl = new WorkFunctionEntity();
        wjgl.setFunname("违纪管理");
        wjgl.setFunflag(FunctionDatas.WORK_SSGL_WJGL);
        wjgl.setFunimg(R.mipmap.fuction_ssgl_wjgl_icon);
        wjgl.setIsadd("0");
        wjgl.setIsdelete("0");
        wjgl.setIsmain("0");
        wjgl.setIspermission("1");
        WorkFunctionEntity wsdfhz = new WorkFunctionEntity();
        wsdfhz.setFunname("卫生得分汇总");
        wsdfhz.setFunflag(FunctionDatas.WORK_SSGL_WSDFHZ);
        wsdfhz.setFunimg(R.mipmap.fuction_ssgl_wsdfhz_icon);
        wsdfhz.setIsadd("0");
        wsdfhz.setIsdelete("0");
        wsdfhz.setIsmain("0");
        wsdfhz.setIspermission("1");
        WorkFunctionEntity pycx = new WorkFunctionEntity();
        pycx.setFunname("评优查询");
        pycx.setFunflag(FunctionDatas.WORK_SSGL_PYCX);
        pycx.setFunimg(R.mipmap.fuction_ssgl_pycx_icon);
        pycx.setIsadd("0");
        pycx.setIsdelete("0");
        pycx.setIsmain("0");
        pycx.setIspermission("1");
        datas.add(rzhtscx);
        datas.add(ssdhcx);
        datas.add(cqgl);
        datas.add(wsgl);
        datas.add(wjgl);
        datas.add(wsdfhz);
        datas.add(pycx);
        return datas;
    }

    //=====================================办公功能数据源===========================================
    public final static String WORK_BGGL_BGGG = "WORK_BGGL_BGGG";//办公公告
    public final static String WORK_BGGL_HYGL = "WORK_BGGL_HYGL";//会议管理
    public final static String WORK_BGGL_CLGL = "WORK_BGGL_CLGL";//车辆管理
    public final static String WORK_BGGL_TPGL = "WORK_BGGL_TPGL";//投票管理

    /**
     * 办公初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getBGGL_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        WorkFunctionEntity bggg = new WorkFunctionEntity();
        bggg.setFunname("办公公告");
        bggg.setFunflag(FunctionDatas.WORK_BGGL_BGGG);
        bggg.setFunimg(R.mipmap.fuction_bggl_bggg_icon);
        bggg.setIsadd("0");
        bggg.setIsdelete("0");
        bggg.setIsmain("0");
        bggg.setIspermission("1");
        WorkFunctionEntity hygl = new WorkFunctionEntity();
        hygl.setFunname("会议管理");
        hygl.setFunflag(FunctionDatas.WORK_BGGL_HYGL);
        hygl.setFunimg(R.mipmap.fuction_bggl_hygl_icon);
        hygl.setIsadd("0");
        hygl.setIsdelete("0");
        hygl.setIsmain("0");
        hygl.setIspermission("1");
        WorkFunctionEntity clgl = new WorkFunctionEntity();
        clgl.setFunname("车辆管理");
        clgl.setFunflag(FunctionDatas.WORK_BGGL_CLGL);
        clgl.setFunimg(R.mipmap.fuction_bggl_clgl_icon);
        clgl.setIsadd("0");
        clgl.setIsdelete("0");
        clgl.setIsmain("0");
        clgl.setIspermission("1");
//        WorkFunctionEntity tpgl = new WorkFunctionEntity();
//        tpgl.setFunname("投票管理");
//        tpgl.setFunflag(FunctionDatas.WORK_BGGL_TPGL);
//        tpgl.setFunimg(R.mipmap.fuction_bggl_tpgl_icon);
//        tpgl.setIsadd("0");
//        tpgl.setIsdelete("0");
//        tpgl.setIsmain("0");
//        tpgl.setIspermission("1");
        datas.add(bggg);
        datas.add(hygl);
        datas.add(clgl);
//        datas.add(tpgl);
        return datas;
    }

    //=====================================人事功能数据源===========================================
    public final static String WORK_RSGL_JZGGL = "WORK_RSGL_JZGGL";//教职工管理
    public final static String WORK_RSGL_LDHTGL = "WORK_RSGL_LDHTGL";//劳动合同管理
    public final static String WORK_RSGL_SHBXJL = "WORK_RSGL_SHBXJL";//社会保险记录
    public final static String WORK_RSGL_RSDA = "WORK_RSGL_RSDA";//人事档案
    public final static String WORK_RSGL_JZGJC = "WORK_RSGL_JZGJC";//教职工奖惩
    public final static String WORK_RSGL_JZGQJ = "WORK_RSGL_JZGQJ";//教职工请假

    /**
     * 人事初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getRSGL_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        WorkFunctionEntity jzggl = new WorkFunctionEntity();
        jzggl.setFunname("教职工管理");
        jzggl.setFunflag(FunctionDatas.WORK_RSGL_JZGGL);
        jzggl.setFunimg(R.mipmap.fuction_rsgl_jzggl_icon);
        jzggl.setIsadd("0");
        jzggl.setIsdelete("0");
        jzggl.setIsmain("0");
        jzggl.setIspermission("1");
        WorkFunctionEntity ldhtgl = new WorkFunctionEntity();
        ldhtgl.setFunname("劳动合同管理");
        ldhtgl.setFunflag(FunctionDatas.WORK_RSGL_LDHTGL);
        ldhtgl.setFunimg(R.mipmap.fuction_rsgl_ldhtgl_icon);
        ldhtgl.setIsadd("0");
        ldhtgl.setIsdelete("0");
        ldhtgl.setIsmain("0");
        ldhtgl.setIspermission("1");
        WorkFunctionEntity shbxjl = new WorkFunctionEntity();
        shbxjl.setFunname("社会保险记录");
        shbxjl.setFunflag(FunctionDatas.WORK_RSGL_SHBXJL);
        shbxjl.setFunimg(R.mipmap.fuction_rsgl_shbxjl_icon);
        shbxjl.setIsadd("0");
        shbxjl.setIsdelete("0");
        shbxjl.setIsmain("0");
        shbxjl.setIspermission("1");
        WorkFunctionEntity resda = new WorkFunctionEntity();
        resda.setFunname("人事档案");
        resda.setFunflag(FunctionDatas.WORK_RSGL_RSDA);
        resda.setFunimg(R.mipmap.fuction_rsgl_rsdagl_icon);
        resda.setIsadd("0");
        resda.setIsdelete("0");
        resda.setIsmain("0");
        resda.setIspermission("1");
//        WorkFunctionEntity jzgjc = new WorkFunctionEntity();
//        jzgjc.setFunname("教职工奖惩");
//        jzgjc.setFunflag(FunctionDatas.WORK_RSGL_JZGJC);
//        jzgjc.setFunimg(R.mipmap.fuction_rsgl_jzgjc_icon);
//        jzgjc.setIsadd("0");
//        jzgjc.setIsdelete("0");
//        jzgjc.setIsmain("0");
//        jzgjc.setIspermission("1");
//        WorkFunctionEntity jzgqj = new WorkFunctionEntity();
//        jzgqj.setFunname("教职工请假");
//        jzgqj.setFunflag(FunctionDatas.WORK_RSGL_JZGQJ);
//        jzgqj.setFunimg(R.mipmap.fuction_rsgl_jzgqjgl_icon);
//        jzgqj.setIsadd("0");
//        jzgqj.setIsdelete("0");
//        jzgqj.setIsmain("0");
//        jzgqj.setIspermission("1");
        datas.add(jzggl);
        datas.add(ldhtgl);
        datas.add(shbxjl);
        datas.add(resda);
//        datas.add(jzgjc);
//        datas.add(jzgqj);
        return datas;
    }

    //=====================================图书功能数据源===========================================
    public final static String WORK_TSGL_TSGL = "WORK_TSGL_TSGL";//图书管理
    public final static String WORK_TSGL_JYGL = "WORK_TSGL_JYGL";//图书借阅管理

    /**
     * 图书初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getTSGL_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        WorkFunctionEntity tsgl = new WorkFunctionEntity();
        tsgl.setFunname("图书管理");
        tsgl.setFunflag(FunctionDatas.WORK_TSGL_TSGL);
        tsgl.setFunimg(R.mipmap.fuction_bookgl_bggg_icon);
        tsgl.setIsadd("0");
        tsgl.setIsdelete("0");
        tsgl.setIsmain("0");
        tsgl.setIspermission("1");
        WorkFunctionEntity tsjygl = new WorkFunctionEntity();
        tsjygl.setFunname("借阅管理");
        tsjygl.setFunflag(FunctionDatas.WORK_TSGL_JYGL);
        tsjygl.setFunimg(R.mipmap.fuction_bookgl_jygg_icon);
        tsjygl.setIsadd("0");
        tsjygl.setIsdelete("0");
        tsjygl.setIsmain("0");
        tsjygl.setIspermission("1");
        datas.add(tsgl);
        datas.add(tsjygl);
        return datas;
    }

    //=====================================财务功能数据源===========================================
    public final static String WORK_CWGL_GZB = "WORK_CWGL_GZB";//工资表
    public final static String WORK_CWGL_SFCX = "WORK_CWGL_SFCX";//收费查询
    public final static String WORK_CWGL_TFCX = "WORK_CWGL_TFCX";//退费查询
    public final static String WORK_CWGL_FYTJ = "WORK_CWGL_FYTJ";//费用统计
    public final static String WORK_CWGL_ZCGL = "WORK_CWGL_ZCGL";//支出管理

    /**
     * 财务初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getCWGL_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        WorkFunctionEntity gzb = new WorkFunctionEntity();
        gzb.setFunname("工资表");
        gzb.setFunflag(FunctionDatas.WORK_CWGL_GZB);
        gzb.setFunimg(R.mipmap.fuction_cwgl_gzb_icon);
        gzb.setIsadd("0");
        gzb.setIsdelete("0");
        gzb.setIsmain("0");
        gzb.setIspermission("1");
        WorkFunctionEntity sfcx = new WorkFunctionEntity();
        sfcx.setFunname("收费查询");
        sfcx.setFunflag(FunctionDatas.WORK_CWGL_SFCX);
        sfcx.setFunimg(R.mipmap.fuction_cwgl_sfcx_icon);
        sfcx.setIsadd("0");
        sfcx.setIsdelete("0");
        sfcx.setIsmain("0");
        sfcx.setIspermission("1");
        WorkFunctionEntity tfcx = new WorkFunctionEntity();
        tfcx.setFunname("退费查询");
        tfcx.setFunflag(FunctionDatas.WORK_CWGL_TFCX);
        tfcx.setFunimg(R.mipmap.fuction_cwgl_tfcx_icon);
        tfcx.setIsadd("0");
        tfcx.setIsdelete("0");
        tfcx.setIsmain("0");
        tfcx.setIspermission("1");
        WorkFunctionEntity fytj = new WorkFunctionEntity();
        fytj.setFunname("费用统计");
        fytj.setFunflag(FunctionDatas.WORK_CWGL_FYTJ);
        fytj.setFunimg(R.mipmap.fuction_cwgl_fytj_icon);
        fytj.setIsadd("0");
        fytj.setIsdelete("0");
        fytj.setIsmain("0");
        fytj.setIspermission("1");
        WorkFunctionEntity zcgl = new WorkFunctionEntity();
        zcgl.setFunname("支出管理");
        zcgl.setFunflag(FunctionDatas.WORK_CWGL_ZCGL);
        zcgl.setFunimg(R.mipmap.fuction_cwgl_zcgl_icon);
        zcgl.setIsadd("0");
        zcgl.setIsdelete("0");
        zcgl.setIsmain("0");
        zcgl.setIspermission("1");
        datas.add(gzb);
        datas.add(sfcx);
        datas.add(tfcx);
        datas.add(fytj);
        datas.add(zcgl);
        return datas;
    }

    //=====================================校长空间数据源===========================================
    public final static String WORK_MASTER_ZSJH = "WORK_MASTER_ZSJH";//招生计划
    public final static String WORK_MASTER_XSZT = "WORK_MASTER_XSZT";//学生状态
    public final static String WORK_MASTER_XSZyFB = "WORK_MASTER_XSZyFB";//学生专业分布
    public final static String WORK_MASTER_GDZCZHTJ = "WORK_MASTER_GDZCZHTJ";//固定资产统计界面
    public final static String WORK_MASTER_BZRKHHZ = "WORK_MASTER_BZRKHHZ";//班主任考核汇总界面
    public final static String WORK_MASTER_JSKHHZ = "WORK_MASTER_JSKHHZ";//教师考核汇总界面
    public final static String WORK_MASTER_JYZKHHZ = "WORK_MASTER_JYZKHHZ";//教研组考核汇总界面

    /**
     * 校长空间初始化功能
     *
     * @return
     */
    public static ArrayList<WorkFunctionEntity> getMaster_Datas() {
        ArrayList<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        WorkFunctionEntity gzb = new WorkFunctionEntity();
        gzb.setFunname("招生计划");
        gzb.setFunflag(FunctionDatas.WORK_MASTER_ZSJH);
        gzb.setFunimg(R.mipmap.fuction_xiaoz_zsjh);
        gzb.setIsadd("0");
        gzb.setIsdelete("0");
        gzb.setIsmain("0");
        gzb.setIspermission("1");
        WorkFunctionEntity sfcx = new WorkFunctionEntity();
        sfcx.setFunname("学生状态");
        sfcx.setFunflag(FunctionDatas.WORK_MASTER_XSZT);
        sfcx.setFunimg(R.mipmap.fuction_xiaoz_xszttj);
        sfcx.setIsadd("0");
        sfcx.setIsdelete("0");
        sfcx.setIsmain("0");
        sfcx.setIspermission("1");
//        WorkFunctionEntity tfcx = new WorkFunctionEntity();
//        tfcx.setFunname("专业分布");
//        tfcx.setFunflag(FunctionDatas.WORK_MASTER_XSZyFB);
//        tfcx.setFunimg(R.mipmap.fuction_xiaoz_petadata);
//        tfcx.setIsadd("0");
//        tfcx.setIsdelete("0");
//        tfcx.setIsmain("0");
//        tfcx.setIspermission("1");
        WorkFunctionEntity fytj = new WorkFunctionEntity();
        fytj.setFunname("固定资产统计");
        fytj.setFunflag(FunctionDatas.WORK_MASTER_GDZCZHTJ);
        fytj.setFunimg(R.mipmap.fuction_xiaoz_gdzictj);
        fytj.setIsadd("0");
        fytj.setIsdelete("0");
        fytj.setIsmain("0");
        fytj.setIspermission("1");
        WorkFunctionEntity zcgl = new WorkFunctionEntity();
        zcgl.setFunname("班主任考核");
        zcgl.setFunflag(FunctionDatas.WORK_MASTER_BZRKHHZ);
        zcgl.setFunimg(R.mipmap.fuction_xiaoz_bzrkhhz);
        zcgl.setIsadd("0");
        zcgl.setIsdelete("0");
        zcgl.setIsmain("0");
        zcgl.setIspermission("1");
        WorkFunctionEntity jskh = new WorkFunctionEntity();
        jskh.setFunname("教师考核");
        jskh.setFunflag(FunctionDatas.WORK_MASTER_JSKHHZ);
        jskh.setFunimg(R.mipmap.fuction_xiaoz_jskhhz);
        jskh.setIsadd("0");
        jskh.setIsdelete("0");
        jskh.setIsmain("0");
        jskh.setIspermission("1");
        WorkFunctionEntity jyzkh = new WorkFunctionEntity();
        jyzkh.setFunname("教研组考核");
        jyzkh.setFunflag(FunctionDatas.WORK_MASTER_JYZKHHZ);
        jyzkh.setFunimg(R.mipmap.fuction_xiaoz_jyzkhtj);
        jyzkh.setIsadd("0");
        jyzkh.setIsdelete("0");
        jyzkh.setIsmain("0");
        jyzkh.setIspermission("1");
        datas.add(gzb);
        datas.add(sfcx);
//        datas.add(tfcx);
        datas.add(fytj);
        datas.add(zcgl);
        datas.add(jskh);
        datas.add(jyzkh);
        return datas;
    }

    //=====================================会议静态数据=========================================
    public static List<MyMeetingEntity> waitmeetingdatas = new ArrayList<MyMeetingEntity>();

    public static List<MyMeetingEntity> dshmeetingdatas = new ArrayList<MyMeetingEntity>();

    public static List<MyMeetingEntity> ytgmeetingdatas = new ArrayList<MyMeetingEntity>();

    /**
     * 获取技能鉴定报名北背景图
     *
     * @return
     */
    public static List<Integer> getBG_JNJDDatas() {
        List<Integer> datas = new ArrayList<Integer>();
        datas.add(R.mipmap.jnbm_bg1);
        datas.add(R.mipmap.jnbm_bg2);
        datas.add(R.mipmap.jnbm_bg3);
        datas.add(R.mipmap.jnbm_bg4);
        return datas;
    }

    /**
     * 获取技能鉴定报名字体颜色
     *
     * @return
     */
    public static List<Integer> getTVCOLOR_JNJDDatas() {
        List<Integer> datas = new ArrayList<Integer>();
        datas.add(R.color.jnjdlb_tv1color);
        datas.add(R.color.jnjdlb_tv2color);
        datas.add(R.color.jnjdlb_tv3color);
        datas.add(R.color.jnjdlb_tv4color);
        return datas;
    }

    /**
     * 获取课程图标
     *
     * @return
     */
    public static List<Integer> getKEMUICON_Datas() {
        List<Integer> datas = new ArrayList<Integer>();
        datas.add(R.mipmap.kemu1_icon);
        datas.add(R.mipmap.kemu2_icon);
        datas.add(R.mipmap.kemu3_icon);
        datas.add(R.mipmap.kemu4_icon);
        datas.add(R.mipmap.kemu5_icon);
        datas.add(R.mipmap.kemu6_icon);
        datas.add(R.mipmap.kemu7_icon);
        return datas;
    }

    //=========================================根据编码获取个登录角色使用功能===================================================
    public final static String FUCIOTN_PTJS = "PTJS";//普通教师
    public final static String FUCIOTN_BZR = "BZR";//班主任
    public final static String FUCIOTN_SSGL = "DOMPART";//宿舍管理
    public final static String FUCIOTN_RSGL = "PERSONMANAGE";//人事管理
    public final static String FUCIOTN_XSGL = "STUDENTMANAGE";//学生管理
    public final static String FUCIOTN_BGGL = "WORKMANAGE";//办公管理
    public final static String FUCIOTN_JWGL = "ENDUCATIONMANAGE";//教务管理务管理
    public final static String FUCIOTN_CWGL = "FINAACEPART";//财务管理
    public final static String FUCIOTN_TSGL = "BOOKMANAGE";//图书管理
    public final static String FUCIOTN_XZKJ = "MASTERMANAGE";//校长空间管理
    public final static String FUCIOTN_KWMK = "EXAMMANAGE";//考务管理
    public static List<String> jsQXdATAS = new ArrayList<String>();//角色权限

    //获取角色功能
    public static List<WorkFunctionEntity> getMyselfFunction(List<String> biaoshis) {
        List<WorkFunctionEntity> datas = new ArrayList<WorkFunctionEntity>();
        //循环遍历权限所使用模块，将模块功能添加到初始菜单
        if (datas.size() > 0) {
            datas.clear();
        }
        for (String str : biaoshis) {
            if (FUCIOTN_PTJS.equals(str)) {
                datas.addAll(getPTJS_Datas());
            } else if (FUCIOTN_BZR.equals(str)) {
                datas.addAll(getBZR_Datas());
            } else if (FUCIOTN_SSGL.equals(str)) {
                datas.addAll(getSSGL_Datas());
            } else if (FUCIOTN_RSGL.equals(str)) {
                datas.addAll(getRSGL_Datas());
            } else if (FUCIOTN_XSGL.equals(str)) {
                datas.addAll(getXSGL_Datas());
            } else if (FUCIOTN_BGGL.equals(str)) {
                datas.addAll(getBGGL_Datas());
            } else if (FUCIOTN_JWGL.equals(str)) {
                datas.addAll(getJW_Datas());
            } else if (FUCIOTN_CWGL.equals(str)) {
                datas.addAll(getCWGL_Datas());
            } else if (FUCIOTN_TSGL.equals(str)) {
                datas.addAll(getTSGL_Datas());
            } else if (FUCIOTN_XZKJ.equals(str)) {
                datas.addAll(getMaster_Datas());
            } else if (FUCIOTN_KWMK.equals(str)) {
                datas.addAll(getKW_Datas());
            }
        }
        return datas;
    }
}
