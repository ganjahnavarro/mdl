package core.dto.transaction;

import java.math.BigDecimal;

import core.dto.StockData;

public class PurchaseOrderItemData {

	private Long id;
	private StockData stock;
	private Integer quantity;
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StockData getStock() {
		return stock;
	}

	public void setStock(StockData stock) {
		this.stock = stock;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
