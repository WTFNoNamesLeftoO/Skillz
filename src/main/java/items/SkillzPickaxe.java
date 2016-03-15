package items;


import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import iamtheissue.skillz.Main;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SkillzPickaxe extends ItemPickaxe
{
	public SkillzPickaxe(String unlocalizedName, ToolMaterial material)
	{
		super(material);
		this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(Main.MODID + ":" + unlocalizedName);
	}
	
	public int getExp(ItemStack stack)
	{
		if(stack.getTagCompound() != null)
		{
			return stack.getTagCompound().getInteger("exp");
		}
		
		return 0;
	}
	
	public void setExp(ItemStack stack, int value)
	{
		if(stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().removeTag("exp");
		stack.getTagCompound().setInteger("exp", value);
	}
	
	public void addExp(ItemStack stack, int value)
	{
		setExp(stack, getExp(stack) + value);
	}
	
	
	
	

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World p_77659_2_, EntityPlayer playerIn) {


		PotionEffect p = new PotionEffect(3, 400, 2);
		playerIn.addPotionEffect(p);
		return super.onItemRightClick(stack, p_77659_2_, playerIn);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_,
			int p_150894_5_, int p_150894_6_, EntityLivingBase playerIn)
	{
		
		addExp(stack, 1);
		NBTTagList list = stack.getEnchantmentTagList();
		int index = -1;
		int currLvl = -1;
		if(list != null)
		{
			for(int i = 0; i < list.tagCount(); i++)
			{
				int id = list.getCompoundTagAt(i).getShort("id");
				int lvl = list.getCompoundTagAt(i).getShort("lvl");
				
				if(id == 32)
				{
					index = i;
					currLvl = lvl;
					break;
				}
				
			}
		}
		
		int newLvl = (int) (getExp(stack) / 10);
		if(newLvl > 0 && newLvl <= 5 && currLvl < newLvl)
		{
			if(index != -1)
			{
				list.removeTag(index);
			}
			
			stack.addEnchantment(Enchantment.efficiency, newLvl);
		}
		
		return super.onBlockDestroyed(stack, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_,
				playerIn);
		
		
	}




	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List toolTip, boolean advanced)
	{
		toolTip.add("\u00a7aEXP: " + getExp(stack));
	}

}
