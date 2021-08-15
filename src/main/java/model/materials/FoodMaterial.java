package model.materials;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.*;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum FoodMaterial {
    /*
    "#forge:crops/beetroot",
    "#forge:crops/carrot",
    "#forge:crops/nether_wart",
    "#forge:crops/potato",
    "#forge:crops/wheat"
 */
    WHEAT("Wheat","wheat","forge:crops/wheat"),
    POTATO("Potato","potato","forge:crops/potato"),
    CARROT("Carrot","carrot","forge:crops/carrot"),
    BEETROOT("Beetroot","beetroot","forge:crops/beetroot");

    private final String displayName;
    private final String name;
    private final String material;
    private final Map<String, Color> colorMap;

    private FoodMaterial(String displayName, String name, String material){
        this.displayName = displayName;
        this.name = name;
        this.material = material;
        this.colorMap = getColors();
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public Map<String, Color> getColorMap() {
        return colorMap;
    }

    private Map<String, Color> getColors(){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Reader reader = Files.newBufferedReader(Paths.get("material_colors/"+name+".json"));

            Map<String, String> map = gson.fromJson(reader, Map.class);
            Map<String,Color> output = new HashMap<>();

            for (Map.Entry<String, String> entry: map.entrySet())
            {
                output.put(entry.getKey(),Color.decode(entry.getValue()));
            }

            reader.close();

            return output;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
