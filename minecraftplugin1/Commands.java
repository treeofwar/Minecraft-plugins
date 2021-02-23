package minecraftplugin1;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.CommandExecute;

public class Commands extends CommandExecute implements Listener, CommandExecutor {
	Map<String,Long> cooldown = new HashMap<String,Long>();
public String cmd1 = "giveitem";
public String cmd2 = "tutorial";
public String cmd3 = "inventory";
public String cmd4 = "heal";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {//checks if the sender is a player
			if(cmd.getName().equalsIgnoreCase(cmd1)) {//checking if the command is giveitem
				if(args.length != 0) {//checks to see if the command is not just 0
					
				
				Material item = Material.getMaterial(args[0].toUpperCase());///setting the args to that material
				if(item != null) {//checks if its a real item
					Inventory inv = ((Player)sender).getInventory();//tells the server the sender is also a player
				inv.addItem(new ItemStack(item, 1));//new item
				return true;
				}
				else {
					sender.sendMessage(ChatColor.GREEN + args[0] + " not a valid item");
					return true;
				}
				
			
			}
				else {
					sender.sendMessage(ChatColor.DARK_RED + " not enough arguments");
					return true;
				}
		}
			if(cmd.getName().equalsIgnoreCase(cmd3)) {
				Player player = (Player) sender;//casts player as the sender
				CustomInventory i = new CustomInventory();//creates instance of the class can do at the top 
				i.MineerInventory(player);
			}
			if(cmd.getName().equalsIgnoreCase(cmd4)) {
				Player player = (Player) sender;
				if(cooldown.containsKey(player.getName())) {
					if(cooldown.get(player.getName()) > System.currentTimeMillis()) {
						long timeleft = (cooldown.get(player.getName()) - System.currentTimeMillis()) /1000;
						player.sendMessage(ChatColor.GOLD + "you still have " + timeleft + " seconds left");
						return true;
					}
				}
				cooldown.put(player.getName(), System.currentTimeMillis() + (5*1000));
				if(player.getHealth() !=  20) {//gets health and makes sure its not full
					player.setHealth(20);
					}
				if(player.getFoodLevel()!= 20) {
					player.setFoodLevel(20);
				}
			}
					
				
				
			
			
	}
		else {
			sender.sendMessage(ChatColor.RED + " only players can use command");//sends the player a message if they are not a player
			return true;
		}
		return false;
	}

}
	
