package core.dto.transaction;

import java.math.BigDecimal;

import core.dto.StockData;
import lombok.Data;

@Data
public class SalesOrderItemData {

	private Long id;
	private StockData stock;
	private Integer quantity;
	private BigDecimal price;
	
	private BigDecimal discount1;
	private BigDecimal discount2;

}
