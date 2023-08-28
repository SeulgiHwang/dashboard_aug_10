package com.dashboard.board.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dashboard.board.dao.BoardDAO;
import com.dashboard.board.domain.Board;

@Controller
public class BoardController {
	
BoardDAO boardDAO = new BoardDAO();
/**
 * 게시판만들기
 * 게시판목록	화면	list	get	
 * 게시판쓰기	화면	insert	get
 * 게시판쓰기	처리	insert	post
 * 게시판읽기	화면	read	get
 * 게시판수정	화면	update	get
 * 게시판수정	처리	update	post
 * 게시판삭제	처리	delete	post
 */

//게시판목록 화면
/*	전체 게시글 목록을 조회
 * 	게시글목록을 모델에 등록
 * 	/board/list.html뷰를 응답
 */
	@GetMapping("/board/list")
	public String list(Model model){
		List<Board> boardList = boardDAO.selectList();
		model.addAttribute("boardList",boardList);

		return "board/list";
	}
	
	@GetMapping("/board/insert")
	public String insert() {
		return "board/insert";
	}
	
	/**
	 * 게시글쓰기 -처리
	 * 제목/작성자/내용 을 파라미터로 전달받는다
	 * db에 게시글 등록처리
	 * 게시후 목록으로 redirect하기 /board/list.html뷰를 응답
	 * @param title
	 * @param writer
	 * @param content
	 * @return
	 */
	@PostMapping("/board/insert")
	public String insertform(@RequestParam("title") String title, 
							 @RequestParam("writer") String writer,
							 @RequestParam("content") String content) {
		//@RequestParam("파라미터명") 타입 매개변수명
		//:요청파라미터를 매개변수로 가져온다
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		int result=boardDAO.insert(board);
		
		System.out.println("title:"+title);
		System.out.println("writer:"+writer);
		System.out.println("content:"+content);
		
		return "redirect:/board/list";
	}
	/**
	 * 게시글 읽기
	 * -게시글 번호를 요청파라미터로 전달받음
	 * -게시글 번호로 게시글 정보조회
	 * -게시글 정보를 모델에 등록
	 * -/board/read.html뷰를 응답
	 * @param model
	 * @param boardNo
	 * @return
	 */
	@GetMapping("/board/read")
	public String read(Model model, int boardNo) {
		System.out.println("게시글조회");
		System.out.println("boardNo"+boardNo);
		
		//게시글 번호로 게시글 정보를 조회->모델에 담기
		Board board =boardDAO.select(boardNo);
		//조회한 게시글 정보를 추가
		model.addAttribute("boardNo",boardNo);		
		//게시글 불러오기
		return "board/read";
	}
	/**
	 * 게시글 수정 화면 
	 * -게시글 번호로 요청파라미터 전달받음
	 * -게시글 번호로 게시글 정보 조회
	 * -게시글정보를 모델에 등록
	 * -/board/update.html 뷰를 응답
	 * @param model
	 * @param boardNo
	 * @return
	 */
	@GetMapping("/board/update")
	//게시글 번호로 게시글 정보를 조회->모델에 담기
	public String update(Model model, int boardNo) {
		
		//조회한 게식ㄹ 정보를 모델에 추가
		model.addAttribute("boardNo",boardNo);
		System.out.println("boardNo: "+boardNo);
		return "board/update"; 

	}
	@PostMapping("/board/update")
	public String updateform(Board board) {
		//요청파라미터명과 일치하는
		//변수명을 가지고 있는 객체(Board파일에 정의해놓음)를 사용하여 
		//여러 파라미터를 가져올수 있다
		int boardNo 	= board.getBoardNo();
		String title	= board.getTitle();
		String writer	= board.getWriter();
		String content	= board.getContent();
				
		System.out.println("title: "+title);
		System.out.println("writer:"+writer);
		System.out.println("content:"+content);
		System.out.println("boardNo: "+boardNo);
				
		return "redirect:/board/read?boardNo=" + boardNo; //게시글수정후,10번
	}
	
	@PostMapping("/board/delete")
	public String delete(int boardNo) {
		System.out.println("boardNo:"+boardNo);
		return "redirect:/board/list";
	}

}
