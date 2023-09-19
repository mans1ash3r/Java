/**
 * Mansi Asher
 * 
 * Project 5
 * 
 * The STudentRecord class represents a GUI application for searching and sorting student records.
 * It extends the JFrame class and contains panels for displaying the student records.
 * 
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class StudentRecord extends JFrame {
	
	private JPanel upperPanel,middlePanel,lowerPanel;
	private JTextField studentId, indivisualRecord;
	private JTextArea allStudentRecord;
	private JScrollPane recordScrollPane;
	private JButton loadData,sortRecords,findStudent;
	private Student[] studentArray;
	
	/**
	 * The constructor sets the title of the frame 
	 * and calls the method to construct the display.
	 */
	public StudentRecord() {
		setTitle("Student Searcher and Sorter\r\n");
		constructDisplay();	
	}
	
	/**
	 * This method sets up the layout of the container and adds the necessary components.
	 */
	private void constructDisplay() {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		setSize(600, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout());
		upperPanel.setBorder(new TitledBorder("Control Panel"));
		
		loadData = new JButton("Load Data");
		loadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentArray = Student.readFromFile("MOCK_DATA.csv");
	            displayAllRecords();
			}
		});
		
		upperPanel.add(loadData);		
		
		sortRecords = new JButton("Sort Records");
		sortRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student.sort(studentArray);
	            displayAllRecords();
			}
		});
		
		upperPanel.add(sortRecords);
		
		findStudent = new JButton("Find Student by ID");
		findStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idString = studentId.getText();
	            int id = Integer.parseInt(idString);
	            int index = Student.search(studentArray, new Student(id,"","",0));
	            indivisualRecord.setText(studentArray[index].toString() + ", Insex: " + index);
			}
		});
		
		upperPanel.add(findStudent);

		studentId = new JTextField(15);
		
		upperPanel.add(studentId);
		
		middlePanel = new JPanel();
		middlePanel.setLayout(new FlowLayout());
		middlePanel.setBorder(new TitledBorder("Individual Record"));
		
		indivisualRecord = new JTextField(50);	
		indivisualRecord.setEditable(false);
		middlePanel.add(indivisualRecord);	
		
		lowerPanel = new JPanel(new BorderLayout());
        lowerPanel.setBorder(new TitledBorder("All Student Records"));
      
        allStudentRecord = new JTextArea(31, 50);
        allStudentRecord.setEditable(false);
        recordScrollPane = new JScrollPane(allStudentRecord);
        lowerPanel.add(recordScrollPane, BorderLayout.CENTER);
			
		container.add(upperPanel, BorderLayout.NORTH);
		container.add(middlePanel, BorderLayout.CENTER);
		container.add(lowerPanel, BorderLayout.SOUTH);
		
	}
	
	/**
	 * The method appends each student's details to the JTextArea.
	 */
	private void displayAllRecords() {
		allStudentRecord.setText("");
	  for (Student student : studentArray) {
		  allStudentRecord.append(student.toString() + "\n");
	  }
	}
	
	/**
	 * The main() method creates an instance of the StudentRecord class and sets its visibility to true.
	 * @param args
	 */
	public static void main(String[] args) {
		StudentRecord studentRecord = new StudentRecord();
		studentRecord.setVisible(true);
	}

}
