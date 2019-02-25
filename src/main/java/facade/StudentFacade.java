/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Semester;
import entity.Student;
import entity.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import mapper.StudentInfo;

/**
 *
 * @author Jesper
 */
public class StudentFacade {

    EntityManagerFactory emf;

    public StudentFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Student> getAllStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createNamedQuery("Student.findAll");
            List<Student> students = q.getResultList();
            return students;
        } finally {
            em.close();
        }
    }

    public List<Student> getStudentByFirstName(String firstName) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createNamedQuery("Student.findByFirstname");
            q.setParameter("firstname", firstName);
            List<Student> students = q.getResultList();
            return students;
        } finally {
            em.close();
        }
    }

    public Student createStudent(String lastName, String firstName) {
        EntityManager em = emf.createEntityManager();
        Student st1 = new Student(lastName, firstName);

        try {
            em.getTransaction().begin();
            em.persist(st1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return st1;
    }

    public Student assignSemester(String firstName, String lastName, Long semesterID) {
        EntityManager em = emf.createEntityManager();
        Student st1 = new Student(firstName, lastName);
        Semester sm = em.find(Semester.class, semesterID);
        st1.setCurrentsemesterId(sm);
        try {
            em.getTransaction().begin();
            em.persist(st1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return st1;
    }

    public List<Student> getAllStudentsByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createNamedQuery("Student.findByLastname");
            q.setParameter("lastname", lastName);
            List<Student> students = q.getResultList();
            return students;
        } finally {
            em.close();
        }
    }

    public long studentCountBad(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createNamedQuery("Student.findBySemesterId");
            q.setParameter("id", id);
            return (long) q.getResultList().size();
        } finally {
            em.close();
        }
    }

    public long studentCount(String semesterName) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT COUNT(s) FROM Student s WHERE s.currentsemesterId.name =:name");
            q.setParameter("name", semesterName);
            return (long) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Long getAllStudentsFromAllSemester() {
        EntityManager em = emf.createEntityManager();

        try {
            Query q = em.createQuery("SELECT COUNT(s) FROM Student s WHERE s.currentsemesterId.id > :count");
            q.setParameter("count", 0);
            return (long) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Teacher getTeacherWithMostSemsters() {
        EntityManager em = emf.createEntityManager();
        
        try {
            return (Teacher) em.createQuery("SELECT t FROM Teacher t LEFT JOIN t.semesterCollection as s GROUP BY t.id ORDER BY t DESC").getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
    public List<StudentInfo> getStudentInfo() {
        EntityManager em = emf.createEntityManager();
        List<Student> students;
        try {
            Query q = em.createNamedQuery("Student.findAll");
            students = q.getResultList();

        } finally {

            em.close();
        }

        List<StudentInfo> getName = new ArrayList();
        for (int i = 0; i < students.size(); i++) {
            getName.add(new StudentInfo(students.get(i).getFirstname(), students.get(i).getLastname()));
        }
        return getName;
    }

    public StudentInfo getSingleStudentInfo(long id) {
        EntityManager em = emf.createEntityManager();
        Student student;
        try {
            Query q = em.createNamedQuery("Student.findById");
            q.setParameter("id", id);
            student = (Student) q.getSingleResult();
        } finally {
            em.close();
        }
        
        StudentInfo si = new StudentInfo(student.getFirstname(), student.getLastname());
        return si;
    }
    
}
