package com.tyrellplayz.big_industries.data;

import com.google.gson.JsonObject;
import com.tyrellplayz.big_industries.common.crafting.BICookingRecipe;
import com.tyrellplayz.big_industries.common.crafting.BICookingRecipeSerializer;
import com.tyrellplayz.big_industries.core.BIRecipesSerializers;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BICookingRecipeBuilder {

    private final Ingredient ingredient;
    private final int ingredientAmount;
    private final Item result;
    private final float experience;
    private final int cookingTime;
    private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
    private String group;
    private final BICookingRecipeSerializer<?> recipeSerializer;

    private BICookingRecipeBuilder( Ingredient ingredient, int ingredientAmount, IItemProvider result, float experience, int cookingTime, BICookingRecipeSerializer<?> serializer) {
        this.ingredient = ingredient;
        this.ingredientAmount = ingredientAmount;
        this.result = result.asItem();
        this.experience = experience;
        this.cookingTime = cookingTime;
        this.recipeSerializer = serializer;
    }

    public static BICookingRecipeBuilder cookingRecipe(Ingredient ingredient, int ingredientAmount, IItemProvider result, float experience, int cookingTime, BICookingRecipeSerializer<?> serializer) {
        return new BICookingRecipeBuilder(ingredient, ingredientAmount, result, experience, cookingTime, serializer);
    }

    public static BICookingRecipeBuilder cokingRecipe(Ingredient ingredient, IItemProvider result, float experience, int cookingTime) {
        return cokingRecipe(ingredient, 1, result, experience, cookingTime);
    }

    public static BICookingRecipeBuilder cokingRecipe(Ingredient ingredient, int ingredientAmount, IItemProvider result, float experience, int cookingTime) {
        return cookingRecipe(ingredient, ingredientAmount, result, experience, cookingTime, (BICookingRecipeSerializer<?>) BIRecipesSerializers.COKING);
    }

    public BICookingRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
        this.advancementBuilder.withCriterion(name, criterionIn);
        return this;
    }

    public void build(Consumer<IFinishedRecipe> consumerIn) {
        this.build(consumerIn, ForgeRegistries.ITEMS.getKey(this.result));
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, String save) {
        ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(this.result);
        ResourceLocation saveResourceLocation = new ResourceLocation(save);
        if (saveResourceLocation.equals(resourceLocation)) {
            throw new IllegalStateException("Recipe " + saveResourceLocation + " should remove its 'save' argument");
        } else {
            this.build(consumerIn, saveResourceLocation);
        }
    }

    public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        this.validate(id);
        this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", RecipeUnlockedTrigger.create(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
        consumerIn.accept(new BICookingRecipeBuilder.Result(id, this.group == null ? "" : this.group, this.ingredient, this.ingredientAmount, this.result, this.experience, this.cookingTime, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getGroup().getPath() + "/" + id.getPath()), this.recipeSerializer));
    }

    /**
     * Makes sure that this obtainable.
     */
    private void validate(ResourceLocation id) {
        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final int ingredientAmount;
        private final Item result;
        private final float experience;
        private final int cookingTime;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;
        private final BICookingRecipeSerializer<? extends BICookingRecipe> serializer;

        public Result(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, int ingredientAmount, Item resultIn, float experienceIn, int cookingTimeIn, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn, BICookingRecipeSerializer<? extends BICookingRecipe> serializerIn) {
            this.id = idIn;
            this.group = groupIn;
            this.ingredient = ingredientIn;
            this.ingredientAmount = ingredientAmount;
            this.result = resultIn;
            this.experience = experienceIn;
            this.cookingTime = cookingTimeIn;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
            this.serializer = serializerIn;
        }

        public void serialize(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            json.add("ingredient", this.ingredient.serialize());
            json.addProperty("ingredientAmount",this.ingredientAmount);
            json.addProperty("result", ForgeRegistries.ITEMS.getKey(this.result).toString());
            json.addProperty("experience", this.experience);
            json.addProperty("cookingtime", this.cookingTime);
        }

        public IRecipeSerializer<?> getSerializer() {
            return this.serializer;
        }

        /**
         * Gets the ID for the recipe.
         */
        public ResourceLocation getID() {
            return this.id;
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         */
        @Nullable
        public JsonObject getAdvancementJson() {
            return this.advancementBuilder.serialize();
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #getAdvancementJson}
         * is non-null.
         */
        @Nullable
        public ResourceLocation getAdvancementID() {
            return this.advancementId;
        }
    }

}
