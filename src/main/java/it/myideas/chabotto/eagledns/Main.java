package it.myideas.chabotto.eagledns;

import java.net.UnknownHostException;
import se.unlogic.eagledns.EagleDNS;

public class Main {
    
    public static void main(String[] args) throws UnknownHostException {
        System.out.println("Uelaaaaaa");
        
        EagleDNS dns = new EagleDNS("conf/config.xml");
        
        // Nope, questo lancia una specie di autorefresh, credo
//        new Thread(new EagleDNS()).start();
                
        System.out.println("*******************************");
        System.out.println("*******************************");
        System.out.println("*******************************");
        System.out.println("*******************************");
        System.out.println("*******************************");
        
//        Thread.sleep(Long.MAX_VALUE);
    }
}