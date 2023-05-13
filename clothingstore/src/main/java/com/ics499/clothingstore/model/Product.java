package com.ics499.clothingstore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Product Model, Parent of all types of products. Represents all products in
 * DB.
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

	@Id
	@GeneratedValue
	private long productId;

	protected float price;
	protected int stock;
	protected String description;
	protected String brand;
	protected String color;
	protected String size;
	protected String fit;
	protected String image_source;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ProductReview> productReviews;

	public Product(float price, int stock, String description, String brand, String color, String size, String fit,
			String imageSource) {
		super();
		this.price = price;
		this.stock = stock;
		this.description = description;
		this.brand = brand;
		this.color = color;
		this.size = size;
		this.fit = fit;
		this.image_source = imageSource;
	}

	public Product() {

	}

	public List<ProductReview> getProductReviews() {
		return productReviews;
	}

	public void addProductReview(ProductReview productReview) {
		this.productReviews.add(productReview);
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getProductId() {
		return productId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFit() {
		return fit;
	}

	public void setFit(String fit) {
		this.fit = fit;
	}

	@Override
	public String toString() {
		return "Product [price=" + price + ", stock=" + stock + ", Description=" + description + ", brand=" + brand
				+ ", color=" + color + ", size=" + size + ", fit=" + fit + "]";
	}

	public String getImageSource() {
		return image_source;
	}

	public void setImageSource(String imageSource) {
		this.image_source = imageSource;
	}

}
