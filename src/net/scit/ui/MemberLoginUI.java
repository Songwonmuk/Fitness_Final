package net.scit.ui;

import net.scit.service.MemberService;
import net.scit.vo.Member;

import java.util.Optional;
import java.util.Scanner;

public class MemberLoginUI {

	private Scanner scanner = new Scanner(System.in);
	private MemberSignInUI signInUI = new MemberSignInUI();
	private MemberUI memberUI = new MemberUI();
	private MemberService memberService = new MemberService();

	public void login() {
		System.out.println("아이디 입력 : ");
		int memberId = scanner.nextInt();
		System.out.println("비밀번호 입력 : ");
		String memberPw = scanner.nextLine();
		Optional<Member> member = memberService.findMember(memberId);

		// id가 등록되어 있지 않으면 로그인 창 또는 회원가입 창으로 이동, 입력한 회원 아이디와 비번이 모두 일치하면 로그인
		if (member.isEmpty()) {
			System.out.println("**등록되지 않은 회원입니다");
			System.out.print("**회원가입 창으로 이동하시겠습니까?(y/n)");
			String answer = scanner.nextLine();

			if (answer.equals("n")) {
				System.out.println("**로그인 창으로 돌아갑니다");
				login();
				return;
			} else {
				System.out.println("**회원가입 창으로 이동합니다");
				signInUI.join();
				return;
			}
		}

		// 로그인 성공
		if (member.get().isMatched(memberPw)) {
			System.out.println("== " + member.get().getMemberName() + "님, 환영합니다! ==");
			memberUI.mainUI(member.get());
			return;
		}

		//로그인 실패
		// TODO: 2022-06-05 비밀번호 틀린 경우 code here
	}
}
