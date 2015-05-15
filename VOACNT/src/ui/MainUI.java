package ui;

import handler.ATTMap;
import handler.Constants;
import handler.Handler;

import javax.swing.JFrame;

import java.lang.String;
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

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class MainUI 
{
	private final static MainUI mainUI = new MainUI();
	static JFrame frmVOA;
	private static Handler handler = new Handler();
	private static String[] COMs = null;
	private JTextField staticAttTextField;
	private JTextField varATTCYCLETextField;
	private JTextField varATTMinTextField;
	private JTextField varATTMaxTextField;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	
	//固定衰减设置
	private double staticATT = Constants.DEFAULT_ATT; 
	private byte staticCh = (byte)0x01;
	
	//可变衰减设置
	private double varATTMin = Constants.VAR_ATT_MIN;
	private double varATTMax = Constants.VAR_ATT_MAX;
	private byte varCh = (byte)0x01;
	private byte varRule = (byte)0x01;
	private int varCycle = 0;
	
	
	ATTMap attMap = new ATTMap();
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
					frmVOA.setVisible(true);
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
		frmVOA = new JFrame();
		frmVOA.setResizable(false);
		frmVOA.setSize(new Dimension(542, 400));
		frmVOA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVOA.setTitle("VOA设置软件V0.1");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{76, 104, 33};
		gridBagLayout.columnWidths = new int[]{444, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
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
		panel_13.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\u6E29\u5EA6\u8BBE\u7F6E", TitledBorder.CENTER, TitledBorder.TOP, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_13);
		
		JLabel lblNewLabel_2 = new JLabel("\u6E29\u5EA6:");
		panel_13.add(lblNewLabel_2);
		
		final JComboBox<String> tempComboBox = new JComboBox<String>();
		panel_13.add(tempComboBox);
		tempComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"25", "30", "35", "40", "45", "50", "55", "60", "65", "70"}));
		
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
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\u56FA\u5B9A\u8870\u51CF\u8BBE\u7F6E", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel_3 = new JLabel("\u901A\u9053\u9009\u62E9:");
		panel.add(lblNewLabel_3);
		
		final JComboBox<String> chComboBox1 = new JComboBox<String>();
		chComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] {"CH1", "CH2", "CH3", "CH4", "SINGLE"}));
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
		panel.add(staticAttTextField);
		staticAttTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("dB");
		panel.add(lblNewLabel_1);
		
		JButton staticAttButton = new JButton("\u786E\u5B9A");
		staticAttButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (staticAttTextField.getText().trim().equals("")) {
					return;
				}
				double inputAtt = Double.valueOf(staticAttTextField.getText());
				if (inputAtt >= 0 && inputAtt <= 37)
				{
					handler.setStaticATT(staticCh, staticATT);
				}
				else
				{
					JOptionPane.showMessageDialog(frmVOA, "衰减量超过范围，请输入0~37以内的数字！", "输入错误", JOptionPane.ERROR_MESSAGE);;
				}
			}
		});
		panel.add(staticAttButton);
		
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
		frmVOA.getContentPane().add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null), "DAC\u8DEF", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		JLabel label_7 = new JLabel("\u901A\u9053\u9009\u62E9:");
		panel_6.add(label_7);
		
		final JComboBox<String> varAttCHcomboBox = new JComboBox<String>();
		varAttCHcomboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"CH1", "CH2", "CH3", "CH4", "SINGLE"}));
		panel_6.add(varAttCHcomboBox);
		
		varAttCHcomboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = varAttCHcomboBox.getSelectedIndex();
				varCh = Constants.CHANNEL[index];
			}
		});
		
		JLabel label_3 = new JLabel("\u53D8\u5316\u89C4\u5F8B:");
		panel_6.add(label_3);
		
		final JComboBox<String> varRuleComboBox = new JComboBox<String>();
		panel_6.add(varRuleComboBox);
		varRuleComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"\u65B9\u6CE2", "\u6B63\u5F26\u6CE2", "\u4E09\u89D2\u6CE2"}));
		
		varRuleComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = varRuleComboBox.getSelectedIndex();
				varRule = Constants.VAR_ATT_RULE_TAGS[index];
			}
		});
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		
		JLabel label_4 = new JLabel("\u5468\u671F\u8BBE\u7F6E:");
		panel_7.add(label_4);
		
		varATTCYCLETextField = new JTextField();
		panel_7.add(varATTCYCLETextField);
		varATTCYCLETextField.setColumns(10);
		
		JLabel lblUs = new JLabel("×");
		panel_7.add(lblUs);
		
		JLabel label_1 = new JLabel("1.43");
		panel_7.add(label_1);
		
		JLabel label_2 = new JLabel("=");
		panel_7.add(label_2);
		
		JLabel VAR_CYCLE = new JLabel("");
		panel_7.add(VAR_CYCLE);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		
		JLabel label_5 = new JLabel("\u8870\u51CF\u6700\u5C0F\u503C:");
		panel_8.add(label_5);
		
		varATTMinTextField = new JTextField();
		varATTMinTextField.setColumns(5);
		panel_8.add(varATTMinTextField);
		
		JLabel lblDb = new JLabel(" - dB");
		panel_8.add(lblDb);
		
		JLabel label_6 = new JLabel("\u8870\u51CF\u6700\u5927\u503C");
		panel_8.add(label_6);
		
		varATTMaxTextField = new JTextField();
		varATTMaxTextField.setColumns(5);
		panel_8.add(varATTMaxTextField);
		
		JLabel label_12 = new JLabel("dB");
		panel_8.add(label_12);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String varCycle = varATTCYCLETextField.getText().trim();
				String varMinAttString = varATTMinTextField.getText().trim();
				String varMaxAttString = varATTMaxTextField.getText().trim();
				if (varCycle.equals("") || varMinAttString.equals("") || varMaxAttString.equals("")) {
					JOptionPane.showMessageDialog(frmVOA, "输入为空！", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
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
		frmVOA.getContentPane().add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_12 = new JPanel();
		panel_2.add(panel_12);
		
		JLabel label_13 = new JLabel("\u9009\u62E9\u6587\u4EF6:");
		panel_12.add(label_13);
		
		textField_7 = new JTextField();
		panel_12.add(textField_7);
		textField_7.setColumns(20);
		
		JButton button_2 = new JButton("...");
		panel_12.add(button_2);
		
		JLabel label = new JLabel("\u5468\u671F\uFF1A");
		panel_12.add(label);
		
		textField_8 = new JTextField();
		panel_12.add(textField_8);
		textField_8.setColumns(4);
		
		JLabel lblUs_2 = new JLabel("us");
		panel_12.add(lblUs_2);
		
		JPanel panel_15 = new JPanel();
		panel_2.add(panel_15);
		
		JButton button_3 = new JButton("\u786E\u5B9A");
		panel_15.add(button_3);
		// 创建面板
	}
}