/**
 * Mansi Asher
 * 
 * Project 5
 * 
 * The Student class represents a student, which has an id, a first name, a last name, and a grade
 * and is used for storing student record.
 * 
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Student implements Comparable<Student> {
	
	private String firstName,lastName;
	private int id,grade;
	
	/**
	 * This method creates a new Student object with the specified id, first name, last name, and grade.
	 * @param id - Student's id
	 * @param firstName - Student's first name
	 * @param lastName - Student's last name
	 * @param grade - Student's grade
	 */
	public Student(int id, String firstName, String lastName,int grade) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade= grade;
	}

	/**
	 * This method compares this student to another student based on their grades.
	 */
	@Override
	public int compareTo(Student s) {
		if(this.id == s.id) {
			return 0;
		}else if (this.id > s.id) {
			return 1;
		}else {
			return -1;
		}
		
	}
	
	/**
	 * This method compares the id instance property only.
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Student)) {
			return false;
		}

		Student s = (Student) o;

		return  id == s.id;
	}
	
	/**
	 * This method reads the data from a file and returns an array of student records.
	 * @param filePath
	 * @return 0 if the grades are equal, 1 if this grade is higher, -1 if this grade is lower.
	 */
	public static Student[] readFromFile(String filePath) {
		ArrayList<Student> studentArray = new ArrayList<Student>();
		Scanner in = null;
		try {
			in = new Scanner(new File(filePath));
			while (in.hasNext()) {
				String[] carData = in.nextLine().split(",");
				studentArray.add(
						new Student(Integer.parseInt(carData[0]), carData[1], carData[2], Integer.parseInt(carData[3])));
			}
		} catch (Exception e) {
			return null;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				return null;
			}
		}
		Student[] students = new Student[studentArray.size()];
		for (int i = 0; i < studentArray.size(); i++) {
			students[i] = studentArray.get(i);
		}
		return students;
	}
	
	/**
	 * This method returns a String in the following format: “id, firstName, lastName, grade”.
	 */
	@Override
	public String toString() {
		return String.format("%d, %s, %s, %d", id, firstName, lastName,grade);
	}
	
	/**
	 * This method sorts student grades from highest to lowest.
	 * @param students
	 */
	public static void sort(Student[] students) {
		for (int i = 1; i < students.length; ++i) {
			Student temp = students[i];
			int j = i - 1;
			while (j >= 0 && students[j].compareTo(temp) > 0) {
				students[j + 1] = students[j];
				j = j - 1;
			}
			students[j + 1] = temp;
		}
	}
	
	/**
	 * This method searches an array of students for a particular student.
	 * @param students
	 * @param s
	 * @return the index of the student  as an int, or -1 if the student was not found.
	 */
	public static int search(Student[] students, Student s) {
		for (int i = 0; i < students.length; i++) {
			if (students[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the first name of the student.
	 * 
	 * @return The first name of the student.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the student.
	 * 
	 * @param firstName The first name of the student
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the student.
	 * 
	 * @return The last name of the student.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the student.
	 * 
	 * @param lastName The last name of the student.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the id of the student.
	 * 
	 * @return The id of the student.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the student.
	 * 
	 * @param id The id of the student.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the grade of the student.
	 * 
	 * @return The grade of the student.
	 */
	public int getGrade() {
		return grade;
	}

	/**
	 * Sets the grade of the student.
	 * 
	 * @param grade The grade of the student.
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
