package artillects;

import java.io.File;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import artillects.entity.Arillect;
import artillects.hive.Hive;
import artillects.items.ItemArtillectSpawner;
import artillects.network.PacketHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Artillects.ID, name = Artillects.NAME, version = Artillects.VERSION, useMetadata = true)
@NetworkMod(channels = { Artillects.CHANNEL }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class Artillects
{

	// @Mod Prerequisites
	public static final String MAJOR_VERSION = "@MAJOR@";
	public static final String MINOR_VERSION = "@MINOR@";
	public static final String REVIS_VERSION = "@REVIS@";
	public static final String BUILD_VERSION = "@BUILD@";
	public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + REVIS_VERSION + "." + BUILD_VERSION;

	// @Mod
	public static final String ID = "Artillects";
	public static final String NAME = "Artillects";

	@SidedProxy(clientSide = "artillects.client.ClientProxy", serverSide = "artillects.CommonProxy")
	public static CommonProxy proxy;

	public static final String CHANNEL = "Artillects";

	public static final Configuration CONFIGURATION = new Configuration(new File(Loader.instance().getConfigDir(), "Dark/Artillects.cfg"));

	private static final String[] LANGUAGES_SUPPORTED = new String[] { "en_US" };

	// Domain and prefix
	public static final String DOMAIN = "artillects";
	public static final String PREFIX = DOMAIN + ":";

	// File paths
	public static final String RESOURCE_DIRECTORY_NO_SLASH = "assets/" + DOMAIN + "/";
	public static final String RESOURCE_DIRECTORY = "/" + RESOURCE_DIRECTORY_NO_SLASH;
	public static final String LANGUAGE_PATH = RESOURCE_DIRECTORY + "lang/";
	public static final String SOUND_PATH = RESOURCE_DIRECTORY + "audio/";

	public static final String TEXTURE_DIRECTORY = "textures/";
	public static final String BLOCK_DIRECTORY = TEXTURE_DIRECTORY + "blocks/";
	public static final String ITEM_DIRECTORY = TEXTURE_DIRECTORY + "items/";
	public static final String MODEL_DIRECTORY = TEXTURE_DIRECTORY + "models/";
	public static final String GUI_DIRECTORY = TEXTURE_DIRECTORY + "gui/";

	/* START IDS */
	public static int BLOCK_ID_PRE = 3856;
	public static int ITEM_ID_PREFIX = 15966;

	@Instance(Artillects.ID)
	private static Artillects instance;

	@Metadata(Artillects.ID)
	public static ModMetadata meta;

	public static Item itemDroneSpawner;

	public static Artillects instance()
	{
		if (instance == null)
		{
			instance = new Artillects();
		}
		return instance;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance();

		// Load meta
		meta.modId = ID;
		meta.name = NAME;
		meta.description = "Alien in nature, it is unknown how these Artillects to exist. What is do know is that they seem to be focused on stripping the planet of its resources...";
		meta.url = "www.universalelectricity.com/artillects";

		meta.logoFile = TEXTURE_DIRECTORY + "Drone_Banner.png";
		meta.version = VERSION;
		meta.authorList = Arrays.asList(new String[] { "Archadia", "DarkGuardsman", "Calclavia", "Hangcow" });
		meta.credits = "Please see the website.";
		meta.autogenerated = false;

		// Register event handlers
		TickRegistry.registerScheduledTickHandler(Hive.instance(), Side.SERVER);
		MinecraftForge.EVENT_BUS.register(Hive.instance());
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		// Register blocks and tiles
		Artillects.CONFIGURATION.load();
		Artillects.itemDroneSpawner = new ItemArtillectSpawner();
		Artillects.CONFIGURATION.save();

		ArtillectsTab.itemStack = new ItemStack(itemDroneSpawner);

		System.out.println(NAME + ": Loaded languages: " + loadLanguages(LANGUAGE_PATH, new String[] { "en_US" }));

		// Reigster entities
		for (Arillect drone : Arillect.values())
		{
			drone.reg();
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		// Load crafting
	}

	/**
	 * Loads all the language files for a mod. This supports the loading of "child" language files
	 * for sub-languages to be loaded all from one file instead of creating multiple of them. An
	 * example of this usage would be different Spanish sub-translations (es_MX, es_YU).
	 * 
	 * @param languagePath - The path to the mod's language file folder.
	 * @param languageSupported - The languages supported. E.g: new String[]{"en_US", "en_AU",
	 * "en_UK"}
	 * @return The amount of language files loaded successfully.
	 */
	public static int loadLanguages(String languagePath, String[] languageSupported)
	{
		int languages = 0;

		/**
		 * Load all languages.
		 */
		for (String language : languageSupported)
		{
			LanguageRegistry.instance().loadLocalization(languagePath + language + ".properties", language, false);

			if (LanguageRegistry.instance().getStringLocalization("children", language) != "")
			{
				try
				{
					String[] children = LanguageRegistry.instance().getStringLocalization("children", language).split(",");

					for (String child : children)
					{
						if (child != "" || child != null)
						{
							LanguageRegistry.instance().loadLocalization(languagePath + language + ".properties", child, false);
							languages++;
						}
					}
				}
				catch (Exception e)
				{
					FMLLog.severe("Failed to load a child language file.");
					e.printStackTrace();
				}
			}

			languages++;
		}

		return languages;
	}

	/**
	 * Gets the local text of your translation based on the given key. This will look through your
	 * mod's translation file that was previously registered. Make sure you enter the full name
	 * 
	 * @param key - e.g tile.block.name
	 * @return The translated string or the default English translation if none was found.
	 */
	public static String getLocal(String key)
	{
		String text = null;

		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			text = LanguageRegistry.instance().getStringLocalization(key);
		}

		if (text == null || text == "")
		{
			text = LanguageRegistry.instance().getStringLocalization(key, "en_US");
		}

		return text;
	}

	public static int nextBlockID()
	{
		int id = BLOCK_ID_PRE;

		while (id > 255 && id < (Block.blocksList.length - 1))
		{
			Block block = Block.blocksList[id];
			if (block == null)
			{
				break;
			}
			id++;
		}
		BLOCK_ID_PRE = id + 1;
		return id;
	}

	public static int nextItemID()
	{
		int id = ITEM_ID_PREFIX;

		while (id > 255 && id < (Item.itemsList.length - 1))
		{
			Item item = Item.itemsList[id];
			if (item == null)
			{
				break;
			}
			id++;
		}
		ITEM_ID_PREFIX = id + 1;
		return id;
	}

}
