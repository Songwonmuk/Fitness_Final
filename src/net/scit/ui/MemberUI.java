package net.scit.ui;

import net.scit.vo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberUI {

    private Scanner scanner = new Scanner(System.in);
    private ManagerUI managerUI = new ManagerUI(this);
    private ProgramManageUI programManageUI = new ProgramManageUI(this);
    private BodyProfileUI bodyProfileUI = new BodyProfileUI(this);



    public void mainUI(Member member) {
        String choice;

        while (true) {
            mainMenu();
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    managerUI.manageManager(member);
                    break; // 1. 운동 매니저 관리
                case "2":
                    programManageUI.manageProgram(member);
                    break; // 2. 운동 프로그램 관리
                case "3":
                    bodyProfileUI.manageBodyProfile(member);
                    break; // 3. 바디 프로필 관리
                case "0":
                    System.out.println("상위 메뉴로 돌아갑니다.");
                    return;
                default:
                    System.out.println("다시 입력해 주세요");
                    break;
            }
        }
    }

    private void mainMenu() {
        System.out.println("==SCIT FITNESS==");
        System.out.println("1. 운동 매니저 관리");
        System.out.println("2. 운동 프로그램 관리");
        System.out.println("3. 바디 프로필 관리");
        System.out.print(">>선택 : ");
    }
}
