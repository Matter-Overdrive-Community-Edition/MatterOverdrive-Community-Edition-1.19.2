package matteroverdrive.datagen.client;

import matteroverdrive.References;
import matteroverdrive.common.block.OverdriveBlockColors;
import matteroverdrive.common.block.type.TypeMachine;
import matteroverdrive.common.block.type.TypeMatterConduit;
import matteroverdrive.common.block.type.TypeMatterNetworkCable;
import matteroverdrive.common.item.ItemUpgrade.UpgradeType;
import matteroverdrive.common.item.tools.ItemMatterContainer.ContainerType;
import matteroverdrive.common.item.tools.electric.ItemBattery.BatteryType;
import matteroverdrive.common.item.tools.electric.ItemCommunicator;
import matteroverdrive.common.item.type.TypeIsolinearCircuit;
import matteroverdrive.common.recipe.item2item.specific_machines.InscriberRecipe;
import matteroverdrive.common.tile.TileTritaniumCrate;
import matteroverdrive.common.tile.TileTritaniumCrate.CrateColors;
import matteroverdrive.registry.BlockRegistry;
import matteroverdrive.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OverdriveLangKeyProvider extends LanguageProvider {

	private String locale;

	public OverdriveLangKeyProvider(DataGenerator gen, String locale) {
		super(gen, References.ID, locale);
		this.locale = locale;
	}

	@Override
	protected void addTranslations() {

		switch (locale) {
		case "ru_ru":
			add("itemGroup.itemgroup" + References.ID + "main", "Энергетическая ячейка");

			addItem(ItemRegistry.ITEM_MATTER_SCANNER, "Сканер материи");
			addItem(ItemRegistry.ITEM_EMERGENCY_RATION, "Аварийный паёк");
			addItem(ItemRegistry.ITEM_PHASER, "Фазер");
			addItem(ItemRegistry.ITEM_BATTERIES.get(BatteryType.REGULAR), "Батарейка");
			addItem(ItemRegistry.ITEM_BATTERIES.get(BatteryType.CREATIVE), "Творческая батарейка");
			addItem(ItemRegistry.ITEM_ME_CONVERSION_MATRIX, "Материально-энергетическая конвертирующая матрица");
			addItem(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1), "Изолинейная схема MK1");
			addItem(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2), "Изолинейная схема MK2");
			addItem(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3), "Изолинейная схема MK3");
			addItem(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER4), "Изолинейная схема MK4");
			addItem(ItemRegistry.ITEM_UPGRADE_BASE, "Оболочка улучшений");
			addItem(ItemRegistry.ITEM_H_COMPENSATOR, "Гейзенберговский компенсатор");
			addItem(ItemRegistry.ITEM_INTEGRATION_MATRIX, "Матрица интеграции");
			addItem(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET, "Сверхпроводящий магнит");
//			item.matteroverdrive.matter_dust_refined.name=Очишенная материальная пыль
			addItem(ItemRegistry.ITEM_MATTER_DUST, "Материальная пыль");

			addItemDescTooltip(ItemRegistry.ITEM_MATTER_DUST, "Положить в утилизатор, чтобы очистить");
			addItem(ItemRegistry.ITEM_MACHINE_CASING, "Машинный корпус");
			addItem(ItemRegistry.ITEM_DILITHIUM_CRYSTAL, "Дилитиевый кристалл");
			addItem(ItemRegistry.ITEM_TRITANIUM_INGOT, "Тритановый слиток");
			addItem(ItemRegistry.ITEM_TRITANIUM_PLATE, "Тритановая пластина");
			addItem(ItemRegistry.ITEM_TRITANIUM_DUST, "Тритановая пыль");
			addItem(ItemRegistry.ITEM_PATTERN_DRIVE, "Шаблон привода");
			addItemDescTooltip(ItemRegistry.ITEM_PATTERN_DRIVE, "Хранит 2 шаблона предмета");
			addItem(ItemRegistry.ITEM_EARL_GRAY_TEA, "Чай. Эрл Грей. Горячий.");
			addItem(ItemRegistry.ITEM_ROMULAN_ALE, "Ромуланский эль");
			addItemDescTooltip(ItemRegistry.ITEM_ROMULAN_ALE, "Высоко опьяняющий алкогольный напиток ромуланского происхождения");
			addItem(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET, "Сверхпроводящий магнит");

			// Not implemented yet.

//			item.matteroverdrive.security_protocol.empty.name=Протокол безопасности
//			item.matteroverdrive.security_protocol.claim.name=Протокол безопасности [Запрос]
//			item.matteroverdrive.security_protocol.claim.details=Защищает машины
//			item.matteroverdrive.security_protocol.access.name=Протокол безопасности [Доступ]
//			item.matteroverdrive.security_protocol.access.details=Позволяет получить доступ к защищенным машинам
//			item.matteroverdrive.security_protocol.remove.name=Протокол безопасности [Удаление]
//			item.matteroverdrive.security_protocol.remove.details=Убирает защиту у машин
//			item.matteroverdrive.security_protocol.invalid=Неверный протокол безопасности

			addItem(ItemRegistry.ITEM_TRITANIUM_WRENCH, "Тритановый ключ");
			addItemDescTooltip(ItemRegistry.ITEM_TRITANIUM_WRENCH, "Безопасно демонтирует и вращает машины");

			// Not implemented yet.
