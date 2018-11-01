package com.zhiyi.medicinebox.api.business.service.weixin.vo;

public class WXSendMegReq {
	
	private String touser;
	private String template_id;
	private String page;
	private String form_id;
	private EatMedTemplate data;
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}
	public EatMedTemplate getData() {
		return data;
	}
	public void setData(EatMedTemplate data) {
		this.data = data;
	}
	public static class MsgTemplate{
		private String color;
		private String value;
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}
	public static class EatMedTemplate{
		private MsgTemplate keyword1;
		private MsgTemplate keyword2;
		private MsgTemplate keyword3;
		public MsgTemplate getKeyword1() {
			return keyword1;
		}
		public void setKeyword1(MsgTemplate keyword1) {
			this.keyword1 = keyword1;
		}
		public MsgTemplate getKeyword2() {
			return keyword2;
		}
		public void setKeyword2(MsgTemplate keyword2) {
			this.keyword2 = keyword2;
		}
		public MsgTemplate getKeyword3() {
			return keyword3;
		}
		public void setKeyword3(MsgTemplate keyword3) {
			this.keyword3 = keyword3;
		}
		
	}

}
