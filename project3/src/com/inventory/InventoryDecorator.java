package com.inventory;

import java.util.List;

import com.inventory.Inventory.InventoryMemento;

public class InventoryDecorator implements InventoryInterface {
	private InventoryInterface inventoryInterface;
	private CommandSerializer commandSerializer = new CommandSerializer();
	private InventoryMemento memento;

	public InventoryDecorator(InventoryInterface inventoryInterface) {
		this.inventoryInterface = inventoryInterface;
	}

	@Override
	public Movie addNewMovie(String movieName, double price, int quantity) throws Exception {
		Command addCommand = new AddCommand(inventoryInterface, movieName, quantity, price);
		Movie addedMovie = (Movie) addCommand.execute();
		if (!commandSerializer.storeCommandToFile(addCommand)) {
			// Commands.txt is full , so create new memento and empty commands.txt
			InventoryMemento memento = (InventoryMemento) createMemento();
			if (inventoryInterface.storeMementoToFile(memento)) {
				commandSerializer.deleteOldCommands();
			} else {
				throw new Exception("Memento Could not be stored");
			}
		}
		return addedMovie;
	}

	@Override
	public Movie addCopies(String movieName, int quantity) throws Exception {
		Command addCopyCommand = new AddCopyCommand(inventoryInterface, movieName, quantity);
		Movie addedMovie = (Movie) addCopyCommand.execute();
		if (!commandSerializer.storeCommandToFile(addCopyCommand)) {
			InventoryMemento memento = (InventoryMemento) createMemento();
			if (inventoryInterface.storeMementoToFile(memento)) {
				commandSerializer.deleteOldCommands();
			} else {
				throw new Exception("Memento could not be stored");
			}
		}
		return addedMovie;
	}

	@Override
	public Object findPrice(String movieName, int id) throws Exception {
		return inventoryInterface.findPrice(movieName, id);
	}

	@Override
	public Movie sellMovie(String movieName) throws Exception {
		Command sellCommand = new SellCommand(inventoryInterface, movieName);
		Movie soldMovie = (Movie) sellCommand.execute();
		if (!commandSerializer.storeCommandToFile(sellCommand)) {
			InventoryMemento memento = (InventoryMemento) createMemento();
			if (inventoryInterface.storeMementoToFile(memento)) {
				commandSerializer.deleteOldCommands();
			} else {
				throw new Exception("Memento could not be stored");
			}
		}
		return soldMovie;
	}

	@Override
	public Movie changePrice(String movieName, double newPrice) throws Exception {
		Command changePriceCommand = new ChangePriceCommand(inventoryInterface, movieName, newPrice);
		Movie modifiedMovie = (Movie) changePriceCommand.execute();
		if (!commandSerializer.storeCommandToFile(changePriceCommand)) {
			InventoryMemento memento = (InventoryMemento) createMemento();
			if (inventoryInterface.storeMementoToFile(memento)) {
				commandSerializer.deleteOldCommands();
			} else {
				throw new Exception("Memento could not be stored");
			}
		}
		return modifiedMovie;
	}

	@Override
	public Object findQuantity(String movieName, int id) throws Exception {
		return inventoryInterface.findQuantity(movieName, id);
	}

	@Override
	public Object createMemento() {
		memento = (InventoryMemento) inventoryInterface.createMemento();
		return memento;
	}

	@Override
	public void restoreMemento() {
		inventoryInterface.restoreMemento();
	}

	@Override
	public void setMemento(InventoryMemento memento) {
		inventoryInterface.setMemento(memento);
	}

	public InventoryInterface restoreCheckPoint() throws Exception {
		List<Command> commandsToPerform = commandSerializer.readCommandsFromFile();
		inventoryInterface.restoreMemento();
		for (Command command : commandsToPerform) {
			command.setReceiver(inventoryInterface);
			command.execute();
		}
		return inventoryInterface;
	}

	@Override
	public boolean storeMementoToFile(InventoryMemento memento) {
		if (this.memento != null && this.memento.equals(memento)) {
			commandSerializer.deleteOldCommands();
			return inventoryInterface.storeMementoToFile(memento);
		}
		return false;
	}

	public boolean equals(Object object) {
		if (object == null || object.getClass() != this.getClass()) {
			return false;
		}
		InventoryDecorator decorator = (InventoryDecorator) object;
		return inventoryInterface.equals(decorator.inventoryInterface);

	}
}
