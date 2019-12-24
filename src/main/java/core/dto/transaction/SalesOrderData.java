package core.dto.transaction;

import java.math.BigDecimal;
import java.util.Set;

import core.dto.AgentData;
import core.dto.CustomerData;
import core.dto.RecordData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SalesOrderData extends RecordData {

	private String documentNo;
	private CustomerData customer;
	private AgentData agent;

	private String date;
	private String remarks;

	private BigDecimal discount1;
	private BigDecimal discount2;

	private Set<SalesOrderItemData> items;

}
