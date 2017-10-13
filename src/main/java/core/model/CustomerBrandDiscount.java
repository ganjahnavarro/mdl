package core.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity(name = CustomerBrandDiscount.ENTITY_NAME)
public class CustomerBrandDiscount extends Record {

	public static final String ENTITY_NAME = "customerBrandDiscount";
	private static final long serialVersionUID = 5311913162936040966L;
	
	private Customer customer;
	private Brand brand;
	
	private BigDecimal discount1;
	private BigDecimal discount2;

	@NotNull(message = "Customer is required")
	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	@NotNull(message = "Brand is required")
	@ManyToOne(targetEntity = Brand.class)
	@JoinColumn(name = "brandId")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
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

	@Transient
	@Override
	public String getDisplayString() {
		return null;
	}

}
