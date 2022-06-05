package net.scit.service;

import net.scit.dao.MemberDAO;
import net.scit.vo.Manager;
import net.scit.vo.Member;

import java.util.Optional;


/**
 * 회원 Service
 *
 */
public class MemberService {
    private static final int SUCCESS = 1;
    private MemberDAO memberDao = new MemberDAO();

    /**
     * 회원 조회
     * @param memberId
     * @return
     */
    public Optional<Member> findMember(int memberId) {
        return Optional.ofNullable(memberDao.findById(memberId));
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public boolean join(Member member) {
        return memberDao.join(member) == SUCCESS;
    }

    public boolean updateManager(Member member, Manager manager) {
        // TODO: 2022-06-05 회원에 managerId 업데이트
        // dao, 쿼리 다시짜야함
        // member에 managerId 업데이트해서 dao update
        return memberDao.registerMyManager(manager.getManagerId()) == SUCCESS;
    }
}
