package com.man.entity;

import lombok.Data;

@Data
public class AdminUser {
	private Integer id;
	private String username;
	private String nickname;
	private Integer roleId;
}
