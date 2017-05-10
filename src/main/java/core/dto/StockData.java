package core.dto;

import java.math.BigDecimal;

public class StockData extends RecordData {

	private String name;
	private String description;

	private BigDecimal cost;
	private BigDecimal price;
	private Long onHand;

	private UnitData unit;
	private CategoryData category;
	private BrandData brand;

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

	public UnitData getUnit() {
		return unit;
	}

	public void setUnit(UnitData unit) {
		this.unit = unit;
	}

	public CategoryData getCategory() {
		return category;
	}

	public void setCategory(CategoryData category) {
		this.category = category;
	}

	public BrandData getBrand() {
		return brand;
	}

	public void setBrand(BrandData brand) {
		this.brand = brand;
	}

}
