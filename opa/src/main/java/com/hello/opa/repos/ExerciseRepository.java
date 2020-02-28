package com.hello.opa.repos;

import org.springframework.data.repository.CrudRepository;

import com.hello.opa.domain.Exercise;


public interface ExerciseRepository extends CrudRepository <Exercise, Integer> {

}
