package xyz.nucleoid.extras.lobby;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import xyz.nucleoid.extras.NucleoidExtras;
import xyz.nucleoid.extras.lobby.block.*;
import xyz.nucleoid.extras.lobby.block.tater.*;

public class NEBlocks {
    public static final Block NUCLEOID_LOGO = createTaterBlock(ParticleTypes.GLOW_SQUID_INK, "bac7400dfcb9a387361a3ad7c296943eb841a9bda13ad89558e2d6efebf167bc");

    public static final Block END_PORTAL = createSimple(Blocks.END_PORTAL);
    public static final Block END_GATEWAY = new VirtualEndGatewayBlock(AbstractBlock.Settings.create().pistonBehavior(PistonBehavior.BLOCK).strength(100).noCollision());
    public static final Block SAFE_TNT = createSimple(Blocks.TNT);

    public static final Block BLACK_CONCRETE_POWDER = createSimple(Blocks.BLACK_CONCRETE_POWDER);
    public static final Block BLUE_CONCRETE_POWDER = createSimple(Blocks.BLUE_CONCRETE_POWDER);
    public static final Block BROWN_CONCRETE_POWDER = createSimple(Blocks.BROWN_CONCRETE_POWDER);
    public static final Block CYAN_CONCRETE_POWDER = createSimple(Blocks.CYAN_CONCRETE_POWDER);
    public static final Block GREEN_CONCRETE_POWDER = createSimple(Blocks.GREEN_CONCRETE_POWDER);
    public static final Block GRAY_CONCRETE_POWDER = createSimple(Blocks.GRAY_CONCRETE_POWDER);
    public static final Block LIGHT_BLUE_CONCRETE_POWDER = createSimple(Blocks.LIGHT_BLUE_CONCRETE_POWDER);
    public static final Block LIGHT_GRAY_CONCRETE_POWDER = createSimple(Blocks.LIGHT_GRAY_CONCRETE_POWDER);
    public static final Block LIME_CONCRETE_POWDER = createSimple(Blocks.LIME_CONCRETE_POWDER);
    public static final Block MAGENTA_CONCRETE_POWDER = createSimple(Blocks.MAGENTA_CONCRETE_POWDER);
    public static final Block ORANGE_CONCRETE_POWDER = createSimple(Blocks.ORANGE_CONCRETE_POWDER);
    public static final Block PINK_CONCRETE_POWDER = createSimple(Blocks.PINK_CONCRETE_POWDER);
    public static final Block PURPLE_CONCRETE_POWDER = createSimple(Blocks.PURPLE_CONCRETE_POWDER);
    public static final Block RED_CONCRETE_POWDER = createSimple(Blocks.RED_CONCRETE_POWDER);
    public static final Block WHITE_CONCRETE_POWDER = createSimple(Blocks.WHITE_CONCRETE_POWDER);
    public static final Block YELLOW_CONCRETE_POWDER = createSimple(Blocks.YELLOW_CONCRETE_POWDER);

