package ae.stock.dao;

import java.util.ArrayList;
import java.util.List;

import ae.stock.entities.StockTransactions;

public class StockTransactionsDAO {
	private static List<StockTransactions> account_transactions=new ArrayList<StockTransactions>();
	public static List<StockTransactions> getAll(){
		return account_transactions;
	}
	
	public static void save(StockTransactions account_transaction) {
		account_transactions.add(account_transaction);
	}
	

}
