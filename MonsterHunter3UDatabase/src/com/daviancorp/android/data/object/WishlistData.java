package com.daviancorp.android.data.object;

public class WishlistData {

	private long id;
	private Item item;
	private int quantity;
	
	public WishlistData() {
		this.id = -1;
		this.item = null;
		this.quantity = -1;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
