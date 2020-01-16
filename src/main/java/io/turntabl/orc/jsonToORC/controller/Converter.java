package io.turntabl.orc.jsonToORC.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Converter {
    public static void runningShellCommand(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        // -- Linux --

        // Run a shell command
//		processBuilder.command("bash", "-c", "java -jar orc-tools-1.5.4-uber.jar  meta sampledata2.json");

        // -- Windows --

        // Run a command
        processBuilder.command("cmd.exe", "/c", "java -jar orc-tools-1.5.4-uber.jar  meta sampledata2.json");

        try {
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            } else {
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        runningShellCommand();
    }
}
