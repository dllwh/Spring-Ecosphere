package org.dllwh.entity;

import java.util.LinkedList;
import java.util.List;

import org.dllwh.entity.server.CpuInfo;
import org.dllwh.entity.server.JvmInfo;
import org.dllwh.entity.server.MemInfo;
import org.dllwh.entity.server.SysInfo;
import org.dllwh.entity.server.SysFileInfo;

import lombok.Data;

@Data
public class ServerInfo {
	/** CPU相关信息 */
	private CpuInfo				cpu;
	/** 內存相关信息 */
	private MemInfo				mem;
	/** JVM相关信息 */
	private JvmInfo				jvm;
	/** 服务器相关信息 */
	private SysInfo				sys;
	/** 磁盘相关信息 */
	private List<SysFileInfo>	sysFiles	= new LinkedList<SysFileInfo>();
}