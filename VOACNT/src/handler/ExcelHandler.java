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
	public ExcelHandler()
	{
		// TODO Auto-generated constructor stub
		try
		{
			workbook = Workbook.getWorkbook(new File(Constants.EXCEL));
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
}
