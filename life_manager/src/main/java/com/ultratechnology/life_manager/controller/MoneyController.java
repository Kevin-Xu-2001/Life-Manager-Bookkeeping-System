package com.ultratechnology.life_manager.controller;

import java.util.List;

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
	public ResponseResult<Void> handleAdd(Money money){
		System.out.println("MoneyController: get into HandleAdd function");
		System.out.println("Money: "+money);
		Money result = moneyService.createLine(money);
		System.out.println("The return money is:"+result);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	
	@GetMapping("/getAllList.do")
	public ResponseResult<List<List<Money>>> handleGetAllList(){
		System.out.println("MoneyController: get into HandleFindAll function");
		List<List<Money>> allResult = moneyService.findSortedByDateConsumed();
		for (List<Money> list : allResult) {
			System.out.println(list);
		}
		ResponseResult<List<List<Money>>> rr = new ResponseResult<List<List<Money>>>(SUCCESS);
		rr.setData(allResult);
		rr.setMessage("successfully searched");
		return rr;
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
	public ResponseResult<List<Money>> handleDayAnalysis(String selectDate){
		System.out.println("MoneyController: get into day analysis function");
		List<Money> result = moneyService.findMoneyByDate(selectDate);
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
	public ResponseResult<List<Money>> handleMonthAnalysis(String selectDate){
		System.out.println("MoneyController: get into month analysis function");
		List<Money> result = moneyService.findMoneyByMonth(selectDate);
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
