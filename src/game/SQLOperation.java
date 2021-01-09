package game;

import java.sql.*;
import java.util.ArrayList;

/*连接数据库和数据库操作部分*/
public class SQLOperation{ 
    static Connection con;
    /*载入数据库的驱动和连接数据库*/
    public static void loadMysql(){
        String driverName="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/acc2048?useSSL=false&user=root&password=666666";
        loadConnection(driverName, url);
    }
    /*载入驱动和连接数据库部分*/
    private static void loadConnection(String driverName,String url) { 
        try {
        	
            Class.forName(driverName);
            System.out.println("驱动加载成功！");
            
        } catch (ClassNotFoundException e) {
        	
            System.out.println("驱动加载失败！");
            e.printStackTrace();
            
        }
        try {
        	
            con= DriverManager.getConnection(url);
            System.out.println("数据库连接成功！");
            
        } catch (SQLException e) {
        	
            System.out.println("数据库连接失败！");
            e.printStackTrace();
            
        }
    }
    /*从数据库中得到排序后的用户和得分*/
    public static Object[][] getAccount() {
    	Object [][]obj=null;
        int i=0,n = 0;
        String line="select count(*) from account;";		//得到数据库有几行信息以便初始化变量
        String sql="select user,maxscore,row_number()over(order by maxscore desc) from account;";	//从数据库得到账户信息的数据库操作字符串
        try {
            Statement stmt=con.createStatement();	//创建con连接的Statement对象
            ResultSet rs=stmt.executeQuery(line);	//创建执行SQL语句后的结果集对象
            if(rs.next())
            	n=rs.getInt(1);
            	obj =new Object[n][3];
            rs=stmt.executeQuery(sql);		//执行查询语句并返回结果集
            while(rs.next()) {
            	obj[i][0]=new String(rs.getString(1));
            	obj[i][1]=new Integer(rs.getInt(2));
            	obj[i][2]=new Integer(rs.getInt(3));
            	i++;
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    /*得到用户名为acc.user的用户所有信息，主要在进入游戏界面后获取到历史最高分*/
    public static Account getAccount(Account acc) {
		String sql="select * from account where user=?;";
		Account newAccount =new Account();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, acc.getUser());
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				newAccount.setUser(rs.getString(1));
				newAccount.setPassword(rs.getString(2));
				newAccount.setMaxScore(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newAccount;
	}
    //更改一个用户的最高分，在用户打破自己最高分时使用
    public static void saveMaxScore(Account acc) {
    	String sql="update account set maxScore=? where user=?;";
    	try {
			PreparedStatement statement=con.prepareStatement(sql);
			statement.setInt(1, acc.getMaxScore());
			statement.setString(2, acc.getUser());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /*	追加一个用户，用来在注册用户时使用*/
    public static void saveAccount(Account acc) {  
        String sql="insert into account(user,password,maxScore)\r\n" +
                "value(?,?,?);";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, acc.getUser());
            ps.setString(2, acc.getPassword());
            ps.setInt(3,acc.getMaxScore());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*	删除一个用户，用来在注销用户时使用*/
    public static void delAccount(Account acc) {
    	String sql="DELETE FROM account WHERE user=?;";
    	PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, acc.getUser());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /*判断账户是否存在于accounts账户集合中,y=0则判别账户名和密码（登入时使用），y=1则只判别账户（注册时使用）	*/
    public static boolean isAccount(Account acc , int y) {
    	System.out.println("账号开始审核！");
    	if(y == 0) {
    		String sql="select * from account where user=? and password=?;";
    		try {
    			PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1,acc.getUser());
				ps.setString(2, acc.getPassword());
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					System.out.println("账号审核通过！");
					return true;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
    	}
    	else if(y==1) {
    		String sql="select * from account where user=?;";
			try {
				PreparedStatement ps= con.prepareStatement(sql);
				ps.setString(1, acc.getUser());
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					System.out.println("账号重复！");
					return true;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
    	}
		return false;
	}
    
}
