import java.io.*;
import java.util.*;

/**
* The damBSTApp class has a dataFrame populated with dam information, from which
* it constructs Dam objects to be stored in an binary search tree (BST), comprised of 
* nodes.
*
* Methods included provide utility for adding Dam objects to the BST, searching
* for individual dams by name, printing all Dams stored in the BST, as well as
* running a time complexity test on the search and insert algorithms underpinning the 
* printDamName method.
*
* @author Jeremy du Plessis
**/
public class DamBSTApp 
{
    private dataFrame df;
    private String[] vals;
    private BinarySearchTree damBST;
    
    /**
    * The damBSTApp constructor.
    *
    * @param String filePath
    * @param String splitBy
    * @param String[] vals
    **/
    public DamBSTApp(String filePath, String splitBy, String [] vals)
    {
        this.vals = vals;
        this.df = new dataFrame(filePath, splitBy);
        this.damBST = new BinarySearchTree();
    }
    
    public static void main( String [] args)
    {
        String path = "data/Dam_Data_Fixed.csv";
        String[] vals = {"Dam Name", "FSC 1'000'000m³ (million m³)", "20160307 (percent full)"};
        String split = ",";
        
        DamBSTApp dap = new DamBSTApp(path, split, vals);

        int[] opCountInsert = new int[(dap.getDf()).numRows()];
        int[] opCountResults = new int[3];

        // load data into BST
        dap.addDams(vals, opCountInsert);

        // Get Best Average and worst cases for opCounts during insert
        Arrays.sort(opCountInsert);
        int sum = 0;
        for(int i = 0 ; i < opCountInsert.length ; i++){ 
        	sum += opCountInsert[i]; 
        }
        opCountResults[0] = opCountInsert[0]; // Best Case
        opCountResults[1] = sum/(opCountInsert.length); // Avergae Case
        opCountResults[2] = opCountInsert[opCountInsert.length-1]; // Worst Case 

        if(args.length == 0)
        {
             System.out.println(dap.printAllDams(opCountResults));
        }
        else if(args[0].equals("test"))
        {
           dap.test(split, vals);
        }
        else
        {
            String query = args[0].trim();
            System.out.println(dap.printDamName(query, opCountResults));
        }
    }
    
    /**
    * The addDams method adds Dam objects to a BST based on the specified values.
    *
    * @param String[] vals
    * @param int[] opCountInsert
    **/
    public void addDams(String[] vals, int[] opCountInsert)
    {
         // Initialise an array corresponding to the number of entried in the dataframe to measure opCounts during insertion
         int [] temp = {0};
         for(int i = 1; i < df.numRows() ; i++)
         {
            temp[0] = 0;
            damBST.insert(new Dam(df.getVal(i, vals[0]), df.getVal(i, vals[1]), df.getVal(i, vals[2])), temp);
            opCountInsert[i] = temp[0];
         }	   
    }
    
    /**
    * The printAllDams prints information on all Dam objects contained in the BST.
    * Outputs best, avergae and worst case for number of comparison operations executed while inserting data.
    *
    * @param int[] opCountResult
    * @return String out 
    **/
    public String printAllDams(int[] opCountResult)
    {
     	// print dam info 
        damBST.preOrder();
        String out = "";

        out = "\nPrinted all dam entries from binary search tree.";
        out +="\nthe size of the dataset was: "+damBST.getSize();
        out += String.format("\nThe number of insertion comparisons (best, average and worst) were: %d, %d, %d", opCountResult[0], opCountResult[1], opCountResult[2]);
        return out;
    }
    
