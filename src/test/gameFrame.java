package test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import test.gamePanel.Status;

import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class gameFrame extends JFrame {
	int time;		//用来修改移动间隔
	Account acc;
	JPanel contentPane = new JPanel();
	gamePanel gp;
	Play0 mp3[]=new Play0[100];		//播放背景音乐的对象
	public gameFrame(Account acc) {
		this.acc=acc;
		setTitle("2048");
		mp3[0]=new Play0();		//初始化一个音乐对象
		mp3[0].start();		//启动音乐对象线程
		setBounds(100, 100, 530, 615);
		/* ---添加菜单面板---- */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		/* 1、添加游戏菜单 */
		JMenu gameMenu = new JMenu("游戏");
		menuBar.add(gameMenu);

		JMenuItem item1 = new JMenuItem("重开");
		item1.addActionListener(new remakeListener());
		gameMenu.add(item1);

		JMenuItem item2 = new JMenuItem("存档");
		item2.addActionListener(new outFile());
		gameMenu.add(item2);
		
		JMenuItem item3= new JMenuItem("读档");
		item3.addActionListener(new inFile());
		gameMenu.add(item3);

		JMenuItem item4 = new JMenuItem("排行榜");
		/*点击排行榜会弹出排行榜框架*/
		item4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new rankList();
			}
		});
		gameMenu.add(item4);

		JMenuItem item5 = new JMenuItem("退出游戏");
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		gameMenu.add(item5);
		/* 2、添加设置菜单 */
		JMenu settingMenu = new JMenu("设置");
		menuBar.add(settingMenu);
		/*	2.1添加速度菜单到设置菜单中*/
		JMenu item6 =new JMenu("速度："+time);
		time=0;
		JMenuItem addspeedItem=new JMenuItem(" + ", KeyEvent.VK_EQUALS);
		addspeedItem.addActionListener(new ActionListener() {
			/*当点击加号菜单选项时会增加移动时间间隔，当间隔大于100时就不再增加*/
			@Override
			public void actionPerformed(ActionEvent e) {
				item6.doClick(0);		//设置点击加号菜单选项时不关闭整个菜单
				time+=10;
				if(time>100) {
					time=100;
				}
				gp.setTime(time);
				item6.setText("速度："+time);
			}
		});
		item6.add(addspeedItem);
		JMenuItem minspeedItem=new JMenuItem(" - ", KeyEvent.VK_EQUALS);
		minspeedItem.addActionListener(new ActionListener() {
			/*当点击减号菜单选项时会减少移动时间间隔，当间隔小于0时就不再减少*/
			@Override
			public void actionPerformed(ActionEvent e) {
				item6.doClick(0);
				time-=10;
				if(time<=0) {
					time=0;
				}
				gp.setTime(time);
				item6.setText("速度："+time);
			}
		});
		item6.add(minspeedItem);
		settingMenu.add(item6);
		/*2.2	添加音乐菜单选项*/
		JMenuItem item7=new JMenuItem("音乐(yes)");
		item7.addActionListener(new ActionListener() {
			/*当音乐是开启时就结束音乐线程，音乐是关闭是就创建一个音乐线程并启动*/
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(item7.getText().compareTo("音乐(yes)")==0) {
						item7.setText("音乐(no)");
						mp3[0].stop();
					}else if (item7.getText().compareTo("音乐(no)")==0) {
						item7.setText("音乐(yes)");
						mp3[0]=new Play0();
						mp3[0].start();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		settingMenu.add(item7);
		
		JMenuItem item8=new JMenuItem("注销");
		item8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new logout(acc);		//弹出注销框架
			}
		});
		settingMenu.add(item8);
		/*3、 添加帮助菜单 */
		JMenu helpMenu = new JMenu("帮助");
		menuBar.add(helpMenu);

		JMenuItem item9 = new JMenuItem("2048手册");
		item9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new guide();		//弹出2048手册框架
			}
		});
		helpMenu.add(item9);

		JMenuItem item10 = new JMenuItem("关于2048");
		item10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new about();		//弹出关于框架		
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		helpMenu.add(item10);

