package dev.virusnest.virusclient.mixin;
;
import dev.virusnest.virusclient.VirusClient;
import dev.virusnest.virusclient.module.ModuleManager;
import dev.virusnest.virusclient.module.mods.exploit.OverflowBypass;
import io.netty.channel.Channel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldBorderInitializeS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldBorderSizeChangedS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    private MinecraftClient mc = MinecraftClient.getInstance();
    @Shadow
    private Channel channel;

    @Shadow private PacketListener packetListener;

    @ModifyVariable(method = "sendImmediately", at = @At("HEAD"), ordinal = 0) // Modify outgoing packets
    private Packet<?> modifyPacket(Packet<?> packet) {

        if (ModuleManager.getModule(OverflowBypass.class).isEnabled() && packet instanceof VehicleMoveC2SPacket) {
            VehicleMoveC2SPacket pkt = (VehicleMoveC2SPacket) packet;

            Entity vehicle = mc.player.getVehicle();

            vehicle.setPos((double) (long)(pkt.getX() * 100.0) / 100.0, pkt.getY(), (double) (long)(pkt.getZ() * 100.0) / 100.0); // Hope this works

            return new VehicleMoveC2SPacket(
                    vehicle
            );
        }

        return packet;

    }

    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, PacketListener listener, CallbackInfo callback) {

        if(ModuleManager.getModule(OverflowBypass.class).isEnabled()) {
            if (packet instanceof GameStateChangeS2CPacket) {
                GameStateChangeS2CPacket pkt = (GameStateChangeS2CPacket) packet;

                if (pkt.getReason().equals(GameStateChangeS2CPacket.DEMO_MESSAGE_SHOWN)/* || pkt.getValue() == 104*/) {
                    VirusClient.LOGGER.info("GameStateChangeS2CPacket called with reason DEMO_MESSAGE_SHOWN and callback blocked!");
                    callback.cancel();
                }

                if (pkt.getReason().equals(GameStateChangeS2CPacket.GAME_MODE_CHANGED)) {
                    VirusClient.LOGGER.info("GameStateChangeS2CPacket called with reason GAME_MODE_CHANGED and callback blocked!");
                    callback.cancel();
                }
            }

            if(packet instanceof WorldBorderSizeChangedS2CPacket){
                VirusClient.LOGGER.info("WorldBorderSizeChangedS2CPacket called and callback blocked!");
                callback.cancel();
            }

            if(packet instanceof WorldBorderInitializeS2CPacket){
                VirusClient.LOGGER.info("WorldBorderSizeChangedS2CPacket called and callback blocked!");
                callback.cancel();
            }
        }
    }
}
