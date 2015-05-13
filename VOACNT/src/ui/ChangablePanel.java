package ui;

import handler.Handler;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ChangablePanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private int swicthIndex = 0; // XFP 编号
	JComboBox<String> comboBox;
	Handler handler;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public ChangablePanel(Handler handler, int switchIndex, String[] ComboxModel)
	{
		this.handler = handler;
		this.swicthIndex = switchIndex;
		JPanel panel = new JPanel();
		add(panel);
		String tag = "OLT Unit";
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		JLabel label = new JLabel("\u53D8\u5316\u89C4\u5F8B\uFF1A");
		panel_1.add(label);
		comboBox = new JComboBox<String>();
		panel_1.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel<String>(ComboxModel));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel label_4 = new JLabel("\u5468\u671F\u8BBE\u7F6E\uFF1A");
		panel_3.add(label_4);
		
		textField_2 = new JTextField();
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblUs = new JLabel("us");
		panel_3.add(lblUs);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel label_1 = new JLabel("\u8870\u51CF\u6700\u5C0F\u503C");
		panel_2.add(label_1);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("dB");
		panel_2.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel label_2 = new JLabel("\u8870\u51CF\u6700\u5927\u503C");
		panel_4.add(label_2);
		
		textField_1 = new JTextField();
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("dB");
		panel_4.add(label_3);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		panel.add(btnNewButton);
		comboBox.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		int key = comboBox.getSelectedIndex();
		MainUI mainUI = MainUI.getMainUI();
		switch (key)
		{
		case 0: // 开启光模块
			
			handler.xpfSwitch(swicthIndex, true);
			break;

		case 1: // 关闭光模块
			
			handler.xpfSwitch(swicthIndex, false);
			break;
		}

	}
}