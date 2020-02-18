package dev.cthompson.diceroller;

import dev.cthompson.diceroller.RollDice;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;


public class CommandRoll implements ICommand{

	private final List aliases;
	
	public CommandRoll() {
		aliases = new ArrayList();
		aliases.add("roll");
		aliases.add("Roll");
		aliases.add("ROLL");
	}
	
	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	@Override
	public String getCommandName() {
		
		return "roll";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		
		return "/roll x'd'y ex.. roll 1d20";
	}

	@Override
	public List getCommandAliases() {
		
		return this.aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] argString) {
		
		RollDice dice = new RollDice();
		final List<IChatComponent> messages =  dice.RolledDice(sender, argString);
		
		final List players = sender.getEntityWorld().playerEntities;
		
		messages.forEach(new Consumer() {
			public void accept(final Object message) {
				players.forEach(new Consumer() {
					@Override
					public void accept(Object player) {
						((EntityPlayer) player).addChatMessage((IChatComponent)message);
					}
				});
			}

	
			
		});
		
		
		
		
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
