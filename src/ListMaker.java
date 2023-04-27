import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker
{
    static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        final String menu = "A - Add  D - Delete  V - View  Q - Quit  O - Open  S - Save  C - Clear";
        boolean done = false;
        String cmd = "";
        String item = "";
        int delete = 0;

        do
        {
            displayList();

            cmd = SafeInput.getRegExString(in, menu, "[AaDdVvQqOoSsCc]");
            cmd = cmd.toUpperCase();
            switch(cmd)
            {
                case "A":
                    item = SafeInput.getNonZeroLenString(in, "What is the item you would like to add to the list: ");
                    System.out.println("" + item + " has been added to the list");
                    list.add(item);
                    break;
                case "D":
                    delete = SafeInput.getInt(in, "What is the number of the item you would like to delete: ");
                    delete = delete - 1;
                    list.remove(delete);
                    System.out.println("Item number " + delete + " has been removed from the list");
                    break;
                case "V":
                    break;
                case "Q":
                    System.exit(0);
                    break;
                case "O":
                    JFileChooser chooser = new JFileChooser();
                    Path target = (new File(System.getProperty("user.dir"))).toPath();
                    target = target.resolve("src");
                    chooser.setCurrentDirectory(target.toFile());

                    try {
                        if (chooser.showOpenDialog((Component)null) == 0) {
                            target = chooser.getSelectedFile().toPath();
                            Scanner inFile = new Scanner(target);

                            while(inFile.hasNextLine()) {
                                String line = inFile.nextLine();
                                System.out.println(line);
                            }

                            inFile.close();
                        } else {
                            System.out.println("Sorry, you must select a file! Termininating!");
                            System.exit(0);
                        }
                    } catch (FileNotFoundException var6) {
                        System.out.println("File Not Found Error");
                        var6.printStackTrace();
                    } catch (IOException var7) {
                        System.out.println("IOException Error");
                        var7.printStackTrace();
                    }
                    break;
                case "S":
                    System.out.println("The List File has been saved!");
                    break;
                case "C":
                    System.out.println("The list has been cleared!");
                    list.removeAll(list);
                    break;
            }
            System.out.println("cmd is " + cmd);
        }
        while(!done);
    }

    private static void displayList()
    {
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        if (list.size() != 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                System.out.printf("%3d%35s", i+1, list.get(i));
                System.out.println();
            }
        }
        else
            System.out.println("+++             List is empty        +++");
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++");
    }
}
