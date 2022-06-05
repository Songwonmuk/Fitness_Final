package net.scit.dao;

import java.util.List;

import net.scit.vo.Member;

public interface MemberMapper {

		//회원 가입
		public int join(Member vo);
		
		//전체 조회
		public List<Member> findAll();
		
		//회원 정보 삭제
		public int delete(int memberid);
		
		//회원 정보 수정
		public int update(Member vo);

		//인원수 확인
		public int getCount();

		//회원 아이디로 조회
		public Member findById(int memberId);

		//입력한 매니저의 ID를 내 프로그램의 매니저로 등록
		public int registerMyManager(int managerId);
		
//없어도 될 듯		
//		public Member findByPw(String memberPw); 
}
