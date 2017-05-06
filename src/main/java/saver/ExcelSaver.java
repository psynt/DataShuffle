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
import java.util.List;
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
	private ArrayList<String> headers;
	
	
	/**
	 * Constructs an object that will save to spreadsheet
	 * @param itemsToSave list of items to be saved
	 * @param threshold minimum Selected-ness for an item/attribute to be printed out
	 */
	public ExcelSaver(Data itemsToSave,int threshold) {
		this.threshold = threshold;
		wb = new HSSFWorkbook();
		s = wb.createSheet();
		headers = new ArrayList<>(Attribute.getAtts().keySet().stream().filter(e -> Attribute.isSel(e,threshold)).collect(Collectors.toList()));
//		headers.add("Group");
		setHeaders(headers);
		save(itemsToSave);
	}

	/**
	 * Constructs an object that will save to spreadsheet with minimum Selected-ness Maybe
	 * @param itemsToSave list of items to be saved
	 */
	public ExcelSaver(Data itemsToSave){
		this(itemsToSave, Selected.Maybe.value());
	}

	
	private void setHeaders(List<String> a){
		Font hfont = wb.createFont();
		hfont.setBold(true);
		CellStyle cs = wb.createCellStyle();
		cs.setFont(hfont);

		Row r=s.createRow(rowcount++);
		Cell c;
		int colcount=1;

		c = r.createCell(0);
		c.setCellStyle(cs);
		c.setCellValue("Group");

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
		items.stream().filter(Item::isSelected).forEach(it -> save(items,it));
	}
	@Override
	public void save(Group g, Item item) {
		Row r=s.createRow(rowcount++);
		Cell c;
		int colcount=1;
		c = r.createCell(0);
		c.setCellValue(g.getName());
		for (String it : headers){
			c = r.createCell(colcount++);
			c.setCellValue(item.get(it));
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
