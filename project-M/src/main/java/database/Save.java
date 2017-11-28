package database;

import org.apache.poi.xssf.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Map;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Simple Loan Calculator. Demonstrates advance usage of cell formulas and named
 * ranges.
 *
 * Usage: LoanCalculator -xls|xlsx
 *
 * @author Yegor Kozlov
 */
public class Save {

	public static void main(String[] args) throws Exception {
		Workbook wb;
		int newRow;

		try {
			FileInputStream fileInputStream = new FileInputStream("Data.xlsx");
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheet("Data");
			HSSFRow row1 = worksheet.getRow(0);
			HSSFCell cellA1 = row1.getCell(0);
			int a1Val = (int) cellA1.getNumericCellValue();
			newRow = a1Val;
			System.out.println("A1: " + a1Val);
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (args.length > 0 && args[0].equals("-xls"))
			wb = new HSSFWorkbook();
		else
			wb = new HSSFWorkbook();

		Map<String, CellStyle> styles = createStyles(wb);
		Sheet sheet = wb.createSheet("Data");

		
		createNames(wb);

		
		Row row = sheet.createRow(2);
		Cell cell = row.createCell(4);
		cell.setCellValue("n");
		cell.setCellStyle(styles.get("item_right"));

		
		  row = sheet.createRow(3); cell = row.createCell(2);
		  cell.setCellValue("Loan amount"); cell.setCellStyle(styles.get("item_left"));
		  cell = row.createCell(4); cell.setCellStyle(styles.get("input_$"));
		  cell.setAsActiveCell(); row = sheet.createRow(4); cell = row.createCell(2);
		  cell.setCellValue("Annual interest rate");
		  cell.setCellStyle(styles.get("item_left")); cell = row.createCell(4);
		  cell.setCellStyle(styles.get("input_%"));
		  
		  row = sheet.createRow(5); cell = row.createCell(2);
		  cell.setCellValue("Loan period in years");
		  cell.setCellStyle(styles.get("item_left")); cell = row.createCell(4);
		  cell.setCellStyle(styles.get("input_i"));
		  
		  row = sheet.createRow(6); cell = row.createCell(2);
		  cell.setCellValue("Start date of loan");
		  cell.setCellStyle(styles.get("item_left")); cell = row.createCell(4);
		  cell.setCellStyle(styles.get("input_d"));
		  
		  row = sheet.createRow(9); cell = row.createCell(3);
		  cell.setCellValue("Monthly payment");
		  cell.setCellStyle(styles.get("item_left")); cell = row.createCell(4);
		  cell.setCellFormula("IF(Values_Entered,Monthly_Payment,\"\")");
		  cell.setCellStyle(styles.get("formula_$"));
		  
		  row = sheet.createRow(10); cell = row.createCell(3);
		  cell.setCellValue("Number of payments");
		  cell.setCellStyle(styles.get("item_left")); cell = row.createCell(4);
		  cell.setCellFormula("IF(Values_Entered,Loan_Years*12,\"\")");
		  cell.setCellStyle(styles.get("formula_i"));
		  
		  row = sheet.createRow(11); cell = row.createCell(3);
		  cell.setCellValue("Total interest");
		  cell.setCellStyle(styles.get("item_left")); cell = row.createCell(4);
		  cell.setCellFormula("IF(Values_Entered,Total_Cost-Loan_Amount,\"\")");
		  cell.setCellStyle(styles.get("formula_$"));
		  
		  row = sheet.createRow(12); cell = row.createCell(3);
		  cell.setCellValue("Total cost of loan");
		  cell.setCellStyle(styles.get("item_left")); cell = row.createCell(4);
		  cell.setCellFormula(
		  "IF(Values_Entered,Monthly_Payment*Number_of_Payments,\"\")");
		  cell.setCellStyle(styles.get("formula_$"));
		

		// Write the output to a file
		String file = "Data.xlsx";
		if (wb instanceof HSSFWorkbook)
			file += "x";
		FileOutputStream out = new FileOutputStream(file);
		wb.write(out);
		out.close();
	}

	/**
	 * cell styles used for formatting calendar sheets
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<>();

		CellStyle style;
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 14);
		titleFont.setFontName("Trebuchet MS");
		style = wb.createCellStyle();
		style.setFont(titleFont);
		// style.setBorderBottom(BorderStyle.DOTTED);
		style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		styles.put("title", style);

		Font itemFont = wb.createFont();
		itemFont.setFontHeightInPoints((short) 9);
		itemFont.setFontName("Trebuchet MS");
		style = wb.createCellStyle();
		// style.setAlignment(HorizontalAlignment.LEFT);
		style.setFont(itemFont);
		styles.put("item_left", style);

		style = wb.createCellStyle();
		// style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFont(itemFont);
		styles.put("item_right", style);

		style = wb.createCellStyle();
		// style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFont(itemFont);
		// style.setBorderRight(BorderStyle.DOTTED);
		style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderBottom(BorderStyle.DOTTED);
		style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderLeft(BorderStyle.DOTTED);
		style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderTop(BorderStyle.DOTTED);
		style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setDataFormat(wb.createDataFormat().getFormat("_($* #,##0.00_);_($* (#,##0.00);_($* \"-\"??_);_(@_)"));
		styles.put("input_$", style);

		style = wb.createCellStyle();
		// style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFont(itemFont);
		// style.setBorderRight(BorderStyle.DOTTED);
		style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderBottom(BorderStyle.DOTTED);
		style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderLeft(BorderStyle.DOTTED);
		style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderTop(BorderStyle.DOTTED);
		style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setDataFormat(wb.createDataFormat().getFormat("0.000%"));
		styles.put("input_%", style);

		style = wb.createCellStyle();
		// style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFont(itemFont);
		// style.setBorderRight(BorderStyle.DOTTED);
		style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderBottom(BorderStyle.DOTTED);
		style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderLeft(BorderStyle.DOTTED);
		style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderTop(BorderStyle.DOTTED);
		style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setDataFormat(wb.createDataFormat().getFormat("0"));
		styles.put("input_i", style);

		style = wb.createCellStyle();
		// style.setAlignment(HorizontalAlignment.CENTER);
		style.setFont(itemFont);
		style.setDataFormat(wb.createDataFormat().getFormat("m/d/yy"));
		styles.put("input_d", style);

		style = wb.createCellStyle();
		// style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFont(itemFont);
		// style.setBorderRight(BorderStyle.DOTTED);
		style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderBottom(BorderStyle.DOTTED);
		style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderLeft(BorderStyle.DOTTED);
		style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderTop(BorderStyle.DOTTED);
		style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setDataFormat(wb.createDataFormat().getFormat("$##,##0.00"));
		// style.setBorderBottom(BorderStyle.DOTTED);
		style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		// style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styles.put("formula_$", style);

		style = wb.createCellStyle();
		// style.setAlignment(HorizontalAlignment.RIGHT);
		style.setFont(itemFont);
		// style.setBorderRight(BorderStyle.DOTTED);
		style.setRightBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderBottom(BorderStyle.DOTTED);
		style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderLeft(BorderStyle.DOTTED);
		style.setLeftBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		// style.setBorderTop(BorderStyle.DOTTED);
		style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setDataFormat(wb.createDataFormat().getFormat("0"));
		// style.setBorderBottom(BorderStyle.DOTTED);
		style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		// style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styles.put("formula_i", style);

		return styles;
	}

	// define named ranges for the inputs and formulas
	public static void createNames(Workbook wb) {
		Name name;

		name = wb.createName();
		name.setNameName("Interest_Rate");
		name.setRefersToFormula("'Loan Calculator'!$E$5");

		name = wb.createName();
		name.setNameName("Loan_Amount");
		name.setRefersToFormula("'Loan Calculator'!$E$4");

		name = wb.createName();
		name.setNameName("Loan_Start");
		name.setRefersToFormula("'Loan Calculator'!$E$7");

		name = wb.createName();
		name.setNameName("Loan_Years");
		name.setRefersToFormula("'Loan Calculator'!$E$6");

		name = wb.createName();
		name.setNameName("Number_of_Payments");
		name.setRefersToFormula("'Loan Calculator'!$E$10");

		name = wb.createName();
		name.setNameName("Monthly_Payment");
		name.setRefersToFormula("-PMT(Interest_Rate/12,Number_of_Payments,Loan_Amount)");

		name = wb.createName();
		name.setNameName("Total_Cost");
		name.setRefersToFormula("'Loan Calculator'!$E$12");

		name = wb.createName();
		name.setNameName("Total_Interest");
		name.setRefersToFormula("'Loan Calculator'!$E$11");

		name = wb.createName();
		name.setNameName("Values_Entered");
		name.setRefersToFormula("IF(Loan_Amount*Interest_Rate*Loan_Years*Loan_Start>0,1,0)");
	}
}