package core.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity(name = SupplierBrandDiscount.ENTITY_NAME)
public class SupplierBrandDiscount extends Record {
	
	public static final String ENTITY_NAME = "supplierBrandDiscount";
	private static final long serialVersionUID = 9150321999211957157L;
	
	private Supplier supplier;
	private Brand brand;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;

	@NotNull(message = "Supplier is required")
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplierId")
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@NotNull(message = "Brand is required")
	@ManyToOne(targetEntity = Brand.class)
	@JoinColumn(name = "brandId")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
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
	
	public BigDecimal getDiscount3() {
		return discount3;
	}

	public void setDiscount3(BigDecimal discount3) {
		this.discount3 = discount3;
	}

	@Transient
	@Override
	public String getDisplayString() {
		return null;
	}

}
