import java.util.regex.Pattern;

/**
 * Created by dannakajima on 5/4/17.
 */
public class Cleaner {

    public static void main(String[] args) {
        System.out.println(Cleaner.cleanXML("<tag>text</tag><anotherone=\"89\">more text with 4534 spaces</end>"));
        System.out.println(Cleaner.cleanWhiteSpace("this  is a tests   " +
                "\t\t string with lots  of  \n\n      spaces"));
        System.out.println(Cleaner.cleanFull("A full    test string with \n \t <tag>text<moretag>everything 023192390 @&^%$^$&*&^"));

    }

    public static String cleanXML(String input) {
        return input.replaceAll("<[^>]*>", "");
    }

    public static String cleanWhiteSpace(String input) {
        input = input.replaceAll("\\s\\s+", " ");
        return input.replaceAll("[\\n+]*[\\t+]*", "");
    }

    public static String cleanFull(String input) {
        return input.replaceAll("[^A-z0-9]", "");
    }
}
