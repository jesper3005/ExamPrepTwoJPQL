/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Semester;
import facade.StudentFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jesper
 */
public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        StudentFacade facade = new StudentFacade(emf);
        
        //Opgave 1
        //System.out.println(facade.getAllStudents());
        
        
        //Opgave 2
        //System.out.println(facade.getStudentByFirstName("Anders"));
        
        
        //Opgave 3
        //System.out.println(facade.createStudent("Jesper", "Christensen"));
        
        //Opgave 4
        //System.out.println(facade.assignSemester("Tina", "Christensen", 2l));
        
        //Opgave 5
        //System.out.println(facade.getAllStudentsByLastName("and"));
        
        //Opgave 6
        //System.out.println(facade.studentCount("CLdat-a14e"));
        
        //Opgave 7
        //System.out.println(facade.getAllStudentsFromAllSemester());
        
        //Opgave 8
        //System.out.println(facade.getTeacherWithMostSemsters());
        
        //Opgave 9
        //System.out.println(facade.getStudentInfo());
        
        //Opgave 10
        //System.out.println(facade.getSingleStudentInfo(2));
        
    }

}
