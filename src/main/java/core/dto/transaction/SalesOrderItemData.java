package core.dto.transaction;

import java.math.BigDecimal;

import core.dto.StockData;

public class SalesOrderItemData {

	private Long id;
	private StockData stock;
	private Integer quantity;
	private BigDecimal price;
	
	private BigDecimal discount1;
	private BigDecimal discount2;

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
