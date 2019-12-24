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
@Entity(name = SupplierBrandDiscount.ENTITY_NAME)
public class SupplierBrandDiscount extends Record {
	
	public static final String ENTITY_NAME = "supplierBrandDiscount";
	private static final long serialVersionUID = 9150321999211957157L;
	
	@NotNull(message = "Supplier is required")
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplierId")
	private Supplier supplier;
	
	@NotNull(message = "Brand is required")
	@ManyToOne(targetEntity = Brand.class)
	@JoinColumn(name = "brandId")
	private Brand brand;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;

	@Transient
	@Override
	public String getDisplayString() {
		return null;
	}

}
