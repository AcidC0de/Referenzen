package FFA.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import FFA.Main.Data;

public class BlockChangeEvent implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(Data.build.contains(e.getPlayer())) {
			e.setCancelled(false);
		} else if(!Data.build.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(Data.build.contains(e.getPlayer())) {
			e.setCancelled(false);
		} else if(!Data.build.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}
	
}

