package core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = Agent.ENTITY_NAME)
public class Agent extends Record {

	private static final long serialVersionUID = -6577286636309267578L;
	public static final String ENTITY_NAME = "agent";

	@NotEmpty(message = "Name is required")
	private String name;

	@Column(columnDefinition = "text")
	private String address;

	private String contact;
	private String tin;

	@NotNull
	private Boolean active = true;

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

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

}
