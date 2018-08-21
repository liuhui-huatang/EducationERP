package com.htcompany.educationerpforgansu.commonpart.views.functionmanageview;

import android.content.Context;
import android.content.Intent;

import com.htcompany.WebActivity;
import com.htcompany.educationerpforgansu.MyWebActivity;
import com.htcompany.educationerpforgansu.internet.InterfaceManager;
import com.htcompany.educationerpforgansu.workpart.activitys.AssetMaintenanceActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.BeOnDutyActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.MainSchedulesActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.MyLeaveApplyActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.MyMeetingNewActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.MyUseCarsActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.MyVotingActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.PlanSummaryActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.TeacherPJActivity;
import com.htcompany.educationerpforgansu.workpart.activitys.WorkFlowActivity;
import com.htcompany.educationerpforgansu.workpart.bookspart.activitys.BooksBorrowRecordActivity;
import com.htcompany.educationerpforgansu.workpart.bookspart.activitys.BooksManageActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys.WorkCarsUseActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys.WorkMeetingActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys.WorkNoticesActivity;
import com.htcompany.educationerpforgansu.workpart.commonworkpart.activitys.WorkVotingManageActivity;
import com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys.DormAppraisingManageActivity;
import com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys.DormDisciplineSearchActivity;
import com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys.DormExchageSeachActivity;
import com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys.DormHealthScoreSummaryActivity;
import com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys.DormKnowingManageActivity;
import com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys.DormRzTsSearchActivity;
import com.htcompany.educationerpforgansu.workpart.dormpart.actiivitys.DormSanitationManageActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.activitys.EdtucationClearExamActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.activitys.EducationClassPerformanceActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.activitys.EducationJXTJActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.activitys.EducationMakeupCjActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.activitys.EducationSearchStudent0ptionalClassActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.activitys.EducationStudentPJManageActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.activitys.EducationTeacherPjActivity;
import com.htcompany.educationerpforgansu.workpart.educationalpart.activitys.EducationTeacherTrainingActivity;
import com.htcompany.educationerpforgansu.workpart.entities.FunctionDatas;
import com.htcompany.educationerpforgansu.workpart.exampart.activitys.ExamProjectActivity;
import com.htcompany.educationerpforgansu.workpart.financepart.activitys.FinalcepartRealityChargeActivity;
import com.htcompany.educationerpforgansu.workpart.financepart.activitys.FinalcepartReturnMoneyActivity;
import com.htcompany.educationerpforgansu.workpart.financepart.activitys.FinalcepartSalaryActivity;
import com.htcompany.educationerpforgansu.workpart.financepart.activitys.FinalcepartStatisticsActivity;
import com.htcompany.educationerpforgansu.workpart.financepart.activitys.FinancepartOutlayActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.activitys.PersonContractManageActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.activitys.PersonLeaveApplyManageActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.activitys.PersonManageActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.activitys.PersonPrizeInfoManageActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.activitys.PersonSocialInsuranceActivity;
import com.htcompany.educationerpforgansu.workpart.personnelpart.activitys.PersonnelFilesActivity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.activity.MasterBZRKHHZActivity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.activity.MasterGDZCZHTJActivity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.activity.MasterJSKHHZActivity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.activity.MasterJYZKHHZActivity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.activity.MasterXSZTActivity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.activity.MasterXSZyFBActivity;
import com.htcompany.educationerpforgansu.workpart.schoolmaster.activity.MasterZSJHActivity;
import com.htcompany.educationerpforgansu.workpart.studentpart.activitys.StudentMessageActivity;
import com.htcompany.educationerpforgansu.workpart.studentpart.activitys.StudentMoralNewsActivity;
import com.htcompany.educationerpforgansu.workpart.studentpart.activitys.StudentNoticesActivity;
import com.htcompany.educationerpforgansu.workpart.studentpart.activitys.StudentPunishmentManageActivity;
import com.htcompany.educationerpforgansu.workpart.studentpart.activitys.StudentRewarsdsManageActivity;
import com.htcompany.educationerpforgansu.workpart.studentpart.activitys.StudentSkillAuthenticateManageActivity;
import com.htcompany.educationerpforgansu.workpart.studentpart.activitys.StudentsSkillGameActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.ClassNameListActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.MyClassSelectActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.SelectClassNameListActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.StudnetLeaveRecordsActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeachPartClassExamActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeacherClassCheckActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeacherClassNotcieActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeacherDormManageActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeacherRcshjcdjActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeacherStudentRemarkActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeacherStudentWJQKActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeacherTermRemarkActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeacherTrainActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeachingClassTableActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeachingDesignActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeachingInteractionActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeachingPlainActivity;
import com.htcompany.educationerpforgansu.workpart.techarepart.activitys.TeachingRecordActivity;

