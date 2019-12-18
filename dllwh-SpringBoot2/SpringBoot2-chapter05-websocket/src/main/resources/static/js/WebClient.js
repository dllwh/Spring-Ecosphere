function WebClient() {
}
var WebClient = {
    userAgent : (function() {
	    return navigator.userAgent;
    })(),
    platform : (function() {
	    return navigator.platform;
    })(),
    appName : (function() {
	    return navigator.appName;
    })(),
    // 检测浏览器版本
    browser : (function() {
	    var agent = navigator.userAgent.toLowerCase();
	    var arr = [];
	    var Browser = "";
	    var Bversion = "";
	    var verinNum = "";
	    // firefox
	    if (agent.indexOf("firefox") > 0) {
		    var regStr_ff = /firefox\/[\d.]+/gi;
		    Browser = "FireFox";
		    Bversion = "" + agent.match(regStr_ff);
	    }
	    // Opera
	    else if (agent.indexOf("opera") >= 0) {
		    var regStr_opera = /version\/[\d.]+/gi;
		    Browser = "Opera";
		    Bversion = "" + agent.match(regStr_opera);
	    }
	    // 猎豹
	    else if (agent.indexOf("lbbrowser") >= 0) {
		    var regStr_opera = /lbbrowser\/[\d.]+/gi;
		    Browser = "liebao";
		    Bversion = "" + agent.match(regStr_opera);
	    }
	    // ucweb
	    else if (agent.indexOf("ucweb") >= 0) {
		    var regStr_opera = /ucweb\/[\d.]+/gi;
		    Browser = "ucweb";
		    Bversion = "" + agent.match(regStr_opera);
	    }
	    // 360se
	    else if (agent.indexOf("360se") >= 0) {
		    var regStr_opera = /360se\/[\d.]+/gi;
		    Browser = "360se";
		    Bversion = "" + agent.match(regStr_opera);
	    }
	    // baidubrowser
	    else if (agent.indexOf("baidubrowser") >= 0) {
		    var regStr_opera = /baidubrowser\/[\d.]+/gi;
		    Browser = "baidubrowser";
		    Bversion = "" + agent.match(regStr_opera);
	    }
	    // 搜狗
	    else if (agent.indexOf("metasr") >= 0) {
		    var regStr_opera = /metasr\/[\d.]+/gi;
		    Browser = "sougou";
		    Bversion = "" + agent.match(regStr_opera);
	    }
	    // qqbrowser
	    else if (agent.indexOf("qqbrowser") >= 0) {
		    var regStr_opera = /qqbrowser\/[\d.]+/gi;
		    Browser = "qqbrowser";
		    Bversion = "" + agent.match(regStr_opera);
	    }
	    // Maxthon
	    else if (agent.indexOf("maxthon") >= 0) {
		    var regStr_opera = /maxthon\/[\d.]+/gi;
		    Browser = "maxthon";
		    Bversion = "" + agent.match(regStr_opera);
	    }
	    // IE
	    else if (agent.indexOf("msie") > 0) {
		    var regStr_ie = /msie [\d.]+;/gi;
		    Browser = "IE";
		    Bversion = "" + agent.match(regStr_ie)
	    }
	    // Chrome
	    else if (agent.indexOf("chrome") > 0 && agent.indexOf("safari") > 0) {
		    var regStr_chrome = /chrome\/[\d.]+/gi;
		    Browser = "Chrome";
		    Bversion = "" + agent.match(regStr_chrome);
	    }
	    // Safari
	    else if (agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0) {
		    var regStr_saf = /safari\/[\d.]+/gi;
		    Browser = "Safari";
		    Bversion = "" + agent.match(regStr_saf);
	    }

	    else {
		    var browser = navigator.appName;
	    }
	    verinNum = (Bversion + "").replace(/[^0-9.]/ig, "");
	    arr.push(Browser);
	    arr.push(verinNum);
	    return arr;
    })(),
    // 检测是否是XX浏览器
    WB : (function() {
	    var UserAgent = navigator.userAgent.toLowerCase();
	    return {
	        isIE6 : /msie 6.0/.test(UserAgent), // IE6
	        isIE7 : /msie 7.0/.test(UserAgent), // IE7
	        isIE8 : /msie 8.0/.test(UserAgent), // IE8
	        isIE9 : /msie 9.0/.test(UserAgent), // IE9
	        isIE10 : /msie 10.0/.test(UserAgent), // IE10
	        isIE11 : /msie 11.0/.test(UserAgent), // IE11
	        isLB : /lbbrowser/.test(UserAgent), // 猎豹浏览器
	        isUc : /ucweb/.test(UserAgent), // UC浏览器
	        is360 : /360se/.test(UserAgent), // 360浏览器
	        isBaidu : /bidubrowser/.test(UserAgent), // 百度浏览器
	        isSougou : /metasr/.test(UserAgent), // 搜狗浏览器
	        isChrome : /chrome/.test(UserAgent.substr(-33, 6)), // Chrome浏览器
	        isFirefox : /firefox/.test(UserAgent), // 火狐浏览器
	        isOpera : /opera/.test(UserAgent), // Opera浏览器
	        isSafire : /safari/.test(UserAgent) && !/chrome/.test(UserAgent), // safire浏览器
	        isQQ : /qqbrowser/.test(UserAgent)
	    // qq浏览器
	    };
    })(),
    // 检测当前操作系统
    sys : (function() {
	    var system = {
	        win : false,
	        mac : false,
	        xll : false,
	        iphone : false,
	        ipoad : false,
	        ipad : false,
	        ios : false,
	        android : false,
	        nokiaN : false,
	        winMobile : false,
	        wii : false,
	        ps : false
	    };
	    var OS = "未知";
	    var ver = "";
	    var ua = navigator.userAgent;
	    // 检测平台
	    var p = navigator.platform;
	    system.win = p.indexOf('Win') == 0;
	    system.mac = p.indexOf('Mac') == 0;
	    system.xll = (p.indexOf('Xll') == 0 || p.indexOf('Linux') == 0);
	    // 检测Windows操作系统
	    if (system.win) {
		    if (/Win(?:dows )?([^do]{2})\s?(\d+\.\d+)?/.test(ua)) {
			    OS = "Windows";
			    if (RegExp['$1'] == 'NT') {
				    switch (RegExp['$2']) {
				    case '5.0':
					    system.win = '2000';
					    ver = "2000";
					    break;
				    case '5.1':
					    system.win = 'XP';
					    ver = "XP";
					    break;
				    case '6.0':
					    system.win = 'Vista';
					    ver = "Vista";
					    break;
				    case '6.1':
					    system.win = '7';
					    ver = "7";
					    break;
				    case '6.2':
					    system.win = '8';
					    ver = "8";
					    break;
				    default:
					    system.win = 'NT';
					    ver = "NT";
					    break;
				    }
			    } else if (RegExp['$1'] == '9x') {
				    system.win = 'ME';
				    ver = "ME";
			    } else {
				    system.win = RegExp['$1'];
				    ver = RegExp['$1'];
			    }
		    }
	    }
	    if (system.mac) {
		    OS = "Mac";
		    var regStr_mac = /mac\sos\s[a-z]+\s[\d_]+[\d]+/gi;
		    if (regStr_mac.test(ua)) {
			    ver = ua.match(regStr_mac);
			    ver = (ver + "").replace(/[^(os\s)0-9_]/ig, "");
		    }
	    }
	    // 移动设备
	    system.iphone = ua.indexOf('iPhone') > -1;
	    system.ipod = ua.indexOf('iPod') > -1;
	    system.ipad = ua.indexOf('iPad') > -1;
	    system.nokiaN = ua.indexOf('nokiaN') > -1;
	    // windows mobile
	    if (system.win == 'CE') {
		    system.winMobile = system.win;
	    } else if (system.win == 'Ph') {
		    if (/Windows Phone OS (\d+.\d)/i.test(ua)) {
			    system.win = 'Phone';
			    system.winMobile = parseFloat(RegExp['$1']);
			    OS = "Windows Phone OS";
			    ver = parseFloat(RegExp['$1']);
		    }
	    }
	    // 检测IOS版本
	    if (system.mac && ua.indexOf('Mobile') > -1) {
		    OS = "ios";
		    if (/CPU (?:iPhone )?OS (\d+_\d+)/i.test(ua)) {
			    system.ios = parseFloat(RegExp['$1'].replace('_', '.'));
			    ver = parseFloat(RegExp['$1'].replace('_', '.'));
		    } else {
			    system.ios = 2; // 不能真正检测出来，所以只能猜测
			    ver = "2";
		    }
	    }
	    // 检测Android版本
	    if (/Android (\d+\.\d+)/i.test(ua)) {
		    system.android = parseFloat(RegExp['$1']);
		    OS = "Android";
		    ver = parseFloat(RegExp['$1']);
	    }
	    // 游戏系统
	    system.wii = ua.indexOf('Wii') > -1;
	    system.ps = /PlayStation/i.test(ua);
	    return {
	        system : system,
	        OS : OS,
	        ver : ver
	    }
    })()
}
function getClientInfo() {
	var info = "\"userAgent\":\"" + WebClient.userAgent + "\",\"platform\":\""
	        + WebClient.platform + "\",\"appName\":\"" + WebClient.appName
	        + "\",\"browser\":\"" + WebClient.browser[0]
	        + "\",\"browser_version\":\"" + WebClient.browser[1]
	        + "\",\"OS\":\"" + WebClient.sys.OS + "\",\"OS_version\":\""
	        + WebClient.sys.ver + "\"";
	return info;
}