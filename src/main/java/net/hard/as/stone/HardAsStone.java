package net.hard.as.stone;

import net.fabricmc.api.ModInitializer;

public class HardAsStone implements ModInitializer {

    @Override
    public void onInitialize() {


        // check if the file exists, if not, create one
        HardAsStoneConfig config = HardAsStoneConfig.readConfig();

        // write to config
        config.writeConfig();
    }
}
