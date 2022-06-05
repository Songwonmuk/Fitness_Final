package net.scit.dao;

import java.util.List;

import net.scit.vo.Manager;

public interface ManagerMapper {

	//�Ŵ��� �ű� ����
	public int join(Manager vo);

	//��ü ��ȸ
	public List<Manager> findAll();

	//�Ŵ��� ���� ����
	public int delete(int managerid);

	//�Ŵ��� ���� ����
	public int update(Manager vo);

	//�ο��� Ȯ��
	public int getCount();

	//�Ŵ��� ���̵�� ��ȸ
	public Manager findById(int managerId);
	
	//�� ȸ���� ���� �Ŵ��� �˻�
	public Manager searchManager(int memberId);
	
	//��� influenceLevel �˻�
	public List<Manager> selectAllInfluenceLevel();
	
	//influenceLevel ���� �˻�
	public List<Manager> findByLevel(int levelNum);
}
