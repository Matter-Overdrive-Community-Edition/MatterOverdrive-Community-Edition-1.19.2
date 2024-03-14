package matteroverdrive.core.packet.type.clientbound.misc;

import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.packet.type.AbstractOverdrivePacket;
import matteroverdrive.core.packet.type.clientbound.PacketBarrierMethods;
import matteroverdrive.registry.MenuRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateClientHoloSign extends AbstractOverdrivePacket<PacketUpdateClientHoloSign> {
    private BlockPos pos;
    private CompoundTag tag;

    public PacketUpdateClientHoloSign(BlockPos pos, CompoundTag tag) {
        this.pos = pos;
        this.tag = tag;
    }

    @Override
    public void encode(FriendlyByteBuf outBuffer) {
        outBuffer.writeBlockPos(pos);
        outBuffer.writeNbt(tag);
    }

    @Override
    public boolean handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();

        ctx.enqueueWork(() -> {
            // On the client.


        });

        return true;
    }

    public static PacketUpdateClientHoloSign decode(FriendlyByteBuf inBuffer) {
        return new PacketUpdateClientHoloSign(inBuffer.readBlockPos(), inBuffer.readNbt());
    }
}
