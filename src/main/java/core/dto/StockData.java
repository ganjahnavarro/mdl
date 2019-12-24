package core.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StockData extends RecordData {

	private String name;
	private String oem;
	private String description;

	private BigDecimal cost;
	private BigDecimal price;
	private Long onHand;

	private UnitData unit;
	private CategoryData category;
	private BrandData brand;
	private SupplierData supplier;

}
