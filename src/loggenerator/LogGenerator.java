/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Random;

/**
 *
 * @author root
 */
public class LogGenerator {
    
    public static void main(String[] args) throws IOException, InterruptedException
    {
        LogGenerator logGenerator = new LogGenerator();
        String logFile = "test_logs.txt";
        Random r = new Random();
        while(true)
        {
            logGenerator.writeToFile(logFile, r.nextInt(10));
            Thread.sleep(r.nextInt(10)*1000);
        }
    }
    public void writeToFile(String logFile, int logCount) throws IOException
    {
        HTTPLog log = new HTTPLog();
        FileOutputStream fileOutputStream = new FileOutputStream(logFile, true);
        FileLock lock = fileOutputStream.getChannel().lock();
        for(int i=0;i<logCount;i++)
        {
            String newLog = log.generateNewLog()+"\n";
            byte[] bytes = newLog.getBytes("UTF-8");
            fileOutputStream.write(bytes);
        }
        lock.release();
        System.out.println("Wrote "+logCount+" logs");
    }
}
