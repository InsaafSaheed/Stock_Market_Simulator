package ae.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "player_stocks")
public class PlayerShares implements Serializable {
	private static final long serialVersionUID = 1L;
	private String player;
	private String Company;
	private int Stock_Count;
	private double Stock_Value;
	

}
