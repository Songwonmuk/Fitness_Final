package net.scit.ui;

import net.scit.service.MemberService;
import net.scit.vo.Member;

import java.util.Scanner;

public class MemberSignInUI {

    private Scanner scanner = new Scanner(System.in);
    private MemberService memberService = new MemberService();


    public void join() {
        String memberPw; // ��� pw
        String memberName; // ��� �̸�
        String phone; // ��ȭ��ȣ
        String gender; // ���� (��, ��)
        String birth; // ����

        System.out.println("�н����� �Է� : ");
        memberPw = scanner.nextLine();
        System.out.println("�̸� �Է� : ");
        memberName = scanner.nextLine();
        System.out.println("��ȭ��ȣ �Է� : ");
        phone = scanner.nextLine();
        System.out.println("���� �Է� (��/��) : ");
        gender = scanner.nextLine();
        System.out.println("���� �Է�(ex)1990/01/01) : ");
        birth = scanner.nextLine();

        Member member = new Member(memberPw, memberName, phone, gender, birth);

        boolean isJoined = memberService.join(member);
        if (isJoined) {
            System.out.println("**ȸ�������� �Ϸ�Ǿ����ϴ�.");
        }
    }
}
