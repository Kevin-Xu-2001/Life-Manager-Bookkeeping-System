package com.ultratechnology.life_manager.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultratechnology.life_manager.entity.Money;
import com.ultratechnology.life_manager.mapper.MoneyMapper;
import com.ultratechnology.life_manager.service.IMoneyService;

//handle with the money manage issues
@Service
public class MoneyServiceImpl implements IMoneyService{

	/**
	 * use the autowired annotation to let the xml file
	 * to automatically fill into the function defined
	 * in the moneyMapper interface
	 */
	@Autowired
	private MoneyMapper moneyMapper;
	
	/**
	 * First split the date_consumed to record the 
	 * year,month and day.
	 * Then create a line of data in the database.
	 */
	public Money createLine(Money money) {
		
		if (money != null && money.getAmount()!=null && money.getItem()!=null && money.getItem()!=""){
			
			String dateString = money.getDate_consumed();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				Date date = format.parse(dateString);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				
				money.setYear(calendar.get(Calendar.YEAR));
				money.setMonth(calendar.get(Calendar.MONTH)+1);
				money.setDay(calendar.get(Calendar.DAY_OF_MONTH));
				money.setWeekday(calendar.get(Calendar.DAY_OF_WEEK));
				// 1-Sunday 2-Monday ... 7-Saturday
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Integer rows = moneyMapper.addnew(money);
			//if we change the database successfully, return moneya
			if(rows == 1) {
				return money;
			}
		}
		return null;
	}

	/**
	 * find the money item in a specific date
	 * @param selectDate
	 * @return
	 */
	public List<Money> findMoneyByDate(String selectDate){
		return moneyMapper.findByDate(selectDate);
	}
	
	
	/**
	 * find the money objects in a specific month
	 * @param selectDate
	 * @return
	 */
	public List<Money> findMoneyByMonth(String selectDate){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = format.parse(selectDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			Integer year = calendar.get(Calendar.YEAR);
			//never forget the MONTH value in calendar is 1 less than the actual month number
			Integer month = calendar.get(Calendar.MONTH)+1;
			
			return moneyMapper.findByMonth(year, month);
			
		}catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * find all the money data
	 */
	public List<Money> findSortedAll() {
		List<Money> allResult = moneyMapper.findAll();
		Collections.sort(allResult, new sortByDateConsumed());
		return allResult;
	}

	

	
	/**
	 * Find the list of date, which contains a list of money consumed
	 * that day.
	 */
	public List<List<Money>> findSortedByDateConsumed(){
		List<Money> sortedAll = findSortedAll();
		
		List<List<Money>> listOfList = new ArrayList<List<Money>>();
		
		Money baseMoney = sortedAll.get(0);
		List<Money> listOfBase = new ArrayList<Money>();
		listOfBase.add(baseMoney);
		
		for(int i=1;i<sortedAll.size();i++) {
			Money thisMoney = sortedAll.get(i);
			/**
			 * ATTENTION: we should use "equals" to compare two Strings
			 * 
			 * If the next one of the Money has the same date_consumed with the current baseMoney, then:
			 * 1.add this money into the list of current base
			 */
			if(thisMoney.getDate_consumed().equals(baseMoney.getDate_consumed())) {
				listOfBase.add(thisMoney);
				
			/**
			 * If the next Money change a date_consumed, then we: 
			 * 1.change a baseMoney
			 * 2.add the listOfBase to the listOfList
			 * 3.release a new list of base 
			 * 4.add thisMoney into the new listOfBase
			 */
			}else {
				baseMoney=thisMoney;
				listOfList.add(listOfBase);
				listOfBase = new ArrayList<Money>();
				listOfBase.add(thisMoney);
			}
		}
		listOfList.add(listOfBase);
		return listOfList;
	}
	
	
	
	/**
	 * Inner class for transform the data consumed to a list ordered by consumed date descend
	 * @author kaiwenxu
	 */
	class sortByDateConsumed implements Comparator<Money> 
	{ 
	    // Used for sorting in ascending order of 
		@Override
		public int compare(Money m1, Money m2) {
			if(m1.getYear()>m2.getYear()){
				return -1;
			}else if(m1.getYear()==m2.getYear()) {
				if(m1.getMonth()>m2.getMonth()) {
					return -1;
				}else if(m1.getMonth()==m2.getMonth()) {
					if(m1.getDay()>m2.getDay()) {
						return -1;
					}else if(m1.getDay()==m2.getDay()) {
						return 0;
					}else {
						return 1;
					}
				}else {
					return 1;
				}
				
			}else {
				return 1;
			}
		} 
	}



	/**
	 * delete unused money line
	 */
	public Integer deleteUnused(Integer id) {
		Integer affected = moneyMapper.delete(id);
		return affected;
	} 
	
}


