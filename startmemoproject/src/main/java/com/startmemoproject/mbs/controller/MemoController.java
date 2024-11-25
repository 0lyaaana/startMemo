package com.startmemoproject.mbs.controller;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.startmemoproject.mbs.domain.Memo;
import com.startmemoproject.mbs.service.MemoService;

import jakarta.servlet.http.HttpServletResponse;

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
	
	@GetMapping("/writeForm")
	public String addMemo() {
		return "views/writeForm";
	}
	
	@PostMapping("/addMemo")
	public String addMemo(Memo memo) {
		memoService.addMemo(memo);
		return "redirect:memoList";
	}
	
	@PostMapping("/updateForm")
	public String updateMemo(Model model, HttpServletResponse resp, PrintWriter out, @RequestParam("no") int no, @RequestParam("pass") String pass) {
		
		boolean isPassCheck = memoService.isPassCheck(no, pass);
		if(! isPassCheck) {
			resp.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("비밀번호를 다시 입력해주세요");
			out.println("history.back()");
			out.println("</script>");
			
			return null;
		}
		
		Memo memo = memoService.getMemo(no);
		model.addAttribute("memo", memo);
		
		return "views/updateForm";
		
	}
	
	@PostMapping("/update")
	public String updatememo(Model model, Memo memo, @RequestParam("no") int no, @RequestParam("pass") String pass, HttpServletResponse resp, PrintWriter out) {
		
		boolean isPassCheck = memoService.isPassCheck(memo.getNo(), memo.getPass());

		if(! isPassCheck) {
			
			resp.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("올바른 비밀번호 입력 바람");
			out.println("history.beck()");
			out.println("</script>");
			
			return null;
		}
		
		memoService.updateMemo(memo);
		
		return "redirect:memoList";
	}

}
