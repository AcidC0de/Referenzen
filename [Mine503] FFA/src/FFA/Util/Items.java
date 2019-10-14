package FFA.Util;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Items {

	public static ItemStack createItem(Material material, int amount, int subid, String Displayname) {
		
		ItemStack item = new ItemStack(material , amount , (short) subid );
		ItemMeta mitem = item.getItemMeta();
		mitem.setDisplayName(Displayname);
		item.setItemMeta(mitem);
		
		return item;
	}
	
	public static ItemStack createItemUnbreakable(Material material, int amount, int subid, String Displayname) {
		
		ItemStack item = new ItemStack(material , amount , (short) subid );
		ItemMeta mitem = item.getItemMeta();
		mitem.spigot().setUnbreakable(true);
		mitem.setDisplayName(Displayname);
		item.setItemMeta(mitem);
		
		return item;
	}

	public static ItemStack createItemLore(Material material, int subid, String Displayname, String lore) {
		
		ItemStack item = new ItemStack(material , 1 , (short) subid );
		ItemMeta mitem = item.getItemMeta();
		ArrayList<String> ilore = new ArrayList<>();
		ilore.clear();
		ilore.add("");
		ilore.add(lore);
		mitem.setDisplayName(Displayname);
		mitem.setLore(ilore);
		item.setItemMeta(mitem);
		
		return item;
	}
	
	public static ItemStack createLeatherArmor(Material material, int subid, String Displayname, Color color) {
		
		ItemStack item = new ItemStack(material , 1 , (short) subid );
		LeatherArmorMeta mitem = (LeatherArmorMeta) item.getItemMeta();
		mitem.setColor(color);
		mitem.setDisplayName(Displayname);
		item.setItemMeta(mitem);
		
		return item;
	}
	
	public static ItemStack createSkull(Material material, int subid, String Displayname, String owner) {
		
		ItemStack item = new ItemStack(material , 1 , (short) subid );
		SkullMeta mitem = (SkullMeta) item.getItemMeta();
		mitem.setOwner(owner);			
		mitem.setDisplayName(Displayname);
		item.setItemMeta(mitem);
		
		return item;
	}
	
}
