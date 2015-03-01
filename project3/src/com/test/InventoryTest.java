package com.test;

import static org.junit.Assert.assertTrue;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.inventory.Inventory;
import com.inventory.Inventory.InventoryMemento;
import com.inventory.Movie;

public class InventoryTest {

	@Test
	public void testAdd() throws Exception {
		Inventory inventory = new Inventory();
		Movie firstMovie = inventory.addNewMovie("testMovie1", 20, 2);
		inventory.addNewMovie("testMovie2", 30, 22);
		assertTrue(inventory.findPrice("testMovie1", 0).equals(20.0));
		assertTrue(inventory.findQuantity(null, firstMovie.getMovieId()).equals(2));
	}

	@Test
	public void testAddCoies() throws Exception {
		Inventory inventory = new Inventory();
		Movie firstMovie = inventory.addNewMovie("testMovie1", 20, 2);
		inventory.addNewMovie("testMovie2", 30, 22);
		inventory.addCopies("testMovie1", 10);
		assertTrue(inventory.findQuantity(null, firstMovie.getMovieId()).equals(12));
	}

	@Test
	public void testFindQuantity() throws Exception {
		Inventory inventory = new Inventory();
		Movie firstMovie = inventory.addNewMovie("testMovie1", 20, 2);
		assertTrue(inventory.findQuantity(null, firstMovie.getMovieId()).equals(2));
		assertTrue(inventory.findQuantity("testMovie1", 0).equals(2));
		assertTrue(inventory.findQuantity("testMovie2", 0).equals("MOVIE_NOT_FOUND"));
	}

	@Test
	public void testFindPrice() throws Exception {
		Inventory inventory = new Inventory();
		Movie firstMovie = inventory.addNewMovie("testMovie1", 20, 2);
		assertTrue(inventory.findPrice(null, firstMovie.getMovieId()).equals(20.0));
		assertTrue(inventory.findPrice("testMovie1", 0).equals(20.0));
		assertTrue(inventory.findPrice("testMovie2", 0).equals("MOVIE_NOT_FOUND"));
	}

	@Test
	public void testSellMovie() throws Exception {
		Inventory inventory = new Inventory();
		Movie firstMovie = inventory.addNewMovie("testMovie1", 20, 2);
		assertTrue(inventory.findQuantity(null, firstMovie.getMovieId()).equals(2));
		inventory.sellMovie("testMovie1");
		assertTrue(inventory.findQuantity(null, firstMovie.getMovieId()).equals(1));
	}

	@Test
	public void testChangePrice() throws Exception {
		Inventory inventory = new Inventory();
		Movie firstMovie = inventory.addNewMovie("testMovie1", 20, 2);
		assertTrue(inventory.findPrice(null, firstMovie.getMovieId()).equals(20.0));
		inventory.changePrice("testMovie1", 30);
		assertTrue(inventory.findPrice(null, firstMovie.getMovieId()).equals(30.0));
	}

	@Test
	public void testSetMemento() throws Exception {
		Inventory inventory = new Inventory();
		Movie firstMovie = inventory.addNewMovie("testMovie1", 20, 2);
		inventory.addNewMovie("testMovie2", 30, 20);
		InventoryMemento memento = (InventoryMemento) inventory.createMemento();
		inventory.addNewMovie("testMovie3", 22, 10);
		assertTrue(inventory.findPrice("testMovie3", 0).equals(22.0));
		assertTrue(inventory.findPrice(null, firstMovie.getMovieId()).equals(20.0));
		inventory.setMemento(memento);
		assertTrue(inventory.findPrice("testMovie3", 0).equals("MOVIE_NOT_FOUND"));
		assertTrue(inventory.findPrice(null, firstMovie.getMovieId()).equals(20.0));
	}

	@Test
	public void testRestoreMemento() throws Exception {
		Inventory inventory = new Inventory();
		Movie firstMovie = inventory.addNewMovie("testMovie1", 20, 2);
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
	public void testFont(){
		Map<Font,String>usedFonts=new HashMap<Font,String>();
		Font font1=new Font("Times New Roman", 2, 10);
		Font font=new Font("Times New Roman", 2, 10);
		usedFonts.put(font1, "aaa");
		System.out.println(usedFonts.containsKey(font));
	}
}
