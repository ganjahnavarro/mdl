package core.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = Stock.ENTITY_NAME)
public class Stock extends Record {

	public static final String ENTITY_NAME = "stock";
	private static final long serialVersionUID = 4117278518178986016L;

	private String name;
	private String description;

	private BigDecimal cost;
	private BigDecimal price;
	private Long onHand;

	private Unit unit;
	private Category category;

	@NotEmpty(message = "Name is required")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

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

	@Transient
	@Override
	public String getDisplayString() {
		return getName();
	}

}
