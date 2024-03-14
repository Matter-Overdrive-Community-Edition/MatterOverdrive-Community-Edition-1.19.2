package matteroverdrive.core.packet.type.serverbound.misc;

import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.packet.type.AbstractOverdrivePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateServerHoloSign extends AbstractOverdrivePacket<PacketUpdateServerHoloSign> {
    private String text;

    public PacketUpdateServerHoloSign(BlockPos pos, String str) {
        this.text = str;
    }

    @Override
    public void encode(FriendlyByteBuf outBuffer) {
        outBuffer.writeUtf(this.text);
    }

    @Override
    public boolean handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();

        ctx.enqueueWork(() -> {
        });

        return true;
    }

    public static PacketUpdateServerHoloSign decode(FriendlyByteBuf inBuffer) {
        return new PacketUpdateServerHoloSign(inBuffer.readBlockPos(), inBuffer.readUtf());
    }
}
