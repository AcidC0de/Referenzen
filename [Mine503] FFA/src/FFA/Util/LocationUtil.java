package FFA.Util;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationUtil {

	public static File file = new File("plugins//FFA//Locations.yml");
	public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public static void setLoc(Location loc, String name) {
		cfg.set(name + ".World", loc.getWorld().getName());
		cfg.set(name + ".X", loc.getX());
		cfg.set(name + ".Y", loc.getY());
		cfg.set(name + ".Z", loc.getZ());
		cfg.set(name + ".Yaw", loc.getYaw());
		cfg.set(name + ".Pitch", loc.getPitch());
		savecfg();
	}
	
	public static Location getLoc(String name) {
		Location loc;
		try {
			World w = Bukkit.getWorld(cfg.getString(name + ".World"));
			double x = cfg.getDouble(name + ".X");
			double y = cfg.getDouble(name + ".Y");
			double z = cfg.getDouble(name + ".Z");
			loc = new Location(w, x, y, z);
			loc.setYaw((float) cfg.getDouble(name + ".Yaw"));
			loc.setPitch((float) cfg.getDouble(name + ".Pitch"));
		} catch (Exception ex) {
			loc = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
		}
		return loc;
	}

	public static void savecfg() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

