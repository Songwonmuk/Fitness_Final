package net.scit.dao;

import java.util.List;

import net.scit.vo.Program;

public interface ProgramMapper {

	List<Program> programList();

	int totalPrograms();

	int addProgram(Program program);

	Program findByProgramID(int programID);

	int deleteProgram(Program program);

	int updateProgram(Program program);

	Program findByProgramName(String programName);

	int insertProgram(Program program);

	Program findByMemberID(int memberId);
}
