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
			case "1" : manageManager(); break; //1. 운동 매니저 관리
			case "2" : manageProgram(); break; //2. 운동 프로그램 관리
			case "3" : manageBodyProfile(); break; //3. 바디 프로필 관리
			case "0" : System.out.println("상위 메뉴로 돌아갑니다."); return;
			default : System.out.println("다시 입력해 주세요"); break;
			}
		}
	}

	private void mainMenu() {
		System.out.println("==SCIT FITNESS==");
		System.out.println("1. 운동 매니저 관리");
		System.out.println("2. 운동 프로그램 관리");
		System.out.println("3. 바디 프로필 관리");
		System.out.print(">>선택 : ");
	}

//1. 운동 매니저 관리
	private void manageManager() {
		String choice;
		subMenu_manager();
		choice = sc.nextLine();

		switch(choice) {
		case "1" : registerManager(); break; //1. 담당 매니저 등록
		case "2" : selectPresentManager(); break; //1. 현재 담당 매니저 조회
		case "3" : changeManager(); break; //1. 담당 매니저 변경	
		case "0" : System.out.println("상위 메뉴로 돌아갑니다"); mainMenu(); //상위 메뉴로
		default : System.out.println("다시 입력해 주세요"); break;
		}
	}

//1. 운동 매니저 관리의 서브 메뉴
	private void subMenu_manager() {
		System.out.println("== " + member.getMemberName() + "님의 매니저 관리 ==");
		System.out.println("1. 담당 매니저 등록");
		System.out.println("2. 현재 담당 매니저 조회");
		System.out.println("3. 담당 매니저 변경");
		System.out.println("0. 상위 메뉴");
		System.out.print(">>선택 : ");
	}

//1. 담당 매니저 등록 - (1)
	private void registerManager() {
		System.out.println("** " + member.getMemberName() + "님의 담당 매니저를 등록합니다 **");		
		managerLevel();
	}

//1. 담당 매니저 등록 - (1) influenceLevel 조회
	private void managerLevel() {
		//모든 매니저의 InfluenceLevel을 list로 받아오기
		List<Manager> influenceLevelList = managerDao.selectAllInfluenceLevel();
		int levelNum; //영향도 레벨 입력
		int managerId;

		//influenceLevelList가 비어있으면
		if(influenceLevelList.isEmpty()) {
			System.out.println("등록된 매니저가 없습니다");
			return;
		}
		else {
			System.out.println("**등록하고자 하는 운동 매니저의 영향도 레벨을 입력하세요(1~5)");
			subMenu_Level();
			levelNum = sc.nextInt();
			
			List<Manager> pickedManager = managerDao.findByLevel(levelNum);
			
			for(Manager list : pickedManager) {
				System.out.println("매니저 ID : " + list.getManagerId() 
				+ "  매니저명 : " + list.getManagerName() 
				+ "영향도 : " + list.getInfluenceLevel());
			}
	
			System.out.print("**담당 매니저로 등록하고 싶은 매니저 ID를 입력하세요 : ");
			managerId = sc.nextInt();
			Manager manager = managerDao.findById(managerId);
			if(manager == null) {
				System.out.println("등록되어 있지 않은 매니저 ID입니다");
				managerLevel(); //Q. 이 화면으로 돌아가도 될까?
			}
			else {
			//입력한 매니저의 ID를 내 프로그램의 매니저로 등록시키기
			member.setManagerId(managerId);
			memberDao.registerMyManager(managerId); //update 쿼리문으로 되어 있음
			System.out.println("** " + member.getMemberName() + "님의 담당 매니저가 정상적으로 등록되었습니다");
			}
		}
	}
	
//인플루언스 레벨 고르기
	private void subMenu_Level() {
		System.out.println("레벨 1");
		System.out.println("레벨 2");
		System.out.println("레벨 3");
		System.out.println("레벨 4");
		System.out.println("레벨 5");
		System.out.print(">>선택 : ");
	}	

//1. 현재 담당 매니저 조회
	private void selectPresentManager() {
		String answer;
		
		System.out.println("**현재 " + member.getMemberName() + "님의 운동 매니저의 정보입니다**");
		//회원에 대한 운동 매니저가 미등록 상태이면, 매니저 등록 여부 묻기
		if(member.getManagerId() == 0) {
			System.out.println("** " + member.getMemberName() + "님은 현재 운동 매니저가 미등록 상태입니다");
			System.out.println("** 매니저를 등록하시겠습니까?(y/n)");
			answer = sc.nextLine();
			
			if(answer.equals("n")) {
				System.out.println("** 이전 화면으로 돌아갑니다");
				subMenu_manager();
			}
			else {
				registerManager(); //매니저 등록 화면으로
			}
		}
		//등록된 매니저가 있다면, 출력
		else {
			System.out.println("**현재 " + member.getMemberName() + "님의 담당 매니저의 정보**");
			System.out.println("** " + member.getMemberName() 
			+ "님의 담당 매니저 : " + member.getManagerId() + "입니다");			
		}
	}

