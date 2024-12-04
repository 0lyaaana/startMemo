package com.startmemoproject.mbs.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private String uniqueName;
	private String userName;
	private String pass;
	private String email;
	private String mobile;
	private String zipcode;
	private String address1;
	private String address2;
	private String phone;
	private String emailGet;
	private Timestamp regDate;

}
