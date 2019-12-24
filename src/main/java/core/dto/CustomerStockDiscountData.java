package core.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerStockDiscountData extends RecordData {

	private CustomerData customer;
	private StockData stock;
	
	private BigDecimal discount1;
	private BigDecimal discount2;

}
