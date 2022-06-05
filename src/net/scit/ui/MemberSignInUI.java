package net.scit.ui;

import net.scit.service.MemberService;
import net.scit.vo.Member;

import java.util.Scanner;

public class MemberSignInUI {

    private Scanner scanner = new Scanner(System.in);
    private MemberService memberService = new MemberService();


    public void join() {
        String memberPw; // 멤버 pw
        String memberName; // 멤버 이름
        String phone; // 전화번호
        String gender; // 성별 (남, 여)
        String birth; // 생일

        System.out.println("패스워드 입력 : ");
        memberPw = scanner.nextLine();
        System.out.println("이름 입력 : ");
        memberName = scanner.nextLine();
        System.out.println("전화번호 입력 : ");
        phone = scanner.nextLine();
        System.out.println("성별 입력 (남/여) : ");
        gender = scanner.nextLine();
        System.out.println("생일 입력(ex)1990/01/01) : ");
        birth = scanner.nextLine();

        Member member = new Member(memberPw, memberName, phone, gender, birth);

        boolean isJoined = memberService.join(member);
        if (isJoined) {
            System.out.println("**회원가입이 완료되었습니다.");
        }
    }
}
