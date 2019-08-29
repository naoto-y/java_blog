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
            int mode = 0;
            while((line = br.readLine()) != null) {
                mode = listCheck(line, mode);
                result.add(convert(line, mode));
            }
            br.close();
        } catch (FileNotFoundException e) {
            result.add("<p>File Not Found</p>");
        } catch (IOException e) {
            result.add("<p>File EROOR</p>");
        }
        return result;
    }

    private String convert(String line, int mode) {
        String converted;

        Pattern headingP = Pattern.compile("^#{1,6} .*$");
        Matcher headingM = headingP.matcher(line);
        if(headingM.find()) {
            int index = headingM.group().indexOf(" ");
            converted = "<h" + index + ">" + headingM.group().substring(index + 1) + "</h" + index + ">";
            return converted;
        }

        Pattern boldP = Pattern.compile("^[_|/*]{2}.*[_|/*]{2}$");
        Matcher boldM = boldP.matcher(line);
        if(boldM.find()) {
            converted = "<b>" + boldM.group().substring(2, boldM.group().length() - 2) + "</b>";
            return converted;
        }

        Pattern emP = Pattern.compile("^[_|/*].*[_|/*]$");
        Matcher emM = emP.matcher(line);
        if(emM.find()) {
            converted = "<em>" + emM.group().substring(1, emM.group().length() - 1) + "</em>";
            return converted;
        }

        Pattern delP = Pattern.compile("^~~.*~~");
        Matcher delM = delP.matcher(line);
        if(delM.find()) {
            converted = "<del>"+ delM.group().substring(2, delM.group().length() - 2) +"</del>";
            return converted;
        }

        Pattern hrP = Pattern.compile("^---$");
        Matcher hrM = hrP.matcher(line);
        if(hrM.find()) {
            converted = "<hr>";
            return converted;
        }

        Pattern olP = Pattern.compile("^[-|/*|/+] .*$");
        Matcher olM = olP.matcher(line);
        if(olM.find()) {
            converted = "<li>" + olM.group().substring(2) + "</li>";
            return converted;
        }

        Pattern ulP = Pattern.compile("^\\d\\. .*$");
        Matcher ulM = ulP.matcher(line);
        if(ulM.find()) {
            int index = ulM.group().indexOf(" ");
            converted = "<li>" + ulM.group().substring(index) + "</li>";
            return converted;
        }

        return line;
    }

    private int listCheck(String line, int nowMode) {
        int mode = 0;

        Pattern olP = Pattern.compile("^[-|/*|/+] .*$");
        Matcher olM = olP.matcher(line);
        if(olM.find()) {
            mode = 1;
        }

        Pattern ol2P = Pattern.compile("^ {2}[-|/*|/+] .*$");
        Matcher ol2M = ol2P.matcher(line);
        if(ol2M.find()) {
            mode = 2;
        }

        Pattern ol4P = Pattern.compile("^ {4}[-|/*|/+] .*$");
        Matcher ol4M = ol4P.matcher(line);
        if(ol4M.find()) {
            if(nowMode == 2) {
                mode = 3;
            } else {
                mode = 2;
            }
        }

        Pattern ol8P = Pattern.compile("^ {8}[-|/*|/+] .*$");
        Matcher ol8M = ol8P.matcher(line);
        if(ol8M.find()) {
            mode = 3;
        }

        Pattern ulP = Pattern.compile("^\\d\\. .*$");
        Matcher ulM = ulP.matcher(line);
        if(ulM.find()) {
            mode = 4;
        }

        Pattern ul2P = Pattern.compile("~ {2}\\d\\. .*$");
        Matcher ul2M = ul2P.matcher(line);
        if(ul2M.find()) {
            mode = 5;
        }

        Pattern ul4P = Pattern.compile("^ {4}\\d\\. .*$");
        Matcher ul4M = ul4P.matcher(line);
        if(ul4M.find()) {
            if(nowMode == 5) {
                mode = 6;
            } else {
                mode = 5;
            }
        }

        Pattern ul8P = Pattern.compile("^ {8}\\d\\. .*$");
        Matcher ul8M = ul8P.matcher(line);
        if(ul8M.find()) {
            mode = 6;
        }

        return mode;

    }
}
