

import java.util.List;
import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

//TODO Update to work with updated data structures when changes are made
public class CSVPort
{
	static public Class_t importClass(Class_t classObj, String filePath) throws IOException
	{
		List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		lines.remove(0);
		for (String line : lines)
		{
			String[] cols = line.split(",");
			Student stu = new Student(cols[1], cols[0], cols[2], cols[3]);
			int score = Integer.parseInt(cols[6]); // can't do anything with this yet
			classObj.addStudent(stu);
		}
		return classObj;
	}
	
	static public void exportClass(Class_t classObj, String filePath) throws FileNotFoundException
	{
		Vector<Student> students = classObj.getStudents();
		String fileText = "Last Name,First Name,Username,Student ID,Availability,Weighted Total [Total Pts: up to 0],Total [Total Pts: up to 100],Test 1 [Total Pts: 100]\n";
		for (Student student : students)
		{
			String score = Integer.toString(100); // int score = Integer.toString(student.getScore());
			
			fileText += student.getLastname() + ","; //write last name column
			fileText += student.getFirstname() + ","; // write first name column
			fileText += student.getUsername() + ","; // write username column
			fileText += student.getCUID() + ","; // write CUID column
			fileText += "Yes,,"; // write unused columns
			fileText += score + "," + score + "\n"; // write the score
		}
		
		String[] lines = fileText.split("\n");
		PrintWriter out = new PrintWriter(filePath);
		for (String line : lines)
			out.println(line);
		out.close();
	}
}
