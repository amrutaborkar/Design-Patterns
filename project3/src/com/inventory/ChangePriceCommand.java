package com.inventory;

public class ChangePriceCommand implements Command {

	private static final long serialVersionUID = -5757893773872086564L;
	private transient InventoryInterface inventory;
	private String movieName;
	private double price;

	public ChangePriceCommand(InventoryInterface inventory, String name, double price) {
		this.inventory = inventory;
		movieName = name;
		this.price = price;
	}

	@Override
	public Object execute() throws Exception {
		return inventory.changePrice(movieName, price);
	}

	@Override
	public void setReceiver(InventoryInterface inventory) {
		this.inventory = inventory;
	}

}
