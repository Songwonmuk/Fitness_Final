package net.scit.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.scit.dao.BodyProfileDAO;
import net.scit.dao.ExerciseDAO;
import net.scit.dao.ManagerDAO;
import net.scit.dao.MemberDAO;
import net.scit.dao.ProgramDAO;
import net.scit.vo.BodyProfile;
import net.scit.vo.Exercise;
import net.scit.vo.Manager;
import net.scit.vo.Member;
import net.scit.vo.Program;

public class SpecificMemberUI {
	private MemberDAO memberDao = new MemberDAO();
	private Scanner scanner = new Scanner(System.in);
	private ManagerDAO managerDao = new ManagerDAO();
	private BodyProfileDAO bodyDao = new BodyProfileDAO();
	private ExerciseDAO exerDao = new ExerciseDAO();
	private ProgramDAO proDao = new ProgramDAO();

	public void specificMember() {
		System.out.println("아이디 입력 : ");
		int memberId = scanner.nextInt();
		System.out.println("비밀번호 입력 : ");
		String memberPw = scanner.nextLine();
		Member member = memberDao.findById(memberId);
//		Member pwVO = memberDao.findByPw(memberPw);

		// id가 등록되어 있지 않으면 로그인 창 또는 회원가입 창으로 이동, 입력한 회원 아이디와 비번이 모두 일치하면 로그인
		if (member == null) {
			System.out.println("**등록되지 않은 회원입니다");
			System.out.print("**회원가입 창으로 이동하시겠습니까?(y/n)");
			String answer = scanner.nextLine();

			if (answer.equals("n")) {
				System.out.println("**로그인 창으로 돌아갑니다");
				specificMember();
			} else {
				System.out.println("**회원가입 창으로 이동합니다");
				memberJoinIn();
			}
		}

		//
		else if (member.getMemberPw().equals(memberPw)) {
			System.out.println("== " + member.getMemberName() + "님, 환영합니다! ==");

			memberStart(member);

		}
	}

	private void memberJoinIn() {
		String memberPw; // 멤버 pw
		String memberName; // 멤버 이름
		String phone; // 전화번호
		String gender; // 성별 (남, 여)
		String birth; // 생일

		System.out.print("패스워드 입력 : ");
		memberPw = scanner.nextLine();
		System.out.print("이름 입력 : ");
		memberName = scanner.nextLine();
		System.out.print("전화번호 입력 : ");
		phone = scanner.nextLine();
		System.out.print("성별 입력 (남/여) : ");
		gender = scanner.nextLine();
		System.out.print("생일 입력(ex)1990/01/01) : ");
		birth = scanner.nextLine();

		Member member = new Member(memberPw, memberName, phone, gender, birth);

		int result = memberDao.join(member);
		System.out.printf("**%d명의 회원이 등록되었습니다.%n", result);
	}

