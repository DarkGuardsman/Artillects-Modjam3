package artillects;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import artillects.container.ContainerWorker;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{
	public static enum GuiIDs
	{
		WORKER;
	}

	public void preInit()
	{

	}

	public void init()
	{

	}

	public void postInit()
	{

	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		if (id == GuiIDs.WORKER.ordinal())
		{
			return new ContainerWorker(player);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

}
