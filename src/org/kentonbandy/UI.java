package org.kentonbandy;


public class UI {
    public static void atLocation(Location location) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(location.getName().toUpperCase() + "\n");
        String desc = location.getDescription();
        String[] wordArr = desc.split(" ");
        String output = "";
        int len = 0;
        //for (int i=0; i<desc.length(); i+=40) {
        //    if(desc.length() - i > 40) output += "\n" + desc.substring(i, i+40);
        //    else output += "\n" + desc.substring(i);
        //}
        for (String word : wordArr) {
            if ((output.length() % 60) + word.length() < 60 && output.length() != 60) {
                output += word + " ";
            } else output += "\n" + word + " ";
        }
        output += "\n\n";
        System.out.println(output);
    }
}
