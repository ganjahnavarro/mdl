package core.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = Customer.ENTITY_NAME)
public class Customer extends Record {

	public static final String ENTITY_NAME = "customer";
	private static final long serialVersionUID = -7415963747435056871L;

	@NotEmpty(message = "Name is required")
	private String name;
	
	@ManyToOne(targetEntity = Agent.class)
	@JoinColumn(name = "agentId")
	private Agent agent;
	
	@Column(columnDefinition = "text")
	private String address;
	
	private String contact;
	private String fax;
	private String tin;
	private BigDecimal commission;
	
	private String terms;
	private String accountNumber;
	private String homeAddress;
	private String ownersName;

	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}
	
	@Transient
	public String getOfficeAddress() {
		return getAddress();
	}

}
