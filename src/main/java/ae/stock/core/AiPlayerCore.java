package ae.stock.core;

import java.util.ArrayList;
import java.util.List;

import ae.stock.dao.BankAccountDAO;
import ae.stock.dao.CompanyDAO;
import ae.stock.dao.MoneyTransactionDAO;
import ae.stock.dao.PlayerDAO;
import ae.stock.dao.PlayerSharesDAO;
import ae.stock.dao.StockTransactionsDAO;
import ae.stock.entities.BankAccount;
import ae.stock.entities.Company;
import ae.stock.entities.MoneyTransaction;
import ae.stock.entities.Player;
import ae.stock.entities.PlayerShares;
import ae.stock.entities.ShareValues;
import ae.stock.entities.StockTransactions;

public class AiPlayerCore {
    private static List<Player> ai_players = new ArrayList<Player>();

	public static List<Player> getPlayers() {
		return ai_players;
	}
	
	public static void newAiPlayer(Player player) {
		ai_players.add(player);
	}

	public static void setPlayers(List<Player> players) {
		AiPlayerCore.ai_players = players;
	}
	

}
