package krg.petr.otusru.service;

import krg.petr.otusru.domain.Student;

public interface StudentService {

    Student determineCurrentStudent();

    Student determineCurrentStudent(String firstName, String lastName);
}
