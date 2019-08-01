package ae.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@XmlRootElement(name = "account_transaction")
public class MoneyTransaction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String account_holder;
	private String transaction_type;
	private double transaction_amount;
	private String trans_time;
	private double balance_after;
	public MoneyTransaction() {
		


}
