package core.dto;

import java.math.BigDecimal;

public class CustomerData extends RecordData {

	private String name;
	private AgentData agent;

	private String address;
	private String contact;
	private String fax;
	private String tin;
	private BigDecimal commission;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public AgentData getAgent() {
		return agent;
	}

	public void setAgent(AgentData agent) {
		this.agent = agent;
	}

}
