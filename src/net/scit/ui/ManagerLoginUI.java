package net.scit.ui;

import java.util.List;
import java.util.Scanner;

import net.scit.dao.MemberDAO;
import net.scit.dao.ManagerDAO;
import net.scit.vo.Manager;
import net.scit.vo.Member;

public class ManagerLoginUI {
	ExerciseListUI managingExercise = new ExerciseListUI();
	ExerciseProgramUI managingProgram = new ExerciseProgramUI();
	MemberDAO memberDao = new MemberDAO();
	ManagerDAO managerDao = new ManagerDAO();
	LoginUI loginUI = new LoginUI();
	Scanner sc = new Scanner(System.in);

	public void managerManagementStart() {

		String choice;

		while (true) {
			managingMenu();
			choice = sc.nextLine();

			switch (choice) {
			case "1":
				manageMember();
				break; // 회원관리
			case "2":
				manageManager();
				break; // 매니저관리
			case "3":
				managingExercise.ExerListStart();
				break; // 운동관리
			case "4":
				managingProgram.StartProgram();
				break;
			case "0":
				System.out.println("상위 메뉴로 돌아갑니다");
				return; // 상위 메뉴로 돌아갑니다.
			default:
				System.out.println("다시 입력해 주세요");
				break;
			}
		}
	}

	private void managingMenu() {
		System.out.println("=====[관리자 프로그램]=====");
		System.out.println("1. 회원관리");
		System.out.println("2. 매니저 관리");
		System.out.println("3. 운동 관리");
		System.out.println("4. 운동 프로그램 관리");
		System.out.println("0. 상위 메뉴");
		System.out.print(">>선택 : ");
	}

//1.회원관리	
	private void manageMember() {

		String choice;
		subMenu_member();
		choice = sc.nextLine();

		switch (choice) {
		case "1":
			registerMember();
			break; // 1.회원 등록
		case "2":
			selectAllMember();
			break; // 1.전체 회원 목록 조회
		case "3":
			selectOneMember();
			break; // 1.회원 찾기
		case "4":
			deleteMember();
			break; // 1.회원 탈퇴
		case "5":
			updateMember();
			break; // 1.회원 수정
		case "0":
			System.out.println("상위 메뉴로 돌아갑니다"); // ManagerLoginUI()로 돌아가기;
			return;
		default:
			System.out.println("번호를 다시 입력해 주세요");
		}
	}

	private void subMenu_member() {
		System.out.println("== Fitness 회원 관리 ==");
		System.out.println("1. 회원 등록");
		System.out.println("2. 전체 회원 목록 조회");
		System.out.println("3. 회원 찾기");
		System.out.println("4. 회원 탈퇴");
		System.out.println("5. 나만의 운동 프로그램 생성");
		System.out.println("0. 상위 메뉴");
		System.out.print(">>선택 : ");
	}

	private void registerMember() {
		String memberPw; // 멤버 pw
		String memberName; // 멤버 이름
		String phone; // 전화번호
		String gender; // 성별 (남, 여)
		String birth; // 생일

		System.out.print("패스워드 입력 : ");
		memberPw = sc.nextLine();
		System.out.print("이름 입력 : ");
		memberName = sc.nextLine();
		System.out.print("전화번호 입력 : ");
		phone = sc.nextLine();
		System.out.print("성별 입력 (남/여) : ");
		gender = sc.nextLine();
		System.out.print("생일 입력(ex)1990/01/01) : ");
		birth = sc.nextLine();

		Member member = new Member(memberPw, memberName, phone, gender, birth);

		int result = memberDao.join(member);
		System.out.printf("**%d명의 회원이 등록되었습니다.%n", result);
	}

	private void selectAllMember() {
		List<Member> list = memberDao.findAll();
		if (list == null) {
			System.out.println("등록된 회원이 없습니다");
			System.out.println();
			return;
		} else {
			System.out.println(list);
		}
	}

	private void selectOneMember() {
		int memberId;

		List<Member> list = memberDao.findAll();
		if (list == null) {
			System.out.println("등록된 회원이 없습니다");
			System.out.println();
			return;
		} else {
			System.out.print("회원 아이디 입력 : ");
			memberId = sc.nextInt();
			Member member = memberDao.findById(memberId);

			if (member == null) {
				System.out.println("일치하는 회원이 없습니다");
				System.out.println();
				return;
			} else {
				System.out.println(member);
			}
		}
	}

