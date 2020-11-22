package org.dllwh.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义操作日志注解
 * 
 */
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {
	/** 操作模块 */
	String operModel() default "";

	/** 操作类型，主要是select,insert,update,delete */
	String operType() default "";

	/** 操作说明,例如：XXX管理-xx操作 */
	String operDesc() default "";
}
