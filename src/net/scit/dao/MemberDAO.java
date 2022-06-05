package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.Member;

public class MemberDAO {

	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	//회원 가입
	public int join(Member vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = mapper.join(vo);
		session.commit();
		return result;
	}

	//전체 조회
	public List<Member> findAll() {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		List<Member> list = mapper.findAll();
		return list;
	}
	
	//회원 정보 삭제
	public int delete(int memberid) {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = mapper.delete(memberid);
		session.commit();
		return result;
	}

	//회원 정보 수정
	public int update(Member vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = mapper.update(vo);
		session.commit();
		return result;
	}
	
	//인원수 확인
	public int getCount() {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = mapper.getCount();
		
		return result;
	}

	//회원 아이디로 조회
	public Member findById(int memberId) {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		Member vo = mapper.findById(memberId);
		return vo;
	}

	//입력한 매니저의 ID를 내 프로그램의 매니저로 등록
	public int registerMyManager(int managerId) {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = mapper.registerMyManager(managerId);
		session.commit();
		return result;
	}
	
//없어도 될듯	
//	public Member findByPw(String memberPw) {
//		SqlSession session = null;
//		session = factory.openSession();
//		
//		MemberMapper mapper = session.getMapper(MemberMapper.class);
//		
//		Member vo = mapper.findByPw(memberPw);
//		return vo;
//	}
}