	private void deleteMember() {
		int memberId;
		String answer;

		List<Member> list = memberDao.findAll();
		if (list == null) {
			System.out.println("등록된 회원이 없습니다");
			System.out.println();
			return;
		} else {
			System.out.print("회원 아이디 입력 : ");
			memberId = sc.nextInt();
			Member member = memberDao.findById(memberId);

			if (member == null) {
				System.out.println("일치하는 회원이 없습니다");
				System.out.println();
				return;
			} else {
				System.out.println(member);

				System.out.println("회원 정보를 삭제하시겠습니까?(y/n)");
				answer = sc.nextLine();
				if (answer.equals("n")) {
					System.out.println("정보 삭제를 취소했습니다");
					System.out.println();
					return;
				} else {
					int result = memberDao.delete(memberId);
					System.out.printf("**%d명의 회원이 삭제되었습니다.%n", result);
				}
			}
		}
	}

//회원 정보 수정
	private void updateMember() {
		int memberId;
		String phone;

		List<Member> list = memberDao.findAll();
		if (list == null) {
			System.out.println("등록된 회원이 없습니다");
			System.out.println();
			return;
		} else {
			System.out.print("회원 아이디 입력 : ");
			memberId = sc.nextInt();

			Member member = memberDao.findById(memberId);

			if (member == null) {
				System.out.println("일치하는 회원이 없습니다");
				System.out.println();
				return;
			} else {
				System.out.println(member);

				System.out.println("휴대폰 번호 변경 : ");
				phone = sc.nextLine();

				member.setPhone(phone);

				int result = memberDao.update(member);
				System.out.printf("**%d명의 회원이 수정되었습니다.%n", result);
			}
		}
	}

//2.매니저 관리
	private void manageManager() {

		String choice;
		subMenu_manager();
		choice = sc.nextLine();

		switch (choice) {
		case "1":
			registerManager();
			break; // 1.회원 등록
		case "2":
			selectAllManager();
			break; // 1.전체 회원 목록 조회
		case "3":
			deleteManager();
			break; // 1.회원 탈퇴
		case "4":
			updateManager();
			break; // 1.회원 수정
		case "0":
			System.out.println("상위 메뉴로 돌아갑니다"); // ManagerLoginUI()로 돌아가기;
			return;
		default:
			System.out.println("번호를 다시 입력해 주세요");
		}
	}

	private void subMenu_manager() {
		System.out.println("== Fitness 회원 관리 ==");
		System.out.println("1. 신규 매니저 등록");
		System.out.println("2. 매니저 목록 조회");
		System.out.println("3. 매니저 해고");
		System.out.println("4. 매니저 정보 수정");
		System.out.println("0. 상위 메뉴");
		System.out.print(">>선택 : ");
	}

	private void registerManager() {
		String managerName;
		int JobPeriod;
		String InfluenceLevel; // 경력기간에 따라 나눠짐

		
		
	}

	private void selectAllManager() {
		List<Manager> list = managerDao.findAll();
		if (list == null) {
			System.out.println("등록된 회원이 없습니다");
			System.out.println();
			return;
		} else {
			System.out.println(list);
		}
	}

	private void deleteManager() {
		int managerId;
		String answer;

		List<Manager> list = managerDao.findAll();
		if (list == null) {
			System.out.println("등록된 매니저가 없습니다");
			System.out.println();
			return;
		} else {
			System.out.print("매니저 아이디 입력 : ");
			managerId = sc.nextInt();
			Manager manager = managerDao.findById(managerId);

			if (manager == null) {
				System.out.println("일치하는 매니저가 없습니다");
				System.out.println();
				return;
			} else {
				System.out.println(manager);

				System.out.println("매니저 정보를 삭제하시겠습니까?(y/n)");
				answer = sc.nextLine();
				if (answer.equals("n")) {
					System.out.println("정보 삭제를 취소했습니다");
					System.out.println();
					return;
				} else {
					int result = managerDao.delete(managerId);
					System.out.printf("**%d명의 매니저가 삭제되었습니다.%n", result);
				}
			}
		}
	}

	private void updateManager() {
		int managerId;
		int jobPeriod;
//		int improvement; //jobPeriod에 의해 고쳐지는 거라면 딱히 여기서 수정할 필요 없을 듯

		List<Manager> list = managerDao.findAll();
		if (list == null) {
			System.out.println("등록된 매니저가 없습니다");
			System.out.println();
			return;
		} else {
			System.out.print("매니저 아이디 입력 : ");
			managerId = sc.nextInt();
			Manager manager = managerDao.findById(managerId);

			if (manager == null) {
				System.out.println("일치하는 매니저가 없습니다");
				System.out.println();
				return;
			} else {
				System.out.println(manager);

				System.out.println("경력 기간 : ");
				jobPeriod = sc.nextInt();

//				member.setMemberPw(memberPw);
				manager.setJobPeriod(jobPeriod);

				int result = managerDao.update(manager);
				System.out.printf("**%d명의 매니저가 수정되었습니다.%n", result);
			}
		}
	}
}