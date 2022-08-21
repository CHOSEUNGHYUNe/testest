package com.example.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.model.Board;
import com.example.board.repository.BoardRepository;

@Controller
public class BoardController {
	
	Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardRepository boardRepository;
	
	// 메인 페이지 이동
	@GetMapping("/")
	public String home() {
		log.info("메인 페이지 이동");
		return "index";
	}
	// 글쓰기 페이지 이동
	@GetMapping("/write")
	public String write() {
		return "/write";
	}
	// 글쓰기 - 바인딩을 해줘야함
	@PostMapping("/write")
	public String writeBoard(@ModelAttribute Board board) {
		log.info("board : {}", board);
		boardRepository.save(board);
		return "redirect:/list";
	}
	// 게시판 목록 페이지 이동, 모델은 속성 값을 부여
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", boardRepository.findAll());
		return "/list";
	}
	
	// 게시판 글 읽기
	@GetMapping("/read")
	public String read(@RequestParam Long id, Model model) {
		log.info("id : {}", id);
		Board findBoard = boardRepository.findById(id);
		findBoard.addHit();
//		boardRepository.updateBoard(findBoard);
		model.addAttribute("board", findBoard);
		return "/read";
	}
	// 글 수정 페이지 이동
	// PathVariable = 경로 받아오는 거
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model) {
		log.info("id : {}", id);
		model.addAttribute("board", boardRepository.findById(id));
		return "/update";
	}
	// 게시글 수정
	@PostMapping("/update/{id}")
	public String updateBoard(@PathVariable Long id, @ModelAttribute Board board) {
		log.info("id : {}", id);
		log.info("board : {}", board);
		Board findBoard = boardRepository.findById(board.getId());
		if(findBoard.getPassword().equals(board.getPassword())) {
			board.setHit(findBoard.getHit());
			board.setInput_date(findBoard.getInput_date());
			board.setName(findBoard.getName());
			boardRepository.updateBoard(board);
			log.info("수정 완료");
		}
		return "redirect:/list";
	}
	// 게시글 삭제
	@PostMapping("/remove")
	public String remove(@ModelAttribute Board board) {
		log.info("board : {}", board);
		Board findBoard = boardRepository.findById(board.getId());
		if(findBoard.getPassword().equals(board.getPassword())) {
			boardRepository.deleteById(board.getId());
		}
		return "redirect:/list";
	}
}
