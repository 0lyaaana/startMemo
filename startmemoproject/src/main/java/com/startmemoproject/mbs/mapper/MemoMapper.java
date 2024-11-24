package com.startmemoproject.mbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.startmemoproject.mbs.domain.Memo;

@Mapper
public interface MemoMapper {
	
	// 메모 리스트 가져오기
	public List<Memo> memoList();
	
	// 메모 상세보기
	public Memo getMemo(int no);
}
