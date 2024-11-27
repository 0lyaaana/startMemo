package com.startmemoproject.mbs.controller;

import java.io.PrintWriter;
import java.util.Map;

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
	
	@GetMapping({"/", "/memoList"})      // value : 요청 파라미터 | required : 필수 조건 | 기본 파라미터
	public String memoList(Model model, @RequestParam(value="pageNum", required = false, defaultValue="1") int pageNum) {
		
		Map<String,Object> modelMap = memoService.memoList(pageNum);
		
		model.addAllAttributes(modelMap);
		
		return "views/memoList";
	}
	
	@GetMapping("/memoDetail")
	public String getMemo(Model model, @RequestParam("no") int no, 
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		
		// true는 메모를 조회하면서 조회 횟수를 1 증가시키는 옵션
		Memo memo = memoService.getMemo(no, true);
		
		model.addAttribute("memo", memo);
		model.addAttribute("pageNum", pageNum);
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
	public String updateMemo(Model model, HttpServletResponse resp, PrintWriter out, @RequestParam("no") int no, 
			@RequestParam("pass") String pass, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) throws Exception {
		
		boolean isPassCheck = memoService.isPassCheck(no, pass);
		if(!isPassCheck) {
			resp.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("alert('비밀번호를 다시 입력해주세요');");
			out.println("location.href='/memoDetail?no=" + no + "';");
			out.println("</script>");
			
			return null;
		}
		
		Memo memo = memoService.getMemo(no, false);
		
		model.addAttribute("memo", memo);
		model.addAttribute("pageNum", pageNum);
		
		return "views/updateForm";
		
		
	}
	
	@PostMapping("/update")
	public String updateMemo(Memo memo, @RequestParam("no") int no, @RequestParam("pass") String pass, HttpServletResponse resp, PrintWriter out) {
		
		boolean isPassCheck = memoService.isPassCheck(memo.getNo(), memo.getPass());

		if(! isPassCheck) {
			
			resp.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("alert('올바른 비밀번호 입력 바람');");
			out.println("history.beck()");
			out.println("</script>");
			
			return null;
		}
		
		memoService.updateMemo(memo);
		
		return "redirect:memoList";
	}
	
	
	@PostMapping("/delete")
	public String deleteMemo(@RequestParam("no") int no, @RequestParam("pass") String pass, HttpServletResponse resp, PrintWriter out) {
		
		boolean isPassCheck = memoService.isPassCheck(no, pass);
		
		if(! isPassCheck) {
			
			resp.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("alert('올바른 비밀번호 입력 바람');");
			out.println("history.beck()");
			out.println("</script>");
			
			return null;
		}
		
		memoService.deleteMemo(no);
		return "redirect:memoList";
	}
	



}
