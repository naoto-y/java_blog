package lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkDownRender {
    public List<String> rendering(String filePath){
        List<String> result = new ArrayList<>();
        File file = new File(filePath);
        FileReader fr;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
               result.add(convert(line));
            }
            br.close();
        } catch (FileNotFoundException e) {
            result.add("<p>File Not Found</p>");
        } catch (IOException e) {
            result.add("<p>File EROOR</p>");
        }
        return result;
    }

    private String convert(String Line) {
        String converted;

        Pattern headingP = Pattern.compile("^#{1,6} .*$");
        Matcher headingM = headingP.matcher(Line);
        if(headingM.find()) {
            int index = headingM.group().indexOf(" ");
            converted = "<h" + index + ">" + headingM.group().substring(index + 1) + "</h" + index + ">";
            return converted;
        }

        Pattern boldP = Pattern.compile("^[_|/*]{2}.*[_|/*]{2}$");
        Matcher boldM = boldP.matcher(Line);
        if(boldM.find()) {
            converted = "<b>" + boldM.group().substring(2, boldM.group().length() - 2) + "</b>";
            return converted;
        }

        Pattern emP = Pattern.compile("^[_|/*]{1}.*[_|/*]{1}$");
        Matcher emM = emP.matcher(Line);
        if(emM.find()) {
            converted = "<em>" + emM.group().substring(1, emM.group().length() - 1) + "</em>";
            return converted;
        }

        Pattern delP = Pattern.compile("^~~.*~~");
        Matcher delM = delP.matcher(Line);
        if(delM.find()) {
            converted = "<del>"+ delM.group().substring(2, delM.group().length() - 2) +"</del>";
            return converted;
        }

        Pattern hrP = Pattern.compile("^---$");
        Matcher hrM = hrP.matcher(Line);
        if(hrM.find()) {
            converted = "<hr>";
            return converted;
        }

        return Line;
    }
}
