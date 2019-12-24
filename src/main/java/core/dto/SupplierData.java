package core.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierData extends RecordData {

	private String name;
	private String address;
	private String contact;
	private String fax;
	private String tin;
	
	private String terms;

}
