import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class LogFileProcessor {

    Map<String, Integer> ipAdresses = new HashMap<>();
    Map<String, Integer> usernames = new HashMap<>();
    String filename;
    int printFlag;

    //Get two inputs: name of the file and print flag
    public LogFileProcessor() {

    }

    //Constructor
    public LogFileProcessor(String filename, int printFlag) {

        this.filename = filename;
        this.printFlag = printFlag;
    }

    public void processLogFile() {
        //Read the file
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {

            //Search for IP addresses
            Pattern ipAddressPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
            Matcher ipAddressMatcher = ipAddressPattern.matcher(line);
            while(ipAddressMatcher.find()) {
                String ipAddressGroup = ipAddressMatcher.group();
                //Put into hashmap
                int count = IPAddresses.getOrDefault(ipAddresses, 0);
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
        }
        reader.close();
        
    }

    //Get the size of IP Addresses
    public int numberOfIPAdresses() {
        return ipAdresses.size();
    }

    //Get the size of Usernames
    public int numberOfUsernames() {
        return usernames.size();
    }

    //Print IPAddresses
    public void printIPAddresses() {
        for (Map.Entry<String, Integer> entry : ipAdresses.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    //Print Usernames
    public void printUsernames() {
        for (Map.Entry<String, Integer> entry : usernames.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    //printFlag
    public void printFlagOutput() {

        System.out.println("There are " + numberOfIPAdresses() + " unique IP address in the log.");
        System.out.println("There are " + numberOfUsernames() + " unique IP address in the log.");
        if(printFlag == 1) {
            printIPAddresses();
        } else if (printFlag == 2) {
            printUsernames();
        }
    }



    //Search the file for ip addresses using regex. Store them in a hashmap
    //Search the file for usernames using regex. Store them in a hashmap

    //If 0, program prints default output
    //If 1, program prints Hashmap of IP addresses and default
    //If 2, program prints Hashmap of usernames and default
    //If >2 program prints default




















    public static void main (String [] args) {

    }
}
