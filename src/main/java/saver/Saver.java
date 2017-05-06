package saver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import content.Group;
import content.Item;
import model.Data;

/**
 * Interface that all save-to-file classes will implement
 * @author nichita
 *
 */
public interface Saver{

	/**
	 * buffer entire model
	 * @param model to be buffered
	 */
	void save(Data model);
	
	/**
	 * buffer items
	 * @param items to be buffered
	 */
	void save(Group items);
	
	/**
	 * buffer item
	 * @param item to be buffered
	 */
	void save(Item item);
	
	
	/**
	 * output buffered contents to file
	 * @param f file to write to
	 * @throws IOException if problems were encountered with file handling
	 */
	void writeToFile(File f) throws IOException;
	
}
