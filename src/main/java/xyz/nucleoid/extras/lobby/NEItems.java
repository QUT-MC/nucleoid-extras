package xyz.nucleoid.extras.lobby;

import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.nucleoid.extras.NucleoidExtras;
import xyz.nucleoid.extras.NucleoidExtrasConfig;
import xyz.nucleoid.extras.lobby.block.tater.TinyPotatoBlock;
import xyz.nucleoid.extras.lobby.item.GamePortalOpenerItem;
import xyz.nucleoid.extras.lobby.item.LaunchFeatherItem;
import xyz.nucleoid.extras.lobby.item.LobbyBlockItem;
import xyz.nucleoid.extras.lobby.item.LobbyHeadItem;
import xyz.nucleoid.extras.lobby.item.LobbyTallBlockItem;
import xyz.nucleoid.extras.lobby.item.QuickArmorStandItem;
import xyz.nucleoid.extras.lobby.item.RuleBookItem;
import xyz.nucleoid.extras.lobby.item.tater.CreativeTaterBoxItem;
import xyz.nucleoid.extras.lobby.item.tater.TaterBoxItem;
import xyz.nucleoid.extras.lobby.item.tater.TaterGuidebookItem;
import xyz.nucleoid.plasmid.game.manager.GameSpaceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class NEItems {
    private static final List<Item> TATERS = new ArrayList<>();

    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
        .displayName(Text.translatable("text.nucleoid_extras.name"))
        .icon(() -> new ItemStack(NEItems.NUCLEOID_LOGO))
        .entries((context, entries) -> {
            entries.add(NEItems.QUICK_ARMOR_STAND);
            entries.add(NEItems.END_PORTAL);
            entries.add(NEItems.END_GATEWAY);
            entries.add(NEItems.SAFE_TNT);
            entries.add(NEItems.LAUNCH_FEATHER);
            entries.add(NEItems.GOLD_LAUNCH_PAD);
            entries.add(NEItems.IRON_LAUNCH_PAD);
            entries.add(NEItems.CONTRIBUTOR_STATUE);
            entries.add(NEItems.INFINITE_DISPENSER);
            entries.add(NEItems.INFINITE_DROPPER);
            entries.add(NEItems.SNAKE_BLOCK);
            entries.add(NEItems.FAST_SNAKE_BLOCK);
            entries.add(NEItems.TRANSIENT_IRON_DOOR);
            entries.add(NEItems.TRANSIENT_OAK_DOOR);
            entries.add(NEItems.TRANSIENT_SPRUCE_DOOR);
            entries.add(NEItems.TRANSIENT_BIRCH_DOOR);
            entries.add(NEItems.TRANSIENT_JUNGLE_DOOR);
            entries.add(NEItems.TRANSIENT_ACACIA_DOOR);
            entries.add(NEItems.TRANSIENT_CHERRY_DOOR);
            entries.add(NEItems.TRANSIENT_DARK_OAK_DOOR);
            entries.add(NEItems.TRANSIENT_MANGROVE_DOOR);
            entries.add(NEItems.TRANSIENT_BAMBOO_DOOR);
            entries.add(NEItems.TRANSIENT_CRIMSON_DOOR);
            entries.add(NEItems.TRANSIENT_WARPED_DOOR);
            entries.add(NEItems.BLACK_CONCRETE_POWDER);
            entries.add(NEItems.BLUE_CONCRETE_POWDER);
            entries.add(NEItems.BROWN_CONCRETE_POWDER);
            entries.add(NEItems.CYAN_CONCRETE_POWDER);
            entries.add(NEItems.GREEN_CONCRETE_POWDER);
            entries.add(NEItems.GRAY_CONCRETE_POWDER);
            entries.add(NEItems.LIGHT_BLUE_CONCRETE_POWDER);
            entries.add(NEItems.LIGHT_GRAY_CONCRETE_POWDER);
            entries.add(NEItems.LIME_CONCRETE_POWDER);
            entries.add(NEItems.MAGENTA_CONCRETE_POWDER);
            entries.add(NEItems.ORANGE_CONCRETE_POWDER);
            entries.add(NEItems.PINK_CONCRETE_POWDER);
            entries.add(NEItems.PURPLE_CONCRETE_POWDER);
            entries.add(NEItems.RED_CONCRETE_POWDER);
            entries.add(NEItems.WHITE_CONCRETE_POWDER);
            entries.add(NEItems.YELLOW_CONCRETE_POWDER);
            entries.add(NEItems.GAME_PORTAL_OPENER);
            entries.add(NEItems.TATER_BOX);
            entries.add(NEItems.CREATIVE_TATER_BOX);
            entries.add(NEItems.TATER_GUIDEBOOK);
            TATERS.forEach(entries::add);
        })
        .build();

    public static final Item NUCLEOID_LOGO = createHead(NEBlocks.NUCLEOID_LOGO);

    public static final Item END_PORTAL = createSimple(NEBlocks.END_PORTAL, Items.BLACK_CARPET);
    public static final Item END_GATEWAY = createSimple(NEBlocks.END_GATEWAY, Items.BLACK_WOOL);
    public static final Item SAFE_TNT = createSimple(NEBlocks.SAFE_TNT, Items.TNT);

    public static final Item GOLD_LAUNCH_PAD = createSimple(NEBlocks.GOLD_LAUNCH_PAD, Items.LIGHT_WEIGHTED_PRESSURE_PLATE);
    public static final Item IRON_LAUNCH_PAD = createSimple(NEBlocks.IRON_LAUNCH_PAD, Items.HEAVY_WEIGHTED_PRESSURE_PLATE);

    public static final Item CONTRIBUTOR_STATUE = createSimple(NEBlocks.CONTRIBUTOR_STATUE, Items.SMOOTH_STONE);

    public static final Item INFINITE_DISPENSER = createSimple(NEBlocks.INFINITE_DISPENSER, Items.DISPENSER);
    public static final Item INFINITE_DROPPER = createSimple(NEBlocks.INFINITE_DROPPER, Items.DROPPER);
    public static final Item SNAKE_BLOCK = createSimple(NEBlocks.SNAKE_BLOCK, Items.LIME_CONCRETE);
    public static final Item FAST_SNAKE_BLOCK = createSimple(NEBlocks.FAST_SNAKE_BLOCK, Items.LIGHT_BLUE_CONCRETE);

    public static final Item TRANSIENT_IRON_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_IRON_DOOR, new Item.Settings(), Items.IRON_DOOR);
    public static final Item TRANSIENT_OAK_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_OAK_DOOR, new Item.Settings(), Items.OAK_DOOR);
    public static final Item TRANSIENT_SPRUCE_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_SPRUCE_DOOR, new Item.Settings(), Items.SPRUCE_DOOR);
    public static final Item TRANSIENT_BIRCH_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_BIRCH_DOOR, new Item.Settings(), Items.BIRCH_DOOR);
    public static final Item TRANSIENT_JUNGLE_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_JUNGLE_DOOR, new Item.Settings(), Items.JUNGLE_DOOR);
    public static final Item TRANSIENT_ACACIA_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_ACACIA_DOOR, new Item.Settings(), Items.ACACIA_DOOR);
    public static final Item TRANSIENT_CHERRY_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_CHERRY_DOOR, new Item.Settings(), Items.CHERRY_DOOR);
    public static final Item TRANSIENT_DARK_OAK_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_DARK_OAK_DOOR, new Item.Settings(), Items.DARK_OAK_DOOR);
    public static final Item TRANSIENT_MANGROVE_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_MANGROVE_DOOR, new Item.Settings(), Items.MANGROVE_DOOR);
    public static final Item TRANSIENT_BAMBOO_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_BAMBOO_DOOR, new Item.Settings(), Items.BAMBOO_DOOR);
    public static final Item TRANSIENT_CRIMSON_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_CRIMSON_DOOR, new Item.Settings(), Items.CRIMSON_DOOR);
    public static final Item TRANSIENT_WARPED_DOOR = new LobbyTallBlockItem(NEBlocks.TRANSIENT_WARPED_DOOR, new Item.Settings(), Items.WARPED_DOOR);

    public static final Item BLACK_CONCRETE_POWDER = createSimple(NEBlocks.BLACK_CONCRETE_POWDER, Items.BLACK_CONCRETE_POWDER);
    public static final Item BLUE_CONCRETE_POWDER = createSimple(NEBlocks.BLUE_CONCRETE_POWDER, Items.BLUE_CONCRETE_POWDER);
    public static final Item BROWN_CONCRETE_POWDER = createSimple(NEBlocks.BROWN_CONCRETE_POWDER, Items.BROWN_CONCRETE_POWDER);
    public static final Item CYAN_CONCRETE_POWDER = createSimple(NEBlocks.CYAN_CONCRETE_POWDER, Items.CYAN_CONCRETE_POWDER);
    public static final Item GREEN_CONCRETE_POWDER = createSimple(NEBlocks.GREEN_CONCRETE_POWDER, Items.GREEN_CONCRETE_POWDER);
    public static final Item GRAY_CONCRETE_POWDER = createSimple(NEBlocks.GRAY_CONCRETE_POWDER, Items.GRAY_CONCRETE_POWDER);
    public static final Item LIGHT_BLUE_CONCRETE_POWDER = createSimple(NEBlocks.LIGHT_BLUE_CONCRETE_POWDER, Items.LIGHT_BLUE_CONCRETE_POWDER);
    public static final Item LIGHT_GRAY_CONCRETE_POWDER = createSimple(NEBlocks.LIGHT_GRAY_CONCRETE_POWDER, Items.LIGHT_GRAY_CONCRETE_POWDER);
    public static final Item LIME_CONCRETE_POWDER = createSimple(NEBlocks.LIME_CONCRETE_POWDER, Items.LIME_CONCRETE_POWDER);
    public static final Item MAGENTA_CONCRETE_POWDER = createSimple(NEBlocks.MAGENTA_CONCRETE_POWDER, Items.MAGENTA_CONCRETE_POWDER);
    public static final Item ORANGE_CONCRETE_POWDER = createSimple(NEBlocks.ORANGE_CONCRETE_POWDER, Items.ORANGE_CONCRETE_POWDER);
    public static final Item PINK_CONCRETE_POWDER = createSimple(NEBlocks.PINK_CONCRETE_POWDER, Items.PINK_CONCRETE_POWDER);
    public static final Item PURPLE_CONCRETE_POWDER = createSimple(NEBlocks.PURPLE_CONCRETE_POWDER, Items.PURPLE_CONCRETE_POWDER);
    public static final Item RED_CONCRETE_POWDER = createSimple(NEBlocks.RED_CONCRETE_POWDER, Items.RED_CONCRETE_POWDER);
    public static final Item WHITE_CONCRETE_POWDER = createSimple(NEBlocks.WHITE_CONCRETE_POWDER, Items.WHITE_CONCRETE_POWDER);
    public static final Item YELLOW_CONCRETE_POWDER = createSimple(NEBlocks.YELLOW_CONCRETE_POWDER, Items.YELLOW_CONCRETE_POWDER);

    public static final Item QUTMC_LOGO = createHead(NEBlocks.QUTMC_LOGO);

    static final Item DICE_TATER = createHead(NEBlocks.DICE_TATER);

    public static final Item TINY_POTATO = createHead(NEBlocks.TINY_POTATO);
    public static final Item COAL_TATER = createHead(NEBlocks.COAL_TATER);
    public static final Item DIAMOND_TATER = createHead(NEBlocks.DIAMOND_TATER);
    public static final Item EMERALD_TATER = createHead(NEBlocks.EMERALD_TATER);
    public static final Item GOLD_TATER = createHead(NEBlocks.GOLD_TATER);
    public static final Item IRON_TATER = createHead(NEBlocks.IRON_TATER);
    public static final Item NETHERITE_TATER = createHead(NEBlocks.NETHERITE_TATER);
    public static final Item TATER_OF_UNDYING = createHead(NEBlocks.TATER_OF_UNDYING);

    public static final Item TATEROID = createHead(NEBlocks.TATEROID);
    public static final Item RED_TATEROID = createHead(NEBlocks.RED_TATEROID);
    public static final Item ORANGE_TATEROID = createHead(NEBlocks.ORANGE_TATEROID);
    public static final Item YELLOW_TATEROID = createHead(NEBlocks.YELLOW_TATEROID);
    public static final Item GREEN_TATEROID = createHead(NEBlocks.GREEN_TATEROID);
    public static final Item BLUE_TATEROID = createHead(NEBlocks.BLUE_TATEROID);
    public static final Item PURPLE_TATEROID = createHead(NEBlocks.PURPLE_TATEROID);

    public static final Item TRANS_TATER = createHead(NEBlocks.TRANS_TATER);
    public static final Item ASEXUAL_TATER = createHead(NEBlocks.ASEXUAL_TATER);
    public static final Item BI_TATER = createHead(NEBlocks.BI_TATER);
    public static final Item GAY_TATER = createHead(NEBlocks.GAY_TATER);
    public static final Item LESBIAN_TATER = createHead(NEBlocks.LESBIAN_TATER);
    public static final Item NONBINARY_TATER = createHead(NEBlocks.NONBINARY_TATER);
    public static final Item PAN_TATER = createHead(NEBlocks.PAN_TATER);
    public static final Item GENDERFLUID_TATER = createHead(NEBlocks.GENDERFLUID_TATER);
    public static final Item DEMISEXUAL_TATER = createHead(NEBlocks.DEMISEXUAL_TATER);

    public static final Item CORRUPTATER = createHead(NEBlocks.CORRUPTATER);

    public static final Item TATER_BOX = new TaterBoxItem(new Item.Settings().maxDamage(0));
    public static final Item CREATIVE_TATER_BOX = new CreativeTaterBoxItem(new Item.Settings().maxDamage(0));

    public static final Item TATER_GUIDEBOOK = new TaterGuidebookItem(new Item.Settings().maxCount(1));
    public static final Item QUICK_ARMOR_STAND = new QuickArmorStandItem(new Item.Settings());
    public static final Item GAME_PORTAL_OPENER = new GamePortalOpenerItem(new Item.Settings().maxCount(1));
    public static final Item LAUNCH_FEATHER = new LaunchFeatherItem(new Item.Settings().maxCount(1));

    public static final Item RULE_BOOK = new RuleBookItem(new Item.Settings().rarity(Rarity.EPIC));

    private static Item createHead(Block head) {
        if (head instanceof TinyPotatoBlock tinyPotatoBlock) {
            return new LobbyHeadItem(head, new Item.Settings(), tinyPotatoBlock.getItemTexture());
        } else if (head instanceof PolymerHeadBlock headBlock) {
            return new LobbyHeadItem(head, new Item.Settings(), headBlock.getPolymerSkinValue(head.getDefaultState(), BlockPos.ORIGIN, null));
        }

        return createSimple(head, Items.STONE);
    }

    private static Item createSimple(Block block, Item virtual) {
        return new LobbyBlockItem(block, new Item.Settings(), virtual);
    }

    public static void register() {
        register("end_portal", END_PORTAL);
        register("end_gateway", END_GATEWAY);
        register("safe_tnt", SAFE_TNT);
        register("gold_launch_pad", GOLD_LAUNCH_PAD);
        register("iron_launch_pad", IRON_LAUNCH_PAD);
        register("contributor_statue", CONTRIBUTOR_STATUE);
        register("infinite_dispenser", INFINITE_DISPENSER);
        register("infinite_dropper", INFINITE_DROPPER);
        register("snake_block", SNAKE_BLOCK);
        register("fast_snake_block", FAST_SNAKE_BLOCK);

        register("transient_iron_door", TRANSIENT_IRON_DOOR);
        register("transient_oak_door", TRANSIENT_OAK_DOOR);
        register("transient_spruce_door", TRANSIENT_SPRUCE_DOOR);
        register("transient_birch_door", TRANSIENT_BIRCH_DOOR);
        register("transient_jungle_door", TRANSIENT_JUNGLE_DOOR);
        register("transient_acacia_door", TRANSIENT_ACACIA_DOOR);
        register("transient_cherry_door", TRANSIENT_CHERRY_DOOR);
        register("transient_dark_oak_door", TRANSIENT_DARK_OAK_DOOR);
        register("transient_mangrove_door", TRANSIENT_MANGROVE_DOOR);
        register("transient_bamboo_door", TRANSIENT_BAMBOO_DOOR);
        register("transient_crimson_door", TRANSIENT_CRIMSON_DOOR);
        register("transient_warped_door", TRANSIENT_WARPED_DOOR);

        register("black_concrete_powder", BLACK_CONCRETE_POWDER);
        register("blue_concrete_powder", BLUE_CONCRETE_POWDER);
        register("brown_concrete_powder", BROWN_CONCRETE_POWDER);
        register("cyan_concrete_powder", CYAN_CONCRETE_POWDER);
        register("green_concrete_powder", GREEN_CONCRETE_POWDER);
        register("gray_concrete_powder", GRAY_CONCRETE_POWDER);
        register("light_blue_concrete_powder", LIGHT_BLUE_CONCRETE_POWDER);
        register("light_gray_concrete_powder", LIGHT_GRAY_CONCRETE_POWDER);
        register("lime_concrete_powder", LIME_CONCRETE_POWDER);
        register("magenta_concrete_powder", MAGENTA_CONCRETE_POWDER);
        register("orange_concrete_powder", ORANGE_CONCRETE_POWDER);
        register("pink_concrete_powder", PINK_CONCRETE_POWDER);
        register("purple_concrete_powder", PURPLE_CONCRETE_POWDER);
        register("red_concrete_powder", RED_CONCRETE_POWDER);
        register("white_concrete_powder", WHITE_CONCRETE_POWDER);
        register("yellow_concrete_powder", YELLOW_CONCRETE_POWDER);

        registerTater("qutmc_logo", QUTMC_LOGO);
        registerTater("nucleoid_logo", NUCLEOID_LOGO);

        registerTater("tiny_potato", TINY_POTATO);
        registerTater("coal_tater", COAL_TATER);
        registerTater("diamond_tater", DIAMOND_TATER);
        registerTater("emerald_tater", EMERALD_TATER);
        registerTater("gold_tater", GOLD_TATER);
        registerTater("iron_tater", IRON_TATER);
        registerTater("netherite_tater", NETHERITE_TATER);
        registerTater("tater_of_undying", TATER_OF_UNDYING);

        registerTater("dice_tater", DICE_TATER);

        registerTater("trans_tater", TRANS_TATER);
        registerTater("asexual_tater", ASEXUAL_TATER);
        registerTater("bi_tater", BI_TATER);
        registerTater("gay_tater", GAY_TATER);
        registerTater("lesbian_tater", LESBIAN_TATER);
        registerTater("nonbinary_tater", NONBINARY_TATER);
        registerTater("genderfluid_tater", GENDERFLUID_TATER);
        registerTater("demisexual_tater", DEMISEXUAL_TATER);
        registerTater("pan_tater", PAN_TATER);

        registerTater("tateroid", TATEROID);
        registerTater("red_tateroid", RED_TATEROID);
        registerTater("orange_tateroid", ORANGE_TATEROID);
        registerTater("yellow_tateroid", YELLOW_TATEROID);
        registerTater("green_tateroid", GREEN_TATEROID);
        registerTater("blue_tateroid", BLUE_TATEROID);
        registerTater("purple_tateroid", PURPLE_TATEROID);

        registerTater("corruptater", CORRUPTATER);

        register("tater_box", TATER_BOX);
        register("creative_tater_box", CREATIVE_TATER_BOX);

        register("tater_guidebook", TATER_GUIDEBOOK);
        register("quick_armor_stand", QUICK_ARMOR_STAND);
        register("game_portal_opener", GAME_PORTAL_OPENER);
        register("launch_feather", LAUNCH_FEATHER);
        register("rule_book", RULE_BOOK);

        PolymerItemGroupUtils.registerPolymerItemGroup(NucleoidExtras.identifier("general"), ITEM_GROUP);

        ServerPlayConnectionEvents.JOIN.register(NEItems::onPlayerJoin);

        UseBlockCallback.EVENT.register(NEItems::onUseBlock);
        UseEntityCallback.EVENT.register(NEItems::onUseEntity);
    }

    private static boolean tryOfferStack(ServerPlayerEntity player, Item item, Consumer<ItemStack> consumer) {
        var inventory = player.getInventory();

        if (inventory.containsAny(Collections.singleton(item))) {
            return false;
        }

        var stack = new ItemStack(item);
        consumer.accept(stack);

        player.getInventory().offer(stack, true);
        return true;
    }

    private static boolean tryOfferStack(ServerPlayerEntity player, Item item) {
        return tryOfferStack(player, item, stack -> {});
    }

    private static void onPlayerJoin(ServerPlayNetworkHandler handler, PacketSender packetSender, MinecraftServer server) {
        giveLobbyItems(handler.getPlayer());
    }

    public static void giveLobbyItems(ServerPlayerEntity player) {
        var config = NucleoidExtrasConfig.get();

        tryOfferStack(player, TATER_BOX);

        if (config.rules() != null) {
            tryOfferStack(player, RULE_BOOK);
        }

        config.gamePortalOpener().ifPresent(gamePortal -> {
            tryOfferStack(player, GAME_PORTAL_OPENER, stack -> {
                GamePortalOpenerItem.setGamePortalId(stack, gamePortal);
            });
        });
    }

    public static boolean canUseTaters(ServerPlayerEntity player) {
        return !GameSpaceManager.get().inGame(player);
    }

    private static ActionResult onUseBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if (!player.getWorld().isClient() && hitResult != null && hand == Hand.MAIN_HAND) {
            ItemStack stack = player.getStackInHand(hand);
            BlockPos pos = hitResult.getBlockPos();

            PlayerLobbyState state = PlayerLobbyState.get(player);
            state.collectTaterFromBlock(world, pos, stack, (ServerPlayerEntity) player);
        }

        return ActionResult.PASS;
    }

    private static ActionResult onUseEntity(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) {
        if (!player.getWorld().isClient() && hitResult != null) {
            ItemStack stack = player.getStackInHand(hand);
            Vec3d hitPos = hitResult.getPos().subtract(entity.getPos());

            PlayerLobbyState state = PlayerLobbyState.get(player);
            state.collectTaterFromEntity(entity, hitPos, stack, (ServerPlayerEntity) player);
        }

        return ActionResult.PASS;
    }

    private static <T extends Item> T registerTater(String id, T item) {
        register(id, item);
        TATERS.add(item);
        return item;
    }

    private static <T extends Item> T register(String id, T item) {
        return Registry.register(Registries.ITEM, NucleoidExtras.identifier(id), item);
    }
}
