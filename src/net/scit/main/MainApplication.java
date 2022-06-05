package net.scit.main; // 한개만!, 맨위에 있어야 함

import net.scit.ui.LoginUI;

public class MainApplication {

	public static void main(String[] args) {
		LoginUI login = new LoginUI();
		login.programStart(); 
	}

}
