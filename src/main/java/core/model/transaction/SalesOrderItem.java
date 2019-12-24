package core.model.transaction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import core.model.Stock;
import lombok.Data;

@Data
@Entity(name = SalesOrderItem.ENTITY_NAME)
public class SalesOrderItem implements Serializable {

	public static final String ENTITY_NAME = "salesOrderItem";
	private static final long serialVersionUID = -5934188629547297156L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Stock is required")
	@ManyToOne(targetEntity = Stock.class)
	@JoinColumn(name = "stockId")
	private Stock stock;
	
	@NotNull(message = "Sales Order is required")
	@ManyToOne(targetEntity = SalesOrder.class)
	@JoinColumn(name = "salesOrderId")
	private SalesOrder salesOrder;

	@NotNull(message = "Quantity is required")
	private Integer quantity;
	
	@NotNull(message = "Price is required")
	private BigDecimal price;

	private BigDecimal discount1;
	private BigDecimal discount2;

	@Transient
	public BigDecimal getGrossAmount() {
		return getPrice();
	}
	
	@Transient
	public BigDecimal getNetAmount() {
		BigDecimal amount = getPrice();
		amount = getDiscount1() != null ? getDiscountedAmount(amount, getDiscount1()) : amount;
		amount = getDiscount2() != null ? getDiscountedAmount(amount, getDiscount2()) : amount;
		return amount;
	}
	
	@Transient
	public BigDecimal getAmount() {
		return getNetAmount()
				.multiply(BigDecimal.valueOf(getQuantity()))
				.setScale(2, RoundingMode.HALF_UP);
	}
	
	@Transient
	private BigDecimal getDiscountedAmount(BigDecimal amount, BigDecimal discount) {
		RoundingMode roundingMode = RoundingMode.HALF_UP;
		
		BigDecimal dividend = BigDecimal.valueOf(100).subtract(discount);
		BigDecimal multiplicand = dividend
				.divide(BigDecimal.valueOf(100), roundingMode)
				.setScale(2, roundingMode);
		
		return amount
				.multiply(multiplicand)
				.setScale(2, roundingMode);
	}

}
