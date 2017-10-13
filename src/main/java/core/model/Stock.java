package core.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = Stock.ENTITY_NAME)
public class Stock extends Record {

	public static final String ENTITY_NAME = "stock";
	private static final long serialVersionUID = 4117278518178986016L;

	private String name;
	private String oem;
	private String description;

	private BigDecimal cost;
	private BigDecimal price;
	private Long onHand;

	private Unit unit;
	private Category category;
	private Brand brand;
	
	private Supplier supplier;

	private Boolean shouldPrintName; 

	@NotEmpty(message = "Name is required")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@NotNull
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getOnHand() {
		return onHand;
	}

	public void setOnHand(Long onHand) {
		this.onHand = onHand;
	}

	@ManyToOne(targetEntity = Unit.class)
	@JoinColumn(name = "unitId")
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "categoryId")
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@ManyToOne(targetEntity = Brand.class)
	@JoinColumn(name = "brandId")
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	@ManyToOne(targetEntity = Supplier.class)
	@JoinColumn(name = "supplierId")
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Boolean getShouldPrintName() {
		return shouldPrintName;
	}

	public void setShouldPrintName(Boolean shouldPrintName) {
		this.shouldPrintName = shouldPrintName;
	}

	@Transient
	@Override
	public String getDisplayString() {
		return getCategory().getDisplayString() + " / " + getName() + " / "
				+ (getDescription() != null ? getDescription() + " / " : "")
				+ getBrand().getDisplayString();
	}

}
