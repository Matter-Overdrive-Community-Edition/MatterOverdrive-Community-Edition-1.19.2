package matteroverdrive.core.packet.type.clientbound.misc;

import matteroverdrive.core.packet.type.AbstractOverdrivePacket;
import matteroverdrive.core.packet.type.clientbound.PacketBarrierMethods;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketClientUpdateMNScreen extends AbstractOverdrivePacket<PacketClientUpdateMNScreen> {
	public PacketClientUpdateMNScreen() {

	}

	@Override
	public void encode(FriendlyByteBuf outBuffer) {

	}

	@Override
	public boolean handle(Supplier<NetworkEvent.Context> context) {
		NetworkEvent.Context ctx = context.get();
		ctx.enqueueWork(() -> {
			PacketBarrierMethods.handlePacketUpdateMNScreen();
		});
		return true;
	}
}
