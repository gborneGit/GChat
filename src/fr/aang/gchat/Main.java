package fr.aang.gchat;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.aang.gchat.commands.Commands;
import fr.aang.gchat.config.Config;
import fr.aang.gchat.listener.MessagesListener;
import fr.aang.gchat.title.TitleListener;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	public Config			config;
	public boolean			disable_all;
	public String			messsage;
	public List<String>		commands;
	
	public Economy eco;
	
	
	@Override
	public void onEnable() {
		
		if (!setupEconomy()) {
			System.out.println(ChatColor.RED + "[GChat] You must have Vault");
			getServer().getPluginManager().disablePlugin(this);
			return ;
		}
		
		this.config = new Config(this, "config.yml");
		
		getServer().getPluginManager().registerEvents(new MessagesListener(this), this);
		getServer().getPluginManager().registerEvents(new TitleListener(), this);
		getCommand("cmd").setExecutor(new Commands(this));
		
	}
	
	@Override
	public void onDisable() {
	}
	
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economy != null)
			eco = economy.getProvider();
		return (economy != null);
	}
	
	public File getDirectory() {
		return getDataFolder();
	}
}
