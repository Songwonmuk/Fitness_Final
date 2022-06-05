package net.scit.service;

import net.scit.dao.MemberDAO;
import net.scit.vo.Manager;
import net.scit.vo.Member;

import java.util.Optional;


/**
 * ȸ�� Service
 *
 */
public class MemberService {
    private static final int SUCCESS = 1;
    private MemberDAO memberDao = new MemberDAO();

    /**
     * ȸ�� ��ȸ
     * @param memberId
     * @return
     */
    public Optional<Member> findMember(int memberId) {
        return Optional.ofNullable(memberDao.findById(memberId));
    }

    /**
     * ȸ�� ����
     * @param member
     * @return
     */
    public boolean join(Member member) {
        return memberDao.join(member) == SUCCESS;
    }

    public boolean updateManager(Member member, Manager manager) {
        // TODO: 2022-06-05 ȸ���� managerId ������Ʈ
        // dao, ���� �ٽ�¥����
        // member�� managerId ������Ʈ�ؼ� dao update
        return memberDao.registerMyManager(manager.getManagerId()) == SUCCESS;
    }
}
