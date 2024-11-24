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

}
