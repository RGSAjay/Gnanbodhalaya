package com.gnanbodhalaya.onlinelearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gnanbodhalaya.onlinelearning.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
