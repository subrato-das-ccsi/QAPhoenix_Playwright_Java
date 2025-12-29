package com.consensus.qaauto.utility;

import testResources.Constant;

import javax.swing.text.Utilities;
import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import static com.consensus.qaauto.common.playwright.Utilities.faker;

public class SalesforceGeneralUtility {
     public static String getRecordFileLocation(String className, String timeStamp) {
           return Paths.get("test-output/report/Files/") + "\\" + className + "-" + timeStamp + ".log";
       }
    public static String getDateInSimpleFormat() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }
    public static String readFromFile(String fileName){
        String fileData="";
        try {
            String filePath = Paths.get("test-output/report/Files/")+"\\"+fileName;
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                fileData = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return fileData;
    }
    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    public static String generateSixDigitNumber() {
        Random random = new Random();
        // Generates a number between 0 (inclusive) and 900000 (exclusive)
        // Then adds 100000 to shift the range to 100000 to 999999
        int randomNumber =  random.nextInt(900000) + 100000;
        return Integer.toString(randomNumber);
    }
    public static void readFromCSv(String csvFilePath){
        String filePath = Paths.get("test-output/report/Files/")+"\\"+csvFilePath;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by comma delimiter
                String []values = line.split(",");
                for(int i=0;i<4;i++){
                    System.out.println(values[i]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
    public static String  generateRandomEmailID(){
        Constant.firstName = faker.name().firstName();
        Constant.lastName = faker.name().lastName();
        return Constant.firstName +"."+Constant.lastName +"@yopmail.com";
    }

}
