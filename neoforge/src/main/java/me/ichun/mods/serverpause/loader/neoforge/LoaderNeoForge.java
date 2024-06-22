package me.ichun.mods.serverpause.loader.neoforge;

import me.ichun.mods.ichunutil.common.iChunUtil;
import me.ichun.mods.ichunutil.loader.neoforge.PacketChannelNeoForge;
import me.ichun.mods.serverpause.client.core.EventHandlerClient;
import me.ichun.mods.serverpause.common.ServerPause;
import me.ichun.mods.serverpause.common.core.Config;
import me.ichun.mods.serverpause.common.core.EventHandlerServer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@Mod(ServerPause.MOD_ID)
public class LoaderNeoForge extends ServerPause
{
    public LoaderNeoForge(IEventBus modEventBus)
    {
        modProxy = this;

        modEventBus.addListener(this::onClientSetup);
        modEventBus.addListener(this::registerPayloadHandler);

        NeoForge.EVENT_BUS.register(eventHandlerServer = new EventHandlerServer());

        config = iChunUtil.d().registerConfig(new Config());
    }

    private void onClientSetup(FMLClientSetupEvent event)
    {
        NeoForge.EVENT_BUS.register(eventHandlerClient = new EventHandlerClient());
    }

    private void registerPayloadHandler(RegisterPayloadHandlersEvent event)
    {
        channel = new PacketChannelNeoForge(event, CHANNEL_ID, NETWORK_PROTOCOL, PACKET_TYPES);
    }
}
