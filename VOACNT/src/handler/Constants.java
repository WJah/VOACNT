package handler;

public abstract class Constants
{
	//�¶�����
	public static final byte TEMP[] = 
		{(byte) 0x80,(byte)0x70,(byte)0x66,
		(byte)0x54,(byte)0x50,(byte)0x46,(byte)0x3E,
		(byte)0x36,(byte)0x2F,(byte)0x2B};
	public static final byte TEMP_SETTING_TAG = (byte)0x00;
	
	//VOAͨ������
	public static final byte CHANNEL[] =
		{(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x04,(byte)0x00};
	
	//Ĭ��˥��
	public static final byte DEFAULT_ATT = (byte)0x3D;
	//��ȡͨ���ű�ʶ
	public static final byte  CH_READ_TAG = (byte) 0xFE;
	
	//excel�ļ���ַ
	public static final String EXCEL = "./VOAATT.xls";
	
	//��Ч˥����Χ
	public static final int EFF_ATT = 242;
	
	//�̶�˥�����ñ�ʶ
	public static final byte STATIC_ATT_SETTING_TAG_1 = 0x01;
	public static final byte STATIC_ATT_SETTING_TAG_2 = 0x01;
	
	//��ȡONUע��״̬��ʶ
	public static final byte ONU1_REG_TAG = (byte)0x21;
	public static final byte ONU2_REG_TAG = (byte)0x22;
	public static final byte ONU3_REG_TAG = (byte)0x23;
	public static final byte ONU4_REG_TAG = (byte)0x24;
}
/**
 * 
 * ��ͣ	0x00	0x00	0x00	0x00	0x00	0x00	0x00
�̶�˥������	���ñ�ʶ	ͨ��ѡ��1
	˥��������	��Чλ
	0x01	0x01	0x00~0x04	0x00~0xFF	XX	XX	XX
�ɱ�˥����	���ñ�ʶ	ͨ��ѡ��	�仯����2	����	��С˥����	���˥����
	0x01	0x02	0x00~0x04	0x01~0x03	0x00~0xFF	0x00~0xFF	0x00~0xFF
�ļ�����	���ñ�ʶ	ͨ��ѡ��	����	�ļ���С��λ3	�ļ���С��λ3	��Чλ
	0x01	0x03	0x00~0x04	0x00~0xFF	0x00~0x0E	0x00~0xFF	XX
LD��ʽ��������	���ñ�ʶ	ͨ��ѡ��	����	ռ�ձ�	��Чλ
	0x02	0x00	0x00~0x04	0x00~0xFF	0x01~0x63	XX	XX
�¶�����	���ñ�ʶ	�¶�4	��Чλ
	0x03	0x00	0xXX	XX	XX	XX	XX
	
	4��	�¶����ã�0x80:25�ȡ�0x70:30�ȡ�0x66:35�ȡ�0x54:40�ȡ�0x50:45�ȡ�0x46:50�ȡ�0x3E:55�ȡ�
0x36:60�ȡ�0x2F:65�ȡ�0x2B:70�ȡ�

**/
