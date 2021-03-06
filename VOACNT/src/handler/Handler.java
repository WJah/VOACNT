package handler;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;





import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.sun.org.apache.regexp.internal.recompile;

import rs232.RS232;
import ui.MainUI;

public class Handler extends NullPointerException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 处理串口通信模块
	 */
	private boolean isConnected = false;
	public RS232 rs;
	int xfpId;
	String xfpString;
	private ATTMap attMap;

	public Handler()
	{
		rs = new RS232(this)
		{
			@Override
			public void back(String s1)
			{
				// TODO Auto-generated method stub
			}
		};
		attMap = new ATTMap();
	}

	public String[] findCom()
	{
		List<String> list = rs.findComs();
		String[] comsStrings = new String[list.size()];
		list.toArray(comsStrings);
		return comsStrings;
	}

	public boolean openCom()
	{
		return isConnected = rs.openCom();
	}

	public boolean closeCom()
	{
		rs.closeCom();
		isConnected = false;
		return !isConnected;
	}

	/**
	 * 设置VOA工作温度
	 * @param temp 工作温度
	 */
	public void setWorkTemp(byte temp)
	{
		rs.write(Constants.TEMP_SETTING_TAG);
		rs.write(temp);
		sendInvalidByte(5);
	}
	
	/**
	 * 设置VOA固定衰减
	 * @param ch 通道
	 * @param att 固定衰减量
	 */
	public void setStaticATT(byte ch, double att)
	{
		byte ATT = attMap.getBestATT(att);
		rs.write(Constants.DAC_FUNC_SETTING_TAG);
		rs.write(Constants.STATIC_ATT_SETTING_TAG);
		rs.write(ch);
		rs.write(ATT);
		sendInvalidByte(3);
	}
	
	/**
	 * 设置可变衰减
	 * @param ch 通道
	 * @param rule 变化规律
	 * @param cycle 周期
	 * @param minAtt 最小衰减
	 * @param maxAtt 最大衰减
	 */
	public void setVarATT(byte ch,byte rule,int cycle,double minAtt,double maxAtt)
	{
		System.out.println("cycle" + (byte)cycle);
		byte MAXATT = attMap.getBestATT(maxAtt);
		byte MINATT = attMap.getBestATT(minAtt);
		rs.write(Constants.DAC_FUNC_SETTING_TAG);
		rs.write(Constants.VAR_ATT_SETTING_TAG);
		rs.write(ch);
		rs.write(rule);
		rs.write((byte)cycle);
		rs.write(MINATT);
		rs.write(MAXATT);
		
	}
	
	/**
	 * 文件接口
	 * @param ch 通道
	 * @param cycle 周期
	 * @param file 文件
	 * @param fileLength 文件长度
	 */
	public void setFileATT(byte ch,int cycle,String[] file,int fileLength)
	{
		rs.write(Constants.DAC_FUNC_SETTING_TAG);
		rs.write(Constants.FILE_ATT_SETTING_TAG);
		rs.write(ch);
		rs.write((byte)cycle);
		rs.write((byte)(fileLength/256));
		rs.write((byte)(fileLength%256));
		rs.write((byte)0x00);
		for (int i = 0; i < fileLength; i++)
		{
			rs.write(Byte.valueOf(file[i]));
		}
	}
	
	/**
	 * 设置LD工作方式
	 * @param ch 通道选择
	 * @param cycle 周期
	 * @param duty 占空比
	 */
	public void setLDATT(byte ch,int cycle,int duty)
	{
		rs.write(Constants.LD_FUNC_SETTING_TAG);
		rs.write((byte)0x00);
		rs.write(ch);
		rs.write((byte)cycle);
		rs.write((byte)duty);
		System.out.println((byte)cycle + " " + (byte)duty);
		sendInvalidByte(2);
	}
	
	/**
	 * 发送无效位
	 * @param l 无效位长度
	 */
	private void sendInvalidByte(int l)
	{
		for (int i = 0; i < l; i++) {
			rs.write((byte)0x00);			
		}
	}

	public void setCom(String com)
	{
		rs.setCom(com);
	}


	private int byteToInt(byte b)
	{
		if (Byte.valueOf(b) < 0)
		{
			return Byte.valueOf(b) + 256;
		}
		return Byte.valueOf(b);
	}

	
		/**
		 * 
		 * 暂停	0x00	0x00	0x00	0x00	0x00	0x00	0x00
		固定衰减设置	设置标识	通道选择1
			衰减量设置	无效位
			0x01	0x01	0x00~0x04	0x00~0xFF	XX	XX	XX
		可变衰减量	设置标识	通道选择	变化规律2	周期	最小衰减量	最大衰减量
			0x01	0x02	0x00~0x04	0x01~0x03	0x00~0xFF	0x00~0xFF	0x00~0xFF
		文件输入	设置标识	通道选择	周期	文件大小高位	文件大小低位	无效位
			0x01	0x03	0x00~0x04	0x00~0xFF	0x00~0x0E	0x00~0xFF	XX
		LD方式方波设置	设置标识	通道选择	周期	占空比    无效位
			0x02	0x00	0x00~0x04	0x00~0xFF	0x01~0x63	XX	XX
		温度设置	设置标识	温度4	无效位
			0x03	0x00	0xXX	XX	XX	XX	XX
			4、	温度设置：0x80:25度、0x70:30度、0x66:35度、0x54:40度、0x50:45度、0x46:50度、0x3E:55度、
		0x36:60度、0x2F:65度、0x2B:70度。

		**/
}
