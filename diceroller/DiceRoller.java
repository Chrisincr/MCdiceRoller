package dev.cthompson.diceroller;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = DiceRoller.MODID, name = DiceRoller.NAME, version = DiceRoller.VERSION, acceptableRemoteVersions = "*")
public class DiceRoller
{
    public static final String MODID = "diceroller";
    public static final String NAME = "Dice Roller";
    public static final String VERSION = "1.3.1";

    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        // register server commands

    event.registerServerCommand(new CommandRoll());
    
    event.registerServerCommand(new CommandRolls());
    
    }

    
}
