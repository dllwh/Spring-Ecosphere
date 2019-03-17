/**
 * js时间转换工具类
 * 
 * @创建时间：2019年1月12日16:27:16
 */
var DateUtils = {
	getCurrentMsTime : function() { // 获取当前时间毫秒数
		var myDate = new Date();
		return myDate.getTime();
	},
	dateToLongMsTime : function(date) { // 时间格式转毫秒
		return date.getTime();
	},
	formatDate : function(dateTime) { // 转换日期格式yyyy-MM-dd HH:mm:ss
		return this.formatterDateTime(dateTime);
	},
	formatterDate : function(date) { // 格式化日期（不含时间）
		var datetime = this.getGMTTime(date);
		var result = datetime.getFullYear()
				+ "-"// "年"
				+ ((datetime.getMonth() + 1) >= 10 ? (datetime.getMonth() + 1)
						: "0" + (datetime.getMonth() + 1))
				+ "-"// "月"
				+ (datetime.getDate() < 10 ? "0" + datetime.getDate()
						: datetime.getDate());
		return result;
	},
	formatterDate2 : function(date) { // 格式化日期（含时间"00:00:00"）
		var datetime = this.getGMTTime(date);
		var result = datetime.getFullYear()
				+ "-"// "年"
				+ ((datetime.getMonth() + 1) >= 10 ? (datetime.getMonth() + 1)
						: "0" + (datetime.getMonth() + 1))
				+ "-"// "月"
				+ (datetime.getDate() < 10 ? "0" + datetime.getDate()
						: datetime.getDate()) + " " + "00:00:00";
		return result;
	},
	formatterDateTime : function(date) {
		var datetime = this.getGMTTime(date);
		var result = datetime.getFullYear()
				+ "-"// "年"
				+ ((datetime.getMonth() + 1) >= 10 ? (datetime.getMonth() + 1)
						: "0" + (datetime.getMonth() + 1))
				+ "-"// "月"
				+ (datetime.getDate() < 10 ? "0" + datetime.getDate()
						: datetime.getDate())
				+ " "
				+ (datetime.getHours() < 10 ? "0" + datetime.getHours()
						: datetime.getHours())
				+ ":"
				+ (datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
						: datetime.getMinutes())
				+ ":"
				+ (datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
						: datetime.getSeconds());
		return result;
	},
	compareDateTime : function(startTime, endTime) { // 时间比较（结束时间大于开始时间）
		return ((new Date(endTime.replace(/-/g, "/"))) > (new Date(startTime
				.replace(/-/g, "/"))));
	},
	compareRightStartTime : function(month, startTime) { // 验证开始时间合理性（开始时间不能小于当前时间X个月）
		var now = formatterDayAndTime(new Date());
		var sms = new Date(startTime.replace(/-/g, "/"));
		var ems = new Date(now.replace(/-/g, "/"));
		var tDayms = month * 30 * 24 * 60 * 60 * 1000;
		var dvalue = ems - sms;
		if (dvalue > tDayms) {
			return false;
		}
		return true;
	},
	getTodayDateTime : function() {// 获取今天（开始时间和结束时间值）
		var daymsTime = 24 * 60 * 60 * 1000;
		var tomorrowDatsmsTime = this.getCurrentMsTime() + daymsTime;
		var currentTime = this.longMsTimeConvertToDateTime(this
				.getCurrentMsTime());
		var termorrowTime = this
				.longMsTimeConvertToDateTime(tomorrowDatsmsTime);
		var nowDate = this.formatterDate2(new Date(currentTime));
		var tomorrowDate = this.formatterDate2(new Date(termorrowTime));
		var obj = {
			startTime : nowDate,
			endTime : tomorrowDate
		};
		return obj;
	},
	getGMTTime : function(date) { // 将CST时间转换为GMT时间，否则时间会有误差
		var index = (date.toString()).indexOf("CST");
		if (index > 0) {
			// 将CST时间转换为GMT时间，否则时间会有误差
			var dateStr = date.split(" ");
			var strGMT = dateStr[0] + " " + dateStr[1] + " " + dateStr[2] + " " + dateStr[5] + " " + dateStr[3] + " GMT+0800";
			return new Date(Date.parse(strGMT));
		} else {
			return new Date(date);
		}
	}
};