    public static final Block GOLD_LAUNCH_PAD = new LaunchPadBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE).strength(100).noCollision(), Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
    public static final Block IRON_LAUNCH_PAD = new LaunchPadBlock(AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).strength(100).noCollision(), Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);

    public static final Block CONTRIBUTOR_STATUE = new ContributorStatueBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE).strength(100));

    public static final Block INFINITE_DISPENSER = new InfiniteDispenserBlock(AbstractBlock.Settings.copy(Blocks.DISPENSER).strength(100));
    public static final Block INFINITE_DROPPER = new InfiniteDropperBlock(AbstractBlock.Settings.copy(Blocks.DROPPER).strength(100));

    public static final Block SNAKE_BLOCK = new SnakeBlock(AbstractBlock.Settings.copy(Blocks.LIME_CONCRETE).strength(100), Blocks.LIME_CONCRETE, 8, 7);
    public static final Block FAST_SNAKE_BLOCK = new SnakeBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_CONCRETE).strength(100), Blocks.LIGHT_BLUE_CONCRETE, 4, 7);

    public static final Block TRANSIENT_IRON_DOOR = new TransientDoorBlock(Blocks.IRON_DOOR);
    public static final Block TRANSIENT_OAK_DOOR = new TransientDoorBlock(Blocks.OAK_DOOR);
    public static final Block TRANSIENT_SPRUCE_DOOR = new TransientDoorBlock(Blocks.SPRUCE_DOOR);
    public static final Block TRANSIENT_BIRCH_DOOR = new TransientDoorBlock(Blocks.BIRCH_DOOR);
    public static final Block TRANSIENT_JUNGLE_DOOR = new TransientDoorBlock(Blocks.JUNGLE_DOOR);
    public static final Block TRANSIENT_ACACIA_DOOR = new TransientDoorBlock(Blocks.ACACIA_DOOR);
    public static final Block TRANSIENT_CHERRY_DOOR = new TransientDoorBlock(Blocks.CHERRY_DOOR);
    public static final Block TRANSIENT_DARK_OAK_DOOR = new TransientDoorBlock(Blocks.DARK_OAK_DOOR);
    public static final Block TRANSIENT_MANGROVE_DOOR = new TransientDoorBlock(Blocks.MANGROVE_DOOR);
    public static final Block TRANSIENT_BAMBOO_DOOR = new TransientDoorBlock(Blocks.BAMBOO_DOOR);
    public static final Block TRANSIENT_CRIMSON_DOOR = new TransientDoorBlock(Blocks.CRIMSON_DOOR);
    public static final Block TRANSIENT_WARPED_DOOR = new TransientDoorBlock(Blocks.WARPED_DOOR);

    public static final Block QUTMC_LOGO = createTaterBlock(ParticleTypes.GLOW_SQUID_INK, "ewogICJ0aW1lc3RhbXAiIDogMTcyNjkwMTk1NzExOCwKICAicHJvZmlsZUlkIiA6ICJkMWRkMDJlOWQwN2E0YWU1YWRjYWQyYzI5YTZhYmIyMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJSYW5pdGFfRmFjaGExOCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zYWJmOWQ3NWQzODhiNjFjZTk2YWE2NzhjOTY4YjViM2FlMDE3MzRlYzJiMjA5NzU1OWE2ZDcwYTljODU3MDZjIgogICAgfQogIH0KfQ==");

    public static final Block TINY_POTATO = createTaterBlock(ParticleTypes.HEART, "573514a23245f15dbad5fb4e622163020864cce4c15d56de3adb90fa5a7137fd");

    public static final Block TRANS_TATER = createColorPatternTaterBlock(new Vector3f[]{
        Vec3d.unpackRgb(0xEE90AD).toVector3f(), // pink
        Vec3d.unpackRgb(0x3CB0DA).toVector3f(), // blue
        Vec3d.unpackRgb(0xCFD5D6).toVector3f(), // white
    }, "f77dbc809b254449023fac0dd4e0d9100b5c4407748be089f0e00c7ef7ab764");
    public static final Block ASEXUAL_TATER = createColorPatternTaterBlock(new Vector3f[]{
        Vec3d.unpackRgb(0x16161B).toVector3f(), // black
        Vec3d.unpackRgb(0x3F4548).toVector3f(), // gray
        Vec3d.unpackRgb(0xCFD5D6).toVector3f(), // white
        Vec3d.unpackRgb(0x7B2BAD).toVector3f(), // purple
    }, "3902887dc55d4f736d0b566ad812f256113aaa4a318ffb865623fb5a677aef32");
    public static final Block BI_TATER = createColorPatternTaterBlock(new Vector3f[]{
        Vec3d.unpackRgb(0xBE46B5).toVector3f(), // pink
        Vec3d.unpackRgb(0x7B2BAD).toVector3f(), // purple
        Vec3d.unpackRgb(0x353A9E).toVector3f(), // blue
    }, "4526a72ca5be42920cd310280c03e2c9e9a70c55aa9cc1a0c48396d556f1c75d");
    public static final Block GAY_TATER = createColorPatternTaterBlock(new Vector3f[]{
        Vec3d.unpackRgb(0xA12823).toVector3f(), // red
        Vec3d.unpackRgb(0xF17716).toVector3f(), // orange
        Vec3d.unpackRgb(0xF9C629).toVector3f(), // yellow
        Vec3d.unpackRgb(0x556E1C).toVector3f(), // green
        Vec3d.unpackRgb(0x353A9E).toVector3f(), // blue
        Vec3d.unpackRgb(0x7B2BAD).toVector3f(), // purple
    }, "f9f446f29396ff444d0ef4f53a70c28afb69e5d1da037c03c277d23917dacded");
    public static final Block LESBIAN_TATER = createColorPatternTaterBlock(new Vector3f[]{
        Vec3d.unpackRgb(0xA12823).toVector3f(), // red
        Vec3d.unpackRgb(0xF17716).toVector3f(), // orange
        Vec3d.unpackRgb(0xEAEDED).toVector3f(), // white
        Vec3d.unpackRgb(0xEE90AD).toVector3f(), // pink
        Vec3d.unpackRgb(0xBE46B5).toVector3f(), // magenta
    }, "44492740f40c19c3e52871cdf6cbd585e980fc7b50cb0fc949bfbe44032a7db7");
    public static final Block NONBINARY_TATER = createColorPatternTaterBlock(new Vector3f[]{
        Vec3d.unpackRgb(0xF9C629).toVector3f(), // yellow
        Vec3d.unpackRgb(0x16161B).toVector3f(), // black
        Vec3d.unpackRgb(0xCFD5D6).toVector3f(), // white
        Vec3d.unpackRgb(0x7B2BAD).toVector3f(), // purple
    }, "10854e473bc7a0a6956cb12df8026de9fc00fae40c0502a3182908bbb50c9aa5");
    public static final Block PAN_TATER = createColorPatternTaterBlock(new Vector3f[]{
        Vec3d.unpackRgb(0xFA318C).toVector3f(), // pink
        Vec3d.unpackRgb(0xFDD73B).toVector3f(), // yellow
        Vec3d.unpackRgb(0x2394F9).toVector3f(), // blue
    }, "3f761be18f070a016e4f61d37ec13b23032a552dcdb70a67f855c3ab2fae54e0");
    public static final Block GENDERFLUID_TATER = createColorPatternTaterBlock(new Vector3f[]{
        Vec3d.unpackRgb(0xBE46B5).toVector3f(), // pink
        Vec3d.unpackRgb(0xCFD5D6).toVector3f(), // white
        Vec3d.unpackRgb(0x7B2BAD).toVector3f(), // purple
        Vec3d.unpackRgb(0x16161B).toVector3f(), // black
        Vec3d.unpackRgb(0x2394F9).toVector3f(), // blue
    }, "ba066cdd8d48501eb51eea1e3e417c25ef51a04284714baad5ab5de5cd4221b8");
    public static final Block DEMISEXUAL_TATER = createColorPatternTaterBlock(new Vector3f[]{
        Vec3d.unpackRgb(0x16161B).toVector3f(), // black
        Vec3d.unpackRgb(0xCFD5D6).toVector3f(), // white
        Vec3d.unpackRgb(0x7B2BAD).toVector3f(), // purple
        Vec3d.unpackRgb(0x3F4548).toVector3f(), // gray
    }, "32b7cd2c5d70cab476ce951e2c520c9b3579250ad900164d6c2321c7f43d6dc7");

    public static final Block DICE_TATER = createDiceTaterBlock();
    public static final Block TATEROID = createTateroidBlock(SoundEvents.BLOCK_NOTE_BLOCK_BELL, -1, "8d531d40d09efd3a9a585b55e66a9a6f04c73af84d94d7c565549bf27b8b26bd");
    public static final Block RED_TATEROID = createTateroidBlock(SoundEvents.BLOCK_NOTE_BLOCK_GUITAR, 7 / 24d, "2be51b227360ab65776725a91cded84b56f6920eec0d6fb5a57d5f1ada147aa6");
    public static final Block ORANGE_TATEROID = createTateroidBlock(SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM, 4 / 24d, "c5362e308822cf1c436a4ba6d0c3976139c98621c7aa2a96be99c73e97708efc");
    public static final Block YELLOW_TATEROID = createTateroidBlock(SoundEvents.BLOCK_NOTE_BLOCK_CHIME, 2.5 / 24d, "fef74a6c7cb45d3c4bae134e6ec41fd7517f7eabe2c74dc76a51b39c63c38bc2");
    public static final Block GREEN_TATEROID = createTateroidBlock(SoundEvents.BLOCK_NOTE_BLOCK_BIT, 21 / 24d, "57bb692499560f0393314a9f1ec11425b360e43c1ddb560de261cd04b8cc8e69");
    public static final Block BLUE_TATEROID = createTateroidBlock(SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE, 17 / 24d, "89ad5aecfb9ab6f36261e0c462acecf2078e7e575d9373bacc0503224c44250e");
    public static final Block PURPLE_TATEROID = createTateroidBlock(SoundEvents.BLOCK_NOTE_BLOCK_FLUTE, 11 / 24d, "d16a37512cb7ca372af5f37f9bd95d4603c4fa44be4143fb26aaa324e681c9b0");


    public static final Block COAL_TATER = createTaterBlock(Blocks.COAL_BLOCK, "7eb25d3f8fcf48673ad0b171ea37154b43d57f6ab04d8ffb546fc606b8505bf4");
    public static final Block DIAMOND_TATER = createTaterBlock(Blocks.DIAMOND_BLOCK, "a399c9d599e0d9dc6a480e85f4dbecc45b318814026895ac8150fd2e2fa2599e");
    public static final Block EMERALD_TATER = createTaterBlock(ParticleTypes.HAPPY_VILLAGER, "cd76730df726b8ee9d72a3a478457d313626133de1d76c26cfc6af8e80e9c476");
    public static final Block GOLD_TATER = createTaterBlock(Blocks.GOLD_BLOCK, "180a7cc71153b89a536c148d2f1012d6772a7d3ba8321f922a6de46773c35af9");
    public static final Block IRON_TATER = createTaterBlock(Blocks.IRON_BLOCK, "174858c976f0274ebce3f3ffcef653609f29d37e0cc9cad25e586864b806cb23");
    public static final Block NETHERITE_TATER = createTaterBlock(Blocks.NETHERITE_BLOCK, "664dce4fade8e5f352001eff6900d9d4b142935ebed303106539f7ad0193621f");
    public static final Block TATER_OF_UNDYING = createTaterBlock(ParticleTypes.TOTEM_OF_UNDYING, "b526d93147825e2db444aaf4a9464b61ad4e4defb0adf944a2275543efc9192a");

    public static final Block CORRUPTATER = new CorruptaterBlock(createTaterBlockSettings(), 2);

    public static final BlockEntityType<LaunchPadBlockEntity> LAUNCH_PAD_ENTITY = FabricBlockEntityTypeBuilder.create(LaunchPadBlockEntity::new, GOLD_LAUNCH_PAD, IRON_LAUNCH_PAD).build();
    public static final BlockEntityType<ContributorStatueBlockEntity> CONTRIBUTOR_STATUE_ENTITY = FabricBlockEntityTypeBuilder.create(ContributorStatueBlockEntity::new, CONTRIBUTOR_STATUE).build();
    public static final BlockEntityType<TateroidBlockEntity> TATEROID_ENTITY = FabricBlockEntityTypeBuilder.create(TateroidBlockEntity::new, TATEROID, RED_TATEROID, ORANGE_TATEROID, YELLOW_TATEROID, GREEN_TATEROID, BLUE_TATEROID, PURPLE_TATEROID).build();
