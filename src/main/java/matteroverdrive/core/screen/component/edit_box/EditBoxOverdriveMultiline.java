package matteroverdrive.core.screen.component.edit_box;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import matteroverdrive.References;
import matteroverdrive.core.screen.GenericScreen;
import matteroverdrive.core.screen.component.button.ButtonOverdrive;
import matteroverdrive.core.screen.component.utils.ITexture;
import matteroverdrive.core.utils.UtilsRendering;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Predicate;

public class EditBoxOverdriveMultiline extends EditBox {
	protected GenericScreen<?> gui;
	protected EditBoxOverdrive.EditBoxTextures texture;

	public EditBoxOverdriveMultiline(EditBoxOverdrive.EditBoxTextures texture, GenericScreen<?> gui, int x, int y, int width, int height) {
		super(gui.getFontRenderer(), x, y, width, height, Component.empty());
		this.gui = gui;
		this.texture = texture;
	}

	@Override
	public void renderButton(PoseStack stack, int mouseX, int mouseY, float partialTick) {
		if (!this.isVisible()) {
			return;
		}

		Font font = gui.getFontRenderer();

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		UtilsRendering.bindTexture(texture.getTexture());
		ButtonOverdrive.drawButton(stack, this.x, this.y, this.width, this.height);

		List<String> lines = List.of(this.value.split("(?<=\\G.{12})"));

		int lineNo = 0;

		String s = "";
		int j = 0;
		int k = 0;
		int l = 0;
		int k1 = 0;
		int i1 = 0;
		int i2 = 0;
		int j1;
		boolean flag;
		boolean flag1 = false;
		boolean flag2 = false;

		for (String line : lines) {
			s = font.plainSubstrByWidth(line, this.getInnerWidth());

			i2 = this.isEditable ? this.textColor : this.textColorUneditable;

			j = this.cursorPos - this.displayPos;
			k = this.highlightPos - this.displayPos;

			flag = j >= 0 && j <= s.length();
			flag1 = this.isFocused() && this.frame / 6 % 2 == 0 && flag;

			l = this.x + 4;
			i1 = this.y + 4 + (10 * lineNo++);

			j1 = l;

			if (k > s.length()) {
				k = s.length();
			}

			if (!s.isEmpty()) {
				String s1 = flag ? s.substring(0, j) : s;

				j1 = font.drawShadow(stack, this.formatter.apply(s1, this.displayPos), (float) l, (float) i1, i2);
			}

			flag2 = this.cursorPos < this.value.length() || this.value.length() >= this.getMaxLength();

			k1 = j1;

			if (!flag) {
				k1 = j > 0 ? l + this.width : l;
			} else if (flag2) {
				k1 = j1 - 1;
				--j1;
			}

			if (!s.isEmpty() && flag && j < s.length()) {
				font.drawShadow(stack, this.formatter.apply(s.substring(j), this.cursorPos), (float) j1, (float) i1, i2);
			}

			if (!flag2 && this.suggestion != null) {
				font.drawShadow(stack, this.suggestion, (float) (k1 - 1), (float) i1, -8355712);
			}
		}

		if (flag1) {
			if (flag2) {
				GuiComponent.fill(stack, k1, i1 - 1, k1 + 1, i1 + 1 + 9, -3092272);
			} else {
				font.drawShadow(stack, "_", (float) k1, (float) i1, i2);
			}
		}

		if (k != j) {
			int l1 = l + font.width(s.substring(0, k));

			this.renderHighlight(k1, i1 - 1, l1 - 1, i1 + 1 + 9);
		}
	}
}
