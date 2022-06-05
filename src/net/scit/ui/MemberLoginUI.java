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
		System.out.println("���̵� �Է� : ");
		int memberId = scanner.nextInt();
		System.out.println("��й�ȣ �Է� : ");
		String memberPw = scanner.nextLine();
		Optional<Member> member = memberService.findMember(memberId);

		// id�� ��ϵǾ� ���� ������ �α��� â �Ǵ� ȸ������ â���� �̵�, �Է��� ȸ�� ���̵�� ����� ��� ��ġ�ϸ� �α���
		if (member.isEmpty()) {
			System.out.println("**��ϵ��� ���� ȸ���Դϴ�");
			System.out.print("**ȸ������ â���� �̵��Ͻðڽ��ϱ�?(y/n)");
			String answer = scanner.nextLine();

			if (answer.equals("n")) {
				System.out.println("**�α��� â���� ���ư��ϴ�");
				login();
				return;
			} else {
				System.out.println("**ȸ������ â���� �̵��մϴ�");
				signInUI.join();
				return;
			}
		}

		// �α��� ����
		if (member.get().isMatched(memberPw)) {
			System.out.println("== " + member.get().getMemberName() + "��, ȯ���մϴ�! ==");
			memberUI.mainUI(member.get());
			return;
		}

		//�α��� ����
		// TODO: 2022-06-05 ��й�ȣ Ʋ�� ��� code here
	}
}
