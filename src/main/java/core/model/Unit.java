package core.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity(name = Unit.ENTITY_NAME)
public class Unit extends Record {
	
	private static final long serialVersionUID = -1877398761241108154L;
	public static final String ENTITY_NAME = "unit";
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

	
}
