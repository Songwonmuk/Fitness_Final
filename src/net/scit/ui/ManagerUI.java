package net.scit.ui;

import net.scit.service.ManagerService;
import net.scit.service.MemberService;
import net.scit.vo.Manager;
import net.scit.vo.Member;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ManagerUI {

    private ManagerService managerService;
    private MemberService memberService;
    private Scanner scanner;
    private MemberUI memberUI;

    public ManagerUI(MemberUI memberUI) {
        this.managerService = new ManagerService();
        this.memberService = new MemberService();
        this.scanner = new Scanner(System.in);
        this.memberUI = memberUI;
    }

    // 1. 운동 매니저 관리
    public void manageManager(Member member) {
        String choice;
        subMenu_manager(member);
        choice = scanner.nextLine();

        switch (choice) {
            case "1":
                registerManager(member);
                break; // 1. 담당 매니저 등록
            case "2":
                selectPresentManager(member);
                break; // 1. 현재 담당 매니저 조회
            case "3":
                changeManager(member);
                break; // 1. 담당 매니저 변경
            case "0":
                System.out.println("상위 메뉴로 돌아갑니다");
                memberUI.mainUI(member); // 상위 메뉴로
            default:
                System.out.println("다시 입력해 주세요");
                break;
        }
    }

    // 1. 운동 매니저 관리의 서브 메뉴
    private void subMenu_manager(Member member) {
        System.out.println("== " + member.getMemberName() + "님의 매니저 관리 ==");
        System.out.println("1. 담당 매니저 등록");
        System.out.println("2. 현재 담당 매니저 조회");
        System.out.println("3. 담당 매니저 변경");
        System.out.println("0. 상위 메뉴");
        System.out.print(">>선택 : ");
    }

    // 1. 담당 매니저 등록 - (1)
    private void registerManager(Member member) {
        System.out.println("** " + member.getMemberName() + "님의 담당 매니저를 등록합니다 **");
        matchManager(member);
    }

    // 1. 담당 매니저 등록 - (1) influenceLevel 조회
    private void matchManager(Member member) {
        if (!managerService.hasManagers()) {
            System.out.println("등록된 매니저가 없습니다");
            return;
        }
        while (true) {
            Optional<Manager> matchedManager = matchManagerByLevel();
            if (matchedManager.isPresent()) {
                // 입력한 매니저의 ID를 내 프로그램의 매니저로 등록시키기
                memberService.updateManager(member, matchedManager.get());
                System.out.println("** " + member.getMemberName() + "님의 담당 매니저가 정상적으로 등록되었습니다");
                break;
            }
            System.out.println("등록되어 있지 않은 매니저 ID입니다");
        }
    }

    /**
     * 영향도에 맞는 manager 조회
     *
     * @return
     */
    private Optional<Manager> matchManagerByLevel() {
        System.out.println("**등록하고자 하는 운동 매니저의 영향도 레벨을 입력하세요(1~5)");
        subMenu_Level();
        int levelNum = scanner.nextInt();

        List<Manager> managers = managerService.findManagersByLevel(levelNum);

        for (Manager manager : managers) {
            System.out.println("매니저 ID : " + manager.getManagerId() + "  매니저명 : " + manager.getManagerName() + "영향도 : "
                    + manager.getInfluenceLevel());
        }

        System.out.print("**담당 매니저로 등록하고 싶은 매니저 ID를 입력하세요 : ");
        int managerId = scanner.nextInt();
        return managers.stream()
                .filter(manager -> manager.getManagerId() == managerId)
                .findAny();
    }

    // 인플루언스 레벨 고르기
    private void subMenu_Level() {
        System.out.println("레벨 1");
        System.out.println("레벨 2");
        System.out.println("레벨 3");
        System.out.println("레벨 4");
        System.out.println("레벨 5");
        System.out.print(">>선택 : ");
    }

    // 1. 현재 담당 매니저 조회
    private void selectPresentManager(Member member) {
        String answer;

        System.out.println("**현재 " + member.getMemberName() + "님의 운동 매니저의 정보입니다**");
        // 회원에 대한 운동 매니저가 미등록 상태이면, 매니저 등록 여부 묻기
        notRegistered(member);
        // 등록된 매니저가 있다면, 출력
        System.out.println("**현재 " + member.getMemberName() + "님의 담당 매니저의 정보**");
        System.out.println("** " + member.getMemberName() + "님의 담당 매니저 : " + member.getManagerId() + "입니다");
    }

    /**
     * 매니저 등록 체크
     *
     * @param member
     */
    private void notRegistered(Member member) {
        System.out.println("** " + member.getMemberName() + "님은 현재 운동 매니저가 미등록 상태입니다");
        System.out.println("** 매니저를 등록하시겠습니까?(y/n)");
        String answer = scanner.nextLine();

        if (answer.equals("n")) {
            System.out.println("** 이전 화면으로 돌아갑니다");
            manageManager(member);
        } else {
            registerManager(member); // 매니저 등록 화면으로
        }
    }

    // 1. 담당 매니저 변경
    private void changeManager(Member member) {
        if (member.getManagerId() == 0) { //매니저 미등록 상태
            notRegistered(member);
            return;
        }

        System.out.println("**현재 " + member.getMemberName() + "님의 담당 매니저의 정보**");
        System.out.println("** " + member.getMemberName() + "님의 담당 매니저 : " + member.getManagerId() + "입니다 %n");

        // 담당 매니저 변경
        matchManager(member);
    }
}
