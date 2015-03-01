package com.inventory;

import com.inventory.Inventory.InventoryMemento;


public interface InventoryInterface {

	Movie addNewMovie(String movieName,double price,int quantity) throws Exception;
	Movie addCopies(String movieName,int quantity) throws Exception;
	Object findPrice(String movieName,int id) throws Exception;
	Movie sellMovie(String movieName) throws Exception;
	Movie changePrice(String movieName, double newPrice) throws Exception;
	Object findQuantity(String movieName,int id) throws Exception;
	Object createMemento();
	void restoreMemento();
	void setMemento(InventoryMemento memento);
	boolean storeMementoToFile(InventoryMemento memento);
}
