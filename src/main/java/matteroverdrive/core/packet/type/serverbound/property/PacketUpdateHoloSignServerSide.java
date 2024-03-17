package matteroverdrive.core.packet.type.serverbound.property;

import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.packet.type.AbstractOverdrivePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateHoloSignServerSide extends AbstractOverdrivePacket<PacketUpdateHoloSignServerSide> {
    public final BlockPos pos;
    public final String value;

    public PacketUpdateHoloSignServerSide(BlockPos pos, String value) {
        this.pos = pos;
        this.value = value;
    }

    public static PacketUpdateHoloSignServerSide decode(FriendlyByteBuf packetBuffer) {
        BlockPos pos = packetBuffer.readBlockPos();
        String val = packetBuffer.readUtf();

        return new PacketUpdateHoloSignServerSide(pos, val);
    }

    @Override
    public void encode(FriendlyByteBuf packetBuffer) {
        packetBuffer.writeBlockPos(pos);
        packetBuffer.writeUtf(value);
    }

    @Override
    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();

        ctx.enqueueWork(() -> {
            // On the server side.
            BlockEntity entity = ctx.getSender().getLevel().getBlockEntity(pos);

            // Catches both casting and null.
            if (entity instanceof TileHoloSign holoSign) {
                holoSign.setText(value);
            }
        });
        return true;
    }
}
