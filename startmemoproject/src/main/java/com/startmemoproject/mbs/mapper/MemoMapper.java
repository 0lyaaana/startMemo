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
	
	// 메모 작성하가ㅣ
	public void insertMemo(Memo memo);
	
	// 비밀번호 체크하기
	public String isPassCheck(int no);
	
	public void updateMemo(Memo memo);
	
	public void deleteMemo(int no);
}
