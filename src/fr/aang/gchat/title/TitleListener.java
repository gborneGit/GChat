package fr.aang.gchat.title;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;



public class TitleListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		
		if (player.hasPlayedBefore())
			player.sendTitle("§aEcoCraft", "§fSurvie", 20, 40, 20);
		else
			player.sendTitle("§fBienvenue sur §aEcoCraft §f!", "§fSurvie", 20, 40, 20);
	}
}