//			item.matteroverdrive.spacetime_equalizer.name=Пространственно-временной эквалайзер
//			item.matteroverdrive.spacetime_equalizer.details=Может быть одет в слот нагрудника, чтобы предотвратить влияние гравитационных сил
//			item.matteroverdrive.rogue_android_part.melee=Рукопашный Андроид
//			item.matteroverdrive.rogue_android_part.range=Дальнобойный Андроид

			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_HEAD, "Андроид-голова");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_ARMS, "Андроид-рука");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_LEGS, "Андроид-нога");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_CHEST, "Андроид-торс");
//			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_MELEE, "Рукопашный Андроид");
//			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_RANGE, "Дальнобойный Андроид")

			addItem(ItemRegistry.ITEM_FORCEFIELD_EMITTER, "Излучатель силового поля");
			addItem(ItemRegistry.ITEM_BATTERIES.get(BatteryType.HIGHCAPACITY), "Высокоёмкостная батарейка");
			addItem(ItemRegistry.ITEM_ANDROID_PILL_RED, "Красная пилюля");
			addItem(ItemRegistry.ITEM_ANDROID_PILL_BLUE, "Синяя пилюля");

			addItemDescTooltip(ItemRegistry.ITEM_ANDROID_PILL_RED, "Примите красную пилюлю /n и забудьте о том, что вас сдерживает");
			addItemDescTooltip(ItemRegistry.ITEM_ANDROID_PILL_BLUE, "Примите синюю пилюлю /n и вы проснетесь в постели более мудрым");

			// Not implemented yet.
//			addItem(ItemRegistry.ITEM_CREATIVE_PATTERN_DRIVE, "Творческий шаблон привода");
//			addItem(ItemRegistry.ITEM_NETWORK_FLASH_DRIVE, "Сетевой флэш-накопитель");
//			item.matteroverdrive.creative_pattern_drive.name=Творческий шаблон привода
//			item.matteroverdrive.creative_pattern_drive.details=Содержит наиболее воспроизводимые элементы /n ПКМ во время приседа, чтобы перезагрузить
//			item.matteroverdrive.network_flash_drive.name=Сетевой флэш-накопитель
//			item.matteroverdrive.network_flash_drive.details=Используется в качестве фильтра в устройствах материальной сети

//			item.matteroverdrive.name=Энергетическая ячейка

			// Not implemented.
//			item.matteroverdrive.energy_pack.details=Используется для быстрой подзарядки./nДля "подзарядки" энергетических оружий

			addItem(ItemRegistry.ITEM_PHASER_RIFLE, "Фазерная винтовка");
			addItem(ItemRegistry.ITEM_TRANSPORTER_FLASHDRIVE, "Транспортный флэш-накопитель");
			addItemDescTooltip(ItemRegistry.ITEM_TRANSPORTER_FLASHDRIVE, "Используется для обозначения места для быстрого использования Транспортера");

			addItem(ItemRegistry.ITEM_MATTER_CONTAINERS.get(ContainerType.REGULAR), "Контейнер материи (Пустой)");

			// Not implemented. I don't know why this is even a thing as a separate item.
//			item.matteroverdrive.matter_container_full.name=Контейнер материи (Полный)
			addItem(ItemRegistry.ITEM_MATTER_CONTAINERS.get(ContainerType.REGULAR), "Контейнер материи");

			addItem(ItemRegistry.ITEM_TRITANIUM_NUGGET, "Тритановый самородок");

			// Not implemented.
//			item.matteroverdrive.tritanium_spine.name=Тритановый позвоночник

			addItem(ItemRegistry.ITEM_DATAPAD, "Датапад");
			addItemDescTooltip(ItemRegistry.ITEM_DATAPAD, "Содержит информацию о всём в Matter Overdrive");

			addItem(ItemRegistry.ITEM_OMNI_TOOL, "Омнитул");

			addItem(ItemRegistry.ITEM_ANDROID_PILL_YELLOW, "Жёлтая пилюля");
			addItemDescTooltip(ItemRegistry.ITEM_ANDROID_PILL_YELLOW, "Используется для сброса всех разблокированных способностей Андроида");

			addItem(ItemRegistry.ITEM_TRITANIUM_HELMET, "Тритановый шлем");
			addItem(ItemRegistry.ITEM_TRITANIUM_CHESTPLATE, "Тритановый нагрудник");
			addItem(ItemRegistry.ITEM_TRITANIUM_LEGGINGS, "Тритановые поножи");
			addItem(ItemRegistry.ITEM_TRITANIUM_BOOTS, "Тритановые ботинки");
			addItem(ItemRegistry.ITEM_TRITANIUM_HOE, "Тритановая мотыга");
			addItem(ItemRegistry.ITEM_TRITANIUM_SWORD, "Тритановый меч");
			addItem(ItemRegistry.ITEM_TRITANIUM_PICKAXE, "Тритановая кирка");
			addItem(ItemRegistry.ITEM_TRITANIUM_AXE, "Тритановый топор");
			addItem(ItemRegistry.ITEM_TRITANIUM_SHOVEL, "Тритановая лопата");

			addItem(ItemRegistry.ITEM_WEAPON_HANDLE, "Рукоятка оружия");
			addItem(ItemRegistry.ITEM_WEAPON_RECEIVER, "Приёмник оружия");
			addItem(ItemRegistry.ITEM_PLASMA_CORE, "Плазменное ядро");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_ARMS, "Руки бунтарского андроида");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_CHEST, "Грудь бунтарского андроида");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_HEAD, "Голова бунтарского андроида");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_LEGS, "Ноги бунтарского андроида");
			addItem(ItemRegistry.ITEM_TRITANIUM_SPINE, "Тританиевая позвоночник");

			addItem(ItemRegistry.ITEM_TRILITHIUM_CRYSTAL, "Трилитиевый кристалл");

			// Not implemented.
