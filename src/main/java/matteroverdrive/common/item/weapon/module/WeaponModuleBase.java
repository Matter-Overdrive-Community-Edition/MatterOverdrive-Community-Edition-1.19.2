//package matteroverdrive.common.item.weapon.module;
//
//import matteroverdrive.common.item.includes.MOBaseItem;
//import net.minecraft.world.item.ItemStack;
//
//import java.util.HashMap;
//import java.util.List;
//
//public abstract class WeaponModuleBase extends MOBaseItem implements IWeaponModule {
//
//	private Map<Integer, Map<IWeaponStat, Float>> metaStatMap = new HashMap<>();
//
//	private int slot = -1;
//
//	public WeaponModuleBase(String name) {
//		super(name);
//		setCreativeTab(MatterOverdrive.TAB_OVERDRIVE_MODULES);
//		this.setHasSubtypes(true);
//		this.setMaxDamage(0);
//		this.setMaxStackSize(1);
//	}
//
//	public void applySlot(int slot) {
//		this.slot = slot;
//	}
//
//	public void applyWeaponStat(int meta, IWeaponStat stat, double d) {
//		if (!metaStatMap.containsKey(meta)) {
//			metaStatMap.put(meta, new HashMap<>());
//		}
//		Map<IWeaponStat, Float> statMap = metaStatMap.get(meta);
//		statMap.put(stat, (float) d);
//	}
//
//	@Override
//	public int getMetadata(int damage) {
//		return damage;
//	}
//
//	@Override
//	public boolean hasDetails(ItemStack stack) {
//		return true;
//	}
//
//	@Override
//	public void addDetails(ItemStack itemstack, EntityPlayer player, @Nullable World worldIn, List<String> infos) {
//		super.addDetails(itemstack, player, worldIn, infos);
//		Map<IWeaponStat, Float> statMap = metaStatMap.get(itemstack.getMetadata());
//		if (statMap != null && !statMap.isEmpty()) {
//			statMap.forEach((stat, value) -> infos.add(MOStringHelper.weaponStatToInfo(stat, value)));
//		}
//		infos.add(MOStringHelper.translateToLocal("moduleslot." + getSlot(itemstack) + ".name"));
//	}
//
//	@Override
//	public float modifyWeaponStat(IWeaponStat stat, ItemStack module, ItemStack weapon, float originalStat) {
//		Map<IWeaponStat, Float> statMap = metaStatMap.get(module.getMetadata());
//		if (statMap == null || statMap.isEmpty()) {
//			return originalStat;
//		}
//		return originalStat * statMap.getOrDefault(stat, 1f);
//	}
//
//	@Override
//	public int getSlot(ItemStack module) {
//		return slot;
//	}
//}
