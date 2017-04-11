package core.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = Unit.ENTITY_NAME)
public class Unit extends Record {

	private static final long serialVersionUID = -1877398761241108154L;
	public static final String ENTITY_NAME = "unit";

	private String name;
	private String pluralName;

	@NotEmpty(message = "Name is required")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message = "Plural name is required")
	public String getPluralName() {
		return pluralName;
	}

	public void setPluralName(String pluralName) {
		this.pluralName = pluralName;
	}

	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

}
