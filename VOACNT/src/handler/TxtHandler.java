package handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TxtHandler
{
	File file;
	public TxtHandler(String path)
	{
		// TODO Auto-generated constructor stub
		file = new File(path);
	}
	
	public int getFileLength()
	{
		int length = 0;
		try
		{
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			while (bufferedReader.readLine() != null)
			{
				length++;
			}
			bufferedReader.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return length;
	}
	
	public String[] getFile()
	{
		String fileContent[] = new String[getFileLength()];
		try
		{
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String tempString = null;
			do
			{
				tempString = bufferedReader.readLine();
			} while (tempString!=null);
			bufferedReader.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileContent;
	}
}
