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

@Entity(name = PurchaseOrderItem.ENTITY_NAME)
public class PurchaseOrderItem implements Serializable {

	public static final String ENTITY_NAME = "purchaseOrderItem";
	private static final long serialVersionUID = 4784359502385628272L;

	private Long id;

	private Stock stock;
	private PurchaseOrder purchaseOrder;

	private Integer quantity;
	private BigDecimal price;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@NotNull(message = "Purchase Order is required")
	@ManyToOne(targetEntity = PurchaseOrder.class)
	@JoinColumn(name = "purchaseOrderId")
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@NotNull(message = "Quantity is required")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@NotNull(message = "Price is required")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public BigDecimal getDiscount3() {
		return discount3;
	}

	public void setDiscount3(BigDecimal discount3) {
		this.discount3 = discount3;
	}
	
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
