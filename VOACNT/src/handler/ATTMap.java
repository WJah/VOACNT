package handler;

import java.util.HashMap;
import java.util.Map;

public class ATTMap
{
	Map<Byte, Double> ATTs = new HashMap<Byte,Double>();
	ExcelHandler excelHandler = new ExcelHandler(Constants.EXCEL);
	byte TEMP = 0x00;
	public ATTMap()
	{		
		ATTs = excelHandler.getATTMap();
	}
	
	public byte getBestATT(double att)
	{
		double min_diff_abs = Math.abs(att - ATTs.get(TEMP));
		byte temp_byte = 0x00;
		for (int i = 1; i < ATTs.size(); i++)
		{
			byte b = Byte.valueOf((byte)i);
			double temp_diff_abs = Math.abs(att - ATTs.get(b));
			if (temp_diff_abs < min_diff_abs)
			{
				min_diff_abs = temp_diff_abs;
				temp_byte = b;
			}
		}
		return temp_byte;
	}
}