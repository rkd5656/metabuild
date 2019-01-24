package com.mesim.bp.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView hello() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping(value = "/nav/view/misun-kim", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("introduce/misun");
		return mav;
	}
}
