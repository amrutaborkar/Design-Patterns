package com.inventory;

public class AddCopyCommand implements Command {

	private static final long serialVersionUID = 7593818764046454141L;
	private transient InventoryInterface inventory;
	private String movieName;
	private int movieQuantity;

	public AddCopyCommand(InventoryInterface inventory, String name, int quantity) {
		this.inventory = inventory;
		movieName = name;
		movieQuantity = quantity;
	}

	@Override
	public Object execute() throws Exception {
		return inventory.addCopies(movieName, movieQuantity);
	}

	@Override
	public void setReceiver(InventoryInterface inventory) {
		this.inventory = inventory;
	}

}
