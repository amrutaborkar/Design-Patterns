package com.inventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.io.AppendableObjectOutputStream;

public class CommandSerializer {

	private int commandsCount = 0;
	private static final int MAX_COMMANDS = 5;
	private static final String COMMANDS_FILE_PATH ="command.txt";

	public boolean storeCommandToFile(Command commandToStore) {
		commandsCount++;
		if (commandsCount <= MAX_COMMANDS) {
			ObjectOutputStream objStream;
			try {
				if (commandsCount == 1)
					objStream = new ObjectOutputStream(new FileOutputStream(COMMANDS_FILE_PATH, true));
				else
					objStream = new AppendableObjectOutputStream(new FileOutputStream(COMMANDS_FILE_PATH, true));
				objStream.writeObject(commandToStore);
				objStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public List<Command> readCommandsFromFile() throws Exception {
		List<Command> commands = new ArrayList<Command>();
		ObjectInputStream objInStr;
		objInStr = new ObjectInputStream(new FileInputStream(COMMANDS_FILE_PATH));
		try {
			while (true) {
				Command readCommand = (Command) objInStr.readObject();
				commands.add(readCommand);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			objInStr.close();
		}
		return commands;
	}

	public void deleteOldCommands() {
		resetCommandsCount();
		File f = new File(COMMANDS_FILE_PATH);
		PrintWriter writer;
		try {
			writer = new PrintWriter(f);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void resetCommandsCount() {
		commandsCount = 0;
	}
}
