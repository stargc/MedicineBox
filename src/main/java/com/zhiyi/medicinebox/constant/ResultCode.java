package com.zhiyi.medicinebox.constant;

public class ResultCode {

	/**正常时返回*/
	public static final String RESULT_NORMAL = "0";
	
	/**返回结果为null*/
	public static final String RESULT_NULL = "1";
	
	/**操作失败*/
	public static final String RESULT_FAIL = "2";
	
	/**参数错误*/
	public static final String RESULT_PARM_ERROR = "3";	
	
	/**文件保存错误*/
	public static final String RESULT_FILE_SAVE_ERROR = "4";

	/**发送不可预见错误，filter 中直接catch住*/
	public static final String RESULT_ERROR = "999";
}
