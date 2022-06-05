package net.scit.ui;

import java.util.List;
import java.util.Scanner;

import net.scit.dao.BodyProfileDAO;

//import org.apache.ibatis.reflection.SystemMetaObject;

import net.scit.dao.ManagerDAO;
import net.scit.dao.MemberDAO;
import net.scit.vo.BodyProfile;
import net.scit.vo.Manager;
import net.scit.vo.Member;

public class MemberLoginUI {

	MemberDAO memberDao = new MemberDAO();
	ManagerDAO managerDao = new ManagerDAO();
	BodyProfileDAO bodyDao = new BodyProfileDAO();
	Member member = new Member();
	Manager manager = new Manager();
	BodyProfile body = new BodyProfile();
	Scanner sc = new Scanner(System.in);
	
	public void memberStart() {		
		String choice;
		
		while(true) {
			mainMenu();
			choice = sc.nextLine();
			
			switch(choice) {
			case "1" : manageManager(); break; //1. � �Ŵ��� ����
			case "2" : manageProgram(); break; //2. � ���α׷� ����
			case "3" : manageBodyProfile(); break; //3. �ٵ� ������ ����
			case "0" : System.out.println("���� �޴��� ���ư��ϴ�."); return;
			default : System.out.println("�ٽ� �Է��� �ּ���"); break;
			}
		}
	}

