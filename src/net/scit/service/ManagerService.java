package net.scit.service;

import net.scit.dao.ManagerDAO;
import net.scit.dao.MemberDAO;
import net.scit.vo.Manager;
import net.scit.vo.Member;

import java.util.List;
import java.util.Optional;


/**
 * 매니저 Service
 *
 */
public class ManagerService {
    private ManagerDAO managerDAO = new ManagerDAO();

    /**
     * 레벨에 맞는 매니저 조회
     * @param level
     * @return
     */
    public List<Manager> findManagersByLevel(int level) {
        return managerDAO.findByLevel(level);
    }

    /**
     * 전체 매니저 조회
     * @return
     */
    public List<Manager> findAllManagers() {
        return managerDAO.selectAllInfluenceLevel();
    }

    public boolean hasManagers() {
        return !findAllManagers().isEmpty();
    }
}
