package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mysql.fabric.xmlrpc.base.Array;


import javax.swing.JLabel;

public class gamePanel extends JPanel {
	int score = 0;	//游戏分数
	Image firstImg; // 进入游戏时的背景
	Image gameImg; // 点击初始界面后出现的界面
	Image overImg; // 游戏失败出现的界面
	Image winImg; // 游戏胜利出现的界面
	int time=0;
	enum Status {
		first, game, over, win
	}

	Status status = Status.first; // 表示游戏状态
	numLabel label[][] = new numLabel[4][4]; // 设定十六个标签作为格子
	static ExecutorService execu = Executors.newSingleThreadExecutor();		//添加一个顺序线程执行的对象

	/* 载入需要用到的背景图片 */
	private void loadImage() {
		try {
			firstImg = ImageIO.read(new File("meta\\1.png"));
			gameImg = ImageIO.read(new File("meta\\2.png"));
			overImg = ImageIO.read(new File("meta\\3.png"));
			winImg = ImageIO.read(new File("meta\\4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*初始化所有lable*/
	private void loadLabel() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				label[i][j] = new numLabel("", i, j);
				add(label[i][j]);
			}
		}
	}
	/*清空所有label的值*/
	public void delLabel() {
		score=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				label[i][j].setNull();
			}
		}
		
	}
	/*得到两个label的值，主要在游戏开始使用*/
	public void addLabel() {
		status = Status.game;
		repaint();
		randNum();
		randNum();
	}
	/*获取所有label的值，用来存档*/
	public int[][] getLabelLevel(){
		int [][]labelLevel=new int[4][4];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				labelLevel[i][j]=label[i][j].getLevel();
			}
		}
		return labelLevel;
	}
	/*设置所有label的值，用来读档后恢复*/
	public void setLabelLevel(int [][]labelLevel) {
		delLabel();
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(labelLevel[i][j]!=0)
				label[i][j].setLevel(labelLevel[i][j]);
			}
		}
	}
	public void setTime(int time) {
		this.time=time;
	}
	/*获取一个随机数并设置为一个随机坐标的label*/
	public void randNum() {
		int count = 0;
		int rand;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (label[i][j].isVisible() == false)
					count++;
			}
		}
		rand = (int) (Math.random() * count);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				if (label[i][j].isVisible() == false) {
					if (rand == 0) {
						System.out.println("随机数坐标位置！" + i + "," + j);
						label[i][j].setLevel((int) (1 + Math.random() * 2));
					}
					rand--;
				}
			}
	}

	public gamePanel() {
		setBounds(0, 0, 500, 500);
		loadLabel();
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				repaint();
				if (status == Status.first) {
					System.out.println("执行开始界面");
					addLabel();
					
				} else if (status == Status.game) {
					setFocusable(true);
				} else if (status == Status.over) {
					System.out.println("执行结束画面");
					addLabel();
				} else if (status == Status.win) {
					System.out.println("执行胜利界面");
					addLabel();
				}
			}
		});
		setBackground(new Color(0xFAF8EF));
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(status==Status.game)
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					System.out.println("up");
					execu.submit(new move(0));
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("down");
					execu.submit(new move(1));
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("left");
					execu.submit(new move(2));
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("right");
					execu.submit(new move(3));
					break;
				default:
					System.out.println("other");
				}
			}
		});
		setLayout(null);
		setVisible(true);
		requestFocus();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		loadImage();
		if (status==Status.first) {
			g2.drawImage(firstImg, 0, 0, 500, 500, this);
		} else if(status==Status.game){
			g2.drawImage(gameImg, 0, 0, 500, 500, this);
		}else if (status==Status.over) {
			g2.drawImage(overImg, 0, 0, 500, 500, this);
			repaint();
			delLabel();
		}else if (status==Status.win) {
			g2.drawImage(winImg, 0, 0, 500, 500, this);
			repaint();
			delLabel();
		}
	}

	private class move extends Thread {
		int flag;

		public move(int flag) {
			super();
			this.flag = flag;
		}

		public synchronized void run() {
			super.run();
			int nexti = 0;
			int nextj = 0;
			int first = 0;		//循环初始值
			int move = 0;		//循环移动值，如果是up or down则从前往后遍历，other则从后往前遍历
			int count = 0;
			switch (flag) {
			case 0: { // up
				nexti = -1;
				nextj = 0;
				first = 0;
				move = 1;
				break;
			}
			case 1: { // down
				nexti = 1;
				nextj = 0;
				first = 3;
				move = -1;
				break;
			}
			case 2: { // left
				nexti = 0;
				nextj = -1;
				first = 0;
				move = 1;
				break;
			}
			case 3: { // right
				nexti = 0;
				nextj = 1;
				first = 3;
				move = -1;
				break;
			}
			}
			for (int i = first; i < 4 && i >= 0; i += move) {
				for (int j = first; j < 4 && j >= 0; j += move) {
					if (!label[i][j].isVisible()) {
						continue;		//如果方块不存在则跳出本次循环
					} else {
						int q1 = i;		//记录下这个方块的位置
						int q2 = j;
						for (int k = 1; k < 4; k++) {
							if (q1 + nexti >= 0 && q1 + nexti < 4 && q2 + nextj >= 0 && q2 + nextj < 4&& !label[q1 + nexti][q2 + nextj].isVisible()) {
								/*判断下一个格子是否存在，如果不存在就直接移动一格*
								 * 移动操作就是把当前格子的值赋给下一个格子，然后隐藏当前格子*/
								label[q1 + nexti][q2 + nextj].setLevel(label[q1][q2].getLevel());
								label[q1][q2].setNull();
								count++;		//判断单次移动中格子的移动次数
								try {
									Thread.sleep(time);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								if (q1 + nexti >= 0 && q1 + nexti < 4 && q2 + nextj >= 0 && q2 + nextj < 4&& !label[q1 + nexti][q2 + nextj].isState() && !label[q1][q2].isState()&& label[q1 + nexti][q2 + nextj].getLevel() == label[q1][q2].getLevel()) {
									/*如果下一个格子存在，当前格子和下一个格子没有合并过且两个格子的值相等就进行合并
									 * 合并操作就是把当前格子的值加一后赋给下一个格子，然后隐藏当前格子，并设定合并标记*/
									label[q1 + nexti][q2 + nextj].setLevel(label[q1][q2].getLevel() + 1);
									label[q1][q2].setNull();
									label[q1 + nexti][q2 + nextj].setState(true);
									score += label[q1 + nexti][q2 + nextj].getValue();		//增加分数
									System.out.println("当前分数"+score);
									count++;
									try {
										Thread.sleep(time);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
							/*最后把当前格子的值后移一位*/
							q2 = q2 + nextj;
							q1 = q1 + nexti;
						}
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (label[i][j].getLevel() >10) {
						/*	等级大于10则表示到了2048则提示胜利*/
						status=status.win;
						repaint();
						System.out.println("胜利");	
					}
				}
			}
			if (count == 0)		//如果移动没有改变任意一个格子的位置，就进行判断能否移动，不能移动则游戏结束
				if (isGameOvewr()) {
					status=Status.over;
					repaint();
					System.out.println("game over");
				}
			randNum();
			setStateToFalse();		//设置所有合并标记为false
		}

	}
	/*用来在每次移动后取消合并标记*/
	private void setStateToFalse() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				label[i][j].setState(false);
			}
		}
	}
	/*	判断游戏是否结束*/
	public boolean isGameOvewr() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!label[i][j].isVisible())
					return false;
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (label[i][j].getLevel() == label[i][j + 1].getLevel())
					return false;
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (label[i][j].getLevel() == label[i + 1][j].getLevel())
					return false;
			}
		}
		return true;
	}
}
