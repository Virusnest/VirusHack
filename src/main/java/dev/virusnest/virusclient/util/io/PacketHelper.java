package dev.virusnest.virusclient.util.io;

import dev.virusnest.virusclient.VirusClient;
import dev.virusnest.virusclient.mixin.ClientConnectionInvoker;
import dev.virusnest.virusclient.mixin.ClientConnectionMixin;
import dev.virusnest.virusclient.util.misc.RoundPosition;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;

public class PacketHelper {
    public static void sendPosition(Vec3d pos){
        MinecraftClient client = MinecraftClient.getInstance();
        ClientConnectionInvoker conn = (ClientConnectionInvoker) client.player.networkHandler.getConnection();
        Packet packet = new PlayerMoveC2SPacket.PositionAndOnGround(pos.getX(), pos.getY(), pos.getZ(),false);
        conn.sendIm(packet,null);
    }

}
