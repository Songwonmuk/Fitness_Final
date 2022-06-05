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

    // 1. � �Ŵ��� ����
    public void manageManager(Member member) {
        String choice;
        subMenu_manager(member);
        choice = scanner.nextLine();

        switch (choice) {
            case "1":
                registerManager(member);
                break; // 1. ��� �Ŵ��� ���
            case "2":
                selectPresentManager(member);
                break; // 1. ���� ��� �Ŵ��� ��ȸ
            case "3":
                changeManager(member);
                break; // 1. ��� �Ŵ��� ����
            case "0":
                System.out.println("���� �޴��� ���ư��ϴ�");
                memberUI.mainUI(member); // ���� �޴���
            default:
                System.out.println("�ٽ� �Է��� �ּ���");
                break;
        }
    }

    // 1. � �Ŵ��� ������ ���� �޴�
    private void subMenu_manager(Member member) {
        System.out.println("== " + member.getMemberName() + "���� �Ŵ��� ���� ==");
        System.out.println("1. ��� �Ŵ��� ���");
        System.out.println("2. ���� ��� �Ŵ��� ��ȸ");
        System.out.println("3. ��� �Ŵ��� ����");
        System.out.println("0. ���� �޴�");
        System.out.print(">>���� : ");
    }

    // 1. ��� �Ŵ��� ��� - (1)
    private void registerManager(Member member) {
        System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ����� ����մϴ� **");
        matchManager(member);
    }

    // 1. ��� �Ŵ��� ��� - (1) influenceLevel ��ȸ
    private void matchManager(Member member) {
        if (!managerService.hasManagers()) {
            System.out.println("��ϵ� �Ŵ����� �����ϴ�");
            return;
        }
        while (true) {
            Optional<Manager> matchedManager = matchManagerByLevel();
            if (matchedManager.isPresent()) {
                // �Է��� �Ŵ����� ID�� �� ���α׷��� �Ŵ����� ��Ͻ�Ű��
                memberService.updateManager(member, matchedManager.get());
                System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ����� ���������� ��ϵǾ����ϴ�");
                break;
            }
            System.out.println("��ϵǾ� ���� ���� �Ŵ��� ID�Դϴ�");
        }
    }

    /**
     * ���⵵�� �´� manager ��ȸ
     *
     * @return
     */
    private Optional<Manager> matchManagerByLevel() {
        System.out.println("**����ϰ��� �ϴ� � �Ŵ����� ���⵵ ������ �Է��ϼ���(1~5)");
        subMenu_Level();
        int levelNum = scanner.nextInt();

        List<Manager> managers = managerService.findManagersByLevel(levelNum);

        for (Manager manager : managers) {
            System.out.println("�Ŵ��� ID : " + manager.getManagerId() + "  �Ŵ����� : " + manager.getManagerName() + "���⵵ : "
                    + manager.getInfluenceLevel());
        }

        System.out.print("**��� �Ŵ����� ����ϰ� ���� �Ŵ��� ID�� �Է��ϼ��� : ");
        int managerId = scanner.nextInt();
        return managers.stream()
                .filter(manager -> manager.getManagerId() == managerId)
                .findAny();
    }

    // ���÷�� ���� ����
    private void subMenu_Level() {
        System.out.println("���� 1");
        System.out.println("���� 2");
        System.out.println("���� 3");
        System.out.println("���� 4");
        System.out.println("���� 5");
        System.out.print(">>���� : ");
    }

    // 1. ���� ��� �Ŵ��� ��ȸ
    private void selectPresentManager(Member member) {
        String answer;

        System.out.println("**���� " + member.getMemberName() + "���� � �Ŵ����� �����Դϴ�**");
        // ȸ���� ���� � �Ŵ����� �̵�� �����̸�, �Ŵ��� ��� ���� ����
        notRegistered(member);
        // ��ϵ� �Ŵ����� �ִٸ�, ���
        System.out.println("**���� " + member.getMemberName() + "���� ��� �Ŵ����� ����**");
        System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ��� : " + member.getManagerId() + "�Դϴ�");
    }

    /**
     * �Ŵ��� ��� üũ
     *
     * @param member
     */
    private void notRegistered(Member member) {
        System.out.println("** " + member.getMemberName() + "���� ���� � �Ŵ����� �̵�� �����Դϴ�");
        System.out.println("** �Ŵ����� ����Ͻðڽ��ϱ�?(y/n)");
        String answer = scanner.nextLine();

        if (answer.equals("n")) {
            System.out.println("** ���� ȭ������ ���ư��ϴ�");
            manageManager(member);
        } else {
            registerManager(member); // �Ŵ��� ��� ȭ������
        }
    }

    // 1. ��� �Ŵ��� ����
    private void changeManager(Member member) {
        if (member.getManagerId() == 0) { //�Ŵ��� �̵�� ����
            notRegistered(member);
            return;
        }

        System.out.println("**���� " + member.getMemberName() + "���� ��� �Ŵ����� ����**");
        System.out.println("** " + member.getMemberName() + "���� ��� �Ŵ��� : " + member.getManagerId() + "�Դϴ� %n");

        // ��� �Ŵ��� ����
        matchManager(member);
    }
}
