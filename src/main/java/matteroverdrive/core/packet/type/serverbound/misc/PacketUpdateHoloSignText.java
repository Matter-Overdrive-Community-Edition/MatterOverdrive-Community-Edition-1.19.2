package matteroverdrive.core.packet.type.serverbound.misc;

import matteroverdrive.common.tile.TileHoloSign;
import matteroverdrive.core.packet.type.AbstractOverdrivePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent.Context;

import java.util.function.Supplier;

// We need to update the text on the client side in the tile, on load, so that it has
// a copy for the renderer when it asks for it.
public class PacketUpdateHoloSignText extends AbstractOverdrivePacket<PacketUpdateHoloSignText> {
	private final BlockPos pos;
	private final String text;

	public PacketUpdateHoloSignText(BlockPos pos, String text) {
		this.pos = pos;
		this.text = text;
	}

	@Override
	public boolean handle(Supplier<Context> context) {
		Context ctx = context.get();
		ctx.enqueueWork(() -> {
			// On the client side.

			BlockEntity entity = Minecraft.getInstance().level.getBlockEntity(pos);

			if (entity instanceof TileHoloSign holoSign) {
				holoSign.setClientText(text);
			}
		});

		return true;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeBlockPos(pos);
		buf.writeUtf(text);
	}

	public static PacketUpdateHoloSignText decode(FriendlyByteBuf buf) {
		BlockPos p = buf.readBlockPos();
		String s = buf.readUtf();

		return new PacketUpdateHoloSignText(p, s);
	}
}
