package org.dllwh.controller;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import org.dllwh.entity.server.SysInfo;
import org.dllwh.util.JavaMonitorHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JavaMonitorViewController {
	@Value("${info.version}")
	private String projectVersion;
	@RequestMapping(value = { "", "/" })
	public String index(ModelMap model) {
		model.addAttribute("jpsList", JavaMonitorHelper.getJps());
		return "index";
	}

	@RequestMapping(value = "main")
	public String main(ModelMap model) {
		model.addAttribute("projectVersion", projectVersion);
		model.addAttribute("jps", JavaMonitorHelper.getJps());
		model.addAttribute("version", JavaMonitorHelper.getJavaVersion());
		OperatingSystemMXBean operatingSystem = ManagementFactory.getOperatingSystemMXBean();
		SysInfo sysInfo = new SysInfo();
		sysInfo.setSystemName(operatingSystem.getName());
		sysInfo.setOsArch(operatingSystem.getArch());
		sysInfo.setAvailableProcessors(String.valueOf(operatingSystem.getAvailableProcessors()));
		sysInfo.setOsVersion(operatingSystem.getVersion());
		model.put("sysInfo", sysInfo);
		return "main";
	}

	@RequestMapping(value = "monitor")
	public String monitor() {
		return "monitor";
	}
}