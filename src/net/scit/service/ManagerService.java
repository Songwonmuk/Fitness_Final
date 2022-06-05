package net.scit.service;

import net.scit.dao.ManagerDAO;
import net.scit.dao.MemberDAO;
import net.scit.vo.Manager;
import net.scit.vo.Member;

import java.util.List;
import java.util.Optional;


/**
 * �Ŵ��� Service
 *
 */
public class ManagerService {
    private ManagerDAO managerDAO = new ManagerDAO();

    /**
     * ������ �´� �Ŵ��� ��ȸ
     * @param level
     * @return
     */
    public List<Manager> findManagersByLevel(int level) {
        return managerDAO.findByLevel(level);
    }

    /**
     * ��ü �Ŵ��� ��ȸ
     * @return
     */
    public List<Manager> findAllManagers() {
        return managerDAO.selectAllInfluenceLevel();
    }

    public boolean hasManagers() {
        return !findAllManagers().isEmpty();
    }
}
