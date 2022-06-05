package net.scit.dao;

import java.util.List;

import net.scit.vo.BodyProfile;

public interface BodyProfileMapper {

	//�ٵ� ������ ���
	public int register(BodyProfile vo);

	//�ٵ� ������ ��ü ��ȸ
	public List<BodyProfile> findAll();

	//�ٵ� ������ ����
	public int delete(int memberid);

	//�ٵ� ������ ����
	public int update(BodyProfile vo);

	//��ϵ� �ٵ������� �� Ȯ��
	public int getCount();

	//ȸ�� ���̵�� ��ȸ
	public List<BodyProfile> findById(int memberId);
	
}
