package matteroverdrive.core.property.manager;

import java.util.List;

import org.apache.commons.lang3.tuple.Triple;

import com.google.common.collect.Lists;

import matteroverdrive.core.packet.NetworkHandler;
import matteroverdrive.core.packet.type.clientbound.property.PacketUpdateClientTileProperty;
import matteroverdrive.core.packet.type.serverbound.property.PacketUpdateServerTileProperty;
import matteroverdrive.core.property.Property;
import matteroverdrive.core.property.PropertyManager;
import matteroverdrive.core.property.PropertyType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BlockEntityPropertyManager extends PropertyManager {

	/**
	 * The {@link BlockEntity}.
	 */
	private final BlockEntity blockEntity;

	/**
	 * BlockEntity PropertyManager Constructor
	 *
	 * @param blockEntity The {@link BlockEntity}
	 */
	public BlockEntityPropertyManager(BlockEntity blockEntity) {
		super(Lists.newArrayList());
		this.blockEntity = blockEntity;
	}

	/**
	 * Client -> Server update method.
	 *
	 * @param property Property to update.
	 * @param value    The value to update the property with.
	 * @param <T>      The T value type.
	 */
	public <T> void updateServerBlockEntity(Property<T> property, T value) {
		short propertyId = -1;
		for (short i = 0; i < properties.size(); i++) {
			if (properties.get(i) == property) {
				propertyId = i;
			}
		}
		property.set(value);
		NetworkHandler.sendToServer(new PacketUpdateServerTileProperty(blockEntity.getBlockPos(),
				property.getPropertyType(), propertyId, value));
	}

	/**
	 * Server -> Client update method.
	 *
	 * @param blockPos The {@link BlockPos} of the {@link BlockEntity} in the world.
	 */
	public void sendBlockEntityChanges(BlockPos blockPos) {
		List<Triple<PropertyType<?>, Short, Object>> dirtyProperties = Lists.newArrayList();
		for (short i = 0; i < properties.size(); i++) {
			Property<?> property = properties.get(i);
			if (property.isDirty()) {
				dirtyProperties.add(Triple.of(property.getPropertyType(), i, property.get()));
			}
		}

		if (!dirtyProperties.isEmpty() && blockEntity.getLevel() != null) {
			NetworkHandler.sendToClientChunk(
					blockEntity.getLevel().getChunkAt(blockEntity.getBlockPos()),
					new PacketUpdateClientTileProperty(blockPos, dirtyProperties));
		}
	}
}
