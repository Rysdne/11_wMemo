package record.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import record.service.RecordServiceInter;

@Controller
public class RecordController {
	
	@Autowired
	RecordServiceInter service;

	@RequestMapping("/recordOption")
	public ModelAndView recordOption() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("recordOption");
		return mv;
	}

	@RequestMapping("/record")
	public ModelAndView record() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("record");
		return mv;
	}
	
	@RequestMapping("/recordList")
	public ModelAndView recordList(String id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("recordList",service.recordList(id));
		mv.setViewName("recordList");
		return mv;
	}
	
	@RequestMapping("/recordListOne")
	public ModelAndView recordListOne(int ridx) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("recordListOne",service.recordListOne(ridx));
		mv.setViewName("recordListOne");
		return mv;
	}
	
	@RequestMapping("/recordSave")
	public ModelAndView recordSave(String id, String rtitle, String rtext) {
		ModelAndView mv = new ModelAndView();
		if (service.recordSave(id, rtitle, rtext) == true) {
			mv.setViewName("record");
		} else {
			mv.setViewName("recordFailed");	
		}
		return mv;
	}

	@RequestMapping("/recordDelete")
	public ModelAndView recordDelete(int ridx) {
		ModelAndView mv = new ModelAndView();
		if(service.recordDelete(ridx) == true) {
			mv.setViewName("record");
		} else {
			mv.setViewName("recordDeleteFailed");
		}
		return mv;
	}
	
}
