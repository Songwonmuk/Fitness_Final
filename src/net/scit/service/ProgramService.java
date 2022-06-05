package net.scit.service;

import net.scit.dao.ProgramDAO;
import net.scit.vo.Program;

import java.util.List;
import java.util.Optional;


/**
 * ���α׷� Service
 *
 */
public class ProgramService {
    private static final int SUCCESS = 1;
    private ProgramDAO programDAO = new ProgramDAO();


    /**
     * ȸ�� id�� �ش��ϴ� ���α׷� ��ȸ
     * @param memberId
     * @return
     */
    public Optional<Program> findProgramByMemberId(int memberId) {
        return Optional.ofNullable(programDAO.findByMemberID(memberId));
    }

    /**
     * ��ü ���α׷� ��ȸ
     * @return
     */
    public List<Program> findAllPrograms() {
        return programDAO.programList();
    }

    public Program registerProgram(Program program) {
        programDAO.addProgram(program);
        return programDAO.findByProgramName(program.getProgramName());
    }

    public boolean updateProgram(Program program) {
        return programDAO.insertProgram(program) == 1;
    }
}