	public void memberStart(Member member) {
		String choice;

		while (true) {
			mainMenu();
			choice = scanner.nextLine();

			switch (choice) {
			case "1":
				manageManager(member);
				break; // 1. 운동 매니저 관리
			case "2":
				manageProgram(member);
				break; // 2. 운동 프로그램 관리
			case "3":
				manageBodyProfile(member);
				break; // 3. 바디 프로필 관리
			case "0":
				System.out.println("상위 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("다시 입력해 주세요");
				break;
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

	// 1. 운동 매니저 관리
	private void manageManager(Member member) {
		String choice;
		subMenu_manager(member);
		choice = scanner.nextLine();

		switch (choice) {
		case "1":
			registerManager(member);
			break; // 1. 담당 매니저 등록
		case "2":
			selectPresentManager(member);
			break; // 1. 현재 담당 매니저 조회
		case "3":
			changeManager(member);
			break; // 1. 담당 매니저 변경
		case "0":
			System.out.println("상위 메뉴로 돌아갑니다");
			mainMenu(); // 상위 메뉴로
		default:
			System.out.println("다시 입력해 주세요");
			break;
		}
	}

	// 1. 운동 매니저 관리의 서브 메뉴
	private void subMenu_manager(Member member) {
		System.out.println("== " + member.getMemberName() + "님의 매니저 관리 ==");
		System.out.println("1. 담당 매니저 등록");
		System.out.println("2. 현재 담당 매니저 조회");
		System.out.println("3. 담당 매니저 변경");
		System.out.println("0. 상위 메뉴");
		System.out.print(">>선택 : ");
	}

	// 1. 담당 매니저 등록 - (1)
	private void registerManager(Member member) {
		System.out.println("** " + member.getMemberName() + "님의 담당 매니저를 등록합니다 **");
		managerLevel(member);
	}

	// 1. 담당 매니저 등록 - (1) influenceLevel 조회
	private void managerLevel(Member member) {
		// 모든 매니저의 InfluenceLevel을 list로 받아오기
		List<Manager> influenceLevelList = managerDao.selectAllInfluenceLevel();
		int levelNum; // 영향도 레벨 입력
		int managerId;

		// influenceLevelList가 비어있으면
		if (influenceLevelList.isEmpty()) {
			System.out.println("등록된 매니저가 없습니다");
			return;
		} else {
			System.out.println("**등록하고자 하는 운동 매니저의 영향도 레벨을 입력하세요(1~5)");
			subMenu_Level();
			levelNum = scanner.nextInt();

			List<Manager> pickedManager = managerDao.findByLevel(levelNum);

			for (Manager list : pickedManager) {
				System.out.println("매니저 ID : " + list.getManagerId() + "  매니저명 : " + list.getManagerName() + "영향도 : "
						+ list.getInfluenceLevel());
			}

			System.out.print("**담당 매니저로 등록하고 싶은 매니저 ID를 입력하세요 : ");
			managerId = scanner.nextInt();
			Manager manager = managerDao.findById(managerId);
			if (manager == null) {
				System.out.println("등록되어 있지 않은 매니저 ID입니다");
				managerLevel(member); // Q. 이 화면으로 돌아가도 될까?
			} else {
				// 입력한 매니저의 ID를 내 프로그램의 매니저로 등록시키기
				member.setManagerId(managerId);
				memberDao.registerMyManager(managerId); // update 쿼리문으로 되어 있음
				System.out.println("** " + member.getMemberName() + "님의 담당 매니저가 정상적으로 등록되었습니다");
			}
		}
	}

	// 인플루언스 레벨 고르기
	private void subMenu_Level() {
		System.out.println("레벨 1");
		System.out.println("레벨 2");
		System.out.println("레벨 3");
		System.out.println("레벨 4");
		System.out.println("레벨 5");
		System.out.print(">>선택 : ");
	}

	// 1. 현재 담당 매니저 조회
	private void selectPresentManager(Member member) {
		String answer;

		System.out.println("**현재 " + member.getMemberName() + "님의 운동 매니저의 정보입니다**");
		// 회원에 대한 운동 매니저가 미등록 상태이면, 매니저 등록 여부 묻기
		if (member.getManagerId() == 0) {
			System.out.println("** " + member.getMemberName() + "님은 현재 운동 매니저가 미등록 상태입니다");
			System.out.println("** 매니저를 등록하시겠습니까?(y/n)");
			answer = scanner.nextLine();

			if (answer.equals("n")) {
				System.out.println("** 이전 화면으로 돌아갑니다");
				subMenu_manager(member);
			} else {
				registerManager(member); // 매니저 등록 화면으로
			}
		}
		// 등록된 매니저가 있다면, 출력
		else {
			System.out.println("**현재 " + member.getMemberName() + "님의 담당 매니저의 정보**");
			System.out.println("** " + member.getMemberName() + "님의 담당 매니저 : " + member.getManagerId() + "입니다");
		}
	}

	// 1. 담당 매니저 변경
	private void changeManager(Member member) {
		String answer;
		int manager;

		if (member.getManagerId() == 0) {
			System.out.println("** " + member.getMemberName() + "님은 현재 운동 매니저가 미등록 상태입니다");
			System.out.println("** 매니저를 등록하시겠습니까?(y/n)");
			answer = scanner.nextLine();

			if (answer.equals("n")) {
				System.out.println("** 이전 화면으로 돌아갑니다");
				subMenu_manager(member);
			}
			// 없으면 매니저 등록
			else {
				registerManager(member);
			}
		} else {
			int levelNum; // 영향도 레벨 입력
			int managerId;

			System.out.println("**현재 " + member.getMemberName() + "님의 담당 매니저의 정보**");
			System.out.println("** " + member.getMemberName() + "님의 담당 매니저 : " + member.getManagerId() + "입니다 %n");

			// 담당 매니저 변경
			System.out.println("**등록하고자 하는 운동 매니저의 영향도 레벨을 입력하세요(1~5)");
			subMenu_Level();
			levelNum = scanner.nextInt();
			List<Manager> pickedManager = managerDao.findByLevel(levelNum);

			for (Manager list : pickedManager) {
				System.out.println("매니저 ID : " + list.getManagerId() + "  매니저명 : " + list.getManagerName() + "영향도 : "
						+ list.getInfluenceLevel());
			}

			System.out.print("**담당 매니저로 등록하고 싶은 매니저 ID를 입력하세요 : ");
			managerId = scanner.nextInt();
			Manager mana = managerDao.findById(managerId);
			if (mana == null) {
				System.out.println("등록되어 있지 않은 매니저 ID입니다");
				managerLevel(member); // Q. 이 화면으로 돌아가도 될까?
			}
			// 입력한 매니저의 ID를 내 프로그램의 매니저로 등록시키기
			else {
				member.setManagerId(managerId);
				memberDao.registerMyManager(managerId); // update 쿼리문으로 되어 있음
				System.out.println("** " + member.getMemberName() + "님의 담당 매니저가 정상적으로 변경되었습니다");
			}
		}
	}

	// 2. 운동 프로그램 관리
	private void manageProgram(Member member) {
		String choice;
		subMenu_program(member);
		choice = scanner.nextLine();

		switch (choice) {
		case "1":
			selectPresentProgram(member);
			break; // 2.현재 운동 프로그램 조회
		case "2":
			changeProgram(member);
			break; // 2.운동 프로그램 변경
		case "3":
			createMyProgram(member);
			break; // 2.나만의 운동 프로그램 생성
		case "0":
			System.out.println("상위 메뉴로 돌아갑니다");
			subMenu_manager(member);
		default:
			System.out.println("번호를 다시 입력해 주세요");
			break;
		}
	}

	// 2. 운동 프로그램 관리 서브메뉴
	private void subMenu_program(Member member) {
		System.out.println("== " + member.getMemberName() + "님의 운동 프로그램 관리 ==");
		System.out.println("1. 현재 운동 프로그램 조회");
		System.out.println("2. 운동 프로그램 변경");
		System.out.println("3. 나만의 운동 프로그램 생성");
		System.out.println("0. 상위 메뉴");
		System.out.print(">>선택 : ");
	}

	// 2.현재 운동 프로그램 조회
	private void selectPresentProgram(Member member) {

		System.out.println(" << " + member.getMemberName() + " 회원님의 현재 운동 프로그램 조회 >> ");
		System.out.println("현재 회원님이 진행중인 운동 프로그램입니다.");
		System.out.println("[프로그램번호]     [프로그램이름]    [운동타입]     [소모칼로리]     [골격근량 증가량]    [스트레스 감소량]    [밸런스 증가량]");
		System.out.println("=================================================================================");
		Program membersProgram = proDao.findByMemberID(member.getMemberId());
		System.out.println(membersProgram);
		System.out.println("=================================================================================");

	}

	// 2.운동 프로그램 변경
	private void changeProgram(Member member) {

		System.out.println(" << " + member.getMemberName() + " 회원님의 운동 프로그램 변경 >> ");

		System.out.println("\n<< 프로그램 전체 목록입니다. 원하시는 프로그램을 골라보세요 >>");
		System.out.println("[프로그램번호]     [프로그램이름]    [운동타입]     [소모칼로리]     [골격근량 증가량]    [스트레스 감소량]    [밸런스 증가량]");
		System.out.println("=================================================================================");
		List<Program> listAll = proDao.programList();

		listAll.forEach(pro -> System.out.println(pro));
		System.out.println("=================================================================================");

		System.out.println(" >> 변경하실 프로그램 번호를 입력해주세요");
		int selectedNumber = scanner.nextInt();

		List<BodyProfile> membersProfileList = bodyDao.findById(member.getMemberId());

		membersProfileList.get(0).setProgramId(selectedNumber); // 변경된 프로그램 번호 저장

		bodyDao.update(membersProfileList.get(0)); // db에 저장
	}

	// 2.나만의 운동 프로그램 생성
	private void createMyProgram(Member member) {

		System.out.println("\n << 나만의 운동 프로그램 등록 >>");

		Program newprogram = new Program();

		System.out.println("> 프로그램의 이름을 입력해주세요");
		String ProgramName = scanner.nextLine();
		newprogram.setProgramName(ProgramName);

		System.out.println("> 프로그램의 상세 정보를 입력해주세요");
		String ProgramInfo = scanner.nextLine();
		newprogram.setProgramInfo(ProgramInfo);

		proDao.addProgram(newprogram);

		Program program = proDao.findByProgramName(ProgramName);

		List<Exercise> exerList = new ArrayList<Exercise>();
		System.out.println("프로그램에 몇개의 운동을 넣으시겠습니까? ");
		int count = scanner.nextInt();

		System.out.println(count + " 개의 운동이 포함된 프로그램을 생성합니다. ");
		scanner.nextLine();

		System.out.println("\n > 이 프로그램에 넣고 싶은 운동들을 입력해주세요");
		int calory = 0;
		int muscle = 0;
		int balance = 0;
		int stress = 0;

		for (int i = 0; i < count; i++) {
			System.out.println((i + 1) + " 번째 운동 이름");
			String Exercise = scanner.nextLine();

			Exercise foundExercise = exerDao.findByName(Exercise);
			exerList.add(foundExercise);

			calory += foundExercise.getConsumedCalory();
			muscle += foundExercise.getGainedMuscle();
			balance += foundExercise.getGetBalance();
			stress += foundExercise.getLowerStress();
		}
		program.setTotalCalory(calory);
		program.setTotalMuscle(muscle);
		program.setTotalBalance(balance);
		program.setTotalStress(stress);

		proDao.insertProgram(program);

		int key = program.getProgramID();
		for (Exercise exer : exerList) {
			exer.setProgramID(key);
			exerDao.updateProgramID(exer);
		}

		System.out.println(" 새로운 운동 프로그램이 정상 등록 되었습니다.");
		System.out.println(" 이 운동프로그램을 내 운동 프로그램으로 설정 하시겠습니까? (y/n)");
		String answer = scanner.nextLine();
		if (!answer.equals("y")) {
			System.out.println("고객님이 만드신 운동은 DB에 저장하겠습니다. 등록을 고려해주세요");
			return;
		}

		List<BodyProfile> membersProfileList = bodyDao.findById(member.getMemberId());

		membersProfileList.get(0).setProgramId(key); // 만든 프로그램 번호 저장

		bodyDao.update(membersProfileList.get(0)); // DB에 등록

	}

	// 3.바디 프로필 관리
	private void manageBodyProfile(Member member) {
		String choice;

		subMenu_bodyProfile(member);
		choice = scanner.nextLine();

		switch (choice) {
		case "1":
			registerProfile(member);
			break;
		case "2":
			selectInbody(member);
			break;
		case "3":
			myFuture(member);
			break;
		case "0":
			System.out.println("상위 메뉴로 돌아갑니다");
			subMenu_manager(member);
		default:
			System.out.println("번호를 다시 입력해 주세요");
		}
	}

	// 3.바디 프로필 서브 메뉴
	private void subMenu_bodyProfile(Member member) {
		System.out.println("== " + member.getMemberName() + "님의 바디 프로필 관리 ==");
		System.out.println("1. 바디 프로필 등록");
		System.out.println("2. 현재 인바디 조회");
		System.out.println("3. 미래의 " + member.getMemberName() + "님의 모습");
		System.out.println("0. 상위 메뉴");
		System.out.print(">>선택 : ");
	}

	// 3.바디 프로필 등록
	private void registerProfile(Member member) {
//			int memberId;
//			Member member = managerDao.findById(memberId);
//			if(member == null) {	
//			}

		int memberId;
		int weight;
		int height;

		// body = bodyDao.findById(memberId);

	}

	// 3.인바디 조회
	private void selectInbody(Member member) {
	}

	// 3.미래의 나 (프로그램을 통해 얻을 수 있는 수치)
	private void myFuture(Member member) {
		System.out.println("==============[내 미래 모습 설계(My Future]===============");

		List<BodyProfile> profileList = bodyDao.findById(member.getMemberId());
		System.out.println(" << 현재까지 등록된 회원님의 바디 프로필 목록입니다. >> ");
		profileList.forEach(profile -> System.out.println(profile));
		// 현재 모습은 소현상 구현했던거... 넣자...
		BodyProfile currentBodyProfile = new BodyProfile(); // 소현상이 구한 현재 바디 프로필

		System.out.println("미래의 모습...드가자");
		System.out.println(" 회원님의 운동 주기를 입력해주세요(1주 운동 횟수)");
		int perweek = scanner.nextInt();
		System.out.println(" 현재 프로그램을 몇달간 진행 하시겠습니까 ? ");
		int period = scanner.nextInt();
		int count = perweek * period * 4; // 총운동횟수
		System.out.println(member.getMemberName() + " 회원님은 " + period + " 달 동안 " + count + " 번의 프로그램을 진행하게 됩니다.");

		Program specificMembersProgram = proDao.findByMemberID(member.getMemberId());

		double caloryResult = specificMembersProgram.getTotalCalory() * count;
		double muscleResult = specificMembersProgram.getTotalMuscle() * count;
		double balanceResult = specificMembersProgram.getTotalBalance() * count;
		double stressResult = specificMembersProgram.getTotalStress() * count;

		// 어찌 영향 줄것인지. 기간에 따른 사람의 기본 섭취 칼로리, 스트레스 증가치 계산
		// 7000칼로리당 1kg 감소
		currentBodyProfile.setSmm(currentBodyProfile.getSmm() + muscleResult);
		currentBodyProfile.setWeight((int) (currentBodyProfile.getWeight() - caloryResult / 7000));
		// set 하자.... currentCalory -caloryResult;
	}
}
