package dev.aura.powermoney.common.compat.computercraft.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dev.aura.powermoney.PowerMoney;
import dev.aura.powermoney.common.tileentity.TileEntityPowerReceiver;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@RequiredArgsConstructor
public class PowerReceiverPeripheral implements IPeripheral {
  public static final String TYPE_ID = PowerMoney.RESOURCE_PREFIX + "power_receiver";

  @NonNull private final World world;
  @NonNull private final BlockPos pos;
  @NonNull private final TileEntityPowerReceiver tileEntity;

  /**
   * Should return a string that uniquely identifies this type of peripheral. This can be queried
   * from lua by calling {@code peripheral.getType()}
   *
   * @return A string identifying the type of peripheral.
   */
  @Override
  @Nonnull
  public String getType() {
    return TYPE_ID;
  }

  /**
   * Should return an array of strings that identify the methods that this peripheral exposes to
   * Lua. This will be called once before each attachment, and should not change when called
   * multiple times.
   *
   * @return An array of strings representing method names.
   * @see #callMethod
   */
  @Override
  @Nonnull
  public String[] getMethodNames() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * This is called when a lua program on an attached computer calls {@code peripheral.call()} with
   * one of the methods exposed by {@link #getMethodNames()}.
   *
   * <p>Be aware that this will be called from the ComputerCraft Lua thread, and must be thread-safe
   * when interacting with Minecraft objects.
   *
   * @param computer The interface to the computer that is making the call. Remember that multiple
   *     computers can be attached to a peripheral at once.
   * @param context The context of the currently running lua thread. This can be used to wait for
   *     events or otherwise yield.
   * @param method An integer identifying which of the methods from getMethodNames() the
   *     computercraft wishes to call. The integer indicates the index into the getMethodNames()
   *     table that corresponds to the string passed into peripheral.call()
   * @param arguments An array of objects, representing the arguments passed into {@code
   *     peripheral.call()}.<br>
   *     Lua values of type "string" will be represented by Object type String.<br>
   *     Lua values of type "number" will be represented by Object type Double.<br>
   *     Lua values of type "boolean" will be represented by Object type Boolean.<br>
   *     Lua values of type "table" will be represented by Object type Map.<br>
   *     Lua values of any other type will be represented by a null object.<br>
   *     This array will be empty if no arguments are passed.
   * @return An array of objects, representing values you wish to return to the lua program.
   *     Integers, Doubles, Floats, Strings, Booleans, Maps and ILuaObject and null be converted to
   *     their corresponding lua type. All other types will be converted to nil.
   *     <p>You may return null to indicate no values should be returned.
   * @throws LuaException If you throw any exception from this function, a lua error will be raised
   *     with the same message as your exception. Use this to throw appropriate errors if the wrong
   *     arguments are supplied to your method.
   * @throws InterruptedException If the user shuts down or reboots the computer the coroutine is
   *     suspended, InterruptedException will be thrown. This exception must not be caught or
   *     intercepted, or the computer will leak memory and end up in a broken state.
   * @see #getMethodNames
   */
  @Override
  @Nullable
  public Object[] callMethod(
      @Nonnull IComputerAccess computer,
      @Nonnull ILuaContext context,
      int method,
      @Nonnull Object[] arguments)
      throws LuaException, InterruptedException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Determine whether this peripheral is equivalent to another one.
   *
   * <p>The minimal example should at least check whether they are the same object. However, you may
   * wish to check if they point to the same block or tile entity.
   *
   * @param other The peripheral to compare against. This may be {@code null}.
   * @return Whether these peripherals are equivalent.
   */
  @Override
  public boolean equals(@Nullable IPeripheral other) {
    if (!(other instanceof PowerReceiverPeripheral)) return false;

    final PowerReceiverPeripheral otherConverted = (PowerReceiverPeripheral) other;

    return (world == otherConverted.world)
        && pos.equals(otherConverted.pos)
        && (tileEntity == otherConverted.tileEntity);
  }
}