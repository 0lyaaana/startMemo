package com.startmemoproject.mbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.startmemoproject.mbs.domain.Memo;

@Mapper
public interface MemoMapper {

	public List<Memo> memoList();
}
