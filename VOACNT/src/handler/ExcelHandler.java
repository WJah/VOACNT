package handler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelHandler
{
	Workbook workbook;
	public ExcelHandler(String fileName)
	{
		// TODO Auto-generated constructor stub
		try
		{
			workbook = Workbook.getWorkbook(new File(fileName));
		} catch (BiffException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<Byte, Double> getATTMap()
	{
		Map<Byte, Double> ATTmap = new HashMap<Byte, Double>();
		Sheet sheet = workbook.getSheet(0);
		for (int i = 0; i < Constants.EFF_ATT; i++)
		{
			ATTmap.put(Byte.valueOf((byte)i), Double.valueOf(sheet.getCell(2, i).getContents()));
		}
		return ATTmap;
	}
	
	public int getFileLength()
	{
		return workbook.getSheet(0).getRows();
	}
	
	public String[] getFile()
	{
		String[] file = new String[getFileLength()];
		for (int i = 0; i < getFileLength(); i++)
		{
			file[i] = workbook.getSheet(0).getCell(0, i).getContents();
		}
		return file;
	}
}
