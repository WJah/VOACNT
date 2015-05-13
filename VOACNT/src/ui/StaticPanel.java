package ui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.border.BevelBorder;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class StaticPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TitledBorder xfpTitledBorder;
	private JTextField textField_1;

	public StaticPanel(int unitId)
	{
		String panelTag = "DAC·";
		JPanel panel = new JPanel();
		Font xfpFont = new Font("XFP", Font.BOLD, 15);
		xfpTitledBorder = new TitledBorder(
				UIManager.getBorder("TitledBorder.border"), panelTag,
				TitledBorder.CENTER, TitledBorder.TOP, xfpFont, Color.GRAY);
		panel.setBorder(xfpTitledBorder);
		add(panel);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_3);
		
		JLabel label = new JLabel("\u901A\u9053\u9009\u62E9:  ");
		panel_3.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"CH1", "CH2", "CH3", "CH4", "SINGLE"}));
		panel_3.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel label_1 = new JLabel("\u8870\u51CF\u91CF\u8BBE\u7F6E:");
		panel_2.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_2.add(textField_1);
		
		JLabel label_2 = new JLabel("dB");
		panel_2.add(label_2);
		
		JButton button_1 = new JButton("\u786E\u5B9A");
		panel_2.add(button_1);
	}

}