//1. 담당 매니저 변경	
	private void changeManager() {
		String answer;
		int manager;
		
		if(member.getManagerId() == 0) {
			System.out.println("** " + member.getMemberName() + "님은 현재 운동 매니저가 미등록 상태입니다");
			System.out.println("** 매니저를 등록하시겠습니까?(y/n)");
			answer = sc.nextLine();
			
			if(answer.equals("n")) {
				System.out.println("** 이전 화면으로 돌아갑니다");
				subMenu_manager();
			}
			//없으면 매니저 등록
			else {
				registerManager();
			}
		}
		else {
			int levelNum; //영향도 레벨 입력
			int managerId;
			
			System.out.println("**현재 " + member.getMemberName() + "님의 담당 매니저의 정보**");
			System.out.println("** " + member.getMemberName() 
			+ "님의 담당 매니저 : " + member.getManagerId() + "입니다 %n");
			
			//담당 매니저 변경
			System.out.println("**등록하고자 하는 운동 매니저의 영향도 레벨을 입력하세요(1~5)");
			subMenu_Level();
			levelNum = sc.nextInt();
			List<Manager> pickedManager = managerDao.findByLevel(levelNum);

			for(Manager list : pickedManager) {
				System.out.println("매니저 ID : " + list.getManagerId() 
				+ "  매니저명 : " + list.getManagerName() 
				+ "영향도 : " + list.getInfluenceLevel());
			}

			System.out.print("**담당 매니저로 등록하고 싶은 매니저 ID를 입력하세요 : ");
			managerId = sc.nextInt();
			Manager mana = managerDao.findById(managerId);
			if(mana == null) {
				System.out.println("등록되어 있지 않은 매니저 ID입니다");
				managerLevel(); //Q. 이 화면으로 돌아가도 될까?
			}
			//입력한 매니저의 ID를 내 프로그램의 매니저로 등록시키기
			else {
				member.setManagerId(managerId);
				memberDao.registerMyManager(managerId); //update 쿼리문으로 되어 있음
				System.out.println("** " + member.getMemberName() + "님의 담당 매니저가 정상적으로 변경되었습니다");
			}
		}
	}

//2. 운동 프로그램 관리
	private void manageProgram() {
		String choice;
		subMenu_program();	
		choice = sc.nextLine();
		
		switch(choice) {
		case "1" : selectPresentProgram(); break; //2.현재 운동 프로그램 조회
		case "2" : changeProgram(); break; //2.운동 프로그램 변경
		case "3" : createMyProgram(); break; //2.나만의 운동 프로그램 생성
		case "0" : System.out.println("상위 메뉴로 돌아갑니다"); subMenu_manager();
		default : System.out.println("번호를 다시 입력해 주세요"); break;
		}
	}	

//2. 운동 프로그램 관리 서브메뉴
	private void subMenu_program() {
		System.out.println("== " + member.getMemberName() + "님의 운동 프로그램 관리 ==");
		System.out.println("1. 현재 운동 프로그램 조회"); 
		System.out.println("2. 운동 프로그램 변경");
		System.out.println("3. 나만의 운동 프로그램 생성");
		System.out.println("0. 상위 메뉴");
		System.out.print(">>선택 : ");
	}

//2.현재 운동 프로그램 조회
	private void selectPresentProgram() {
		
	}

//2.운동 프로그램 변경	
	private void changeProgram() {

	}
	
//2.나만의 운동 프로그램 생성	
	private void createMyProgram() {

	}

//3.바디 프로필 관리
	private void manageBodyProfile() {
		String choice;
		
		subMenu_bodyProfile();
		choice = sc.nextLine();
		
		switch(choice) {
		case "1" : registerProfile(); break;
		case "2" : selectInbody(); break;
		case "3" : myFuture(); break;
		case "0" : System.out.println("상위 메뉴로 돌아갑니다"); subMenu_manager();
		default : System.out.println("번호를 다시 입력해 주세요");	
		}
	}
	

	//3.바디 프로필 서브 메뉴
	private void subMenu_bodyProfile() {
		System.out.println("== " + member.getMemberName() + "님의 바디 프로필 관리 ==");
		System.out.println("1. 바디 프로필 등록");
		System.out.println("2. 현재 인바디 조회");
		System.out.println("3. 미래의 " + member.getMemberName() + "님의 모습");
		System.out.println("0. 상위 메뉴");
		System.out.print(">>선택 : ");
	}

//3.바디 프로필 등록
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
	
//3.인바디 조회	
	private void selectInbody() {		
	}
	
//3.미래의 나 (프로그램을 통해 얻을 수 있는 수치)
	private void myFuture() {
		
	}


}