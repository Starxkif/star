package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class rankList extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public rankList() {
		setTitle("排行");
		setBounds(100, 100, 250, 470);
		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(0x66ccff));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Object[][] accounts=SQLOperation.getAccount();		//从数据库得到所有用户的名字最高分和排行
		String columnNames[]= {"用户名","最高得分","排行"};
		DefaultTableModel tableModel=new DefaultTableModel(accounts, columnNames);	//创建一个表格模型
		contentPanel.setLayout(null);
		JTable table=new JTable(tableModel);		//创建一个表格
		JScrollPane scrollPane=new JScrollPane(table);		//设置可滚动的面板
		scrollPane.setLocation(17, 10);
		scrollPane.setSize(200,400);
		contentPanel.add(scrollPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
