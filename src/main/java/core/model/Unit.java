package core.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = Unit.ENTITY_NAME)
public class Unit extends Record {

	private static final long serialVersionUID = -1877398761241108154L;
	public static final String ENTITY_NAME = "unit";

	@NotEmpty(message = "Name is required")
	private String name;
	
	@NotEmpty(message = "Plural name is required")
	private String pluralName;

	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

}