	private void mainMenu() {
		System.out.println("==SCIT FITNESS==");
		System.out.println("1. � �Ŵ��� ����");
		System.out.println("2. � ���α׷� ����");
		System.out.println("3. �ٵ� ������ ����");
		System.out.print(">>���� : ");
	}

//1. � �Ŵ��� ����
	private void manageManager() {
		String choice;
		subMenu_manager();
		choice = sc.nextLine();

		switch(choice) {
		case "1" : registerManager(); break; //1. ��� �Ŵ��� ���
		case "2" : selectPresentManager(); break; //1. ���� ��� �Ŵ��� ��ȸ
		case "3" : changeManager(); break; //1. ��� �Ŵ��� ����	
		case "0" : System.out.println("���� �޴��� ���ư��ϴ�"); mainMenu(); //���� �޴���
		default : System.out.println("�ٽ� �Է��� �ּ���"); break;
		}
	}

//1. � �Ŵ��� ������ ���� �޴�
	private void subMenu_manager() {
		System.out.println("== " + member.getMemberName() + "���� �Ŵ��� ���� ==");
		System.out.println("1. ��� �Ŵ��� ���");
		System.out.println("2. ���� ��� �Ŵ��� ��ȸ");
		System.out.println("3. ��� �Ŵ��� ����");
		System.out.println("0. ���� �޴�");
		System.out.print(">>���� : ");
	}

//1. ��� �Ŵ��� ��� - (1)
	private void registerManager() {
		System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ����� ����մϴ� **");		
		managerLevel();
	}

//1. ��� �Ŵ��� ��� - (1) influenceLevel ��ȸ
	private void managerLevel() {
		//��� �Ŵ����� InfluenceLevel�� list�� �޾ƿ���
		List<Manager> influenceLevelList = managerDao.selectAllInfluenceLevel();
		int levelNum; //���⵵ ���� �Է�
		int managerId;

		//influenceLevelList�� ���������
		if(influenceLevelList.isEmpty()) {
			System.out.println("��ϵ� �Ŵ����� �����ϴ�");
			return;
		}
		else {
			System.out.println("**����ϰ��� �ϴ� � �Ŵ����� ���⵵ ������ �Է��ϼ���(1~5)");
			subMenu_Level();
			levelNum = sc.nextInt();
			
			List<Manager> pickedManager = managerDao.findByLevel(levelNum);
			
			for(Manager list : pickedManager) {
				System.out.println("�Ŵ��� ID : " + list.getManagerId() 
				+ "  �Ŵ����� : " + list.getManagerName() 
				+ "���⵵ : " + list.getInfluenceLevel());
			}
	
			System.out.print("**��� �Ŵ����� ����ϰ� ���� �Ŵ��� ID�� �Է��ϼ��� : ");
			managerId = sc.nextInt();
			Manager manager = managerDao.findById(managerId);
			if(manager == null) {
				System.out.println("��ϵǾ� ���� ���� �Ŵ��� ID�Դϴ�");
				managerLevel(); //Q. �� ȭ������ ���ư��� �ɱ�?
			}
			else {
			//�Է��� �Ŵ����� ID�� �� ���α׷��� �Ŵ����� ��Ͻ�Ű��
			member.setManagerId(managerId);
			memberDao.registerMyManager(managerId); //update ���������� �Ǿ� ����
			System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ����� ���������� ��ϵǾ����ϴ�");
			}
		}
	}
	
//���÷�� ���� ����
	private void subMenu_Level() {
		System.out.println("���� 1");
		System.out.println("���� 2");
		System.out.println("���� 3");
		System.out.println("���� 4");
		System.out.println("���� 5");
		System.out.print(">>���� : ");
	}	

//1. ���� ��� �Ŵ��� ��ȸ
	private void selectPresentManager() {
		String answer;
		
		System.out.println("**���� " + member.getMemberName() + "���� � �Ŵ����� �����Դϴ�**");
		//ȸ���� ���� � �Ŵ����� �̵�� �����̸�, �Ŵ��� ��� ���� ����
		if(member.getManagerId() == 0) {
			System.out.println("** " + member.getMemberName() + "���� ���� � �Ŵ����� �̵�� �����Դϴ�");
			System.out.println("** �Ŵ����� ����Ͻðڽ��ϱ�?(y/n)");
			answer = sc.nextLine();
			
			if(answer.equals("n")) {
				System.out.println("** ���� ȭ������ ���ư��ϴ�");
				subMenu_manager();
			}
			else {
				registerManager(); //�Ŵ��� ��� ȭ������
			}
		}
		//��ϵ� �Ŵ����� �ִٸ�, ���
		else {
			System.out.println("**���� " + member.getMemberName() + "���� ��� �Ŵ����� ����**");
			System.out.println("** " + member.getMemberName() 
			+ "���� ��� �Ŵ��� : " + member.getManagerId() + "�Դϴ�");			
		}
	}

//1. ��� �Ŵ��� ����	
	private void changeManager() {
		String answer;
		int manager;
		
		if(member.getManagerId() == 0) {
			System.out.println("** " + member.getMemberName() + "���� ���� � �Ŵ����� �̵�� �����Դϴ�");
			System.out.println("** �Ŵ����� ����Ͻðڽ��ϱ�?(y/n)");
			answer = sc.nextLine();
			
			if(answer.equals("n")) {
				System.out.println("** ���� ȭ������ ���ư��ϴ�");
				subMenu_manager();
			}
			//������ �Ŵ��� ���
			else {
				registerManager();
			}
		}
		else {
			int levelNum; //���⵵ ���� �Է�
			int managerId;
			
			System.out.println("**���� " + member.getMemberName() + "���� ��� �Ŵ����� ����**");
			System.out.println("** " + member.getMemberName() 
			+ "���� ��� �Ŵ��� : " + member.getManagerId() + "�Դϴ� %n");
			
			//��� �Ŵ��� ����
			System.out.println("**����ϰ��� �ϴ� � �Ŵ����� ���⵵ ������ �Է��ϼ���(1~5)");
			subMenu_Level();
			levelNum = sc.nextInt();
			List<Manager> pickedManager = managerDao.findByLevel(levelNum);

			for(Manager list : pickedManager) {
				System.out.println("�Ŵ��� ID : " + list.getManagerId() 
				+ "  �Ŵ����� : " + list.getManagerName() 
				+ "���⵵ : " + list.getInfluenceLevel());
			}

			System.out.print("**��� �Ŵ����� ����ϰ� ���� �Ŵ��� ID�� �Է��ϼ��� : ");
			managerId = sc.nextInt();
			Manager mana = managerDao.findById(managerId);
			if(mana == null) {
				System.out.println("��ϵǾ� ���� ���� �Ŵ��� ID�Դϴ�");
				managerLevel(); //Q. �� ȭ������ ���ư��� �ɱ�?
			}
			//�Է��� �Ŵ����� ID�� �� ���α׷��� �Ŵ����� ��Ͻ�Ű��
			else {
				member.setManagerId(managerId);
				memberDao.registerMyManager(managerId); //update ���������� �Ǿ� ����
				System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ����� ���������� ����Ǿ����ϴ�");
			}
		}
	}

//2. � ���α׷� ����
	private void manageProgram() {
		String choice;
		subMenu_program();	
		choice = sc.nextLine();
		
		switch(choice) {
		case "1" : selectPresentProgram(); break; //2.���� � ���α׷� ��ȸ
		case "2" : changeProgram(); break; //2.� ���α׷� ����
		case "3" : createMyProgram(); break; //2.������ � ���α׷� ����
		case "0" : System.out.println("���� �޴��� ���ư��ϴ�"); subMenu_manager();
		default : System.out.println("��ȣ�� �ٽ� �Է��� �ּ���"); break;
		}
	}	

//2. � ���α׷� ���� ����޴�
	private void subMenu_program() {
		System.out.println("== " + member.getMemberName() + "���� � ���α׷� ���� ==");
		System.out.println("1. ���� � ���α׷� ��ȸ"); 
		System.out.println("2. � ���α׷� ����");
		System.out.println("3. ������ � ���α׷� ����");
		System.out.println("0. ���� �޴�");
		System.out.print(">>���� : ");
	}

//2.���� � ���α׷� ��ȸ
	private void selectPresentProgram() {
		
	}

//2.� ���α׷� ����	
	private void changeProgram() {

	}
	
//2.������ � ���α׷� ����	
	private void createMyProgram() {

	}

//3.�ٵ� ������ ����
	private void manageBodyProfile() {
		String choice;
		
		subMenu_bodyProfile();
		choice = sc.nextLine();
		
		switch(choice) {
		case "1" : registerProfile(); break;
		case "2" : selectInbody(); break;
		case "3" : myFuture(); break;
		case "0" : System.out.println("���� �޴��� ���ư��ϴ�"); subMenu_manager();
		default : System.out.println("��ȣ�� �ٽ� �Է��� �ּ���");	
		}
	}
	

	//3.�ٵ� ������ ���� �޴�
	private void subMenu_bodyProfile() {
		System.out.println("== " + member.getMemberName() + "���� �ٵ� ������ ���� ==");
		System.out.println("1. �ٵ� ������ ���");
		System.out.println("2. ���� �ιٵ� ��ȸ");
		System.out.println("3. �̷��� " + member.getMemberName() + "���� ���");
		System.out.println("0. ���� �޴�");
		System.out.print(">>���� : ");
	}

//3.�ٵ� ������ ���
	private void registerProfile() {
//		int memberId;
//		Member member = managerDao.findById(memberId);
//		if(member == null) {	
//		}
		
		int memberId;
		int weight;
		int height;
		
		//body = bodyDao.findById(memberId);
		
		
		
	}
	
//3.�ιٵ� ��ȸ	
	private void selectInbody() {		
	}
	
//3.�̷��� �� (���α׷��� ���� ���� �� �ִ� ��ġ)
	private void myFuture() {
		
	}


}