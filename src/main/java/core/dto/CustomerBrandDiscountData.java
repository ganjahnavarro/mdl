package core.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerBrandDiscountData extends RecordData {

	private CustomerData customer;
	private BrandData brand;
	
	private BigDecimal discount1;
	private BigDecimal discount2;

}
