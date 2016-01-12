package red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Network
{
    public static boolean pingear(String direccionIP)
    {
        boolean vivo = false;
        
        String so = System.getProperty("os.name");
        String respuestaCMD;
        
        if(so.toLowerCase().contains("windows"))
        {
            respuestaCMD = red.Cmd.execute("ping -w 3000 " + direccionIP + " -l 1 -n 1 ");
            if( !respuestaCMD.contains("destino inaccesible") && !respuestaCMD.contains("Tiempo de espera agotado"))
            {
                vivo = true;
            }
            else
            {
                vivo = false;
            }
        }
        else
        {
            respuestaCMD = red.Cmd.execute("ping -c 1 -s 1 -W 3000" + direccionIP);
            if(respuestaCMD.contains("1 received"))
            {
                vivo = true;
            }
            else
            {
                vivo = false;
            }
        }
                
      
        
        return vivo;
    }
    public static ArrayList<String> procesarIFConfig(String entrada)
    {
        ArrayList<String> arr = new ArrayList<String>();
        
        String acumuladorPosta = "";
        String acumulador = "";
        boolean flag = false;
        
        for(int i = 0 ; i < entrada.length(); i++)
        {
            char caracterActual = entrada.charAt(i);
            
            if(acumulador.endsWith("inet addr"))
            {
                //System.out.println(acumulador);
                acumulador = "";
                flag = true;
            }
            else if(acumulador.endsWith(" Bcast"))
            {
                flag = false;
                
                acumuladorPosta = acumuladorPosta.substring(0, acumuladorPosta.length() - 5);
                //System.out.println("ACUMULADOR POSTA:" + acumuladorPosta);
                arr.add(acumuladorPosta);
                acumuladorPosta = "";
                acumulador = "";
            }
            else
            {
                if(flag)
                {
                    acumuladorPosta+= caracterActual;
                }
                acumulador +=  caracterActual;
            }
        }
        
        
        return arr;
    }
}
