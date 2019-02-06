import java.io.*;
import java.util.*;

/**
* The damAVLApp class has a dataFrame populated with dam information, from which
* it constructs Dam objects to be stored in an AVL tree, comprised of 
* nodes.
*
* Methods included provide utility for adding Dam objects to the AVL tree, searching
* for individual dams by name, printing all Dams stored in the AVL tree, as well as
* running a time complexity test on the search and insert algorithm underpinning the 
* printDamName method.
*
* @author Hussein Suleman
* @author Jeremy du Plessis
**/
public class DamAVLapp 
{
    private dataFrame df;
    private String[] vals;
    private AVLTree damAVL;
    
    /**
    * The damAVLapp constructor.
    *
    * @param String filePath
    * @param String splitBy
    * @param String[] vals
    **/
    public DamAVLapp(String filePath, String splitBy, String [] vals)
    {
        this.vals = vals;
        this.df = new dataFrame(filePath, splitBy);
        this.damAVL = new AVLTree();
    }
    
    public static void main( String [] args)
    {
        String path = "data/Dam_Data_Fixed.csv";
        String[] vals = {"Dam Name", "FSC 1'000'000m³ (million m³)", "20160307 (percent full)"};
        String split = ",";
        
        DamAVLapp dap = new DamAVLapp(path, split, vals);

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
    * The addDams method adds Dam objects to a AVL Tree based on the specified values.
    *
    * @param String[] vals
    * @param int[] opCount
    **/
    public void addDams(String[] vals, int[] opCountInsert)
    {
        int[] temp = {0};
         for(int i = 1; i < df.numRows() ; i++)
         {
            temp[0] = 0;
            damAVL.insert(new Dam(df.getVal(i, vals[0]), df.getVal(i, vals[1]), df.getVal(i, vals[2])), temp);
            opCountInsert[i] = temp[0];
         }	   
    }
    
    /**
    * The printAllDams prints information on all Dam objects contained in the AVL Tree.
    * Outputs best, avergae and worst case for number of comparison operations executed while inserting data.
    *
    * @param int[] opCountResult
    * @return String out 
    **/
    public String printAllDams(int[] opCountResult)
    {        
        // print dam info
        damAVL.treeOrder();
        String out = "";

        out = "\nPrinted all dam entries from AVL tree.";
        out += "\nthe size of the dataset was: "+damAVL.getSize();
        out += String.format("\nThe number of insertion comparisons (best, average and worst) were: %d, %d, %d", opCountResult[0], opCountResult[1], opCountResult[2]);
        return out;
    }
    
    /**
    * The printDamName method accepts a dam name as a search query and returns a String of
    * Dam object information if the queried object if found. Else it returns a notification
    * that the query was found in the AVL Tree. The number of comparisons for search and insertion 
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

            Dam d2 = (Dam)damAVL.find(d1, opCountSearch).data;
            out = "Result: "+ String.format("\nname: %-20s \ndam level: %-10s \nFSC: %-10s", d2.getName(), d2.getLevel(), d2.getFSC());
            out += "\nthe size of the dataset was: "+damAVL.getSize();
            out += String.format("\nThe number of insertion comparisons (best, average and worst) were: %d, %d, %d", opCountResult[0], opCountResult[1], opCountResult[2]);
            out += "\nThe total number of search comaprisons was: "+opCountSearch[0];
            return out;

        } catch (NullPointerException e){
            out =  "Result: \nDam not found"+"\nquery: "+query;
            out += "\nthe size of the dataset was: "+damAVL.getSize();
            out += String.format("\nThe number of insertion comparisons (best, average and worst) were: %d, %d, %d", opCountResult[0], opCountResult[1], opCountResult[2]);
            out += "\nThe total number of search comaprisons was: "+opCountSearch[0];
            return out;
        }
    } 

    /**
    * The test method executes a time complexity test on the underlying search algorithm of the printDamName
    * method. Using multiple subsets of the original data set, it records counts of comparisons during insertion and search
    * and records the worst, average and best cases for each subset of data. Results are written to a text file. 
    *
    * @param String splitBy
    * @param String[] vals
    **/ 
    public void test(String splitBy, String [] vals)
    {

        System.out.println("writing results to file doc/testResults/part_5_experiment/AVLResults.txt...");
        
        // String builder to store results from opCounts
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
            
            // Create temporary DamAVLApp object for testing
            DamAVLapp tempApp = new DamAVLapp(testPath, splitBy, vals);
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

            // counters to keep track of opCounts
            int [] opCounts = new int[names.length];
            int worstS;
            int bestS;
            int averageS = 0;

            // run test: search AVL Tree for each name in the names array
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
            PrintWriter pw = new PrintWriter(new FileWriter("doc/testResults/part_5_experiment/AVLSearchResults.txt"));
            pw.write(sb.toString());
            pw.close();

            // Write insert results to file
            pw = new PrintWriter(new FileWriter("doc/testResults/part_5_experiment/AVLInsertResults.txt"));
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