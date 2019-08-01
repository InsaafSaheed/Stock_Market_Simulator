package ae.stock.dao;
import java.util.ArrayList;
import java.util.List;

import ae.stock.entities.MarketEvent;
public class MarketEventDAO {
	private static List<MarketEvent> event_list=new ArrayList<MarketEvent>();
	public static List<MarketEvent> getAll(){
		return event_list;
	}
	public static void save(MarketEvent event) {
		event_list.add(event);
	}
}
