package com.startmemoproject.mbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.startmemoproject.mbs.service.MemoService;

@Controller
public class MemoController {
	
	@Autowired
	private MemoService memoService;
	
	@GetMapping({"/", "/memoList"})
	public String memoList(Model model) {
		model.addAttribute("mList", memoService.memoList());
		return "views/memoList";
	}

}
