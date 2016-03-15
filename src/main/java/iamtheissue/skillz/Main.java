package iamtheissue.skillz;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
	public static final String MODID = "skillz";
	public static final String NAME = "Skillz";
	public static final String VERSION = "1.0";
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent even)
	{
		// Initialize + register items, etc.
		// Load config
		Items.init();
		Items.register();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		// Proxy, TileEntity, Entity, GUI, Packet registering
		MinecraftForge.EVENT_BUS.register(new EventHook());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
