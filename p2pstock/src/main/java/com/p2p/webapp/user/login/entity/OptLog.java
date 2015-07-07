package com.p2p.webapp.user.login.entity;

/**
 * 用户操作日志表
 * @author cssong
 *
 */
public class OptLog {

		//ID唯一
		private String city_id;
		//用户ID
		private String user_id;
		//操作状态
		private String opt_status;
		//操作时间
		private String opt_date;
		//操作类型
		private String opt_type;
		//操作IP
		private String opt_ip;
		//创建时间
		private String create_date;
		//更新时间
		private String update_date;
		public String getCity_id() {
			return city_id;
		}
		public void setCity_id(String cityId) {
			city_id = cityId;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String userId) {
			user_id = userId;
		}
		public String getOpt_status() {
			return opt_status;
		}
		public void setOpt_status(String optStatus) {
			opt_status = optStatus;
		}
		public String getOpt_date() {
			return opt_date;
		}
		public void setOpt_date(String optDate) {
			opt_date = optDate;
		}
		public String getOpt_type() {
			return opt_type;
		}
		public void setOpt_type(String optType) {
			opt_type = optType;
		}
		public String getOpt_ip() {
			return opt_ip;
		}
		public void setOpt_ip(String optIp) {
			opt_ip = optIp;
		}
		public String getCreate_date() {
			return create_date;
		}
		public void setCreate_date(String createDate) {
			create_date = createDate;
		}
		public String getUpdate_date() {
			return update_date;
		}
		public void setUpdate_date(String updateDate) {
			update_date = updateDate;
		}
		
		
		
}
