package core.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerData extends RecordData {

	private String name;
	private AgentData agent;

	private String address;
	private String contact;
	private String fax;
	private String tin;
	private BigDecimal commission;
	
	private String terms;
	private String accountNumber;
	private String homeAddress;
	private String ownersName;

}
