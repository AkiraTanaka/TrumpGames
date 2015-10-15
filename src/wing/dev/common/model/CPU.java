package wing.dev.common.model;

import java.util.List;
import java.util.Random;

public class CPU extends Player {
	public CPU(int no, String name, boolean isTestMode) {
		super(no, name, true, isTestMode);
	}

	// CPUなので、自動選択
	@Override
	public int selectTrump(List<Trump> trumpList) {
		int maxNumber = trumpList.size();
		Random r = new Random();
		// 1 ~ maxNumberなので
		int inputNumber = 1;
		if (maxNumber > 1) {
			inputNumber = r.nextInt(maxNumber-1) + 1;
		}
		return inputNumber;
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
