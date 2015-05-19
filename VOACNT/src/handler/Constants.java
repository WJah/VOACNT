package handler;

import java.math.BigDecimal;

import com.sun.org.apache.xerces.internal.xs.datatypes.ByteList;

public abstract class Constants
{
	//温度数组
	public static final byte TEMP[] = 
		{(byte) 0x80,(byte)0x70,(byte)0x66,
		(byte)0x54,(byte)0x50,(byte)0x46,(byte)0x3E,
		(byte)0x36,(byte)0x2F,(byte)0x2B};
	public static final byte TEMP_SETTING_TAG = (byte)0x03;
	
	//VOA通道数组
	public static final byte CHANNEL[] =
		{(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x04,(byte)0x00};
	
	//默认衰减10dB
	public static final double DEFAULT_ATT = 10;
	//读取通道号标识
	public static final byte  CH_READ_TAG = (byte) 0xFE;
	
	//excel文件地址
	public static final String EXCEL = "./VOAATT.xls";
	
	//有效衰减范围
	public static final int EFF_ATT = 242;
	
	//DAC方式功能标示
	public static final byte DAC_FUNC_SETTING_TAG = 0x01;
	//LD方式功能标示
	public static final byte LD_FUNC_SETTING_TAG = 0x02;
	
	//固定衰减设置标识
	public static final byte STATIC_ATT_SETTING_TAG = 0x01;
	
	
	//可变衰减功能标示
	public static final byte VAR_ATT_SETTING_TAG = 0x02;
	
	//可变衰减变化规律标示
	public static final byte VAR_ATT_RULE_TAGS[] = {(byte)0x01 ,(byte)0x02,(byte)0x03}; //方波
	
	//可变衰减默认最值
	public static final double VAR_ATT_MIN = 0;
	public static final double VAR_ATT_MAX = 37;
	
	//可变衰减方波周期精度1.43us
	public static final double MIN_CYCLE_RESOLUTION = 1.45;
	//三角波、正弦波周期精度1.43×256us
	public static final double SIN_TRI_CYCLE_RESOLUTION = new BigDecimal(0.256 * MIN_CYCLE_RESOLUTION).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	
	//文件输入功能表示
	public static final byte FILE_ATT_SETTING_TAG = 0x03;
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
