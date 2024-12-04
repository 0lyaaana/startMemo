package com.startmemoproject.mbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.startmemoproject.mbs.domain.User;

@Mapper
public interface UserMapper {
	
	//멤버 불러오기
	public List<User> userList(String uniqueName);
}
