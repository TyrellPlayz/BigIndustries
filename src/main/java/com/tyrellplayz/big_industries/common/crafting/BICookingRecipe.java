package com.tyrellplayz.big_industries.common.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * The main difference between Minecraft's cooking recipe and this one is that this one allows you to require more than 1 of the ingredient.
 */
public abstract class BICookingRecipe implements IRecipe<IInventory> {

    protected final IRecipeType<?> type;
    protected final ResourceLocation id;
    protected final String group;
    protected final Ingredient ingredient;
    protected final int ingredientAmount;
    protected final ItemStack result;
    protected final float experience;
    protected final int cookTime;

    public BICookingRecipe(IRecipeType<?> type, ResourceLocation id, String group, Ingredient ingredient, int ingredientAmount, ItemStack result, float experience, int cookTime) {
        this.type = type;
        this.id = id;
        this.group = group;
        this.ingredient = ingredient;
        this.ingredientAmount = ingredientAmount;
        this.result = result;
        this.experience = experience;
        this.cookTime = cookTime;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return ingredient.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return result.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(ingredient);
        return ingredients;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getIngredientAmount() {
        return ingredientAmount;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return result;
    }

    public float getExperience() {
        return experience;
    }

    public int getCookTime() {
        return cookTime;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public IRecipeType<?> getType() {
        return type;
    }
}
