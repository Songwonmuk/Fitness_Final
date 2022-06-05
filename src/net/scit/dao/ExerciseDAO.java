package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.Exercise;

public class ExerciseDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	public List<Exercise> ExerciseList() {
		SqlSession session = factory.openSession();

		ExerciseMapper mapper = session.getMapper(ExerciseMapper.class);

		List<Exercise> listAll = mapper.ExerciseList();
		
		return listAll;	
	}

	public int totalExercises() {
		SqlSession session = factory.openSession();

		ExerciseMapper mapper = session.getMapper(ExerciseMapper.class);
		
		int result = mapper.totalExercises();
		
		session.commit();
		
		return result;
	}

	public int addExercise(Exercise exercise) {
		SqlSession session = factory.openSession();

		ExerciseMapper mapper = session.getMapper(ExerciseMapper.class);

		int result = mapper.addExercise(exercise);

		session.commit();

		return result;
		
	}

	public Exercise findByName(String ExerName) {
		SqlSession session = factory.openSession();

		ExerciseMapper mapper = session.getMapper(ExerciseMapper.class);

		Exercise exercise = mapper.findByName(ExerName);
		
		return exercise;	
	}

	public int updateExercise(Exercise exercise) {
		SqlSession session = factory.openSession();

		ExerciseMapper mapper = session.getMapper(ExerciseMapper.class);

		int result = mapper.updateExercise(exercise);

		session.commit();

		return result;
		
	}

	public int deleteExercise(Exercise exercise) {
		SqlSession session = factory.openSession();

		ExerciseMapper mapper = session.getMapper(ExerciseMapper.class);

		int result = mapper.deleteExercise(exercise);

		session.commit();

		return result;
		
	}

	public int updateProgramID(Exercise exercise) {
		SqlSession session = factory.openSession();

		ExerciseMapper mapper = session.getMapper(ExerciseMapper.class);

		int result = mapper.updateProgramID(exercise);

		session.commit();

		return result;
		
	}

	public List<Exercise> findByProgramID(int programID) {
		SqlSession session = factory.openSession();

		ExerciseMapper mapper = session.getMapper(ExerciseMapper.class);

		List<Exercise> exercise = mapper.findByProgramID(programID);

		return exercise;
		
	}

	public int whenDeleteIdUpdate(int exerNum) {
		SqlSession session = factory.openSession();

		ExerciseMapper mapper = session.getMapper(ExerciseMapper.class);

		int result = mapper.whenDeleteIdUpdate(exerNum);

		session.commit();

		return result;
	}

}
