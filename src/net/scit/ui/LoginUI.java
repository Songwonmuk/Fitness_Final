package net.scit.ui;

import java.util.Scanner;


//로그인 
public class LoginUI {

	private AdminLoginUI managerUI = new AdminLoginUI();
	private MemberLoginUI specificUI = new MemberLoginUI();
	private Scanner scanner = new Scanner(System.in);

	public void programStart() {

		String choice;

		while (true) {
			mainMenu();
			choice = scanner.nextLine();

			switch (choice) {
			case "1":
				masterLogin();
				managerUI.managerManagementStart();
				break;
			case "2":
				specificUI.login();
				break;
			case "0":
				System.out.println("프로그램을 종료합니다");
				System.exit(0);
			default:
				System.out.println("다시 입력해 주세요");
				break;
			}
		}
	}

	private void mainMenu() {
		System.out.println("==scit fitness 프로그램 ==");
		System.out.println("1) 관리자로 로그인");
		System.out.println("2) 회원으로 로그인");
		System.out.println("0) 프로그램 종료");
		System.out.print(">> 선택 : ");
	}

	// 관리자로 로그인 (관리자 테이블 요함)
	private void masterLogin() {
		System.out.println(" << 관리자로 로그인 하려면 마스터 키를 입력하세요 >> ");
		System.out.print("마스터 키 입력 : ");
		String masterKey = scanner.nextLine();

		if (!masterKey.equals("scit_master")) {
			System.out.println("Error) 마스터키가 올바르지 않습니다.");
			return;
		}
		System.out.println(" ## 관리자님 환영합니다.");
		System.out.println(" Scit Fitness 관리를 시작합니다.");
	}


}
