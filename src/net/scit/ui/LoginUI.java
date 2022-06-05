package net.scit.ui;

import java.util.Scanner;


//�α��� 
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
				System.out.println("���α׷��� �����մϴ�");
				System.exit(0);
			default:
				System.out.println("�ٽ� �Է��� �ּ���");
				break;
			}
		}
	}

	private void mainMenu() {
		System.out.println("==scit fitness ���α׷� ==");
		System.out.println("1) �����ڷ� �α���");
		System.out.println("2) ȸ������ �α���");
		System.out.println("0) ���α׷� ����");
		System.out.print(">> ���� : ");
	}

	// �����ڷ� �α��� (������ ���̺� ����)
	private void masterLogin() {
		System.out.println(" << �����ڷ� �α��� �Ϸ��� ������ Ű�� �Է��ϼ��� >> ");
		System.out.print("������ Ű �Է� : ");
		String masterKey = scanner.nextLine();

		if (!masterKey.equals("scit_master")) {
			System.out.println("Error) ������Ű�� �ùٸ��� �ʽ��ϴ�.");
			return;
		}
		System.out.println(" ## �����ڴ� ȯ���մϴ�.");
		System.out.println(" Scit Fitness ������ �����մϴ�.");
	}


}
