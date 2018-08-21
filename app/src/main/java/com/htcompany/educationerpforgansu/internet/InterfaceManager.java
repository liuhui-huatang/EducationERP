package com.htcompany.educationerpforgansu.internet;

import java.util.HashMap;

/**
 * 路径设置类
 *
 * @author wrb
 */
public class InterfaceManager {


//    public static final String siteURLIP = "http://1.203.80.74:8087/";//外网
//    	public static final String siteURLIP="http://172.16.100.212:80/";//内网
        public static final String siteURLIP="http://ecampus.gipc.edu.cn:8081/";//内网


//    		public static final String siteURLIP="http://192.168.1.173";//曹登物




    /**
     * 、、、、、、、、、、、、、、、、、登录模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String lOGIN_LOGIN = "lOGIN_LOGIN";//登录接口
    /**
     * 、、、、、、、、、、、、、、、、、个人中心模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String PERSONCENTER_BASEMSG = "PERSONCENTER_BASEMSG";//个人信息
    public static final String PERSONCENTER_MYCONTRACTMSG = "PERSONCENTER_MYCONTRACTMSG";//我的合同信息
    public static final String PERSONCENTER_MYREWARDS_LIST = "PERSONCENTER_MYREWARDS_LIST";//我的奖励列表数据
    public static final String PERSONCENTER_MYPUNISHMENT_LIST = "PERSONCENTER_MYPUNISHMENT_LIST";//我的惩罚列表数据
    public static final String PERSONCENTER_PERSONALASSESSMENT_LIST = "PERSONCENTER_PERSONALASSESSMENT_LIST";//个人考核列表数据
    public static final String PERSONCENTER_PERSONALJXHD_LIST = "PERSONCENTER_PERSONALJXHD_LIST";//绩效核定列表数据
    /**
     * 、、、、、、、、、、、、、、、、、个人办公模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String WROKFNOTICE_MYPART = "WROKFNOTICE_MYPART";//部门公告
    public static final String WROKFLOW_WAIT = "WROKFLOW_WAIT";//待办事项
    public static final String WROKFLOW_SHCG = "WROKFLOW_SHCG";//待办事项-审核成功
    public static final String WROKFLOW_ZYGZL_TGNEXT = "WROKFLOW_ZYGZL_TGNEXT";//自由工作流-通过下一步
    public static final String WROKFLOW_ZYGZL_TGOVER = "WROKFLOW_ZYGZL_TGOVER";//自由工作流-通过结束
    public static final String WROKFLOW_ZYGZL_BTGOVER = "WROKFLOW_ZYGZL_BTGOVER";//自由工作流-不通过结束
    public static final String WROKFLOW_OVER = "WROKFLOW_OVER";//已办事项
    public static final String WROKFLOW_MYSEND = "WROKFLOW_MYSEND";//我发起的工作流
    public static final String WROKFLOW_OVERFLOWDETIALS = "WROKFLOW_OVERFLOWDETIALS";//已办事项详情
    public static final String WROKFLOW_STARTSENTTYPE_LIST = "WROKFLOW_STARTSENTTYPE_LIST";//发起工作流类型列表
    public static final String WROKFLOW_STARTSENFREEFLOW = "WROKFLOW_STARTSENFREEFLOW";//发起自由工作流类
    public static final String WROKFLOW_STARTSENTGDH5 = "WROKFLOW_STARTSENTGDH5";//发起固定工作流类型列表
    public static final String WROKFLOW_GET_TEACHERS = "WROKFLOW_GET_TEACHERS";//获取教师
    public static final String SCHOOLTHINGS_ZCWH_LIST = "SCHOOLTHINGS_ZCWH_LIST";//资产列表接口
    public static final String SCHOOLTHINGS_ADDZCWH = "SCHOOLTHINGS_ADDZCWH";//添加资产报修列表
    public static final String PERSONWORK_ADDMEETINGS = "PERSONWORK_ADDMEETINGS";//添加会议
    public static final String PERSONWORK_MYMEETINGS_LIST = "PERSONWORK_MYMEETINGS_LIST";//我的会议列表
    public static final String PERSONWORK_MYMEETINGADDRESS_LIST = "PERSONWORK_MYMEETINGADDRESS_LIST";//会议地址列表
    public static final String PERSONWORK_MYUSERCAR_LIST = "PERSONWORK_MYUSERCAR_LIST";//我的车辆使用列表
    public static final String PERSONWORK_CARTYPES_LIST = "PERSONWORK_CARTYPES_LIST";//车辆类型列表
    public static final String PERSONWORK_ADDMYUSERCAR = "PERSONWORK_ADDMYUSERCAR";//添加我的车辆使用
    public static final String PERSONWORK_MYLEAVEAPPLY_LIST = "PERSONWORK_MYLEAVEAPPLY_LIST";//我的请假列表
    public static final String PERSONWORK_MYLEAVEAPPLYTYPES_LIST = "PERSONWORK_MYLEAVEAPPLYTYPES_LIST";//我的请假类型列表

    public static final String PERSONWORK_MYLEAVEAPPLY_ADD = "PERSONWORK_MYLEAVEAPPLY_ADD";//添加我的请假
    public static final String PERSONWORK_BEONDUTE_LIST = "PERSONWORK_BEONDUTE_LIST";//值班列表
    public static final String PERSONWORK_SCHEDULES_LIST = "PERSONWORK_SCHEDULES_LIST";//日程列表
    public static final String PERSONWORK_SCHEDULES_ADD = "PERSONWORK_SCHEDULES_ADD";//添加日程列表
    public static final String SCHOOLTHINGS_DYNEWS = "SCHOOLTHINGS_DYNEWS";//德育新闻列表
    /**
     * 、、、、、、、、、、、、、、、、、通讯录模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String TXL_TEACHER = "TXL_TEACHER";//通讯录
    /**
     * 、、、、、、、、、、、、、、、、、消息模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String MESSAGE_MESSAGELSIT = "MESSAGE_MESSAGELSIT";//消息
    public static final String MESSAGE_READMESS = "MESSAGE_READMESS";//消息

    /**
     * 、、、、、、、、、、、、、、、、、教师模块模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String WROK_TEACHER_CLASSTABLE = "WROK_TEACHER_CLASSTABLE";//教师课表
    public static final String WROK_TEACHERCLASS_LIST = "WROK_TEACHERCLASS_LIST";//班主任下教学班
    public static final String WROK_BZR_CLASSTABLE = "WROK_BZR_CLASSTABLE";//班主任课表
    public static final String WROK_TEACHER_DMC = "WROK_TEACHER_DMC";//教师点名
    public static final String WROK_TEACHER_CHECKED = "WROK_TEACHER_CHECKED";//班级考核

    public static final String WROK_TEACHER_DM_STATUS = "WROK_TEACHER_DM_STATUS";//教师点名状态
    public static final String WROK_TEACHER_JXHD_LIST = "WROK_TEACHER_JXHD_LIST";//教学互动列表
    public static final String WROK_TEACHER_JXHD_ANSWER = "WROK_TEACHER_JXHD_ANSWER";//教学互动问题回答
    public static final String WROK_TEACHER_TRAINPLAN_LIST = "WROK_TEACHER_TRAINPLAN_LIST";//教师培训计划
    public static final String WROK_TEACHER_TRAINPLAN_ADD = "WROK_TEACHER_TRAINPLAN_ADD";//教师培训计划报名
    public static final String WROK_TEACHERRECORD_LIST = "WROK_TEACHERRECORD_LIST";//教师授课记录列表
    public static final String WROK_TEACHERREPALCECLASS_LIST = "WROK_TEACHERREPALCECLASS_LIST";//教师调课代课列表
    public static final String WROK_ALLTERMS_LIST = "WROK_ALLTERMS_LIST";//学期选择列表
    public static final String WROK_ALLCLASS_LIST = "WROK_ALLCLASS_LIST";//班级选择列表
    public static final String WROK_ALLWEEKS_LIST = "WROK_ALLWEEKS_LIST";//周次选择列表
    public static final String WROK_ALLLESSON_LIST = "WROK_ALLLESSON_LIST";//课程选择列表
    public static final String WROK_REPALACELESSONORTEACHER_LIST = "WROK_REPALACELESSONORTEACHER_LIST";//调课课程，代课教师选择列表
    public static final String WROK_REPALACELESSONORTEACHER_ADD = "WROK_REPALACELESSONORTEACHER_ADD";//添加调课课程，代课教师
    public static final String WROK_CLASSNAME_LIST = "WROK_CLASSNAME_LIST";//班级名单列表
    public static final String WROK_CLASSEXAM_LIST = "WROK_CLASSEXAM_LIST";//班级成绩列表
    public static final String WROK_CLASSNOTICE_LIST = "WROK_CLASSNOTICE_LIST";//班级公告列表
    public static final String WROK_CLASSNOTICE_DELETE = "WROK_CLASSNOTICE_DELETE";//删除班级公告
    public static final String WROK_ADDCLASSNOTICE = "WROK_ADDCLASSNOTICE";//发布班级公告
    public static final String WROK_STUDENTLEAVERECORDS_LIST = "WROK_STUDENTLEAVERECORDS_LIST";//学生请假列表
    public static final String WROK_STUDENTLEAVERECORDS_UPLOAD = "WROK_STUDENTLEAVERECORDS_UPLOAD";//提交学生请假列表
    public static final String WROK_STUDENTLEAVERECORDS_XIAOJIA = "WROK_STUDENTLEAVERECORDS_XIAOJIA";//销假学生请假列表
    public static final String WROK_TEACHERDORMMANGE_LIST = "WROK_TEACHERDORMMANGE_LIST";//学生宿舍管理列表
    public static final String WROK_TEACHERRCSWDJ_LIST = "WROK_TEACHERRCSWDJ_LIST";//日常事务登记管理列表

    /**
     * 、、、、、、、、、、、、、、、、、图书管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String SCHOLLBOOKS_LIST = "SCHOLLBOOKS_LIST";//图书列表
    public static final String SCHOLLBOOKS_TYPES = "SCHOLLBOOKS_TYPES";//图书分类
    public static final String SCHOLLBOOKS_MYREADJL = "SCHOLLBOOKS_MYREADJL";//图书借阅记录
    /**
     * 、、、、、、、、、、、、、、、、、校长空间管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String SCHOLMASTER_ZSJF_LIST = "SCHOLMASTER_ZSJF_LIST";//招生计划列表
    public static final String SCHOLMASTER_BZRKH_TITLE_LIST = "SCHOLMASTER_BZRKH_TITLE_LIST";//班主任考核主题列表
    public static final String SCHOLMASTER_BZRKH_LIST = "SCHOLMASTER_BZRKH_LIST";//班主任考核汇总列表
    public static final String SCHOLMASTER_PTJSKH_TITLE_LIST = "SCHOLMASTER_PTJSKH_TITLE_LIST";//普通教师考核主题列表
    public static final String SCHOLMASTER_JSKHZ_LIST = "SCHOLMASTER_JSKHZ_LIST";//教师考核汇总列表
    public static final String SCHOLMASTER_JYZKH_TITLE_LIST = "SCHOLMASTER_JYZKH_TITLE_LIST";//教研组考核主题列表
    public static final String SCHOLMASTER_JYZKHZ_LIST = "SCHOLMASTER_JYZKHZ_LIST";//教研组考核汇总列表
    public static final String SCHOLMASTER_XSZT_LIST = "SCHOLMASTER_XSZT_LIST";//学生状态汇总列表
    public static final String SCHOLMASTER_XSZYFB_LIST = "SCHOLMASTER_XSZYFB_LIST";//学生专业分布列表
    public static final String SCHOLMASTER_GDZCTJ_LIST = "SCHOLMASTER_GDZCTJ_LIST";//固定资产统计列表
    /**
     * 、、、、、、、、、、、、、、、、、财务管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String FINALPART_GZLS_LIST = "FINALPART_GZLS_LIST";//工资列表
    public static final String FINALPART_SFJL_LIST = "FINALPART_SFJL_LIST";//收费记录列表
    public static final String FINALPART_OUTLAY_LIST = "FINALPART_OUTLAY_LIST";//支出管理列表
    public static final String FINALPART_OUTLAY_AGREE = "FINALPART_OUTLAY_AGREE";//支出管理审核通过
    public static final String FINALPART_OUTLAY_NOT = "FINALPART_OUTLAY_NOT";//支出管理审核失败
    public static final String FINALPART_TFJL_LIST = "FINALPART_TFJL_LIST";//退费记录列表
    public static final String FINALPART_FYTJ_LIST = "FINALPART_FYTJ_LIST";//费用统计列表
    /**
     * 、、、、、、、、、、、、、、、、、教务管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String EDUCATIONPART_STUDENTXXCLASS_LIST = "EDUCATIONPART_STUDENTXXCLASS_LIST";//学生选修课列表
    public static final String EDUCATIONPART_JSHDTJ_LIST = "EDUCATIONPART_JSHDTJ_LIST";//教师互动统计列表
    public static final String EDUCATIONPART_CLASSEXAM_LIST = "EDUCATIONPART_CLASSEXAM_LIST";//班级成绩汇总列表
    public static final String EDUCATIONPART_CLEAREXAM_LIST = "EDUCATIONPART_CLEAREXAM_LIST";//清考成绩汇总列表
    public static final String EDUCATIONPART_MAKEEXAM_LIST = "EDUCATIONPART_MAKEEXAM_LIST";//补考考成绩汇总列表
    public static final String EDUCATIONPART_TEACHERTRAINPROJECT_LIST = "EDUCATIONPART_TEACHERTRAINPROJECT_LIST";//教师培训管理列表
    public static final String EDUCATIONPART_TEACHERTRAINPROJECTBMPERSON_LIST = "EDUCATIONPART_TEACHERTRAINPROJECTBMPERSON_LIST";//教师培训管理报名人列表
    public static final String EDUCATIONPART_TEACHERTRAINPROJECTBMPERSON_UPLOAD = "EDUCATIONPART_TEACHERTRAINPROJECTBMPERSON_UPLOAD";//教师培训管理报名人报名
    /**
     * 、、、、、、、、、、、、、、、、、办公管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String COMMONWORKPART_WORKNOTICE_LIST = "COMMONWORKPART_WORKNOTICE_LIST";//办公公告列表
    public static final String COMMONWORKPART_DELETEWORKNOTICE_LIST = "COMMONWORKPART_DELETEWORKNOTICE_LIST";//删除办公公告
    public static final String COMMONWORKPART_WORKNOTICE_ADD = "COMMONWORKPART_WORKNOTICE_ADD";//添加办公公告
    public static final String COMMONWORKPART_PART_LIST = "COMMONWORKPART_PART_LIST";//获取部门数据
    public static final String COMMONWORKPART_MEETINGMANAGE_LIST = "COMMONWORKPART_MEETINGMANAGE_LIST";//会议管理列表
    public static final String COMMONWORKPART_ASSESSMEETINGMANAGE = "COMMONWORKPART_ASSESSMEETINGMANAGE";//会议管理审核通过
    public static final String COMMONWORKPART_NTOASSESSMEETINGMANAGE = "COMMONWORKPART_NTOASSESSMEETINGMANAGE";//会议管理审核不通过

    public static final String COMMONWORKPART_CARUSER_LIST = "COMMONWORKPART_CARUSER_LIST";//车辆使用管理列表
    public static final String COMMONWORKPART_CARSH = "COMMONWORKPART_CARSH";//车辆审核
    public static final String COMMONWORKPART_VOTINGMANGE_LIST = "COMMONWORKPART_VOTINGMANGE_LIST";//投票管理列表
    public static final String COMMONWORKPART_VOTINGOVER = "COMMONWORKPART_VOTINGOVER";//结束投票

    /**
     * 、、、、、、、、、、、、、、、、、学生模块模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String STUDENTPART_STUDENTMESSAGE_LIST = "STUDENTPART_STUDENTMESSAGE_LIST";//学生列表
    public static final String STUDENTPART_STUDENTNOTICE_LIST = "STUDENTPART_STUDENTNOTICE_LIST";//学生公告列表
    public static final String STUDENTPART_STUDENTNOTICE_DELETE = "STUDENTPART_STUDENTNOTICE_DELETE";//删除学生公告数据
    public static final String STUDENTPART_STUDENTRWARD_LIST = "STUDENTPART_STUDENTRWARD_LIST";//学生奖励列表
    public static final String STUDENTPART_STUDENTRWARD_DELETE = "STUDENTPART_STUDENTRWARD_DELETE";//删除学生奖励数据
    /**
     * 、、、、、、、、、、、、、、、、、宿舍管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String DORMPART_CQGL_LIST = "DORMPART_CQGL_LIST";//s宿舍查寝管理列表
    public static final String DORMPART_GETSELECTDATAS_LIST = "DORMPART_GETSELECTDATAS_LIST";//宿舍查寝管理字典接口
    public static final String DORMPART_ADDCQMANGE_LIST = "DORMPART_ADDCQMANGE_LIST";//添加宿舍查寝管理字典接口
    public static final String DORMPART_DORMWSGL_LIST = "DORMPART_DORMWSGL_LIST";//卫生检查管理列表
    public static final String DORMPART_WSJCITME_LIST = "DORMPART_WSJCITME_LIST";//卫生检查评分项列表
    public static final String DORMPART_ADDWSJCITME = "DORMPART_ADDWSJCITME";//卫生检查添加评分项列表
    public static final String DORMPART_DORMWSDFHZ_LIST = "DORMPART_DORMWSDFHZ_LIST";//卫生得分汇总列表
    public static final String DORMPART_DORMPY_LIST = "DORMPART_DORMPY_LIST";//卫生评优列表
    public static final String DORMPART_WJCX_LIST = "DORMPART_WJCX_LIST";//违纪查询列表
    public static final String DORMPART_WJCX_ADD = "DORMPART_WJCX_ADD";//添加宿舍违纪
    public static final String DORMPART_RZTUCX_LIST = "DORMPART_RZTUCX_LIST";//入住退宿查询列表
    public static final String DORMPART_SSDH_LIST = "DORMPART_SSDH_LIST";//入宿舍调换查询列表
    /**
     * 、、、、、、、、、、、、、、、、、人事管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String WORKPERSON_PERSONFILE_LIST = "WORKPERSON_PERSONFILE_LIST";//人事档案列表
    public static final String WORKPERSON_PERSONSOCIALINSURANCE_LIST = "WORKPERSON_PERSONSOCIALINSURANCE_LIST";//社会保险记录列表
    public static final String WORKPERSON_PERSONCONTRACT_LIST = "WORKPERSON_PERSONCONTRACT_LIST";//劳动合同列表
    public static final String WORKPERSON_PERSONMANAGE_LIST = "WORKPERSON_PERSONMANAGE_LIST";//教职工管理列表
    public static final String WORKPERSON_PERSONLEAVEAPPLY_LIST = "WORKPERSON_PERSONLEAVEAPPLY_LIST";//教职工请假列表
    public static final String WORKPERSON_PERSONLEAVEAPPLY_XJ_LIST = "WORKPERSON_PERSONLEAVEAPPLY_XJ_LIST";//教职工销假
    public static final String WORKPERSON_PERSONLEAVEAPPLY_TJ_LIST = "WORKPERSON_PERSONLEAVEAPPLY_TJ_LIST";//教职工提交
    /**
     * 、、、、、、、、、、、、、、、、、考务管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String EXAMPART_PROJECT_LIST = "EXAMPART_PROJECT_LIST";//考试项目列表
    public static final String EXAMPART_STUDENTSEARCH_LIST = "EXAMPART_STUDENTSEARCH_LIST";//学生考试项目列表
    public static final String EXAMPART_JKTEACHERSEARCH_LIST = "EXAMPART_JKTEACHERSEARCH_LIST";//监考教师查询列表
    public static final String MEETING_LIST = "MEETING_LIST";
    public static final String SIGN_TOTAL_DATA = "SIGN_TOTAL_DATA";

    private static HashMap<String, String> urlManager = new HashMap<String, String>();
    private static InterfaceManager manager;


    public static InterfaceManager getInstance() {
        if (manager == null) {
            manager = new InterfaceManager();
/**
 * 、、、、、、、、、、、、、、、、、登录模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.lOGIN_LOGIN, siteURLIP + "/api/user/teacher/login");//登录
            /**
             * 、、、、、、、、、、、、、、、、、个人中心模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.PERSONCENTER_BASEMSG, siteURLIP + "/api/teacherBasic/appInfo");//个人信息
            urlManager.put(InterfaceManager.PERSONCENTER_MYCONTRACTMSG, siteURLIP + "/api/TeacherHeTong/appList");//我的合同信息
            urlManager.put(InterfaceManager.PERSONCENTER_MYREWARDS_LIST, siteURLIP + "/api/teacherReward/appList");//我的奖励列表数据
            urlManager.put(InterfaceManager.PERSONCENTER_MYPUNISHMENT_LIST, siteURLIP + "/api/teacherPunish/appList");//我的惩罚列表数据
            urlManager.put(InterfaceManager.PERSONCENTER_PERSONALASSESSMENT_LIST, siteURLIP + "/api/getScore/appList");//个人考核列表数据
            urlManager.put(InterfaceManager.PERSONCENTER_PERSONALJXHD_LIST, siteURLIP + "/api/user/login");//绩效核定列表数据
            urlManager.put(InterfaceManager.SCHOLMASTER_XSZYFB_LIST, siteURLIP + "/api/user/login");//学生专业分布列表数据

/**
 * 、、、、、、、、、、、、、、、、、通讯录模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.TXL_TEACHER, siteURLIP + "/api/teacher/txl");//同学录
            /**
             * 、、、、、、、、、、、、、、、、、消息模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.MESSAGE_MESSAGELSIT, siteURLIP + "/api/message/appList");//消息列表
            urlManager.put(InterfaceManager.MESSAGE_READMESS, siteURLIP + "/api/message/appRead");//读取消息
/**
 * 、、、、、、、、、、、、、、、、、工作流模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.WROKFNOTICE_MYPART, siteURLIP + "/api/gonggao/appMyGongGao");//部门公告
            urlManager.put(InterfaceManager.WROKFLOW_WAIT, siteURLIP + "/api/process/daiban");//代办事项
            urlManager.put(InterfaceManager.WROKFLOW_SHCG, siteURLIP + "/api/process/gbanli");//固定、预定义办理接口
            urlManager.put(InterfaceManager.WROKFLOW_ZYGZL_TGNEXT, siteURLIP + "/api/process/zyxbanli");//自由工作流-通过下一步
            urlManager.put(InterfaceManager.WROKFLOW_ZYGZL_TGOVER, siteURLIP + "/api/process/zytbanli");//自由工作流-通过结束
            urlManager.put(InterfaceManager.WROKFLOW_ZYGZL_BTGOVER, siteURLIP + "/api/process/zynbanli");//自由工作流-不通过结束
            urlManager.put(InterfaceManager.WROKFLOW_OVER, siteURLIP + "/api/process/yiban");//已办办事项
            urlManager.put(InterfaceManager.WROKFLOW_MYSEND, siteURLIP + "/api/process/myprocess");//我发起的工作流
            urlManager.put(InterfaceManager.WROKFLOW_OVERFLOWDETIALS, siteURLIP + "/api/process/getyewu");//待办详情

            urlManager.put(InterfaceManager.WROKFLOW_GET_TEACHERS, siteURLIP + "/api/teachers");//教职工
            urlManager.put(InterfaceManager.WROKFLOW_STARTSENTTYPE_LIST, siteURLIP + "/api/process/fqlist");//发起工作流类型列表
            urlManager.put(InterfaceManager.WROKFLOW_STARTSENFREEFLOW, siteURLIP + "/api/process/zygzltj");//发起自由工作流
            urlManager.put(InterfaceManager.WROKFLOW_STARTSENTGDH5, siteURLIP + "/api/process/gdfqgzl");//发起固定工作流


            urlManager.put(InterfaceManager.SCHOOLTHINGS_ZCWH_LIST, siteURLIP + "/api/repair/info");//资产列表接口
            urlManager.put(InterfaceManager.SCHOOLTHINGS_ADDZCWH, siteURLIP + "/api/repair/add");//添加资产报修接口
            urlManager.put(InterfaceManager.PERSONWORK_MYMEETINGS_LIST, siteURLIP + "/api/ManageMeeting/appList");//我的会议列表
            urlManager.put(InterfaceManager.PERSONWORK_ADDMEETINGS, siteURLIP + "/api/ManageMeeting/appApply");//添加会议
            urlManager.put(InterfaceManager.PERSONWORK_MYUSERCAR_LIST, siteURLIP + "/api/usecar/getUseCarList");//我的车辆使用列表
            urlManager.put(InterfaceManager.PERSONWORK_MYMEETINGADDRESS_LIST, siteURLIP + "/api/MeetingRoom/appRoom");//会议地址列表

            urlManager.put(InterfaceManager.PERSONWORK_CARTYPES_LIST, siteURLIP + "/api/usecar/getCarType");//车辆类型列表

            urlManager.put(InterfaceManager.PERSONWORK_ADDMYUSERCAR, siteURLIP + "/api/usecar/userCarApply");//添加我的车辆使用
            urlManager.put(InterfaceManager.PERSONWORK_MYLEAVEAPPLY_LIST, siteURLIP + "/api/AskLevel/appList");//我的请假列表
            urlManager.put(InterfaceManager.PERSONWORK_MYLEAVEAPPLYTYPES_LIST, siteURLIP + "/api/AskLevel/appType");//我的请假类型列表


            urlManager.put(InterfaceManager.PERSONWORK_MYLEAVEAPPLY_ADD, siteURLIP + "/api/AskLevel/appApply");//添加我的请假
            urlManager.put(InterfaceManager.PERSONWORK_BEONDUTE_LIST, siteURLIP + "/api/duty/appList");//值班列表
            urlManager.put(InterfaceManager.PERSONWORK_SCHEDULES_LIST, siteURLIP + "/api/schedule/getList");//日程列表
            urlManager.put(InterfaceManager.PERSONWORK_SCHEDULES_ADD, siteURLIP + "/api/schedule/create");//添加日程
            urlManager.put(InterfaceManager.SCHOOLTHINGS_DYNEWS, siteURLIP + "/api/student/moraledunews");//添加日程


/**
 * 、、、、、、、、、、、、、、、、、教师模块模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.WROK_TEACHER_CLASSTABLE, siteURLIP + "/api/teacher/kebiao");//教师课表
            urlManager.put(InterfaceManager.WROK_TEACHERCLASS_LIST, siteURLIP + "/api/getbzrjxb");//班主任下教学班雷彪
            urlManager.put(InterfaceManager.WROK_BZR_CLASSTABLE, siteURLIP + "/api/teacher/bzrkebiao");//班主任课表
            urlManager.put(InterfaceManager.WROK_TEACHER_CHECKED, siteURLIP + "/api/classevaluation/appMyClassKpi");//班级考核


            urlManager.put(InterfaceManager.WROK_TEACHER_DMC, siteURLIP + "/api/shouke/shjall");//教师点民侧
            urlManager.put(InterfaceManager.WROK_TEACHER_DM_STATUS, siteURLIP + "/api/shouke/qingjia");//教师点名
            urlManager.put(InterfaceManager.WROK_TEACHER_JXHD_LIST, siteURLIP + "/api/shouke/teacherlist");//教学互动列表
            urlManager.put(InterfaceManager.WROK_TEACHER_JXHD_ANSWER, siteURLIP + "/api/shouke/question_da");//教学互动问题回答
            urlManager.put(InterfaceManager.WROK_TEACHER_TRAINPLAN_LIST, siteURLIP + "/api/shouke/getxiangmu");//教师培训计划
            urlManager.put(InterfaceManager.WROK_TEACHER_TRAINPLAN_ADD, siteURLIP + "/api/shouke/getbaoming");//教师培训计划报名
            urlManager.put(InterfaceManager.WROK_TEACHERRECORD_LIST, siteURLIP + "/api/teacher/skjl");//教师授课记录列表
            urlManager.put(InterfaceManager.WROK_TEACHERREPALCECLASS_LIST, siteURLIP + "/api/schedule/dttlist");//代课调课列表

            urlManager.put(InterfaceManager.WROK_ALLTERMS_LIST, siteURLIP + "/api/getterm");//学期选择列表
            urlManager.put(InterfaceManager.WROK_ALLCLASS_LIST, siteURLIP + "/api/ClassBulletinTea/appClass");//根据教师班级选择
            urlManager.put(InterfaceManager.WROK_ALLWEEKS_LIST, siteURLIP + "/api/schedule/dttlist");//周次选择列表
            urlManager.put(InterfaceManager.WROK_ALLLESSON_LIST, siteURLIP + "/api/schedule/getdtjcource");//课程选择列表
            urlManager.put(InterfaceManager.WROK_REPALACELESSONORTEACHER_LIST, siteURLIP + "/api/schedule/getdtjcources");//调课课程，代课教师选择列表
            urlManager.put(InterfaceManager.WROK_REPALACELESSONORTEACHER_ADD, siteURLIP + "/api/schedule/dttadd");//添加调课课程，代课教师选择列表

            urlManager.put(InterfaceManager.WROK_CLASSNAME_LIST, siteURLIP + "/api/substudent/bzrhmc");//班级名单列表
            urlManager.put(InterfaceManager.WROK_CLASSEXAM_LIST, siteURLIP + "/api/Chengji/appMyList");//班级成绩列表
            urlManager.put(InterfaceManager.WROK_CLASSNOTICE_LIST, siteURLIP + "/api/ClassBulletinTea/appList");//班级公告列表
            urlManager.put(InterfaceManager.WROK_CLASSNOTICE_DELETE, siteURLIP + "/api/ClassBulletinTea/appDelete");//删除班级公告

            urlManager.put(InterfaceManager.WROK_ADDCLASSNOTICE, siteURLIP + "/api/ClassBulletinTea/appApply");//添加班级公告列表

            urlManager.put(InterfaceManager.WROK_STUDENTLEAVERECORDS_LIST, siteURLIP + "/api/bzr/stqingjia");//学生请假列表
            urlManager.put(InterfaceManager.WROK_STUDENTLEAVERECORDS_UPLOAD, siteURLIP + "/api/bzr/stleavesubmit");//提交学生请假列表
            urlManager.put(InterfaceManager.WROK_STUDENTLEAVERECORDS_XIAOJIA, siteURLIP + "/api/bzr/stbacksubmit");//销假学生请假列表


            urlManager.put(InterfaceManager.WROK_TEACHERDORMMANGE_LIST, siteURLIP + "/api/dormitory/studentList");//学生宿舍管理列表
            urlManager.put(InterfaceManager.WROK_TEACHERRCSWDJ_LIST, siteURLIP + "/api/daily/appList");//学生宿舍管理列表


            /**
             * 、、、、、、、、、、、、、、、、、图书管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.SCHOLLBOOKS_LIST, siteURLIP + "/api/book/getbook");//图书列表
            urlManager.put(InterfaceManager.SCHOLLBOOKS_TYPES, siteURLIP + "/api/book/getcategory");//图书分类
            urlManager.put(InterfaceManager.SCHOLLBOOKS_MYREADJL, siteURLIP + "/api/book/getjieyue");//图书借阅记录
/**
 * 、、、、、、、、、、、、、、、、、校长空间管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.SCHOLMASTER_ZSJF_LIST, siteURLIP + "/api/zhaosheng/zsplan");//招生计划列表
            urlManager.put(InterfaceManager.SCHOLMASTER_BZRKH_LIST, siteURLIP + "/api/teacher/appManageCheck");//班主任考核汇总列表
            urlManager.put(InterfaceManager.SCHOLMASTER_BZRKH_TITLE_LIST, siteURLIP + "/api/teacher/appManageTheme");//班主任考核主题列表
            urlManager.put(InterfaceManager.SCHOLMASTER_PTJSKH_TITLE_LIST, siteURLIP + "/api/teacher/appTeacherTheme");//普通教师考核主题列表
            urlManager.put(InterfaceManager.SCHOLMASTER_JSKHZ_LIST, siteURLIP + "/api/teacher/appTeacherCheck");//教师考核汇总列表
            urlManager.put(InterfaceManager.SCHOLMASTER_JYZKH_TITLE_LIST, siteURLIP + "/api/teacher/appOrgThemeId");//教研组考核汇总主题列表
            urlManager.put(InterfaceManager.SCHOLMASTER_JYZKHZ_LIST, siteURLIP + "/api/teacher/appOrgTotal");//教研组考核汇总列表
            urlManager.put(InterfaceManager.SCHOLMASTER_XSZT_LIST, siteURLIP + "/api/xzkj/studentstatus");//学生状态汇总列表
            urlManager.put(InterfaceManager.SCHOLMASTER_GDZCTJ_LIST, siteURLIP + "/api/asset/getStatistics");//固定资产统计列表

            /**
             * 、、、、、、、、、、、、、、、、、财务管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.FINALPART_GZLS_LIST, siteURLIP + "/api/teacher/mygongzi");//工资列表
            urlManager.put(InterfaceManager.FINALPART_SFJL_LIST, siteURLIP + "/api/teacher/shoufei");//收费记录列表
            urlManager.put(InterfaceManager.FINALPART_OUTLAY_LIST, siteURLIP + "/api/teacher/zhichu");//支出管理列表
            urlManager.put(InterfaceManager.FINALPART_OUTLAY_AGREE, siteURLIP + "/api/teacher/zhichuTongYi");//支出管理审核通过
            urlManager.put(InterfaceManager.FINALPART_OUTLAY_NOT, siteURLIP + "/api/teacher/zhichuShiBai");//支出管理审核失败
            urlManager.put(InterfaceManager.FINALPART_TFJL_LIST, siteURLIP + "/api/teacher/tuifei");//退费记录列表
            urlManager.put(InterfaceManager.FINALPART_FYTJ_LIST, siteURLIP + "/api/teacher/tongji");//费用统计列表
/**
 * 、、、、、、、、、、、、、、、、、教务管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.EDUCATIONPART_STUDENTXXCLASS_LIST, siteURLIP + "/api/xuanxius");//学生选修课列表
            urlManager.put(InterfaceManager.EDUCATIONPART_JSHDTJ_LIST, siteURLIP + "/api/jiaoqwu/hudongtongji");//教学互动统计列表
            urlManager.put(InterfaceManager.EDUCATIONPART_CLASSEXAM_LIST, siteURLIP + "/api/Chengji/appList");//班级成绩汇总列表
            urlManager.put(InterfaceManager.EDUCATIONPART_CLEAREXAM_LIST, siteURLIP + "/api/qingkao/appList");//清考成绩汇总列表
            urlManager.put(InterfaceManager.EDUCATIONPART_MAKEEXAM_LIST, siteURLIP + "/api/bukao/appList");//补考成绩汇总列表
            urlManager.put(InterfaceManager.EDUCATIONPART_TEACHERTRAINPROJECT_LIST, siteURLIP + "/api/getpxxiangmu");//教师培训管理列表
            urlManager.put(InterfaceManager.EDUCATIONPART_TEACHERTRAINPROJECTBMPERSON_LIST, siteURLIP + "/api/getpxxiangmubm");//教师培训报名人列表
            urlManager.put(InterfaceManager.EDUCATIONPART_TEACHERTRAINPROJECTBMPERSON_UPLOAD, siteURLIP + "/api/czpxxiangmu");//教师培训报名人列表


/**
 * 、、、、、、、、、、、、、、、、、办公管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.COMMONWORKPART_WORKNOTICE_LIST, siteURLIP + "/api/gonggao/appGongGao");//办公公告列表
            urlManager.put(InterfaceManager.COMMONWORKPART_DELETEWORKNOTICE_LIST, siteURLIP + "/api/gonggao/appDeleteGongGao");//删除办公公告
            urlManager.put(InterfaceManager.COMMONWORKPART_WORKNOTICE_ADD, siteURLIP + "/api/gonggao/appCreateGongGao");//添加办公公告
            urlManager.put(InterfaceManager.COMMONWORKPART_PART_LIST, siteURLIP + "/api/gonggao/appAllOrg");//获取部门
            urlManager.put(InterfaceManager.COMMONWORKPART_MEETINGMANAGE_LIST, siteURLIP + "/api/ManageMeeting/appList");//获取会议管理列表数据
            urlManager.put(InterfaceManager.COMMONWORKPART_ASSESSMEETINGMANAGE, siteURLIP + "/api/ManageMeeting/appApplySuccess");//会议管理审核通过
            urlManager.put(InterfaceManager.COMMONWORKPART_NTOASSESSMEETINGMANAGE, siteURLIP + "/api/ManageMeeting/appApplyError");//会议管理审核不通过
            urlManager.put(InterfaceManager.COMMONWORKPART_CARUSER_LIST, siteURLIP + "/api/usecar/getUseCarList");//获取车辆使用列表数据
            urlManager.put(InterfaceManager.COMMONWORKPART_CARSH, siteURLIP + "/api/usecar/examineUseCar");//车辆审核数据

            urlManager.put(InterfaceManager.COMMONWORKPART_VOTINGMANGE_LIST, siteURLIP + "/api/Voteproject/appList");//获取投票管理列表数据
            urlManager.put(InterfaceManager.COMMONWORKPART_VOTINGOVER, siteURLIP + "/api/Voteproject/appEnd");//结束投票
/**
 * 、、、、、、、、、、、、、、、、、学生模块模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.STUDENTPART_STUDENTMESSAGE_LIST, siteURLIP + "/api/substudent/xuesheng");//学生信息列表
            urlManager.put(InterfaceManager.STUDENTPART_STUDENTNOTICE_LIST, siteURLIP + "/api/Voteproject/appEnd");//学生公告列表
            urlManager.put(InterfaceManager.STUDENTPART_STUDENTNOTICE_DELETE, siteURLIP + "/api/Voteproject/appEnd");//删除学生公告数据
            urlManager.put(InterfaceManager.STUDENTPART_STUDENTRWARD_LIST, siteURLIP + "/api/Voteproject/appEnd");//学生奖励列表
            urlManager.put(InterfaceManager.STUDENTPART_STUDENTRWARD_DELETE, siteURLIP + "/api/Voteproject/appEnd");//删除学生奖励数据
/**
 * 、、、、、、、、、、、、、、、、、宿舍管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.DORMPART_CQGL_LIST, siteURLIP + "/api/dormitory/checkChamberList");//宿舍查寝管理列表
            urlManager.put(InterfaceManager.DORMPART_GETSELECTDATAS_LIST, siteURLIP + "/api/dormitory/getCheckChamberParameter");//宿舍查寝字典
            urlManager.put(InterfaceManager.DORMPART_ADDCQMANGE_LIST, siteURLIP + "/api/dormitory/creatCheckChamber");//添加宿舍查寝管理
            urlManager.put(InterfaceManager.DORMPART_DORMWSGL_LIST, siteURLIP + "/api/dormitory/getScoreManage");//卫生检查管理
            urlManager.put(InterfaceManager.DORMPART_WSJCITME_LIST, siteURLIP + "/api/dormitory/getScoreManageOtherFiel");//卫生检查评分项管理
            urlManager.put(InterfaceManager.DORMPART_ADDWSJCITME, siteURLIP + "/api/dormitory/createScoreManage");//卫生检查添加管理
            urlManager.put(InterfaceManager.DORMPART_DORMWSDFHZ_LIST, siteURLIP + "/api/dormitory/getScoreSummary");//卫生得分汇总列表
            urlManager.put(InterfaceManager.DORMPART_DORMPY_LIST, siteURLIP + "/api/dormitory/getScoreSearch");//卫生评优列表
            urlManager.put(InterfaceManager.DORMPART_WJCX_LIST, siteURLIP + "/api/dormitory/getDiscipline");//违纪查询列表
            urlManager.put(InterfaceManager.DORMPART_WJCX_ADD, siteURLIP + "/api/dormitory/addDiscipline");//天机违纪
            urlManager.put(InterfaceManager.DORMPART_RZTUCX_LIST, siteURLIP + "/api/dormitory/getDormitoryLog");//入住退宿查询列表
            urlManager.put(InterfaceManager.DORMPART_SSDH_LIST, siteURLIP + "/api/dormitory/getDormitoryChangeLog");//宿舍调换查询列表

/**
 * 、、、、、、、、、、、、、、、、、人事管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.WORKPERSON_PERSONFILE_LIST, siteURLIP + "/api/Personnel/appList");//人事档案列表
            urlManager.put(InterfaceManager.WORKPERSON_PERSONSOCIALINSURANCE_LIST, siteURLIP + "/api/SocialInsurance/appList");//社会保险记录列表
            urlManager.put(InterfaceManager.WORKPERSON_PERSONCONTRACT_LIST, siteURLIP + "/api/HeTong/appAllList");//劳动合同列表
            urlManager.put(InterfaceManager.WORKPERSON_PERSONMANAGE_LIST, siteURLIP + "/api/teacherBasic/appList");//教职工管理列表
            urlManager.put(InterfaceManager.WORKPERSON_PERSONLEAVEAPPLY_LIST, siteURLIP + "/api/AskLevel/appAllList");//教职工请假列表
            urlManager.put(InterfaceManager.WORKPERSON_PERSONLEAVEAPPLY_XJ_LIST, siteURLIP + "/api/AskLevel/xiaojia");//教职工销假列表
            urlManager.put(InterfaceManager.WORKPERSON_PERSONLEAVEAPPLY_TJ_LIST, siteURLIP + "/api/AskLevel/tijiao");//教职工提交列表


/**
 * 、、、、、、、、、、、、、、、、、考务管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.EXAMPART_PROJECT_LIST, siteURLIP + "/api/project/appList");//考试项目列表
            urlManager.put(InterfaceManager.EXAMPART_STUDENTSEARCH_LIST, siteURLIP + "/api/sdpk/appList");//学生考试项查询目列表
            urlManager.put(InterfaceManager.EXAMPART_JKTEACHERSEARCH_LIST, siteURLIP + "/api/jkxx/appList");//监考教师查询目列表



            urlManager.put(InterfaceManager.MEETING_LIST, siteURLIP + "/api/qiandaoJiekouList");//会议列表
            urlManager.put(InterfaceManager.SIGN_TOTAL_DATA, siteURLIP + "api/teacher/sign");//考勤统计

        }
        return manager;
    }

    /**
     * 获取路径
     *
     * @param name
     * @return
     */
    public String getURL(String name) {
        return urlManager.get(name);
    }


}
