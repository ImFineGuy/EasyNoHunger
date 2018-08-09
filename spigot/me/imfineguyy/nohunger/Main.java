package spigot.me.imfineguyy.nohunger;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public static Main instance;
	public String prefix = this.getConfig().getString("prefix").replaceAll("&", "§");
	
	
	public static final Main getInstance() {

		return instance;
		
	}
	
	
	
	@EventHandler
	public void onChange(FoodLevelChangeEvent flce) {
		if (flce.getEntityType() == EntityType.PLAYER && (flce.getEntity().hasPermission("easynohunger.nolose") || flce.getEntity().isOp())) {
			
			flce.setFoodLevel(20);
		} else {
			flce.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent pje) {
		
		// Join Plugin Info
		
		if (this.getConfig().getBoolean("joininfo") == true) {
		
			String s = pje.getJoinMessage().toString();
			Bukkit.getConsoleSender().sendMessage(s);
			Bukkit.getConsoleSender().sendMessage("§eEasyNoHunger: §aInfo Message send to §e" + pje.getPlayer().getDisplayName());
			
		pje.getPlayer().sendMessage(prefix + " §aServer is using EasyNoHunger v§6" + this.getDescription().getVersion());
		pje.getPlayer().sendMessage(prefix + " §aAuthor: §eImFineGuyy_");
		
		} else {
			String s = pje.getJoinMessage().toString();
			Bukkit.getConsoleSender().sendMessage(s);
			Bukkit.getConsoleSender().sendMessage("§eEasyNoHunger: §cInfo Message not send to §e" + pje.getPlayer().getDisplayName() + "§c. You disabled or bad configuration");
		}
		
		// Join Hunger Info
		if (this.getConfig().getBoolean("hungerinfo") == true && (pje.getPlayer().hasPermission("easynohunger.nolose") || pje.getPlayer().isOp())) {
			
			String s = pje.getJoinMessage().toString();
			Bukkit.getConsoleSender().sendMessage("§eEasyNoHunger: §aNoHunger Message send to §e" + pje.getPlayer().getDisplayName());
			
			pje.getPlayer().sendMessage(prefix + " §aYou will not lose any hunger!");
			if (pje.getPlayer().getFoodLevel() != 20) {
				
				pje.getPlayer().setFoodLevel(20);
			}
		
			
		}
		
		if (this.getConfig().getBoolean("hungerinfo") == true && !(pje.getPlayer().hasPermission("easynohunger.nolose") || pje.getPlayer().isOp())) {
			Bukkit.getConsoleSender().sendMessage("§e" + pje.getPlayer().getDisplayName() + " §cIS LOSING HUNGER!");
			Bukkit.getConsoleSender().sendMessage("§eEasyNoHunger: §aHunger Message send to §e" + pje.getPlayer().getDisplayName());
			pje.getPlayer().sendMessage(prefix + " §cYou will lose hunger!");
			
		}
		
		if (this.getConfig().getBoolean("hungerinfo") == false) {
			
			Bukkit.getConsoleSender().sendMessage("§eEasyNoHunger: §cNoHunger info Message not send to §e" + pje.getPlayer().getDisplayName() + "§c. You disabled or bad configuration");
			
		}
		
}
	
	
	
	public void onEnable() {
		
		ConsoleCommandSender cs = Bukkit.getConsoleSender();
		
		
		this.getCommand("enh").setExecutor((CommandExecutor)new ENHcommand());
		this.getCommand("easynohunger").setExecutor((CommandExecutor)new EasyNoHungerCommand());
		
		
		cs.sendMessage("===============================================");
		cs.sendMessage("");
		cs.sendMessage("§aEnabling EasyNoHunger version " + this.getDescription().getVersion());
		cs.sendMessage("§aAuthor: §eImFineGuyy_");
		
		this.saveDefaultConfig();
		cs.sendMessage("");
		cs.sendMessage("§7Prefix: " + prefix);
		
		if (this.getConfig().getBoolean("joininfo") == true) {
			cs.sendMessage("§7Join Plugin Info Message: §aEnabled");
		} else {
			cs.sendMessage("§7Join Plugin Info Message: §cDisabled");
		}
		if (this.getConfig().getBoolean("hungerinfo") == true) {
			cs.sendMessage("§7Hunger Info Message: §aEnabled");
		} else {
			cs.sendMessage("§7Hunger Info Message: §cDisabled");
		}
		Bukkit.getPluginManager().registerEvents(this, this);
		cs.sendMessage("");
		cs.sendMessage("§ePlugin loaded");
		
		cs.sendMessage("");
		cs.sendMessage("===============================================");
	}
		
	}


