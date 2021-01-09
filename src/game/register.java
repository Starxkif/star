package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class register extends JDialog {

	public register() {
		
		Account acc=new Account();
		JPanel contentPanel = new JPanel();
		setTitle("注册账号");
		setBounds(400, 400, 337, 225);
		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel accountLabel = new JLabel("账号：");
		accountLabel.setBounds(20, 20, 60, 25);
		contentPanel.add(accountLabel);
		
		JLabel passwordLabel = new JLabel("密码：");
		passwordLabel.setBounds(20, 50, 60, 25);
		contentPanel.add(passwordLabel);
		
		JLabel rePasswordLabel = new JLabel("重复密码：");
		rePasswordLabel.setBounds(20, 80, 60, 20);
		contentPanel.add(rePasswordLabel);
		
		JTextField userText = new JTextField();
		userText.setBounds(100, 20, 150, 22);
		contentPanel.add(userText);
		userText.setColumns(10);
		
		JPasswordField passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 150, 22);
		contentPanel.add(passwordText);
		
		JPasswordField rePasswordText = new JPasswordField();
		rePasswordText.setBounds(100, 80, 150, 22);
		contentPanel.add(rePasswordText);
		
		JButton okButton = new JButton("确定");
		okButton.setMnemonic(KeyEvent.VK_ENTER);
		/*添加一个确定按钮事件，主要用来注册账号*/
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password=new String(passwordText.getPassword());
				String rePassword=new String(rePasswordText.getPassword());
				/*如果两次密码相同则进行注册，如果不相同则提示*/
				if(password.compareTo(rePassword)==0) {
					acc.setUser(userText.getText());
					acc.setPassword(password);
					/*如果该账户在集合中存在则注册失败，如果不存在则注册成功*/
					if(SQLOperation.isAccount(acc,1)){
						System.out.println("该账户已存在，注册失败！");
					}else {
						SQLOperation.saveAccount(acc);
						System.out.println("注册成功！");
						dispose();
					}
				}
				else {
					System.out.println("两次密码输入不相同，请重新输入！");
				}
				
			}
		});
		okButton.setBounds(60, 130, 67, 23);
		contentPanel.add(okButton);
		
		JButton delButton = new JButton("清空");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passwordText.setText("");
				rePasswordText.setText("");
			}
		});
		delButton.setBounds(180, 130, 67, 23);
		contentPanel.add(delButton);		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
