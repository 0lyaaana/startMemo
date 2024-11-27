package com.startmemoproject.mbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startmemoproject.mbs.domain.Memo;
import com.startmemoproject.mbs.mapper.MemoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemoService {
	
	@Autowired
	private MemoMapper memoMapper;
	
	// 한 페이지에 출력할 게시글 수
	private static final int PAGE_SIZE = 10;
	
	// 한 페이지에 출력할 페이지 그룹의 수를 상수로 선언
	private static final int PAGE_GROUP = 10;
	
	// 메모 리스트 가져오기
	public Map<String, Object> memoList(int pageNum){
		log.info("MemoService: memoList()");
		
		// 현재 페이지로 설정(요청 파라미터 : pageNum)
		int currentPage = pageNum;
		
		// 현재 페이지에 해당하는 게시글 리스트의 첫 번쨰 행의 값을 계산
		int startRow = (currentPage - 1) * PAGE_SIZE;
		
		// 전체 게시글의 수 받아보기
		int memoListCount = memoMapper.getMemoCount();
		
		// 현재 페이지에 해당하는 메모 리스트 불러오기
		List<Memo> memoList = memoMapper.memoList(startRow, PAGE_SIZE);
		
		// 전체 페이지 = 전체 메모 수 / 한 페이지에 표시할 메모 수. 이 계산식에서 나머지가 존재하면 전체 페이지 수는 전체 페이지 + 1이 됨.
		int pageCount = memoListCount / PAGE_SIZE + (memoListCount % PAGE_SIZE == 0 ? 0: 1);
		
		// 페이지 그룹 처리를 위해 페이지 그룹별 시작 페이지 계산
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1 - (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		// 페이지 그룹 처리를 위해 페이지 그룹별 마지막 페이지 계산
		int endPage = startPage + PAGE_GROUP - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		// View 페이지에서 필요한 데이터를 Map에 저장
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("mList", memoList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("memoListCount", memoListCount);
		modelMap.put("currentPage", currentPage);
		modelMap.put("pageGroup", PAGE_GROUP);
		
		
		return modelMap;
	}
	
	// 메모 상세보기
	public Memo getMemo(int no, boolean isCount) {
		log.info("MemoService: getMemo(int no)");
		
		if(isCount) {
			memoMapper.memoReadCount(no);
		}
		
		return memoMapper.getMemo(no);
	}
	
	// 메모 작성하기
	public void addMemo(Memo memo) {
		log.info("MemoService: addMemo(Memo memo)");
		memoMapper.insertMemo(memo);
	}
	
	// 수정 시, 비밀번호 체크하기
	public boolean isPassCheck(int no, String pass) {
		
		boolean result = false;
		
		String dbPass = memoMapper.isPassCheck(no);
		
		if(dbPass.equals(pass)) {
			result = true;
		}
		
		return result;
	}
	
	// 메모 수정하기
	public void updateMemo(Memo memo) {
		memoMapper.updateMemo(memo);
	}
	
	// 메모 삭제하기
	public void deleteMemo(int no) {
		memoMapper.deleteMemo(no);
	}
	
	// 전체 메모 수 반환하기
	public int getMemoCount() {
		return memoMapper.getMemoCount();
	}
	
	

}
