package rpg.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rpg.BaseClassList;
import rpg.config.base.archer.ArcherArmor;
import rpg.config.base.archer.ArcherWeapons;
import rpg.config.base.mage.MageArmor;
import rpg.config.base.mage.MageWeapons;
import rpg.config.base.warrior.WarriorArmor;
import rpg.config.base.warrior.WarriorWeapons;
import rpg.gui.ContainerEmpty;
import rpg.playerinfo.PlayerInfoFake;

public class GuiChooseClass extends GuiContainer {

	String[] starterList;
	Item item;

	public GuiChooseClass() {
		super(new ContainerEmpty());
		starterList = BaseClassList.getStarterStringList();
	}

	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		buttonList.clear();
		for (int i = 0; i < starterList.length; i++) {
			buttonList.add(new GuiButton(i, width / 3 - 100, height / 6 + i * 20, starterList[i]));
		}
	}

	public void keyTyped(char i, int i1) {
	}
	
	public void actionPerformed(GuiButton button) {
        mc.thePlayer.addExperienceLevel(1);
		
        switch(button.id) {
			case 0:
				PlayerInfoFake.setPlayersClass("Archer");
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(ArcherWeapons.bowTraining, 1));
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(ArcherWeapons.arrowTraining, 32));
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(ArcherArmor.tunicTraining, 1));
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(ArcherArmor.legsTraining, 1));
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(ArcherArmor.bootsTraining, 1));
				break;
			case 1:
				PlayerInfoFake.setPlayersClass("Mage");
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(MageWeapons.staffTraining, 1));
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(MageArmor.hatTraining, 1));
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(MageArmor.robeTraining, 1));
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(MageArmor.sandalsTraining, 1));
				break;
			case 2:
				PlayerInfoFake.setPlayersClass("Warrior");
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(WarriorWeapons.swordTraining, 1));
                mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(WarriorArmor.helmetTraining, 1));
                mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(WarriorArmor.cuirassTraining, 1));
                mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(WarriorArmor.greavesTraining, 1));
                mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(WarriorArmor.sabatonsTraining, 1));
				break;
		}
        
        mc.thePlayer.sendChatToPlayer("[MinePG] You have chosen the path of the " + PlayerInfoFake.getPlayersClass());
		mc.thePlayer.sendChatToPlayer("<Mysterious Voice> Take care in this world my Friend...");
		mc.thePlayer.sendChatToPlayer("<Mysterious Voice> Many things lurk here that are better left alone");
		mc.thePlayer.sendChatToPlayer("<Mysterious Voice> ...");
		mc.thePlayer.sendChatToPlayer("<Mysterious Voice> You will need to be equiped");
		mc.thePlayer.sendChatToPlayer("<Mysterious Voice> Have this equipment, learn to use it");
		mc.thePlayer.closeScreen();
	}

	public void drawGuiContainerBackgroundLayer(float par3, int par1, int par2) {
		drawDefaultBackground();
		drawString(fontRenderer, "Welcome to the world of MinePG!!", 3, 10, 0xFFFFFF);
		drawString(fontRenderer, "Please pick your desired Class:", 3, 20, 0xFFFFFF);
	}
}