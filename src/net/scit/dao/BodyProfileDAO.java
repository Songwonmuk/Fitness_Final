package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.BodyProfile;

public class BodyProfileDAO {
	
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	//바디 프로필 등록
	public int register(BodyProfile vo) {
		
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		int result = mapper.register(vo);
		session.commit();
		return result;
	}

	//전체 바디 프로필 조회
	public List<BodyProfile> findAll() {
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		List<BodyProfile> list = mapper.findAll();
		return list;
	}

	//바디 프로필 삭제
	public int delete(int memberId) {
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		int result = mapper.delete(memberId);
		session.commit();
		return result;
	}

	//바디 프로필 수정
	public int update(BodyProfile vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		int result = mapper.update(vo);
		return result;
	}

	//등록된 바디프로필 수 확인
	public int getCount() {
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		int result = mapper.getCount();
		return result;
	}

	//회원 아이디로 조회
	public List<BodyProfile> findById(int memberId) {
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		List<BodyProfile> profileList = mapper.findById(memberId);
		return profileList;
	}	
}
