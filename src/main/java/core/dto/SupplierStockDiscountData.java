package core.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierStockDiscountData extends RecordData {

	private SupplierData supplier;
	private StockData stock;

	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;

}
