package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.Member;

public class MemberDAO {

	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	//ȸ�� ����
	public int join(Member vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = mapper.join(vo);
		session.commit();
		return result;
	}

	//��ü ��ȸ
	public List<Member> findAll() {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		List<Member> list = mapper.findAll();
		return list;
	}
	
	//ȸ�� ���� ����
	public int delete(int memberid) {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = mapper.delete(memberid);
		session.commit();
		return result;
	}

	//ȸ�� ���� ����
	public int update(Member vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = mapper.update(vo);
		session.commit();
		return result;
	}
	
	//�ο��� Ȯ��
	public int getCount() {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = mapper.getCount();
		
		return result;
	}

	//ȸ�� ���̵�� ��ȸ
	public Member findById(int memberId) {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		Member vo = mapper.findById(memberId);
		return vo;
	}

	//�Է��� �Ŵ����� ID�� �� ���α׷��� �Ŵ����� ���
	public int registerMyManager(int managerId) {
		SqlSession session = null;
		session = factory.openSession();
		
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		int result = mapper.registerMyManager(managerId);
		session.commit();
		return result;
	}
	
//��� �ɵ�	
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