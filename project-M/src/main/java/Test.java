import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Test {
	public static void main(String[] args) throws IOException {
		try {
			InputStream inp = new FileInputStream("test.xls");
			System.out.println("past: InputStream ");
			Workbook wb = WorkbookFactory.create(inp);// hier gaat die de mist in WorkbookFactory.create
			System.out.println("past: Workbook");
			Sheet sheet = wb.getSheetAt(0);
			System.out.println("past: Sheet ");
			Row row = sheet.createRow(sheet.getLastRowNum()+1);
			System.out.println("past: Row " + sheet.getLastRowNum());
			//Cell cell = row.getCell(1);
			Cell cell = row.createCell(1);
			System.out.println("past: Cell ");
			//String cellContents = cell.getStringCellValue();
			// Modify the cellContents here
			cell.setCellValue("test" + sheet.getLastRowNum());
			System.out.println("past: setCellValue ");
			// Write the output to a file
			//cell.setCellValue(cellContents);
			FileOutputStream fileOut = new FileOutputStream("test.xls");
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
		}
		System.out.println("out: try  ");
	}
}

