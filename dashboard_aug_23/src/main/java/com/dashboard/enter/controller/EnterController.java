package com.dashboard.enter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnterController {
	/**
	 * 입력하기
	 * 날짜		expenseDate
	 * 금액		expenseAmount
	 * 카테고리		expenseType
	 * 결제수단 	expenseMethod
	 * 내용		expenseNotes
	 */
	//지출목록 보기
	@GetMapping("enterExpense/expenseList")
	public String expenseList( ) {
		return "/enterExpense/expenseList";
	}
	
	//지출입력 보기
	@GetMapping("enterExpense/expense")
	public String expense( ) {
		return "/enterExpense/expense";
	}
	//지출입력 처리
	@PostMapping("enterExpense/expense")
	public String expenseform( ) {
		return "/enterExpense/expenseList";
	}
//	//지출입력 보기
//	@GetMapping("enterExpense/expenseDate")
//	public String expenseDate() {
//		return "/enterExpense/expense";
//	}
//	//지출입력 처리
//	@PostMapping("enterExpense/expenseDate")
//	public String expenseDateform() {
//		System.out.println("비용날짜입력");
//		return "redirect:/enterExpense/expense";
//	}
	
	//지출수정 보기
	
	//지출수정 처리
	//지출삭제 처리
	
	//카테고리 연결 화면
	@GetMapping("enterExpense/category")
	public String category() {
		System.out.println("카테고리");
		return "/enterExpense/category";
	}
	//카테고리 연결 처리
	@PostMapping("enterExpense/category")
	public String categoryform() {
		System.out.println("카테고리");
		return "/enterExpense/category";
	}
	//결제수단 연결 화면
	@GetMapping("enterExpense/expenseMethod")
	public String expenseMethod() {
		System.out.println("카테고리");
		return "/enterExpense/expenseMethod";
	}
	//결제수단 연결 처리
	@PostMapping("enterExpense/expenseMethod")
	public String expenseMethodform() {
		System.out.println("카테고리");
		return "/enterExpense/expenseMethod";
	}
}
