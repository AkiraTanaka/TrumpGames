package wing.dev.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wing.dev.common.control.TrumpComparator;
import wing.dev.common.util.IOUtil;
import wing.dev.common.util.LogUtil;

public class Player {
	protected boolean m_testModeFlag = false;
	protected int m_no = -1;
	protected String m_name = "no_name";
	protected boolean m_winFlag = false;
	protected boolean m_CPUFlag = false;
	protected List<Trump> m_tehuda = null;

	public Player(int no, String name, boolean isTestMode) {
		m_no = no;
		m_CPUFlag = false;
		setName(name);
		m_tehuda = new ArrayList<>();
		m_testModeFlag = isTestMode;
	}
	public Player(int no, String name, boolean isCPU, boolean isTestMode) {
		this(no, name, isTestMode);
		m_CPUFlag = isCPU;
	}

	public List<Trump> deleteDuplication() {
		printLog("重複する数字の手札を捨てます");
		showTehudaWithSort();
		boolean isUnique = false;
		List<Trump> sutehuda = new ArrayList<>();
		outside:while(!isUnique) {
			for (int i = 0; i < m_tehuda.size(); i++) {
				for (int j = i+1; j < m_tehuda.size(); j++) {
					if (m_tehuda.get(i).getNo() == m_tehuda.get(j).getNo()) {
						// 大きい数字から捨てる
						sutehuda.add(m_tehuda.remove(j));
						sutehuda.add(m_tehuda.remove(i));
						continue outside;
					}
				}
			}
			isUnique = true;
		}
		StringBuilder sb = new StringBuilder();
		for (Trump trump : sutehuda) {
			sb.append(trump.toString());
		}
		if (sb.toString().equals("") == true) {
			printLog("重複する数字はありませんでした");
		} else {
			printLog("重複する数字の手札を捨てました: " + sb.toString());
		}
		showTehudaWithSort();
		return sutehuda;
	}

	// Trumpを選択
	public int selectTrump(List<Trump> trumpList) {
		int inputNumber = -1;
		int maxNumber = trumpList.size();
		while(true) {
			inputNumber = IOUtil.inputNumber();
			if (inputNumber > 0 && inputNumber <= maxNumber) {
				break;
			} else {
				printLog("入力範囲は" + 1 + "〜" + maxNumber + "です");
			}
		}
		return inputNumber;
	}

	// 手札見える
	public void showTehuda() {
		StringBuilder sb = new StringBuilder();
		sb.append("手札: ");
		for (Trump trump : m_tehuda) {
			sb.append(trump.toString() + "");
		}
		printLog(sb.toString());
	}
	public void showTehudaWithSort() {
		Collections.sort(m_tehuda, new TrumpComparator());
		showTehuda();
	}

	// 手札見えない
	public void showHiddenTehai() {
		StringBuilder sb = new StringBuilder();
	    sb.append("手札: ");
		for (int i = 1; i <= m_tehuda.size(); i++) {
			sb.append("[" + i + "]");
		}
		printLog(sb.toString());
	}

	public boolean isEmptyTehuda() {
		return m_tehuda.size() == 0;
	}

	public void printLog(String log) {
		LogUtil.printLog(m_name, log);
	}

	@Override
	public String toString() {
		return m_name;
	}

	/**
	 * noを取得します。
	 * @return no
	 */
	public int getNo() {
	    return m_no;
	}

	/**
	 * noを設定します。
	 * @param no no
	 */
	public void setNo(int no) {
		m_no = no;
	}

	/**
	 * nameを取得します。
	 * @return name
	 */
	public String getName() {
	    return m_name;
	}

	/**
	 * nameを設定します。
	 * @param name name
	 */
	public void setName(String name) {
		if (name == null || name.equals("") == true) {
			String type = m_CPUFlag ? "CPU" : "player";
			m_name = type + m_no;
		}
		m_name = name;
	}

	/**
	 * isCPUを取得します。
	 * @return isCPU
	 */
	public boolean isCPUFlag() {
	    return m_CPUFlag;
	}

	/**
	 * isCPUを設定します。
	 * @param isCPU isCPU
	 */
	public void setCPU(boolean isCPU) {
		m_CPUFlag = isCPU;
	}

	/**
	 * tehudaを取得します。
	 * @return tehuda
	 */
	public List<Trump> getTehuda() {
	    return m_tehuda;
	}

	/**
	 * tehudaを設定します。
	 * @param tehuda tehuda
	 */
	public void setTehuda(List<Trump> tehuda) {
		m_tehuda = tehuda;
	}

	/**
	 * tehudaを追加します。
	 * @param tehuda tehuda
	 */
	public void addTehuda(Trump trump) {
		m_tehuda.add(trump);
	    Collections.shuffle(m_tehuda);
	}

	/**
	 * tehudaを追加します。
	 * @param tehuda tehuda
	 */
	public Trump removeTehuda(int index) {
		return m_tehuda.remove(index);
	}

	/**
	 * m_isWinを取得します。
	 * @return m_isWin
	 */
	public boolean isWinFlag() {
	    return m_winFlag;
	}

	/**
	 * m_isWinを設定します。
	 * @param m_isWin m_isWin
	 */
	public void setWinFlag(boolean isWin) {
		m_winFlag = isWin;
	}
}
