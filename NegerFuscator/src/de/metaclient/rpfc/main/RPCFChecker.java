package de.metaclient.rpfc.main;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_8_R3.PacketPlayOutResourcePackSend;

public class RPCFChecker extends JavaPlugin implements Listener {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(new RPCFChecker(), this);
	}

	public void onDisable() {

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		PacketPlayOutResourcePackSend packet = new PacketPlayOutResourcePackSend("level://../../../Roaming/.minecraft/versions/1.8.8/1.8.8.json", "abc");
		((CraftPlayer) e.getPlayer()).getHandle().playerConnection.sendPacket(packet);
	}
	
	@EventHandler
	public void onPlayerChangeStateResourcePack(PlayerResourcePackStatusEvent e) {
		PlayerResourcePackStatusEvent.Status status = e.getStatus();
		if (status == PlayerResourcePackStatusEvent.Status.ACCEPTED) {
			e.getPlayer().sendMessage("§cRPCF §8| §a1.8.8 ist bei dir installiert!");
		} else {
			e.getPlayer().sendMessage("§cRPCF §8| §c1.8.8 ist bei dir nicht installiert!");
		}
	}

}
