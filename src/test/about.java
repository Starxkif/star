package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class about extends JDialog {


	public about() {
		setTitle("关于2048");
		setBounds(100, 100, 450, 300);
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel label1 = new JLabel("2048");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("宋体", Font.PLAIN, 35));
		label1.setForeground(new Color(153, 153, 255));
		label1.setBounds(110, 30, 180, 50);
		panel.add(label1);

		JLabel label2 = new JLabel("<html>作品说明：java课程设计项目<br>指导老师：张庆华<br>版权所有：软件5班--曾爱民<br>制作时间：2021年1月</html>");
		label2.setFont(new Font("宋体", Font.PLAIN, 15));
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBounds(65, 100, 300, 110);
		panel.add(label2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
