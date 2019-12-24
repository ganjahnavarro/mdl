package core.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = Category.ENTITY_NAME)
public class Category extends Record {

	public static final String ENTITY_NAME = "category";
	private static final long serialVersionUID = 5235716781507663078L;

	@NotEmpty(message = "Name is required")
	private String name;
	
	private String description;

	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

}
