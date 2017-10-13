package core.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity(name = CustomerStockDiscount.ENTITY_NAME)
public class CustomerStockDiscount extends Record {
	
	public static final String ENTITY_NAME = "customerStockDiscount";
	private static final long serialVersionUID = -9084218672853783904L;
	
	private Customer customer;
	private Stock stock;
	
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

	@NotNull(message = "Stock is required")
	@ManyToOne(targetEntity = Stock.class)
	@JoinColumn(name = "stockId")
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
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
