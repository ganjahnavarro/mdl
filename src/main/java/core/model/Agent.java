package core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import core.enums.AgentType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = Agent.ENTITY_NAME)
public class Agent extends Record {

	private static final long serialVersionUID = -6577286636309267578L;
	public static final String ENTITY_NAME = "agent";

	@NotEmpty(message = "Name is required")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Type is required")
	private AgentType type;

	@Column(columnDefinition = "text")
	private String address;
	
	private String contact;
	private String tin;
	
	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

}
