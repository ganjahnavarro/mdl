package core.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BrandData extends RecordData {
	
	private String name;
	private String description;

}
