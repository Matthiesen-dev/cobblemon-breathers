package dev.matthiesen.common.cobblemon_breathers.compat.accessories.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import io.wispforest.accessories.api.client.Side;
import io.wispforest.accessories.api.client.SimpleAccessoryRenderer;
import io.wispforest.accessories.api.slot.SlotReference;
import io.wispforest.accessories.mixin.client.ModelPartAccessor;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class ReBreatherRenderer implements SimpleAccessoryRenderer {
    @Override
    public <M extends LivingEntity> void align(ItemStack stack, SlotReference reference, EntityModel<M> model, PoseStack matrices) {
        if(!(model instanceof HumanoidModel<?> humanoidModel)) return;
        transformToModelPart(matrices, humanoidModel.head);
    }

    private static void transformToModelPart(PoseStack poseStack, ModelPart part) {
        Side side = Side.FRONT;
        int xPercent = side.direction.getNormal().getX();
        int yPercent = side.direction.getNormal().getY();
        int zPercent = side.direction.getNormal().getZ();

        part.translateAndRotate(poseStack);
        var aabb = getAABB(part);
        poseStack.scale(1 / 16f, 1 / 16f, 1 / 16f);
        poseStack.translate(
                Mth.lerp((-(double) xPercent + 1) / 2, aabb.getFirst().x, aabb.getSecond().x),
                Mth.lerp((-(double) yPercent + 1) / 2, aabb.getFirst().y, aabb.getSecond().y) + 2.8,
                Mth.lerp((-(double) zPercent + 1) / 2, aabb.getFirst().z, aabb.getSecond().z) - 0.5
        );
        poseStack.scale(8, 8, 8);
    }

    @SuppressWarnings("ConstantConditions")
    private static Pair<Vec3, Vec3> getAABB(ModelPart part) {
        Vec3 min = new Vec3(0, 0, 0);
        Vec3 max = new Vec3(0, 0, 0);

        if (part.getClass().getSimpleName().contains("EMFModelPart")) {
            var parts = new ArrayList<ModelPart>();

            parts.add(part);
            parts.addAll(((ModelPartAccessor) (Object) part).getChildren().values());

            for (var modelPart : parts) {
                for (ModelPart.Cube cube : ((ModelPartAccessor) (Object) modelPart).getCubes()) {
                    min = new Vec3(
                            Math.min(min.x, Math.min(cube.minX + modelPart.x, cube.maxX + modelPart.x)),
                            Math.min(min.y, Math.min(cube.minY + modelPart.y, cube.maxY + modelPart.y)),
                            Math.min(min.z, Math.min(cube.minZ + modelPart.z, cube.maxZ + modelPart.z))
                    );
                    max = new Vec3(
                            Math.max(max.x, Math.max(cube.minX + modelPart.x, cube.maxX + modelPart.x)),
                            Math.max(max.y, Math.max(cube.minY + modelPart.y, cube.maxY + modelPart.y)),
                            Math.max(max.z, Math.max(cube.minZ + modelPart.z, cube.maxZ + modelPart.z))
                    );
                }
            }
        } else {
            for (ModelPart.Cube cube : ((ModelPartAccessor) (Object) part).getCubes()) {
                min = new Vec3(
                        Math.min(min.x, Math.min(cube.minX, cube.maxX)),
                        Math.min(min.y, Math.min(cube.minY, cube.maxY)),
                        Math.min(min.z, Math.min(cube.minZ, cube.maxZ))
                );
                max = new Vec3(
                        Math.max(max.x, Math.max(cube.minX, cube.maxX)),
                        Math.max(max.y, Math.max(cube.minY, cube.maxY)),
                        Math.max(max.z, Math.max(cube.minZ, cube.maxZ))
                );
            }
        }

        return Pair.of(min, max);
    }
}