//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*添加工具栏*/
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(5, 5, 133, 40);
		toolBar.setFloatable(false);
		contentPane.add(toolBar);

		JButton writeFileButton = new JButton("write");
		writeFileButton.addActionListener(new outFile());	//添加存档事件
		writeFileButton.setFont(new Font("Serif", Font.BOLD, 16));
		writeFileButton.setToolTipText("存档");		//鼠标移动到按钮上显示存档
		toolBar.add(writeFileButton);

		JButton readFileButton = new JButton("read");
		readFileButton.addActionListener(new inFile());		//添加读档事件
		readFileButton.setToolTipText("读档");
		readFileButton.setFont(new Font("Serif", Font.BOLD, 16));
		toolBar.add(readFileButton);
		
		JButton remakeButton=new JButton(" Re ");
		remakeButton.setToolTipText("重开");
		remakeButton.setFont(new Font("Serif",Font.BOLD,16));
		remakeButton.addActionListener(new remakeListener());	//添加重开事件
		toolBar.add(remakeButton);
		/*添加状态栏和游戏面板的线程*/
		stateBar sb=new stateBar();
		sb.start();
		writeFileButton.setFocusable(false);	//设置所有按钮离开焦点，以防抢到游戏面板的焦点
		readFileButton.setFocusable(false);
		remakeButton.setFocusable(false);
		setResizable(false);		//设置不能拖动窗口大小
		setLocationRelativeTo(null);	//设置窗口位置居中
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	/*重开事件的监听器*/
	private class remakeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			gp.delLabel();	//清空所有标签
			gp.addLabel();	//添加两个随机标签并开始游戏
		}
		
	}
	/*存档、写文件事件的监听器*/
	private class outFile implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int level[][];		//设置一个二维数组用来保存标签的值
			level=gp.getLabelLevel();	//获取到标签的值
			DataOutputStream ds;
			try {
				ds = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("files\\"+acc.getUser()+"file.txt"))));
				for(int i=0;i<4;i++) {
					for(int j=0;j<4;j++) {
						ds.writeInt(level[i][j]);
					}
				}
				ds.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("存档成功！");
		}
		
	}
	/*读档、读文件事件的监听器*/
	private class inFile implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int level[][]=new int[4][4];
			try {
				DataInputStream ds=new DataInputStream(new BufferedInputStream(new FileInputStream(new File("files\\"+acc.getUser()+"file.txt"))));
				for(int i=0;i<4;i++) {
					for(int j=0;j<4;j++) {
						level[i][j]=ds.readInt();
					}
				}
				ds.close();
			} catch (FileNotFoundException e1) {
				try {
					new File("files\\"+acc.getUser()+"file.txt").createNewFile();	//当文件不存在时新建一个文件
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gp.setLabelLevel(level);		//将获取到的值传递给标签
			System.out.println("读档成功！");
		}
		
	}
	class stateBar extends Thread{
		JLabel statusLabel;
		JLabel maxscoreLabel;
		JLabel scoreLabel;
		public stateBar() {
			/*--添加状态栏参数--*/
			statusLabel=new JLabel();
			statusLabel.setFont(new Font("幼圆", Font.BOLD, 18));
			statusLabel.setForeground(Color.PINK);
			statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
			statusLabel.setText("playing");
			statusLabel.setBounds(155, 5, 70, 30);
			
			maxscoreLabel=new JLabel();
			maxscoreLabel.setFont(new Font("华文行楷", Font.BOLD, 16));
			maxscoreLabel.setForeground(Color.ORANGE);
			maxscoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
			maxscoreLabel.setText("最高分：10000");
			maxscoreLabel.setSize(110, 30);
			maxscoreLabel.setLocation(240, 5);
			
			scoreLabel=new JLabel();
			scoreLabel.setFont(new Font("华文行楷", Font.BOLD, 16));
			scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scoreLabel.setText("当前得分：23454");
			scoreLabel.setSize(130, 30);
			scoreLabel.setLocation(370, 5);
			
			contentPane.add(statusLabel);
			contentPane.add(scoreLabel);
			contentPane.add(maxscoreLabel);
			
			gp = new gamePanel();
			gp.setBounds(5, 43, 516, 518);
			contentPane.add(gp);
		}
		public void run() {
			super.run();
			try {
				while(true) {
					Thread.sleep(1000);
					/*判断状态*/
					if(gp.status==Status.first)
					statusLabel.setText("UnGame");
					else if(gp.status==Status.game)
						statusLabel.setText("Playing");
					else if(gp.status==Status.over) {
						SQLOperation.saveMaxScore(acc);
						statusLabel.setText("failed");
					}
					else if(gp.status==Status.win) {						
						SQLOperation.saveMaxScore(acc);
						statusLabel.setText("win");
					}
					/*判断最高分*/
					if(gp.score>acc.getMaxScore()) {
						acc.setMaxScore(gp.score);
					}
					maxscoreLabel.setText("最高分："+acc.getMaxScore());
					/*判断当前分数*/
					scoreLabel.setText("当前分数："+gp.score);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
