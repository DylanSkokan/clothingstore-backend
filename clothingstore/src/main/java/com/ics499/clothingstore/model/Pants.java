package com.ics499.clothingstore.model;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * Pants model, used to represent a pair of pants in the DB.
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

@Entity
public class Pants extends Product {

	public enum PantsType {
		Jeans, Cargo, Dress, Khaki, Sweat, Pajama
	}

	private PantsType type;
	private boolean pockets;
	private boolean zipper;
	private boolean beltloops;

	public Pants(float price, int stock, String description, String brand, String color, String size, String fit,
			PantsType type, boolean pockets, boolean zipper, boolean beltloops, String imageSource) {
		super(price, stock, description, brand, color, size, fit, imageSource);
		this.type = type;
		this.pockets = pockets;
		this.zipper = zipper;
		this.beltloops = beltloops;
	}

	public Pants() {

	}

	public PantsType getType() {
		return type;
	}

	public void setType(PantsType type) {
		this.type = type;
	}

	public boolean isPockets() {
		return pockets;
	}

	public void setPockets(boolean pockets) {
		this.pockets = pockets;
	}

	public boolean isZipper() {
		return zipper;
	}

	public void setZipper(boolean zipper) {
		this.zipper = zipper;
	}

	public boolean isBeltloops() {
		return beltloops;
	}

	public void setBeltloops(boolean beltloops) {
		this.beltloops = beltloops;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Pants))
			return false;
		Pants pants = (Pants) o;
		return Float.compare(pants.price, price) == 0 && stock == pants.stock
				&& Objects.equals(description, pants.description) && Objects.equals(brand, pants.brand)
				&& Objects.equals(color, pants.color) && Objects.equals(size, pants.size)
				&& Objects.equals(fit, pants.fit) && type == pants.type && pockets == pants.pockets
				&& zipper == pants.zipper && beltloops == pants.beltloops
				&& Objects.equals(image_source, pants.image_source);
	}
}
