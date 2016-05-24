
/**
 *In this class I am storing TV's name,Screen Size,Type and the price.
 *Through this class I can also get the TVName,Price,Screen size and type
 *@author MD Mizanur Rahman
 *@version  15/03/2013
 */
import java.io.*;
public class MDsTV implements Serializable
{
	private String modelNo; //it will hold tvModel number
	private String tvName; //Storing TVNames
	private int scrSize; //Storing Screen Sizes
	private String tvType; //Such as LED/HD/Plasma/CD
	private Double tvPrice; //Storing TV Prices
	private int guarantee; //Length of Labour Guarantee(Years)

	public MDsTV(String modelNoIn,String tvNameIn, int scrSizeIn, String tvTypeIn, Double tvPriceIn,int guaranteeIn)
	{
		/*
		 *Storing ModelNumber,TVName,ScreenSize,TVTYpe and the TVPrice
		 */
		modelNo=modelNoIn;
		tvName = tvNameIn;
		scrSize = scrSizeIn;
		tvType = tvTypeIn;
		tvPrice = tvPriceIn;
		guarantee=guaranteeIn;
	}
	public String getModelNo()
	{
		//returning model nomber
		return modelNo;
	}
	public String getTVName()
	{
		/*this method will return the TV name*/
		return tvName;
	}
	public int getScreenSize()
	{
		/*this method will return the TV screen size*/
		return scrSize;
	}
	public String getTVType()
	{
		/*this method will return the TV'a type*/
		return tvType;
	}
	public double getPrice()
	{
		/*this method will return the TV price*/
		return tvPrice;
	}
	public int getGuarantee()
	{
		return guarantee;
	}
	public String toString()
	{
		return "Model Number : "
		+modelNo
		+ "\nTV Name: "
		+ tvName
		+ "\nScreen Size: "
		+ scrSize
		+ "\nType: "
		+ tvType
		+ "\nPrice: £"
		+ tvPrice
		+"\nLength of Guarantee(Year): "
		+guarantee
		+ "\n"
		+"\n";
	}
	public boolean equals (Object objIn)
	{
		/** We need this equal method to compare to String */
		MDsTV tvIn = (MDsTV)objIn; //type casting here
		return modelNo.equals(tvIn.modelNo);
	}
	public int hashCode()
	{
		/**We need this hasCode method when using List or Map Collection*/
		return modelNo.hashCode();
	}
}
