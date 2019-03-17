package org.dllwh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.dllwh.core.cmd.ExecuteCmd;
import org.dllwh.core.entity.JpsEntity;

import com.google.common.collect.Lists;

public final class JavaMonitorHelper {

	/**
	 * @方法描述 : jps主要用来输出JVM中运行的进程状态信息
	 */
	public static List<JpsEntity> getJps() {
		String s = ExecuteCmd.execute(new String[] { "jps", "-l", "-v" });
		String[] line = s != null ? s.split("\n") : new String[0];
		List<JpsEntity> jpsInfoList = Lists.newArrayList();
		for (String aLine : line) {
			String[] one = aLine.split("\\s+");
			// 排除sun.tools进程
			if (one[1].contains("sun.tools")) {
				continue;
			}
			String pid = one[0];
			String className = "";
			String smallName = "";
			List<String> parameters = null;
			// 格式化控制台输出
			if (!one[1].substring(0, 1).equals("-")) {
				className = one[1];
				smallName = className.contains(".") ? className.substring(className.lastIndexOf(".") + 1)
						: className;
				parameters = Arrays.stream(one).skip(2).collect(Collectors.toList());
			} else {
				parameters = Arrays.stream(one).skip(1).collect(Collectors.toList());
			}
			jpsInfoList.add(new JpsEntity(pid, className, smallName, parameters));
		}
		return jpsInfoList;
	}

	public static String getJavaVersion() {
		StringBuilder sb = null;
		try {
			Process p = Runtime.getRuntime().exec(new String[] { "java", "-version" });
			InputStreamReader inputStreamReader = new InputStreamReader(p.getErrorStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			bufferedReader.close();
			p.destroy();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static int appearNumber(String srcText, String findText) {
		int count = 0;
		Pattern p = Pattern.compile(findText);
		Matcher m = p.matcher(srcText);
		while (m.find()) {
			count++;
		}
		return count;
	}

	public void executeInternal() {
		List<JpsEntity> jps = getJps();
		String prefix = "java.lang.Thread.State: ";
		for (JpsEntity jpsEntity : jps) {
			String s = ExecuteCmd.execute(new String[] { "jstack", jpsEntity.getPid() });
			int total = appearNumber(s, "nid=");
			int runnable = appearNumber(s, prefix + "RUNNABLE");
			int timed_waiting = appearNumber(s, prefix + "TIMED_WAITING");
			int waiting = appearNumber(s, prefix + "WAITING");

			System.out.println("total:" + total);
			System.out.println("runnable:" + runnable);
			System.out.println("timed_waiting:" + timed_waiting);
			System.out.println("waiting:" + waiting);
		}
	}
}