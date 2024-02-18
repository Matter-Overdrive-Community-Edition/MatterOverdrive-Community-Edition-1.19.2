package matteroverdrive.core.packet.type.serverbound.android;

import java.util.function.Supplier;

import matteroverdrive.core.capability.MatterOverdriveCapabilities;
import matteroverdrive.core.packet.type.AbstractOverdrivePacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class PacketAndroidPerkToggle extends AbstractOverdrivePacket<PacketAndroidPerkToggle> {

	private final String perk;

	public PacketAndroidPerkToggle(String perk) {
		this.perk = perk;
	}

	@Override
	public void encode(FriendlyByteBuf outBuffer) {
		outBuffer.writeUtf(perk);
	}

	@Override
	public boolean handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			ctx.get().getSender().getCapability(MatterOverdriveCapabilities.ANDROID_DATA).ifPresent(iAndroid -> {
				iAndroid.getPerkManager().togglePerk(perk);
				iAndroid.requestUpdate();
			});
		});
		return true;
	}
	
	public static PacketAndroidPerkToggle decode(FriendlyByteBuf inBuffer) {
		return new PacketAndroidPerkToggle(inBuffer.readUtf());
	}


}
