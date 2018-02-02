import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class FileEditor 
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

	
	public FileEditor(String fileName) throws FileNotFoundException
	{
		this.fileName = fileName;
		this.file = new File(fileName);
		this.scanner = new Scanner(file);
		this.reader = new Scanner(System.in);
		this.writer = new RandomAccessFile(file,"rw");
		this.fileSize = file.length();
		fileSize = file.length();
		
	}
	
	public void editTime(long entry, String updatedTime) throws IOException
	{
		if(updatedTime.length() < maxTimeLength)
		{
			writer.seek(entry * 18);
			writer.writeBytes("\n0"+ updatedTime);
		}
		else if (updatedTime.length() > maxTimeLength)
		{
			writer.seek(entry * 18);
			writer.writeBytes("\n" + updatedTime.substring(0, 5));
		}
		else
		{
			writer.seek(entry* 18);
			writer.writeBytes("\n" +updatedTime);
		}
	}

	public void editName(long entryNum, String updatedName) throws IOException 
	{
		
			long editLocation =(entryNum * 18) + maxTimeLength + 1;
			if(updatedName.length() < maxNameLength)
			{
				long nameDif = maxNameLength - updatedName.length();
				writer.seek(editLocation);
				writer.writeBytes(updatedName);
				
				for(int i = 0; i < nameDif; i++)
				{
					writer.seek(editLocation + updatedName.length()  + i );
					writer.writeBytes(" ");
				}
			}
			else if(updatedName.length() > maxNameLength)
			{
				writer.seek(editLocation);
				writer.writeBytes(updatedName.substring(0, 10));
			}
			
			else
			{
				writer.seek(editLocation);
				writer.writeBytes(updatedName);
			}
		
		
	}

	public void editAge(int entryNum, String updatedAge) throws IOException 
	{
		long editLocation = (entryNum * 18) + maxTimeLength +maxNameLength +1;
		if(updatedAge.length() < maxAgeLength)
		{
			long ageDif = maxAgeLength - updatedAge.length();
			writer.seek(editLocation);
			writer.writeBytes(updatedAge);
			
			for( int i = 0; i < ageDif; i++)
			{
				writer.seek(editLocation+ updatedAge.length() + i);
				writer.writeBytes(" ");
			}
		}
		else if (updatedAge.length() > maxAgeLength)
		{
			writer.seek(editLocation);
			writer.writeBytes(updatedAge.substring(0, 3));
		}
		
		else
		{
			writer.seek(editLocation);
			writer.writeBytes(updatedAge);
		}
		
	}
}
