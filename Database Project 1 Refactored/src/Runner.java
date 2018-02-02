import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Runner 
{

	public static void main(String[] args) throws IOException 
	{
		String fileName = "file.dat";
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		Scanner reader = new Scanner(System.in);
		String userInput;
		int entryNum;
		int editTime;
		int editName;
		int editAge;
		
		System.out.println("Please enter 1 to append the file, or enter 2 to edit the file.");
		int userDecision = reader.nextInt();
		
		if(userDecision == 1)
		{
			System.out.println("Please enter the time you wish to add to the file.");
			reader.nextLine();
			userInput = reader.nextLine();
			FileAppender fp = new FileAppender("file.dat");
			fp.appendTime(userInput);
			
			System.out.println("Please enter the name you wish to add to the file");
			userInput = reader.nextLine();
			fp.appendName(userInput);
			
			System.out.println("Please enter the Age you wish to add to the file");
			userInput = reader.nextLine();
			fp.appendAge(userInput);
		}
		
		if(userDecision == 2)
		{
			FileEditor fe = new FileEditor("file.dat");
			System.out.println("Please enter the number of the entry you wish to edit.");
			entryNum = reader.nextInt();
			if(entryNum >= 1)
			{
				System.out.println("Do you wish to edit the time of that entry?( 1 = Yes, 2 = No)");
				editTime = reader.nextInt();
				if(editTime == 1)
				{
					reader.nextLine();
					System.out.println("Please enter the updated time.");
					String updatedTime = reader.nextLine();
					fe.editTime(entryNum, updatedTime );
				}
				
				System.out.println("Do you wish to edit the name of that entry? (1 = Yes, 2 = No");
				editName= reader.nextInt();
				if(editName == 1)
				{
					reader.nextLine();
					System.out.println("Please enter the the updated name");
					String updatedName = reader.nextLine();
					fe.editName(entryNum, updatedName);
				}
				
				System.out.println("Do you wish to edit the age of that entry? (1 = Yes, 2 = No");
				editAge= reader.nextInt();
				if(editAge == 1)
				{
					reader.nextLine();
					System.out.println("Please enter the the updated age");
					String updatedAge = reader.nextLine();
					fe.editAge(entryNum, updatedAge);
				}
			}
			else
			{
				System.out.println("Sorry, that entry is not able to be edited.");
			}
		reader.close();
		scanner.close();
		}
	}
}
