package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.My_favorite;

public interface SearchCustom {

	// SELECT * FROM MY_FAVORITE WHERE ID LIKE '%(String id)%'; （部分一致）
	List<My_favorite> findByIdIs(int id);

	// SELECT * FROM MY_fAVORITE WHERE TITLE LIKE '%(String title)%';（部分一致）
	List<My_favorite> findByTitleContaining(String title);

	// SELECT * FROM MY_FAVORITE WHERE GRPOUPING ='(String select)';（完全一致）
	List<My_favorite> findByGroupingIs(String select);

	// SELECT * FORM MU_FAVORITE WHERE POINTS BETWEEN (String min) AND (String max);（範囲検索）
	List<My_favorite> findByPointsBetween(int min, int max);

	@Query(nativeQuery = true, value = "SELECT MAX(ID) FROM MY_FAVORITE;")
	int maxId();
}