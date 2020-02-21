package dev.cthompson.diceroller;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class RollDice {
	
	public List<IChatComponent> RolledDice(ICommandSender player, String[] argString){
		List<IChatComponent> messages = new ArrayList<IChatComponent>();
		ArrayList<Integer> vals = new ArrayList<Integer>();
		
		// ToDo: Use Switch Statement
		if (argString.length == 0) {
			messages.add(new ChatComponentText(player.getCommandSenderName() + " is rolling 1d20"));
			vals = (Roll(1,20)); 
		}
		else if (argString[0].equals("9001")) {
			messages.add(new ChatComponentText(player.getCommandSenderName().toUpperCase() + " IS TRYING TO ROLL GREATER THAN 9000!!!"));
			vals = (Roll(1,9001)); 
		}
		else if (argString[0].equals("a")) {
			messages.add(new ChatComponentText(player.getCommandSenderName() + " is rolling with advantage."));
			vals = (Roll(2,20));
				
			Integer highest = 0;
			String rolls = "";
			
			for (int i = 0; i < vals.size(); i++) {
				Integer val = vals.get(i);
				rolls += val.toString() + " ";
				if (val > highest) {
					highest = val;
				}
			}
			messages.add(new ChatComponentText(rolls));
			messages.add(new ChatComponentText("Highest is: " + highest.toString()));
			
			return messages;
		}
		else if (argString[0].equals("d")) {
			messages.add(new ChatComponentText(player.getCommandSenderName() + " is rolling with disadvantage."));
			vals = (Roll(2,20));
				
			Integer lowest = 20;
			String rolls = "";
			
			for (int i = 0; i < vals.size(); i++) {
				Integer val = vals.get(i);
				rolls += val.toString() + " ";
				if (val < lowest) {
					lowest = val;
				}
			}
			messages.add(new ChatComponentText(rolls));
			messages.add(new ChatComponentText("Lowest is: " + lowest.toString()));
			
			return messages;
		}
		else {
			if(Pattern.matches("\\d+", argString[0])) {
				messages.add(new ChatComponentText(player.getCommandSenderName() + " is rolling 1d" +argString[0]));
           		vals = Roll(1,Integer.parseInt(argString[0]));
           		
			} else {
				if(Pattern.matches("\\d+[d]{1}\\d+", argString[0])) {
					String[] splitted = argString[0].split("d");

					vals = Roll(Integer.parseInt(splitted[0]),Integer.parseInt(splitted[1]));
					messages.add(new ChatComponentText(player.getCommandSenderName() + " is rolling " +argString[0]));		
				} else if(Pattern.matches("\\d+[d]{1}\\d+([+]{1}\\d+[d]{1}\\d+)*", argString[0])) {
					String[] splitted = argString[0].split("+");					
					int numOfDiceTypes = splitted.length;
					
					messages.add(new ChatComponentText(player.getCommandSenderName() + " is rolling " +argString[0]));	
					
					ArrayList<Integer> results = new ArrayList<Integer>(numOfDiceTypes);
					for (int multiroll = 0; multiroll < numOfDiceTypes; multiroll++) {
						String[] splitDie = splitted[multiroll].split("d");
						vals.addAll(Roll(Integer.parseInt(splitDie[0]),Integer.parseInt(splitDie[1])));						
					}
				} else if (Pattern.matches("\\d+[d]{1}\\d+([+]{1}\\d+[d]{1}\\d+)*([+]{1}\\d+)*", argString[0])) {
					String[] splitted = argString[0].split("+");					
					int numOfDiceTypes = splitted.length - 1;
					
					messages.add(new ChatComponentText(player.getCommandSenderName() + " is rolling " +argString[0]));	
					
					ArrayList<Integer> results = new ArrayList<Integer>(numOfDiceTypes);
					for (int multiroll = 0; multiroll < numOfDiceTypes; multiroll++) {
						String[] splitDie = splitted[multiroll].split("d");
						vals.addAll(Roll(Integer.parseInt(splitDie[0]),Integer.parseInt(splitDie[1])));						
					}
					vals.add(Integer.parseInt(splitted[splitted.length - 1]));
				}
				else {					
					messages.add( new ChatComponentText(player.getCommandSenderName() + "Needs to learn to type"));
					
					return messages;
				} 
			}
		}
		
		// Totals and shows results and the total.
		Integer total = 0;
		String rolls = "";
		
		for (int i = 0; i < vals.size(); i++) {
			Integer val = vals.get(i);
			rolls += val.toString() + " ";
			total += val;
		}
		messages.add(new ChatComponentText(rolls));
		messages.add(new ChatComponentText("Total is: " + total.toString()));
		
		return messages;
	}
	
	public ArrayList<Integer> Roll(Integer num, Integer sides) {
		
		ArrayList<Integer> results = new ArrayList<Integer>(num);
		
		for(int i = 0; i < num; i++) {
			int rand = (int)(Math.random() * sides) +1;
			results.add(rand);
		}
		
		return results;
	}
}
