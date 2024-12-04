package com.startmemoproject.mbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.startmemoproject.mbs.domain.Memo;

@Mapper
public interface MemoMapper {
	
	// 메모 리스트 가져오기
	// 맵퍼 XML의 맵핑 구문을 호출하면서 전달해야 할 파라미터가 여러 개일 때 @Param("파라미터 이름") 애노테이션을 사용
	public List<Memo> memoList(@Param("startRow") int startRow, @Param("num") int num,
			@Param("type") String type, @Param("keyword") String keyword);
	
	// 메모 상세보기
	public Memo getMemo(int no);
	
	// 메모 작성하가ㅣ
	public void insertMemo(Memo memo);
	
	// 비밀번호 체크하기
	public String isPassCheck(int no);
	
	// 메모 수정하기
	public void updateMemo(Memo memo);
	
	// 메모 삭제하기
	public void deleteMemo(int no);
	
	// 전체 메모 수 반환하기
	public int getMemoCount(@Param("type") String type, @Param("keyword") String keyword);
	
	public void memoReadCount(int no);
}
