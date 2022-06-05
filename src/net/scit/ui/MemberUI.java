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
                    break; // 1. � �Ŵ��� ����
                case "2":
                    programManageUI.manageProgram(member);
                    break; // 2. � ���α׷� ����
                case "3":
                    bodyProfileUI.manageBodyProfile(member);
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
}
