package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Save {
	String file = "save.xls";
	String user = "test 3";
	boolean newSheet = false;

	public void intialize() {
		try {
			InputStream inp = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(inp);
			if(wb.getSheetIndex(user) == -1) {
				wb.createSheet(user);
				newSheet = true;
			}
			Sheet sheet = wb.getSheet(user);

		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			FileOutputStream out;
			try {
				newSheet = true;
				Workbook wb = new HSSFWorkbook();
				Sheet sheet = wb.createSheet(user);
				out = new FileOutputStream(file);
				wb.write(out);
				out.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// wb.write(out);

		}
	}

	public void save(String[] data) {
		try {
			InputStream inp = new FileInputStream(file);
			System.out.println("past: InputStream ");
			Workbook wb = WorkbookFactory.create(inp);// hier gaat die de mist in WorkbookFactory.create
			System.out.println("past: Workbook");
			
			Sheet sheet = wb.getSheet(user);
			System.out.println("past: Sheet ");
			
			int rowN;
			if (newSheet) {
				rowN = 0;
			} else {
				rowN = sheet.getLastRowNum() + 1;
			}

			Row row = sheet.createRow(rowN);
			System.out.println("past: Row " + rowN);
			// Cell cell = row.getCell(1);

			for (int i = 0; i < data.length; i++) {
				Cell cell = row.createCell(i);
				System.out.println("past: Cell ");
				// String cellContents = cell.getStringCellValue();
				// Modify the cellContents here
				cell.setCellValue(data[i]);
				System.out.println("past: " + data[i]);
			}

			// Write the output to a file
			// cell.setCellValue(cellContents);
			FileOutputStream fileOut = new FileOutputStream(file);
			System.out.println("past: FileOutputStream  ");
			wb.write(fileOut);
			System.out.println("past: write ");
			fileOut.close();
			System.out.println("past: close ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}