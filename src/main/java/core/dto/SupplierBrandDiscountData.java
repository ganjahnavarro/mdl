package core.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierBrandDiscountData extends RecordData {

	private SupplierData supplier;
	private BrandData brand;
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;

}
