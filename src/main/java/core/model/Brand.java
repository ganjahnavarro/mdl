package core.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = Brand.ENTITY_NAME)
public class Brand extends Record {
	
	public static final String ENTITY_NAME = "brand";
	private static final long serialVersionUID = -1378470589548385896L;

	private String name;
	private String description;

	@NotEmpty(message = "Name is required")
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