//			item.matteroverdrive.contract.name=Контракт

			addItem(ItemRegistry.ITEM_ION_SNIPER, "Ионная винтовка");
			addItem(ItemRegistry.ITEM_PLASMA_SHOTGUN, "Плазменный дробовик");

			// Not implemented.
//			item.matteroverdrive.plasma_shotgun.name=Плазменный дробовик
//			item.matteroverdrive.weapon_receiver.name=Оружейный приёмник
//			item.matteroverdrive.weapon_handle.name=Оружейная рукоять

			// Not implemented.
//			item.matteroverdrive.plasma_core.name=Плазменное ядро
//			item.matteroverdrive.portable_decomposer.name=Портативный разлагатель
//			item.matteroverdrive.portable_decomposer.details=Разлагает подобранные предметы в плазменную материю
//			item.record.matteroverdrive.transformation.desc=BoxCat Games - Trace Route

			// Not implemented.
//			item.matteroverdrive.trilithium_crystal.name=Чистый Трилитиевый кристалл
//			item.matteroverdrive.artifact.name=Неизвестный артефакт
//			item.matteroverdrive.quantum_fold_manipulator.name=Квантовый складной манипулятор

//			item.matteroverdrive.matter.name=Материя

			addBlock(BlockRegistry.BLOCK_MACHINE_HULL, "Корпус машины");

			case "en_us":
			default:

			add("itemGroup.itemgroup" + References.ID + "main", "Matter Overdrive");
			add("itemGroup.itemgroup" + References.ID + "modules", "Matter Overdrive: Modules");
			add("itemGroup.itemgroup" + References.ID + "upgrades", "Matter Overdrive: Upgrades");
			add("itemGroup.itemgroup" + References.ID + "food", "Matter Overdrive: Food");
			add("itemGroup.itemgroup" + References.ID + "ships", "Matter Overdrive: Ships");
			add("itemGroup.itemgroup" + References.ID + "buildings", "Matter Overdrive: Buildings");
			add("itemGroup.itemgroup" + References.ID + "decorative", "Matter Overdrive: Decorative");

			addItem(ItemRegistry.ITEM_RAW_MATTER_DUST, "Raw Matter Dust");
			addItem(ItemRegistry.ITEM_MATTER_DUST, "Matter Dust");
			addItem(ItemRegistry.ITEM_UPGRADE_BASE, "Upgrade Shell");
			addItem(ItemRegistry.ITEM_UPGRADES.get(UpgradeType.SPEED), "Speed Upgrade");
			addItem(ItemRegistry.ITEM_UPGRADES.get(UpgradeType.MATTER_STORAGE), "Matter Storage Upgrade");
			addItem(ItemRegistry.ITEM_UPGRADES.get(UpgradeType.POWER), "Power Upgrade");
			addItem(ItemRegistry.ITEM_UPGRADES.get(UpgradeType.POWER_STORAGE), "Power Storage Upgrade");
			addItem(ItemRegistry.ITEM_UPGRADES.get(UpgradeType.FAIL_SAFE), "Fail-Safe Upgrade");
			addItem(ItemRegistry.ITEM_UPGRADES.get(UpgradeType.HYPER_SPEED), "Hyper Speed Upgrade");
			addItem(ItemRegistry.ITEM_UPGRADES.get(UpgradeType.RANGE), "Range Upgrade");
			addItem(ItemRegistry.ITEM_UPGRADES.get(UpgradeType.MUFFLER), "Muffler Upgrade");
			addItem(ItemRegistry.ITEM_ION_SNIPER, "Ion Sniper");
			addItem(ItemRegistry.ITEM_PHASER_RIFLE, "Phaser Rifle");
			addItem(ItemRegistry.ITEM_PHASER, "Phaser");
			addItem(ItemRegistry.ITEM_PLASMA_SHOTGUN, "Plasma Shotgun");
			addItem(ItemRegistry.ITEM_OMNI_TOOL, "Omni Tool");
			addItem(ItemRegistry.SHIP_FACTORY, "Ship Factory");
			addItem(ItemRegistry.BUILDING_BASE, "Base");
			addItem(ItemRegistry.BUILDING_MATTER_EXTRACTOR, "Matter Extractor");
			addItem(ItemRegistry.BUILDING_RESIDENTIAL, "Residential Buildings");
			addItem(ItemRegistry.BUILDING_SHIP_HANGAR, "Ship Hangar");
			addItem(ItemRegistry.BUILDING_POWER_GENERATOR, "Power Generator");
			addItem(ItemRegistry.SCOUT_SHIP, "Scout Ship");
			addItem(ItemRegistry.SHIP_COLONIZER, "Colonizer Ship");
			addItem(ItemRegistry.ITEM_BATTERIES.get(BatteryType.REGULAR), "Battery");
			addItem(ItemRegistry.ITEM_BATTERIES.get(BatteryType.HIGHCAPACITY), "High-Capacity Battery");
			addItem(ItemRegistry.ITEM_BATTERIES.get(BatteryType.CREATIVE), "Creative Battery");
			addItem(ItemRegistry.ITEM_MATTER_CONTAINERS.get(ContainerType.REGULAR), "Matter Container");
			addItem(ItemRegistry.ITEM_MATTER_CONTAINERS.get(ContainerType.CREATIVE), "Creative Matter Container");
			addItem(ItemRegistry.ITEM_MACHINE_CASING, "Machine Casing");
			addItem(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER1), "Isolinear Circuit Mk1");
			addItem(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER2), "Isolinear Circuit Mk2");
			addItem(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER3), "Isolinear Circuit Mk3");
			addItem(ItemRegistry.ITEM_ISOLINEAR_CIRCUITS.get(TypeIsolinearCircuit.TIER4), "Isolinear Circuit Mk4");
			addItem(ItemRegistry.ITEM_TRANSPORTER_FLASHDRIVE, "Location Flashdrive");
			addItem(ItemRegistry.ITEM_PATTERN_DRIVE, "Pattern Drive");
			addItem(ItemRegistry.ITEM_MATTER_SCANNER, "Matter Scanner");
			addItem(ItemRegistry.ITEM_TRITANIUM_PLATE, "Tritanium Plate");
			addItem(ItemRegistry.ITEM_TRITANIUM_INGOT, "Tritanium Ingot");
			addItem(ItemRegistry.ITEM_TRITANIUM_NUGGET, "Tritanium Nugget");
			addItem(ItemRegistry.ITEM_TRITANIUM_DUST, "Tritanium Dust");
			addItem(ItemRegistry.ITEM_ANDROID_PILL_BLUE, "Blue Pill");
			addItem(ItemRegistry.ITEM_ANDROID_PILL_RED, "Red Pill");
			addItem(ItemRegistry.ITEM_ANDROID_PILL_YELLOW, "Yellow Pill");
			addItem(ItemRegistry.ITEM_COMMUNICATOR, "Communicator");
			addItem(ItemRegistry.ITEM_EARL_GRAY_TEA, "Tea. Earl Grey. Hot.");
			addItem(ItemRegistry.ITEM_ROMULAN_ALE, "Romulan Ale");
			addItem(ItemRegistry.ITEM_EMERGENCY_RATION, "Emergency Ration");
			addItem(ItemRegistry.ITEM_ME_CONVERSION_MATRIX, "ME Conversion Matrix");
			addItem(ItemRegistry.ITEM_H_COMPENSATOR, "Heisenberg Compensator");
			addItem(ItemRegistry.ITEM_INTEGRATION_MATRIX, "Integration Matrix");
			addItem(ItemRegistry.ITEM_SUPERCONDUCTOR_MAGNET, "Superconductor Magnet");
			addItem(ItemRegistry.ITEM_TRITANIUM_WRENCH, "Tritanium Wrench");
			addItem(ItemRegistry.ITEM_DATAPAD, "Datapad");
			addItem(ItemRegistry.ITEM_DILITHIUM_CRYSTAL, "Dilithium Crystal");
			addItem(ItemRegistry.ITEM_FORCEFIELD_EMITTER, "Force Field Emitter");
			addItem(ItemRegistry.ITEM_WEAPON_HANDLE, "Weapon Handle");
			addItem(ItemRegistry.ITEM_WEAPON_RECEIVER, "Weapon Reciver");
			addItem(ItemRegistry.ITEM_PLASMA_CORE, "Plasma Core");
			addItem(ItemRegistry.ITEM_TRITANIUM_AXE, "Tritanium Axe");
			addItem(ItemRegistry.ITEM_TRITANIUM_PICKAXE, "Tritanium Pickaxe");
			addItem(ItemRegistry.ITEM_TRITANIUM_SWORD, "Tritanium Sword");
			addItem(ItemRegistry.ITEM_TRITANIUM_HOE, "Tritanium Hoe");
			addItem(ItemRegistry.ITEM_TRITANIUM_SHOVEL, "Tritanium Shovel");
			addItem(ItemRegistry.ITEM_TRITANIUM_HELMET, "Tritanium Helmet");
			addItem(ItemRegistry.ITEM_TRITANIUM_CHESTPLATE, "Tritanium Chestplate");
			addItem(ItemRegistry.ITEM_TRITANIUM_LEGGINGS, "Tritanium Leggings");
			addItem(ItemRegistry.ITEM_TRITANIUM_BOOTS, "Tritanium Boots");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_ARMS, "Rogue Android Arms");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_CHEST, "Rogue Android Chest");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_HEAD, "Rogue Android Head");
			addItem(ItemRegistry.ITEM_ROGUE_ANDROID_LEGS, "Rogue Android Legs");
			addItem(ItemRegistry.ITEM_TRITANIUM_SPINE, "Tritanium Spine");
			addItem(ItemRegistry.ITEM_TRILITHIUM_CRYSTAL, "Trilithium Crystal");
			addItem(ItemRegistry.ITEM_SNIPER_SCOPE, "Snipe Scope");
			addItem(ItemRegistry.ITEM_SECURITY_PROTOCOL, "Security Protocol");
			addItem(ItemRegistry.ITEM_PORTABLE_DECOMPOSER, "Portable Decomposer");
			addItem(ItemRegistry.ITEM_NETWORK_FLASH_DRIVE, "Network Flash Drive");
