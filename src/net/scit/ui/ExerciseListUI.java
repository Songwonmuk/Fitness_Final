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
				System.out.println("** 이전 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("err) 메뉴를 다시 선택해 주세요");
			}
		}
	}

	private void mainMenu() {
		System.out.println("===== [운동 목록 관리] =====");
		System.out.println("       1) 등록된 운동 전체 조회");
		System.out.println("       2) 새로운 운동 목록 추가");
		System.out.println("       3) 기존 운동 목록 수정하기");
		System.out.println("       4) 등록된 운동 제거 하기");
		System.out.println("       0) 이전 메뉴로 돌아갑니다.");
		System.out.println("============================");
		System.out.print("          선택> ");
	}

	private void list() {
		System.out.println("\n<< 등록된 운동 전체 조회 >>");
		System.out.println("[운동번호]     [운동이름]    [운동타입]    [운동시간(분)] " + "[소모칼로리] [골격근량 증가량] [스트레스 감소량] [밸런스 증가량]");
		System.out.println("=================================================================================");
		List<Exercise> listAll = edao.ExerciseList();
		listAll.forEach(exer -> System.out.println(exer));
		System.out.println("=================================================================================");
		int total = edao.totalExercises();
		System.out.println("현재 등록된 운동은 총 " + total + " 개입니다.");

	}

	private void add() {
		System.out.println("\n<< 새로운 운동 목록 추가 >>");

		System.out.print("> 운동 이름 : ");
		String ExerName = scanner.nextLine();
		if (ExerName.trim().equals("")) {
			System.out.println("err) 이름을 입력해주세요");
			return;
		}
		Exercise exercise = edao.findByName(ExerName);

		if (exercise != null) {
			System.out.println("이미 등록된 운동입니다. 다른 운동을 추가하세요");
			return;
		}
		ExerType exerType = inputType();

		System.out.print("> 운동시간 : ");
		int ExerTime = scanner.nextInt();

		System.out.println("> 운동시 소모 칼로리 : ");
		int ConsumedCalory = scanner.nextInt();

		System.out.println("> 골격근량 증가 기대치 : ");
		int AddMuscle = scanner.nextInt();

		System.out.println("> 스트레스 지수 감소 기대치 : ");
		int LowerStress = scanner.nextInt();

		System.out.println("> 밸런스 및 코어 기능 향상치 : ");
		int GetBalance = scanner.nextInt();
		scanner.nextLine();

		Exercise addedExercise = new Exercise(ExerName, exerType, ExerTime, ConsumedCalory, AddMuscle, LowerStress,
				GetBalance);
		edao.addExercise(addedExercise);

		System.out.println("** 새로운 운동이 등록되었습니다.\n");

	}

	private ExerType inputType() {
		// 진료과목을 잘못 입력하면 제대로 입력할 때까지 반복적으로 입력받는다.
		while (true) {
			System.out.println("** 운동의 타입을 설정해주세요");
			System.out.print("1) 근력  2) 다이어트  3) 밸런스  4) 마음안정\n");
			int TypeNumber = scanner.nextInt();
			try {
				return ExerType.of(TypeNumber);
			} catch (IllegalArgumentException e) {
				System.out.println("목록중에서 선택하세요");
			}
		}
	}

	private void update() {
		System.out.println("\n<< 기존 운동 목록 수정하기 >>");

		System.out.println("찾으려는 운동의 이름을 입력하세요");
		String ExerName = scanner.nextLine();

		Exercise foundExercise = edao.findByName(ExerName);
		if (foundExercise == null) {
			System.out.println("입력하신 운동을 찾을수 없습니다.");
			return;
		}
		ExerType exerType = inputType();
		foundExercise.setExerType(exerType);

		System.out.print("> 운동시간 : ");
		int ExerTime = scanner.nextInt();
		foundExercise.setExerTime(ExerTime);

		System.out.println("> 운동시 소모 칼로리 : ");
		int ConsumedCalory = scanner.nextInt();
		foundExercise.setConsumedCalory(ConsumedCalory);

		System.out.println("> 골격근량 증가 기대치 : ");
		int AddMuscle = scanner.nextInt();
		foundExercise.setGainedMuscle(AddMuscle);

		System.out.println("> 스트레스 지수 감소 기대치 : ");
		int LowerStress = scanner.nextInt();
		foundExercise.setLowerStress(LowerStress);

		System.out.println("> 밸런스 및 코어 기능 향상치 : ");
		int GetBalance = scanner.nextInt();
		foundExercise.setGetBalance(GetBalance);

		edao.updateExercise(foundExercise);

		System.out.println("수정이 완료되었습니다.");

	}

	private void delete() {
		System.out.println("\n<< 등록된 운동 제거 하기 >>");

		System.out.println("제거하실 운동의 이름을 입력하세요");
		String ExerName = scanner.nextLine();

		Exercise foundExercise = edao.findByName(ExerName);

		if (foundExercise == null) {
			System.out.println("입력하신 운동을 찾을수 없습니다.");
			return;
		}

		edao.deleteExercise(foundExercise);

		System.out.println("삭제 작업이 완료 되었습니다.");

	}

}
