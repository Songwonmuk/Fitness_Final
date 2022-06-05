package net.scit.ui;

import net.scit.service.BodyProfileService;
import net.scit.service.ExerciseService;
import net.scit.service.ProgramService;
import net.scit.vo.BodyProfile;
import net.scit.vo.Exercise;
import net.scit.vo.Member;
import net.scit.vo.Program;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProgramManageUI {

    private Scanner scanner;
    private MemberUI memberUI;
    private ProgramService programService;
    private BodyProfileService bodyProfileService;
    private ExerciseService exerciseService;

    public ProgramManageUI(MemberUI memberUI) {
        this.scanner = new Scanner(System.in);
        this.memberUI = memberUI;
        this.programService = new ProgramService();
        this.bodyProfileService = new BodyProfileService();
        this.exerciseService = new ExerciseService();
    }

    // 2. � ���α׷� ����
    public void manageProgram(Member member) {
        subMenu_program(member);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                selectPresentProgram(member);
                break; // 1.���� � ���α׷� ��ȸ
            case "2":
                changeProgram(member);
                break; // 2.� ���α׷� ����
            case "3":
                createMyProgram(member);
                break; // 2.������ � ���α׷� ����
            case "0":
                System.out.println("���� �޴��� ���ư��ϴ�");
                memberUI.mainUI(member);
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
        programService.findProgramByMemberId(member.getMemberId())
                        .ifPresent(System.out::println);
        System.out.println("=================================================================================");

    }

    // 2.� ���α׷� ����
    private void changeProgram(Member member) {

        while (true) {
            System.out.println(" << " + member.getMemberName() + " ȸ������ � ���α׷� ���� >> ");

            System.out.println("\n<< ���α׷� ��ü ����Դϴ�. ���Ͻô� ���α׷��� ��󺸼��� >>");
            System.out.println("[���α׷���ȣ]     [���α׷��̸�]    [�Ÿ��]     [�Ҹ�Į�θ�]     [��ݱٷ� ������]    [��Ʈ���� ���ҷ�]    [�뷱�� ������]");
            System.out.println("=================================================================================");
            List<Program> allPrograms = programService.findAllPrograms();
            allPrograms.forEach(System.out::println);
            System.out.println("=================================================================================");

            System.out.println(" >> �����Ͻ� ���α׷� ��ȣ�� �Է����ּ���");
            int programNumber = scanner.nextInt();

            Optional<Program> findProgram = allPrograms.stream()
                    .filter(program -> program.getProgramID() == programNumber)
                    .findAny();

            if (findProgram.isPresent()) {
                BodyProfile recentBodyProfile = bodyProfileService.findRecentBodyProfile(member.getMemberId());
                recentBodyProfile.setProgramId(programNumber); // ����� ���α׷� ��ȣ ����
                bodyProfileService.updateBodyProfile(recentBodyProfile); // db�� ����
                break;
            }
            System.out.println("Ʋ�� ��ȣ�Դϴ�. �ٽ� �Է����ּ���.");
        }
    }

    // 2.������ � ���α׷� ����
    private void createMyProgram(Member member) {

        System.out.println("\n << ������ � ���α׷� ��� >>");

        System.out.println("> ���α׷��� �̸��� �Է����ּ���");
        String ProgramName = scanner.nextLine();

        System.out.println("> ���α׷��� �� ������ �Է����ּ���");
        String ProgramInfo = scanner.nextLine();

        Program program = programService.registerProgram(new Program(ProgramName, ProgramInfo));

        List<Exercise> exercises = new ArrayList<Exercise>();
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
            Exercise exercise = inputExercise(i);

            exercises.add(exercise);

            calory += exercise.getConsumedCalory();
            muscle += exercise.getGainedMuscle();
            balance += exercise.getGetBalance();
            stress += exercise.getLowerStress();
        }

        program.setTotalCalory(calory);
        program.setTotalMuscle(muscle);
        program.setTotalBalance(balance);
        program.setTotalStress(stress);

        programService.updateProgram(program);

        int programId = program.getProgramID();
        for (Exercise exercise : exercises) {
            exerciseService.updateProgramID(exercise, programId);
        }

        System.out.println(" ���ο� � ���α׷��� ���� ��� �Ǿ����ϴ�.");
        System.out.println(" �� ����α׷��� �� � ���α׷����� ���� �Ͻðڽ��ϱ�? (y/n)");
        String answer = scanner.nextLine();
        if (!answer.equals("y")) {
            System.out.println("������ ����� ��� DB�� �����ϰڽ��ϴ�. ����� ������ּ���");
            return;
        }

        BodyProfile recentBodyProfile = bodyProfileService.findRecentBodyProfile(member.getMemberId());
        recentBodyProfile.setProgramId(programId); // ���� ���α׷� ��ȣ ����
        bodyProfileService.updateBodyProfile(recentBodyProfile); // DB�� ���
    }

    private Exercise inputExercise(int i) {
        while (true) {
            System.out.println((i + 1) + " ��° � �̸�");
            String name = scanner.nextLine();
            Optional<Exercise> exercise = exerciseService.findExerciseByName(name);

            if (exercise.isPresent()) {
                return exercise.get();
            }
            System.out.println("��̸��� Ʋ�Ƚ��ϴ�. �ٽ� �Է����ּ���.");
        }
    }
}
