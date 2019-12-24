package core.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = SupplierStockDiscount.ENTITY_NAME)
public class SupplierStockDiscount extends Record {
	
	public static final String ENTITY_NAME = "supplierStockDiscount";
	private static final long serialVersionUID = 6479651860184870896L;
	
	@NotNull(message = "Supplier is required")
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplierId")
	private Supplier supplier;
	
	@NotNull(message = "Stock is required")
	@ManyToOne(targetEntity = Stock.class)
	@JoinColumn(name = "stockId")
	private Stock stock;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;
	
	@Transient
	@Override
	public String getDisplayString() {
		return null;
	}

}
