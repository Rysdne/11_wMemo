package home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import home.service.HomeServiceInter;

@Controller
public class HomeController {

	@Autowired
	HomeServiceInter service;

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest req) {


		service.wTableCheck();

		ModelAndView mv = new ModelAndView();
		if (req.getSession().getAttribute("id") == null) {
			mv.addObject("content", "login/login.jsp");
		} else {
			mv.addObject("content", "home/main.jsp");
		}
		mv.setViewName("index");
		return mv;
	}
}
