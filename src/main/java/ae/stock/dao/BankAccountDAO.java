package ae.stock.dao;

import java.util.ArrayList;
import java.util.List;

import ae.stock.entities.BankAccount;
public class BankAccountDAO {
	private static List<BankAccount> bank_accounts=new ArrayList<BankAccount>();
	public static List<BankAccount> getAll(){
		return bank_accounts;
	}
	

}
