package ui;

import handler.AWGMap;
import handler.Constants;
import handler.Handler;

import javax.swing.JFrame;

import java.lang.String;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;


import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.sun.org.apache.xerces.internal.impl.dv.xs.FullDVFactory;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Component;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class MainUI
{
	private ListenerPanel listenerPanel;
	private ConfigPanel configPanel;
	private FilePanel filePanel;
	private final static MainUI mainUI = new MainUI();
	static JFrame frmWdmpon;
	private static Handler handler = new Handler();
	public static AWGMap awgMap;
	private static String[] COMs = null;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the application.
	 */
	public static void main(String[] args)
	{
		final JFrame frmVoa = new JFrame();
		frmVoa.setVisible(true);
		frmVoa.setTitle("VOA\u8BBE\u7F6E\u8F6F\u4EF6");
		frmVoa.setBounds(100, 100, 210, 114);
		frmVoa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVoa.getContentPane().setLayout(new BoxLayout(frmVoa.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		frmVoa.getContentPane().add(panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
				JLabel lblNewLabel = new JLabel("COM:");
				lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				panel.add(lblNewLabel);
		COMs = handler.findCom();
		JComboBox<String> comboBox = new JComboBox<String>(COMs);	
		if (COMs.length != 0) {
			handler.setCom(COMs[0]);
		}
		panel.add(comboBox);

		JPanel panel_1 = new JPanel();
		frmVoa.getContentPane().add(panel_1);

		JButton loginButton = new JButton("\u8FDE\u63A5");
		loginButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (handler.openCom()) {
					frmVoa.setVisible(false);
					frmWdmpon.setVisible(true);
				}
			}
		});
		panel_1.add(loginButton);
	}

	public static MainUI getMainUI()
	{
		return mainUI;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private MainUI()
	{
		awgMap = new AWGMap();
		frmWdmpon = new JFrame();
		frmWdmpon.setResizable(false);
		frmWdmpon.setSize(new Dimension(542, 373));
		frmWdmpon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWdmpon.setTitle("VOA设置软件V0.1");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{76, 104, 33};
		gridBagLayout.columnWidths = new int[]{444, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		frmWdmpon.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		frmWdmpon.getContentPane().add(panel_3, gbc_panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\u6E29\u5EA6\u8BBE\u7F6E", TitledBorder.CENTER, TitledBorder.TOP, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_13);
		
		JLabel lblNewLabel_2 = new JLabel("\u6E29\u5EA6:");
		panel_13.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		panel_13.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"25", "30", "35", "40", "45", "50", "55", "60", "65", "70"}));
		
		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\u56FA\u5B9A\u8870\u51CF\u8BBE\u7F6E", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel_3 = new JLabel("\u901A\u9053\u9009\u62E9:");
		panel.add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"CH1", "CH2", "CH3", "CH4", "SINGLE"}));
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("\u8870\u51CF\u91CF:");
		panel.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("dB");
		panel.add(lblNewLabel_1);
		
		JPanel panel_14 = new JPanel();
		panel_3.add(panel_14);
		panel_14.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, null), "\u6682\u505C", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JButton btnNewButton = new JButton("\u6682\u505C");
		panel_14.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\u53EF\u53D8\u8870\u51CF\u8BBE\u7F6E", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		frmWdmpon.getContentPane().add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null), "DAC\u8DEF", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		JLabel label_7 = new JLabel("\u901A\u9053\u9009\u62E9:");
		panel_6.add(label_7);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"CH1", "CH2", "CH3", "CH4", "SINGLE"}));
		panel_6.add(comboBox_3);
		
		JLabel label_3 = new JLabel("\u53D8\u5316\u89C4\u5F8B:");
		panel_6.add(label_3);
		
		JComboBox comboBox_2 = new JComboBox();
		panel_6.add(comboBox_2);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"\u65B9\u6CE2", "\u6B63\u5F26\u6CE2", "\u4E09\u89D2\u6CE2"}));
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		
		JLabel label_4 = new JLabel("\u5468\u671F\u8BBE\u7F6E:");
		panel_7.add(label_4);
		
		textField_2 = new JTextField();
		panel_7.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblUs = new JLabel("us");
		panel_7.add(lblUs);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		
		JLabel label_5 = new JLabel("\u8870\u51CF\u6700\u5C0F\u503C:");
		panel_8.add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setColumns(5);
		panel_8.add(textField_3);
		
		JLabel lblDb = new JLabel(" - dB");
		panel_8.add(lblDb);
		
		JLabel label_6 = new JLabel("\u8870\u51CF\u6700\u5927\u503C");
		panel_8.add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setColumns(5);
		panel_8.add(textField_4);
		
		JLabel label_12 = new JLabel("dB");
		panel_8.add(label_12);
		
		JButton button = new JButton("\u786E\u5B9A");
		panel_4.add(button);
		
		JPanel panel_5 = new JPanel();
		panel_5.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LD\u8DEF\u65B9\u6CE2\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JPanel panel_9 = new JPanel();
		panel_5.add(panel_9);
		
		JLabel label_8 = new JLabel("\u901A\u9053\u9009\u62E9:");
		panel_9.add(label_8);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"CH1", "CH2", "CH3", "CH4", "SINGLE"}));
		panel_9.add(comboBox_4);
		
		JPanel panel_10 = new JPanel();
		panel_5.add(panel_10);
		
		JLabel label_9 = new JLabel("\u5468\u671F\u8BBE\u7F6E:");
		panel_10.add(label_9);
		
		textField_5 = new JTextField();
		panel_10.add(textField_5);
		textField_5.setColumns(5);
		
		JLabel lblUs_1 = new JLabel("us");
		panel_10.add(lblUs_1);
		
		JPanel panel_11 = new JPanel();
		panel_5.add(panel_11);
		
		JLabel label_10 = new JLabel("\u5360\u7A7A\u6BD4:");
		panel_11.add(label_10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(5);
		panel_11.add(textField_6);
		
		JLabel label_11 = new JLabel("%");
		panel_11.add(label_11);
		
		JButton button_1 = new JButton("\u786E\u5B9A");
		panel_5.add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\u6587\u4EF6\u63A5\u53E3", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		frmWdmpon.getContentPane().add(panel_2, gbc_panel_2);
		
		JPanel panel_12 = new JPanel();
		panel_2.add(panel_12);
		
		JLabel label_13 = new JLabel("\u9009\u62E9\u6587\u4EF6:");
		panel_12.add(label_13);
		
		textField_7 = new JTextField();
		panel_12.add(textField_7);
		textField_7.setColumns(30);
		
		JButton button_2 = new JButton("...");
		panel_12.add(button_2);
		
		JButton button_3 = new JButton("\u786E\u5B9A");
		panel_2.add(button_3);
		// 创建面板
		listenerPanel = new ListenerPanel();
		configPanel = new ConfigPanel();
		filePanel = new FilePanel();

		listenerPanel.setBackground(Color.WHITE);
		configPanel.setBackground(Color.WHITE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public ConfigPanel getConfigPanel()
	{
		return configPanel;
	}

	public ListenerPanel getListenerPanel()
	{
		return listenerPanel;
	}
	
	public FilePanel getFilePanel()
	{
		return filePanel;
	}

	public class ListenerPanel extends JPanel
	{
		/**
		 * 
		 */
		StaticPanel xfpPanel1;
		private static final long serialVersionUID = 1L;

		public ListenerPanel()
		{
			setLayout(new FlowLayout());

			JPanel panel = new JPanel();
			add(panel, BorderLayout.NORTH);

			final JLabel tempThroLabel = new JLabel("工作温度:");
			panel.add(tempThroLabel);

			final JComboBox<Integer> tempsComboBox = new JComboBox<Integer>();
			tempsComboBox.setModel(new DefaultComboBoxModel<>(new Integer[]
			{30,40,50,60}));
			tempsComboBox.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO Auto-generated method stub
					int index = tempsComboBox.getSelectedIndex();
					int tempThro = tempsComboBox.getItemAt(index);
					Constants.TEMP_ALARM_THRO = tempThro;
				}
			});
			panel.add(tempsComboBox);

			JPanel panel_1 = new JPanel();
			add(panel_1, BorderLayout.CENTER);
			xfpPanel1 = new StaticPanel(1);
						
			panel_1.add(xfpPanel1);
			
			xfpPanel1.setLayout(new GridLayout(1, 2, 0, 0));
			

		}

	}

	public class ConfigPanel extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ChangablePanel switchPanel1;
	
		private LDPanel waveConPanel1;

		public ConfigPanel()
		{
			setLayout(new BorderLayout(0, 0));

			JPanel panel_1 = new JPanel();
			add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new BorderLayout(0, 0));

			JPanel panel = new JPanel();
			panel_1.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

			JLabel lblNewLabel = new JLabel("");
			panel.add(lblNewLabel);

			JPanel panel_2 = new JPanel();
			panel_1.add(panel_2, BorderLayout.CENTER);
			panel_2.setLayout(new GridLayout(0, 2, 0, 0));

			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null,
					"DAC路", TitledBorder.LEFT,
					TitledBorder.TOP, null, null));
			panel_2.add(panel_3);
			panel_3.setLayout(new BorderLayout(0, 0));

			String[] switchComboxModel = new String[]
			{ "方波", "三角波","正弦波" };

			switchPanel1 = new ChangablePanel(handler, 0, switchComboxModel);
			panel_3.add(switchPanel1,BorderLayout.CENTER);

			JPanel panel_5 = new JPanel();
			panel_5.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"),
					"LD路", TitledBorder.LEFT,
					TitledBorder.TOP, null, null));
			panel_2.add(panel_5);
			panel_5.setLayout(new GridLayout(1, 1, 0, 0));

			// 设置XFP下拉框选项
			String[] xfpComboxModel = awgMap.getChannels().keySet()
					.toArray(new String[0]);
			waveConPanel1 = new LDPanel(handler, 0, xfpComboxModel);
			panel_5.add(waveConPanel1);

		}
	}
	
	public class FilePanel extends JPanel
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public FilePanel() {
			// TODO Auto-generated constructor stub
			setLayout(new BorderLayout(0, 0));

			JPanel panel_1 = new JPanel();
			add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new BorderLayout(0, 0));

			JPanel panel = new JPanel();
			panel_1.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

			JLabel lblNewLabel = new JLabel("");
			panel.add(lblNewLabel);

			JPanel panel_2 = new JPanel();
			panel_1.add(panel_2, BorderLayout.CENTER);
			panel_2.setLayout(new GridLayout(0, 1 , 0, 0));

			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null,
					"文件选择", TitledBorder.LEFT,
					TitledBorder.TOP, null, null));
			panel_3.setLayout(new FlowLayout());
			
			JLabel label = new JLabel("选择文件:");
			JTextField textField = new JTextField(20);
			JButton button = new JButton("...");
			
			panel_3.add(label);
			panel_3.add(textField);
			panel_3.add(button);
			
			panel_2.add(panel_3);

		}
	}

}