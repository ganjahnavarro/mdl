package core.dto;

import java.math.BigDecimal;

public class StockData extends RecordData {

	private String name;
	private String oem;
	private String description;

	private BigDecimal cost;
	private BigDecimal price;
	private Long onHand;

	private UnitData unit;
	private CategoryData category;
	private BrandData brand;
	private SupplierData supplier;

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

	public SupplierData getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierData supplier) {
		this.supplier = supplier;
	}

}
