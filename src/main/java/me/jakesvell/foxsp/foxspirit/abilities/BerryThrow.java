package me.jakesvell.foxsp.foxspirit.abilities;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.util.DamageHandler;
import me.jakesvell.foxsp.foxspirit.FoxAbility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Objects;

public class BerryThrow extends FoxAbility implements AddonAbility {
    private Location location = player.getEyeLocation();
    private Vector direction = player.getEyeLocation().getDirection().normalize();
    private double speed;
    private double range;
    private double damage;
    private double originLocation;
    private long cooldown;
    public BerryThrow(Player player) {
        super(player);
        if(!this.bPlayer.canBend(this)) return;
        this.setFields();
        this.start();
    }
    private void setFields(){
        this.location = player.getEyeLocation();
        this.direction = player.getEyeLocation().getDirection().normalize();
        this.speed = 1;
        this.damage = 1;
        this.range = 15;
        this.cooldown = 8000;
        this.originLocation = 0;
    }
    private void displayBerry(){
        org.bukkit.inventory.ItemStack berryItem = new org.bukkit.inventory.ItemStack(Material.SWEET_BERRIES);
        Objects.requireNonNull(location.getWorld()).spawnParticle(Particle.ITEM, location, 20, 0.1, 0.1, 0.1, 0.02, berryItem);
    }

    @Override
    public void progress() {
        if(!player.isOnline() || player.isDead() || this.originLocation > this.range) {
            remove();
            return;
        }
        location.add(direction.clone().multiply(speed));
        this.originLocation += this.speed;
        displayBerry();
        bPlayer.addCooldown(this);
        if(location.getBlock().getType().isSolid()) {
            remove();
            return;
        }
        if(CheckCollisions()){
            remove();
        }
    }
    private boolean CheckCollisions(){
        for (Entity entity : GeneralMethods.getEntitiesAroundPoint(location, 1.5)){
            if (entity instanceof LivingEntity target && !entity.getUniqueId().equals(player.getUniqueId())){
                DamageHandler.damageEntity(target,damage,this);
                target.getWorld().spawnParticle(Particle.BLOCK, target.getLocation().add(0,1,0),15, Material.REDSTONE_BLOCK.createBlockData());
            }
            return true;
        }
        return false;
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
        return cooldown;
    }

    @Override
    public String getName() {
        return "BerryThrow";
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
