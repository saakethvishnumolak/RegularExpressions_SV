package logProcessor;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a class that searches a log file. It stores each unique IP address and Username in their own hashmaps and counts them. This class accepts two arguements: the filename and a print flag arguement. 0 prints the default output, 1 prints hashmap of IP addresses and default output, 2 prints hashmap of usernames, and anything else prints default output. 
 * @author Saaki Vishnumolakala
 * @version 1.0
 * Compiler Project 3
 * CS 322 - Compiler Construction
 * Spring 2023 
 */

public class LogFileProcessor {

    Map<String, Integer> ipAddresses = new HashMap<>();
    Map<String, Integer> usernames = new HashMap<>();
    String filename;
    int printFlag;
    int parsedLines;

    //Get two inputs: name of the file and print flag
    public LogFileProcessor() {

    }

    /**
     * Constructor
     * @param filename The file that is passed
     * @param printFlag The value of print flag that is passed
     */
    public LogFileProcessor(String filename, int printFlag) {

        this.filename = filename;
        this.printFlag = printFlag;
    }

    /**
     * Processes the Log file. 
     * First, it reads the file and parses through line by line to check for all IP addresses and unique usernames.
     * IP addresses are put into one hashmap.
     * Usernames are put into a different hashmap.
     */
    public void processLogFile() {

        BufferedReader reader = null;

        try {
            //Read the file
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {

                //Search for IP addresses
                Pattern ipAddressPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
                Matcher ipAddressMatcher = ipAddressPattern.matcher(line);
                while(ipAddressMatcher.find()) {
                    String ipAddressGroup = ipAddressMatcher.group();
                    //Put into hashmap
                    int count = ipAddresses.getOrDefault(ipAddresses, 0);
                    ipAddresses.put(ipAddressGroup, count + 1);
                }

                //Search for usernames
                Pattern userPattern = Pattern.compile("user=([a-zA-Z0-9_]+)");
                Matcher userMatcher = userPattern.matcher(line);
                while(userMatcher.find()) {
                    String username = userMatcher.group(1);
                    //Put into hashmap
                    int count = usernames.getOrDefault(username, 0);
                    usernames.put(username, count + 1);
                }
                parsedLines++;
            }
            reader.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @return Returns the size of the IP address hashmap
     */
    public int numberOfIPAddresses() {
        return ipAddresses.size();
    }

    /**
     * @return Returns the size of the username hashmap
     */
    public int numberOfUsernames() {
        return usernames.size();
    }

    /**
     * Prints the key and value of the IP addresses from hashmap
     */
    public void printIPAddresses() {
        for (Map.Entry<String, Integer> entry : ipAddresses.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Prints the key and value of the usernames from hashmap
     */
    public void printUsernames() {
        for (Map.Entry<String, Integer> entry : usernames.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /**
     * Print Flag output. Prints the default output. 
     * It prints IP addresses if flag is 1, usernames if flag is 2
     */
    public void printFlagOutput() {

        System.out.println(parsedLines + " lines in the log file were parsed.");
        System.out.println("There are " + numberOfIPAddresses() + " unique IP addresses in the log.");
        System.out.println("There are " + numberOfUsernames() + " users in the log.");
        if(printFlag == 1) {
            printIPAddresses();
        } else if (printFlag == 2) {
            printUsernames();
        }
    }


    public static void main (String [] args) {

    }
}
