//package matteroverdrive.common.item.weapon.module;
//
//import net.minecraft.core.NonNullList;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.client.event.RenderTooltipEvent;
//
//public class WeaponModuleColor extends WeaponModuleBase implements IWeaponColor {
//	public static final RenderTooltipEvent.Color defaultColor = new RenderTooltipEvent.Color(255, 255, 255);
//	public static final RenderTooltipEvent.Color colors[] = { new RenderTooltipEvent.Color(204, 0, 0), // red
//		new Color(0, 153, 51), // green
//		new Color(0, 102, 255), // blue
//		new Color(102, 51, 51), // brown
//		new Color(255, 153, 255), // pink
//		new Color(153, 204, 255), // sky blue
//		new Color(212, 175, 55), // gold
//		new Color(102, 255, 102), // lime green
//		new Color(30, 30, 30), // black
//		new Color(128, 128, 128) // grey
//	};
//	public static final String names[] = { "red", "green", "blue", "brown", "pink", "sky_blue", "gold", "lime_green",
//		"black", "grey" };
//
//	public WeaponModuleColor(String name) {
//		super(name);
//		applySlot(Reference.MODULE_COLOR);
//	}
//
//	public void addToDunguns() {
//		for (int i = 0; i < colors.length; i++) {
//			// TODO: Add to dungeon loot
//			/*
//			 * ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new
//			 * WeightedRandomChestContent(new ItemStack(this,1,i),1,1,1));
//			 * ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new
//			 * WeightedRandomChestContent(new ItemStack(this,1,i),1,1,1));
//			 * ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new
//			 * WeightedRandomChestContent(new ItemStack(this,1,i),1,1,1));
//			 * ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new
//			 * WeightedRandomChestContent(new ItemStack(this,1,i),1,1,1));
//			 * ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new
//			 * WeightedRandomChestContent(new ItemStack(this,1,i),1,1,1));
//			 * ChestGenHooks.getInfo(Reference.CHEST_GEN_ANDROID_HOUSE).addItem(new
//			 * WeightedRandomChestContent(new ItemStack(this,1,i),1,1,10));
//			 */
//		}
//	}
//
//	@Override
//	public String getItemStackDisplayName(ItemStack itemStack) {
//		return ("" + MOStringHelper.translateToLocal(this.getUnlocalizedNameInefficiently(itemStack) + ".name")).trim()
//			+ " (" + MOStringHelper.translateToLocal("module.color." + names[itemStack.getItemDamage()]) + ")";
//	}
//
//	@Override
//	public String getModelPath() {
//		return null;
//	}
//
//	@Override
//	public ResourceLocation getModelTexture(ItemStack module) {
//		return null;
//	}
//
//	@Override
//	public String getModelName(ItemStack module) {
//		return null;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void getSubItems(CreativeTabs creativeTabs, NonNullList<ItemStack> list) {
//		if (isInCreativeTab(creativeTabs))
//			for (int i = 0; i < colors.length; i++) {
//				list.add(new ItemStack(this, 1, i));
//			}
//	}
//
//	@Override
//	public int getColor(ItemStack module, ItemStack weapon) {
//		int damage = module.getItemDamage();
//		if (damage >= 0 && damage < colors.length) {
//			return colors[damage].getColor();
//		}
//		return defaultColor.getColor();
//	}
//}
