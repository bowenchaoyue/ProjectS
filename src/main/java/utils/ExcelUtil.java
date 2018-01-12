package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
	
	private final static Logger log = LoggerFactory.getLogger(ExcelUtil.class);  
	/**
	 * 写入Excel
	 * @param file 文件对象
	 * @param data 数据对象
	 * @param startRowIndex 开始行索引
	 * @return 是否成功
	 */
	public static boolean writeExcel(File file, List<String[]> data, int startRowIndex){
		return writeExcel(file, data, 0, startRowIndex);
	}
	/**
	 * 写入Excel
	 * @param file 文件对象
	 * @param data 数据对象
	 * @param sheetIndex sheet页索引
	 * @param startRowIndex 开始行索引
	 * @return 是否成功
	 */
	public static  boolean writeExcel(File file, List<String[]> data, int sheetIndex, int startRowIndex){
		//获取文件流
		InputStream is = null;
		FileOutputStream os = null;
		Workbook workbook = null;
		try {
			is = new FileInputStream(file);
			workbook = getWorkBook(file.getName(), is); 
			if(workbook != null){
				 Sheet sheet = workbook.getSheetAt(sheetIndex);  
				 //遍历行
				for(String[] rowData:data){
					int currRowIndex = startRowIndex++;
					Row row =  sheet.getRow(currRowIndex);
					 if(row == null){
						 row = sheet.createRow(currRowIndex);
					 }
					 //遍历列
					 for(int i = 0, length = rowData.length; i<length; i++){
						 Cell cell = row.getCell(i);
						 if(cell ==null){
							 cell = row.createCell(i);
						 }
						 cell.setCellValue(rowData[i]);
					 }
				 }
				 closeInputStream(is);
				 os = new FileOutputStream(file);
				 workbook.write(os);
			}else{
				log.info("Bad file mode!");  
				return false;
			}
		} catch (FileNotFoundException e) {
			log.info("File not found!r\n"+e.getMessage());  
			return false;
		}catch(IOException e){
			log.info("Failed to initialize workbook!\r\n"+e.getMessage());  
			return false;
		}finally{
			closeInputStream(is);
			closeOutputStream(os);
			closeWorkbook(workbook);
		}
		return true;		
	}
	/**
	 * 读取Excel
	 * @params file 文件对象, 从第1行开始
	 */
	public static  List<String[]> readExcel(File file){
		return readExcel(file, 0);
	}
	/**
	 * 读取Excel
	 * @params file 文件对象
	 * @params startRowIndex 起始行索引，从0开始
	 */
	public static  List<String[]> readExcel(File file, int startRowIndex){
		//获取文件流
		InputStream is = null;
		Workbook workbook = null;
		try {
			is = new FileInputStream(file);
			workbook = getWorkBook(file.getName(), is); 
			if(workbook != null){
				 List<String[]> list = new ArrayList<String[]>();  
				 for(int sheetNum = 0,  sheetMaxNum =workbook.getNumberOfSheets();sheetNum <sheetMaxNum ;sheetNum++){  
		                //获得当前sheet工作表  
		                Sheet sheet = workbook.getSheetAt(sheetNum);  
		                if(sheet == null){  
		                    continue;  
		                }   
		                //获得当前sheet的结束行  
		                int lastRowNum = sheet.getLastRowNum();  
		                //循环除了第一行的所有行  
		                for(int rowNum = startRowIndex;rowNum <= lastRowNum;rowNum++){  
		                    //获得当前行  
		                    Row row = sheet.getRow(rowNum);  
		                    if(row == null){  
		                        continue;  
		                    }  
		                    //获得当前行的开始列  
		                    int firstCellNum = row.getFirstCellNum();  
		                    //获得当前行的列数  
		                    int lastCellNum = row.getPhysicalNumberOfCells();  
		                    String[] cells = new String[row.getPhysicalNumberOfCells()];  
		                    //循环当前行  
		                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){  
		                        Cell cell = row.getCell(cellNum);  
		                        cells[cellNum] = getCellValue(cell);  
		                    }  
		                    list.add(cells);  
		                }  
		            }
				 	return list;
			}else{
				log.info("Bad file mode!");  
				return null;
			}
		} catch (FileNotFoundException e) {
			log.info("File not found!r\n"+e.getMessage());  
			return null;
		}catch(IOException e){
			log.info("Failed to initialize workbook!\r\n"+e.getMessage());  
			return null;
		}finally{
			closeInputStream(is);
			closeWorkbook(workbook);
		}
	}
	/**
	 * 获取Workbook对象
	 * @return
	 */
	private static  Workbook getWorkBook(String fileName, InputStream is) throws IOException{  
		 Workbook workbook = null;  
		//根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象  
         if(fileName.endsWith("xls")){  
             //2003  
             workbook = new HSSFWorkbook(is);  
         }else if(fileName.endsWith("xlsx")){  
             //2007  
             workbook = new XSSFWorkbook(is);  
         }         
	    return workbook;  
	}
	private static String getCellValue(Cell cell){  
        String cellValue = "";  
        if(cell == null){  
            return cellValue;  
        }  
        //把数字当成String来读，避免出现1读成1.0的情况  
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
            cell.setCellType(Cell.CELL_TYPE_STRING);  
        }  
        //判断数据的类型  
        switch (cell.getCellType()){  
            case Cell.CELL_TYPE_NUMERIC: //数字  
                cellValue = String.valueOf(cell.getNumericCellValue());  
                break;  
            case Cell.CELL_TYPE_STRING: //字符串  
                cellValue = String.valueOf(cell.getStringCellValue());  
                break;  
            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
                cellValue = String.valueOf(cell.getBooleanCellValue());  
                break;  
            case Cell.CELL_TYPE_FORMULA: //公式  
                cellValue = String.valueOf(cell.getCellFormula());  
                break;  
            case Cell.CELL_TYPE_BLANK: //空值   
                cellValue = "";  
                break;  
            case Cell.CELL_TYPE_ERROR: //故障  
                cellValue = "";  
                break;  
            default:  
                cellValue = "";  
                break;  
        }  
        return cellValue;  
    }  
	/**
	 * 关闭输入流
	 * @param is
	 */
	private static  void closeInputStream(InputStream inputStream){
		if(inputStream != null){
			try {
				inputStream.close();
			} catch (IOException e) {
				log.info("Fail  close inputstream!r\n"+e.getMessage());  
			}	
		}
	}
	/**
	 * 关闭输出流
	 * @param is
	 */
	private static  void closeOutputStream(FileOutputStream os){
		if(os != null){
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				log.info("Fail  close inputstream!r\n"+e.getMessage());  
			}	
		}
	}
	/**
	 * 关闭工作簿对象
	 * @param workbook
	 */
	private static  void closeWorkbook(Workbook workbook){
		if(workbook != null){
			try {
				workbook.close();
				workbook = null;
			} catch (IOException e) {
				log.info("Fail to close workbook!r\n"+e.getMessage());  
			}
		}
	}







	public static void main(String[] args){
		File file = new File("F:/1.xlsx");
		try {
			InputStream is = new FileInputStream(file);
			ExcelUtil.getWorkBook("1.xlsx", is);
			is.close();
			OutputStream os = new FileOutputStream(file);
			os.close();
			System.out.print("success");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
