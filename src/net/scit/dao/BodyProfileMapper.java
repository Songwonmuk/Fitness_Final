package net.scit.dao;

import java.util.List;

import net.scit.vo.BodyProfile;

public interface BodyProfileMapper {

	//바디 프로필 등록
	public int register(BodyProfile vo);

	//바디 프로필 전체 조회
	public List<BodyProfile> findAll();

	//바디 프로필 삭제
	public int delete(int memberid);

	//바디 프로필 수정
	public int update(BodyProfile vo);

	//등록된 바디프로필 수 확인
	public int getCount();

	//회원 아이디로 조회
	public List<BodyProfile> findById(int memberId);
	
}
