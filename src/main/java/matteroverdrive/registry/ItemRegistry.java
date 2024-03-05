package matteroverdrive.registry;

import com.google.common.base.Function;

import matteroverdrive.References;
import matteroverdrive.client.ClientReferences.Colors;
import matteroverdrive.common.item.ItemPatternDrive;
import matteroverdrive.common.item.ItemUpgrade;
import matteroverdrive.common.item.ItemUpgrade.UpgradeType;
import matteroverdrive.common.item.pill.ItemAndroidPill;
import matteroverdrive.common.item.pill.types.ItemAndroidBluePill;
import matteroverdrive.common.item.pill.types.ItemAndroidRedPill;
import matteroverdrive.common.item.pill.types.ItemAndroidYellowPill;
import matteroverdrive.common.item.tools.ItemMatterContainer;
import matteroverdrive.common.item.tools.ItemMatterContainer.ContainerType;
import matteroverdrive.common.item.tools.ItemSecurityProtocol;
import matteroverdrive.common.item.tools.ItemTransporterFlashdrive;
import matteroverdrive.common.item.tools.electric.ItemBattery;
import matteroverdrive.common.item.tools.electric.ItemBattery.BatteryType;
import matteroverdrive.common.item.tools.electric.ItemCommunicator;
import matteroverdrive.common.item.tools.electric.ItemEnergyWeapon;
import matteroverdrive.common.item.tools.electric.ItemMatterScanner;
import matteroverdrive.common.item.type.TypeIsolinearCircuit;
import matteroverdrive.common.item.utils.OverdriveItem;
import matteroverdrive.common.tile.matter_network.matter_replicator.TileMatterReplicator;
import matteroverdrive.core.armor.MOArmorMaterial;
import matteroverdrive.core.registers.BulkRegister;
import matteroverdrive.core.registers.IBulkRegistryObject;
import matteroverdrive.core.tools.MOToolTiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
public class ItemRegistry {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.ID);

//  Materials
	public static final RegistryObject<Item> ITEM_DILITHIUM_CRYSTAL = ITEMS.register("dilithium_crystal",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_TRITANIUM_INGOT = ITEMS.register("tritanium_ingot",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_TRITANIUM_NUGGET = ITEMS.register("tritanium_nugget",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_TRITANIUM_DUST = ITEMS.register("tritanium_dust",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	// Is this base matter dust, and the next one refined?
	public static final RegistryObject<Item> ITEM_RAW_MATTER_DUST = ITEMS.register("raw_matter_dust",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), true));
	public static final RegistryObject<Item> ITEM_MATTER_DUST = ITEMS.register("matter_dust",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), true));
//	public static final RegistryObject<Item> ITEM_MATTER_DUST_REFINED = ITEMS.register("matter_dust_refined",
//		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), true));

//  Food
	public static final RegistryObject<Item> ITEM_ANDROID_PILL_RED = ITEMS.register("android_pill_red",
		() -> new ItemAndroidRedPill(new Item.Properties().food(ItemAndroidPill.PILLS).tab(References.FOOD),
		Colors.PILL_RED, true));
	public static final RegistryObject<Item> ITEM_ANDROID_PILL_BLUE = ITEMS.register("android_pill_blue",
		() -> new ItemAndroidBluePill(new Item.Properties().food(ItemAndroidPill.PILLS).tab(References.FOOD),
			Colors.PILL_BLUE, true));
	public static final RegistryObject<Item> ITEM_ANDROID_PILL_YELLOW = ITEMS.register("android_pill_yellow",
		() -> new ItemAndroidYellowPill(new Item.Properties().food(ItemAndroidPill.PILLS).tab(References.FOOD),
			Colors.PILL_YELLOW, true));

	public static final RegistryObject<Item> ITEM_EARL_GRAY_TEA = ITEMS.register("earl_gray_tea",
            () -> new OverdriveItem(new Item.Properties().tab(References.FOOD).food(Foods.ITEM_EARL_GRAY_TEA), false));
	public static final RegistryObject<Item> ITEM_ROMULAN_ALE = ITEMS.register("romulan_ale",
            () -> new OverdriveItem(new Item.Properties().tab(References.FOOD).food(Foods.ITEM_ROMULAN_ALE), true));
	public static final RegistryObject<Item> ITEM_EMERGENCY_RATION = ITEMS.register("emergency_ration",
            () -> new OverdriveItem(new Item.Properties().tab(References.FOOD).food(Foods.ITEM_EMERGENCY_RATION), false));

    public static class Foods {
        public static final FoodProperties ITEM_EARL_GRAY_TEA = new FoodProperties.Builder()
                .nutrition(6).saturationMod(0.8f).meat().fast().alwaysEat().effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600, 3), 0.9f).build();
        public static final FoodProperties ITEM_ROMULAN_ALE = new FoodProperties.Builder()
                .nutrition(6).saturationMod(0.6f).meat().fast().alwaysEat().effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600, 3), 0.9f).build();
        public static final FoodProperties ITEM_EMERGENCY_RATION = new FoodProperties.Builder()
                .nutrition(6).saturationMod(0.6f).meat().fast().effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600, 3), 0.9f).build();
    }

