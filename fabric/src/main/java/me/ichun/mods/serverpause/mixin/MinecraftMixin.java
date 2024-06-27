package me.ichun.mods.serverpause.mixin;

import me.ichun.mods.serverpause.common.ServerPause;
import net.minecraft.client.Minecraft;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin
{
    //Thanks https://github.com/SpongePowered/Mixin/issues/209 for being reference to how I figured out @ModifyVariable
    @Inject(method = "runTick", //Method to check
            at = @At(value = "FIELD",
                target = "Lnet/minecraft/client/Minecraft;pause:Z",
                opcode = Opcodes.PUTFIELD,
                ordinal = 0, //Do it at the first reference where it is set.
                shift = At.Shift.AFTER //Shift to after that call is done
            ),
            require = 1 //Require at least one success.
    )
    private void serverpause$onPauseCheck(boolean renderLevel, CallbackInfo ci)
    {
        Minecraft mc = ((Minecraft)(Object)this);
        boolean currentPause = ((MinecraftAccessorMixin)mc).getPause();
        boolean shouldPause = mc.getConnection() != null && mc.getConnection().getConnection().isConnected() && ServerPause.eventHandlerClient.serverPause || currentPause;
        if(shouldPause && !currentPause)
        {
            ((MinecraftAccessorMixin)mc).setPause(shouldPause);
        }
    }
}
