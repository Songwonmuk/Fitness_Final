package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.Manager;

public class ManagerDAO {

	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	//������ ����
	public int join(Manager vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		int result = mapper.join(vo);
		session.commit();
		return result;
	}

	//������ ��ü ��ȸ
	public List<Manager> findAll() {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		List<Manager> list = mapper.findAll();
		return list;
	}
	
	//������ ����
	public int delete(int managerid) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		int result = mapper.delete(managerid);
		session.commit();
		return result;
	}

	//������ ���� ����
	public int update(Manager vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		int result = mapper.update(vo);
		session.commit();
		return result;
	}
	
	//�ο��� Ȯ��
	public int getCount() {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		int result = mapper.getCount();
		
		return result;
	}

	//������ ���̵�� ��ȸ
	public Manager findById(int managerId) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		Manager vo = mapper.findById(managerId);
		return vo;
	}

	// �� ȸ���� ���� �Ŵ��� �˻�
	public Manager searchManager(int memberId) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		Manager vo = mapper.searchManager(memberId);
		return vo;
	}
	
	//��� influenceLevel �˻�
	public List<Manager> selectAllInfluenceLevel() {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		List<Manager> list = mapper.selectAllInfluenceLevel();
		return list;
	}
	
	//influenceLevel ���� �˻�
	public List<Manager> findByLevel(int levelNum) {
		SqlSession session = null;
		session = factory.openSession();
		
		ManagerMapper mapper = session.getMapper(ManagerMapper.class);
		
		List<Manager> list = mapper.findByLevel(levelNum);
		return list;
	}

}