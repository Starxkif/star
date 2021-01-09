package game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class numLabel extends JLabel{
	private int value;		//每个标签里面的数字
	private boolean state=false;	//标签是否合并的状态,为真表示合并
	private int level;			//表示为第几种标签，为1就是2，为10就是1024
    int i,j;
	final Color[] bgcolor = {
		      new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3),
		      new Color(0xffdac3), new Color(0xe7b08e), new Color(0xe7bf8e),
		      new Color(0xffc4c3), new Color(0xE7948e), new Color(0xbe7e56),
		      new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710)};
	final Color[] textColor = {
			  new Color(0x2A0A12), new Color(0x3B0B39), new Color(0x3104B4),
		      new Color(0xA9BCF5), new Color(0xF6CEEC), new Color(0x088A08),
		      new Color(0xE0F8F1), new Color(0x292A0A), new Color(0x3B170B),
		      new Color(0xFACC2E), new Color(0xF8E6E0), new Color(0xFE2E2E)};
	public numLabel() {
		super();
	}
	public numLabel(String str,int i,int j) {
		super(str);
		this.i=i;
		this.j=j;
		setVisible(false);
		state=false;
		setBounds((j+1)*19+j*100, (i+1)*19+i*100, 103, 103);	//初始化标签大小和位置
		setOpaque(true);	//设置字体背景颜色可见
		setFont(new Font("宋体", Font.BOLD, 50));
		setHorizontalAlignment(SwingConstants.CENTER);		//设置字体在标签中间
	}
	public void setLevel(int level) {
		try {
			this.level=level;
			value=(int)Math.pow(2, level);
			setBounds((j+1)*19+j*100, (i+1)*19+i*100, 103, 103);
			System.out.println("num中level等级："+level);
			setText(Integer.toString(value));		//设置标签中的文本
			setBackground(bgcolor[level]);		//设置标签背景颜色
			setForeground(textColor[level]);		//设置标签字体颜色
			if(level<4)
				setFont(new Font("宋体", Font.BOLD, 70));
			else if(level<7)
				setFont(new Font("宋体", Font.BOLD, 57));
			else if(level<10)
				setFont(new Font("宋体", Font.BOLD, 44));
			else if(level>=10)
				setFont(new Font("宋体", Font.BOLD, 36));
			setVisible(true);	//设置为可见
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setNull() {
		this.setVisible(false);
		this.level=0;
		value=0;
		state=false;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getLevel() {
		return level;
	}
	public int getValue() {
		return value;
	}
}
