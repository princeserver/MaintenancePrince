package com.github.majisyou.maintenace.commands;

import com.github.majisyou.maintenace.Maintenace;
import com.github.majisyou.maintenace.system.ConfigManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class cmd_maitenance implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("maintenance_admin")) {            //permissionが持っているなら
            return true;
        }

        if (args.length == 0){
            sender.sendMessage("Maintenance_mode is "+ConfigManager.getMaintenance_mode());
            //メンテナンスモードが今どのような状態かを見ることができる
            return true;
        }

        if (args.length > 1){
            sender.sendMessage("引数の数が違います");
            return true;
        }

        if(args[0].equalsIgnoreCase("true")){
            //コマンドでtrueを打った時に
            //configの中のServer_maintenanceをtrue
            //この中にパーミッションを持っていない人はkickするコマンドを使用する。
            this.Login_player_kick(); //この下のメソッドでkick
            ConfigManager.setMaintenance_mode(args[0]);
            ConfigManager.loadConfig();
            sender.sendMessage("MaintenanceModeをONにしました");
            return true;
        }
        if(args[0].equalsIgnoreCase("false")){
             //コマンドでfalseと打った場合
             //configの中のServer_maintenanceをfalseにする
             ConfigManager.setMaintenance_mode(args[0]);
             ConfigManager.loadConfig();
             sender.sendMessage("MaintenanceModeをOFFにしました");
             return true;
        }

        sender.sendMessage("2引数目はtrueもしくはfalseにしましょう");


        return true;
    }

    public void Login_player_kick(){
        Maintenace.getInstance().getServer().getOnlinePlayers();
        Player[] players= Maintenace.getInstance().getServer().getOnlinePlayers().toArray(new Player[0]);
        if(!(players==null)){
            for (Player p : players){
                if(!p.hasPermission("maintenance_admin")){
                    p.kick(Component.text("メンテナンスを開始したよ"));
                }
            }
        }else{
            Bukkit.getLogger().info("プレイヤーがいません");
        }
    }
}
