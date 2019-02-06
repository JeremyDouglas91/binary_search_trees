import java.io.*;
import java.util.*;

/**
* The test_6 class contains static methods for runnining a single test. 
* It uses both the AVL and Binary Search Tree data Structures. 
* Output is stored in .txt files
*
* @author Jeremy du Plessis
**/
public class test_6 
{
    /**
    * The damBSTApp constructor.
    *
    * @param String filePath
    * @param String splitBy
    * @param String[] vals
    **/
    
    public static void main( String [] args)
    {
        String path = "data/Dam_Data_Sorted.csv";
        String[] vals = {"Dam Name", "FSC 1'000'000m³ (million m³)", "20160307 (percent full)"};
        String split = ",";
        
        if(args[0].equals("test"))
        {
           testBST(split, vals, path);
           testAVL(split, vals, path);
        }

    }

    /**
    * The testBST method executes a time complexity test on the underlying insertion and search algorithms of the printDamName
    * method. Using multiple subsets of the original data set, it records counts of search and insert comparisons 
    * and records the worst, average and best case for each. Results are written to a text file. 
    *
    * @param String splitBy
    * @param String[] vals
    * @param String testPath
    **/ 
	public static void testBST(String splitBy, String [] vals, String testPath)
    {

        System.out.println("writing results to file doc/testResults/part_6_experiment/BSTResults.txt...");
        
        // String builder to store results from opCounts
        StringBuilder sb = new StringBuilder();
        StringBuilder sbIn = new StringBuilder();

        // define filepath to data for testing
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
        sb.append("BST search opCount Results: "+"\nbest: "+bestS+"\naverage: "+averageS+"\nworst: "+worstS);
        sb.append("\nBST insert opCount Results: "+"\nbest: "+bestI+"\naverage: "+averageI+"\nworst: "+worstI);

        try{
            // Write search results to file
            PrintWriter pw = new PrintWriter(new FileWriter("doc/testResults/part_6_experiment/BSTResults.txt"));
            pw.write(sb.toString());
            pw.close();

            System.out.println("done!");
        } catch (IOException e){
            System.out.println(e);
            System.out.println("Error creating or writing results to file...");

        }
    }

    /**
    * The testAVL method executes a time complexity test on the underlying insertion and search algorithms of the printDamName
    * in the DamAVLapp class. Using an ordered subset of the original data set, it records counts of search and insert comparisons 
    * and records the worst, average and best case for each. Results are written to a text file. 
    *
    * @param String splitBy
    * @param String[] vals
    * @param String testPath
    **/ 
    public static void testAVL(String splitBy, String [] vals, String testPath)
    {

        System.out.println("writing results to file doc/testResults/part_6_experiment/AVLResults.txt...");
        
        // String builder to store results from opCounts
        StringBuilder sb = new StringBuilder();

        // define filepath to data for testing
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
        sb.append("AVL search opCount Results: "+"\nbest: "+bestS+"\naverage: "+averageS+"\nworst: "+worstS);
        sb.append("\nAVL insert opCount Results: "+"\nbest: "+bestI+"\naverage: "+averageI+"\nworst: "+worstI);

        try{
            // Write search results to file
            PrintWriter pw = new PrintWriter(new FileWriter("doc/testResults/part_6_experiment/AVLResults.txt"));
            pw.write(sb.toString());
            pw.close();

            System.out.println("done!");
        } catch (IOException e){
            System.out.println(e);
            System.out.println("Error creating or writing results to file...");

        }
    }
}