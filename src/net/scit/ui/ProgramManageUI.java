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

    // 2. 운동 프로그램 관리
    public void manageProgram(Member member) {
        subMenu_program(member);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                selectPresentProgram(member);
                break; // 1.현재 운동 프로그램 조회
            case "2":
                changeProgram(member);
                break; // 2.운동 프로그램 변경
            case "3":
                createMyProgram(member);
                break; // 2.나만의 운동 프로그램 생성
            case "0":
                System.out.println("상위 메뉴로 돌아갑니다");
                memberUI.mainUI(member);
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
        programService.findProgramByMemberId(member.getMemberId())
                        .ifPresent(System.out::println);
        System.out.println("=================================================================================");

    }

    // 2.운동 프로그램 변경
    private void changeProgram(Member member) {

        while (true) {
            System.out.println(" << " + member.getMemberName() + " 회원님의 운동 프로그램 변경 >> ");

            System.out.println("\n<< 프로그램 전체 목록입니다. 원하시는 프로그램을 골라보세요 >>");
            System.out.println("[프로그램번호]     [프로그램이름]    [운동타입]     [소모칼로리]     [골격근량 증가량]    [스트레스 감소량]    [밸런스 증가량]");
            System.out.println("=================================================================================");
            List<Program> allPrograms = programService.findAllPrograms();
            allPrograms.forEach(System.out::println);
            System.out.println("=================================================================================");

            System.out.println(" >> 변경하실 프로그램 번호를 입력해주세요");
            int programNumber = scanner.nextInt();

            Optional<Program> findProgram = allPrograms.stream()
                    .filter(program -> program.getProgramID() == programNumber)
                    .findAny();

            if (findProgram.isPresent()) {
                BodyProfile recentBodyProfile = bodyProfileService.findRecentBodyProfile(member.getMemberId());
                recentBodyProfile.setProgramId(programNumber); // 변경된 프로그램 번호 저장
                bodyProfileService.updateBodyProfile(recentBodyProfile); // db에 저장
                break;
            }
            System.out.println("틀린 번호입니다. 다시 입력해주세요.");
        }
    }

    // 2.나만의 운동 프로그램 생성
    private void createMyProgram(Member member) {

        System.out.println("\n << 나만의 운동 프로그램 등록 >>");

        System.out.println("> 프로그램의 이름을 입력해주세요");
        String ProgramName = scanner.nextLine();

        System.out.println("> 프로그램의 상세 정보를 입력해주세요");
        String ProgramInfo = scanner.nextLine();

        Program program = programService.registerProgram(new Program(ProgramName, ProgramInfo));

        List<Exercise> exercises = new ArrayList<Exercise>();
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

        System.out.println(" 새로운 운동 프로그램이 정상 등록 되었습니다.");
        System.out.println(" 이 운동프로그램을 내 운동 프로그램으로 설정 하시겠습니까? (y/n)");
        String answer = scanner.nextLine();
        if (!answer.equals("y")) {
            System.out.println("고객님이 만드신 운동은 DB에 저장하겠습니다. 등록을 고려해주세요");
            return;
        }

        BodyProfile recentBodyProfile = bodyProfileService.findRecentBodyProfile(member.getMemberId());
        recentBodyProfile.setProgramId(programId); // 만든 프로그램 번호 저장
        bodyProfileService.updateBodyProfile(recentBodyProfile); // DB에 등록
    }

    private Exercise inputExercise(int i) {
        while (true) {
            System.out.println((i + 1) + " 번째 운동 이름");
            String name = scanner.nextLine();
            Optional<Exercise> exercise = exerciseService.findExerciseByName(name);

            if (exercise.isPresent()) {
                return exercise.get();
            }
            System.out.println("운동이름이 틀렸습니다. 다시 입력해주세요.");
        }
    }
}
