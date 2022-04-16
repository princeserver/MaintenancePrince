package com.github.majisyou.maintenace.system;

import com.github.majisyou.maintenace.Maintenace;
import org.bukkit.configuration.Configuration;

public class ConfigManager {
    private static Maintenace plugin = Maintenace.getInstance();
    private static Configuration config = plugin.getConfig();
    private static String Maintenance_mode;

    public static void loadConfig(){
        Maintenance_mode = config.getString("Server_maintenace");
    }

    public static String getMaintenance_mode(){
        return Maintenance_mode;
    }

    public static void setMaintenance_mode(String true_or_false){
        if(true_or_false.equals("true")||true_or_false.equals("false")){
            config.set("Server_maintenace",true_or_false);
            //configの中の設定がtrueかどうかを返すようにできてる
        }
    }
}
