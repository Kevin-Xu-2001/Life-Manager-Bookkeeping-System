package test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultratechnology.life_manager.entity.Money;
import com.ultratechnology.life_manager.mapper.MoneyMapper;
import com.ultratechnology.life_manager.service.impl.MoneyServiceImpl;

@Service
public class Test1 {
	
	private static MoneyServiceImpl impl = new MoneyServiceImpl();

	public static void main(String[] args) {
		System.out.println(impl);
		List<Money> moneyList = impl.findMoneyByDate("2019-11-14");
		System.out.println(moneyList);
	}
}
