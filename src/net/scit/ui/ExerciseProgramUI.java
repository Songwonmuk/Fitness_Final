package net.scit.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.scit.dao.ExerciseDAO;
import net.scit.dao.ProgramDAO;
import net.scit.vo.Exercise;
import net.scit.vo.Program;

public class ExerciseProgramUI {
	private Scanner scanner = new Scanner(System.in);
	private ExerciseDAO edao = new ExerciseDAO();
	private ProgramDAO pdao = new ProgramDAO();
	
	public void StartProgram() {
		mainMenu();
		String choice = scanner.nextLine();

		switch (choice) {
		case "1":
			list();
			break;
		case "2":
			add();
			break;
		case "3":
			delete();
			break;
		case "4":
			inform();
			break;
		case "0":
			System.out.println("** 이전 메뉴로 돌아갑니다.");
			return;
		default:
			System.out.println("err) 메뉴를 다시 선택해 주세요");
		}
		// scanner.nextLine(); // 버퍼 비우기
	}

	
	private void mainMenu() {
		System.out.println("===== [운동 프로그램 관리] =====");
		System.out.println("       1) 등록된 프로그램 전체 조회");
		System.out.println("       2) 운동 프로그램 등록");
		System.out.println("       3) 등록된 운동 프로그램 삭제 하기");
		System.out.println("       4) 운동 프로그램 상세 정보");
		System.out.println("       0) 이전 메뉴로 돌아갑니다.");
		System.out.println("============================");
		System.out.print("          선택> ");

	}
	
	private void list() {
		System.out.println("\n<< 등록된 프로그램 전체 조회 >>");
		System.out.println("[프로그램번호]     [프로그램이름]    [운동타입]     [소모칼로리]     [골격근량 증가량]    [스트레스 감소량]    [밸런스 증가량]");
		System.out.println("=================================================================================");
		List<Program> listAll = pdao.programList();
		
		listAll.forEach(pro -> System.out.println(pro));
		System.out.println("=================================================================================");
		
		int total = pdao.totalPrograms();
		System.out.println("현재 등록된 운동은 총 " + total + " 개입니다.");
	}

	private void add() {
		System.out.println("\n << 새로운 운동 프로그램 등록 >>");
		
		Program newprogram = new Program();
		
		System.out.println("> 프로그램의 이름을 입력해주세요");
		String ProgramName = scanner.nextLine();
		newprogram.setProgramName(ProgramName);
		
		System.out.println("> 프로그램의 상세 정보를 입력해주세요");
		String ProgramInfo = scanner.nextLine();
		newprogram.setProgramInfo(ProgramInfo);
		
		pdao.addProgram(newprogram);
		
		
		Program program = pdao.findByProgramName(ProgramName);
		
		
		
		List<Exercise> exerList = new ArrayList<Exercise>();
		System.out.println("프로그램에 몇개의 운동을 넣으시겠습니까? ");
		int count = scanner.nextInt();
		
		System.out.println(count+" 개의 운동이 포함된 프로그램을 생성합니다. ");
		scanner.nextLine();
		
		System.out.println("\n > 이 프로그램에 넣고 싶은 운동들을 입력해주세요");
		int calory = 0;
		int muscle = 0;
		int balance = 0;
		int stress =0;		
		
		for(int i=0; i<count; i++) {
			System.out.println((i+1) + " 번째 운동 이름");
			String Exercise = scanner.nextLine();
			
			Exercise foundExercise = edao.findByName(Exercise);
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
		
		pdao.insertProgram(program);		
		
		int key = program.getProgramID();
		for(Exercise exer : exerList) {
			exer.setProgramID(key);
			edao.updateProgramID(exer);
		}
		
	
				
		System.out.println("프로그램이 정상 등록 되었습니다.");
		
	}
	

	private void delete() {
		System.out.println("\n << 등록된 운동 프로그램 삭제 하기 >>");
		
		System.out.println("> 삭제할 프로그램의 번호를 입력하세요");
		int ProgramID = scanner.nextInt();
		
		Program foundProgram = pdao.findByProgramID(ProgramID);
		
		System.out.println(" > 삭제할 프로그램의 정보");
		System.out.println(foundProgram);
		scanner.nextLine();
		System.out.println("정말로 삭제 하시겠습니까? (y/n)");
		String answer = scanner.nextLine();
		if(!answer.equals("y")) {
			System.out.println("삭제 작업을 취소합니다. ");
			return;
		}
		//자식클래스의 programID를 먼저 바꿔줘야 한다.
		List<Exercise> list = edao.findByProgramID(ProgramID);
		
		for(Exercise exer : list) {
			edao.whenDeleteIdUpdate(exer.getExerNum());
		}			
//		
//		pdao.deleteProgram(ProgramID);
//		
		System.out.println("***삭제 작업이 완료 되었습니다.");
		
	}

	private void inform() {
		System.out.println("<< 운동 프로그램 상세 정보 >>");
		
		System.out.println("상세 정보를 확인할 프로그램 번호를 입력하세요 ");
		int ProgramID = scanner.nextInt();
		
		Program foundProgram = pdao.findByProgramID(ProgramID);
		
		System.out.println("<< 조회 하신 프로그램의 Information >>");
		System.out.println(foundProgram.getProgramInfo());
		
		System.out.println("=======================[ "+foundProgram.getProgramName()+" 에 포함된 운동들]============================");
		System.out.println("[운동번호]     [운동이름]    [운동타입]    [운동시간(분)] "
				+ "[소모칼로리] [골격근량 증가량] [스트레스 감소량] [밸런스 증가량]");
		System.out.println("=================================================================================");
					List<Exercise> list = edao.findByProgramID(ProgramID);
					list.forEach(exer->System.out.println(exer));
		System.out.println("=================================================================================");
	}

}
