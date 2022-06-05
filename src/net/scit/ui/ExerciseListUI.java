package net.scit.ui;

import java.util.List;
import java.util.Scanner;

import net.scit.dao.ExerciseDAO;
import net.scit.vo.ExerType;
import net.scit.vo.Exercise;

public class ExerciseListUI {
	private Scanner scanner = new Scanner(System.in);
	private ExerciseDAO edao = new ExerciseDAO();

	public void ExerListStart() {
		String choice;
		while (true) {
			mainMenu();
			choice = scanner.nextLine();

			switch (choice) {
			case "1":
				list();
				break;
			case "2":
				add();
				break;
			case "3":
				update();
				break;
			case "4":
				delete();
				break;
			case "0":
				System.out.println("** ���� �޴��� ���ư��ϴ�.");
				return;
			default:
				System.out.println("err) �޴��� �ٽ� ������ �ּ���");
			}
		}
	}

	private void mainMenu() {
		System.out.println("===== [� ��� ����] =====");
		System.out.println("       1) ��ϵ� � ��ü ��ȸ");
		System.out.println("       2) ���ο� � ��� �߰�");
		System.out.println("       3) ���� � ��� �����ϱ�");
		System.out.println("       4) ��ϵ� � ���� �ϱ�");
		System.out.println("       0) ���� �޴��� ���ư��ϴ�.");
		System.out.println("============================");
		System.out.print("          ����> ");
	}

	private void list() {
		System.out.println("\n<< ��ϵ� � ��ü ��ȸ >>");
		System.out.println("[���ȣ]     [��̸�]    [�Ÿ��]    [��ð�(��)] " + "[�Ҹ�Į�θ�] [��ݱٷ� ������] [��Ʈ���� ���ҷ�] [�뷱�� ������]");
		System.out.println("=================================================================================");
		List<Exercise> listAll = edao.ExerciseList();
		listAll.forEach(exer -> System.out.println(exer));
		System.out.println("=================================================================================");
		int total = edao.totalExercises();
		System.out.println("���� ��ϵ� ��� �� " + total + " ���Դϴ�.");

	}

	private void add() {
		System.out.println("\n<< ���ο� � ��� �߰� >>");

		System.out.print("> � �̸� : ");
		String ExerName = scanner.nextLine();
		if (ExerName.trim().equals("")) {
			System.out.println("err) �̸��� �Է����ּ���");
			return;
		}
		Exercise exercise = edao.findByName(ExerName);

		if (exercise != null) {
			System.out.println("�̹� ��ϵ� ��Դϴ�. �ٸ� ��� �߰��ϼ���");
			return;
		}
		ExerType exerType = inputType();

		System.out.print("> ��ð� : ");
		int ExerTime = scanner.nextInt();

		System.out.println("> ��� �Ҹ� Į�θ� : ");
		int ConsumedCalory = scanner.nextInt();

		System.out.println("> ��ݱٷ� ���� ���ġ : ");
		int AddMuscle = scanner.nextInt();

		System.out.println("> ��Ʈ���� ���� ���� ���ġ : ");
		int LowerStress = scanner.nextInt();

		System.out.println("> �뷱�� �� �ھ� ��� ���ġ : ");
		int GetBalance = scanner.nextInt();
		scanner.nextLine();

		Exercise addedExercise = new Exercise(ExerName, exerType, ExerTime, ConsumedCalory, AddMuscle, LowerStress,
				GetBalance);
		edao.addExercise(addedExercise);

		System.out.println("** ���ο� ��� ��ϵǾ����ϴ�.\n");

	}

	private ExerType inputType() {
		// ��������� �߸� �Է��ϸ� ����� �Է��� ������ �ݺ������� �Է¹޴´�.
		while (true) {
			System.out.println("** ��� Ÿ���� �������ּ���");
			System.out.print("1) �ٷ�  2) ���̾�Ʈ  3) �뷱��  4) ��������\n");
			int TypeNumber = scanner.nextInt();
			try {
				return ExerType.of(TypeNumber);
			} catch (IllegalArgumentException e) {
				System.out.println("����߿��� �����ϼ���");
			}
		}
	}

	private void update() {
		System.out.println("\n<< ���� � ��� �����ϱ� >>");

		System.out.println("ã������ ��� �̸��� �Է��ϼ���");
		String ExerName = scanner.nextLine();

		Exercise foundExercise = edao.findByName(ExerName);
		if (foundExercise == null) {
			System.out.println("�Է��Ͻ� ��� ã���� �����ϴ�.");
			return;
		}
		ExerType exerType = inputType();
		foundExercise.setExerType(exerType);

		System.out.print("> ��ð� : ");
		int ExerTime = scanner.nextInt();
		foundExercise.setExerTime(ExerTime);

		System.out.println("> ��� �Ҹ� Į�θ� : ");
		int ConsumedCalory = scanner.nextInt();
		foundExercise.setConsumedCalory(ConsumedCalory);

		System.out.println("> ��ݱٷ� ���� ���ġ : ");
		int AddMuscle = scanner.nextInt();
		foundExercise.setGainedMuscle(AddMuscle);

		System.out.println("> ��Ʈ���� ���� ���� ���ġ : ");
		int LowerStress = scanner.nextInt();
		foundExercise.setLowerStress(LowerStress);

		System.out.println("> �뷱�� �� �ھ� ��� ���ġ : ");
		int GetBalance = scanner.nextInt();
		foundExercise.setGetBalance(GetBalance);

		edao.updateExercise(foundExercise);

		System.out.println("������ �Ϸ�Ǿ����ϴ�.");

	}

	private void delete() {
		System.out.println("\n<< ��ϵ� � ���� �ϱ� >>");

		System.out.println("�����Ͻ� ��� �̸��� �Է��ϼ���");
		String ExerName = scanner.nextLine();

		Exercise foundExercise = edao.findByName(ExerName);

		if (foundExercise == null) {
			System.out.println("�Է��Ͻ� ��� ã���� �����ϴ�.");
			return;
		}

		edao.deleteExercise(foundExercise);

		System.out.println("���� �۾��� �Ϸ� �Ǿ����ϴ�.");

	}

}
