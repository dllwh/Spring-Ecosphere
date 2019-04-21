package org.dllwh.thymeleaf.extend.customtag;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: thymeleaf 自定义标签属性
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年4月3日 下午9:50:47
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Component
public class ThSysDialect extends AbstractProcessorDialect {
	// 定义方言名称
	private static final String DIALECT_NAME = "Sys Dialect";

	public ThSysDialect() {
		super(DIALECT_NAME, "thSys", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		return createStandardProcessorsSet(dialectPrefix);
	}

	private Set<IProcessor> createStandardProcessorsSet(String dialectPrefix) {
		LinkedHashSet<IProcessor> processors = new LinkedHashSet<IProcessor>();
		processors.add(new ThymeleafProcessor(dialectPrefix));
		processors.add(new PagingTagProcessor(dialectPrefix));
		return processors;
	}
}