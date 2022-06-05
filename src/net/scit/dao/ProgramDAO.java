package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.Program;

public class ProgramDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	public List<Program> programList() {
		SqlSession session = factory.openSession();

		ProgramMapper mapper = session.getMapper(ProgramMapper.class);

		List<Program> listAll = mapper.programList();

		return listAll;
	}

	public int totalPrograms() {
		SqlSession session = factory.openSession();

		ProgramMapper mapper = session.getMapper(ProgramMapper.class);

		int result = mapper.totalPrograms();

		return result;
	}

	public int addProgram(Program program) {
		SqlSession session = factory.openSession();

		ProgramMapper mapper = session.getMapper(ProgramMapper.class);

		int result = mapper.addProgram(program);

		session.commit();

		return result;

	}

	public Program findByProgramID(int programID) {
		SqlSession session = factory.openSession();

		ProgramMapper mapper = session.getMapper(ProgramMapper.class);

		Program program = mapper.findByProgramID(programID);

		return program;

	}

	public int deleteProgram(Program program) {
		SqlSession session = factory.openSession();

		ProgramMapper mapper = session.getMapper(ProgramMapper.class);

		int result = mapper.deleteProgram(program);

		session.commit();

		return result;
	}
	
	public Program findByProgramName(String programName) {
		SqlSession session = factory.openSession();

		ProgramMapper mapper = session.getMapper(ProgramMapper.class);

		Program foundProgram = mapper.findByProgramName(programName);

		return foundProgram;		
	}

	public int insertProgram(Program program) {
		SqlSession session = factory.openSession();

		ProgramMapper mapper = session.getMapper(ProgramMapper.class);

		int result = mapper.insertProgram(program);

		session.commit();

		return result;
		
	}

	public Program findByMemberID(int memberId) {
		SqlSession session = factory.openSession();

		ProgramMapper mapper = session.getMapper(ProgramMapper.class);

		Program foundProgram = mapper.findByMemberID(memberId);

		return foundProgram;		
	}

}
