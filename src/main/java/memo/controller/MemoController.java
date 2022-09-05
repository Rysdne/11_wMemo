package memo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import list.vo.ListVO;
import memo.service.MemoServiceInter;

@Controller
public class MemoController {

	@Autowired
	MemoServiceInter service;

	@RequestMapping("/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("content", "/memo/create.jsp");
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/createProc")
	public ModelAndView createProc(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();

		if (service.createProc(req) == true) {
			mv.addObject("content", "/memo/createSuccess.jsp");
		} else {
			mv.addObject("content", "/memo/createFailed.jsp");
		}
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/findOne")
	public ModelAndView findOne(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("findOne",service.findOne(req));
		mv.addObject("content","/memo/findOne.jsp");
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/updateProc")
	public ModelAndView updateProc(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();

		if (service.updateProc(req) == true) {
			mv.addObject("content", "/memo/updateSuccess.jsp");
		} else {
			mv.addObject("content", "/memo/updateFailed.jsp");
		}
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/deleteProc")
	public ModelAndView deleteProc(String idx, String id, String preSubfolder) {
		ModelAndView mv = new ModelAndView();
		
		if (service.deleteProc(idx, id, preSubfolder) == true) {
			mv.addObject("content", "/memo/deleteSuccess.jsp");
		} else {
			mv.addObject("content", "/memo/deleteFailed.jsp");
		}
		mv.setViewName("index");
		return mv;
	}
	
}
