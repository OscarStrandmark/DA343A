package l02;

import javax.swing.*;

import java.util.*;
import java.io.*;

public class Exercise6 {

    private HashMap<String, String> dictonary = new HashMap<String, String>();
    
    public Exercise6(String filename) {
        readDictionary(filename,dictonary);
    }

    public static void readDictionary(String filename, Map<String, String> map) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"ISO-8859-1"));
            String[] parts;
            String english, swedish;
            String str = br.readLine();
            while (str != null) {
                parts = str.split(",");
                english = parts[2];
                swedish = parts[1];
                map.put(english, swedish);
                str = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("readDictionary: " + e);
        }
    }

    public void list() {
        String strList = dictonary.toString(); // format: "{ eng1=sve1, eng2=sve2, ...}"
        strList = strList.substring(1, strList.length() - 1);
        String[] parts = strList.split(",");

        System.out.println("------------------------------------------------");
        for (String str : parts) {
            System.out.println(str);
        }
        System.out.println("------------------------------------------------");
    }
    
    public void translate() {
    	String eng = JOptionPane.showInputDialog("Ange ett engelskt ord");
    	String sve = dictonary.get("input");
    	
    	if(sve == null) {
    		JOptionPane.showMessageDialog(null, eng + " finns inte i ordlistan");
    	} else {
    		JOptionPane.showMessageDialog(null, sve + " = " + eng);
    	}
    }
    
    public String translate(String str) {
		return dictonary.get(str);
	}

    public static int menu(String[] options) {
        int res = 0;
        String input;
        String message = "V�LJ ETT ALTERNATIV\n";
        for (int i = 0; i < options.length; i++) {
            message += "\n" + (i + 1) + ". " + options[i];
        }

        do {
            try {
                input = JOptionPane.showInputDialog(message);
                if (input == null) {
                    return 0;
                }
                res = Integer.parseInt(input);
            } catch (NumberFormatException e) {
            }
        } while (res < 1 || res > options.length);

        return res;
    }

    public static void main(String[] args) {
        Exercise6 ex6 = new Exercise6("C:\\Users\\oscar\\git\\DA343A\\src\\l02\\files\\SkSvEn.txt");
        String[] menuOptions = {"�vers�tt ord", "Skriv ut ordlista"};
        int choice = Exercise6.menu(menuOptions);
        while (choice != 0) {
            switch (choice) {
                case 1:
                    ex6.translate();
                    break;
                case 2:
                    ex6.list();
                    break;
            }
            choice = Exercise6.menu(menuOptions);
        }
    }
}