//			addItem(ItemRegistry.ITEM_SPACETIME_EQUALIZER, "Spacetime Equalizer.");

			for (OverdriveBlockColors color : OverdriveBlockColors.values()) {
				String name = getNameFromEnum(color.toString());
				addBlock(BlockRegistry.BLOCK_COLORED_TRITANIUM_PLATING.get(color), name + " Tritanium Plating");
				addBlock(BlockRegistry.BLOCK_FLOOR_TILE.get(color), name + " Floor Tile");
				addBlock(BlockRegistry.BLOCK_FLOOR_TILES.get(color), name + " Floor Tiles");
			}
			addBlock(BlockRegistry.BLOCK_REGULAR_TRITANIUM_PLATING, "Tritanium Plating");
			for (CrateColors color : TileTritaniumCrate.CrateColors.values()) {
				String name = getNameFromEnum(color.toString());
				if (name.equals("Reg")) {
					addBlock(BlockRegistry.BLOCK_TRITANIUM_CRATES.get(color), "Tritanium Crate");
				} else {
					addBlock(BlockRegistry.BLOCK_TRITANIUM_CRATES.get(color), name + " Tritanium Crate");
				}

			}
			addBlock(BlockRegistry.DEEPSLATE_TRITANIUM_ORE, "Deepslate Tritanium Ore");
			addBlock(BlockRegistry.DEEPSLATE_DILITHIUM_ORE, "Deepslate Dilithium Ore");
			addBlock(BlockRegistry.TRITANIUM_ORE, "Tritanium Ore");
			addBlock(BlockRegistry.DILITHIUM_ORE, "Dilithium Ore");
			addBlock(BlockRegistry.BLOCK_INDUSTRIAL_GLASS, "Industrial Glass");
			addBlock(BlockRegistry.BLOCK_SOLAR_PANEL, "Solar Panel");
			addBlock(BlockRegistry.BLOCK_MATTER_DECOMPOSER, "Matter Decomposer");
			addBlock(BlockRegistry.BLOCK_MATTER_RECYCLER, "Matter Recycler");
			addBlock(BlockRegistry.BLOCK_CHARGER_CHILD, "Charger Child");
			addBlock(BlockRegistry.BLOCK_CHARGER, "Android Charger");
			addBlock(BlockRegistry.BLOCK_MICROWAVE, "Microwave");
			addBlock(BlockRegistry.BLOCK_INSCRIBER, "Inscriber");
			addBlock(BlockRegistry.BLOCK_TRANSPORTER, "Transporter");
			addBlock(BlockRegistry.BLOCK_SPACETIME_ACCELERATOR, "Spacetime Accelerator");
			addBlock(BlockRegistry.BLOCK_CHUNKLOADER, "Chunkloader");
			addBlock(BlockRegistry.BLOCK_MATTER_ANALYZER, "Matter Analyzer");
			addBlock(BlockRegistry.BLOCK_PATTERN_STORAGE, "Pattern Storage");
			addBlock(BlockRegistry.BLOCK_PATTERN_MONITOR, "Pattern Monitor");
			addBlock(BlockRegistry.BLOCK_MATTER_REPLICATOR, "Matter Replicator");
			addBlock(BlockRegistry.BLOCK_MACHINE_HULL, "Machine Hull");
			addBlock(BlockRegistry.BLOCK_MATTER_CONDUITS.get(TypeMatterConduit.REGULAR), "Matter Conduit");
			addBlock(BlockRegistry.BLOCK_MATTER_CONDUITS.get(TypeMatterConduit.HEAVY), "Heavy Matter Conduit");
			addBlock(BlockRegistry.BLOCK_MATTER_NETWORK_CABLES.get(TypeMatterNetworkCable.REGULAR), "Network Cable");
			addBlock(BlockRegistry.BLOCK_TRITANIUM, "Tritanium Block");
			addBlock(BlockRegistry.BLOCK_ANDROID_STATION, "Android Station");
