package org.dllwh.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor // 用在类上,全参构造器
@Data // 注解在类上，相当于同时使用了@ToString、@EqualsAndHashCode、@Getter、@Setter和@RequiredArgsConstrutor这些注解，如为final属性，则不会为该属性生成setter方法
public class UserEntity implements Serializable {
	private static final long	serialVersionUID	= 1L;
	@NonNull // 该注解用在属性或构造器上，可用于校验参数，能帮助避免空指针
	private String				userId;
	private String				username;
	private String				password;
}
