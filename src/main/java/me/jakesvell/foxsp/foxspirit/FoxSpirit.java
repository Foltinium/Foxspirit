package me.jakesvell.foxsp.foxspirit;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.ability.CoreAbility;
import org.bukkit.plugin.java.JavaPlugin;

public final class FoxSpirit extends JavaPlugin {
    public static Element fox;
    @Override
    public void onEnable() {
        fox = new Element("fox");
        CoreAbility.registerPluginAbilities(this, "me.jakesvell.foxsp.foxspirit.abilities");
        System.out.println("Юй");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
