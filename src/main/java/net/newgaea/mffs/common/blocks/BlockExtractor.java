package net.newgaea.mffs.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.newgaea.mffs.common.init.MFFSTiles;

public class BlockExtractor extends BlockSimpleNetwork{
    public BlockExtractor(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType interactWith(World world, BlockPos pos, PlayerEntity player) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof INamedContainerProvider) {
            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
        } else {
            throw new IllegalStateException("Our named container provider is missing!");
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return MFFSTiles.EXTRACTOR.create();
    }
}
