package com.jwtspringsecurity.jwtssapp.request;

import lombok.Data;

@Data
public class UserRequest {
	private String username;
	private String password;
	private String name;
}