//			addBlock(BlockRegistry.BLOCK_DISC_MANIPULATOR, "Disc Manipulator");
			addBlock(BlockRegistry.BLOCK_DECORATIVE_BEAMS, "Decorative Beams");
			addBlock(BlockRegistry.BLOCK_DECORATIVE_CARBON_FIBER_PLATE, "Carbon Fiber Plate");
			addBlock(BlockRegistry.BLOCK_DECORATIVE_CLEAN, "Tritanium Smooth Plate");
			addBlock(BlockRegistry.BLOCK_DECORATIVE_COILS, "Decorative Coils");
			addBlock(BlockRegistry.HOLO_MATRIX, "Holographic Matrix");
			addBlock(BlockRegistry.YELLOW_STRIPES, "Yellow Stripes");
			addBlock(BlockRegistry.EXHAUST_PLASMA, "Engine Exhaust Plasma");
			addBlock(BlockRegistry.SOFT_WALL_PLATES, "Soft Wall Plates");
			addBlock(BlockRegistry.TRITANIUM_RAIL, "Tritanium Rail");
			addBlock(BlockRegistry.TRITANIUM_PLATE_STRIPE, "Tritanium Plate Guided");
			addBlock(BlockRegistry.MATTER_TUBE, "Matter Tube");
			addBlock(BlockRegistry.TRITANIUM_LAMP, "Tritanium Lamp");
			addBlock(BlockRegistry.BLOCK_STAR_MAP, "Star Map");
			addBlock(BlockRegistry.BLOCK_WEAPON_STATION, "Weapon Station");
			addBlock(BlockRegistry.BLOCK_NETWORK_SWITCH, "Network Switch");
			addBlock(BlockRegistry.BLOCK_VENT_CLOSED, "Closed Vents");
			addBlock(BlockRegistry.BLOCK_VENT_OPEN, "Open Vents");
			addBlock(BlockRegistry.BLOCK_HOLO_SIGN, "Holo Sign");

			addTooltip("energystored", "%1$s / %2$s %3$sFE");
			addTooltip("creativeenergystored", "INFINITE");
			addTooltip("matterval", "Matter: %s");
			addTooltip("potmatterval", "Potential Matter: %s");
			addTooltip("nomatter", "NONE");
			addTooltip("openmenu", "Open Menu");
			addTooltip("closemenu", "Close Menu");
			addTooltip("menuhome", "Home");
			addTooltip("menusettings", "Settings");
			addTooltip("menuupgrades", "Upgrades");
			addTooltip("matterstored", "%1$s / %2$s %3$skM");
			addTooltip("usage", "%s");
			addTooltip("usagetick", "%s/t");
			addTooltip("menuio", "I/O");
			addTooltip("ioinput", "Input");
			addTooltip("iooutput", "Output");
			addTooltip("ionone", "None");
			addTooltip("iotop", "Top");
			addTooltip("iobottom", "Bottom");
			addTooltip("ioleft", "Left");
			addTooltip("ioright", "Right");
			addTooltip("iofront", "Front");
			addTooltip("ioback", "Back");
			addTooltip("io", "%1$s (%2$s)");
			addTooltip("upgradeinfo", "Hold %s for Details");
			addTooltip("controlkey", "Ctrl");
			addTooltip("speedbonus", "Speed: %s");
			addTooltip("mattstorebonus", "Matter Storage: %s");
			addTooltip("mattusebonus", "Matter Usage: %s");
			addTooltip("failurebonus", "Failure: %s");
			addTooltip("powstorebonus", "Power Storage: %s");
			addTooltip("powusebonus", "Power Usage: %s");
			addTooltip("rangebonus", "Range: %s");
			addTooltip("mufflerupgrade", "Mutes machine sound");
			addTooltip("invaliddest", "Invalid Destination");
			addTooltip("empty", "Empty");
			addTooltip("storedpattern", "%1$s [%2$s]");
			addTooltip("has_storage_loc", "Bound: %s");
			addTooltip("no_storage_loc", "Unbound");
			addTooltip("menutasks", "Tasks");
			addTooltip("order", "Order");
			addTooltip("orderabv", "O");
			addTooltip("remainabv", "R");
			addTooltip("reporder", "%1$s: %2$s, %3$s: %4$s");
			addTooltip("noorder", "No Orders Queued");
			addTooltip("fused", "Fused");
			addTooltip("effectiveuses", "Uses: %s");
			addTooltip("hasshifttip", "Hold %s for info...");
			addTooltip("shiftkey", "Shift");
			addTooltip("fusedrive", "Fuse Drive");
			addTooltip("erasepattern", "Erase");
			addTooltip("erasedrive", "Erase Drive");
			addTooltip("fusedpattern", "Fused");
			addTooltip("fusepattern", "Fuse");
			
			addItemDescTooltip(ItemRegistry.ITEM_RAW_MATTER_DUST, "A failure, but recyclable");
			addItemDescTooltip(ItemRegistry.ITEM_MATTER_DUST, "Ready to decompose");
			addItemDescTooltip(ItemRegistry.ITEM_ION_SNIPER, "The zombie is WAY over there");
			addItemDescTooltip(ItemRegistry.ITEM_PHASER_RIFLE, "There are many zombies");
			addItemDescTooltip(ItemRegistry.ITEM_PLASMA_SHOTGUN, "There is a big zombie next to me");
			addItemDescTooltip(ItemRegistry.ITEM_PHASER, "There is one zombie plus B E A M");
			addItemDescTooltip(ItemRegistry.ITEM_OMNI_TOOL, "Like a pocket multi-tool but better");
			addItemDescTooltip(ItemRegistry.ITEM_TRANSPORTER_FLASHDRIVE, "Right-click to store a location");
			addItemDescTooltip(ItemRegistry.ITEM_PATTERN_DRIVE, "Stores up to 3 unique patterns");
			addItemDescTooltip(ItemRegistry.ITEM_MATTER_SCANNER, "A portable Matter Analyzer with perks");
			addItemDescTooltip(ItemRegistry.ITEM_ANDROID_PILL_RED, "Makes you an android");
			addItemDescTooltip(ItemRegistry.ITEM_ANDROID_PILL_BLUE, "Makes you an human for a price");
			addItemDescTooltip(ItemRegistry.ITEM_ANDROID_PILL_YELLOW, "Resets all android abilities");
			addItemDescTooltip(ItemRegistry.ITEM_COMMUNICATOR, "Link to a Transporter for on the go");
			addItemDescTooltip(ItemRegistry.ITEM_TRITANIUM_WRENCH, "Safely disassembles and rotates machines.");
			addItemDescTooltip(ItemRegistry.ITEM_ROMULAN_ALE, "Highly intoxicating alcoholic beverage of Romulan origin.");
			addItemDescTooltip(ItemRegistry.ITEM_EMERGENCY_RATION, "Emergency Rations.");
			addItemDescTooltip(ItemRegistry.SHIP_FACTORY, "Allows the building of ships");
			addItemDescTooltip(ItemRegistry.BUILDING_BASE, "Main building, needed for everything");
			addItemDescTooltip(ItemRegistry.SCOUT_SHIP, "A fast ship, used for scouting");
			addItemDescTooltip(ItemRegistry.SHIP_COLONIZER, "Used to claim planets by building a Base building on arrival");

			for (CrateColors color : TileTritaniumCrate.CrateColors.values()) {
				addBlockItemDescTooltip(BlockRegistry.BLOCK_TRITANIUM_CRATES.get(color), "Retains items when broken");
			}
			addBlockItemDescTooltip(BlockRegistry.BLOCK_SOLAR_PANEL, "E=MC^2 if you think about it");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_MATTER_DECOMPOSER, "Converts items to matter");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_MATTER_RECYCLER, "Makes raw matter dust usable again");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_CHARGER, "Wireless charging for androids");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_MICROWAVE, "A 24th century smoker");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_INSCRIBER, "Makes ciruits n' stuff");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_STAR_MAP, "A Galactic delight");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_ANDROID_STATION, "Android Development Kit");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_WEAPON_STATION, "Weapon Accessory Emporium");
			
			for(TypeMatterConduit conduit : TypeMatterConduit.values()) {
				addBlockItemDescTooltip(BlockRegistry.BLOCK_MATTER_CONDUITS.get(conduit), "XFER Limit: " + conduit.capacity + "kM");
			}
			addBlockItemDescTooltip(BlockRegistry.BLOCK_TRANSPORTER, "Say the line Kirk");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_SPACETIME_ACCELERATOR, "Speeds up machines around it");
			for(TypeMatterNetworkCable cable : TypeMatterNetworkCable.values()) {
				addBlockItemDescTooltip(BlockRegistry.BLOCK_MATTER_NETWORK_CABLES.get(cable), "It's not an AE cable you guys");
			}
			addBlockItemDescTooltip(BlockRegistry.BLOCK_CHUNKLOADER, "The bane of servers");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_MATTER_ANALYZER, "Scans a block's matter content");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_PATTERN_STORAGE, "Holds 6 Pattern Drives");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_PATTERN_MONITOR, "Queues Replication tasks");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_MATTER_REPLICATOR, "Makes ordered items from matter");
			addBlockItemDescTooltip(BlockRegistry.BLOCK_DISC_MANIPULATOR, "Wipe patterns and more");

			addGuiLabel("redstonelow", "Low");
			addGuiLabel("redstonehigh", "High");
			addGuiLabel("redstonenone", "None");
			addGuiLabel("redstone", "Redstone");
			addGuiLabel("ioitems", "Items");
			addGuiLabel("ioenergy", "Energy");
			addGuiLabel("iomatter", "Matter");
			addGuiLabel("time", "Time: %s");
			addGuiLabel("usage", "Usage: %s");
			addGuiLabel("usagetick", "Usage: %s/t");
			addGuiLabel("failure", "Failure: %s");
			addGuiLabel("range", "Range: %s Blocks");
			addGuiLabel("storage", "Storage: %s");
			addGuiLabel("soundmuted", "Sound Muffled");
			addGuiLabel("unknown", "Unknown");
			addGuiLabel("xlabel", "X");
			addGuiLabel("ylabel", "Y");
			addGuiLabel("zlabel", "Z");
			addGuiLabel("importpos", "Import");
			addGuiLabel("resetpos", "Reset");
			addGuiLabel("dimensionname", "DIM: %s");
			addGuiLabel("multiplier", "Multiplier: %s");
			addGuiLabel("orderratio", "%1$s / %2$s");
			addGuiLabel("replicatorqueue", "Local Orders");
			addGuiLabel("systemqueue", "Global Orders");
			addGuiLabel("isreciever", "Recieve");
			addGuiLabel("notreciever", "Transmit");
			addGuiLabel("transportermode", "Function");

			addContainer("tritanium_crate", "Tritanium Crate");
			addContainer(TypeMachine.SOLAR_PANEL.id(), "Solar Panel");
			addContainer(TypeMachine.MATTER_DECOMPOSER.id(), "Matter Decomposer");
			addContainer(TypeMachine.MATTER_RECYCLER.id(), "Matter Recycler");
			addContainer(TypeMachine.CHARGER.id(), "Android Charger");
			addContainer(TypeMachine.MICROWAVE.id(), "Microwave");
			addContainer(TypeMachine.INSCRIBER.id(), "Inscriber");
			addContainer(TypeMachine.TRANSPORTER.id(), "Transporter");
			addContainer(TypeMachine.SPACETIME_ACCELERATOR.id(), "Spacetime Accelerator");
			addContainer(TypeMachine.CHUNKLOADER.id(), "Chunkloader");
			addContainer(TypeMachine.PATTERN_MONITOR.id(), "Pattern Monitor");
			addContainer(TypeMachine.PATTERN_STORAGE.id(), "Pattern Storage");
			addContainer(TypeMachine.MATTER_ANALYZER.id(), "Matter Analyzer");
			addContainer(TypeMachine.MATTER_REPLICATOR.id(), "Matter Replicator");
			addContainer(TypeMachine.DISC_MANIPULATOR.id(), "Disc Manipulator");
			addContainer(TypeMachine.HOLO_SIGN.id(), "Holo Sign");

			addCommand("startmattercalc", "Starting Matter calculations...");
			addCommand("endmattercalc", "Finshed Matter calculations. Saved under \"Matter Overdrive/generated.json\"");
			addCommand("manualfailed", "unexpected error");
			addCommand("mainhandempty", "You must be holding an item");
			addCommand("assignedvalue", "Assigned %1$s kM to %2$s");
			addCommand("endmanualassign", "Saved under \"Matter Overdrive/manual.json\"");
			addCommand("startzeroscommand", "Starting Zeros Command...");
			addCommand("endzeroscommand", "Finshed writing zeros file. Saved under \"Matter Overdrive/zeros.json\"");

			addSubtitle("crate_open", "Tritanium Crate Opens");
			addSubtitle("crate_close", "Tritanium Crate Closes");
			addSubtitle("button_expand", "Button Shifts");
			addSubtitle("button_generic", "Button is pressed");
			addSubtitle("matter_decomposer", "Matter Decomposer running");
			addSubtitle("generic_machine", "Machine runs");
			addSubtitle("transporter", "Transporter Build-up");
			addSubtitle("transporter_arrive", "Transported Entity Appears");
			addSubtitle("matter_scanner_running", "Matter Scanner scans");
			addSubtitle("matter_scanner_beep", "Matter Scanner beeps");
			addSubtitle("matter_scanner_fail", "Scan fails");
			addSubtitle("matter_scanner_success", "Scan succeeds");

			addDimension("overworld", "Overworld");
			addDimension("the_nether", "Nether");
			addDimension("the_end", "End");

			addKeyCategory("main", "Matter Overdrive - Main");
			addKeyLabel("togglematterscanner", "Toggle Matter Scanner");
			
			addJei(InscriberRecipe.RECIPE_GROUP, "Inscriber");
			addJei("microwave", "Microwave");
			addJei("matter_recycler", "Matter Recycler");
			addJei("matter_decomposer", "Matter Decomposer");
			addJei("powerconsumed", "Total: %s");
			addJei("usagepertick", "%s/t");
			addJei("processtime", "Time: ");
			
			addDamageSource("android_transformation", "%s became human again");
			
			addChatMessage(ItemCommunicator.CHAT_MESSAGE, "Transporter location corrupted. Attempting Cancelation...");
		}
	}

	private void addItem(RegistryObject<Item> item, String translation) {
		add(item.get(), translation);
	}

	private void addBlock(RegistryObject<Block> block, String translation) {
		add(block.get(), translation);
	}

	private void addTooltip(String key, String translation) {
		add("tooltip." + References.ID + "." + key, translation);
	}
	
	private void addItemDescTooltip(RegistryObject<Item> reg, String translation) {
		addTooltip(name(reg.get()) + ".desc", translation);
	}
	
	private void addBlockItemDescTooltip(RegistryObject<Block> reg, String translation) {
		addTooltip(name(reg.get().asItem()) + ".desc", translation);
	}

	private void addContainer(String key, String translation) {
		add("container." + key, translation);
	}

	private void addCommand(String key, String translation) {
		add("command." + References.ID + "." + key, translation);
	}

	private void addSubtitle(String key, String translation) {
		add("subtitles." + References.ID + "." + key, translation);
	}

	private void addGuiLabel(String key, String translation) {
		add("gui." + References.ID + "." + key, translation);
	}

	private void addDimension(String key, String translation) {
		add("dimension." + References.ID + "." + key, translation);
	}

	private void addKeyCategory(String key, String translation) {
		add("keycategory." + References.ID + "." + key, translation);
	}

	private void addKeyLabel(String key, String translation) {
		add("key." + References.ID + "." + key, translation);
	}
	
	private void addJei(String key, String translation) {
		add("jei." + References.ID + "." + key, translation);
	}
	
	private void addDamageSource(String key, String translation) {
		add("death.attack." + key, translation);
	}
	
	private void addChatMessage(String key, String translation) {
		add("chat." + References.ID + "." + key, translation);
	}
	
	private String name(Item item) {
		return ForgeRegistries.ITEMS.getKey(item).getPath();
	}

	private String getNameFromEnum(String baseString) {
		String name = baseString.toLowerCase();
		if (name.contains("_")) {
			String[] split = name.split("_");
			name = "";
			for (String str : split) {
				if (str.length() > 0) {
					name = name + str.substring(0, 1).toUpperCase() + str.substring(1) + " ";
				}
			}
			while (name.charAt(name.length() - 1) == ' ') {
				name = name.substring(0, name.length() - 1);
			}
		} else {
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		return name;
	}

}
