package com.inventory;

import java.io.Serializable;

public interface Command extends Serializable {

	Object execute() throws Exception;

	void setReceiver(InventoryInterface inventory);
}
