package core.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = Brand.ENTITY_NAME)
public class Brand extends Record {
	
	public static final String ENTITY_NAME = "brand";
	private static final long serialVersionUID = -1378470589548385896L;

	@NotEmpty(message = "Name is required")
	private String name;
	
	private String description;

	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

}
