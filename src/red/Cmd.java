package red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cmd 
{
	private static Process cmd;
	private static BufferedReader bf;
	public static void executeVisual(String comando)
	{
            try 
            {    
                cmd = Runtime.getRuntime().exec(comando);
                bf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));  

                String linea = null;  	//IMPRIME RESULTADOS:
                while ((linea = bf.readLine()) != null) 
                {  
                    System.out.println(linea);  
                }  
            } catch (IOException e) 
            {
                    System.out.println("Error de ejecucion CMD");
                    e.printStackTrace();
            }
	}
	public static String execute(String comando)
	{
		String respuesta= "";
		try 
		{    
		    cmd = Runtime.getRuntime().exec(comando);
		    bf = new BufferedReader(new InputStreamReader(cmd.getInputStream()));  
		   
		    String linea = null;  	//IMPRIME RESULTADOS:
                    while ((linea = bf.readLine()) != null) 
                    {  
                            respuesta += linea + ";\n";  
                    }  
		} 
                catch (IOException e) 
		{
			System.out.println("Error de ejecucion CMD:" +comando);
			e.printStackTrace();
		}
		return respuesta;
	}
}
