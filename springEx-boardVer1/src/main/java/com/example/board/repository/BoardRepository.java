package com.example.board.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.board.model.Board;

@Repository
public class BoardRepository {
	Logger log = LoggerFactory.getLogger(BoardRepository.class);
	List<Board> data = new ArrayList<>();
	Long id_seq = 0L;

	// 게시글 저장
	public void save(Board board) {
		board.setId(++id_seq);
		board.setHit(0L);
		board.setInput_date(LocalDateTime.now());
		data.add(board);
		
		//확인용 코드
//		for (Board b : data) {
//			log.info(b.toString());
//		}
	}
	// 게시글 읽기
	public Board findById(Long id) {
		for (Board board : data) {
			if (id == board.getId()) {
				return board;
			}
		}
		return null;
	}
	// 게시글 수정
	public void updateBoard(Board board) {
		for (Board boardData : data) {
			if (boardData.getId() == board.getId()) {
				data.remove(boardData);
				break;
			}
			data.add(board);
		}
	}
	// 게시글 삭제
	public void deleteById(Long id) {
		for (Board boardData : data) {
			if(boardData.getId() == id) {
				data.remove(boardData);
				break;
			}
		}
	}
	// 게시글 전체 조회
	public List<Board> findAll() {
		return data;
	}
}
