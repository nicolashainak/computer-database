package main.webapp;

import MySQLAccess;

public class main {
	public static void main(String[] args)throws Exception{
		MySQLAcces dao = new MySQLAccess();
		dao.readDatabase();
		
	}
}
