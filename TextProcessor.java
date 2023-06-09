package processor;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This java program searches text files. The program excepts two inputs: the file and a regex command. The program searches the file with the regex command passed and print each occurence and the number of occurences.
 * @author Saaki Vishnumolakala
 * @version 1.0
 * Compiler Project 3
 * CS322 - Compiler Construction
 * Spring 2023
 */

public class TextProcessor {

    public static void main (String [] args) {

        //First accept two inputs: the name of the file and a regex command
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a text file: ");
        String filename = scan.nextLine();

        System.out.println("Enter a regex pattern: ");
        String command = scan.nextLine();

        //Read the file
        File file = new File(filename);
        try {
            scan = new Scanner(file);

            //Count total number of occurrences
            int count = 0;

            while (scan.hasNextLine())
            {
                String line = scan.nextLine();
                Pattern pattern = Pattern.compile(command);
                Matcher matcher = pattern.matcher(line);
                
                //Print every occurence of the regex command
                while(matcher.find())
                {
                    String group = matcher.group();
                    int start = matcher.start();
                    int end = matcher.end();
                    System.out.println(group + " " + start + " " + end);
                    count++;

                }
            }
            //Print total # of occurences
            System.out.println(count);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
