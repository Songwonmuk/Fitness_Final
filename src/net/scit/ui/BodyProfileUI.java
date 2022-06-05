package net.scit.ui;

import net.scit.service.BodyProfileService;
import net.scit.service.ProgramService;
import net.scit.vo.BodyProfile;
import net.scit.vo.Member;
import net.scit.vo.Program;

import java.util.List;
import java.util.Scanner;

public class BodyProfileUI {

    private Scanner scanner = new Scanner(System.in);
    private MemberUI memberUI;
    private BodyProfileService bodyProfileService;
    private ProgramService programService;


    public BodyProfileUI(MemberUI memberUI) {
        this.memberUI = memberUI;
        this.bodyProfileService = new BodyProfileService();
        this.programService = new ProgramService();
    }

    // 3.바디 프로필 관리
    public void manageBodyProfile(Member member) {
        subMenu_bodyProfile(member);
        String choice = scanner.nextLine();

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
                memberUI.mainUI(member);
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
//
//        List<BodyProfile> profileList = bodyDao.findById(member.getMemberId());
//        System.out.println(" << 현재까지 등록된 회원님의 바디 프로필 목록입니다. >> ");
//        profileList.forEach(System.out::println);
//        // 현재 모습은 소현상 구현했던거... 넣자...
//        BodyProfile currentBodyProfile = new BodyProfile(); // 소현상이 구한 현재 바디 프로필
//
//        System.out.println("미래의 모습...드가자");
//        System.out.println(" 회원님의 운동 주기를 입력해주세요(1주 운동 횟수)");
//        int perweek = scanner.nextInt();
//        System.out.println(" 현재 프로그램을 몇달간 진행 하시겠습니까 ? ");
//        int period = scanner.nextInt();
//        int count = perweek * period * 4; // 총운동횟수
//        System.out.println(member.getMemberName() + " 회원님은 " + period + " 달 동안 " + count + " 번의 프로그램을 진행하게 됩니다.");
//
//        Program specificMembersProgram = proDao.findByMemberID(member.getMemberId());
//
//        double caloryResult = specificMembersProgram.getTotalCalory() * count;
//        double muscleResult = specificMembersProgram.getTotalMuscle() * count;
//        double balanceResult = specificMembersProgram.getTotalBalance() * count;
//        double stressResult = specificMembersProgram.getTotalStress() * count;
//
//        // 어찌 영향 줄것인지. 기간에 따른 사람의 기본 섭취 칼로리, 스트레스 증가치 계산
//        // 7000칼로리당 1kg 감소
//        currentBodyProfile.setSmm(currentBodyProfile.getSmm() + muscleResult);
//        currentBodyProfile.setWeight((int) (currentBodyProfile.getWeight() - caloryResult / 7000));
        // set 하자.... currentCalory -caloryResult;
    }
	
}
