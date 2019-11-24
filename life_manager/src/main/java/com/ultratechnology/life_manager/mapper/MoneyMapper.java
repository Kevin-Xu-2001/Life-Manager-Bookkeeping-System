package com.ultratechnology.life_manager.mapper;

import java.util.List;

import com.ultratechnology.life_manager.entity.Money;

//persistent layer to handle the money issues
public interface MoneyMapper {

	/**
	 * insert a consume data to database
	 * @param money
	 * @return the line affected by the operation
	 */
	Integer addnew(Money money);
	
	
	/**
	 * 
	 * @return a list of all the money data
	 */
	List<Money> findAll();
	
	/**
	 * 
	 * @return the line affected by the operation
	 */
	Integer delete(Integer id);
	
	/**
	 * 
	 * @return a list of all the money spent at that specific date
	 */
	List<Money> findByDate(String selectDate);
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @return a list of money objects spent at the specific month
	 */
	List<Money> findByMonth(Integer year, Integer month);
	
	
}
