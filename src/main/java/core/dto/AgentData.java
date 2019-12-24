package core.dto;

import core.enums.AgentType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AgentData extends RecordData {

	private String name;
	private AgentType type;
	private String address;
	private String contact;
	private String tin;

}
