package com.Hebert.HPlayer.HMusic.implementation;

import java.util.ArrayList;
import java.util.List;

public class YoutubeCodeCleaner {
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
}
