package core.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnitData extends RecordData {

	private String name;
	private String pluralName;

}
