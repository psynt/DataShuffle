package saver;

import content.Attribute;
import content.Group;
import content.Item;
import content.Selected;
import model.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.w3c.dom.Attr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static content.Attribute.isSel;


/**
 * Saves to .xls files
 * @author nichita
 */
public class ExcelSaver implements Saver {
	
	private Workbook wb;
	private Sheet s;
	private int rowcount;
	private int threshold;
	
	
	/**
	 * Constructs an object that will save to spreadsheet
	 * @param itemsToSave list of items to be saved
	 * @param threshold minimum Selected-ness for an item/attribute to be printed out
	 */
	public ExcelSaver(Data itemsToSave,int threshold) {
		this.threshold = threshold;
		wb = new HSSFWorkbook();
		s = wb.createSheet();
		Set<String> headers = Attribute.getAtts().keySet().stream().filter(e -> Attribute.isSel(e,threshold)).collect(Collectors.toSet());
//		headers.add("Group");
		setHeaders(headers);
		save(itemsToSave);
	}
	
//	/**
//	 * Constructs an object that will save to spreadsheet
//	 * @param itemToSave item to be saved
//	 * @param threshold minimum Selected-ness for an item/attribute to be printed out
//	 */
//	public ExcelSaver(T itemToSave, int threshold) {
//		this.threshold = threshold;
//		wb = new HSSFWorkbook();
//		s = wb.createSheet();
//		setHeaders(itemToSave.getAttributes());
//		save(itemToSave);
//	}

	/**
	 * Constructs an object that will save to spreadsheet with minimum Selected-ness Maybe
	 * @param itemsToSave list of items to be saved
	 */
	public ExcelSaver(Data itemsToSave){
		this(itemsToSave, Selected.Maybe.value());
	}
	
//	/**
//	 * Constructs an object that will save to spreadsheet with minimum Selected-ness Maybe
//	 * @param itemToSave item to be saved
//	 */
//	public ExcelSaver(T itemToSave){
//		this(itemToSave,0);
//	}
	
	
	private void setHeaders(Set<String> a){
		Font hfont = wb.createFont();
		hfont.setBold(true);
		CellStyle cs = wb.createCellStyle();
		cs.setFont(hfont);

		Row r=s.createRow(rowcount++);
		Cell c;
		int colcount=1;

		for(String e :a){
			if(!isSel(e,threshold))
				continue;
			c = r.createCell(colcount++);
			c.setCellStyle(cs);
			c.setCellValue(e);
		}
	}

	@Override
	public void save(Data model) {
		model.forEach(this::save);
	}

	@Override
	public void save(Group items) {
		for(Item b :items){
			if(b.getSelected().value()<threshold){
				continue;
			}
			save(b);
		}
		
	}
	@Override
	public void save(Item item) {
		Row r=s.createRow(rowcount++);
		Cell c;
//		c = r.createCell(0);
//		c.setCellValue(item.getSelected().toString());
		int colcount=1;
		for(Map.Entry<String,String> e : item.getAttributes().entrySet()){
			if(!isSel(e.getKey(), threshold))
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
