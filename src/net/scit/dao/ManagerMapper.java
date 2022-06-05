package net.scit.dao;

import java.util.List;

import net.scit.vo.Manager;

public interface ManagerMapper {

	//매니저 신규 가입
	public int join(Manager vo);

	//전체 조회
	public List<Manager> findAll();

	//매니저 정보 삭제
	public int delete(int managerid);

	//매니저 정보 수정
	public int update(Manager vo);

	//인원수 확인
	public int getCount();

	//매니저 아이디로 조회
	public Manager findById(int managerId);
	
	//각 회원에 대한 매니저 검색
	public Manager searchManager(int memberId);
	
	//모든 influenceLevel 검색
	public List<Manager> selectAllInfluenceLevel();
	
	//influenceLevel 별로 검색
	public List<Manager> findByLevel(int levelNum);
}
