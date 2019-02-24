package org.dllwh.cloud.feign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // 生成一个无参数的构造方法
public class User {
	private String	userName;
	private String	age;
}