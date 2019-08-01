package ae.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;

import ae.stock.dao.BankAccountDAO;

@XmlRootElement(name = "player")
public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String player_name;
	private double stock_value;
	private int current_round;
	private BankAccount bank_account;
	

}
