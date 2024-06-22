package me.ichun.mods.serverpause.loader.forge;

import me.ichun.mods.ichunutil.common.iChunUtil;
import me.ichun.mods.ichunutil.loader.forge.PacketChannelForge;
import me.ichun.mods.serverpause.client.core.EventHandlerClient;
import me.ichun.mods.serverpause.common.ServerPause;
import me.ichun.mods.serverpause.common.core.Config;
import me.ichun.mods.serverpause.common.core.EventHandlerServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ServerPause.MOD_ID)
public class LoaderForge extends ServerPause
{
    public LoaderForge()
    {
        modProxy = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);

        MinecraftForge.EVENT_BUS.register(eventHandlerServer = new EventHandlerServer());

        channel = new PacketChannelForge(CHANNEL_ID, NETWORK_PROTOCOL, PACKET_TYPES);

        config = iChunUtil.d().registerConfig(new Config());
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
        MinecraftForge.EVENT_BUS.register(eventHandlerClient = new EventHandlerClient());
    }
}
