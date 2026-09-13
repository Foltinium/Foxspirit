package me.jakesvell.foxsp.foxspirit;

import com.projectkorra.projectkorra.BendingPlayer;
import me.jakesvell.foxsp.foxspirit.abilities.Pounce;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ablistener implements Listener {
    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event){
        if (!event.isSneaking()){
            return;
        }
        Player player = event.getPlayer();
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
        if(bPlayer == null){
            return;
        }
        if (bPlayer.getBoundAbilityName().equalsIgnoreCase("Pounce")){ // Проверка выбранного слота
           new Pounce(player);
        }
    }
}
