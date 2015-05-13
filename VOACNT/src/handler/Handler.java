package handler;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;



import rs232.RS232;
import ui.MainUI;
import ui.StaticPanel;

public class Handler extends NullPointerException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ������ͨ��ģ��
	 */
	private boolean isConnected = false;
	public RS232 rs;
	int xfpId;
	String xfpString;

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

	// ����ͨ������
	public void setChannel(int XFPId, int channelId, String xfpString)
	{
		if (!isConnected)
		{
			return;
		}
		xfpId = XFPId;
		this.xfpString = xfpString;
		rs.write((byte)(0x21 + 0x10 * XFPId + channelId));// ���ù�ģ��ͨ����
		ChannelGetter channelGetter = new ChannelGetter(XFPId);//�����µ��̣߳���30s�ٶ�ȡ����ͨ��
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
				rs.write((byte) (0x29 + 0x10 * XFPId)); // ��ù�ģ��ͨ����
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
			AWGMap awgMap = new AWGMap();
			Map<String, List<String>> Channels = awgMap.getChannels();
			System.out.println(xfpString);
			List<String> waveStrings = Channels.get(xfpString);
			
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			this.closeCom();
			this.openCom();
		}
		
		
	}

	// ���ع�ģ��
	public void xpfSwitch(int xfpId, boolean open)
	{
		if (!isConnected)
		{
			return;
		}
		if (open)
		{
			rs.write((byte) (0x00 + xfpId * 2)); // ����4213
			rs.write((byte) (0x00 + (xfpId * 2 + 1)));// ����1412
		} else
		{
			rs.write((byte) (0x10 + xfpId * 2));// �ر�4213
			rs.write((byte) (0x10 + (xfpId * 2 + 1)));// �ر�1412
		}
	}

	public void setCom(String com)
	{
		rs.setCom(com);
	}

		// ���ü����¶�
	public void getTemperature(Map<Byte, Map<Integer, Byte>> maps)
	{
		Set<Byte> keySet = maps.keySet();
		Byte[] bytes = keySet.toArray(new Byte[0]);
		for (Byte key : bytes)
		{
			Map<Integer, Byte> map = maps.get(key);
			byte INT = map.get(1); // ��������
			if(INT == 0x00) return;//����Ϊ��
			byte DEC = map.get(2); // С������
			double temp = INT + (byteToInt(DEC) * 0.0039);
			if(INT <= 15) return; // �¶��쳣
			DecimalFormat df = new DecimalFormat("#.00"); // ������λС��
			int xfpId = (0x0F & key) - 1;
			
			if (temp > Constants.TEMP_ALARM_THRO) // ���¶ȴ��ڸ��¾�����ֵʱ�رչ�ģ��
			{
				xpfSwitch(xfpId, false);
				
			} else
			{
				
			}
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

	// ����ONUע���ʶ
	//tag : 0=��ע�ᣬ1=��ע�ᣬ2=���޹�
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
		
//		System.out.println("Green");

	}
}
