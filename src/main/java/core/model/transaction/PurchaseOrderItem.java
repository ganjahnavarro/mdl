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
@Entity(name = PurchaseOrderItem.ENTITY_NAME)
public class PurchaseOrderItem implements Serializable {

	public static final String ENTITY_NAME = "purchaseOrderItem";
	private static final long serialVersionUID = 4784359502385628272L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Stock is required")
	@ManyToOne(targetEntity = Stock.class)
	@JoinColumn(name = "stockId")
	private Stock stock;
	
	@NotNull(message = "Purchase Order is required")
	@ManyToOne(targetEntity = PurchaseOrder.class)
	@JoinColumn(name = "purchaseOrderId")
	private PurchaseOrder purchaseOrder;

	@NotNull(message = "Quantity is required")
	private Integer quantity;
	
	@NotNull(message = "Price is required")
	private BigDecimal price;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;

	@Transient
	public BigDecimal getGrossAmount() {
		return getPrice();
	}
	
	@Transient
	public BigDecimal getNetAmount() {
		BigDecimal amount = getPrice();
		amount = getDiscount1() != null ? getDiscountedAmount(amount, getDiscount1()) : amount;
		amount = getDiscount2() != null ? getDiscountedAmount(amount, getDiscount2()) : amount;
		amount = getDiscount3() != null ? getDiscountedAmount(amount, getDiscount3()) : amount;
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
