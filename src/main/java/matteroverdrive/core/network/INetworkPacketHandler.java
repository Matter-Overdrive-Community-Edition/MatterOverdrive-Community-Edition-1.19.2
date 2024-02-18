package matteroverdrive.core.network;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public interface INetworkPacketHandler<T> {

	void encode(FriendlyByteBuf outBuffer);

	boolean handle(Supplier<NetworkEvent.Context> ctx);
}
