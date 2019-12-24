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
@Entity(name = CustomerStockDiscount.ENTITY_NAME)
public class CustomerStockDiscount extends Record {
	
	public static final String ENTITY_NAME = "customerStockDiscount";
	private static final long serialVersionUID = -9084218672853783904L;
	
	@NotNull(message = "Customer is required")
	@ManyToOne(targetEntity = Customer.class)
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@NotNull(message = "Stock is required")
	@ManyToOne(targetEntity = Stock.class)
	@JoinColumn(name = "stockId")
	private Stock stock;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	
	@Transient
	@Override
	public String getDisplayString() {
		return null;
	}

}
