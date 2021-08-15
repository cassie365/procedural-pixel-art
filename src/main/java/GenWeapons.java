import model.materials.FoodMaterial;
import model.weapons.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenWeapons {
    public static void main(String[] args) throws IOException {
        List<Weapon> weapons = new ArrayList<>();
        for(FoodMaterial f:FoodMaterial.values()){
            weapons.add(new Dagger(f));
            weapons.add(new LongSword(f));
            weapons.add(new Mace(f));
            weapons.add(new Morningstar(f));
            weapons.add(new Rapier(f));
            weapons.add(new Spear(f));
            weapons.add(new Staff(f));
        }

        for(Weapon w: weapons){
            System.out.println(w.genImage());
        }
    }
}
