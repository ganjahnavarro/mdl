package core.dto;

import java.math.BigDecimal;

public class SupplierStockDiscountData extends RecordData {

	private SupplierData supplier;
	private StockData stock;

	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;

	public SupplierData getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierData supplier) {
		this.supplier = supplier;
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

	public BigDecimal getDiscount3() {
		return discount3;
	}

	public void setDiscount3(BigDecimal discount3) {
		this.discount3 = discount3;
	}

}
