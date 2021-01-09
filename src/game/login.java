package game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class login extends JFrame {
	
		public login() {
		Account acc=new Account();
		JPanel contentPane;
		setTitle("2048登入");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		/*设置为两行一列，上面为背景图片，下面会登入面板*/
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel p1 = new JPanel();
		contentPane.add(p1);
		p1.setLayout(new BorderLayout(0, 0));
		
		JLabel _2048Label = new JLabel("New label");
		_2048Label.setHorizontalAlignment(SwingConstants.CENTER);
		_2048Label.setIcon(new ImageIcon("meta\\2048.png"));
		p1.add(_2048Label);
		JPanel p2 = new JPanel();
		contentPane.add(p2);
		p2.setLayout(null);
		
		JLabel accountLabel = new JLabel("账号：");
		accountLabel.setFont(new Font("幼圆", Font.BOLD, 15));
		accountLabel.setBounds(45, 20, 58, 25);
		p2.add(accountLabel);
		
		JLabel passwordLabel = new JLabel("密码：");
		passwordLabel.setFont(new Font("幼圆", Font.BOLD, 15));
		passwordLabel.setBounds(45, 55, 58, 25);
		p2.add(passwordLabel);
		
		JTextField userText = new JTextField();
		userText.setBounds(150, 20, 150, 25);
		p2.add(userText);
		
		JPasswordField passwordText = new JPasswordField();
		passwordText.setBounds(150, 55, 150, 25);
		p2.add(passwordText);
		
		JButton loginButton = new JButton("登入");
		loginButton.setMnemonic(KeyEvent.VK_ENTER);
		/*点击登入按钮事件*/
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acc.setUser(userText.getText());	//从文本框密码框获取信息给账户
				acc.setPassword(new String(passwordText.getPassword()));
				acc.setMaxScore(0);
				/*登入账户存在则进入游戏界面，不存在则提示错误！*/
				if(SQLOperation.isAccount(acc,0)) {
					new gameFrame(SQLOperation.getAccount(acc));
					dispose();	//关闭当前窗口
				}
				else {
					System.out.println("密码错误或账户不存在，请重新输入！");
				}
			}
		});
		loginButton.setBounds(70, 90, 97, 25);
		p2.add(loginButton);
		
		JButton registerButton = new JButton("注册");
		/*点击注册按钮事件*/
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new register();		//建立一个注册框架类
			}
		});
		registerButton.setBounds(240, 90, 97, 25);
		p2.add(registerButton);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
