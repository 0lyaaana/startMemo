package com.startmemoproject.mbs.service;

import java.util.List;

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
	
	// 메모 리스트 가져오기
	public List<Memo> memoList(){
		log.info("MemoService: memoList()");
		return memoMapper.memoList();
	}
	
	// 메모 상세보기
	public Memo getMemo(int no) {
		log.info("MemoService: getMemo(int no)");
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
	
	

}
