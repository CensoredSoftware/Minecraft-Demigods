package com.censoredsoftware.demigods.episodes.demo.ability.passive;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.censoredsoftware.demigods.engine.element.Ability;
import com.censoredsoftware.demigods.engine.element.Deity;

public class Swim extends Ability
{
    private final static String name = "Swim", command = null;
    private final static int cost = 0, delay = 0, repeat = 20;
    private static Info info;
    private final static Devotion.Type type = Devotion.Type.PASSIVE;
    private final static List<String> details = new ArrayList<String>(1)
    {
        {
            add("Crouch while in water to swim very fast.");
        }
    };

    public Swim(final String deity, String permission)
    {
        super(info = new Info(deity, name, command, permission, cost, delay, repeat, details, type), null, new BukkitRunnable()
        {
            @Override
            public void run()
            {
                for(Player player : Bukkit.getOnlinePlayers())
                {
                    if(!player.isSneaking() || !Deity.Util.canUseDeitySilent(player, deity)) return;

                    // PHELPS SWIMMING
                    if((player.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER) || player.getLocation().getBlock().getType().equals(Material.WATER)))
                    {
                        Vector direction = player.getLocation().getDirection().normalize().multiply(1.3D);
                        Vector victor = new Vector(direction.getX(), direction.getY(), direction.getZ());
                        player.setVelocity(victor);
                    }
                }
            }
        });
    }
}