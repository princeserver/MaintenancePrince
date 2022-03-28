package com.github.majisyou.maintenace;

import com.github.majisyou.maintenace.commands.cmd_maitenance;
import com.github.majisyou.maintenace.event.Player_Login;
import com.github.majisyou.maintenace.system.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Maintenace extends JavaPlugin {

    private static Maintenace instance;

    public Maintenace(){
        instance = this;
    }

    public static Maintenace getInstance(){
        return instance;
    }


    @Override
    public void onEnable() {
        getLogger().info("Maintenaceプラグインを読み込んだよ");

        getServer().getPluginManager().registerEvents(new Player_Login(), this);
        this.getCommand("maintenance_mode").setExecutor(new cmd_maitenance());
        // Plugin startup logic

        this.saveDefaultConfig();
        ConfigManager.loadConfig();

        // config.ymlが存在しない場合はファイルに出力します。
        saveDefaultConfig();
        // config.ymlを読み込みます。
        FileConfiguration config = getConfig();
        // この2つは殆ど定型文の様な物なので覚えておきましょう。
    }

    @Override
    public void onDisable() {
        this.saveConfig();
        getLogger().info("Maintenaceプラグインを終了しました");
        // Plugin shutdown logic
    }
}

