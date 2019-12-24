package core.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = Supplier.ENTITY_NAME)
public class Supplier extends Record {

	public static final String ENTITY_NAME = "supplier";
	private static final long serialVersionUID = -7699458379737469434L;

	@NotEmpty(message = "Name is required")
	private String name;
	
	@Column(columnDefinition = "text")
	private String address;
	
	private String contact;
	private String fax;
	private String tin;
	
	private String terms;

	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

}
