package handler;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;



import rs232.RS232;
import ui.MainUI;

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

	/**
	 * ����VOA�����¶�
	 * @param temp �����¶�
	 */
	public void setWorkTemp(byte temp)
	{
		rs.write(Constants.TEMP_SETTING_TAG);
		rs.write(temp);
	}
	
	/**
	 * ����VOA�̶�˥��
	 * @param ch ͨ��
	 * @param att �̶�˥����
	 */
	public void setStaticATT(byte ch, byte att)
	{
		rs.write(Constants.STATIC_ATT_SETTING_TAG_1);
		rs.write(Constants.STATIC_ATT_SETTING_TAG_2);
		rs.write(ch);
		rs.write(att);
	}
	// ����ͨ������
	public void setChannel(int XFPId, int channelId, String xfpString)
	{
		if (!isConnected)
		{sss
			return;
		}
		xfpId = XFPId;
		this.xfpString = xfpString;
		rs.write((byte)(0x21 + 0x10 * XFPId + channelId));// ���ù�ģ��ͨ����
		ChannelGetter channelGetter = new ChannelGetter(XFPId);//�����µ��̣߳���30s�ٶ�ȡ����ͨ��
		channelGetter.start();sssss
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
			ATTMap awgMap = new ATTMap();
			System.out.println(xfpString);
			
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
