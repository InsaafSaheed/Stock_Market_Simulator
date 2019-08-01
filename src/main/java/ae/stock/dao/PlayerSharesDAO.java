package ae.stock.dao;
import java.util.ArrayList;
import java.util.List;

import ae.stock.entities.*;
public class PlayerSharesDAO {
	private static List<PlayerShares> player_stocks = new ArrayList<PlayerShares>();
	public static List<PlayerShares> getAll(){
		return player_stocks;
	}
	
	public static void save(PlayerShares player_stock) {
		player_stocks.add(player_stock);	
	}

}
