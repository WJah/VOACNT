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
	 * ������ͨ��ģ��
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
	 * ����VOA�����¶�
	 * @param temp �����¶�
	 */
	public void setWorkTemp(byte temp)
	{
		rs.write(Constants.TEMP_SETTING_TAG);
		rs.write(temp);
		sendInvalidByte(5);
	}
	
	/**
	 * ����VOA�̶�˥��
	 * @param ch ͨ��
	 * @param att �̶�˥����
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
	 * ���ÿɱ�˥��
	 * @param ch ͨ��
	 * @param rule �仯����
	 * @param cycle ����
	 * @param minAtt ��С˥��
	 * @param maxAtt ���˥��
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
	 * �ļ��ӿ�
	 * @param ch ͨ��
	 * @param cycle ����
	 * @param file �ļ�
	 * @param fileLength �ļ�����
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
	 * ����LD������ʽ
	 * @param ch ͨ��ѡ��
	 * @param cycle ����
	 * @param duty ռ�ձ�
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
	 * ������Чλ
	 * @param l ��Чλ����
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
		 * ��ͣ	0x00	0x00	0x00	0x00	0x00	0x00	0x00
		�̶�˥������	���ñ�ʶ	ͨ��ѡ��1
			˥��������	��Чλ
			0x01	0x01	0x00~0x04	0x00~0xFF	XX	XX	XX
		�ɱ�˥����	���ñ�ʶ	ͨ��ѡ��	�仯����2	����	��С˥����	���˥����
			0x01	0x02	0x00~0x04	0x01~0x03	0x00~0xFF	0x00~0xFF	0x00~0xFF
		�ļ�����	���ñ�ʶ	ͨ��ѡ��	����	�ļ���С��λ	�ļ���С��λ	��Чλ
			0x01	0x03	0x00~0x04	0x00~0xFF	0x00~0x0E	0x00~0xFF	XX
		LD��ʽ��������	���ñ�ʶ	ͨ��ѡ��	����	ռ�ձ�    ��Чλ
			0x02	0x00	0x00~0x04	0x00~0xFF	0x01~0x63	XX	XX
		�¶�����	���ñ�ʶ	�¶�4	��Чλ
			0x03	0x00	0xXX	XX	XX	XX	XX
			4��	�¶����ã�0x80:25�ȡ�0x70:30�ȡ�0x66:35�ȡ�0x54:40�ȡ�0x50:45�ȡ�0x46:50�ȡ�0x3E:55�ȡ�
		0x36:60�ȡ�0x2F:65�ȡ�0x2B:70�ȡ�

		**/
}
