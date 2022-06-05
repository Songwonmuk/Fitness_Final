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
				break; // ȸ������
			case "2":
				manageManager();
				break; // �Ŵ�������
			case "3":
				managingExercise.ExerListStart();
				break; // �����
			case "4":
				managingProgram.StartProgram();
				break;
			case "0":
				System.out.println("���� �޴��� ���ư��ϴ�");
				return; // ���� �޴��� ���ư��ϴ�.
			default:
				System.out.println("�ٽ� �Է��� �ּ���");
				break;
			}
		}
	}

	private void managingMenu() {
		System.out.println("=====[������ ���α׷�]=====");
		System.out.println("1. ȸ������");
		System.out.println("2. �Ŵ��� ����");
		System.out.println("3. � ����");
		System.out.println("4. � ���α׷� ����");
		System.out.println("0. ���� �޴�");
		System.out.print(">>���� : ");
	}

//1.ȸ������	
	private void manageMember() {

		String choice;
		subMenu_member();
		choice = sc.nextLine();

		switch (choice) {
		case "1":
			registerMember();
			break; // 1.ȸ�� ���
		case "2":
			selectAllMember();
			break; // 1.��ü ȸ�� ��� ��ȸ
		case "3":
			selectOneMember();
			break; // 1.ȸ�� ã��
		case "4":
			deleteMember();
			break; // 1.ȸ�� Ż��
		case "5":
			updateMember();
			break; // 1.ȸ�� ����
		case "0":
			System.out.println("���� �޴��� ���ư��ϴ�"); // ManagerLoginUI()�� ���ư���;
			return;
		default:
			System.out.println("��ȣ�� �ٽ� �Է��� �ּ���");
		}
	}

	private void subMenu_member() {
		System.out.println("== Fitness ȸ�� ���� ==");
		System.out.println("1. ȸ�� ���");
		System.out.println("2. ��ü ȸ�� ��� ��ȸ");
		System.out.println("3. ȸ�� ã��");
		System.out.println("4. ȸ�� Ż��");
		System.out.println("5. ������ � ���α׷� ����");
		System.out.println("0. ���� �޴�");
		System.out.print(">>���� : ");
	}

	private void registerMember() {
		String memberPw; // ��� pw
		String memberName; // ��� �̸�
		String phone; // ��ȭ��ȣ
		String gender; // ���� (��, ��)
		String birth; // ����

		System.out.print("�н����� �Է� : ");
		memberPw = sc.nextLine();
		System.out.print("�̸� �Է� : ");
		memberName = sc.nextLine();
		System.out.print("��ȭ��ȣ �Է� : ");
		phone = sc.nextLine();
		System.out.print("���� �Է� (��/��) : ");
		gender = sc.nextLine();
		System.out.print("���� �Է�(ex)1990/01/01) : ");
		birth = sc.nextLine();

		Member member = new Member(memberPw, memberName, phone, gender, birth);

		int result = memberDao.join(member);
		System.out.printf("**%d���� ȸ���� ��ϵǾ����ϴ�.%n", result);
	}

	private void selectAllMember() {
		List<Member> list = memberDao.findAll();
		if (list == null) {
			System.out.println("��ϵ� ȸ���� �����ϴ�");
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
			System.out.println("��ϵ� ȸ���� �����ϴ�");
			System.out.println();
			return;
		} else {
			System.out.print("ȸ�� ���̵� �Է� : ");
			memberId = sc.nextInt();
			Member member = memberDao.findById(memberId);

			if (member == null) {
				System.out.println("��ġ�ϴ� ȸ���� �����ϴ�");
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
			System.out.println("��ϵ� ȸ���� �����ϴ�");
			System.out.println();
			return;
		} else {
			System.out.print("ȸ�� ���̵� �Է� : ");
			memberId = sc.nextInt();
			Member member = memberDao.findById(memberId);

			if (member == null) {
				System.out.println("��ġ�ϴ� ȸ���� �����ϴ�");
				System.out.println();
				return;
			} else {
				System.out.println(member);

				System.out.println("ȸ�� ������ �����Ͻðڽ��ϱ�?(y/n)");
				answer = sc.nextLine();
				if (answer.equals("n")) {
					System.out.println("���� ������ ����߽��ϴ�");
					System.out.println();
					return;
				} else {
					int result = memberDao.delete(memberId);
					System.out.printf("**%d���� ȸ���� �����Ǿ����ϴ�.%n", result);
				}
			}
		}
	}

//ȸ�� ���� ����
	private void updateMember() {
		int memberId;
		String phone;

		List<Member> list = memberDao.findAll();
		if (list == null) {
			System.out.println("��ϵ� ȸ���� �����ϴ�");
			System.out.println();
			return;
		} else {
			System.out.print("ȸ�� ���̵� �Է� : ");
			memberId = sc.nextInt();

			Member member = memberDao.findById(memberId);

			if (member == null) {
				System.out.println("��ġ�ϴ� ȸ���� �����ϴ�");
				System.out.println();
				return;
			} else {
				System.out.println(member);

				System.out.println("�޴��� ��ȣ ���� : ");
				phone = sc.nextLine();

				member.setPhone(phone);

				int result = memberDao.update(member);
				System.out.printf("**%d���� ȸ���� �����Ǿ����ϴ�.%n", result);
			}
		}
	}

//2.�Ŵ��� ����
	private void manageManager() {

		String choice;
		subMenu_manager();
		choice = sc.nextLine();

		switch (choice) {
		case "1":
			registerManager();
			break; // 1.ȸ�� ���
		case "2":
			selectAllManager();
			break; // 1.��ü ȸ�� ��� ��ȸ
		case "3":
			deleteManager();
			break; // 1.ȸ�� Ż��
		case "4":
			updateManager();
			break; // 1.ȸ�� ����
		case "0":
			System.out.println("���� �޴��� ���ư��ϴ�"); // ManagerLoginUI()�� ���ư���;
			return;
		default:
			System.out.println("��ȣ�� �ٽ� �Է��� �ּ���");
		}
	}

	private void subMenu_manager() {
		System.out.println("== Fitness ȸ�� ���� ==");
		System.out.println("1. �ű� �Ŵ��� ���");
		System.out.println("2. �Ŵ��� ��� ��ȸ");
		System.out.println("3. �Ŵ��� �ذ�");
		System.out.println("4. �Ŵ��� ���� ����");
		System.out.println("0. ���� �޴�");
		System.out.print(">>���� : ");
	}

	private void registerManager() {
		String managerName;
		int JobPeriod;
		String InfluenceLevel; // ��±Ⱓ�� ���� ������

		
		
	}

	private void selectAllManager() {
		List<Manager> list = managerDao.findAll();
		if (list == null) {
			System.out.println("��ϵ� ȸ���� �����ϴ�");
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
			System.out.println("��ϵ� �Ŵ����� �����ϴ�");
			System.out.println();
			return;
		} else {
			System.out.print("�Ŵ��� ���̵� �Է� : ");
			managerId = sc.nextInt();
			Manager manager = managerDao.findById(managerId);

			if (manager == null) {
				System.out.println("��ġ�ϴ� �Ŵ����� �����ϴ�");
				System.out.println();
				return;
			} else {
				System.out.println(manager);

				System.out.println("�Ŵ��� ������ �����Ͻðڽ��ϱ�?(y/n)");
				answer = sc.nextLine();
				if (answer.equals("n")) {
					System.out.println("���� ������ ����߽��ϴ�");
					System.out.println();
					return;
				} else {
					int result = managerDao.delete(managerId);
					System.out.printf("**%d���� �Ŵ����� �����Ǿ����ϴ�.%n", result);
				}
			}
		}
	}

	private void updateManager() {
		int managerId;
		int jobPeriod;
//		int improvement; //jobPeriod�� ���� �������� �Ŷ�� ���� ���⼭ ������ �ʿ� ���� ��

		List<Manager> list = managerDao.findAll();
		if (list == null) {
			System.out.println("��ϵ� �Ŵ����� �����ϴ�");
			System.out.println();
			return;
		} else {
			System.out.print("�Ŵ��� ���̵� �Է� : ");
			managerId = sc.nextInt();
			Manager manager = managerDao.findById(managerId);

			if (manager == null) {
				System.out.println("��ġ�ϴ� �Ŵ����� �����ϴ�");
				System.out.println();
				return;
			} else {
				System.out.println(manager);

				System.out.println("��� �Ⱓ : ");
				jobPeriod = sc.nextInt();

//				member.setMemberPw(memberPw);
				manager.setJobPeriod(jobPeriod);

				int result = managerDao.update(manager);
				System.out.printf("**%d���� �Ŵ����� �����Ǿ����ϴ�.%n", result);
			}
		}
	}
}