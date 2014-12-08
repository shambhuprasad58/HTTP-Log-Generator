/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author shambhu
 */
public class HTTPLog {
    private String[] hosts = {"localhost","127.0.0.1"};
    private String[] methods = {"GET", "POST"};
    private String[] paths={"/xampp/","/xampp/logo.png"};
    private String[] protocols={"HTTP/1.1"};
    private final int[] status={200, 302, 404};
    private final Random r = new Random();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
    
    private String generateHost()
    {
        return hosts[r.nextInt(hosts.length)];
    }
    private String generateMethod()
    {
        return methods[r.nextInt(methods.length)];
    }
    private String generatePath()
    {
        return paths[r.nextInt(paths.length)];
    }
    private String generateProtocol()
    {
        return protocols[r.nextInt(protocols.length)];
    }
    private int generateStatus()
    {
        return status[r.nextInt(status.length)];
    }
    private String generateDate()
    {
        Date now = new Date();
        return formatter.format(now);
    }
    private int generateBytes()
    {
        return r.nextInt(1000);
    }
    public String generateNewLog()
    {
        return generateHost() + " - - [" + generateDate() + "] \"" + generateMethod() + " " + generatePath() + " " + 
                generateProtocol() + "\" " + generateStatus() + " " + generateBytes();
    }
}
