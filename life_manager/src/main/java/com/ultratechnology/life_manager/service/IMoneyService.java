package com.ultratechnology.life_manager.service;

import java.util.List;

import com.ultratechnology.life_manager.entity.Money;

/**
 * The interface to handle the money issues
 * @author kaiwenxu
 *
 */
public interface IMoneyService {

	/**
	 * Money consume line create
	 * @param money
	 * @return the money info when successfully created
	 */
	Money createLine(Money money);
	
	
	/**
	 * Find all the data that is in the database
	 * @return
	 */
	List<List<Money>> findSortedByDateConsumed();
	
	/**
	 * delete the unused lines
	 */
	Integer deleteUnused(Integer id);
	
	/**
	 * find the money items in a specific date
	 * @param selectDate
	 * @return
	 */
	List<Money> findMoneyByDate(String selectDate);
	
	/**
	 * find the money items in a specific month
	 * @param selectDate
	 * @return
	 */
	List<Money> findMoneyByMonth(String selectDate);
	
}
