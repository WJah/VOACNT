package ui;

import handler.ATTMap;
import handler.Constants;
import handler.ExcelHandler;
import handler.Handler;
import handler.TxtHandler;

import javax.swing.JFrame;

import java.io.File;
import java.lang.String;
import java.math.BigDecimal;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.sun.org.apache.xerces.internal.impl.dv.xs.FullDVFactory;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Component;
import java.beans.PropertyChangeListener;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

public class MainUI
{
	private final static MainUI mainUI = new MainUI();
	static JFrame frmVOA;
	private static Handler handler = new Handler();
	private static String[] COMs = null;
	private JTextField staticAttTextField;
	private JTextField varATTMinTextField;
	private JTextField varATTMaxTextField;
	private JTextField dutyCycleTextField;
	private JTextField choosenFilePath;
	private JLabel var_cycle_Label;
	private JLabel att_resolution_Label;
	private ExcelHandler excelHandler;
	private TxtHandler txtHandler;
	// 固定衰减设置
	private double staticATT = Constants.DEFAULT_ATT;
	private byte staticCh = (byte) 0x01;

	// 可变衰减设置
	private double varATTMin = Constants.VAR_ATT_MIN;
	private double varATTMax = Constants.VAR_ATT_MAX;
	private byte varCh = (byte) 0x01;
	private byte varRule = (byte) 0x01;
	private int varCycle = 0;

	ATTMap attMap = new ATTMap();
	private JTextField varATTCYCLETextField;
	private JTextField ldCycleTextField;
	private JTextField fileCycleTextField;

	// LD方式设置
	private byte ldCh = (byte) 0x01;
	private int ldCycle = 0;
	private int ldDuty = 1;

