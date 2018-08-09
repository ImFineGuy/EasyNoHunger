package spigot.me.imfineguyy.nohunger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class EasyNoHungerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
        if (arg3.length == 1 && arg3[0].equalsIgnoreCase("info")) {
			this.sendMessage(sender);
		} else {
			sender.sendMessage("§cCorrect usage: /easynohunger info");
		}
		return true;
	}
	
	public void sendMessage(CommandSender sender) {
	if (!(sender instanceof Player)) {
		sender.sendMessage("");
		sender.sendMessage("§aThis server is running §6EasyNoHunger §aby §eImFineGuyy__");
		sender.sendMessage("");
		sender.sendMessage("§cOnly 1 thing you can do with this plugin in console is read this mesasage :D");
		sender.sendMessage("");
		sender.sendMessage("§eSpigot: " + "§ahttps://bit.ly/2vskPe0");
		sender.sendMessage("");
	} else {
		sender.sendMessage("");
		sender.sendMessage("§aThis server is running §6EasyNoHunger §aby §eImFineGuyy__");
		sender.sendMessage("");
		sender.sendMessage("§eSpigot: " + "§ahttps://bit.ly/2vskPe0");
		sender.sendMessage("");
		}
	}
}


