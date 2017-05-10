package core.dto;

import core.enums.AgentType;

public class AgentData extends RecordData {

	private String name;
	private AgentType type;
	private String address;
	private String contact;
	private String tin;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public AgentType getType() {
		return type;
	}

	public void setType(AgentType type) {
		this.type = type;
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

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

}
