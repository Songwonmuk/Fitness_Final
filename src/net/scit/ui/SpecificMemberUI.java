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
		System.out.println("���̵� �Է� : ");
		int memberId = scanner.nextInt();
		System.out.println("��й�ȣ �Է� : ");
		String memberPw = scanner.nextLine();
		Member member = memberDao.findById(memberId);
//		Member pwVO = memberDao.findByPw(memberPw);

		// id�� ��ϵǾ� ���� ������ �α��� â �Ǵ� ȸ������ â���� �̵�, �Է��� ȸ�� ���̵�� ����� ��� ��ġ�ϸ� �α���
		if (member == null) {
			System.out.println("**��ϵ��� ���� ȸ���Դϴ�");
			System.out.print("**ȸ������ â���� �̵��Ͻðڽ��ϱ�?(y/n)");
			String answer = scanner.nextLine();

			if (answer.equals("n")) {
				System.out.println("**�α��� â���� ���ư��ϴ�");
				specificMember();
			} else {
				System.out.println("**ȸ������ â���� �̵��մϴ�");
				memberJoinIn();
			}
		}

		//
		else if (member.getMemberPw().equals(memberPw)) {
			System.out.println("== " + member.getMemberName() + "��, ȯ���մϴ�! ==");

			memberStart(member);

		}
	}

	private void memberJoinIn() {
		String memberPw; // ��� pw
		String memberName; // ��� �̸�
		String phone; // ��ȭ��ȣ
		String gender; // ���� (��, ��)
		String birth; // ����

		System.out.print("�н����� �Է� : ");
		memberPw = scanner.nextLine();
		System.out.print("�̸� �Է� : ");
		memberName = scanner.nextLine();
		System.out.print("��ȭ��ȣ �Է� : ");
		phone = scanner.nextLine();
		System.out.print("���� �Է� (��/��) : ");
		gender = scanner.nextLine();
		System.out.print("���� �Է�(ex)1990/01/01) : ");
		birth = scanner.nextLine();

		Member member = new Member(memberPw, memberName, phone, gender, birth);

		int result = memberDao.join(member);
		System.out.printf("**%d���� ȸ���� ��ϵǾ����ϴ�.%n", result);
	}

	public void memberStart(Member member) {
		String choice;

		while (true) {
			mainMenu();
			choice = scanner.nextLine();

			switch (choice) {
			case "1":
				manageManager(member);
				break; // 1. � �Ŵ��� ����
			case "2":
				manageProgram(member);
				break; // 2. � ���α׷� ����
			case "3":
				manageBodyProfile(member);
				break; // 3. �ٵ� ������ ����
			case "0":
				System.out.println("���� �޴��� ���ư��ϴ�.");
				return;
			default:
				System.out.println("�ٽ� �Է��� �ּ���");
				break;
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

	// 1. � �Ŵ��� ����
	private void manageManager(Member member) {
		String choice;
		subMenu_manager(member);
		choice = scanner.nextLine();

		switch (choice) {
		case "1":
			registerManager(member);
			break; // 1. ��� �Ŵ��� ���
		case "2":
			selectPresentManager(member);
			break; // 1. ���� ��� �Ŵ��� ��ȸ
		case "3":
			changeManager(member);
			break; // 1. ��� �Ŵ��� ����
		case "0":
			System.out.println("���� �޴��� ���ư��ϴ�");
			mainMenu(); // ���� �޴���
		default:
			System.out.println("�ٽ� �Է��� �ּ���");
			break;
		}
	}

	// 1. � �Ŵ��� ������ ���� �޴�
	private void subMenu_manager(Member member) {
		System.out.println("== " + member.getMemberName() + "���� �Ŵ��� ���� ==");
		System.out.println("1. ��� �Ŵ��� ���");
		System.out.println("2. ���� ��� �Ŵ��� ��ȸ");
		System.out.println("3. ��� �Ŵ��� ����");
		System.out.println("0. ���� �޴�");
		System.out.print(">>���� : ");
	}

	// 1. ��� �Ŵ��� ��� - (1)
	private void registerManager(Member member) {
		System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ����� ����մϴ� **");
		managerLevel(member);
	}

	// 1. ��� �Ŵ��� ��� - (1) influenceLevel ��ȸ
	private void managerLevel(Member member) {
		// ��� �Ŵ����� InfluenceLevel�� list�� �޾ƿ���
		List<Manager> influenceLevelList = managerDao.selectAllInfluenceLevel();
		int levelNum; // ���⵵ ���� �Է�
		int managerId;

		// influenceLevelList�� ���������
		if (influenceLevelList.isEmpty()) {
			System.out.println("��ϵ� �Ŵ����� �����ϴ�");
			return;
		} else {
			System.out.println("**����ϰ��� �ϴ� � �Ŵ����� ���⵵ ������ �Է��ϼ���(1~5)");
			subMenu_Level();
			levelNum = scanner.nextInt();

			List<Manager> pickedManager = managerDao.findByLevel(levelNum);

			for (Manager list : pickedManager) {
				System.out.println("�Ŵ��� ID : " + list.getManagerId() + "  �Ŵ����� : " + list.getManagerName() + "���⵵ : "
						+ list.getInfluenceLevel());
			}

			System.out.print("**��� �Ŵ����� ����ϰ� ���� �Ŵ��� ID�� �Է��ϼ��� : ");
			managerId = scanner.nextInt();
			Manager manager = managerDao.findById(managerId);
			if (manager == null) {
				System.out.println("��ϵǾ� ���� ���� �Ŵ��� ID�Դϴ�");
				managerLevel(member); // Q. �� ȭ������ ���ư��� �ɱ�?
			} else {
				// �Է��� �Ŵ����� ID�� �� ���α׷��� �Ŵ����� ��Ͻ�Ű��
				member.setManagerId(managerId);
				memberDao.registerMyManager(managerId); // update ���������� �Ǿ� ����
				System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ����� ���������� ��ϵǾ����ϴ�");
			}
		}
	}

	// ���÷�� ���� ����
	private void subMenu_Level() {
		System.out.println("���� 1");
		System.out.println("���� 2");
		System.out.println("���� 3");
		System.out.println("���� 4");
		System.out.println("���� 5");
		System.out.print(">>���� : ");
	}

	// 1. ���� ��� �Ŵ��� ��ȸ
	private void selectPresentManager(Member member) {
		String answer;

		System.out.println("**���� " + member.getMemberName() + "���� � �Ŵ����� �����Դϴ�**");
		// ȸ���� ���� � �Ŵ����� �̵�� �����̸�, �Ŵ��� ��� ���� ����
		if (member.getManagerId() == 0) {
			System.out.println("** " + member.getMemberName() + "���� ���� � �Ŵ����� �̵�� �����Դϴ�");
			System.out.println("** �Ŵ����� ����Ͻðڽ��ϱ�?(y/n)");
			answer = scanner.nextLine();

			if (answer.equals("n")) {
				System.out.println("** ���� ȭ������ ���ư��ϴ�");
				subMenu_manager(member);
			} else {
				registerManager(member); // �Ŵ��� ��� ȭ������
			}
		}
		// ��ϵ� �Ŵ����� �ִٸ�, ���
		else {
			System.out.println("**���� " + member.getMemberName() + "���� ��� �Ŵ����� ����**");
			System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ��� : " + member.getManagerId() + "�Դϴ�");
		}
	}

	// 1. ��� �Ŵ��� ����
	private void changeManager(Member member) {
		String answer;
		int manager;

		if (member.getManagerId() == 0) {
			System.out.println("** " + member.getMemberName() + "���� ���� � �Ŵ����� �̵�� �����Դϴ�");
			System.out.println("** �Ŵ����� ����Ͻðڽ��ϱ�?(y/n)");
			answer = scanner.nextLine();

			if (answer.equals("n")) {
				System.out.println("** ���� ȭ������ ���ư��ϴ�");
				subMenu_manager(member);
			}
			// ������ �Ŵ��� ���
			else {
				registerManager(member);
			}
		} else {
			int levelNum; // ���⵵ ���� �Է�
			int managerId;

			System.out.println("**���� " + member.getMemberName() + "���� ��� �Ŵ����� ����**");
			System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ��� : " + member.getManagerId() + "�Դϴ� %n");

			// ��� �Ŵ��� ����
			System.out.println("**����ϰ��� �ϴ� � �Ŵ����� ���⵵ ������ �Է��ϼ���(1~5)");
			subMenu_Level();
			levelNum = scanner.nextInt();
			List<Manager> pickedManager = managerDao.findByLevel(levelNum);

			for (Manager list : pickedManager) {
				System.out.println("�Ŵ��� ID : " + list.getManagerId() + "  �Ŵ����� : " + list.getManagerName() + "���⵵ : "
						+ list.getInfluenceLevel());
			}

			System.out.print("**��� �Ŵ����� ����ϰ� ���� �Ŵ��� ID�� �Է��ϼ��� : ");
			managerId = scanner.nextInt();
			Manager mana = managerDao.findById(managerId);
			if (mana == null) {
				System.out.println("��ϵǾ� ���� ���� �Ŵ��� ID�Դϴ�");
				managerLevel(member); // Q. �� ȭ������ ���ư��� �ɱ�?
			}
			// �Է��� �Ŵ����� ID�� �� ���α׷��� �Ŵ����� ��Ͻ�Ű��
			else {
				member.setManagerId(managerId);
				memberDao.registerMyManager(managerId); // update ���������� �Ǿ� ����
				System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ����� ���������� ����Ǿ����ϴ�");
			}
		}
	}

	// 2. � ���α׷� ����
	private void manageProgram(Member member) {
		String choice;
		subMenu_program(member);
		choice = scanner.nextLine();

		switch (choice) {
		case "1":
			selectPresentProgram(member);
			break; // 2.���� � ���α׷� ��ȸ
		case "2":
			changeProgram(member);
			break; // 2.� ���α׷� ����
		case "3":
			createMyProgram(member);
			break; // 2.������ � ���α׷� ����
		case "0":
			System.out.println("���� �޴��� ���ư��ϴ�");
			subMenu_manager(member);
		default:
			System.out.println("��ȣ�� �ٽ� �Է��� �ּ���");
			break;
		}
	}

	// 2. � ���α׷� ���� ����޴�
	private void subMenu_program(Member member) {
		System.out.println("== " + member.getMemberName() + "���� � ���α׷� ���� ==");
		System.out.println("1. ���� � ���α׷� ��ȸ");
		System.out.println("2. � ���α׷� ����");
		System.out.println("3. ������ � ���α׷� ����");
		System.out.println("0. ���� �޴�");
		System.out.print(">>���� : ");
	}

	// 2.���� � ���α׷� ��ȸ
	private void selectPresentProgram(Member member) {

		System.out.println(" << " + member.getMemberName() + " ȸ������ ���� � ���α׷� ��ȸ >> ");
		System.out.println("���� ȸ������ �������� � ���α׷��Դϴ�.");
		System.out.println("[���α׷���ȣ]     [���α׷��̸�]    [�Ÿ��]     [�Ҹ�Į�θ�]     [��ݱٷ� ������]    [��Ʈ���� ���ҷ�]    [�뷱�� ������]");
		System.out.println("=================================================================================");
		Program membersProgram = proDao.findByMemberID(member.getMemberId());
		System.out.println(membersProgram);
		System.out.println("=================================================================================");

	}

	// 2.� ���α׷� ����
	private void changeProgram(Member member) {

		System.out.println(" << " + member.getMemberName() + " ȸ������ � ���α׷� ���� >> ");

		System.out.println("\n<< ���α׷� ��ü ����Դϴ�. ���Ͻô� ���α׷��� ��󺸼��� >>");
		System.out.println("[���α׷���ȣ]     [���α׷��̸�]    [�Ÿ��]     [�Ҹ�Į�θ�]     [��ݱٷ� ������]    [��Ʈ���� ���ҷ�]    [�뷱�� ������]");
		System.out.println("=================================================================================");
		List<Program> listAll = proDao.programList();

		listAll.forEach(pro -> System.out.println(pro));
		System.out.println("=================================================================================");

		System.out.println(" >> �����Ͻ� ���α׷� ��ȣ�� �Է����ּ���");
		int selectedNumber = scanner.nextInt();

		List<BodyProfile> membersProfileList = bodyDao.findById(member.getMemberId());

		membersProfileList.get(0).setProgramId(selectedNumber); // ����� ���α׷� ��ȣ ����

		bodyDao.update(membersProfileList.get(0)); // db�� ����
	}

	// 2.������ � ���α׷� ����
	private void createMyProgram(Member member) {

		System.out.println("\n << ������ � ���α׷� ��� >>");

		Program newprogram = new Program();

		System.out.println("> ���α׷��� �̸��� �Է����ּ���");
		String ProgramName = scanner.nextLine();
		newprogram.setProgramName(ProgramName);

		System.out.println("> ���α׷��� �� ������ �Է����ּ���");
		String ProgramInfo = scanner.nextLine();
		newprogram.setProgramInfo(ProgramInfo);

		proDao.addProgram(newprogram);

		Program program = proDao.findByProgramName(ProgramName);

		List<Exercise> exerList = new ArrayList<Exercise>();
		System.out.println("���α׷��� ��� ��� �����ðڽ��ϱ�? ");
		int count = scanner.nextInt();

		System.out.println(count + " ���� ��� ���Ե� ���α׷��� �����մϴ�. ");
		scanner.nextLine();

		System.out.println("\n > �� ���α׷��� �ְ� ���� ����� �Է����ּ���");
		int calory = 0;
		int muscle = 0;
		int balance = 0;
		int stress = 0;

		for (int i = 0; i < count; i++) {
			System.out.println((i + 1) + " ��° � �̸�");
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

		System.out.println(" ���ο� � ���α׷��� ���� ��� �Ǿ����ϴ�.");
		System.out.println(" �� ����α׷��� �� � ���α׷����� ���� �Ͻðڽ��ϱ�? (y/n)");
		String answer = scanner.nextLine();
		if (!answer.equals("y")) {
			System.out.println("������ ����� ��� DB�� �����ϰڽ��ϴ�. ����� ������ּ���");
			return;
		}

		List<BodyProfile> membersProfileList = bodyDao.findById(member.getMemberId());

		membersProfileList.get(0).setProgramId(key); // ���� ���α׷� ��ȣ ����

		bodyDao.update(membersProfileList.get(0)); // DB�� ���

	}

	// 3.�ٵ� ������ ����
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
			System.out.println("���� �޴��� ���ư��ϴ�");
			subMenu_manager(member);
		default:
			System.out.println("��ȣ�� �ٽ� �Է��� �ּ���");
		}
	}

	// 3.�ٵ� ������ ���� �޴�
	private void subMenu_bodyProfile(Member member) {
		System.out.println("== " + member.getMemberName() + "���� �ٵ� ������ ���� ==");
		System.out.println("1. �ٵ� ������ ���");
		System.out.println("2. ���� �ιٵ� ��ȸ");
		System.out.println("3. �̷��� " + member.getMemberName() + "���� ���");
		System.out.println("0. ���� �޴�");
		System.out.print(">>���� : ");
	}

	// 3.�ٵ� ������ ���
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

	// 3.�ιٵ� ��ȸ
	private void selectInbody(Member member) {
	}

	// 3.�̷��� �� (���α׷��� ���� ���� �� �ִ� ��ġ)
	private void myFuture(Member member) {
		System.out.println("==============[�� �̷� ��� ����(My Future]===============");

		List<BodyProfile> profileList = bodyDao.findById(member.getMemberId());
		System.out.println(" << ������� ��ϵ� ȸ������ �ٵ� ������ ����Դϴ�. >> ");
		profileList.forEach(profile -> System.out.println(profile));
		// ���� ����� ������ �����ߴ���... ����...
		BodyProfile currentBodyProfile = new BodyProfile(); // �������� ���� ���� �ٵ� ������

		System.out.println("�̷��� ���...�尡��");
		System.out.println(" ȸ������ � �ֱ⸦ �Է����ּ���(1�� � Ƚ��)");
		int perweek = scanner.nextInt();
		System.out.println(" ���� ���α׷��� ��ް� ���� �Ͻðڽ��ϱ� ? ");
		int period = scanner.nextInt();
		int count = perweek * period * 4; // �ѿȽ��
		System.out.println(member.getMemberName() + " ȸ������ " + period + " �� ���� " + count + " ���� ���α׷��� �����ϰ� �˴ϴ�.");

		Program specificMembersProgram = proDao.findByMemberID(member.getMemberId());

		double caloryResult = specificMembersProgram.getTotalCalory() * count;
		double muscleResult = specificMembersProgram.getTotalMuscle() * count;
		double balanceResult = specificMembersProgram.getTotalBalance() * count;
		double stressResult = specificMembersProgram.getTotalStress() * count;

		// ���� ���� �ٰ�����. �Ⱓ�� ���� ����� �⺻ ���� Į�θ�, ��Ʈ���� ����ġ ���
		// 7000Į�θ��� 1kg ����
		currentBodyProfile.setSmm(currentBodyProfile.getSmm() + muscleResult);
		currentBodyProfile.setWeight((int) (currentBodyProfile.getWeight() - caloryResult / 7000));
		// set ����.... currentCalory -caloryResult;
	}
}
