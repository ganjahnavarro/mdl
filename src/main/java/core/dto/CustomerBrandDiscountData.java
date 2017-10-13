package core.dto;

import java.math.BigDecimal;

public class CustomerBrandDiscountData extends RecordData {

	private CustomerData customer;
	private BrandData brand;
	
	private BigDecimal discount1;
	private BigDecimal discount2;

	public CustomerData getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerData customer) {
		this.customer = customer;
	}

	public BrandData getBrand() {
		return brand;
	}

	public void setBrand(BrandData brand) {
		this.brand = brand;
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
