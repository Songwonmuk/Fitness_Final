package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.BodyProfile;

public class BodyProfileDAO {
	
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	//�ٵ� ������ ���
	public int register(BodyProfile vo) {
		
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		int result = mapper.register(vo);
		session.commit();
		return result;
	}

	//��ü �ٵ� ������ ��ȸ
	public List<BodyProfile> findAll() {
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		List<BodyProfile> list = mapper.findAll();
		return list;
	}

	//�ٵ� ������ ����
	public int delete(int memberId) {
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		int result = mapper.delete(memberId);
		session.commit();
		return result;
	}

	//�ٵ� ������ ����
	public int update(BodyProfile vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		int result = mapper.update(vo);
		return result;
	}

	//��ϵ� �ٵ������� �� Ȯ��
	public int getCount() {
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		int result = mapper.getCount();
		return result;
	}

	//ȸ�� ���̵�� ��ȸ
	public List<BodyProfile> findById(int memberId) {
		SqlSession session = null;
		session = factory.openSession();
		
		BodyProfileMapper mapper = session.getMapper(BodyProfileMapper.class);
		
		List<BodyProfile> profileList = mapper.findById(memberId);
		return profileList;
	}	
}
