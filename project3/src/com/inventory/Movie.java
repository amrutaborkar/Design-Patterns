package com.inventory;

import java.io.Serializable;

public class Movie implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	private String movieName;
	static int numberOfMovies = 0;
	private double price;
	private int quantity = 0;
	private int movieId = 0;

	public Movie(String name, double price, int quantity) {
		this.movieName = name;
		this.price = price;
		this.quantity += quantity;
		numberOfMovies++;
		this.movieId = numberOfMovies;
	}

	public Movie(String name) {
		this.movieName = name;
		this.quantity++;
	}

	void incrementQuantity(int quantityToAdd) {
		this.quantity += quantityToAdd;
	}

	public double getPrice() {
		return price;
	}

	public Movie sell() {
		this.quantity--;
		return this;
	}

	public Movie changePrice(double newPrice) {
		this.price = newPrice;
		return this;
	}

	Movie deepCopy() {
		Movie currentState = new Movie(new String(movieName), new Double(price), new Integer(quantity));
		currentState.movieId = new Integer(movieId);
		numberOfMovies--;
		return currentState;
	}

	public String getMovieName() {
		return movieName;
	}

	public int getMovieId() {
		return movieId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public boolean equals(Object object) {
		if (object == null || object.getClass() != this.getClass()) {
			return false;
		}
		Movie movieToCompare = (Movie) object;
		if (this.movieName.equals(movieToCompare.movieName) && this.price == movieToCompare.price
				&& this.quantity == movieToCompare.quantity) {
			return true;
		}
		return false;
	}
}
