import java.io.*;

/**
* fiDamCSV is a utility class used for repairing line breaks and replacing commas in CSV files
* It generates a repaired CSV file
*
* @author Jeremy du Plessis
**/
public class fixDamCSV
{
	public static void main(String [] args) throws FileNotFoundException 
	{
		String path = "../data/Dam_Levels_Individual_Nov2015-Mar2016.csv";
		String[] toFile = new String[212];
		int count = 0;
		
		try (BufferedReader buff = new BufferedReader(new FileReader(path)))
		{
			String line = "";
			String head = fixHeader(buff.readLine());
			toFile[count] = head;
			count++;

			while ((line = buff.readLine()) != null)
			{   
				if(line.indexOf("\"") != -1)
				{
					String lineAdd = buff.readLine();
					toFile[count] = fixLineBreak(line, lineAdd);
					count++;
				}
				
				else
				{
					toFile[count]=line;
					count++;
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}

		PrintWriter pw = new PrintWriter(new File("Dam_Data_Fixed.csv"));
		StringBuilder sb = new StringBuilder();
        
		for(int i=0 ; i<toFile.length ; i++)
                {
                        sb.append(toFile[i]+"\n");         
                }
		
		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");
	}
	
	/**
	* fixHeader is a helper function to replace commas with apostraphes in a line of a CSV file
	* 
	* @param line 	a String, the line of the CSV file to be repaired
	* @return 	 	a String, the repaired line of data				
	**/
	public static String fixHeader(String line)
	{
        	String [] ls = line.split("\"");
        	String s = ls[0] + ls[1].replaceAll(",","'") + ls[2];

        	return s;
	}

	/**
	* fixLineBreak is a helper function to fix line breaks in CSV files
	* 
	* @param l1 	a String, the first part of line of the CSV file to be concatenated
	* @param l2 	a String, the second part of the line from the CSV file to be concatenated
	* @return 	 	a String, the repaired line of data				
	**/
	public static String fixLineBreak(String l1, String l2)
	{
        	String[] arr1 = l1.split("\"");
        	String[] arr2 = l2.split("\"");
        
        	String s = arr1[0] + arr1[1] + arr2[0] + arr2[1];
        	return s;  
	}
}