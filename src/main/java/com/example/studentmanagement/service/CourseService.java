package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    public Course create(Course course) {
        if (courseRepository.existsByCourseNameIgnoreCase(course.getCourseName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course name already exists");
        }
        return courseRepository.save(course);
    }

    public Course update(Long id, Course request) {
        Course course = findById(id);
        course.setCourseName(request.getCourseName());
        course.setDescription(request.getDescription());
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.delete(findById(id));
    }
}
