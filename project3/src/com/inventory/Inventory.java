package com.inventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

public class Inventory implements InventoryInterface {

	private Hashtable<String, Movie> moviesByName;
	private Hashtable<Integer, Movie> moviesById;
	private static final String MOVIE_NOT_FOUND = "MOVIE_NOT_FOUND";

	@Override
	public Movie addNewMovie(String movieName, double price, int quantity) throws Exception {
		Object existingMovie = findMovieByName(movieName);
		if (existingMovie.equals(MOVIE_NOT_FOUND)) {
			Movie movie = new Movie(movieName, price, quantity);
			addToMap(movie);
			return movie;
		} else {
			return addCopies(movieName, quantity);
		}
	}

	@Override
	public Movie addCopies(String movieName, int quantity) throws Exception {
		Object existingMovie = findMovieByName(movieName);
		if (existingMovie.equals(MOVIE_NOT_FOUND)) {
			throw new Exception(MOVIE_NOT_FOUND);
		} else {
			Movie foundMovie = ((Movie) existingMovie);
			foundMovie.incrementQuantity(quantity);
			return foundMovie;
		}
	}

	@Override
	public Object findPrice(String movieName, int movieId) throws Exception {
		if (movieName != null)
			return findPrice(movieName);
		if (movieId > 0)
			return findPrice(movieId);
		return null;
	}

	@Override
	public Movie changePrice(String movieName, double newPrice) throws Exception {
		Object existingMovie = findMovieByName(movieName);
		if (existingMovie.equals(MOVIE_NOT_FOUND)) {
			throw new Exception(MOVIE_NOT_FOUND);
		}
		return ((Movie) findMovieByName(movieName)).changePrice(newPrice);
	}

	@Override
	public Movie sellMovie(String movieName) throws Exception {
		Object existingMovie = findMovieByName(movieName);
		if (existingMovie.equals(MOVIE_NOT_FOUND)) {
			throw new Exception(MOVIE_NOT_FOUND);
		}
		return ((Movie) findMovieByName(movieName)).sell();
	}

	@Override
	public Object createMemento() {
		return new InventoryMemento(moviesById);
	}

	@Override
	public void setMemento(InventoryMemento memento) {
		this.moviesById = memento.getMoviesById();
		this.moviesByName = getMoviesByName();
	}

	@Override
	public void restoreMemento() {
		Object oldState;
		ObjectInputStream objInStr;
		try {
			objInStr = new ObjectInputStream(new FileInputStream("memento.ser"));
			Object readMemento = objInStr.readObject();
			oldState = readMemento;
			objInStr.close();
			Hashtable<Integer, Movie> memento = (Hashtable<Integer, Movie>) oldState;
			this.moviesById = memento;
			this.moviesByName = getMoviesByName();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean storeMementoToFile(InventoryMemento memento) {
		return memento.storeMementoToFile();
	}

	public static class InventoryMemento implements Serializable {
		private static final long serialVersionUID = -1406621555929544918L;
		private Hashtable<Integer, Movie> moviesById;

		public InventoryMemento(Hashtable<Integer, Movie> stateMap) {
			this.moviesById = new Hashtable<Integer, Movie>();
			for (Movie movie : stateMap.values()) {
				Movie deepCopy = movie.deepCopy();
				this.moviesById.put(deepCopy.getMovieId(), deepCopy);
			}
		}

		private Hashtable<Integer, Movie> getMoviesById() {
			return moviesById;
		}

		private boolean storeMementoToFile() {
			try {
				// Save new memento with different name, if written properly,then rename it
				ObjectOutputStream objStream = new ObjectOutputStream(new FileOutputStream("memento_new.ser"));
				objStream.writeObject(moviesById);
				objStream.close();
				File oldMemento = new File("memento.ser");
				boolean isOldDeleted = true;
				if (oldMemento.exists() && !oldMemento.isDirectory()) {
					isOldDeleted = oldMemento.delete();
				}
				if (isOldDeleted) {
					File newMemento = new File("memento_new.ser");
					newMemento.renameTo(new File("memento.ser"));
				}
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	@Override
	public Object findQuantity(String movieName, int movieId) throws Exception {
		if (movieName != null)
			return findQuantity(movieName);
		if (movieId > 0)
			return findQuantity(movieId);
		return null;
	}

	public boolean equals(Object object) {
		if (object == null || object.getClass() != this.getClass()) {
			return false;
		}
		Inventory inventoryToCompare = (Inventory) object;
		for (Map.Entry<String, Movie> htEntries : inventoryToCompare.moviesByName.entrySet()) {
			if (!moviesByName.containsKey(htEntries.getKey())
					|| !moviesByName.get(htEntries.getKey()).equals(htEntries.getValue())) {
				return false;
			}
		}
		return true;
	}

	private Object findQuantity(String movieName) {
		if (!moviesByName.containsKey(movieName))
			return MOVIE_NOT_FOUND;
		return moviesByName.get(movieName).getQuantity();
	}

	private Object findQuantity(int id) {
		if (!moviesById.containsKey(id))
			return MOVIE_NOT_FOUND;
		return moviesById.get(id).getQuantity();
	}

	private void addToMap(Movie movie) {
		if (moviesById == null) {
			moviesById = new Hashtable<Integer, Movie>();
			moviesByName = new Hashtable<String, Movie>();
		}
		moviesById.put(movie.getMovieId(), movie);
		moviesByName.put(movie.getMovieName(), movie);
	}

	private Object findMovieByName(String movieName) {
		if (moviesByName == null)
			return MOVIE_NOT_FOUND;
		if (!moviesByName.containsKey(movieName))
			return MOVIE_NOT_FOUND;
		return moviesByName.get(movieName);
	}

	private Object findPrice(String movieName) {
		if (!moviesByName.containsKey(movieName))
			return MOVIE_NOT_FOUND;
		return moviesByName.get(movieName).getPrice();
	}

	private Object findPrice(int id) {
		if (!moviesById.containsKey(id))
			return MOVIE_NOT_FOUND;
		return moviesById.get(id).getPrice();
	}

	private Hashtable<String, Movie> getMoviesByName() {
		Hashtable<String, Movie> mapByName = new Hashtable<String, Movie>();
		for (Movie each : moviesById.values()) {
			mapByName.put(each.getMovieName(), each);
		}
		return mapByName;
	}
}
