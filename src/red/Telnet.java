
package red;

import java.io.InputStream;
import java.io.PrintStream;
import org.apache.commons.net.telnet.TelnetClient;

public class Telnet
{
    private static TelnetClient telnet = new TelnetClient();
    private static InputStream in;
    private static PrintStream out;
    private static String prompt = "%";
    
    public static String fast(String ip , int puerto , String user, String password, String comando)
    {
        String respuesta = "";
        try 
        {
            // Connect:
            telnet.connect(ip, puerto);

            //Canales I/O:
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());

            
            readUntil("login: ");
            write(user);
            readUntil("Password: ");
            write(password);
            
            respuesta = sendOneSimpleCommandAsRoot("ifconfig");
                
            disconnect();
    	} 
        catch (Exception e) 
        {
            e.printStackTrace();
    	}
        return respuesta;
    }
    
    public static void su(String password) 
    {
    	try 
        {
            write("su");
            readUntil("Password: ");
            
            write(password);
            prompt = "#";
            
            readUntil(prompt + " ");
    	} 
        catch (Exception e)
        {
    		e.printStackTrace();
    	}
    }

    public static String readUntil(String pattern) 
    {
    	try 
        {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            boolean found = false;
            
            char ch = (char) in.read();
            while (true) 
            {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) 
                {
                    if (sb.toString().endsWith(pattern)) 
                    {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
    	}
        catch (Exception e) 
        {
    		e.printStackTrace();
    	}
    	return null;
    }

    public static void write(String value) 
    {
    	try 
        {
            out.println(value);
            out.flush();
            System.out.println(value);
    	} 
        catch (Exception e) 
        {
            e.printStackTrace();
    	}
    }
    public static String sendOneSimpleCommandAsRoot(String comando)
    {
        String respuesta = "";
        try 
        {
            write(comando);
            readUntil("#");
            respuesta = readUntil("#");
    	}
        catch (Exception e)
        {
            e.printStackTrace();
    	}
    	return respuesta;
        
    }
    public static String sendCommand(String command) 
    {
    	try 
        {
            write(command);
            return readUntil(prompt + " ");
    	} 
        catch (Exception e)
        {
    		e.printStackTrace();
    	}
    	return null;
    }
    public static void disconnect()
    {
    	try
        {
            telnet.disconnect();
    	} 
        catch (Exception e) 
        {
            e.printStackTrace();
    	}
    }
}
