package com.github.majisyou.maintenace;

import com.github.majisyou.maintenace.commands.cmd_maitenance;
import com.github.majisyou.maintenace.event.Player_Login;
import com.github.majisyou.maintenace.system.ConfigManager;
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

        //config設定
        this.saveDefaultConfig();
        ConfigManager.loadConfig();
    }

    @Override
    public void onDisable() {
        this.saveConfig();//これは現在の設定を保存する
        getLogger().info("Maintenaceプラグインを終了しました");
        // Plugin shutdown logic
    }
}