/**
 * 功能跳转封装类
 * Created by wrb on 2016/12/5.
 */
public class FuntionJumpMethod {
  private Intent intent=null;
   private static  FuntionJumpMethod funtionJumpMethod=null;
    /**
     * 返回FuntionJumpMethod实例化的对象
     * @return
     */
    public static FuntionJumpMethod getInstance() {
        if (null == funtionJumpMethod) {
            funtionJumpMethod = new FuntionJumpMethod();
        }
        return funtionJumpMethod;
    }
  public void startJupActivity(Context context,String pageFlag){
     if(FunctionDatas.WORK_GRBG_RC.equals(pageFlag)){
          //我的日程
          intent = new Intent(context, MainSchedulesActivity.class);
      }else if(FunctionDatas.WORK_GRBG_JSPJ.equals(pageFlag)){
          //教师评教
          intent = new Intent(context, TeacherPJActivity.class);
      }else if(FunctionDatas.WORK_GRBG_ZCWH.equals(pageFlag)){
          //资产维护
          intent = new Intent(context, AssetMaintenanceActivity.class);
      }else if(FunctionDatas.WORK_GRBG_GZL.equals(pageFlag)){
          //工作流
          intent = new Intent(context, WorkFlowActivity.class);
      }else if(FunctionDatas.WORK_GRBG_HY.equals(pageFlag)){
          //我的会议
         intent = new Intent(context, MyMeetingNewActivity.class);
      } else if(FunctionDatas.WORK_GRBG_QJSQ.equals(pageFlag)){
          //请假申请
          intent = new Intent(context, MyLeaveApplyActivity.class);
      } else if(FunctionDatas.WORK_GRBG_CARUSE.equals(pageFlag)){
          //车辆使用
          intent = new Intent(context, MyUseCarsActivity.class);
      }else if(FunctionDatas.WORK_GRBG_JHZJ.equals(pageFlag)){
          //个人计划总结
          intent = new Intent(context, PlanSummaryActivity.class);
      }else if(FunctionDatas.WORK_GRBG_TP.equals(pageFlag)){
          //投票
          intent = new Intent(context, MyVotingActivity.class);
      }else if(FunctionDatas.WORK_GRBG_ZB.equals(pageFlag)){
          //值班列表
          intent = new Intent(context, BeOnDutyActivity.class);
      } else if(FunctionDatas.WORK_PTJS_JXJH.equals(pageFlag)){
          //教学计划-普通教师
          intent = new Intent(context, TeachingPlainActivity.class);
      } else if(FunctionDatas.WORK_PTJS_JXSJ.equals(pageFlag)){
          //教学设计-普通教师
          intent = new Intent(context, TeachingDesignActivity.class);
      }else if(FunctionDatas.WORK_PTJS_JSPX.equals(pageFlag)){
          //教师培训
          intent = new Intent(context, TeacherTrainActivity.class);
      }else if(FunctionDatas.WORK_PTJS_SKJL.equals(pageFlag)){
          //授课记录
          intent = new Intent(context, TeachingRecordActivity.class);
      }else if(FunctionDatas.WORK_PTJS_XXKBJHMC.equals(pageFlag)){
          //选修课班级化名册
          intent = new Intent(context, SelectClassNameListActivity.class);
      }else if(FunctionDatas.WORK_PTJS_JXHD.equals(pageFlag)){
          //普通教师教学互动
          intent = new Intent(context, TeachingInteractionActivity.class);
      }else if(FunctionDatas.WORK_PTJS_JSKB.equals(pageFlag)){
          //普通教师教学课表我的课表
          intent = new Intent(context, TeachingClassTableActivity.class);
      }else if(FunctionDatas.WORK_BZR_BJHMC.equals(pageFlag)){
          //班级花名册
          intent = new Intent(context, ClassNameListActivity.class);
      }else if(FunctionDatas.WORK_BZR_BJKM.equals(pageFlag)){
          //班级课表
          intent = new Intent(context, MyClassSelectActivity.class);
      }else if(FunctionDatas.WORK_BZR_BJCJ.equals(pageFlag)){
          //班级成绩
          intent = new Intent(context, TeachPartClassExamActivity.class);
      }else if(FunctionDatas.WORK_BZR_BJGG.equals(pageFlag)){
          //班级公告
          intent = new Intent(context, TeacherClassNotcieActivity.class);
      }else if(FunctionDatas.WORK_BZR_BJKH.equals(pageFlag)){
          //班级考核
          intent = new Intent(context, TeacherClassCheckActivity.class);
      }else if(FunctionDatas.WORK_BZR_XSQJ.equals(pageFlag)){
          //学生请假
          intent = new Intent(context, StudnetLeaveRecordsActivity.class);
      }else if(FunctionDatas.WORK_BZR_RCSWJCDJ.equals(pageFlag)){
          //教师日常事务添加
          intent = new Intent(context, TeacherRcshjcdjActivity.class);
      }else if(FunctionDatas.WORK_BZR_XQPY.equals(pageFlag)){
          //学期评语
          intent = new Intent(context, TeacherTermRemarkActivity.class);
      }else if(FunctionDatas.WORK_BZR_XSPJ.equals(pageFlag)){
          //学生评教
          intent = new Intent(context, TeacherStudentRemarkActivity.class);
      }else if(FunctionDatas.WORK_BZR_XSWJ.equals(pageFlag)){
          //学生违纪
          intent = new Intent(context, TeacherStudentWJQKActivity.class);
      }else if(FunctionDatas.WORK_BZR_SDGL.equals(pageFlag)){
          //宿舍管理
          intent = new Intent(context, TeacherDormManageActivity.class);
      }else if(FunctionDatas.WORK_BZR_BKCJ.equals(pageFlag)){
          //补考成绩查询
          intent = new Intent(context, EducationMakeupCjActivity.class);
      }else if(FunctionDatas.WORK_JW_XXKXSCX.equals(pageFlag)){
          //选修课学生查询
          intent = new Intent(context, EducationSearchStudent0ptionalClassActivity.class);
      }else if(FunctionDatas.WORK_JW_BJCJHZ.equals(pageFlag)){
          //班级成绩汇总
          intent = new Intent(context, EducationClassPerformanceActivity.class);
      }else if(FunctionDatas.WORK_JW_QKCJCX.equals(pageFlag)){
          //清考成绩查询
          intent = new Intent(context, EdtucationClearExamActivity.class);
      }else if(FunctionDatas.WORK_JW_BKCJCX.equals(pageFlag)){
          //补考成绩查询
          intent = new Intent(context, EducationMakeupCjActivity.class);
      }else if(FunctionDatas.WORK_JW_JSPXJHGL.equals(pageFlag)){
          //教师培训计划管理
          intent = new Intent(context, EducationTeacherTrainingActivity.class);
      }else if(FunctionDatas.WORK_JW_XSPJGL.equals(pageFlag)){
          //学生评教管理
          intent = new Intent(context, EducationStudentPJManageActivity.class);
      }else if(FunctionDatas.WORK_JW_JSPJGL.equals(pageFlag)){
          //教师评教管理
          intent = new Intent(context, EducationTeacherPjActivity.class);
      }else if(FunctionDatas.WORK_JW_JSHDTJ.equals(pageFlag)){
          //教学互动统计
          intent = new Intent(context, EducationJXTJActivity.class);
      }else if(FunctionDatas.WORK_KW_XSKCXXCX.equals(pageFlag)){
          //学生考场信息查询
          intent = new Intent(context, ExamProjectActivity.class);
          intent.putExtra("flag","XSKCXXCX");
      }else if(FunctionDatas.WORK_KW_JKXXCX.equals(pageFlag)){
          //监考信息查询
          intent = new Intent(context, ExamProjectActivity.class);
          intent.putExtra("flag","JKXXCX");
      }else if(FunctionDatas.WORK_XSGL_XSCX.equals(pageFlag)){
          //学生信息
          intent = new Intent(context, StudentMessageActivity.class);
      }else if(FunctionDatas.WORK_XSGL_XSGG.equals(pageFlag)){
          //学生公告
          intent = new Intent(context, StudentNoticesActivity.class);
      }else if(FunctionDatas.WORK_XSGL_DYXW.equals(pageFlag)){
          //学生德育新闻
          intent = new Intent(context, StudentMoralNewsActivity.class);
      }else if(FunctionDatas.WORK_XSGL_JNDS.equals(pageFlag)){
          //技能大赛
          intent = new Intent(context, StudentsSkillGameActivity.class);
      }else if(FunctionDatas.WORK_XSGL_JNJD.equals(pageFlag)){
          //技能鉴定
          intent = new Intent(context, StudentSkillAuthenticateManageActivity.class);
      }else if(FunctionDatas.WORK_XSGL_XSJL.equals(pageFlag)){
          //学生奖励
          intent = new Intent(context, StudentRewarsdsManageActivity.class);
      }else if(FunctionDatas.WORK_XSGL_XSCC.equals(pageFlag)){
          //学生惩处
          intent = new Intent(context, StudentPunishmentManageActivity.class);
      }else if(FunctionDatas.WORK_SSGL_RZHTSCX.equals(pageFlag)){
          //住宿和退宿查询
          intent = new Intent(context, DormRzTsSearchActivity.class);
      }else if(FunctionDatas.WORK_SSGL_SSDHCX.equals(pageFlag)){
          //宿舍调换查询
          intent = new Intent(context, DormExchageSeachActivity.class);
      }else if(FunctionDatas.WORK_SSGL_CQGL.equals(pageFlag)){
          //宿舍查寝管理
          intent = new Intent(context, DormKnowingManageActivity.class);
      }else if(FunctionDatas.WORK_SSGL_WJGL.equals(pageFlag)){
          //违纪管理
          intent = new Intent(context, DormDisciplineSearchActivity.class);
      }else if(FunctionDatas.WORK_SSGL_WSDFHZ.equals(pageFlag)){
          //卫生得分汇总
          intent = new Intent(context, DormHealthScoreSummaryActivity.class);
      }else if(FunctionDatas.WORK_SSGL_PYCX.equals(pageFlag)){
          //卫生评优
          intent = new Intent(context, DormAppraisingManageActivity.class);
      }else if(FunctionDatas.WORK_SSGL_WSGL.equals(pageFlag)){
          //卫生管理
          intent = new Intent(context, DormSanitationManageActivity.class);
      }else if(FunctionDatas.WORK_BGGL_BGGG.equals(pageFlag)){
          //办公公告
          intent = new Intent(context, WorkNoticesActivity.class);
      }else if(FunctionDatas.WORK_BGGL_HYGL.equals(pageFlag)){
          //会议
          intent = new Intent(context, WorkMeetingActivity.class);
      }else if(FunctionDatas.WORK_BGGL_CLGL.equals(pageFlag)){
          //车辆使用
          intent = new Intent(context, WorkCarsUseActivity.class);
      }else if(FunctionDatas.WORK_BGGL_TPGL.equals(pageFlag)){
          //投票管理
          intent = new Intent(context, WorkVotingManageActivity.class);
      }else if(FunctionDatas.WORK_RSGL_JZGGL.equals(pageFlag)){
          //教职工管理
          intent = new Intent(context, PersonManageActivity.class);
      }else if(FunctionDatas.WORK_RSGL_LDHTGL.equals(pageFlag)){
          //合同管理
          intent = new Intent(context, PersonContractManageActivity.class);
      }else if(FunctionDatas.WORK_RSGL_SHBXJL.equals(pageFlag)){
          //社会保险记录
          intent = new Intent(context, PersonSocialInsuranceActivity.class);
      }else if(FunctionDatas.WORK_RSGL_RSDA.equals(pageFlag)){
          //人事档案管理
          intent = new Intent(context, PersonnelFilesActivity.class);
      }else if(FunctionDatas.WORK_RSGL_JZGJC.equals(pageFlag)){
          //教职工奖惩管理
          intent = new Intent(context, PersonPrizeInfoManageActivity.class);
      }else if(FunctionDatas.WORK_RSGL_JZGQJ.equals(pageFlag)){
          //教职工请假管理
          intent = new Intent(context, PersonLeaveApplyManageActivity.class);
      }else if(FunctionDatas.WORK_TSGL_TSGL.equals(pageFlag)){
          //图书管理
          intent = new Intent(context, BooksManageActivity.class);
      }else if(FunctionDatas.WORK_TSGL_JYGL.equals(pageFlag)){
          //图书借阅管理
          intent = new Intent(context, BooksBorrowRecordActivity.class);
      }else if(FunctionDatas.WORK_CWGL_GZB.equals(pageFlag)){
          //工资表
          intent = new Intent(context, FinalcepartSalaryActivity.class);
      }else if(FunctionDatas.WORK_CWGL_TFCX.equals(pageFlag)){
          //退费记录汇总
          intent = new Intent(context, FinalcepartReturnMoneyActivity.class);
      }else if(FunctionDatas.WORK_CWGL_SFCX.equals(pageFlag)){
          //实际收费记录
          intent = new Intent(context, FinalcepartRealityChargeActivity.class);
      }else if(FunctionDatas.WORK_CWGL_FYTJ.equals(pageFlag)){
          //费用统计
          intent = new Intent(context, FinalcepartStatisticsActivity.class);
      }else if(FunctionDatas.WORK_CWGL_ZCGL.equals(pageFlag)){
          //财务支出管理
          intent = new Intent(context, FinancepartOutlayActivity.class);
      }else if(FunctionDatas.WORK_MASTER_ZSJH.equals(pageFlag)){
         //招生计划
         intent = new Intent(context, MasterZSJHActivity.class);
     }else if(FunctionDatas.WORK_MASTER_XSZT.equals(pageFlag)){
         //学生状态
         intent = new Intent(context, MasterXSZTActivity.class);
     }else if(FunctionDatas.WORK_MASTER_XSZyFB.equals(pageFlag)){
         //学生专业分布
         intent = new Intent(context, MasterXSZyFBActivity.class);
     }else if(FunctionDatas.WORK_MASTER_GDZCZHTJ.equals(pageFlag)){
         //固定资产统计
         intent = new Intent(context, MasterGDZCZHTJActivity.class);
     }else if(FunctionDatas.WORK_MASTER_BZRKHHZ.equals(pageFlag)){
         //班主任考核汇总
         intent = new Intent(context, MasterBZRKHHZActivity.class);
     }else if(FunctionDatas.WORK_MASTER_JSKHHZ.equals(pageFlag)){
         //教师考核汇总
         intent = new Intent(context, MasterJSKHHZActivity.class);
     }else if(FunctionDatas.WORK_MASTER_JYZKHHZ.equals(pageFlag)){
         //教研组考核汇总
         intent = new Intent(context, MasterJYZKHHZActivity.class);
     }else if(FunctionDatas.WORK_BGGL_HYLB.equals(pageFlag)){
         intent = new Intent(context,WebActivity.class);//会议列表
         intent.putExtra("from",FunctionDatas.WORK_BGGL_HYLB);

     }else if(FunctionDatas.WORK_BGGL_KQTJ.equals(pageFlag)){
         intent = new Intent(context,WebActivity.class);//考勤管理
         intent.putExtra("from",FunctionDatas.WORK_BGGL_KQTJ);
     }
      context.startActivity(intent);
  }
}
