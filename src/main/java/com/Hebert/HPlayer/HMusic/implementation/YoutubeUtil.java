package com.Hebert.HPlayer.HMusic.implementation;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import java.io.File;
import java.io.FileInputStream;

public class YoutubeUtil {
    public static String linkCodeGetter(String link) {
        //https://www.youtube.com/watch?v=1f81qXxggo8
        //https://youtu.be/1f81qXxggo8?feature=shared
        //https://www.youtube.com/watch?v=u5heWZ9occg&list=PLbLnVwFkWyIi-4rcBuuMPT5tVZuI3ba0z


        String[] splittedLink = link.split("/");
        
        String dirtyCode;

    

        if (link.contains("https://")){
            dirtyCode = splittedLink[3];
        }else{
            dirtyCode = splittedLink[1];
        }


        if (dirtyCode.contains("watch?v=")){
            dirtyCode = dirtyCode.replace("watch?v=", "");
        }

        String cleanCode = "";
        
        int dirtyCodeLength = dirtyCode.length();
        
        for (int i = 0; i < dirtyCodeLength; i++){
            char currentChar = dirtyCode.charAt(i);

            if (currentChar == '?' || currentChar == '&'){
                break;
            }

            cleanCode += currentChar;
        }



        return cleanCode;
    }

    public static byte[] convertFileToByteArray(File file) throws IOException {
        byte[] fileContent = new byte[(int) file.length()];
        
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileContent);
        }
        
        return fileContent;
    }


    public static Integer convertDurationIntoSeconds(String duration){
        duration = duration.replace("PT", "");

        int durationCharLength = duration.length();

        String numberTracker = "";

        Integer totalDuration = 0;

        for (int i = 0; i < durationCharLength; i++){
            char currentChar = duration.charAt(i);

            if (currentChar == 'H'){
                totalDuration += Integer.parseInt(numberTracker) * 3600;
                numberTracker = "";
                continue;
            }else if(currentChar == 'M'){
                totalDuration += Integer.parseInt(numberTracker) * 60;
                numberTracker = "";
                continue;
            }else if (currentChar == 'S'){
                totalDuration += Integer.parseInt(numberTracker);
                numberTracker = "";
                continue;
            }

            numberTracker += currentChar;
        }


        return totalDuration;
    }
}
