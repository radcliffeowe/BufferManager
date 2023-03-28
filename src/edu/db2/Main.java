package edu.db2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int bufferSize = Integer.parseInt(args[0]); // read the size of the buffer pool from the command arguments
        Scanner scanner = new Scanner(System.in);
        BufferPool bufferPool = new BufferPool(bufferSize); // create the buffer pool given the size
        boolean exit = false;
        while(!exit) {
            System.out.println("The program is ready for the next command");
            String command = scanner.nextLine();
            String[] split = command.split("\""); // for the SET command, isolates the new String from the command and the record number
            String[] cmdArgs = split[0].split("\\s+"); // splits the command and the blockId/recordNumber for parsing
            switch (cmdArgs[0]) {
                case ("GET"):
                    bufferPool.GET(Integer.parseInt(cmdArgs[1]));
                    break;
                case ("SET"):
                    bufferPool.SET(Integer.parseInt(cmdArgs[1]), split[1]);
                    break;
                case ("PIN"):
                    bufferPool.PIN(Integer.parseInt(cmdArgs[1]));
                    break;
                case ("UNPIN"):
                    bufferPool.UNPIN(Integer.parseInt(cmdArgs[1]));
                    break;
                case ("EXIT"):
                    exit = true; //exit the shell, end the program
                    break;
                default:
                    break;
            }
        }

    }
}
