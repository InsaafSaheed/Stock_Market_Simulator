package ae.stock.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ae.stock.entities.Company;
import ae.stock.entities.MarketSector;
public class CompanyDAO {
	private static List<Company> company_stocks = new ArrayList<Company>();
	public static List<Company> getAll(){
		
		return company_stocks;
	}
	
	public static void save(Company company) {
		company_stocks.add(company);
	}


}
