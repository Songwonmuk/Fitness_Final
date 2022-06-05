package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.Manager;

public class ManagerDAO {

	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	//관리자 가입
	public int join(Manager vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		int result = mapper.join(vo);
		session.commit();
		return result;
	}

	//관리자 전체 조회
	public List<Manager> findAll() {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		List<Manager> list = mapper.findAll();
		return list;
	}
	
	//관리자 삭제
	public int delete(int managerid) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		int result = mapper.delete(managerid);
		session.commit();
		return result;
	}

	//관리자 정보 수정
	public int update(Manager vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		int result = mapper.update(vo);
		session.commit();
		return result;
	}
	
	//인원수 확인
	public int getCount() {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		int result = mapper.getCount();
		
		return result;
	}

	//관리자 아이디로 조회
	public Manager findById(int managerId) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		Manager vo = mapper.findById(managerId);
		return vo;
	}

	// 각 회원에 대한 매니저 검색
	public Manager searchManager(int memberId) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		Manager vo = mapper.searchManager(memberId);
		return vo;
	}
	
	//모든 influenceLevel 검색
	public List<Manager> selectAllInfluenceLevel() {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		List<Manager> list = mapper.selectAllInfluenceLevel();
		return list;
	}
	
	//influenceLevel 별로 검색
	public List<Manager> findByLevel(int levelNum) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		List<Manager> list = mapper.findByLevel(levelNum);
		return list;
	}

}