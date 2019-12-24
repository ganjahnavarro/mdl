package core.dto.transaction;

import java.math.BigDecimal;

import core.dto.StockData;
import lombok.Data;

@Data
public class PurchaseOrderItemData {

	private Long id;
	private StockData stock;
	private Integer quantity;
	private BigDecimal price;
	
	private BigDecimal discount1;
	private BigDecimal discount2;
	private BigDecimal discount3;

}
