package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌, 즉 IoC를 해줌
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {  //title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}	
	
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
}
