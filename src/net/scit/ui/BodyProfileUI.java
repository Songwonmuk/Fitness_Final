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

    // 3.�ٵ� ������ ����
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
                System.out.println("���� �޴��� ���ư��ϴ�");
                memberUI.mainUI(member);
            default:
                System.out.println("��ȣ�� �ٽ� �Է��� �ּ���");
        }
    }

    // 3.�ٵ� ������ ���� �޴�
    private void subMenu_bodyProfile(Member member) {
        System.out.println("== " + member.getMemberName() + "���� �ٵ� ������ ���� ==");
        System.out.println("1. �ٵ� ������ ���");
        System.out.println("2. ���� �ιٵ� ��ȸ");
        System.out.println("3. �̷��� " + member.getMemberName() + "���� ���");
        System.out.println("0. ���� �޴�");
        System.out.print(">>���� : ");
    }

    // 3.�ٵ� ������ ���
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

    // 3.�ιٵ� ��ȸ
    private void selectInbody(Member member) {
    }

    // 3.�̷��� �� (���α׷��� ���� ���� �� �ִ� ��ġ)
    private void myFuture(Member member) {
        System.out.println("==============[�� �̷� ��� ����(My Future]===============");
//
//        List<BodyProfile> profileList = bodyDao.findById(member.getMemberId());
//        System.out.println(" << ������� ��ϵ� ȸ������ �ٵ� ������ ����Դϴ�. >> ");
//        profileList.forEach(System.out::println);
//        // ���� ����� ������ �����ߴ���... ����...
//        BodyProfile currentBodyProfile = new BodyProfile(); // �������� ���� ���� �ٵ� ������
//
//        System.out.println("�̷��� ���...�尡��");
//        System.out.println(" ȸ������ � �ֱ⸦ �Է����ּ���(1�� � Ƚ��)");
//        int perweek = scanner.nextInt();
//        System.out.println(" ���� ���α׷��� ��ް� ���� �Ͻðڽ��ϱ� ? ");
//        int period = scanner.nextInt();
//        int count = perweek * period * 4; // �ѿȽ��
//        System.out.println(member.getMemberName() + " ȸ������ " + period + " �� ���� " + count + " ���� ���α׷��� �����ϰ� �˴ϴ�.");
//
//        Program specificMembersProgram = proDao.findByMemberID(member.getMemberId());
//
//        double caloryResult = specificMembersProgram.getTotalCalory() * count;
//        double muscleResult = specificMembersProgram.getTotalMuscle() * count;
//        double balanceResult = specificMembersProgram.getTotalBalance() * count;
//        double stressResult = specificMembersProgram.getTotalStress() * count;
//
//        // ���� ���� �ٰ�����. �Ⱓ�� ���� ����� �⺻ ���� Į�θ�, ��Ʈ���� ����ġ ���
//        // 7000Į�θ��� 1kg ����
//        currentBodyProfile.setSmm(currentBodyProfile.getSmm() + muscleResult);
//        currentBodyProfile.setWeight((int) (currentBodyProfile.getWeight() - caloryResult / 7000));
        // set ����.... currentCalory -caloryResult;
    }
	
}
