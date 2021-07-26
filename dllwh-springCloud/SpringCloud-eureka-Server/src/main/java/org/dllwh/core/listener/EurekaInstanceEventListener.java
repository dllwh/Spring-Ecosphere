package org.dllwh.core.listener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EurekaInstanceEventListener {

	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	@EventListener
	public void listen(EurekaInstanceCanceledEvent event) {
		log.info(LocalDateTime.now().format(df) + " 服务下线事件:" + event.getAppName() + "---" + event.getServerId());
	}

	@EventListener
	public void listen(EurekaInstanceRegisteredEvent event) {
		InstanceInfo instanceInfo = event.getInstanceInfo();
		log.info(LocalDateTime.now().format(df) + " 服务上线事件:" + instanceInfo.getAppName() + "---"
				+ instanceInfo.getInstanceId());
	}

	@EventListener
	public void listen(EurekaInstanceRenewedEvent event) {
		log.info(LocalDateTime.now().format(df) + " 服务续约/心跳上报事件:" + event.getAppName() + "---" + event.getServerId());

	}

	@EventListener
	public void listen(EurekaRegistryAvailableEvent event) {
		log.info(LocalDateTime.now().format(df) + " 注册中心可用事件");
	}

	@EventListener
	public void listen(EurekaServerStartedEvent event) {
		log.info(LocalDateTime.now().format(df) + " 注册中心启动事件");

	}
}
