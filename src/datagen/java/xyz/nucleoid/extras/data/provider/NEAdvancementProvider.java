package xyz.nucleoid.extras.data.provider;

import java.util.Optional;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements.CriterionMerger;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.nucleoid.extras.NucleoidExtras;
import xyz.nucleoid.extras.lobby.NEBlocks;
import xyz.nucleoid.extras.lobby.NECriteria;
import xyz.nucleoid.extras.lobby.NEItems;
import xyz.nucleoid.extras.lobby.block.tater.TinyPotatoBlock;
import xyz.nucleoid.extras.lobby.criterion.TaterCollectedCriterion;
import xyz.nucleoid.extras.lobby.criterion.TaterCount;
import xyz.nucleoid.extras.lobby.criterion.WearTaterCriterion;

public class NEAdvancementProvider extends FabricAdvancementProvider {
    public NEAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<AdvancementEntry> consumer) {
        var root = accept(consumer, "root", null, Advancement.Builder.createUntelemetered()
                .display(
                        NEItems.NUCLEOID_LOGO,
                        Text.translatable("advancements.nucleoid_extras.root.title"),
                        Text.translatable("advancements.nucleoid_extras.root.description"),
                        new Identifier("textures/block/lime_concrete.png"),
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )
                .criterion("get_tater", NECriteria.TATER_COLLECTED.create(
                        new TaterCollectedCriterion.Conditions(Optional.empty(), Optional.of(new TaterCount.Value(1)))
                ))
        );

        // Based on number collected
        var firstTater = accept(consumer, "first_tater", NEBlocks.TINY_POTATO, requiringTatersCollected(1).parent(root));
        var tenTaters = accept(consumer, "ten_taters", NEBlocks.IRON_TATER, AdvancementFrame.GOAL, requiringTatersCollected(2).parent(firstTater));
        var twentyFiveTaters = accept(consumer, "twenty_five_taters", NEBlocks.GOLD_TATER, AdvancementFrame.GOAL, requiringTatersCollected(3).parent(tenTaters));
        var fiftyTaters = accept(consumer, "fifty_taters", NEBlocks.DIAMOND_TATER, AdvancementFrame.GOAL, requiringTatersCollected(4).parent(twentyFiveTaters));
        var oneHundredTaters = accept(consumer, "one_hundred_taters", NEBlocks.EMERALD_TATER, AdvancementFrame.GOAL, requiringTatersCollected(5).parent(fiftyTaters));
        var twoHundredTaters = accept(consumer, "two_hundred_taters", NEBlocks.NETHERITE_TATER, AdvancementFrame.GOAL, requiringTatersCollected(6).parent(oneHundredTaters));

        accept(consumer, "all_taters", null, requiringTatersCollected(new TaterCount.All())
                .display(
                        NEBlocks.TATER_OF_UNDYING,
                        Text.translatable("advancements.nucleoid_extras.all_taters.title"),
                        Text.translatable("advancements.nucleoid_extras.all_taters.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .parent(twoHundredTaters)
        );
        accept(consumer, "rare_taters", NEBlocks.COAL_TATER, requiringTatersCollected(NEBlocks.COAL_TATER, NEBlocks.DIAMOND_TATER, NEBlocks.EMERALD_TATER, NEBlocks.GOLD_TATER, NEBlocks.IRON_TATER, NEBlocks.NETHERITE_TATER).parent(firstTater));
        accept(consumer, "pride_taters", NEBlocks.GAY_TATER, requiringTatersCollected(NEBlocks.ASEXUAL_TATER, NEBlocks.BI_TATER, NEBlocks.DEMISEXUAL_TATER, NEBlocks.GAY_TATER, NEBlocks.GENDERFLUID_TATER, NEBlocks.LESBIAN_TATER, NEBlocks.NONBINARY_TATER, NEBlocks.PAN_TATER, NEBlocks.TRANS_TATER).parent(firstTater));

    }

    private static Advancement.Builder requiringTatersCollected(int count) {
        return requiringTatersCollected(new TaterCount.Value(count));
    }

    private static Advancement.Builder requiringTatersCollected(TaterCount count) {
        var builder = Advancement.Builder.createUntelemetered();

        var name = "get_" + count.count() + "_tater" + (count.count() == 1 ? "" : "s");
        var conditions = new TaterCollectedCriterion.Conditions(Optional.empty(), Optional.of(count));

        builder.criterion(name, NECriteria.TATER_COLLECTED.create(conditions));

        return builder;
    }

    private static Advancement.Builder requiringTatersCollected(Block... taters) {
        var builder = Advancement.Builder.createUntelemetered();

        for (Block tater : taters) {
            var id = Registries.BLOCK.getId(tater);
            var name = "get_" + id.getPath();

            var conditions = new TaterCollectedCriterion.Conditions(getTaterEntry(tater), Optional.empty());

            builder.criterion(name, NECriteria.TATER_COLLECTED.create(conditions));
        }

        return builder;
    }

    private static AdvancementEntry accept(Consumer<AdvancementEntry> consumer, String path, ItemConvertible icon, Advancement.Builder builder) {
        return accept(consumer, path, icon, AdvancementFrame.TASK, builder);
    }

    private static AdvancementEntry accept(Consumer<AdvancementEntry> consumer, String path, ItemConvertible icon, AdvancementFrame frame, Advancement.Builder builder) {
        if (icon != null) {
            builder.display(
                    icon,
                    Text.translatable("advancements.nucleoid_extras." + path + ".title"),
                    Text.translatable("advancements.nucleoid_extras." + path + ".description"),
                    null,
                    frame,
                    true,
                    true,
                    false
            );
        }

        var id = NucleoidExtras.identifier("taters/" + path);
        var advancement = builder.build(id);

        consumer.accept(advancement);
        return advancement;
    }

    private static Optional<RegistryEntry<Block>> getTaterEntry(Block block) {
        if (block instanceof TinyPotatoBlock tater) {
            return Optional.of(tater.getRegistryEntry());
        }

        throw new IllegalArgumentException("Not a tater: " + block);
    }
}
