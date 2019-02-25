/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

/**
 *
 * @author Jesper
 */
public class StudentInfo {

    public String fullName;
    public long studentId;
    public String classNameThisSemester;
    public String classDescription;

    public StudentInfo(String firstName, String lastName) {
        this.fullName = firstName + " " + lastName;
    }
    
}
