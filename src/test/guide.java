package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;

public class guide extends JDialog {

	private final JPanel panel = new JPanel();

	public guide() {
		setTitle("2048手册");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JLabel label2 = new JLabel("<html>2048是一款简单小巧的益智类游戏，既简单又耐玩。<br>而且方块的值和数量会越来越多，难度随之增大。<br>游戏规则：<br>通过键盘上下左右键控制方块的移动，<br>两个数字一样的小方块相撞时就会相加合成一个方块，<br>每合并一次方块就能得到一定的分，<br>每次操作之后会在空白的方格处随机生成一个2或4的方块，<br>最终得到一个2048的方块就算胜利了，<br>如果16个格子全部填满无法移动的话则GAMEOVER。</html>");
		label2.setFont(new Font("宋体", Font.PLAIN, 14));
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setBounds(30, 30, 380, 200);
		panel.add(label2);
		JLabel label1 = new JLabel("2048帮助手册");
		label1.setFont(new Font("楷体", Font.PLAIN, 20));
		label1.setForeground(new Color(0x66,0xcc,0xff));		//设置字体颜色
		label1.setHorizontalAlignment(SwingConstants.CENTER);	//设置字体居中
		label1.setBounds(120, 10, 150, 30);
		panel.add(label1);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
