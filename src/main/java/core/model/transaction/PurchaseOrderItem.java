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

@Entity(name = PurchaseOrderItem.ENTITY_NAME)
public class PurchaseOrderItem implements Serializable {

	public static final String ENTITY_NAME = "purchaseOrderItem";
	private static final long serialVersionUID = 4784359502385628272L;

	private Long id;

	private Stock stock;
	private PurchaseOrder purchaseOrder;

	private Integer quantity;
	private BigDecimal price;

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

}
