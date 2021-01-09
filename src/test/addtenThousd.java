package test;

import game.Account;
import game.SQLOperation;

public class addtenThousd {
	public static void main(String[] args) {
		SQLOperation.loadMysql();
		int count=0;
		String str1=new String();
		String str2=new String();
		String a[]= {"a","b","c","d","e","f","g","h","j","k","l","m","n","o","p","q","r","s","t","u","w","x","y","z"};
		String b[]= {"i","u","v","a","o","e","ia","ua","uo","ie","ve","ai","uai","ei","uei","ao","iao","ou","iu","an","ian","uan","en","in","un","ang","iang","uang","eng","ing","ueng","iong","ong"};
		for(int i=0;i<24;i+=2) {
			for(int j=0;j<32;j+=2) {
				for(int x=1;x<24;x+=2) {
					for(int y=1;y<32;y+=2) {
						str1=a[i]+b[j]+a[x]+b[y];
						str2=str1+"123456";
						count++;
						if(count>10001)
							return;
						SQLOperation.saveAccount(new Account(str1,str2,x*y+i*j));
					}
				}
			}
		}
	}
}