//    public static final BlockEntityType<DaylightDetectorTaterBlockEntity> DAYLIGHT_DETECTOR_TATER_ENTITY = FabricBlockEntityTypeBuilder.create(DaylightDetectorTaterBlockEntity::new, DAYLIGHT_DETECTOR_TATER, INVERTED_DAYLIGHT_DETECTOR_TATER).build();
//    public static final BlockEntityType<BellTaterBlockEntity> BELL_TATER_ENTITY = FabricBlockEntityTypeBuilder.create(BellTaterBlockEntity::new, BELL_TATER).build();

    private static Block createSimple(Block virtual) {
        return new SimplePolymerBlock(AbstractBlock.Settings.copy(virtual).strength(100), virtual);
    }

    private static AbstractBlock.Settings createTaterBlockSettings() {
        return AbstractBlock.Settings.create().mapColor(MapColor.PALE_GREEN).strength(100);
    }

    private static Block createBotanicTaterBlock(ParticleEffect effect, String textureUp, String textureDown) {
        return new BotanicalPotatoBlock(createTaterBlockSettings(), textureUp, textureDown, effect, 2);
    }

    private static Block createTaterBlock(ParticleEffect effect, String texture) {
        return new CubicPotatoBlock(createTaterBlockSettings(), effect, texture);
    }

    private static Block createTaterBlock(Block particleBlock, String texture) {
        return new CubicPotatoBlock(createTaterBlockSettings(), particleBlock, texture);
    }

    private static Block createTaterBlock(Item particleItem, String texture) {
        return new CubicPotatoBlock(createTaterBlockSettings(), particleItem, texture);
    }

    private static Block createTaterBlock(ParticleEffect effect, String texture, int particleRate) {
        return new CubicPotatoBlock(createTaterBlockSettings(), effect, texture, particleRate);
    }
  
    private static Block createColorPatternTaterBlock(Vector3f[] pattern, String texture) {
        return new ColorPatternTaterBlock(createTaterBlockSettings(), pattern, texture);
    }

    private static Block createLuckyTaterBlock(String texture, String cooldownTexture) {
        return new LuckyTaterBlock(createTaterBlockSettings(), texture, cooldownTexture);
    }

    private static Block createWardenTaterBlock(String texture) {
        return new WardenTaterBlock(createTaterBlockSettings(), texture);
    }

    private static Block createDiceTaterBlock() {
        return new DiceTaterBlock(createTaterBlockSettings());
    }

    private static Block createTateroidBlock(RegistryEntry<SoundEvent> defaultSound, double particleColor, String texture) {
        return new TateroidBlock(createTaterBlockSettings(), defaultSound, particleColor, texture);
    }

    private static Block createColorTaterBlock(DyeColor color, String texture) {
        return new ColorTaterBlock(createTaterBlockSettings(), color, texture);
    }

    private static Block createRedstoneTaterBlock(ParticleEffect effect, String texture) {
        return new RedstoneTaterBlock(createTaterBlockSettings(), effect, texture);
    }

