package org.kentonbandy.DAO;

import org.kentonbandy.UI.Output;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CsvLoader implements DataLoader {
    private File filePath;
    private List<String> objectTypes = new ArrayList<>(List.of(
            "ATTACK",
            "ITEM",
            "ARMOR",
            "WEAPON",
            "ENEMY",
            "SHOP OWNER",
            "LOCATION"));

    public CsvLoader() {
        this.filePath = new File("src/main/resources/microcosm_database.csv");
    }

    public CsvLoader(String filePath) {
        this.filePath = new File(filePath);
    }


    public Map<String,List<String>> loadGameData() {
        // create map
        Map<String,List<String>> gameData = new HashMap<>();
        // open file with resources, add keys and values according to CSV content
        try(Scanner fileData = new Scanner(filePath);) {
            String key = "";
            while (fileData.hasNextLine()) {
                String line = fileData.nextLine();
                if (objectTypes.contains(line)) {
                    key = line.toLowerCase();
                    gameData.put(key, new ArrayList<>());
                    fileData.nextLine();
                } else if (line.equals("") || key.equals("")) continue;
                else {
                    gameData.get(key).add(line);
                }
            }
        } catch (FileNotFoundException e) {
            Output.error("The New Game source file can't be found!");
            return null;
        }
        return gameData;
    }
}