//  Storage
	public static final BulkRegister<Item> ITEM_BATTERIES = bulkItem(
		battery -> ITEMS.register(((BatteryType) battery).id(), () -> new ItemBattery((BatteryType) battery)),
		BatteryType.values());
	public static final BulkRegister<Item> ITEM_MATTER_CONTAINERS = bulkItem(
		container -> ITEMS.register(container.id(), () -> new ItemMatterContainer((ContainerType) container)),
		ContainerType.values());

//  Crafting
	public static final RegistryObject<Item> ITEM_ME_CONVERSION_MATRIX = ITEMS.register("me_conversion_matrix",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_H_COMPENSATOR = ITEMS.register("h_compensator",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_INTEGRATION_MATRIX = ITEMS.register("integration_matrix",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_MACHINE_CASING = ITEMS.register("machine_casing",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final BulkRegister<Item> ITEM_ISOLINEAR_CIRCUITS = bulkItem(
		circuit -> ITEMS.register(((TypeIsolinearCircuit) circuit).id(),
			() -> new Item(new Item.Properties().tab(References.MAIN))),
		TypeIsolinearCircuit.values());

//  Matter. <-- Is this fluid matter plasma?
	public static final RegistryObject<Item> ITEM_FORCEFIELD_EMITTER = ITEMS.register("forcefield_emitter",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_WEAPON_HANDLE = ITEMS.register("weapon_handle",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_WEAPON_RECEIVER = ITEMS.register("weapon_receiver",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_PLASMA_CORE = ITEMS.register("plasma_core",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));

//  Weapons
	public static final RegistryObject<Item> ITEM_PHASER = ITEMS.register("phaser",
		() -> new ItemEnergyWeapon(new Item.Properties().tab(References.MAIN).rarity(Rarity.UNCOMMON), true, 10000,
			true, true, 1000));
	public static final RegistryObject<Item> ITEM_PHASER_RIFLE = ITEMS.register("phaser_rifle",
		() -> new ItemEnergyWeapon(new Item.Properties().tab(References.MAIN).rarity(Rarity.UNCOMMON), true, 10000,
			true, true, 1000));
	public static final RegistryObject<Item> ITEM_PLASMA_SHOTGUN = ITEMS.register("plasma_shotgun",
		() -> new ItemEnergyWeapon(new Item.Properties().tab(References.MAIN).rarity(Rarity.UNCOMMON), true, 10000,
			true, true, 1000));
	public static final RegistryObject<Item> ITEM_ION_SNIPER = ITEMS.register("ion_sniper",
		() -> new ItemEnergyWeapon(new Item.Properties().tab(References.MAIN).rarity(Rarity.UNCOMMON), true, 10000,
			true, true, 1000));
	public static final RegistryObject<Item> ITEM_OMNI_TOOL = ITEMS.register("omni_tool",
		() -> new ItemEnergyWeapon(new Item.Properties().tab(References.MAIN).rarity(Rarity.UNCOMMON), true, 10000,
			true, true, 1000));

//  Weapon Modules
	// weapon_module_color <-- This needs reworked.
	// weapon_module_barrel <-- This needs reworked, too.
	// sniper_scope <-- Same here.
	// weapon_module_ricochet <-- Same here.
	// weapon_module_holo_sights <-- Same here.
	// holo_sights_base <-- Same here.

//  Buildings
	public static final RegistryObject<Item> SHIP_FACTORY = ITEMS.register("ship_factory",
			() -> new OverdriveItem(new Item.Properties().tab(References.BUILDINGS), true));
	public static final RegistryObject<Item> BUILDING_BASE = ITEMS.register("building_base",
			() -> new OverdriveItem(new Item.Properties().tab(References.BUILDINGS), true));
	public static final RegistryObject<Item> BUILDING_MATTER_EXTRACTOR = ITEMS.register("building_matter_extractor",
			() -> new OverdriveItem(new Item.Properties().tab(References.BUILDINGS), true));
	public static final RegistryObject<Item> BUILDING_RESIDENTIAL = ITEMS.register("building_residential",
			() -> new OverdriveItem(new Item.Properties().tab(References.BUILDINGS), true));
	public static final RegistryObject<Item> BUILDING_SHIP_HANGAR = ITEMS.register("building_ship_hangar",
			() -> new OverdriveItem(new Item.Properties().tab(References.BUILDINGS), true));
	public static final RegistryObject<Item> BUILDING_POWER_GENERATOR = ITEMS.register("building_power_generator",
			() -> new OverdriveItem(new Item.Properties().tab(References.BUILDINGS), true));

//  Ships
	public static final RegistryObject<Item> SCOUT_SHIP = ITEMS.register("scout_ship",
			() -> new OverdriveItem(new Item.Properties().tab(References.SHIPS), true));
	public static final RegistryObject<Item> SHIP_COLONIZER = ITEMS.register("ship_colonizer",
			() -> new OverdriveItem(new Item.Properties().tab(References.SHIPS), true));

//  Tools
	public static final RegistryObject<Item> ITEM_TRITANIUM_WRENCH = ITEMS.register("tritanium_wrench",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), true));
	public static final RegistryObject<Item> ITEM_TRITANIUM_AXE = ITEMS.register("tritanium_axe",
		() -> new AxeItem(MOToolTiers.TRITANIUM, 6, -3.1f, new Item.Properties().stacksTo(1).tab(References.MAIN)));
	public static final RegistryObject<Item> ITEM_TRITANIUM_PICKAXE = ITEMS.register("tritanium_pickaxe",
		() -> new PickaxeItem(MOToolTiers.TRITANIUM, 1, -2.8f, new Item.Properties().stacksTo(1).tab(References.MAIN)));
	public static final RegistryObject<Item> ITEM_TRITANIUM_SWORD = ITEMS.register("tritanium_sword",
		() -> new SwordItem(MOToolTiers.TRITANIUM, 3, -2.4f, new Item.Properties().stacksTo(1).tab(References.MAIN)));
	public static final RegistryObject<Item> ITEM_TRITANIUM_HOE = ITEMS.register("tritanium_hoe",
		() -> new HoeItem(MOToolTiers.TRITANIUM, -2, -1, new Item.Properties().stacksTo(1).tab(References.MAIN)));
	public static final RegistryObject<Item> ITEM_TRITANIUM_SHOVEL = ITEMS.register("tritanium_shovel",
		() -> new ShovelItem(MOToolTiers.TRITANIUM, 1.5f, -3, new Item.Properties().stacksTo(1).tab(References.MAIN)));
	
//  Armor - Missing Cool armor look...
	public static final RegistryObject<ArmorItem> ITEM_TRITANIUM_HELMET = ITEMS.register("tritanium_helmet",
		() -> new ArmorItem(MOArmorMaterial.TRITANIUM, EquipmentSlot.HEAD, new Item.Properties().stacksTo(1).tab(References.MAIN)));
	public static final RegistryObject<ArmorItem> ITEM_TRITANIUM_CHESTPLATE = ITEMS.register("tritanium_chetsplate",
		() -> new ArmorItem(MOArmorMaterial.TRITANIUM, EquipmentSlot.CHEST, new Item.Properties().stacksTo(1).tab(References.MAIN)));
	public static final RegistryObject<ArmorItem> ITEM_TRITANIUM_LEGGINGS = ITEMS.register("tritanium_leggings",
		() -> new ArmorItem(MOArmorMaterial.TRITANIUM, EquipmentSlot.LEGS, new Item.Properties().stacksTo(1).tab(References.MAIN)));
	public static final RegistryObject<ArmorItem> ITEM_TRITANIUM_BOOTS = ITEMS.register("tritanium_boots",
		() -> new ArmorItem(MOArmorMaterial.TRITANIUM, EquipmentSlot.FEET, new Item.Properties().stacksTo(1).tab(References.MAIN)));

//  Android
	public static final RegistryObject<Item> ITEM_ROGUE_ANDROID_ARMS = ITEMS.register("rogue_android_arms",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), true));
	public static final RegistryObject<Item> ITEM_ROGUE_ANDROID_CHEST = ITEMS.register("rogue_android_chest",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), true));
	public static final RegistryObject<Item> ITEM_ROGUE_ANDROID_HEAD = ITEMS.register("rogue_android_head",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), true));
	public static final RegistryObject<Item> ITEM_ROGUE_ANDROID_LEGS = ITEMS.register("rogue_android_legs",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), true));
	public static final RegistryObject<Item> ITEM_TRITANIUM_SPINE = ITEMS.register("tritanium_spine",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), true));

