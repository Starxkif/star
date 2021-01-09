package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class logout extends JDialog {

	private JPanel contentPane;


	public logout(Account acc) {
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("确定注销吗？注销后会清除当前账户并退出游戏");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(20, 20, 350, 30);
		contentPane.add(label);
		
		JButton button1 = new JButton("确定");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLOperation.delAccount(acc);
				System.exit(0);
			}
		});
		button1.setBounds(36, 94, 97, 30);
		contentPane.add(button1);
		
		JButton button2 = new JButton("退出");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button2.setBounds(226, 94, 97, 30);
		contentPane.add(button2);
		setVisible(true);
	}
}
