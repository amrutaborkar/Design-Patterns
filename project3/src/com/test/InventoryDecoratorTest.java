package com.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.inventory.Inventory;
import com.inventory.InventoryDecorator;
import com.inventory.InventoryInterface;
import com.inventory.Movie;
import com.inventory.Inventory.InventoryMemento;

public class InventoryDecoratorTest {

	@Test
	public void testAdd() throws Exception {
		InventoryInterface movieInventory = new Inventory();
		InventoryDecorator inventory = new InventoryDecorator(movieInventory);
		inventory.addNewMovie("testMovie1", 32.4, 20);
		assertTrue(inventory.findPrice("testMovie1", 0).equals(32.4));
	}

	@Test
	public void testAddCoies() throws Exception {
		InventoryInterface movieInventory = new Inventory();
		InventoryDecorator inventory = new InventoryDecorator(movieInventory);
		inventory.addNewMovie("testMovie1", 32.4, 20);
		inventory.addCopies("testMovie1", 10);
		assertTrue(inventory.findQuantity("testMovie1", 0).equals(30));
	}

	@Test
	public void testFindQuantity() throws Exception {
		InventoryInterface movieInventory = new Inventory();
		InventoryDecorator inventory = new InventoryDecorator(movieInventory);
		Movie firstMovie = inventory.addNewMovie("testMovie1", 32.4, 20);
		assertTrue(inventory.findQuantity(null, firstMovie.getMovieId()).equals(20));
		assertTrue(inventory.findQuantity("testMovie1", 0).equals(20));
		assertTrue(inventory.findQuantity("testMovie2", 0).equals("MOVIE_NOT_FOUND"));
	}

	@Test
	public void testFindPrice() throws Exception {
		InventoryInterface movieInventory = new Inventory();
		InventoryDecorator inventory = new InventoryDecorator(movieInventory);
		Movie firstMovie = inventory.addNewMovie("testMovie1", 32.4, 20);
		assertTrue(inventory.findPrice(null, firstMovie.getMovieId()).equals(32.4));
		assertTrue(inventory.findPrice("testMovie1", 0).equals(32.4));
		assertTrue(inventory.findPrice("testMovie2", 0).equals("MOVIE_NOT_FOUND"));
	}

	@Test
	public void testSellMovie() throws Exception {
		InventoryInterface movieInventory = new Inventory();
		InventoryDecorator inventory = new InventoryDecorator(movieInventory);
		Movie firstMovie = inventory.addNewMovie("testMovie1", 32.4, 20);
		assertTrue(inventory.findQuantity(null, firstMovie.getMovieId()).equals(20));
		inventory.sellMovie("testMovie1");
		assertTrue(inventory.findQuantity(null, firstMovie.getMovieId()).equals(19));
	}

	@Test
	public void testChangePrice() throws Exception {
		InventoryInterface movieInventory = new Inventory();
		InventoryDecorator inventory = new InventoryDecorator(movieInventory);
		Movie firstMovie = inventory.addNewMovie("testMovie1", 32.4, 20);
		assertTrue(inventory.findPrice(null, firstMovie.getMovieId()).equals(32.4));
		inventory.changePrice("testMovie1", 30);
		assertTrue(inventory.findPrice(null, firstMovie.getMovieId()).equals(30.0));
	}

	@Test
	public void testSetMemento() throws Exception {
		InventoryInterface movieInventory = new Inventory();
		InventoryDecorator inventory = new InventoryDecorator(movieInventory);
		Movie firstMovie = inventory.addNewMovie("testMovie3", 20, 2);
		inventory.addNewMovie("testMovie2", 30, 20);
		InventoryMemento memento = (InventoryMemento) inventory.createMemento();
		inventory.addNewMovie("testMovie4", 22, 10);
		assertTrue(inventory.findPrice("testMovie4", 0).equals(22.0));
		assertTrue(inventory.findPrice(null, firstMovie.getMovieId()).equals(20.0));
		inventory.setMemento(memento);
		assertTrue(inventory.findPrice("testMovie4", 0).equals("MOVIE_NOT_FOUND"));
		assertTrue(inventory.findPrice(null, firstMovie.getMovieId()).equals(20.0));
	}

	@Test
	public void testRestoreMemento() throws Exception {
		InventoryInterface movieInventory = new Inventory();
		InventoryDecorator inventory = new InventoryDecorator(movieInventory);
		Movie firstMovie = inventory.addNewMovie("testMovie3", 20, 2);
		Movie secondMovie = inventory.addNewMovie("testMovie2", 30, 20);
		InventoryMemento memento = (InventoryMemento) inventory.createMemento();
		inventory.storeMementoToFile(memento);
		inventory.addNewMovie("testMovie4", 30, 20);
		Inventory newInventory = new Inventory();
		newInventory.restoreMemento();
		assertTrue(newInventory.findPrice(null, firstMovie.getMovieId()).equals(20.0));
		assertTrue(newInventory.findPrice(null, secondMovie.getMovieId()).equals(30.0));
	}

	@Test
	public void testRestoreCheckpoint() throws Exception {
		InventoryInterface movieInventory = new Inventory();
		InventoryDecorator inventory = new InventoryDecorator(movieInventory);
		Movie firstMovie = inventory.addNewMovie("testMovie3", 20, 2);
		Movie secondMovie = inventory.addNewMovie("testMovie2", 30, 20);
		InventoryMemento memento = (InventoryMemento) inventory.createMemento();
		inventory.storeMementoToFile(memento);
		inventory.addNewMovie("testMovie4", 30, 20);
		Inventory newInventory = new Inventory();
		newInventory.restoreMemento();
		assertTrue(newInventory.findPrice(null, firstMovie.getMovieId()).equals(20.0));
		assertTrue(newInventory.findPrice(null, secondMovie.getMovieId()).equals(30.0));
		assertTrue(newInventory.findPrice("testMovie4", 0).equals("MOVIE_NOT_FOUND"));
		InventoryDecorator decorator = new InventoryDecorator(newInventory);
		decorator.restoreCheckPoint();
		assertTrue(decorator.findPrice("testMovie4", 0).equals(30.0));
	}

	@Test
	public void testRestoreMementoWhenCommandsExceedMaxLimit() throws Exception {
		InventoryInterface movieInventory = new Inventory();
		InventoryDecorator originalInventory = new InventoryDecorator(movieInventory);
		originalInventory.addNewMovie("testMovie1", 20, 2);
		originalInventory.addNewMovie("testMovie2", 30, 20);
		originalInventory.changePrice("testMovie1", 33);
		originalInventory.changePrice("testMovie2", 40);
		originalInventory.addNewMovie("testMovie3", 100, 10);
		originalInventory.addNewMovie("testMovie4", 44.32, 10);
		originalInventory.addNewMovie("testMovie5", 29.45, 50);
		Inventory newInventory = new Inventory();
		newInventory.restoreMemento();
		assertTrue(newInventory.findPrice("testMovie4", 0).equals(44.32));
		assertTrue(newInventory.findPrice("testMovie1", 0).equals(33.0));
		assertTrue(newInventory.findPrice("testMovie5", 0).equals("MOVIE_NOT_FOUND"));
		InventoryDecorator recoveredInventory = new InventoryDecorator(newInventory);
		recoveredInventory.restoreCheckPoint();
		assertTrue(recoveredInventory.findPrice("testMovie5", 0).equals(29.45));
		assertTrue(recoveredInventory.equals(originalInventory));
	}
}
