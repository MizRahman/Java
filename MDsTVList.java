
/**
 *In this class I am storing TV's name,Screen Size,Type,guarantee and the price into the collection.
 *also writting the store collection into the text file
 *reading text file
 *@author MD Mizanur Rahman
 *@version  22/03/2013
 */
import java.util.*;
import java.io.*;
class MDsTVList
{
	private List<MDsTV> myTelly; //Declaring the List object with Generic
	public MDsTVList()
	{
		myTelly = new ArrayList<MDsTV>(); //Creating new List object
		myTelly = readTVFile();//Putting whatever in the text file into my list
	}
	public void addTV(MDsTV myTellyIn)
	{
		myTelly.add(myTellyIn); //adding whatever into the parameter into the list
		writeTVFile(); //replacing the the text file with new list
	}
	public List<MDsTV> getTVList()
	{
		return myTelly;//returing the whole list of TVs
	}
	//Writting into the Text file
	public void writeTVFile()
	{
		try
		{
		   	FileWriter tvFile = new FileWriter("tv.txt");
		    PrintWriter tvWriter = new PrintWriter(tvFile);
		    for(MDsTV item : getTVList())
		    {
		    	tvWriter.println(item.getModelNo());
		        tvWriter.println(item.getTVName());
				tvWriter.println(item.getScreenSize());
		        tvWriter.println(item.getTVType());
		        tvWriter.println(item.getPrice());
		        tvWriter.println(item.getGuarantee());
			}
			tvWriter.close();
	        }
	   catch(IOException errmsg) {}
	}
	//Reading from the text file
	private List<MDsTV> readTVFile()
    {
    	List<MDsTV> tempTV = new ArrayList<MDsTV>();
        String modelNo,tvName,tvType,tempGuarantee;
        int  sSize,guarantee;
        String tempSize;
        String tmpPrice;
        double tvPrice;
        try
        {
            FileReader tvFile = new FileReader("tv.txt");
            BufferedReader tvReader = new BufferedReader(tvFile);
			modelNo = tvReader.readLine(); // read the firstline of the file
            while(modelNo!= null) // a null string indicates end of file
            {
                tvName = tvReader.readLine();
                tempSize = tvReader.readLine();
                sSize = Integer.parseInt(tempSize);
               	tvType = tvReader.readLine();
               	tmpPrice = tvReader.readLine();
               	tvPrice = Double.parseDouble(tmpPrice.toString());
               	tempGuarantee = tvReader.readLine();
               	guarantee = Integer.parseInt(tempGuarantee);
               	tempTV.add(new MDsTV(modelNo,tvName,sSize,tvType,tvPrice,guarantee)); //adding the text file value into the collection
               	modelNo = tvReader.readLine();
            }
            tvReader.close();
		}
	    catch(IOException e) {}
        return tempTV; //returing the collection
    }
}
