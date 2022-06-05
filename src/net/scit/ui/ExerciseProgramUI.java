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
			System.out.println("** ���� �޴��� ���ư��ϴ�.");
			return;
		default:
			System.out.println("err) �޴��� �ٽ� ������ �ּ���");
		}
		// scanner.nextLine(); // ���� ����
	}

	
	private void mainMenu() {
		System.out.println("===== [� ���α׷� ����] =====");
		System.out.println("       1) ��ϵ� ���α׷� ��ü ��ȸ");
		System.out.println("       2) � ���α׷� ���");
		System.out.println("       3) ��ϵ� � ���α׷� ���� �ϱ�");
		System.out.println("       4) � ���α׷� �� ����");
		System.out.println("       0) ���� �޴��� ���ư��ϴ�.");
		System.out.println("============================");
		System.out.print("          ����> ");

	}
	
	private void list() {
		System.out.println("\n<< ��ϵ� ���α׷� ��ü ��ȸ >>");
		System.out.println("[���α׷���ȣ]     [���α׷��̸�]    [�Ÿ��]     [�Ҹ�Į�θ�]     [��ݱٷ� ������]    [��Ʈ���� ���ҷ�]    [�뷱�� ������]");
		System.out.println("=================================================================================");
		List<Program> listAll = pdao.programList();
		
		listAll.forEach(pro -> System.out.println(pro));
		System.out.println("=================================================================================");
		
		int total = pdao.totalPrograms();
		System.out.println("���� ��ϵ� ��� �� " + total + " ���Դϴ�.");
	}

	private void add() {
		System.out.println("\n << ���ο� � ���α׷� ��� >>");
		
		Program newprogram = new Program();
		
		System.out.println("> ���α׷��� �̸��� �Է����ּ���");
		String ProgramName = scanner.nextLine();
		newprogram.setProgramName(ProgramName);
		
		System.out.println("> ���α׷��� �� ������ �Է����ּ���");
		String ProgramInfo = scanner.nextLine();
		newprogram.setProgramInfo(ProgramInfo);
		
		pdao.addProgram(newprogram);
		
		
		Program program = pdao.findByProgramName(ProgramName);
		
		
		
		List<Exercise> exerList = new ArrayList<Exercise>();
		System.out.println("���α׷��� ��� ��� �����ðڽ��ϱ�? ");
		int count = scanner.nextInt();
		
		System.out.println(count+" ���� ��� ���Ե� ���α׷��� �����մϴ�. ");
		scanner.nextLine();
		
		System.out.println("\n > �� ���α׷��� �ְ� ���� ����� �Է����ּ���");
		int calory = 0;
		int muscle = 0;
		int balance = 0;
		int stress =0;		
		
		for(int i=0; i<count; i++) {
			System.out.println((i+1) + " ��° � �̸�");
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
		
	
				
		System.out.println("���α׷��� ���� ��� �Ǿ����ϴ�.");
		
	}
	

	private void delete() {
		System.out.println("\n << ��ϵ� � ���α׷� ���� �ϱ� >>");
		
		System.out.println("> ������ ���α׷��� ��ȣ�� �Է��ϼ���");
		int ProgramID = scanner.nextInt();
		
		Program foundProgram = pdao.findByProgramID(ProgramID);
		
		System.out.println(" > ������ ���α׷��� ����");
		System.out.println(foundProgram);
		scanner.nextLine();
		System.out.println("������ ���� �Ͻðڽ��ϱ�? (y/n)");
		String answer = scanner.nextLine();
		if(!answer.equals("y")) {
			System.out.println("���� �۾��� ����մϴ�. ");
			return;
		}
		//�ڽ�Ŭ������ programID�� ���� �ٲ���� �Ѵ�.
		List<Exercise> list = edao.findByProgramID(ProgramID);
		
		for(Exercise exer : list) {
			edao.whenDeleteIdUpdate(exer.getExerNum());
		}			
//		
//		pdao.deleteProgram(ProgramID);
//		
		System.out.println("***���� �۾��� �Ϸ� �Ǿ����ϴ�.");
		
	}

	private void inform() {
		System.out.println("<< � ���α׷� �� ���� >>");
		
		System.out.println("�� ������ Ȯ���� ���α׷� ��ȣ�� �Է��ϼ��� ");
		int ProgramID = scanner.nextInt();
		
		Program foundProgram = pdao.findByProgramID(ProgramID);
		
		System.out.println("<< ��ȸ �Ͻ� ���α׷��� Information >>");
		System.out.println(foundProgram.getProgramInfo());
		
		System.out.println("=======================[ "+foundProgram.getProgramName()+" �� ���Ե� ���]============================");
		System.out.println("[���ȣ]     [��̸�]    [�Ÿ��]    [��ð�(��)] "
				+ "[�Ҹ�Į�θ�] [��ݱٷ� ������] [��Ʈ���� ���ҷ�] [�뷱�� ������]");
		System.out.println("=================================================================================");
					List<Exercise> list = edao.findByProgramID(ProgramID);
					list.forEach(exer->System.out.println(exer));
		System.out.println("=================================================================================");
	}

}
