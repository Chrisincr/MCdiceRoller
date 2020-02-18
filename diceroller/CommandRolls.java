package dev.cthompson.diceroller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;

public class CommandRolls implements ICommand {

	
	private final List aliases;
	
	public CommandRolls() {
		aliases = new ArrayList();
		aliases.add("rolls");
		aliases.add("Rolls");
		aliases.add("ROLLS");
	}
	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	@Override
	public String getCommandName() {
		
		return "rolls";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		
		return "/rolls x'd'y ex.. roll 1d20";
	}

	@Override
	public List getCommandAliases() {
		// TODO Auto-generated method stub
		return this.aliases;
	}

	@Override
	public void processCommand(final ICommandSender sender, String[] argString) {
		RollDice dice = new RollDice();
		final List<IChatComponent> messages =  dice.RolledDice(sender, argString);
		
		
		
		messages.forEach(new Consumer() {
			public void accept(final Object message) {
				
					sender.addChatMessage((IChatComponent)message);
				
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
