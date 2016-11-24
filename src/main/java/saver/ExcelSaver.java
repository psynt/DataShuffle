package saver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import content.Attribute;
import content.Item;

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
	 */
	public ExcelSaver(ArrayList<T> itemsToSave) {
		threshold = 0;
		wb = new HSSFWorkbook();
		s = wb.createSheet();
		setHeaders(itemsToSave.get(0).getAttributes());
		save(itemsToSave);
	}
	public ExcelSaver(T itemToSave) {
		threshold = 0;
		wb = new HSSFWorkbook();
		s = wb.createSheet();
		setHeaders(itemToSave.getAttributes());
		save(itemToSave);
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
			if(e.getSelected().value()<threshold)
				continue;
			c = r.createCell(colcount++);
			c.setCellValue(e.getValue());
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
