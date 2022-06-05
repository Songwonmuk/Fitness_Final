package net.scit.dao;

import java.util.List;

import net.scit.vo.Member;

public interface MemberMapper {

		//ȸ�� ����
		public int join(Member vo);
		
		//��ü ��ȸ
		public List<Member> findAll();
		
		//ȸ�� ���� ����
		public int delete(int memberid);
		
		//ȸ�� ���� ����
		public int update(Member vo);

		//�ο��� Ȯ��
		public int getCount();

		//ȸ�� ���̵�� ��ȸ
		public Member findById(int memberId);

		//�Է��� �Ŵ����� ID�� �� ���α׷��� �Ŵ����� ���
		public int registerMyManager(int managerId);
		
//��� �� ��		
//		public Member findByPw(String memberPw); 
}
