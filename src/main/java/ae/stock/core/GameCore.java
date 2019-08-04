package ae.stock.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ae.stock.dao.CompanyDAO;
import ae.stock.dao.MarketSectorDAO;
import ae.stock.dao.PlayerDAO;
import ae.stock.entities.*;
public class GameCore {
	
    private static int current_round=1;
	private static List<Player> player_list;
	private static int[] market_trends=new int[10];
	private static int[] general_trends=new int[10];
	private static List<MarketSector> sector_trends=new ArrayList<MarketSector>();
	private static List<MarketEvent> event_list=new ArrayList<MarketEvent>();
	private static List<ShareValues> company_trends=new ArrayList<ShareValues>();
	public static boolean game_started=false;
	
	public static int[] getGeneral_trends() {
		return general_trends;
	}
	public static void setGeneral_trends(int[] generalTrends) {
		general_trends = generalTrends;
	}
	public static int getCurrent_round() {
		return current_round;
	}
	public static void setCurrent_round(int currentRound) {
		current_round = currentRound;
	}
	public static List<Player> getPlayer_list() {
		return player_list;
	}
	public static void setPlayer_list(List<Player> playerList) {
		player_list = playerList;
	}
	public static int[] getMarket_trends() {
		return market_trends;
	}
	public static void setMarket_trends(int[] marketTrends) {
		market_trends = marketTrends;
	}
	public static List<MarketSector> getSector_trends() {
		return sector_trends;
	}
	public static void setSector_trends(List<MarketSector> sectorTrends) {
		sector_trends = sectorTrends;
	}
	public static List<MarketEvent> getEvent_list() {
		return event_list;
	}
	public static List<ShareValues> getCompanyTrends(){
		return company_trends;
	}
	public void setEvent_list(List<MarketEvent> eventList) {
		event_list = eventList;
	}

}
