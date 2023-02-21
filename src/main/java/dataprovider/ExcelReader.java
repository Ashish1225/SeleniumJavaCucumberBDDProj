package dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	static XSSFSheet sheet =null;
	static XSSFWorkbook workbook = null;
	
	public static Object[][] getDataFromSheet(String sheetName) {
		
		System.out.println("******Loading data from excel*********");
		
		Object[][] data = null;
		
		File file = new File(System.getProperty("user.dir")+"//testdata//LoginIneurone.xlsx");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the file "+e.getMessage());
		}
		
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			System.out.println("Could not load the file "+e.getMessage());
		}
		
		sheet = workbook.getSheet(sheetName);
		
		int ttlRows = sheet.getPhysicalNumberOfRows();
		
		System.out.println("Total rows are :"+ttlRows);
		
		int ttlColumns = sheet.getRow(0).getPhysicalNumberOfCells();
		
		System.out.println("Total Columns are "+ttlColumns);
		
		data=new Object[ttlRows-1][ttlColumns];
		
		for(int r = 1;r<ttlRows;r++) {
			XSSFRow row = sheet.getRow(r);
			
			for(int c = 0;c<ttlColumns;c++) {
				XSSFCell cell = row.getCell(c);
				//data[r-1][c] = getCellType(sheetName,r, c);
				data[r-1][c] = getCellType(cell);
			}		
		}
		return data;
	}
	
	// 1st way to get cell type
	public static String getCellType(String sheetName , int row ,int column) {
		
		sheet = workbook.getSheet(sheetName);
		XSSFRow r = sheet.getRow(row);
		XSSFCell c = r.getCell(column);
		String data = "";
		CellType cellType = c.getCellType();
		switch (cellType) {
		case STRING:
			data=c.getStringCellValue();
			break;
		case NUMERIC:
			data=String.valueOf((int)c.getNumericCellValue());
			break;
		case BLANK:
			data="";
			break;
		case BOOLEAN:
			data = String.valueOf(c.getBooleanCellValue());
			break;
		default:
			data=c.getStringCellValue();
			break;
		}
		
		return data;
		
	}
	
	// 2nd way to get cell type
	public static String getCellType(XSSFCell cell) {
		
		String data ="";
		CellType cellType = cell.getCellType();
		switch (cellType) {
		case STRING:
			data=cell.getStringCellValue();
			break;
		case NUMERIC:
			data=String.valueOf((int)cell.getNumericCellValue());
			break;
		case BLANK:
			data="";
			break;
		case BOOLEAN:
			data = String.valueOf(cell.getBooleanCellValue());
			break;
		default:
			data=cell.getStringCellValue();
			break;
		}
		
		return data;
		
	}

}