    /**
    * The printDamName method accepts a dam name as a search query and returns a String of
    * Dam object information if the queried object if found. Else it returns a notification
    * that the query was found in the BST. The number of comparisons for search and insertion 
    * are recorded and returned in the out String variable.
    *
    * @param String query
    * @param int[] opCountResult
    * @return String out
    **/
    public String printDamName(String query, int[] opCountResult)
    {
        int[] opCountSearch = {0};
        String out = "";
        Dam d1 = new Dam(query, "0", "0");

        try {

            Dam d2 = (Dam)damBST.find(d1, opCountSearch).data;
            out = "Result: "+ String.format("\nname: %-20s \ndam level: %-10s \nFSC: %-10s", d2.getName(), d2.getLevel(), d2.getFSC());
            out += "\nthe size of the dataset was: "+damBST.getSize();
            out += String.format("\nThe number of insertion comparisons (best, average and worst) were: %d, %d, %d", opCountResult[0], opCountResult[1], opCountResult[2]);
            out += "\nThe total number of search comaprisons was: "+opCountSearch[0];
            return out;

        } catch (NullPointerException e){
            out =  "Result: \nDam not found"+"\nquery: "+query; 
            out += "\nthe size of the dataset was: "+damBST.getSize();
            out += String.format("\nThe number of insertion comparisons (best, average and worst) were: %d, %d, %d", opCountResult[0], opCountResult[1], opCountResult[2]);
            out += "\nThe total number of search comaprisons was: "+opCountSearch[0];
            return out;
        }
    }

    /**
    * The test method executes a time complexity test on the underlying search algorithm of the printDamName
    * method. Using multiple subsets of the original data set, it records counts of search and insert comparisons during search
    * and records the worst, average and best cases for each subset of data. Results are written to a text file. 
    *
    * @param String splitBy
    * @param String[] vals
    **/ 
    public void test(String splitBy, String [] vals)
    {

        System.out.println("writing results to file doc/testResults/part_5_experiment/BSTResults.txt...");
        
        // String builders to store results from opCounts
        StringBuilder sb = new StringBuilder();
        StringBuilder sbIn = new StringBuilder();

        for(int i =2; i<213; i++)
        {
            // define filepath to data subset for testing (from 1 - 212)
            String testPath = "data/subsets/test_"+i+".csv";
            dataFrame dfTemp = new dataFrame(testPath, ",");
            String[] names = new String[dfTemp.numRows()-1];
            int[] opIn = new int[dfTemp.numRows()+1];
            
            // populate names array to use for testing
            for(int j = 1; j < dfTemp.numRows(); j++){ 
                names[j-1] = (dfTemp.getVal(j, "Dam Name")).trim();
            }
            
            // Create temporary DamBSTApp object for testing
            DamBSTApp tempApp = new DamBSTApp(testPath, splitBy, vals);
            tempApp.addDams(vals, opIn);

            // Get Best Average and worst cases for opCounts during insert
        	Arrays.sort(opIn);
        	int inSum = 0;
        	for(int l = 0 ; l < opIn.length ; l++){ 
        		inSum += opIn[l]; 
        	}
        	int bestI = opIn[0]; // Best Case
        	int averageI = inSum/(opIn.length); // Avergae Case
        	int worstI = opIn[opIn.length-1]; // Worst Case 

            // counters to keep track of Search opCounts
            int [] opCounts = new int[names.length];
            int worstS;
            int bestS;
            int averageS = 0;

            // run test: search BST for each name in the names array
            for(int k = 0; k<names.length; k++)
            {
                String s = tempApp.printDamName(names[k], opIn);

                // Store the opCount in array
                String [] splitS = s.split(" ");
                opCounts[k] = Integer.parseInt(splitS[splitS.length-1]);
                averageS += opCounts[k];
            }
            
            // Get worst, average and best opCOunts from test on data subset
            Arrays.sort(opCounts);
            worstS = opCounts[opCounts.length-1];
            bestS = opCounts[0];
            averageS = averageS/opCounts.length;

            //Build string of results
            sb.append("\n"+bestS+" "+averageS+" "+worstS);
            sbIn.append("\n"+bestI+" "+averageI+" "+worstI);
        }

        try{
            // Write search results to file
            PrintWriter pw = new PrintWriter(new FileWriter("doc/testResults/part_5_experiment/BSTSearchResults.txt"));
            pw.write(sb.toString());
            pw.close();

            // Write search results to file
            pw = new PrintWriter(new FileWriter("doc/testResults/part_5_experiment/BSTInsertResults.txt"));
            pw.write(sbIn.toString());
            pw.close();

            System.out.println("done!");
        } catch (IOException e){
            System.out.println(e);
            System.out.println("Error creating or writing results to file...");

        }
    }
    /**
    * The getDf returns a dataFrame object refrence.
    *
    * @return dataFrame df
    **/ 
    public dataFrame getDf(){
    	return df;
    }
}
