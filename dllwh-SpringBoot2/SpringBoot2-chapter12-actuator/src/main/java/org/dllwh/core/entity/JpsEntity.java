package org.dllwh.core.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JpsEntity {
	private String			pid;
	private String			className;
	private String			smallName;
	private List<String>	parameters;
}