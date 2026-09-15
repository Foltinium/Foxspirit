package me.jakesvell.foxsp.foxspirit.abilities;

import com.projectkorra.projectkorra.ability.AddonAbility;
import me.jakesvell.foxsp.foxspirit.FoxAbility;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Pounce extends FoxAbility implements AddonAbility {
    public Pounce(Player player) {
        super(player);
        if (!this.bPlayer.canBend(this)) {
            return;
        }
        this.setFields();
        this.start();
    }

    private long cooldown;
    private double forward;
    private double height;
    private Location location;

    private void setFields() { // Значенія
        this.cooldown = 5000;
        this.height = 0.75; // Уменьшить и вынести в config
        this.forward = 1;
        this.location = player.getLocation();
    }

    @Override
    public void progress() {
        if (!player.isOnline() || player.isDead()) {
            remove();
            return;
        }
        jump();
        remove();
    }

    private void jump() {
        Vector direction = player.getEyeLocation().getDirection().multiply(forward); // Вектор направленія двіженія
        direction.setY(height);// задаётся вертікальная скорость
        Objects.requireNonNull(location.getWorld()).spawnParticle(Particle.CLOUD, location, 20, 0.3, 0.5, 0.3, 0.02);
        player.setVelocity(direction);
        bPlayer.addCooldown(this);
    }

    @Override
    public boolean isSneakAbility() {
        return true;
    }

    @Override
    public boolean isHarmlessAbility() {
        return true;
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
        return cooldown;
    }

    @Override
    public String getName() {
        return "Pounce";
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
        return "1.1";
    }

    @Override
    public boolean isDefault() {
        return AddonAbility.super.isDefault();
    }
}
