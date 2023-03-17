package demoPackage;
import logProcessor.LogFileProcessor;


public class demo {
    public static void main(String [] args) {
        LogFileProcessor processor = new LogFileProcessor(args[0], Integer.parseInt(args[1]));
        processor.processLogFile();
        processor.printFlagOutput();
    }
}
