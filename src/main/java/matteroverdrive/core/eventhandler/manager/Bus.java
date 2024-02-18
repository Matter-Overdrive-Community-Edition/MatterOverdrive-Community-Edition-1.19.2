package matteroverdrive.core.eventhandler.manager;

import java.util.function.Supplier;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public enum Bus {
  FORGE(() -> MinecraftForge.EVENT_BUS),
  MOD(() -> FMLJavaModLoadingContext.get().getModEventBus());

  private final Supplier<IEventBus> busSupplier;

  Bus(final Supplier<IEventBus> eventBusSupplier) {
    this.busSupplier = eventBusSupplier;
  }

  public IEventBus bus() {
    return busSupplier.get();
  }
}
