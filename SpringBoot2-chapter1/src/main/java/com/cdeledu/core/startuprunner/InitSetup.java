package com.cdeledu.core.startuprunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 让服务器启动或停止时自动执行代码
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月10日 下午2:26:12
 * @版本: V1.0
 * @since: JDK 1.8
 */
@WebListener
@Slf4j
public class InitSetup implements ServletContextListener {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 停止时执行的方法
	 */
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		sc.removeAttribute("syspath");
		log.info(sdf.format(new Date()) + "--->服务器容器销毁成功");

	}

	/**
	 * 启动时执行方法
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		try {
			sc.setAttribute("syspath", sc.getContextPath());
			log.info("" + sdf.format(new Date()) + "--->业务平台路径:" + sc.getContextPath());
			log.info(sdf.format(new Date()) + "--->进入类加载");
			log.info(sdf.format(new Date()) + "--->装载配置文件");
		} catch (Exception e) {
			log.error("启动容器服务出现异常{0}", e.getMessage());
		}
		log.info(sdf.format(new Date()) + "--->结束类加载");
	}
}