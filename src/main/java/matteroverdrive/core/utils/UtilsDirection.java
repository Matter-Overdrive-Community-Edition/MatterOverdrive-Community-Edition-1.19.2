// Credit to AurilisDev https://github.com/aurilisdev/Electrodynamics
package matteroverdrive.core.utils;

import net.minecraft.core.Direction;

public class UtilsDirection {

	public static final int[][] RELATIVE_MATRIX = { { 3, 2, 1, 0, 5, 4 }, { 4, 5, 0, 1, 2, 3 }, { 0, 1, 3, 2, 4, 5 },
			{ 0, 1, 2, 3, 5, 4 }, { 0, 1, 5, 4, 3, 2 }, { 0, 1, 4, 5, 2, 3 } };

	public static Direction getRelativeSide(Direction main, Direction relative) {
		if (main == null || relative == null) {
			return Direction.UP;
		}
		return Direction.from3DDataValue(RELATIVE_MATRIX[main.ordinal()][relative.ordinal()]);
	}

}
