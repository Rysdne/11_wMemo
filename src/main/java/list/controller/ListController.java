package list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import list.service.ListServiceInter;

@Controller
public class ListController {
	
	@Autowired
	ListServiceInter service;

	// ===============================================================
	
	@RequestMapping("/listOption")
	public ModelAndView listOption() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("listOption");
		return mv;
	}

	@RequestMapping("/listAll")
	public ModelAndView listAll(String id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("memoList",service.listAll(id));
		mv.setViewName("list");
		return mv;
	}

	@RequestMapping("/listMonth")
	public ModelAndView listMonth(String id, String idxMonth) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("memoList",service.listMonth(id, idxMonth));
		mv.setViewName("list");
		return mv;
	}

	// ===============================================================
	
}
