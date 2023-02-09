package fr.aang.gchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aang.gchat.Main;

public class Commands implements CommandExecutor {
	
	private Main _main;
	
	public Commands(Main main) {
		_main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player player = Bukkit.getPlayer(args[0]);
		
		if (player != null) {
			
			if (!(sender instanceof Player)) {
				
				if (args.length >= 8 && args[1].equals("buy")) {
					
					
					if (_main.eco.getBalance(player) >= Double.valueOf(args[2])) {
						
						_main.eco.withdrawPlayer(player, Double.valueOf(args[2]));
						String string = args[4] ;
						for (int i = 5; i < args.length; i++) {
							string += (" " + args[i]);
						}
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string);
					}
					else
						player.sendMessage("§c[⛃] §cVous n'avez pas assez d'argent");
					return true;
					
				}
				else if (args.length >= 2) {
					
					String cmd = args[1];
					for (int i = 2; i < args.length; i++) {
						cmd += (" " + args[i]);
					}
					if (!Bukkit.dispatchCommand(player, cmd)) {
						System.out.println(ChatColor.RED + "Wrong cmd command '" + cmd + "'");
						return false;
					}
					return true;
				}
				
				// "cmd " + player.getName() + " buy " + monture.getPrice() + " send monture buy " + player.getName() + " " + monture.getName()
				System.out.println(ChatColor.RED + "Wrong cmd command");
			}
			
		}
		return false;
	}
}
