package ae.stock.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stock_transactions")
public class StockTransactions implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String player;
	private String company;
	private int share_amount;
	private double transaction_amount;
	private String transaction_type;
	private String trans_time;
	public String getPlayer() {
		return player;
	}
	

	
}
