package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "MY_FAVORITE")
public class My_favorite {
	@Id
	@Column(name = "ID")
	private int id;
	@Column(name = "GROUPING")
	private String grouping;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "POINTS")
	private int points;
	@Column(name = "MEMO")
	private String memo;
}