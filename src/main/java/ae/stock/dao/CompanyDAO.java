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
	public static Company update(Company company) {
		for(Company company_stock:company_stocks) {
			if(company.getCompany_Name().equals(company_stock.getCompany_Name())) {
				company_stock.setNo_of_Stocks(company.getNo_of_Stocks());
				company_stock.setRandom_Trend(company.getRandom_Trend());
				company_stock.setShare_Vlaue(company.getShare_Vlaue());
			}
		}
		return company;
	}


}
