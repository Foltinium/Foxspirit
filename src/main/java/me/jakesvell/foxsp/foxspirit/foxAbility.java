package me.jakesvell.foxsp.foxspirit;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.ability.ElementalAbility;
import org.bukkit.entity.Player;

public abstract class foxAbility extends ElementalAbility {
    public foxAbility(Player player){
        super(player);
    }
    @Override
    public Element getElement(){
        return FoxSpirit.fox;
    }
}
