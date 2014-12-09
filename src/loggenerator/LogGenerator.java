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
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter number of new logs you want to write");
            int count = scanner.nextInt();
            logGenerator.writeToFile(logFile, count);
            //Thread.sleep(r.nextInt(10)*1000);
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