	// 文件接口文件绝对地址
	String filePath = null;
	int fileLength = 0;
	private byte fileCH = (byte) 0x01;
	private String file[];

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
		frmVoa.getContentPane().setLayout(
				new BoxLayout(frmVoa.getContentPane(),
						BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		frmVoa.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("COM:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN,
				20));
		panel.add(lblNewLabel);
		COMs = handler.findCom();
		JComboBox<String> comboBox = new JComboBox<String>(COMs);
		if (COMs.length != 0)
		{
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
				// if (handler.openCom()) {
				frmVoa.setVisible(false);
				frmVOA.setVisible(true);
				// }
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
		frmVOA = new JFrame();
		frmVOA.setResizable(false);
		frmVOA.setSize(new Dimension(650, 400));
		frmVOA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVOA.setTitle("VOA设置软件V0.1");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]
		{ 76, 104, 33 };
		gridBagLayout.columnWidths = new int[]
		{ 444, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0 };
		frmVOA.getContentPane().setLayout(gridBagLayout);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		frmVOA.getContentPane().add(panel_3, gbc_panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 0), 1, true),
				"\u6E29\u5EA6\u8BBE\u7F6E", TitledBorder.CENTER,
				TitledBorder.TOP, null, null), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_13);

		JLabel lblNewLabel_2 = new JLabel("\u6E29\u5EA6:");
		panel_13.add(lblNewLabel_2);

		final JComboBox<String> tempComboBox = new JComboBox<String>();
		panel_13.add(tempComboBox);
		tempComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[]
				{ "25", "30", "35", "40", "45", "50", "55", "60",
						"65", "70" }));

		tempComboBox.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int index = tempComboBox.getSelectedIndex();
				handler.setWorkTemp(Constants.TEMP[index]);
			}
		});

		JPanel panel = new JPanel();
		panel_3.add(panel);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0,
				0, 0), 1, true),
				"\u56FA\u5B9A\u8870\u51CF\u8BBE\u7F6E",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));

		JLabel lblNewLabel_3 = new JLabel("\u901A\u9053\u9009\u62E9:");
		panel.add(lblNewLabel_3);

		final JComboBox<String> chComboBox1 = new JComboBox<String>();
		chComboBox1.setModel(new DefaultComboBoxModel<String>(
				new String[]
				{ "CH1", "CH2", "CH3", "CH4", "SINGLE" }));
		panel.add(chComboBox1);

		chComboBox1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int index = chComboBox1.getSelectedIndex();
				staticCh = Constants.CHANNEL[index];
			}
		});

		JLabel lblNewLabel_4 = new JLabel("\u8870\u51CF\u91CF:");
		panel.add(lblNewLabel_4);

		staticAttTextField = new JTextField();
		staticAttTextField.setName("att");
		panel.add(staticAttTextField);
		staticAttTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("dB");
		panel.add(lblNewLabel_1);

		JButton staticAttButton = new JButton("\u786E\u5B9A");
		staticAttButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (staticAttTextField.getText().trim().equals(""))
				{
					return;
				}
				double inputAtt = Double.valueOf(staticAttTextField
						.getText());
				if (inputAtt >= 0 && inputAtt <= 37)
				{
					handler.setStaticATT(staticCh, staticATT);
				} else
				{
					JOptionPane.showMessageDialog(frmVOA,
							"衰减量超过范围，请输入0~37以内的数字！", "输入错误",
							JOptionPane.ERROR_MESSAGE);
					;
				}
			}
		});
		panel.add(staticAttButton);

		JPanel panel_14 = new JPanel();
		panel_3.add(panel_14);
		panel_14.setBorder(new TitledBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 0), 1, true), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null),
				"\u6682\u505C", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));

		JButton btnNewButton = new JButton("\u6682\u505C");
		panel_14.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(
				0, 0, 0), 1, true),
				"\u53EF\u53D8\u8870\u51CF\u8BBE\u7F6E",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		frmVOA.getContentPane().add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null),
				"DAC\u8DEF", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);

		JLabel label_7 = new JLabel("\u901A\u9053\u9009\u62E9:");
		panel_6.add(label_7);

		final JComboBox<String> varAttCHcomboBox = new JComboBox<String>();
		varAttCHcomboBox.setModel(new DefaultComboBoxModel<String>(
				new String[]
				{ "CH1", "CH2", "CH3", "CH4", "SINGLE" }));
		panel_6.add(varAttCHcomboBox);

		varAttCHcomboBox.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int index = varAttCHcomboBox.getSelectedIndex();
				varCh = Constants.CHANNEL[index];
			}
		});

		JLabel label_3 = new JLabel("\u53D8\u5316\u89C4\u5F8B:");
		panel_6.add(label_3);

		final JComboBox<String> varRuleComboBox = new JComboBox<String>();
		panel_6.add(varRuleComboBox);
		varRuleComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[]
				{ "\u65B9\u6CE2", "\u6B63\u5F26\u6CE2",
						"\u4E09\u89D2\u6CE2" }));

		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);

		JLabel label_4 = new JLabel("\u5468\u671F\u8BBE\u7F6E:");
		panel_7.add(label_4);

		varATTCYCLETextField = new JTextField();
		varATTCYCLETextField.setName("input");
		varATTCYCLETextField.setColumns(5);
		panel_7.add(varATTCYCLETextField);

		JLabel label_14 = new JLabel("\u00D7");
		panel_7.add(label_14);

		att_resolution_Label = new JLabel(
				Constants.MIN_CYCLE_RESOLUTION + "");
		att_resolution_Label.setName("res");
		panel_7.add(att_resolution_Label);

		JLabel label_16 = new JLabel("=");
		panel_7.add(label_16);

		var_cycle_Label = new JLabel("--");
		var_cycle_Label.setName("cycle");
		panel_7.add(var_cycle_Label);

		final JLabel unit_Label = new JLabel("us");
		panel_7.add(unit_Label);

		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);

		JLabel label_5 = new JLabel("\u8870\u51CF\u6700\u5C0F\u503C:");
		panel_8.add(label_5);

		varATTMinTextField = new JTextField();
		varATTMinTextField.setName("att");
		varATTMinTextField.setColumns(5);
		panel_8.add(varATTMinTextField);

		JLabel lblDb = new JLabel(" - dB");
		panel_8.add(lblDb);

		JLabel label_6 = new JLabel("\u8870\u51CF\u6700\u5927\u503C");
		panel_8.add(label_6);

		varATTMaxTextField = new JTextField();
		varATTMaxTextField.setName("att");
		varATTMaxTextField.setColumns(5);
		panel_8.add(varATTMaxTextField);

		JLabel label_12 = new JLabel("dB");
		panel_8.add(label_12);

		staticAttTextField.addKeyListener(new myKeyListener(
				staticAttTextField));
		staticAttTextField.setInputMap(JTextField.WHEN_FOCUSED,
				new InputMap());

		varATTCYCLETextField.addKeyListener(new myKeyListener(
				varATTCYCLETextField));
		varATTCYCLETextField.setInputMap(JTextField.WHEN_FOCUSED,
				new InputMap());

		varATTMinTextField.addKeyListener(new myKeyListener(
				varATTMinTextField));
		varATTMinTextField.setInputMap(JTextField.WHEN_FOCUSED,
				new InputMap());

		varATTMaxTextField.addKeyListener(new myKeyListener(
				varATTMaxTextField));
		varATTMaxTextField.setInputMap(JTextField.WHEN_FOCUSED,
				new InputMap());

		varRuleComboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int index = varRuleComboBox.getSelectedIndex();
				varRule = Constants.VAR_ATT_RULE_TAGS[index];
				if (index != 0)
				{
					att_resolution_Label
							.setText(Constants.SIN_TRI_CYCLE_RESOLUTION
									+ "");
					unit_Label.setText("ms");
				} else
				{
					att_resolution_Label
							.setText(Constants.MIN_CYCLE_RESOLUTION
									+ "");
					unit_Label.setText("us");
				}

				if (varATTCYCLETextField.getText().length() > 0)
				{
					var_cycle_Label.setText(new BigDecimal(Integer
							.valueOf(varATTCYCLETextField.getText())
							* Double.valueOf(att_resolution_Label
									.getText())).setScale(2,
							BigDecimal.ROUND_HALF_UP)
							+ "");
				}

			}
		});

		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String varCycle = varATTCYCLETextField.getText()
						.trim();
				String varMinAttString = varATTMinTextField.getText()
						.trim();
				String varMaxAttString = varATTMaxTextField.getText()
						.trim();
				if (varCycle.equals("") || varMinAttString.equals("")
						|| varMaxAttString.equals(""))
				{
					JOptionPane.showMessageDialog(frmVOA, "输入为空！",
							"错误", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (Double.valueOf(varMaxAttString) > 37
						|| Double.valueOf(varMinAttString) < 0)
				{
					JOptionPane.showMessageDialog(frmVOA,
							"衰减量超过范围，请输入0~37以内的数字！", "输入错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (Double.valueOf(varMaxAttString) <= Double
						.valueOf(varMinAttString))
				{
					JOptionPane.showMessageDialog(frmVOA,
							"衰减量最大值不大于最小值", "输入错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				handler.setVarATT(varCh, varRule, Integer
						.valueOf(varATTCYCLETextField.getText()
								.trim()), varATTMin, varATTMax);
			}
		});
		panel_4.add(button);

		JPanel panel_5 = new JPanel();
		panel_5.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_5.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"LD\u8DEF\u65B9\u6CE2\u8BBE\u7F6E",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		JPanel panel_9 = new JPanel();
		panel_5.add(panel_9);

		JLabel label_8 = new JLabel("\u901A\u9053\u9009\u62E9:");
		panel_9.add(label_8);

		final JComboBox<String> ldComboBox = new JComboBox<String>();
		ldComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[]
				{ "CH1", "CH2", "CH3", "CH4", "SINGLE" }));
		panel_9.add(ldComboBox);

		ldComboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int index = ldComboBox.getSelectedIndex();
				ldCh = Constants.CHANNEL[index];
			}
		});

		JPanel panel_10 = new JPanel();
		panel_5.add(panel_10);

		JLabel label_9 = new JLabel("\u5468\u671F\u8BBE\u7F6E:");
		panel_10.add(label_9);

		ldCycleTextField = new JTextField();
		ldCycleTextField.setName("input");
		ldCycleTextField.setColumns(5);
		panel_10.add(ldCycleTextField);

		JLabel label_18 = new JLabel("\u00D7");
		panel_10.add(label_18);

		JLabel ld_resolution_JLabel = new JLabel("1.43");
		ld_resolution_JLabel.setName("res");
		panel_10.add(ld_resolution_JLabel);

		JLabel label_20 = new JLabel("=");
		panel_10.add(label_20);

		JLabel ld_cycle_label = new JLabel("--");
		ld_cycle_label.setName("cycle");
		panel_10.add(ld_cycle_label);

		JLabel label_22 = new JLabel("us");
		panel_10.add(label_22);

		JPanel panel_11 = new JPanel();
		panel_5.add(panel_11);

		JLabel label_10 = new JLabel("\u5360\u7A7A\u6BD4:");
		panel_11.add(label_10);

		dutyCycleTextField = new JTextField();
		dutyCycleTextField.setName("duty");
		dutyCycleTextField.setColumns(5);
		panel_11.add(dutyCycleTextField);

		dutyCycleTextField.addKeyListener(new myKeyListener(
				dutyCycleTextField));
		dutyCycleTextField.setInputMap(JTextField.WHEN_FOCUSED,
				new InputMap());

		JLabel label_11 = new JLabel("%");
		panel_11.add(label_11);

		JButton ldBbutton = new JButton("\u786E\u5B9A");
		ldBbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (ldCycleTextField.getText().trim().equals("")
						|| dutyCycleTextField.getText().trim()
								.equals(""))
				{
					JOptionPane.showMessageDialog(frmVOA, "输入为空!",
							"输入错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				handler.setLDATT(ldCh, ldCycle, ldDuty);
			}
		});
		panel_5.add(ldBbutton);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(
				0, 0, 0), 1, true), "\u6587\u4EF6\u63A5\u53E3",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		frmVOA.getContentPane().add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JPanel panel_12 = new JPanel();
		panel_2.add(panel_12);

		JLabel label_15 = new JLabel("\u901A\u9053\u9009\u62E9:");
		panel_12.add(label_15);

		final JComboBox<String> fileCHcomboBox = new JComboBox<String>();
		fileCHcomboBox.setModel(new DefaultComboBoxModel<String>(
				new String[]
				{ "CH1", "CH2", "CH3", "CH4", "SINGLE" }));
		panel_12.add(fileCHcomboBox);
		fileCHcomboBox.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int index = fileCHcomboBox.getSelectedIndex();
				fileCH = Constants.CHANNEL[index];
			}
		});

		JLabel label_13 = new JLabel("\u9009\u62E9\u6587\u4EF6:");
		panel_12.add(label_13);

		choosenFilePath = new JTextField();
		panel_12.add(choosenFilePath);
		choosenFilePath.setColumns(20);

		JButton fileChooseButton = new JButton("...");

		panel_12.add(fileChooseButton);

		JPanel panel_15 = new JPanel();
		panel_2.add(panel_15);

		JButton fileButton = new JButton("\u786E\u5B9A");
		fileButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (filePath == null)
				{
					JOptionPane.showMessageDialog(frmVOA, "请选择文件!",
							"输入错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (fileCycleTextField.getText().trim().equals(""))
				{
					JOptionPane.showMessageDialog(frmVOA, "请输入周期!",
							"输入错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				handler.setFileATT(
						fileCH,
						Integer.valueOf(fileCycleTextField.getText()),
						file, fileLength);
			}
		});

		JLabel label = new JLabel("\u5468\u671F\uFF1A");
		panel_15.add(label);

		fileCycleTextField = new JTextField();
		panel_15.add(fileCycleTextField);
		fileCycleTextField.setName("input");
		fileCycleTextField.setColumns(5);

		fileCycleTextField.addKeyListener(new myKeyListener(
				fileCycleTextField));
		fileCycleTextField.setInputMap(JTextField.WHEN_FOCUSED,
				new InputMap());

		JLabel label_1 = new JLabel("\u00D7");
		panel_15.add(label_1);

		JLabel file_res_label = new JLabel("1.43");
		panel_15.add(file_res_label);
		file_res_label.setName("res");

		JLabel label_2 = new JLabel("\u00D7");
		panel_15.add(label_2);

		final JLabel file_length_Label = new JLabel("L");
		panel_15.add(file_length_Label);
		file_length_Label.setName("length");

		JLabel label_23 = new JLabel("=");
		panel_15.add(label_23);

		JLabel file_cycle_label = new JLabel("--");
		panel_15.add(file_cycle_label);
		file_cycle_label.setName("cycle");

		JLabel label_25 = new JLabel("us");
		panel_15.add(label_25);
		panel_15.add(fileButton);

		fileChooseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser("D:");
				fileChooser
						.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.showOpenDialog(frmVOA);
				if (fileChooser.getSelectedFile() != null)
				{
					filePath = fileChooser.getSelectedFile()
							.getAbsolutePath();
					String ext = getExtension(new File(filePath))
							.toLowerCase();
					if (ext.equals("xls"))
					{
						choosenFilePath.setText(filePath);
						excelHandler = new ExcelHandler(filePath);
						fileLength = excelHandler.getFileLength();
						fileLength = fileLength < 4000 ? fileLength
								: 4000;
						file_length_Label.setText(fileLength + "");
						file = excelHandler.getFile();
					} else if (ext.equals("txt"))
					{
						choosenFilePath.setText(filePath);
						txtHandler = new TxtHandler(filePath);
						fileLength = txtHandler.getFileLength();
						fileLength = fileLength < 4000 ? fileLength
								: 4000;
						file_length_Label.setText(fileLength + "");
						file = txtHandler.getFile();
					} else
					{
						JOptionPane.showMessageDialog(frmVOA,
								"只支持.xls和.txt文件", "文件输入错误",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		ldCycleTextField.addKeyListener(new myKeyListener(
				ldCycleTextField));
		ldCycleTextField.setInputMap(JTextField.WHEN_FOCUSED,
				new InputMap());
	}

	private String getExtension(File f)
	{
		String name = f.getName();
		int index = name.lastIndexOf('.');

		if (index == -1)
		{
			return "";
		} else
		{
			return name.substring(index + 1).toLowerCase();
		}
	}

	class myKeyListener implements KeyListener
	{
		private JTextField textField;

		public myKeyListener(JTextField textField)
		{
			// TODO Auto-generated constructor stub
			this.textField = textField;
		}

		@Override
		public void keyTyped(KeyEvent e)
		{
			// TODO Auto-generated method stub
			String oldText = textField.getText();
			String textFileName = textField.getName();
			JLabel resJLabel = getSpecJLabel(textField, "res");
			JLabel cycleJLabel = getSpecJLabel(textField, "cycle");
			JLabel fileLengthJLabel = getSpecJLabel(textField,
					"length");

			if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9'
					|| e.getKeyChar() == '.')
			{
				if (textFileName != null
						&& !textFileName.equals("att")
						&& e.getKeyChar() != '.')
				{
					textField.setText(oldText + e.getKeyChar());

					int inputCycleNum = Integer.valueOf(textField
							.getText());

					if (resJLabel != null)
					{
						double cycle;
						if (fileLengthJLabel != null)
						{
							double res = Double.valueOf(resJLabel
									.getText());
							cycle = inputCycleNum * fileLength * res;
						} else
						{
							double res = Double.valueOf(resJLabel
									.getText());
							cycle = inputCycleNum * res;
						}
						BigDecimal cycleBigDecimal = new BigDecimal(
								cycle).setScale(2,
								BigDecimal.ROUND_HALF_UP);
						cycleJLabel.setText(cycleBigDecimal + "");
					}

					if (inputCycleNum > 256)
					{
						JOptionPane.showMessageDialog(frmVOA,
								"周期最大值不能超过256!", "输入超出范围",
								JOptionPane.ERROR_MESSAGE);
						textField.setText("");
						return;
					}
					if (textFileName.equals("duty")
							&& inputCycleNum > 100)
					{
						JOptionPane.showMessageDialog(frmVOA,
								"占空比最大值不能超过100!", "输入超出范围",
								JOptionPane.ERROR_MESSAGE);
						textField.setText("");
						return;
					}
				}
				if (textFileName.equals("att"))
				{
					textField.setText(oldText + e.getKeyChar());
					try
					{
						double inputAtt = Double.valueOf(textField
								.getText());
						if (inputAtt > 37)
						{
							JOptionPane.showMessageDialog(frmVOA,
									"衰减量最大值不能超过37!", "输入超出范围",
									JOptionPane.ERROR_MESSAGE);
							textField.setText("");
							return;
						}
					} catch (NumberFormatException e2)
					{
						// TODO: handle exception
						textField.setText(oldText);
					}

				}

			} else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE
					&& oldText.length() > 0)
			{
				textField.setText(oldText.substring(0,
						oldText.length() - 1));
				if (textFileName != null
						&& !textFileName.equals("att")
						&& textField.getText().length() > 0)
				{
					int inputCycleNum = Integer.valueOf(textField
							.getText());
					if (resJLabel != null)
					{
						double cycle;
						if (fileLengthJLabel != null)
						{
							double res = Double.valueOf(resJLabel
									.getText());
							cycle = inputCycleNum * fileLength * res;
						} else
						{
							double res = Double.valueOf(resJLabel
									.getText());
							cycle = inputCycleNum * res;
						}
						BigDecimal cycleBigDecimal = new BigDecimal(
								cycle).setScale(2,
								BigDecimal.ROUND_HALF_UP);
						cycleJLabel.setText(cycleBigDecimal + "");
					}
					if (inputCycleNum > 256)
					{
						JOptionPane.showMessageDialog(frmVOA,
								"占空比最大值不能超过100!", "输入超出范围",
								JOptionPane.ERROR_MESSAGE);
						textField.setText("");
						return;
					}
					if (textFileName.equals("duty")
							&& inputCycleNum >= 100)
					{
						JOptionPane.showMessageDialog(frmVOA,
								"周期最大值不能超过256!", "输入超出范围",
								JOptionPane.ERROR_MESSAGE);
						textField.setText("");
						return;
					}
					if (textFileName.equals("att"))
					{
						textField.setText(oldText + e.getKeyChar());
						try
						{
							double inputAtt = Double
									.valueOf(textField.getText());
							if (inputAtt > 37)
							{
								JOptionPane.showMessageDialog(frmVOA,
										"衰减量最大值不能超过37!", "输入超出范围",
										JOptionPane.ERROR_MESSAGE);
								textField.setText("");
								return;
							}
						} catch (NumberFormatException exception)
						{
							textField.setText(oldText);
						}
					}
				} else if (cycleJLabel != null
						&& textField.getText().length() == 0)
				{
					cycleJLabel.setText("--");
				}
			}
		}

		/**
		 * 活动特点名字的JLabel对象
		 * 
		 * @param component
		 *            与需要获得的JLabel对象同父组件的组件
		 * @param componentName
		 *            需要获得的JLabel的name
		 * @return
		 */
		private JLabel getSpecJLabel(Component component,
				String componentName)
		{
			Component components[] = component.getParent()
					.getComponents();
			JLabel label = null;
			for (Component c : components)
			{
				if (c.getName() != null
						&& c.getName().equals(componentName))
				{
					label = (JLabel) c;
				}
			}
			return label;
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			// TODO Auto-generated method stub

		}

	}
}