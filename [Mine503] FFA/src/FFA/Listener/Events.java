package FFA.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import FFA.Main.Data;

public class Events implements Listener {

	@EventHandler
	public void onArmorStand(PlayerArmorStandManipulateEvent e) {
		Player p = e.getPlayer();
		if (!Data.build.contains(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onAchieve(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPickUpItem(PlayerPickupItemEvent e) {
		if(!Data.build.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDropItem(PlayerDropItemEvent e) {
		if(!Data.build.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
}

