package core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import core.enums.AgentType;

@Entity(name = Agent.ENTITY_NAME)
public class Agent extends Record {

	private static final long serialVersionUID = -6577286636309267578L;
	public static final String ENTITY_NAME = "agent";

	private String name;
	private AgentType type;
	private String address;
	private String contact;
	private String tin;
	
	@NotEmpty(message = "Name is required")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Type is required")
	public AgentType getType() {
		return type;
	}

	public void setType(AgentType type) {
		this.type = type;
	}

	@Column(columnDefinition = "text")
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

	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

}
