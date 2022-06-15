package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.My_favoriteService;

@Controller
public class My_favoriteController {

	@Autowired
	My_favoriteService my_favoriteService;

	@GetMapping("my_favorite/list")
	public ModelAndView displayList(ModelAndView mav) {

		mav.setViewName("my_favoriteDataList");
		return mav;
	}

	// 一覧表示ボタンが押下時の処理
	@RequestMapping(value = "all", method = RequestMethod.POST)
	@ResponseBody
	public String all() {
		return my_favoriteService.searchAll();
	}

	// 検索ボタンが押下時の処理
	@RequestMapping(value = "scarch", method = RequestMethod.POST)
	@ResponseBody
	public String squeeze(
			// 入力された値をそれぞれの変数に代入
			@RequestParam(name = "id", defaultValue = "0") int id,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "select", defaultValue = "") String select,
			@RequestParam(name = "min", defaultValue = "0") int min,
			@RequestParam(name = "max", defaultValue = "0") int max) {
		return my_favoriteService.search(id, title, select, min, max);
	}

	// 登録ボタン押下時
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(
			@RequestParam(name = "points", defaultValue = "0") int points,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "select", defaultValue = "") String select,
			@RequestParam(name = "memo", defaultValue = "") String memo) {
		return my_favoriteService.saveing(points, title, select, memo);
	}
}