package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.My_favorite;
import com.example.demo.repository.My_favoriteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class My_favoriteService {

	@Autowired
	My_favoriteRepository my_favoriteRepository;
	My_favorite my_favorite = new My_favorite();

	// 一覧表示
	public String searchAll() {
		List<My_favorite> list = my_favoriteRepository.findAll();
		if (list == null || list.size() == 0) {
			return null;
		}
		return getJson(list);
	}

	// 検索
	public String search(int id, String title, String select, int min, int max) {

		// からの箱を用意
		List<My_favorite> list = null;

		// idがnullと空文字ではない場合
		if (id != 0) {
			// listにidに格納された条件に合うデータを格納
			list = my_favoriteRepository.findByIdIs(id);

			// titleがnullと空文字ではない場合
		} else if (title != null && !title.equals("")) {
			// listにtitleに格納された条件に合うデータを格納
			list = my_favoriteRepository.findByTitleContaining(title);

			// selectがnullと空文字ではない場合
		} else if (select != null && !select.equals("")) {
			// listにselectに格納された条件に合うデータを格納
			list = my_favoriteRepository.findByGroupingIs(select);

			// min/maxがnullと空文字ではない場合
		} else if (min != 0 || max != 0) {
			// listにmin/maxに格納された条件に合うデータを格納
			list = my_favoriteRepository.findByPointsBetween(min, max);
		}
		if (list == null || list.size() == 0) {
			return null;
		}

		return getJson(list);
	}

	public String saveing(int points, String title, String select, String memo) {

		String resultP = "";

		if (my_favorite != null) {
			my_favorite.setId(0);
		}

		if (title != null && !title.equals("")) {
			my_favorite.setTitle(title);
		} else if (title.length() >= 31) {
			resultP = "<br>タイトルが長すぎます";
		} else {
			resultP = "<br>タイトルを入れてね！<br>　30文字まで入力できる最近のロングタイトルに対応してるぞ！";
		}

		if (select != null && !select.equals("")) {
			my_favorite.setGrouping(select);
		} else {
			resultP += "<br>ジャンルが選択されてません";
		}

		if (points != 0 && points <= 100) {
			my_favorite.setPoints(points);
		} else if (points >= 101) {
			resultP += "<br>100点以下で入力しください";
		} else {
			resultP += "<br>得点が入力されてません";
		}

		if (memo != null && !memo.equals("")) {
			my_favorite.setMemo(memo);
		} else {
			resultP += "<br>感想を入力してください";
		}

		if (!resultP.equals("")) {
			return resultP;
		}

		int id = my_favoriteRepository.maxId() + 1;
		my_favorite.setId(id);

		my_favoriteRepository.save(my_favorite);
		String resultT = "<tr><th>ID</th><td>" + id
				+ "</td><tr><th>タイトル</th><td>" + title
				+ "</td><tr><th>ジャンル</th><td>" + select
				+ "</td><tr><th>得点</th><td>" + points
				+ "</td><tr><th>感想</th><td>" + memo
				+ "</td></tr><tr>";
		return resultT;
	}

	private String getJson(List<My_favorite> list) {
		String str = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			str = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return str;
	}

}