package core.dto;

import java.math.BigDecimal;

public class CustomerDiscountData extends RecordData {

	private CustomerData customer;
	private SupplierData supplier;
	private BigDecimal discount1;
	private BigDecimal discount2;

	public CustomerData getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerData customer) {
		this.customer = customer;
	}

	public SupplierData getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierData supplier) {
		this.supplier = supplier;
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
