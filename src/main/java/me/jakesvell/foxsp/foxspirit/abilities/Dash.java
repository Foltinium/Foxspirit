package me.jakesvell.foxsp.foxspirit.abilities;

import com.projectkorra.projectkorra.ability.AddonAbility;
import me.jakesvell.foxsp.foxspirit.FoxAbility;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Dash extends FoxAbility implements AddonAbility {
    public Dash(Player player) {
        super(player);
        if (!this.bPlayer.canBend(this)) {
            return;
        }
        this.setFields();
        this.start();
    }
    private long cooldown;
    private double forward;
    private Location location;
    private void setFields() {
        this.cooldown = 6000;
        this.forward = 1.5;
        this.location = player.getLocation();
}
    @Override
    public void progress() {
        if (!player.isOnline() || player.isDead()) {
            remove();
            return;
        }
        dash();
        remove();
    }
    private void dash() {
        Vector direction = player.getEyeLocation().getDirection().multiply(forward);
        Objects.requireNonNull(location.getWorld()).spawnParticle(Particle.CLOUD, location, 20, 0.3, 1, 0.3, 0.02);
        player.setVelocity(direction);
        bPlayer.addCooldown(this);
    }

    @Override
    public boolean isSneakAbility() {
        return false;
    }

    @Override
    public boolean isHarmlessAbility() {
        return false;
    }

    @Override
    public boolean isIgniteAbility() {
        return false;
    }

    @Override
    public boolean isExplosiveAbility() {
        return false;
    }

    @Override
    public long getCooldown() {
        return this.cooldown;
    }

    @Override
    public String getName() {
        return "Dash";
    }

    @Override
    public Location getLocation() {
        return player.getLocation();
    }

    @Override
    public void load() {

    }

    @Override
    public void stop() {

    }

    @Override
    public String getAuthor() {
        return "JakeSvell";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}
