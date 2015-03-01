package com.inventory;

public class SellCommand implements Command {

	private static final long serialVersionUID = -6743054717364828838L;
	private transient InventoryInterface inventory;
	private String movieName;

	public SellCommand(InventoryInterface inventory, String name) {
		this.inventory = inventory;
		movieName = name;
	}

	@Override
	public Object execute() throws Exception {
		return inventory.sellMovie(movieName);
	}

	@Override
	public void setReceiver(InventoryInterface inventory) {
		this.inventory = inventory;
	}

}
