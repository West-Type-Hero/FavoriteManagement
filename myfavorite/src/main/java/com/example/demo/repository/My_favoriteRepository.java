package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.My_favorite;

@Repository
public interface My_favoriteRepository extends JpaRepository<My_favorite, Long>,JpaSpecificationExecutor<My_favorite>,SearchCustom{
	
}