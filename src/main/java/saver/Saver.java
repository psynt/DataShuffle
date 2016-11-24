package saver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import content.Item;

/**
 * Interface that all save-to-file classes will implement
 * @author nichita
 * @param <T> Type of Item that will be saved
 *
 */
public interface Saver <T extends Item>{
	
	/**
	 * buffer items
	 * @param items to be buffered
	 */
	void save(ArrayList<T> items);
	
	/**
	 * buffer item
	 * @param item to be buffered
	 */
	void save(T item);
	
	
	/**
	 * output buffered contents to file
	 * @param f file to write to
	 * @throws IOException if problems were wncountered with file handling
	 */
	void writeToFile(File f) throws IOException;
	
}
