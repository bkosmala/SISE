package puzzleSolver.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IOOperations {

    public static int[][] readFromFile(String url) {
//        System.out.println("Odczyt z pliku:");
        String line;
        ArrayList<String> input = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(url))) {
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                input.add(line);
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

        int counter = 0;
        String tab[] = {};
        int[][] result = {};
        if (input.size() > 0) {
//            System.out.println("Odkodowane:");
            String[] firstRow = input.get(0).split(" ");

            result = new int[Integer.parseInt(firstRow[0])][Integer.parseInt(firstRow[1])];
            for (int i = 1; i < input.size(); i++) {
                tab = input.get(i).split(" ");
                for (int k = 0; k < tab.length; k++) {
                    result[counter][k] = Integer.parseInt(tab[k]);
//                    System.out.print(result[counter][k] + " ");
                }
                counter++;
//                System.out.println("");
            }
        }

        return result;
    }

    public static void writeToFile(String url, String... lines) {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(url))) {
            for (String line : lines) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (Exception se) {
            System.err.println("blad sec");
        }
    }
}