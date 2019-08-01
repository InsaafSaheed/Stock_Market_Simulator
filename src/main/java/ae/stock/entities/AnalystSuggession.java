package ae.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "recommendation") 
public class AnalystSuggession implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private String company_name;
	private String recommend;
}
