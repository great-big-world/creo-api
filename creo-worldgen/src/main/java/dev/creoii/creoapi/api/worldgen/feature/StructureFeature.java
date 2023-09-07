package dev.creoii.creoapi.api.worldgen.feature;

import com.mojang.serialization.Codec;
import dev.creoii.creoapi.api.worldgen.feature.config.StructureFeatureConfig;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class StructureFeature extends Feature<StructureFeatureConfig> {
    public StructureFeature(Codec<StructureFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<StructureFeatureConfig> context) {
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos origin = context.getOrigin();
        int k = origin.getY();
        StructureFeatureConfig config = context.getConfig();
        int i = random.nextInt(config.structures().size());
        StructureTemplateManager manager = structureWorldAccess.toServerWorld().getServer().getStructureTemplateManager();
        StructureTemplate template = manager.getTemplateOrBlank(config.structures().get(i));
        ChunkPos chunkPos = new ChunkPos(origin);
        BlockBox blockBox = new BlockBox(chunkPos.getStartX() - 16, structureWorldAccess.getBottomY(), chunkPos.getStartZ() - 16, chunkPos.getEndX() + 16, structureWorldAccess.getTopY(), chunkPos.getEndZ() + 16);

        BlockRotation blockRotation = BlockRotation.random(random);
        StructurePlacementData structurePlacementData = new StructurePlacementData().setRotation(blockRotation).setBoundingBox(blockBox).setRandom(random);
        Vec3i vec3i = config.randomRotation() ? template.getRotatedSize(blockRotation) : template.getSize();
        BlockPos place = origin.add(-vec3i.getX() / 2, 0, -vec3i.getZ() / 2);
        if (config.forcedHeightmap() != null) {
            int j = origin.getY();
            for (k = 0; k < vec3i.getX(); ++k) {
                for (int l = 0; l < vec3i.getZ(); ++l) {
                    j = Math.min(j, structureWorldAccess.getTopY(config.forcedHeightmap(), place.getX() + k, place.getZ() + l));
                }
            }
            k = Math.max(j - 15 - random.nextInt(10), structureWorldAccess.getBottomY() + 10);
        }
        BlockPos blockPos3 = template.offsetByTransformedSize(place.withY(k), BlockMirror.NONE, config.randomRotation() ? blockRotation : BlockRotation.NONE);
        structurePlacementData.clearProcessors();
        config.processors().value().getList().forEach(structurePlacementData::addProcessor);
        template.place(structureWorldAccess, blockPos3, blockPos3, structurePlacementData, random, 4);
        structurePlacementData.clearProcessors();
        return true;
    }
}