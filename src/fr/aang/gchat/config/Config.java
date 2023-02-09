package fr.aang.gchat.config;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import fr.aang.gchat.Main;

public class Config {
	
	private Main 				_main;
	private File				_file;
	private YamlConfiguration	_config;
	
	public Config(Main main, String file_name) {
		_main = main;
		_config = loadConfig(file_name);
		readConfig();
	}
	
	private YamlConfiguration loadConfig(String file_name) {
		
		if(!_main.getDirectory().exists()) {
			_main.getDirectory().mkdir();
		}
		
		_file = new File(_main.getDataFolder(), file_name);
		
		if (!_file.exists()) {
			_main.saveResource(file_name, false);
		}
		
		return YamlConfiguration.loadConfiguration(_file);
	}
	
	private void readConfig() {
		_main.disable_all = _config.getBoolean("forbiden_all");
		_main.messsage = _config.getString("forbiden_message").replace('&', '§');
		_main.commands = _config.getStringList("forbiden_commands");
		
	}

}
