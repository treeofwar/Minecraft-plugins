package minecraftplugin1;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class EventsClass implements Listener {
	Map<String,Long> cooldown = new HashMap<String,Long>();
	 private Plugin plugin = main.getPlugin(main.class);
	 private CustomItems items = new CustomItems();
	 private int count;
	 public String prefix = (ChatColor.BLUE + "tutorials");
	@EventHandler
	public void onMove(PlayerMoveEvent event) {//creates a movement event when the players moves 
		Player player = event.getPlayer();//casts something for the player
		Inventory pInven = player.getInventory();//gets the players inventory
		
		//ItemStack item = new ItemStack(Material.GOLDEN_APPLE, 1);//item stack is any item in the game even if its 1. you add the one after golden apples to show quantity
		
		if(!player.isOnGround()) {//checks if they are jumping
		//player.sendMessage(ChatColor.RED + "you are moving");//sends the player a message if they are jumping
	//	pInven.addItem(item);//add the item we made to the inventory
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {//creates an event that is a player left clicks or right clicks
		Action action = event.getAction();//obtains which action they is happening
		Player player  = event.getPlayer();
		Block block = event.getClickedBlock();//obtains the blocked that is clicked
		if(action.equals(Action.LEFT_CLICK_BLOCK)) {//if their action is a left click do this
			if(block.getType().equals(Material.EMERALD_BLOCK)) {//sees if the block is an emerald block
				player.sendMessage(ChatColor.GREEN + "this is a healing block");
				if(player.getHealth() !=  20) {//gets health and makes sure its not full
					player.setHealth(player.getHealth() +1);//if not full sets the health plus 1 after getting it again
				}
			}
			else {
				player.sendMessage(ChatColor.DARK_RED + block.getType().toString().toUpperCase());//if the player clicks any block besides emerald it tells the type of block
			}
		}
		
	}
	@EventHandler
	public void onBreak(BlockBreakEvent event) {//block break event
		Block block = event.getBlock();//gets the info on the block that broke
		Player player = event.getPlayer();
		Location loc = block.getLocation();
		
		player.sendMessage(ChatColor.BLUE + "you broke" + block.getType().toString().toUpperCase());
		player.sendMessage(ChatColor.AQUA + "the X location was " + loc.getBlockX());
		player.sendMessage(ChatColor.AQUA + "the X location was " + loc.getBlockY());
		player.sendMessage(ChatColor.AQUA + "the X location was " + loc.getBlockZ());
		
		if(block.getType().equals(Material.TNT)) {
			plugin.getConfig().set("users." + player.getUniqueId() + "block." + count + "world.", loc.getWorld().getName());
			plugin.getConfig().set("users." + player.getUniqueId() + "block." + count + "world.", loc.getBlockX());
			plugin.getConfig().set("users." + player.getUniqueId() + "block." + count + "world.", loc.getBlockY());		
			plugin.getConfig().set("users." + player.getUniqueId() + "block." + count + "world.", loc.getBlockZ());
			plugin.saveConfig();//how to save whats being done to the config
			count++;
		
		
		}
	
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		Location loc = block.getLocation();
		player.sendMessage(ChatColor.BLUE + "you placed" + block.getType().toString().toUpperCase());
		player.sendMessage(ChatColor.AQUA + "the X location was " + loc.getBlockX());
		player.sendMessage(ChatColor.AQUA + "the X location was " + loc.getBlockY());
		player.sendMessage(ChatColor.AQUA + "the X location was " + loc.getBlockZ());
		
		if(block.getType().equals(Material.TNT)) {//how to broadcast to everyone in game also how you get a certain players name
			plugin.getServer().broadcastMessage(ChatColor.AQUA + player.getName() + "the X location was " + loc.getBlockZ());
		}
		
		
	}
	@EventHandler
	public void onPunch(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		//items.giveItem(player);//gives player the item from customer items on punch
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage("kms");
		player.sendMessage(prefix + ChatColor.GOLD +   "welcome back " + player.getName());
		if(!player.hasPlayedBefore()) {
		items.welcomebook(player);//calls the welcome book in customitems 
		Location spawn = new Location(player.getWorld(), 10,86,6);//sets the spawn to a location variable 
		player.teleport(spawn);//gets the player when joined and teleports the to the set spawn
		
		Firework fw = player.getWorld().spawn(player.getLocation(),Firework.class);
		FireworkMeta fwm = fw.getFireworkMeta();
		Builder builder = FireworkEffect.builder();
		
		fwm.addEffect(builder.flicker(true).withColor(Color.BLUE).build());
		fwm.addEffect(builder.trail(true).build());
		fwm.addEffect(builder.withFade(Color.AQUA).build());
		fwm.addEffect(builder.with(Type.BURST).build());
		fwm.setPower(1);//sets the height of power
		fw.setFireworkMeta(fwm);
	
		}
	}
	@EventHandler
	public void craftingReward(CraftItemEvent event) {
		Player player = (Player) event.getWhoClicked();//whoever clicks it gets the effect 
		ItemStack item = event.getCurrentItem();//gets the current item in the item stack
		
		if(item.getType() == Material.DIAMOND_AXE) {//checks if the item crafted is diamond blocks
			player.sendMessage(ChatColor.YELLOW + " you have crafted " + ChatColor.AQUA + item.getType().toString());
			plugin.getServer().broadcastMessage(prefix + ChatColor.YELLOW  + player.getName() + ChatColor.LIGHT_PURPLE + " has crafted" + ChatColor.AQUA + "axe of zues" );
			player.giveExp(1);//gives xp if crafted
		
		
		}
		
		
		for(Player online : plugin.getServer().getOnlinePlayers()) {//checks for everyplayer online for this 
			online.getWorld().playSound(online.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER,1,1);
		}
	
	}
	@EventHandler
	public void InventClick(InventoryClickEvent event) {
	
		Player player = (Player) event.getWhoClicked();

		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();

		if (open == null) {
			return;
		}
		if (open.getName().equals(ChatColor.DARK_GREEN + " Miner Inventory")) {
			
			event.setCancelled(true);
			
			if (item == null || !item.hasItemMeta()) {
				return;
			}

			if (item.getItemMeta().getDisplayName().equals(ChatColor.RED + "HEALTH")) {
				player.closeInventory();
				CustomInventory ci = new CustomInventory();
				player.setHealth(20);
				ci.MineerInventory(player);
			}
		}

	}
	}
	
	































