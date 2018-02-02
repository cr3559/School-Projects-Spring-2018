import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class FileAppender 
{
	String fileName;
	File file;
	Scanner scanner;
	Scanner reader;
	RandomAccessFile writer;
	
	
	int maxTimeLength = 5;
	int maxNameLength = 10;
	int maxAgeLength = 3;
	long fileSize;

	
	public FileAppender(String fileName) throws FileNotFoundException
	{
		this.fileName = fileName;
		this.file = new File(fileName);
		this.scanner = new Scanner(file);
		this.reader = new Scanner(System.in);
		this.writer = new RandomAccessFile(file,"rw");
		this.fileSize = file.length();
		fileSize = file.length();
		
	}
	
	public void appendTime(String inputTime) throws IOException
	{
			if(inputTime.length() < maxTimeLength)
			{
				writer.seek(fileSize);
				writer.writeBytes("\n0"+ inputTime);
			}
			else if (inputTime.length() > maxTimeLength)
			{
				writer.seek(fileSize);
				writer.writeBytes("\n" + inputTime.substring(0, 5));
			}
			else
			{
				writer.seek(fileSize);
				writer.writeBytes("\n" + inputTime);
			}
		
		
	}
	public void appendName(String inputName) throws IOException
	{
		if(inputName.length() < maxNameLength)
		{
			long nameDif = maxNameLength - inputName.length();
			writer.seek(fileSize + maxTimeLength + 1);
			writer.writeBytes(inputName);
			
			for(int i = 0; i < nameDif; i++)
			{
				writer.seek(fileSize + maxTimeLength +inputName.length() + 1 + i );
				writer.writeBytes(" ");
			}
		}
		else if(inputName.length() > maxNameLength)
		{
			writer.seek(fileSize + maxTimeLength + 1);
			writer.writeBytes(inputName.substring(0, 10));
		}
		
		else
		{
			writer.seek(fileSize + maxTimeLength + 1);
			writer.writeBytes(inputName);
		}
	}
	public void appendAge(String inputAge) throws IOException
	{
		if(inputAge.length() < maxAgeLength)
		{
			long ageDif = maxAgeLength - inputAge.length();
			writer.seek(fileSize + maxTimeLength + maxNameLength + 1);
			writer.writeBytes(inputAge);
			
			for( int i = 0; i < ageDif; i++)
			{
				writer.seek(fileSize + maxTimeLength +maxNameLength + inputAge.length() + 1 + i);
				writer.writeBytes(" ");
			}
		}
		else if (inputAge.length() > maxAgeLength)
		{
			writer.seek(fileSize + maxTimeLength + maxNameLength + 1);
			writer.writeBytes(inputAge.substring(0, 3));
		}
		
		else
		{
			writer.seek(fileSize + maxTimeLength +maxNameLength + 1);
			writer.writeBytes(inputAge);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
