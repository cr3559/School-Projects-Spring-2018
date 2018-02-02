import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) throws IOException 
	{
		String fileName = "project1.txt";
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		Scanner reader = new Scanner(System.in);
		RandomAccessFile writer = new RandomAccessFile(file, "rw");
		long fileSize = file.length();
		String appendedString;
		
		
		System.out.println("Do you wish add to the file?(1 for yes, 2 for no)");
		int selectionInput = reader.nextInt();
		if(selectionInput == 1)
		{
				System.out.println("What kind of data are you adding to the file? (1 for Time, 2 for Name, 3 for Age)");
				int dataType = reader.nextInt();
				while(scanner.hasNextLine())
				{
					scanner.nextLine();
				}
				
				if(dataType == 1)
				{
					System.out.println("Please enter the time.");
					reader.nextLine();
					appendedString = reader.nextLine();
					int timeLength = 5;
			
					if(appendedString.length() < timeLength && appendedString.length() > 1)
					{
						writer.seek(fileSize);
						writer.writeBytes("\n" +"0" + appendedString);
					}
					else if(appendedString.length() == timeLength)
					{
						writer.seek(fileSize);
						writer.writeBytes("\n" + appendedString);
					}
					else
					{
						System.out.println("Sorry, that is not of the proper format.");
					}
				}
			
				else if(dataType == 2)
				{
					System.out.println("Please enter a Name.");
					reader.nextLine();
					appendedString = reader.nextLine();
					int nameLength = 10;
					int inputLength = appendedString.length();
					int offset = (nameLength - inputLength);
					long lastInputLocation;
				
					if(appendedString.length() <= nameLength && appendedString.length() > 1)
					{
						writer.seek(fileSize);
						writer.writeBytes("\n" + appendedString);
						lastInputLocation = (fileSize + appendedString.length() + 1);
						writer.seek(lastInputLocation);
						for(int i = 0; i < offset; i++)
						{
							
							writer.seek(lastInputLocation + i);
							writer.writeBytes(" ");
						}
						writer.writeBytes("\nTesting");
					}
				}
					
					else if(dataType == 3)
					{
						System.out.println("Please enter an Age.");
						reader.nextLine();
						appendedString = reader.nextLine();
						int allowedAgeLength = 3;
						int ageInputLength = appendedString.length();
						int ageOffset = (allowedAgeLength - ageInputLength);
						long lastAgeInputLocation;
					
						if(ageInputLength<= allowedAgeLength && appendedString.length() >= 1)
						{
							writer.seek(fileSize);
							writer.writeBytes("\n" + appendedString);
							lastAgeInputLocation = (fileSize + appendedString.length() + 1);
							writer.seek(lastAgeInputLocation);
							
							for(int i = 0; i < ageOffset; i++)
							{
								writer.seek(lastAgeInputLocation + i);
								writer.writeBytes(".");
							}
							writer.writeBytes("\nTesting");
						}
						else
						{
							System.out.println("Sorry, please input an age up to three digits in length.");
						}
					}
				}
		scanner.close();
		reader.close();
		writer.close();
		}
}

