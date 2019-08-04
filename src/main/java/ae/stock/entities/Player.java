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
public int getCurrent_round() {
		return current_round;
	}

	@XmlElement
	public void setCurrent_round(int current_round) {
		this.current_round = current_round;
	}
	
	
	public BankAccount getBank_account() {
		return bank_account;
	}
	public void setBank_account(BankAccount bank_account) {
		this.bank_account = bank_account;
		BankAccountDAO.save(this.bank_account);
	}

	public Player() {
		
	}
	
	public Player(String player_name) {
		this.player_name = player_name;
		this.stock_value=0;
		this.current_round=1;
		this.bank_account=new BankAccount(player_name);
		BankAccountDAO.save(this.bank_account);
	}	
 
}
