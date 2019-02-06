import java.io.*;

/**
* dataFrame is a utility class used for extracting elements from a CSV file,
* creating an n x m (max dimensions) empty String array, splitting the rows
* by the given parameter and storing each element in it's repective position.
*
* The dataFrame class has methods for returning individual values based on 
* index, dimensions of rows and columns.
*
* @author Jeremy du Plessis
**/

public class dataFrame
{
    private String [][] dataArray;
    private String filePath;
    private String splitBy;

    /**
    *  The fiDamCSV constructor. 
    *
    * @param String filePath
    * @param String splitBy 
    **/
    public dataFrame(String filePath, String splitBy)
    {
        this.filePath = filePath;
        this.splitBy = splitBy;
        this.dataArray = make(filePath, splitBy);
    }
    
    /**
    * The make method locates the CSV file in the given directory, extracts 
    * the data and stores it in an m x n array.
    *
    * @param String filePath
    * @param String splitBy 
    **/
    public String [][] make(String filePath, String splitBy)
    {
        int [] dimensions = getDimensions(filePath, splitBy);
        String [][] tempArr = new String[dimensions[0]][dimensions[1]]; // rows, cols
        
        try (BufferedReader buff = new BufferedReader(new FileReader(filePath)))
        {
            int count = 0;
            String line = "";
            
            while((line=buff.readLine()) != null) 
            {  
                String[] lineArr = line.split(",");
                
                for(int j=0; j < lineArr.length ; j++)
                {   
                    tempArr[count][j] = lineArr[j];
                }
                //System.out.println(count + " " + tempArr[count][0] + " " + tempArr[count][1] + " " + tempArr[count][2]);
                count++;            
            } 
        
        } catch(IOException e){
            e.printStackTrace();
        }
        return tempArr;
    }
    
    /**
    *  The getDimensions mehtod returns the maximum dimensions of the CSV files based on
    *  numRows and numCols. 
    *
    * @param String filePath
    * @param String splitBy 
    **/
    public int[] getDimensions(String filePath, String splitBy)
    {
        int[] dims = new int[2];
        
        try (BufferedReader buff = new BufferedReader(new FileReader(filePath)))
        {
            String line = "";
            int cols = ((buff.readLine()).split(",")).length;
            int rows = 1;
               
            while ((line = buff.readLine()) != null)
            {   
                rows++;
            }
            dims[0] = rows;
            dims[1] = cols; 
            
        } catch(IOException e){
            e.printStackTrace();
        }   
        return dims; 
    }
    
    /**
    *  The getColIndex method returns the index of a given column name in the data frame.
    *
    * @param String col
    **/    
    public int getColIndex(String col)
    {
        int index = -1;
        
        for(int j = 0 ; j < numCols() ; j++)
        {
            if(col.equals(dataArray[0][j]))
            {
                index = j;
            }
        }
        return index;
    }
    
    /**
    * The numCols method returns the number of columns required for constructing the array,
    * based on the number of header entries in the CSV file.
    *
    * @return int
    **/    
    public int numCols()
    {
        return dataArray[0].length;    
    }
    
    /**
    * The numRows method returns the number of rows required for constructing the array,
    * based on the number of row entries in the CSV file.
    *
    * @return int 
    **/ 
    public int numRows()
    {
        return dataArray.length;
    }
    
    /**
    * The getVal mehtod returns a value in the given row and column name.
    *
    * @return String
    **/ 
    public String getVal(int row, String colName)
    {
        return dataArray[row][getColIndex(colName)];
    }
}