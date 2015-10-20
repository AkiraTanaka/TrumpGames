package wing.dev.common.model;

import java.util.concurrent.ThreadLocalRandom;

public class CPU extends Player {
	/**
	 * コンストラクタ
	 * @param no プレイヤーNo
	 * @param name プレイヤー名
	 * @param isTestMode テストモードフラグ
	 */
	public CPU(int no, String name, boolean isTestMode) {
		super(no, name, true, isTestMode);
	}

	/**
	 * トランプの数字を取得
	 * @param maxNumber 上限値
	 * @return トランプの選択値
	 */
	@Override
	public int selectTrumpNumber(int maxNumber) {
		// CPUなので、自動選択
		return ThreadLocalRandom.current().nextInt(1, maxNumber + 1);
	}

	@Override
	public void showTehudaWithSort() {
		if (m_testModeFlag == true) {
			super.showTehudaWithSort();
		} else {
			showHiddenTehai();
		}
	}

	@Override
	public void showTehuda() {
		if (m_testModeFlag == true) {
			super.showTehuda();
		} else {
			showHiddenTehai();
		}
	}
}
