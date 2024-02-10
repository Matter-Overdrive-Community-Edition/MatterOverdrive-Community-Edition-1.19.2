//package matteroverdrive.common.item.includes;
//
//import matteroverdrive.common.item.ItemBase;
//import net.minecraft.world.item.ItemStack;
//
//import java.util.List;
//
//public class MOBaseItem extends ItemBase {
//	public MOBaseItem(String name) {
//		super(name);
//		this.setCreativeTab(MatterOverdrive.TAB_OVERDRIVE);
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
//		if (hasDetails(stack)) {
//			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
//				addDetails(stack, Minecraft.getMinecraft().player, worldIn, tooltip);
//			} else {
//				tooltip.add(MOStringHelper.MORE_INFO);
//			}
//		}
//	}
//
//	@SideOnly(Side.CLIENT)
//	public void addDetails(ItemStack itemstack, EntityPlayer player, @Nullable World worldIn, List<String> infos) {
//		if (MOStringHelper.hasTranslation(getTranslationKey(itemstack) + ".details")) {
//			String[] infoList = MOStringHelper.translateToLocal(getTranslationKey(itemstack) + ".details").split("/n");
//			for (String info : infoList) {
//				infos.add(TextFormatting.GRAY + info);
//			}
//		}
//	}
//
//	public void InitTagCompount(ItemStack stack) {
//		stack.setTagCompound(new NBTTagCompound());
//	}
//
//	public void TagCompountCheck(ItemStack stack) {
//		if (!stack.hasTagCompound()) {
//			InitTagCompount(stack);
//		}
//	}
//
//	@SideOnly(Side.CLIENT)
//	public boolean hasDetails(ItemStack stack) {
//		return false;
//	}
//}