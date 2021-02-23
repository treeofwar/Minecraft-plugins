package minecraftplugin1;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class main extends JavaPlugin implements Listener {
	private Commands commands = new Commands();
	@Override
    public void onEnable() {//enables the plugin and you can send a message to consol to see if its working as well as enables any evenets or listings
		getCommand(commands.cmd1).setExecutor(commands);//executes the command
		getCommand(commands.cmd3).setExecutor(commands);
		getCommand(commands.cmd4).setExecutor(commands);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "plugin enabled");
		getServer().getPluginManager().registerEvents(new EventsClass(),this);//registers the event from a differnent class
		loadConfig();
		
		
		CustomItems items = new CustomItems();
		//items.customRecipe();
		items.unshaped();//calls the unshaped item
	}
    @Override
    public void onDisable() {
    	getServer().getConsoleSender().sendMessage(ChatColor.RED + "plugin disabled");
    }
    public void loadConfig() {
    	getConfig().options().copyDefaults(true);//any default in config copy to config file
    	saveConfig();
    
    
    }
    
}


