package com.github.majisyou.maintenace.event;

import com.github.majisyou.maintenace.Maintenace;
import com.github.majisyou.maintenace.system.ConfigManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.logging.Logger;

public class Player_Login implements Listener {
    static String Maintenance_mode;

    @EventHandler
    public void Login_cancel (PlayerJoinEvent event){

        if(ConfigManager.getMaintenance_mode().equals("true")){ //configの中のmaintenane_modeがtrueだからっていうやつ
            if(!(event.getPlayer().isPermissionSet("prince_server_maintenance"))){ //パーミッションprince_server_maintenanceがなったら
                event.getPlayer().kick(Component.text(ChatColor.RED+"メンテナンス中です")); //キックするコマンド
//            System.getLogger(event.getPlayer().getName()+"がログインしようとしたよ");
            }else {
                event.getPlayer().sendMessage("メンテナンス中だよ");
            }
        }
    }
}
