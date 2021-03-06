package rpg.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import rpg.enums.EnumRPGToolMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRPGTool extends RPGItem {

    /** Array of blocks the tool has extra effect against. */
    private Block[] blocksEffectiveAgainst;
    public float efficiencyOnProperMaterial = 4.0F;

    /** Damage versus entities. */
    public int damageVsEntity;

    /** The material this tool is made from. */
    protected EnumRPGToolMaterial toolMaterial;

    protected ItemRPGTool(int par1, int par2, EnumRPGToolMaterial par3EnumToolMaterial, Block[] par4ArrayOfBlock, String name) {
        super(par1, name);
        this.toolMaterial = par3EnumToolMaterial;
        this.blocksEffectiveAgainst = par4ArrayOfBlock;
        this.maxStackSize = 1;
        this.setMaxDamage(par3EnumToolMaterial.getMaxUses());
        this.efficiencyOnProperMaterial = par3EnumToolMaterial.getEfficiencyOnProperMaterial();
        this.damageVsEntity = par2 + par3EnumToolMaterial.getDamageVsEntity();
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    /**
     * Returns the damage against a given entity.
     */
    @Override
    public int getDamageVsEntity(Entity par1Entity) {
        return this.damageVsEntity;
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return this.toolMaterial.getToolCraftingMaterial() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    /**
     * Return the enchantability factor of the item, most of
     * the time is based on material.
     */
    @Override
    public int getItemEnchantability() {
        return this.toolMaterial.getEnchantability();
    }

    /**
     * Returns the strength of the stack against a given
     * block. 1.0F base, (Quality+1)*2 if correct blocktype,
     * 1.5F if sword
     */
    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        for (int i = 0; i < this.blocksEffectiveAgainst.length; ++i) {
            if (this.blocksEffectiveAgainst[i] == par2Block)
                return this.efficiencyOnProperMaterial;
        }

        return 1.0F;
    }

    /** FORGE: Overridden to allow custom tool effectiveness */
    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta) {
        if (ForgeHooks.isToolEffective(stack, block, meta))
            return this.efficiencyOnProperMaterial;
        return this.getStrVsBlock(stack, block);
    }

    /**
     * Return the name for this tool's material.
     */
    public String getToolMaterialName() {
        return this.toolMaterial.toString();
    }

    /**
     * Current implementations of this method in child
     * classes do not use the entry argument beside ev. They
     * just raise the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving) {
        par1ItemStack.damageItem(2, par3EntityLiving);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    public boolean isFull3D() {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving) {
        if (Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D) {
            par1ItemStack.damageItem(1, par7EntityLiving);
        }

        return true;
    }
}
