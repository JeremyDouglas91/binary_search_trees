/**
* Dam is a class created to store name, level and Dam information about dams
* It implements the comparable interface so that one dam name can be compared to anoher lexicographically
*
* @author Jeremy du Plessis
**/

public class Dam implements Comparable
{
    private String name;
    private String level;
    private String FSC;
    
    /**
    * The constructor for a dam object
    * 
    * @param name   a String, the name of the dam 
    * @param FSC    a String, the FSC number of the dam
    * @param level   a String, the level of the dam             
    **/
    public Dam(String name, String FSC, String level)
    {
        this.name = name.trim();
        this.level = level;
        this.FSC = FSC;
    }
    /**
    * returns the name of a Dam object
    * 
    * @return   a String, the name of the dam             
    **/
    public String getName()
    {
        return name;
    }
    /**
    * returns the level of a Dam object
    * 
    * @return   a String, the level of the dam             
    **/
    public String getLevel()
    {
        return level;
    }
    /**
    * returns the FSC of a Dam object
    * 
    * @return   a String, the FSC of the dam             
    **/
    public String getFSC()
    {
        return FSC;
    }
    /**
    * compares the names of two Dam objects based on lexicographical ordering
    * 
    * @return   an int, > 0 if greater, < 0 is less than, == 0 if equivalent             
    **/
    public int compareTo(Object d)
    {
        Dam otherDam = (Dam) d;
        return (this.getName()).compareTo(otherDam.getName());
    }

    /**
    * returns the value of the instance variables of a Dam object
    * 
    * @return   a String, containing name, level and FSC of the dam             
    **/
    public String toString(){
        return "\nname: "+this.getName()+"\nlevel: "+this.getLevel()+"\nFSC: "+this.getFSC(); 
    }
}