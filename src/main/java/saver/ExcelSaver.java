package saver;

import content.Attribute;
import content.Item;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Saves to .xls files
 * @author nichita
 * @param <T> Type of Item that will be saved in this spreadsheet
 */
public class ExcelSaver<T extends Item> implements Saver<T> {
	
	private Workbook wb;
	private Sheet s;
	private int rowcount;
	private int threshold;
	
	
	/**
	 * Constructs an object that will save to spreadsheet
	 * @param itemsToSave list of items to be saved
	 * @param threshold minimum Selected-ness for an item/attribute to be printed out
	 */
	public ExcelSaver(ArrayList<T> itemsToSave,int threshold) {
		this.threshold = threshold;
		wb = new HSSFWorkbook();
		s = wb.createSheet();
		setHeaders(itemsToSave.get(0).getAttributes());
		save(itemsToSave);
	}
	
	/**
	 * Constructs an object that will save to spreadsheet
	 * @param itemToSave item to be saved
	 * @param threshold minimum Selected-ness for an item/attribute to be printed out
	 */
	public ExcelSaver(T itemToSave, int threshold) {
		this.threshold = threshold;
		wb = new HSSFWorkbook();
		s = wb.createSheet();
		setHeaders(itemToSave.getAttributes());
		save(itemToSave);
	}

	/**
	 * Constructs an object that will save to spreadsheet with minimum Selected-ness Maybe
	 * @param itemsToSave list of items to be saved
	 */
	public ExcelSaver(ArrayList<T> itemsToSave){
		this(itemsToSave,0);
	}
	
	/**
	 * Constructs an object that will save to spreadsheet with minimum Selected-ness Maybe
	 * @param itemToSave item to be saved
	 */
	public ExcelSaver(T itemToSave){
		this(itemToSave,0);
	}
	
	
	private void setHeaders(ArrayList<Attribute> a){
		Row r=s.createRow(rowcount++);
		Cell c;
		int colcount=1;
		for(Attribute e :a){
			if(e.getSelected().value()<threshold)
				continue;
			c = r.createCell(colcount++);
			c.setCellValue(e.getName());
		}
	}
	
	@Override
	public void save(ArrayList<T> items) {
		for(T b :items){
			if(b.getSelected().value()<threshold){
				continue;
			}
			save(b);
		}
		
	}
	@Override
	public void save(T item) {
		Row r=s.createRow(rowcount++);
		Cell c;
		c = r.createCell(0);
		c.setCellValue(item.getSelected().toString());
		int colcount=1;
		for(Attribute e :item.getAttributes()){
			if(!(e.getValue() instanceof String) || e.getSelected().value()<threshold)
				continue;
			c = r.createCell(colcount++);
			c.setCellValue(e.getValue().toString());
		}
	}

	@Override
	public void writeToFile(File f) throws IOException {
		f.createNewFile();
		try(FileOutputStream out = new FileOutputStream(f)){
			wb.write(out);
		} catch (IOException e) {
			throw e;
		}
	}

	
}
