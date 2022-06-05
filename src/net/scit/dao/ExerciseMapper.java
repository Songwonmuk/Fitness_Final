package net.scit.dao;

import java.util.List;

import net.scit.vo.Exercise;

public interface ExerciseMapper {

	List<Exercise> ExerciseList();

	int totalExercises();

	int addExercise(Exercise exercise);

	Exercise findByName(String exerName);

	int updateExercise(Exercise exercise);

	int deleteExercise(Exercise exercise);


	int updateProgramID(Exercise exercise);

	List<Exercise> findByProgramID(int programID);

	int whenDeleteIdUpdate(int exerNum);
	
}
