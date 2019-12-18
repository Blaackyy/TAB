package me.neznamy.tab.platforms.bukkit.unlimitedtags;

import me.neznamy.tab.platforms.bukkit.TabPlayer;
import me.neznamy.tab.platforms.bukkit.packets.method.MethodAPI;
import me.neznamy.tab.shared.ITabPlayer;
import me.neznamy.tab.shared.ProtocolVersion;

public class NameTagLineManager {

	public static void removeFromRegistered(ITabPlayer armorStandOwner, ITabPlayer removed) {
		for (ArmorStand as : armorStandOwner.getArmorStands()) as.removeFromRegistered(removed);
	}
	public static ArmorStand bindLine(ITabPlayer p, String text, double heightDifference, String ID, boolean staticOffset){
		if (!NameTagX.enable) return null;
		ArmorStand as = new ArmorStand(p, text, heightDifference, ID, staticOffset);
		p.armorStands.add(as);
		return as;
	}
	public static void updateVisibility(ITabPlayer armorStandOwner) {
		for (ArmorStand as : armorStandOwner.getArmorStands().toArray(new ArmorStand[0])) as.updateVisibility();
	}
	public static void sneak(ITabPlayer armorStandOwner, boolean sneaking) {
		for (ArmorStand as : armorStandOwner.getArmorStands()) as.sneak(sneaking);
	}
	public static void destroy(ITabPlayer armorStandOwner) {
		for (ArmorStand as : armorStandOwner.getArmorStands().toArray(new ArmorStand[0])) as.destroy();
	}
	public static void destroy(ITabPlayer armorStandOwner, ITabPlayer packetReceiver){
		for (ArmorStand as : armorStandOwner.getArmorStands().toArray(new ArmorStand[0])) as.destroy(packetReceiver);
	}
	public static void spawnArmorStand(ITabPlayer armorStandOwner, ITabPlayer packetReceiver, boolean addToRegistered) {
		for (ArmorStand as : armorStandOwner.getArmorStands().toArray(new ArmorStand[0])) {
			packetReceiver.sendCustomPacket(as.getSpawnPacket(packetReceiver, addToRegistered));
			if (ProtocolVersion.SERVER_VERSION.getMinorVersion() >= 15) {
				packetReceiver.sendPacket(MethodAPI.getInstance().newPacketPlayOutEntityMetadata(as.getEntityId(), as.createDataWatcher(as.property.get(), packetReceiver).toNMS(), true));
			}
		}
	}
	public static void teleportArmorStand(ITabPlayer armorStandOwner, ITabPlayer packetReceiver) {
		for (ArmorStand as : armorStandOwner.getArmorStands()) packetReceiver.sendPacket(as.getNMSTeleportPacket(packetReceiver));
	}
	public static void teleportOwner(ITabPlayer armorStandOwner, ITabPlayer packetReceiver) {
		if (armorStandOwner == packetReceiver) return; //avoiding buggy movement when riding entities
		packetReceiver.sendPacket(MethodAPI.getInstance().newPacketPlayOutEntityTeleport(((TabPlayer)armorStandOwner).player));
	}
	public static void refreshNames(ITabPlayer armorStandOwner) {
		for (ArmorStand as : armorStandOwner.getArmorStands().toArray(new ArmorStand[0])) as.refreshName();
	}
}