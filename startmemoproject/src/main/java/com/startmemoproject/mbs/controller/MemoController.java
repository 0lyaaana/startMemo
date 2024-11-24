package com.startmemoproject.mbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/memoDetail")
	public String getMemo(Model model, @RequestParam("no") int no) {
		model.addAttribute("memo", memoService.getMemo(no));
		return "views/memoDetail";
	}

}
