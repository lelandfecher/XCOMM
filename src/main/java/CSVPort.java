package main.java;

import java.util.List;
import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/* 
 * 
 */
public class CSVPort
{
	static public Class_t importClass(Class_t classObj, String filePath) throws IOException
	{
		List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.US_ASCII);
		for (String line : lines)
		{
			String[] cols = line.split(",");
			classObj.addStudent(cols[1] + " " + cols[0]);
		}
		return classObj;
	}
	
	static public void exportClass(Class_t classObj, String filePath) throws FileNotFoundException
	{
		Vector<Student> students = classObj.getStudents();
		String fileText = "Last Name,First Name,Username,Student ID,Availability,Weighted Total [Total Pts: up to 0],Total [Total Pts: up to 100],Test 1 [Total Pts: 100]\n";
		for (Student student : students)
		{
			fileText += student.getName().split(" ")[1];
			fileText += ",";
			fileText += student.getName().split(" ")[0];
			fileText += ",NULL,NULL,Yes,,100,100\n";
		}
		
		String[] lines = fileText.split("\n");
		PrintWriter out = new PrintWriter(filePath);
		for (String line : lines)
			out.println(line);
	}
}
