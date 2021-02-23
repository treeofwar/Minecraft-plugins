package minecraftplugin1;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CustomItems implements Listener{
	public ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
	private ItemMeta meta = item.getItemMeta();
	private Plugin plugin = main.getPlugin(main.class);
	
 public void giveItem(Player player) {
	ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1); //creates an item stack of diamond axe
	ItemMeta meta = item.getItemMeta();//obtains the item referenced its meta a meta is everything about the item
	
	meta.setDisplayName(ChatColor.GREEN + "axe of zeus");//setting a new display name
	ArrayList<String> lore = new ArrayList<String>();
	lore.add(ChatColor.BLACK + "used by your mum");//adds the lore from the string array 
	meta.setLore(lore);//sets the lore made to the meta
	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	item.setItemMeta(meta);//sets everything back to eachother
	player.getInventory().addItem(item);
 }
 public void welcomebook(Player player) {
	 ItemStack book = new ItemStack(Material.BOOK);
	 ItemMeta meta = book.getItemMeta();
	 meta.setDisplayName(ChatColor.GREEN + " instruction book");
	 ArrayList<String> instructions = new ArrayList<String>();
	 instructions.add(ChatColor.AQUA +" read for instructions");
	 meta.setLore(instructions);
	 book.setItemMeta(meta);
	 player.getInventory().setItem(0, book);
	 
 }
 public void customRecipe() {
		NamespacedKey nsKey = new NamespacedKey(plugin, "unique_key_here");//needs a unique key for this version
		 ShapedRecipe DH = new ShapedRecipe(nsKey,item);
		 DH.shape("#% ", "#$", " $ ");//each set is a row on the table so "XXX" is the top 3 and so on spaces for empty
		 DH.setIngredient('#', Material.DIAMOND);//sets the character to whatever matieral you want
		 DH.setIngredient('%', Material.IRON_INGOT);
		 DH.setIngredient('$', Material.STICK);
		 
		 plugin.getServer().addRecipe(DH);
	 }

public void unshaped() {
	ItemStack item = new ItemStack(Material.BLAZE_POWDER, 1);
	NamespacedKey nsKey = new NamespacedKey(plugin, "unique_key_here");
	ShapelessRecipe slr = new ShapelessRecipe(nsKey,item);
	
	slr.addIngredient(3, Material.STICK);//the number sets how many of that item you need
	slr.addIngredient(3,Material.FLINT);
	plugin.getServer().addRecipe(slr);
}
}
