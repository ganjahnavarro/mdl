package core.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity(name = SupplierStockDiscount.ENTITY_NAME)
public class SupplierStockDiscount extends Record {
	
	public static final String ENTITY_NAME = "supplierStockDiscount";
	private static final long serialVersionUID = 6479651860184870896L;
	
	private Supplier supplier;
	private Stock stock;
	
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

	@NotNull(message = "Stock is required")
	@ManyToOne(targetEntity = Stock.class)
	@JoinColumn(name = "stockId")
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
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

	@Transient
	@Override
	public String getDisplayString() {
		return null;
	}

}
