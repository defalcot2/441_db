package utilities;

import javax.servlet.http.HttpServletRequest;

public class DataTools {
	public static boolean NullOrEmpty(String input)
    {
        if(input==null||input.trim().equals(""))    //trim removes any whitespace the user might have entered
        {
            return true;                            //this states that nothing was entered into the form
        }
        else
        {
            return false;                           //this states that something was in the form
        }
    }
	
	public static boolean isInteger(String input)
	{
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
   public static boolean IfReferredBy(HttpServletRequest request, String cameFrom)
    {
        if(request.getHeader("Referer")==null)
        {
            return false;
        }
        else
        {
            String Referer = (String)request.getHeader("Referer");
            if(Referer.contains(cameFrom))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
