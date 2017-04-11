package core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = Supplier.ENTITY_NAME)
public class Supplier extends Record {

	public static final String ENTITY_NAME = "supplier";
	private static final long serialVersionUID = -7699458379737469434L;

	private String name;
	private String address;
	private String contact;
	private String fax;
	private String tin;

	@NotEmpty(message = "Name is required")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Transient
	@Override
	public String getDisplayString() {
		// TODO Auto-generated method stub
		return null;
	}

}
