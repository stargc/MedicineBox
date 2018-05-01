package com.zhiyi.medicinebox.util.global;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalStaticVariable {
	//频率单位
	public static final String FREQUENY_UNIT_MINUTE = "minute";//分
	public static final String FREQUENY_UNIT_HOUR = "hour";//时
	public static final String FREQUENY_UNIT_DAY = "day";//天
	public static final String FREQUENY_UNIT_WEEK = "week";//周
	public static final String FREQUENY_UNIT_MONTH = "month";//月
	
	//消息类型
	public static final String MSG_CATE_MAINPLAN = "mainPlan";//保养
	public static final String MSG_CATE_INSPLAN = "insPlan";//巡检
	
	//审核状态
	public static final String AUDIT_STATUS_WAIT = "0";//待审核
	public static final String AUDIT_STATUS_PASS = "1";//已审核
	public static final String AUDIT_STATUS_NOPASS = "2";//审核不通过
	
	public static final String USER_SESSION_STORE_KEY = "USER";
	public static final String PERMISSION_SESSION_STORE_KEY = "PERMISSION";
	public static final String PERMISSIONLTS_SESSION_STORE_KEY = "PERMISSIONLTS";
	public static final String USERORG_SESSION_STORE_KEY = "USERORG";
	
	//消息状态
	public static final String MESSAGE_STATUE_ON = "0";//未读
	public static final String MESSAGE_STATUE_OFF = "1";//已读
	
	//计划状态  
	//新建流程  0--1--2  3,4
	//配合  0--6-- 3,4,5,1  --2 3,4
	//领导指派  0--1--2  3,4
	public static final String WP_STATUS_ON = "0";//开启
	public static final String WP_STATUS_ONING = "1";//进行中  指派 的同意和重新指派
	public static final String WP_STATUS_AUDIT = "2";//审核中
	public static final String WP_STATUS_END = "3";//结束
	public static final String WP_STATUS_COMEND = "4";//条件关闭
	public static final String WP_STATUS_REFUSE = "5";//拒绝 Refuse
	public static final String WP_STATUS_OK = "6";//领导确认
	
	//字典表所有属性
	public static final Integer SYS_CODE_SEX = 1;//性别
	public static final Integer SYS_CODE_LEVER = 2;//级别
	public static final Integer SYS_CODE_UNIT = 3;//频率单位
	public static final Integer SYS_CODE_AUDITE = 4;//审核状态
	public static final Integer SYS_CODE_MSG = 5;//消息状态
	public static final Integer SYS_CODE_DOC = 6;//资料类型
	public static final Integer SYS_CODE_COORDINATE= 7;//是否需要协调
	public static final Integer SYS_CODE_WPSTATUS= 8;//计划状态
	public static final Integer SYS_CODE_MEETLEVEL= 9;//会议级别
	public static final Integer SYS_CODE_EVENTTYPE= 10;//奖惩类别
	public static final Integer SYS_CODE_MEMBER = 11;//政治面貌
	public static final Integer SYS_CODE_MARRIAGE = 12;//婚姻状况
	public static final Integer SYS_CODE_EDUCATION= 13;//学历
	public static final Integer SYS_CODE_HEALTH= 14;//健康状态
	public static final Integer SYS_CODE_LICENSE= 15;//驾照
	public static final Integer SYS_CODE_NCRE= 16;//计算机等级
	public static final Integer SYS_CODE_CONTRACTTYPE= 17;//合同类型
	public static final Integer SYS_CODE_AGREEMENTS= 18;//其他协议
	public static final Integer SYS_CODE_PROBATION= 19;//试用期
	public static final Integer SYS_CODE_CONTRACTSTATUS= 20;//合同状态
	public static final Integer SYS_CODE_TRAINLEVEL= 21;//培训级别
	public static final Integer SYS_CODE_ENGLEVEL= 22;//英语级别
	public static final Integer SYS_CODE_SCORE_LEVEL= 23;//评分等级 scoreLevel
	public static final Integer SYS_CODE_OPERATE_MODE= 24;//经营方式 operationMode
	public static final Integer SYS_CODE_PRICE_Level= 25;//价格水平
	public static final Integer SYS_CODE_ORG_WAY= 26;//组织形式Organizational 
	public static final Integer SYS_CODE_EVALUATION= 27;//评价Evaluation
	public static final Integer SYS_CODE_PLACE= 28;//地位place
	public static final Integer SYS_CODE_EXAM_STATUS= 29;//绩效状态
	public static final Integer SYS_CODE_NORMTYPE= 30;//关键指标任务类别normTypeLts
	public static final Integer SYS_CODE_NATURE= 31;//人员性质staffNature
	public static final Integer SYS_CODE_TENDER_STATUS= 32;//标书状态
	public static final Integer SYS_CODE_PRO_TYPE= 33;//项目类型
	public static final Integer SYS_CODE_COMPUTE= 34;//计算方式 Compute
	public static final Integer SYS_CODE_TEMP_LEVER= 35;//模板等级
	public static final Integer SYS_CODE_FUN_TYPE= 36;//社保公积金项目类型
	public static final Integer SYS_CODE_PRO_TRACK= 37;//项目进度状态
	public static final Integer SYS_CODE_SAFETY_PRO_TYPE= 38;//安全项目类型
	public static final Integer SYS_CODE_SAFETY_PRO_RISK_TYPE= 39;//检查类型
	public static final Integer SYS_CODE_SAFETY_PRO_RISK_CATE= 40;//检查类别
	public static final Integer SYS_CODE_QUEST_TYPE= 41;//问题类型 question
	public static final Integer SYS_CODE_ACCIDENT_TYPE= 42;//事故类型  accident
	public static final Integer SYS_CODE_RISK_STATUS_TYPE= 43;//隐患状态类型
	public static final Integer SYS_CODE_ACCEPT_WAY= 44;//验收方式 acceptanceWayLts
	public static final Integer SYS_CODE_REWARD_PUNISH_TYPE= 45;//奖惩类型
	public static final Integer SYS_CODE_REWARD_PUNISH_STATUS= 46;//奖惩状态
	public static final Integer SYS_CODE_BUSINESS_TYPE= 47;//业务类型businessType
	public static final Integer SYS_CODE_SUPPLIER_LEVEL= 48;//供应商等级supplierLeval
	public static final Integer SYS_CODE_PLAN_STATUS= 49;//计划状态
	//状态
	public static final Integer STATUS_YES = 1;//启动
	public static final Integer STATUS_NO = 0;//停止
	
	//消息类型ID
	public static final int MEG_CATEGORY_WAIT_AUDIT = 1;//审核
	public static final int MEG_CATEGORY_MAINTENCE = 2;//保养
	public static final int MEG_CATEGORY_REPAIR = 3;//维修
	public static final int MEG_CATEGORY_INSPECTION = 4;//巡检
	
	//文件类型 INFO
	public static final String FILE_TYPE_MONTH = "0";//月度
	public static final String FILE_TYPE_QUARTER= "1";//季度
	public static final String FILE_TYPE_YEAR = "2";//年度
	public static final String FILE_TYPE_COMPANY= "3";//公司
	public static final String FILE_TYPE_DEPARTMENT = "4";//部门
	public static final String FILE_TYPE_PERSONNEL = "5";//个人
	
	//删除.数据状态
	public static final String ISUSEING_STATUE_ON = "1";//正常数据
	public static final String ISUSEING_STATUE_OFF = "0";//已删除数据
	
	// 记录类型
	public static final String NODES_MEETING = "0";// 会议 meeting
	public static final String NODES_STROKE = "1";// 行程stroke
	public static final String NODES_AMIS= "2";// 目标aims
	public static final String NODES_FEEDBACK = "3";// 巡检Feedback
	
	// 事件类型
	public static final String EVENT_REWARD = "0";// 奖励Reward
	public static final String EVENT_PUNISH = "1";// 惩罚Punish
	
	// 行事历类型
	public static final String CALE_TYPE_MEET = "0";// 会议
	public static final String CALE_TYPE_STROKE= "1";// 形成
	public static final String CALE_TYPE_PLAN= "2";// 计划
	
	//绩效状态
	public static final String EXAM_PAPER_CREATEING = "0";// 创建中
	public static final String EXAM_PAPER_WAIT_AUDIT = "1";// 待审核
	public static final String EXAM_PAPER_AUDIT_ONE= "2";// 一级评审
	public static final String EXAM_PAPER_AUDIT_TWO= "3";// 二级评审
	public static final String EXAM_PAPER_AUDIT_THR= "4";// 三级评审
	public static final String EXAM_PAPER_AUDIT_ONING= "5";// 进行中
	public static final String EXAM_PAPER_SCORE_ONE= "6";// 一级评分
	public static final String EXAM_PAPER_SCORE_TWO= "7";// 二级评分
	public static final String EXAM_PAPER_SCORE_THR= "8";// 三级评分
	public static final String EXAM_PAPER_AUDIT_CONFIRM= "9";// 确认
	//指标类型
	public static final String TASK_EXAM_TRAIN = "0";// 素养类
	public static final String TASK_EXAM_WORK= "1";// 工作类
	
	//评分等级
	public static final String SCORE_LEVEL_ONE = "1";// 一scoreLevel
	public static final String SCORE_LEVEL_TWO= "2";// 二
	public static final String SCORE_LEVEL_THR= "3";// 三
	
	//公司类型
	public static final String PRO_COMPANY_TYPE_A = "0";// 服务方
	public static final String PRO_COMPANY_TYPE_B= "1";// 实施方
	
	//员工性质
	public static final String NATURE_STAFF = "0";// 系统人员
	public static final String NATURE_COOP_STAFF= "1";// 外部人员
	
	//合同类型CONTRACT_TYPElabor
	public static final String CONTRACT_TYPE_LABOR= "1";// 劳动合同
	
	//薪资项目 001----999
	public static final String SAL_PRO_SOCIAL = "001";// 基本工资 social
	public static final String SAL_PRO_POST = "002";// 岗位工资 post
	public static final String SAL_PRO_ABILITY = "003";// 绩效 ability
	public static final String SAL_PRO_ABILITYCOE = "004";// 绩效系数 Performance
	public static final String SAL_PRO_WORK = "005";// 应出勤天数
	public static final String SAL_PRO_ACTUAL="006";// 实际出勤天数
	public static final String SAL_PRO_OUT = "007";// 公出
	public static final String SAL_PRO_ADD= "008";// 加班
	public static final String SAL_PRO_INSURANCE = "012";// 五险基数insurance
	public static final String SAL_PRO_FUND="013";//公积金基数fund
	public static final String SAL_PRO_WASTE = "010";// 旷工
	public static final String SAL_PRO_LATE = "031";// 迟到
	public static final String SAL_PRO_ADV = "033";// 早退
	public static final String SAL_PRO_THING = "035";// 事假小时数
	public static final String SAL_PRO_SICK = "037";// 病假
	public static final String SAL_PRO_MATE = "039";// 产假
	public static final String SAL_PRO_FUNERAL = "041";// 丧假
	public static final String SAL_PRO_CARE = "043";// 护理假
	public static final String SAL_PRO_INJURY = "045";// 工伤假
	public static final String SAL_PRO_HUGH = "047";// 年休假
	public static final String SAL_PRO_SAL_LEVER = "049";// 薪资等级
	
	//项目类型
	public static final String SAL_PRO_ENTER= "0";// 输入项 enter
	public static final String SAL_PRO_FOEMULA = "1";// 公式  formula
	public static final String SAL_PRO_EXPORT = "2";// 系统提取项export
	public static final String SAL_PRO_ATTENDANCE = "3";// 考勤项目Attendance
	
	//状态
	public static final String STATUE_ON = "1";//是
	public static final String STATUE_OFF = "0";//否
	
	//项目进度状态
	public static final String TRACK_OFF = "0";//未启动
	public static final String TRACK_ON = "1";//进行中
	public static final String TRACK_END = "2";//完成
	//人员类型
	public static final String NATURE_EXTERNAL= "1";//外部external
	//计划类型
	public static final String OLAN_TYPE_ADD= "0";//新建
	public static final String PLAN_TYPE_ASSIGN= "1";//指派Assign
	
	//首页缓存
	public static final Map HOME_CACHE = new ConcurrentHashMap();//Cache
	
	//消息类型
	public static final String MSG_WORK_PLAN = "0";//计划消息类型
	public static final String MSG_AUDIT_WORK_PLAN = "1";//工作计划审核消息类型
	public static final String MSG_SAL_COUNT = "2";//薪资计算
	public static final String MSG_EXAM_AUDIT = "3";//个人绩效审核
	public static final String MSG_CONFIRM_WORK_PLAN = "4";//待确认confirm
	
	//日期类型
	public static final String DATA_TYPE_Y = "Y";//年
	public static final String DATA_TYPE_Q = "Q";//季度 Quarterly
	public static final String DATA_TYPE_M = "M";//月
	public static final String DATA_TYPE_D = "D";//日
	public static final String DATA_TYPE_W = "W";//周week
	public static final String DATA_TYPE_YD = "YD";//查询当年每个月的
	public static final String DATA_TYPE_MD = "MD";//当月每天的
	
	//安全检查状态RiskCheck
	public static final String RISK_CHECK_NO_MATURITY = "00";//未到期maturity
	public static final String RISK_CHECK_WAIT = "01";//待反馈
	public static final String RISK_CHECK_ING = "02";//待验收
	public static final String RISK_CHECK_END = "03";//已验收
	public static final String RISK_CHECK_ERROR = "04";//验收未完成
	
	public static final String SAFETY_REWARD = "0";//奖励
	public static final String SAFETY_PUNISHMENT = "1";//惩罚
	
	public static final String SAFETY_REWARDPUN_WAIT = "0";//未到期
	public static final String SAFETY_REWARDPUN_EXPIRE = "1";//到期
	public static final String SAFETY_REWARDPUN_END = "2";//结束 完成
	public static final String SAFETY_REWARDPUN_STOP = "3";//终止
	
	public static final String TRADE_PROJECT_TYPE_ORDINARY= "0";//普通项目ordinary
	public static final String TRADE_PROJECT_TYPE_TWODEPLOY = "1";//二次配项目
	
	public static final String PLAN_STATUS_IN= "0";//计划状态 计划内
	public static final String PLAN_STATUS_OUT = "1";//计划状态 计划外
	
	public static final String GOAL_TYPE_COMPANY= "0";//公司
	public static final String GOAL_TYPE_PERSONAL= "1";//个人personal
}