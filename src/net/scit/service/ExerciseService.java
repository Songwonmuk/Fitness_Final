package net.scit.service;

import net.scit.dao.ExerciseDAO;
import net.scit.vo.BodyProfile;
import net.scit.vo.Exercise;

import java.util.List;
import java.util.Optional;


/**
 * ¿îµ¿ Service
 *
 */
public class ExerciseService {
    private ExerciseDAO exerciseDAO = new ExerciseDAO();


    public Optional<Exercise> findExerciseByName(String name) {
        return Optional.ofNullable(exerciseDAO.findByName(name));
    }

    public void updateProgramID(Exercise exercise, int programId) {
        exercise.setProgramID(programId);
        exerciseDAO.updateProgramID(exercise);
    }
}
