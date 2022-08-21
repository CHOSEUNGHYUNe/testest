package com.example.board.model;

import java.time.LocalDateTime;

public class Board {
	private Long id;  // 게시글의 시퀀스
	private String title;  // 제목
	private String content;  // 내용
	private Long hit;  // 조회수
	private LocalDateTime input_date;  //작성일
	private String name;  //작성자
	private String password; //패스워드
	
	public void addHit() {
		hit += 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}

	public LocalDateTime getInput_date() {
		return input_date;
	}

	public void setInput_date(LocalDateTime input_date) {
		this.input_date = input_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", title=" + title + ", content=" + content + ", hit=" + hit + ", input_date="
				+ input_date + ", name=" + name + ", password=" + password + "]";
	}
	
}
