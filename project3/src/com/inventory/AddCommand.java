package com.inventory;

public class AddCommand implements Command {

	private static final long serialVersionUID = 6274522932505759135L;

	private transient InventoryInterface inventory;

	private String movieName;
	private int movieQuantity;
	private double moviePrice;

	public void setInventory(InventoryInterface inventory) {
		this.inventory = inventory;
	}

	public AddCommand(InventoryInterface inventory, String name, int quantity, double price) throws Exception {
		this.inventory = inventory;
		movieName = name;
		movieQuantity = quantity;
		moviePrice = price;
	}

	@Override
	public Movie execute() throws Exception {
		return inventory.addNewMovie(movieName, moviePrice, movieQuantity);
	}

	@Override
	public void setReceiver(InventoryInterface inventory) {
		this.inventory = inventory;
	}
}
