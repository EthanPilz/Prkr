package com.luzinsk.prkr.Files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import com.luzinsk.prkr.Prkr;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DataManager {

    private Prkr plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public DataManager(Prkr plugin){
        this.plugin = plugin;
        //saves/initializes the config
        saveDefaultConfig();

    }

    public void reloadConfig(){
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "checkpoints.yml");
        }
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = this.plugin.getResource("checkpoints.yml");
        if (defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);

        }

    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null)
            reloadConfig();



        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Could not save checkpoints to " + this.configFile, e);

        }

    }

    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "checkpoints.yml");

        if (!this.configFile.exists())
            this.plugin.saveResource("checkpoints.yml", false);
    }

}
