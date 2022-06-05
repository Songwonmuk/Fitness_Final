package net.scit.service;

import net.scit.dao.BodyProfileDAO;
import net.scit.dao.ManagerDAO;
import net.scit.vo.BodyProfile;
import net.scit.vo.Manager;

import java.util.List;


/**
 * 바디 프로필 Service
 *
 */
public class BodyProfileService {
    private BodyProfileDAO bodyProfileDAO = new BodyProfileDAO();

    /**
     * 최근 바디 프로필 조회
     * todo 쿼리 수정
     * @param memberId
     * @return
     */
    public BodyProfile findRecentBodyProfile(int memberId) {
        List<BodyProfile> profiles = bodyProfileDAO.findById(memberId);
        return profiles.get(0);
    }

    public boolean updateBodyProfile(BodyProfile bodyProfile) {
        return bodyProfileDAO.update(bodyProfile) == 1;
    }
}
