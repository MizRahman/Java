
/**
 *This my Exception Class
 *@author MD Mizanur Rahman
 *@version  12/04/2013
 */
import java.io.*;
public class MdsTVException extends Exception
{
	public MdsTVException()
	{
		super("Error in the MDs TV Shop");//Using the Exception constructor
	}
	public MdsTVException(String errMessage) //Overriding the Constructor
	{
		super(errMessage);
	}
}