//  Misc
	public static final RegistryObject<Item> ITEM_MATTER_SCANNER = ITEMS.register("matter_scanner",
		ItemMatterScanner::new);
	public static final RegistryObject<Item> ITEM_PATTERN_DRIVE = ITEMS.register("pattern_drive",
		ItemPatternDrive::new);
	public static final RegistryObject<Item> ITEM_TRANSPORTER_FLASHDRIVE = ITEMS.register("transporter_flashdrive",
		ItemTransporterFlashdrive::new);
//  energy_pack
	public static final RegistryObject<Item> ITEM_DATAPAD = ITEMS.register("data_pad",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN).stacksTo(1), true));
	// contract
	// portable_decomposer
	// security_protocol
	// spacetime_equalizer
	// record_transformation
	// artifact
	public static final RegistryObject<Item> ITEM_TRILITHIUM_CRYSTAL = ITEMS.register("trilithium_crystal",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN).stacksTo(64), false));
  // quantum_fold_manipulator
	// Not sure where the following fit. They are new items compared to the 1.12.2 version.
	public static final RegistryObject<Item> ITEM_SUPERCONDUCTOR_MAGNET = ITEMS.register("s_magnet",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_UPGRADE_BASE = ITEMS.register("upgrade_base",
		() -> new OverdriveItem(new Item.Properties().tab(References.UPGRADES).stacksTo(16), false));
	public static final BulkRegister<Item> ITEM_UPGRADES = bulkItem(
			upgrade -> ITEMS.register(((UpgradeType) upgrade).id(), () -> new ItemUpgrade((UpgradeType) upgrade)),
			UpgradeType.values());
	public static final RegistryObject<Item> ITEM_TRITANIUM_PLATE = ITEMS.register("tritanium_plate",
			() -> new OverdriveItem(new Item.Properties().tab(References.MAIN), false));
	public static final RegistryObject<Item> ITEM_COMMUNICATOR = ITEMS.register("communicator",
			() -> new ItemCommunicator(new Item.Properties().tab(References.MAIN).stacksTo(1)));

	public static final RegistryObject<Item> ITEM_NETWORK_FLASH_DRIVE = ITEMS.register("network_flash_drive",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN).stacksTo(4), false));

	public static final RegistryObject<Item> ITEM_PORTABLE_DECOMPOSER = ITEMS.register("portable_decomposer",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN).stacksTo(1), false));

	public static final RegistryObject<Item> ITEM_SECURITY_PROTOCOL = ITEMS.register("security_protocol",
		() -> new ItemSecurityProtocol(new Item.Properties().tab(References.MAIN).stacksTo(1), false));

	public static final RegistryObject<Item> ITEM_SNIPER_SCOPE = ITEMS.register("sniper_scope",
		() -> new OverdriveItem(new Item.Properties().tab(References.MAIN).stacksTo(1), false));

//	public static final RegistryObject<Item> ITEM_SPACETIME_EQUALIZER = ITEMS.register("spacetime_equalizer",
//		() -> new Item(new Item.Properties().tab(References.MAIN).stacksTo(1)));

	private static BulkRegister<Item> bulkItem(Function<IBulkRegistryObject, RegistryObject<Item>> factory,
			IBulkRegistryObject[] bulkValues) {
		return new BulkRegister<>(factory, bulkValues);
	}
}
