package core.dto.transaction;

import java.math.BigDecimal;
import java.util.Set;

import core.dto.AgentData;
import core.dto.CustomerData;
import core.dto.RecordData;

public class SalesOrderData extends RecordData {

	private String documentNo;
	private CustomerData customer;
	private AgentData agent;

	private String date;
	private String remarks;

	private BigDecimal discount1;
	private BigDecimal discount2;

	private Set<SalesOrderItemData> items;

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public CustomerData getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerData customer) {
		this.customer = customer;
	}

	public AgentData getAgent() {
		return agent;
	}

	public void setAgent(AgentData agent) {
		this.agent = agent;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getDiscount1() {
		return discount1;
	}

	public void setDiscount1(BigDecimal discount1) {
		this.discount1 = discount1;
	}

	public BigDecimal getDiscount2() {
		return discount2;
	}

	public void setDiscount2(BigDecimal discount2) {
		this.discount2 = discount2;
	}

	public Set<SalesOrderItemData> getItems() {
		return items;
	}

	public void setItems(Set<SalesOrderItemData> items) {
		this.items = items;
	}

}
