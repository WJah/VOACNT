package ui;

import handler.Handler;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;


public class LDPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	int comboTag = 0; // 开关/配置面板标志
	private int swicthIndex = 0; // XFP 编号
	Handler handler;
	List<String> chList;
	String[] ComboxModel;
	String xfpString;
	private JTextField txtUs;
	private JTextField textField;

	public LDPanel(Handler handler, int switchIndex, String[] ComboxModel)
	{
		this.ComboxModel = ComboxModel;
		this.handler = handler;
		this.swicthIndex = switchIndex;
		JPanel panel = new JPanel();
		add(panel);
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
				JPanel panel_2 = new JPanel();
				panel.add(panel_2);
				
				JLabel label = new JLabel("\u5468\u671F\u8BBE\u7F6E\uFF1A");
				panel_2.add(label);
				
				textField = new JTextField();
				textField.setColumns(10);
				panel_2.add(textField);
				
				JLabel label_1 = new JLabel("us");
				panel_2.add(label_1);
				
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				
						JLabel lblNewLabel = new JLabel("\u5360\u7A7A\u6BD4\uFF1A");
						panel_1.add(lblNewLabel);
						
						txtUs = new JTextField();
						panel_1.add(txtUs);
						txtUs.setColumns(10);
						
						JLabel lblUs = new JLabel("%");
						panel_1.add(lblUs);
						
						JButton btnNewButton = new JButton("\u786E\u5B9A");
						panel.add(btnNewButton);
	}
}