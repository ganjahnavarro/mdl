package core.dto;

import java.math.BigDecimal;

import core.model.Category;
import core.model.Unit;

public class StockData extends RecordData {

	private String name;
	private String description;

	private BigDecimal cost;
	private BigDecimal price;
	private Long onHand;

	private Unit unit;
	private Category category;

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

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
