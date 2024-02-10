//package matteroverdrive.common.item;
//
//import net.minecraft.client.resources.model.ModelResourceLocation;
//import net.minecraft.core.NonNullList;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.client.model.generators.ItemModelProvider;
//
//public class ItemBase extends Item {
//	protected final String name;
//
//	public ItemBase(String name) {
//		this.name = name;
//		this.setRegistryName(name);
//		this.setTranslationKey(getRegistryName().toString().replace(':', '.'));
//	}
//
//	@Override
//	public void initItemModel() {
//		if (this instanceof IAdvancedModelProvider) {
//			String[] subNames = ((IAdvancedModelProvider) this).getSubNames();
//			for (int i = 0; i < subNames.length; i++) {
//				String sub = subNames[i];
//
//				MOLog.debug("Adding resource location for: '" + getRegistryName() + "_" + sub + "'");
//
//				ModelLoader.setCustomModelResourceLocation(this, i,
//					new ModelResourceLocation(getRegistryName() + "_" + sub, "inventory"));
//			}
//			return;
//		}
//
//		MOLog.debug(getRegistryName() + " has subtypes: " + getHasSubtypes());
//
//		if (!getHasSubtypes())
//			ClientUtil.registerModel(this, getRegistryName().toString());
//		else {
//			NonNullList<ItemStack> sub = NonNullList.create();
//			getSubItems(CreativeTabs.SEARCH, sub);
//			for (ItemStack stack : sub) {
//				ModelLoader.setCustomModelResourceLocation(stack.getItem(), stack.getMetadata(),
//					new ModelResourceLocation(getRegistryName(), "inventory"));
//			}
//		}
//	}
//
//	@Override
//	protected void registerModels() {
//
//	}
//}