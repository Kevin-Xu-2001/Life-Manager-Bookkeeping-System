package com.ultratechnology.life_manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ultratechnology.life_manager.entity.Money;
import com.ultratechnology.life_manager.service.IMoneyService;
import com.ultratechnology.life_manager.util.ResponseResult;

@RestController
@RequestMapping("/money")
public class MoneyController extends BaseController{

	@Autowired
	private IMoneyService moneyService;
	
	@PostMapping("/add.do")
	public ResponseResult<Void> handleAdd(Money money,HttpSession session){
	    //Get Uid from Session
		Integer uid = getUidFromSession(session);
		System.out.println(uid);
		money.setUid(uid);
		Money result = moneyService.createLine(money);
		System.out.println("The return money is:"+result);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	
	@GetMapping("/getAllList.do")
	public ResponseResult<List<List<Money>>> handleGetAllList(HttpSession session){
		//Get Uid from Session
		Integer uid = getUidFromSession(session);
		//Find all data sorted by the date consumed of one User
		List<List<Money>> allResult = moneyService.findSortedByDateConsumed(uid);
		for (List<Money> list : allResult) {
			System.out.println(list);
		}
		return new ResponseResult<List<List<Money>>>(SUCCESS,allResult);
	}
	
	
	@PostMapping("/delete.do")
	public ResponseResult<Void> handleDelete(Integer id){
		System.out.println("MoneyController: get into HandleDelete function");
		Integer affected = moneyService.deleteUnused(id);
		if (affected==1) {
		return new ResponseResult<Void>(SUCCESS);
		}
		return new ResponseResult<Void>(0);
	}
	
	@GetMapping("/dayAnalysis.do")
	public ResponseResult<List<Money>> handleDayAnalysis(String selectDate,HttpSession session){
		//Get Uid from Session
		Integer uid = getUidFromSession(session);
		List<Money> result = moneyService.findMoneyByDate(selectDate,uid);
		ResponseResult<List<Money>> rr = null;
		if(result!=null) {
		rr = new ResponseResult<List<Money>>(SUCCESS);
		rr.setData(result);
		}else {
		rr = new ResponseResult<List<Money>>(0);
		}
		
		return rr;
	}
	
	@GetMapping("/monthAnalysis.do")
	public ResponseResult<List<Money>> handleMonthAnalysis(String selectDate,HttpSession session){
		//Get Uid from Session
		Integer uid = getUidFromSession(session);
		List<Money> result = moneyService.findMoneyByMonth(selectDate,uid);
		ResponseResult<List<Money>> rr = null;
		if(result!=null) {
		rr = new ResponseResult<List<Money>>(SUCCESS);
		rr.setData(result);
		}else {
		rr = new ResponseResult<List<Money>>(0);
		}
		
		return rr;
	}
	
	
}
