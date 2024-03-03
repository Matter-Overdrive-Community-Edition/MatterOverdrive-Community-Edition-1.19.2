package matteroverdrive.common.item.tools;

import matteroverdrive.common.item.utils.OverdriveItem;
import matteroverdrive.core.registers.IBulkRegistryObject;

public class ItemSecurityProtocol extends OverdriveItem {
	public ItemSecurityProtocol(Properties pProperties, boolean hasShiftTip) {
		super(pProperties, hasShiftTip);
	}

	public enum ProtocolType implements IBulkRegistryObject {
		EMPTY("empty"),
		ACCESS("access"),
		CLAIM("claim"),
		REMOVE("remove");

		public final String name;

		private ProtocolType(String name) {
			this.name = name;
		}

		@Override
		public String id() {
			return this.name;
		}

		@Override
		public String id(String id) {
			return IBulkRegistryObject.super.id(id);
		}
	}
}