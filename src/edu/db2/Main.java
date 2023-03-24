package edu.db2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int bufferSize = Integer.parseInt(args[0]);
        Scanner scanner = new Scanner(System.in);
        BufferPool bufferPool = new BufferPool(bufferSize);
        boolean exit = false;
        while(!exit) {
            System.out.println("The program is ready for the next command");
            String command = scanner.nextLine();
            String[] cmdArgs = command.split("\\s+");
            switch (cmdArgs[0]) {
                case ("GET"):
                    bufferPool.GET(Integer.parseInt(cmdArgs[1]));
                    break;
                case ("SET"):
                    break;
                case ("PIN"):
                    bufferPool.PIN(Integer.parseInt(cmdArgs[1]));
                    break;
                case ("UNPIN"):
                    break;
                case ("EXIT"):
                    exit = true;
                    break;
                default:
                    break;
            }
        }

    }
}
