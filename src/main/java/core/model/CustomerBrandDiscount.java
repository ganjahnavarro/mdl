package core.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = CustomerBrandDiscount.ENTITY_NAME)
public class CustomerBrandDiscount extends Record {

	public static final String ENTITY_NAME = "customerBrandDiscount";
	private static final long serialVersionUID = 5311913162936040966L;
	
	@NotNull(message = "Customer is required")
	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@NotNull(message = "Brand is required")
	@ManyToOne(targetEntity = Brand.class)
	@JoinColumn(name = "brandId")
	private Brand brand;
	
	private BigDecimal discount1;
	private BigDecimal discount2;

	@Transient
	@Override
	public String getDisplayString() {
		return null;
	}

}
