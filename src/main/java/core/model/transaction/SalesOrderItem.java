package core.model.transaction;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import core.model.Stock;

@Entity(name = SalesOrderItem.ENTITY_NAME)
public class SalesOrderItem implements Serializable {

	public static final String ENTITY_NAME = "salesOrderItem";
	private static final long serialVersionUID = -5934188629547297156L;

	private Long id;
	private Stock stock;
	private SalesOrder salesOrder;

	private Integer quantity;
	private BigDecimal price;

	private BigDecimal discount1;
	private BigDecimal discount2;

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

	@NotNull(message = "Sales Order is required")
	@ManyToOne(targetEntity = SalesOrder.class)
	@JoinColumn(name = "salesOrderId")
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
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

}
