package dev.aura.powermoney.common.sponge;

import dev.aura.powermoney.PowerMoney;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import net.minecraftforge.fml.common.Loader;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;

@UtilityClass
public class SpongeMoneyInterface {
  private static final DirectInterface directInterface =
      Loader.isModLoaded("sponge") ? new DirectInterface() : null;

  public static boolean isSpongeLoaded() {
    return directInterface != null;
  }

  public static boolean canAcceptMoney() {
    return isSpongeLoaded() && directInterface.hasEconomyService();
  }

  public static void addMoneyToPlayer(UUID player, BigDecimal money) {
    if (!canAcceptMoney())
      throw new IllegalStateException(
          "Sponge or an EconomySerice is missing. Cannot accept money!");

    directInterface.addMoneyToPlayer(player, money);
  }

  private static class DirectInterface {
    private final Optional<EconomyService> economyService =
        Sponge.getServiceManager().provide(EconomyService.class);

    @Getter(value = AccessLevel.PRIVATE, lazy = true)
    private final Currency currency = getCurrencyFromConfig();

    public boolean hasEconomyService() {
      return economyService.isPresent();
    }

    public void addMoneyToPlayer(UUID player, BigDecimal money) {
      economyService.ifPresent(
          economyService ->
              economyService
                  .getOrCreateAccount(player)
                  .get()
                  .deposit(
                      getCurrency(),
                      money,
                      Cause.of(EventContext.empty(), PowerMoney.getInstance())));
    }

    private Currency getCurrencyFromConfig() {
      // TODO Use value from config. And fall back to the default if it doesn't exist
      return economyService.map(EconomyService::getDefaultCurrency).orElse(null);
    }
  }
}