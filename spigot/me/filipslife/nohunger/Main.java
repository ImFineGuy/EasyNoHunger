package spigot.me.filipslife.nohunger;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	public static Main instance;
	public String prefix = this.getConfig().getString("prefix").replaceAll("&", "§");
	
	
	@EventHandler
	public void onChange(FoodLevelChangeEvent flce) {
		if (flce.getEntityType() == EntityType.PLAYER && flce.getEntity().hasPermission("easynohunger.nolose") || flce.getEntity().isOp()) {
			flce.setCancelled(true);
		} else {
			flce.setCancelled(false);
		}
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent pje) {
		
		// Join Plugin Info --> Admin

		if (this.getConfig().getBoolean("joinadmin") == true) {
		
			String s = pje.getJoinMessage().toString();
			Bukkit.getConsoleSender().sendMessage(s);
			Bukkit.getConsoleSender().sendMessage("§eEasyNoHunger: §aInfo Message send to §e" + pje.getPlayer().getDisplayName());
			
		pje.getPlayer().sendMessage(prefix + " §aServer is using EasyNoHunger v.§6" + this.getDescription().getVersion());
		pje.getPlayer().sendMessage(prefix + " §aAuthor: §eFilipsLifeCZ");
		
		} else {
			String s = pje.getJoinMessage().toString();
			Bukkit.getConsoleSender().sendMessage(s);
			Bukkit.getConsoleSender().sendMessage("§eEasyNoHunger: §cInfo Message not send to §e" + pje.getPlayer().getDisplayName() + "§c. You disabled or bad configuration");
		}
		
		// Join Hunger Info
		if (pje.getPlayer().hasPermission("easynohunger.nolose") 
				|| pje.getPlayer().hasPermission("easynohunger.admin") 
					|| pje.getPlayer().isOp() == true) {
			
		pje.getPlayer().sendMessage(prefix + " §aYou will not lose any hunger!");
		
		}
	}
	
	
	
	public void onEnable() {
		
		ConsoleCommandSender cs = Bukkit.getConsoleSender();
		
		cs.sendMessage("===============================================");
		cs.sendMessage("");
		cs.sendMessage("§aEnabling EasyNoHunger version " + this.getDescription().getVersion());
		
		this.saveDefaultConfig();
		cs.sendMessage("");
		cs.sendMessage("§7Prefix: " + prefix);
		
		if (this.getConfig().getBoolean("joinadmin") == true) {
			cs.sendMessage("§7Join Plugin Info Message: §aEnabled");
		} else {
			cs.sendMessage("§7Join Plugin Info Message: §cDisabled");
		}
		Bukkit.getPluginManager().registerEvents(this, this);
		cs.sendMessage("");
		cs.sendMessage("§ePlugin loaded");
		
		cs.sendMessage("");
		cs.sendMessage("===============================================");
	}
		
	}


