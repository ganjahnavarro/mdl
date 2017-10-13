package core.dto;

import java.math.BigDecimal;

public class CustomerStockDiscountData extends RecordData {

	private CustomerData customer;
	private StockData stock;
	
	private BigDecimal discount1;
	private BigDecimal discount2;

	public CustomerData getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerData customer) {
		this.customer = customer;
	}

	public StockData getStock() {
		return stock;
	}

	public void setStock(StockData stock) {
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

}
