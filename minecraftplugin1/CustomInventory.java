package minecraftplugin1;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class CustomInventory implements Listener {
	private Plugin plugin = main.getPlugin(main.class);
	
	public void MineerInventory(Player player) {
		
		Inventory i = plugin.getServer().createInventory(null, 9, ChatColor.DARK_GREEN + "Tutorial Inventory");
		int healthint = (int) player.getHealth();
		int foodint = player.getFoodLevel();
		
		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName("'");
		empty.setItemMeta(emptyMeta);
		
		ItemStack health = new ItemStack(Material.INK_SACK, healthint, (byte) 1);
		ItemMeta hmeta = health.getItemMeta();
		hmeta.setDisplayName(ChatColor.RED + "HEALTH");
		health.setItemMeta(hmeta);
		
		ItemStack food = new ItemStack(Material.APPLE, foodint);
		ItemMeta fmeta = food.getItemMeta();
		fmeta.setDisplayName(ChatColor.YELLOW + "FOOD");
		food.setItemMeta(fmeta);
		

		i.setItem(0,empty);
		i.setItem(1,empty);
		i.setItem(2,empty);
		i.setItem(3, health);
		i.setItem(4,empty);
		i.setItem(5, food);
		i.setItem(6,empty);
		i.setItem(7,empty);
		i.setItem(8,empty);
		
		player.openInventory(i);
	}
}
