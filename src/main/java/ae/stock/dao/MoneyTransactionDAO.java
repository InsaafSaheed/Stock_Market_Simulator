package ae.stock.dao;

import java.util.ArrayList;
import java.util.List;

import ae.stock.entities.MoneyTransaction;
public class MoneyTransactionDAO {
	private static List<MoneyTransaction> account_transactions=new ArrayList<MoneyTransaction>();
	public static List<MoneyTransaction> getAll(){
		return account_transactions;
	}
	
	public static void save(MoneyTransaction account_transaction) {
		account_transactions.add(account_transaction);
	}
	

}
