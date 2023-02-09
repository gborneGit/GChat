package fr.aang.gchat.listener;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import fr.aang.gchat.Main;

public class MessagesListener implements Listener {
	
	private Main _main;
	
	public MessagesListener(Main main) {
		_main = main;
	}
	
	@EventHandler
	public void onList(PlayerCommandSendEvent event) {
		
			// Commande interdite
		if (!event.getPlayer().hasPermission("gchat.use")) {
			
			if (_main.disable_all) {
				event.getCommands().clear();
			}
			else {
				Iterator<String> it = event.getCommands().iterator();
				String str;
				
				while (it.hasNext()) {
					str = (String) it.next();
					if (str.contains(":")) {
						it.remove();
					}
			    }
				
				for (int i = 0; i < _main.commands.size(); i++) {
					event.getCommands().remove(_main.commands.get(i));
				}
			}
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		
		Player player = event.getPlayer();
		
		if (!player.hasPermission("gchat.use")) {
			
			if (_main.disable_all) {
				event.setCancelled(true);
				player.sendMessage(_main.messsage);
				return ;
			}
			else {
				String[] args = event.getMessage().split(" ");
				
				// Commande inexistante
				if(Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null) {
					event.setCancelled(true);
					player.sendMessage(_main.messsage);
					return ;
				}
				
				// Commande interdite
				for (int i = 0; i < _main.commands.size(); i++) {
					if (args[0].equalsIgnoreCase("/" + _main.commands.get(i))) {
						event.setCancelled(true);
						player.sendMessage(_main.messsage);
						return ;
					}
				}
			}
		}
	}
}
