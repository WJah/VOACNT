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
	 * 发送无效位
	 * @param l 无效位长度
	 */
	private void sendInvalidByte(int l)
	{
		for (int i = 0; i < l; i++) {
			rs.write((byte)0x00);			
		}
	}
	
	
	// 设置通道波长
	public void setChannel(int XFPId, int channelId, String xfpString)
	{
		if (!isConnected)
		{
			return;
		}
		xfpId = XFPId;
		this.xfpString = xfpString;
		rs.write((byte)(0x21 + 0x10 * XFPId + channelId));// 设置光模块通道号
		ChannelGetter channelGetter = new ChannelGetter(XFPId);//启动新的线程，隔30s再读取波长通道
		channelGetter.start();
		System.err.println((byte) (0x21 + 0x10 * XFPId + channelId));
//		setChannelNum(channelId);
	}

	class ChannelGetter extends Thread
	{
		int XFPId;

		public ChannelGetter(int XFPId)
		{
			this.XFPId = XFPId;
		}

		@Override
		public void run()
		{
			try
			{
				Thread.sleep(60 * 1000);
				rs.write((byte) (0x29 + 0x10 * XFPId)); // 获得光模块通道号
				System.out.println((byte) (0x29 + 0x10 * XFPId));
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
		}
	}

	public void setChannelNum(int channelId) 
	{
		try {
			ATTMap awgMap = new ATTMap();
			System.out.println(xfpString);
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			this.closeCom();
			this.openCom();
		}		
	}

	// 开关光模块
	public void xpfSwitch(int xfpId, boolean open)
	{
		if (!isConnected)
		{
			return;
		}
		if (open)
		{
			rs.write((byte) (0x00 + xfpId * 2)); // 开启4213
			rs.write((byte) (0x00 + (xfpId * 2 + 1)));// 开启1412
		} else
		{
			rs.write((byte) (0x10 + xfpId * 2));// 关闭4213
			rs.write((byte) (0x10 + (xfpId * 2 + 1)));// 关闭1412
		}
	}

	public void setCom(String com)
	{
		rs.setCom(com);
	}

		// 设置监听温度
	public void getTemperature(Map<Byte, Map<Integer, Byte>> maps)
	{
		Set<Byte> keySet = maps.keySet();
		Byte[] bytes = keySet.toArray(new Byte[0]);
		for (Byte key : bytes)
		{
			Map<Integer, Byte> map = maps.get(key);
			byte INT = map.get(1); // 整数部分
			if(INT == 0x00) return;//整数为零
			byte DEC = map.get(2); // 小数部分
			double temp = INT + (byteToInt(DEC) * 0.0039);
			if(INT <= 15) return; // 温度异常
			DecimalFormat df = new DecimalFormat("#.00"); // 保留两位小数
			int xfpId = (0x0F & key) - 1;
			
		}
	}

	private int byteToInt(byte b)
	{
		if (Byte.valueOf(b) < 0)
		{
			return Byte.valueOf(b) + 256;
		}
		return Byte.valueOf(b);
	}

	// 设置ONU注册标识
	//tag : 0=已注册，1=可注册，2=收无光
	public void setONUState(int xfpid,int tag)
	{
//		System.out.println("xfpid:" + xfpid);
		
		if (tag == 0) {
			
			
		}
		else if(tag == 1)
		{
			
		}
		else if(tag == 2)
		{
			
		}
		/**
		 * 
		 * 暂停	0x00	0x00	0x00	0x00	0x00	0x00	0x00
		固定衰减设置	设置标识	通道选择1
			衰减量设置	无效位
			0x01	0x01	0x00~0x04	0x00~0xFF	XX	XX	XX
		可变衰减量	设置标识	通道选择	变化规律2	周期	最小衰减量	最大衰减量
			0x01	0x02	0x00~0x04	0x01~0x03	0x00~0xFF	0x00~0xFF	0x00~0xFF
		文件输入	设置标识	通道选择	周期	文件大小高位3	文件大小低位3	无效位
			0x01	0x03	0x00~0x04	0x00~0xFF	0x00~0x0E	0x00~0xFF	XX
		LD方式方波设置	设置标识	通道选择	周期	占空比	无效位
			0x02	0x00	0x00~0x04	0x00~0xFF	0x01~0x63	XX	XX
		温度设置	设置标识	温度4	无效位
			0x03	0x00	0xXX	XX	XX	XX	XX
			
			4、	温度设置：0x80:25度、0x70:30度、0x66:35度、0x54:40度、0x50:45度、0x46:50度、0x3E:55度、
		0x36:60度、0x2F:65度、0x2B:70度。

		**/

	}
}
