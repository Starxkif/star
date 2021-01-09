package test;

public class Main {

	public static void main(String[] args) {
		SQLOperation.loadMysql();		//初始化数据库
		new login();
	}

}
