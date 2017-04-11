package core.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = Category.ENTITY_NAME)
public class Category extends Record {

	public static final String ENTITY_NAME = "category";
	private static final long serialVersionUID = 5235716781507663078L;

	private String name;

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

}
