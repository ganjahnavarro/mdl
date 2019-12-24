package core.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = Stock.ENTITY_NAME)
public class Stock extends Record {

	public static final String ENTITY_NAME = "stock";
	private static final long serialVersionUID = 4117278518178986016L;

	@NotEmpty(message = "Name is required")
	private String name;
	private String oem;
	private String description;

	@NotNull
	private BigDecimal cost;
	
	@NotNull
	private BigDecimal price;
	
	private Long onHand;

	@ManyToOne(targetEntity = Unit.class)
	@JoinColumn(name = "unitId")
	private Unit unit;
	
	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@ManyToOne(targetEntity = Brand.class)
	@JoinColumn(name = "brandId")
	private Brand brand;
	
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplierId")
	private Supplier supplier;

	private Boolean shouldPrintName; 

	@Transient
	@Override
	public String getDisplayString() {
		return getCategory().getDisplayString() + " / " + getName() + " / "
				+ (getDescription() != null ? getDescription() + " / " : "")
				+ getBrand().getDisplayString();
	}

}
