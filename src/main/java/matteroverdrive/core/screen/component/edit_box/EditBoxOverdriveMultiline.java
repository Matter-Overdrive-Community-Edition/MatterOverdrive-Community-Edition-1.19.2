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

import java.util.ArrayList;
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

	private List<String> calculateLines(Font font, String input) {
		// Calculate all the lines.
		List<String> lines = new ArrayList<>();

		lines.add("");

		int lineNo = 0;

		for (int index=0; index<input.length(); index++) {
			String curLine = lines.get(lineNo);
			char curChar = input.charAt(index);

			if (font.width(curLine + curChar)
					> getInnerWidth()) {
				lineNo += 1;

				lines.add("");

				curLine = lines.get(lineNo);
			}

			lines.set(lineNo, curLine + curChar);
		}

		return lines;
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

//		System.out.println("Position: " + this.cursorPos);

		if (this.cursorPos > 59) {
			this.cursorPos = 59;
		}

		List<String> lines = calculateLines(font, this.value);

		int lineNo = 0;

		int xpos = this.x + 4;

		for (String line : lines) {
			int ypos = this.y + 4 + (10 * lineNo);

			textColor = this.isEditable ? this.textColor : this.textColorUneditable;

			font.drawShadow(stack, this.formatter.apply(line, this.cursorPos),
						(float) xpos, (float) ypos, textColor);

			lineNo++;
		}

//		System.out.println(cursorPos);
//
//		int charWidth = font.width(lines.get(0));
//
//		int x1 = this.x + 4 + charWidth;
//		int y1 = this.y + 4;
//
//		if (this.x + 4 + charWidth > getInnerWidth()) {
//			x1 = this.x + 4 + font.width(lines.get(1));
//			y1 = this.y + 4 + 10;
//		}
//
////		int x1 = this.x + 4 + font.width(lines.get(lineNo-1)) + 1;
////
////		int y1 = this.y + 4 + (((charWidth-1) / getInnerWidth()) * 10);
//
//		GuiComponent.fill(stack, x1 - 1, y1, x1, y1 + 8, -3092272);
	}
}