//    private static Block createDaylightDetectorTaterBlock(String texture, boolean inverted) {
//        return new DaylightDetectorTaterBlock(createTaterBlockSettings(), texture, inverted);
//    }

    private static Block createTargetTaterBlock(String texture) {
        return new TargetTaterBlock(createTaterBlockSettings(), texture);
    }

//    private static Block createBellTaterBlock(String texture) {
//        return new BellTaterBlock(createTaterBlockSettings(), texture);
//    }

    private static Block createElderGuardianParticleTaterBlock(String texture) {
        return new ElderGuardianParticleTater(createTaterBlockSettings(), texture);
    }

    private static Block createCapsuleTaterBlock(Vector3f color, int weight, String texture) {
        return new CapsuleTaterBlock(createTaterBlockSettings(), color, weight, texture);
    }

    private static Block createMarkerTaterBlock(Block particleBlock, String texture) {
        return new MarkerTaterBlock(createTaterBlockSettings(), particleBlock, texture);
    }

    private static Block createLightTaterBlock(String texture) {
        return new LightTaterBlock(createTaterBlockSettings(), texture);
    }

    public static void register() {
        register("qutmc_logo", QUTMC_LOGO);
        register("nucleoid_logo", NUCLEOID_LOGO);

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

        register("tiny_potato", TINY_POTATO);

        register("coal_tater", COAL_TATER);
        register("diamond_tater", DIAMOND_TATER);
        register("emerald_tater", EMERALD_TATER);
        register("gold_tater", GOLD_TATER);
        register("iron_tater", IRON_TATER);
        register("netherite_tater", NETHERITE_TATER);
        register("tater_of_undying", TATER_OF_UNDYING);

        register("dice_tater", DICE_TATER);
        register("tateroid", TATEROID);
        register("red_tateroid", RED_TATEROID);
        register("orange_tateroid", ORANGE_TATEROID);
        register("yellow_tateroid", YELLOW_TATEROID);
        register("green_tateroid", GREEN_TATEROID);
        register("blue_tateroid", BLUE_TATEROID);
        register("purple_tateroid", PURPLE_TATEROID);

        register("trans_tater", TRANS_TATER);
        register("asexual_tater", ASEXUAL_TATER);
        register("bi_tater", BI_TATER);
        register("gay_tater", GAY_TATER);
        register("lesbian_tater", LESBIAN_TATER);
        register("nonbinary_tater", NONBINARY_TATER);
        register("pan_tater", PAN_TATER);
        register("genderfluid_tater", GENDERFLUID_TATER);
        register("demisexual_tater", DEMISEXUAL_TATER);

        register("corruptater", CORRUPTATER);

        registerBlockEntity("launch_pad", LAUNCH_PAD_ENTITY);
        registerBlockEntity("contributor_statue", CONTRIBUTOR_STATUE_ENTITY);
        registerBlockEntity("tateroid", TATEROID_ENTITY);
    }

    private static <T extends Block> T register(String id, T block) {
        return Registry.register(Registries.BLOCK, NucleoidExtras.identifier(id), block);
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String id, BlockEntityType<T> type) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, NucleoidExtras.identifier(id), type);
        PolymerBlockUtils.registerBlockEntity(type);
        return type;
    }
}
