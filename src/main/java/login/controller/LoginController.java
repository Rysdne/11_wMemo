package login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import login.service.LoginServiceInter;
import login.vo.LoginVO;

@Controller
public class LoginController {

	@Autowired
	LoginServiceInter service;

	// ===============================================================

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("content", "login/login.jsp");
		mv.setViewName("index");

		return mv;
	}
	// ...............................................................

	@RequestMapping("/loginProc")
	public ModelAndView loginProc(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		boolean result = service.login(req);
		if (result) {
			System.out.println("로그인 성공");
			mv.addObject("content", "home/main.jsp");
		} else {
			System.out.println("로그인 실패");
			mv.addObject("content", "login/loginFailed.jsp");
		}
		mv.setViewName("index");		
		return mv;
	}
	// ...............................................................

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest req) {
		req.getSession().invalidate();
		System.out.println("로그아웃");
		ModelAndView mv = new ModelAndView();
		mv.addObject("content", "login/login.jsp");
		mv.setViewName("index");
		return mv;
	}

	// ===============================================================

	@RequestMapping("/findId")
	public ModelAndView findId() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("content", "login/findId.jsp");
		mv.setViewName("index");
		return mv;
	}
	// ...............................................................

	@RequestMapping("/findIdProc")
	public ModelAndView findIdProc(String email) {
		ModelAndView mv = new ModelAndView();
		if (service.findId(email).getId() != "") {
			mv.addObject("result", service.findId(email));
			mv.addObject("content", "login/findIdResult.jsp");
		} else {
			mv.addObject("content", "login/findFailed.jsp");
		}
		mv.setViewName("index");
		return mv;
	}

	// ===============================================================

	@RequestMapping("/findPw")
	public ModelAndView findPw() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("content", "login/findPw.jsp");
		mv.setViewName("index");
		return mv;
	}
	// ...............................................................

	@RequestMapping("/findPwProc")
	public ModelAndView findPwProc(String id, String email) {
		ModelAndView mv = new ModelAndView();
		if (service.findPw(id, email).getId() != "") {
			mv.addObject("result", service.findPw(id, email));
			mv.addObject("content", "login/pwReset.jsp");
		} else {
			mv.addObject("content", "login/findFailed.jsp");
		}
		mv.setViewName("index");
		return mv;
	}

	// ...............................................................

	@RequestMapping("/pwResetProc")
	public String pwResetProc(String id, String password) {
		int result = service.pwReset(id, password);
		return "redirect:/login/pwResetSuccess";
	}
	// ...............................................................

	@RequestMapping("/pwResetSuccess")
	public ModelAndView pwResetResult() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("content", "login/pwResetSuccess.jsp");
		mv.setViewName("index");
		return mv;
	}

	// ===============================================================

	@RequestMapping("/regId")
	public ModelAndView regId() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("content", "login/regId.jsp");
		mv.setViewName("index");
		return mv;
	}
	// ...............................................................

	@RequestMapping("/regIdProc")
	public ModelAndView regIdProc(String id, String password, String email) {
		ModelAndView mv = new ModelAndView();
		if (service.regId(id, password, email) != 0) {
			int result = service.regId(id, password, email);
			mv.addObject("content", "login/regIdSuccess.jsp");
		} else {
			mv.addObject("content", "login/regIdFailed.jsp");
		}
		mv.setViewName("index");
		return mv;
	}

	// ===============================================================
}
