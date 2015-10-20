package wing.dev.trumpGame;

import java.util.ArrayList;
import java.util.List;

import wing.dev.common.util.IOUtil;
import wing.dev.common.util.LogUtil;
import wing.dev.trumpGame.games.BabaNuki;
import wing.dev.trumpGame.games.JijiNuki;


/**
 * ゲームを実行するクラス
 */
public class GameMaster {
	private static final List<BaseTrumpGame> m_trumpGames = new ArrayList<>();
	static {
		m_trumpGames.add(new BabaNuki());
		m_trumpGames.add(new JijiNuki());
	}

	public static void main(String[] args) {
		BaseTrumpGame selectedTrumpGame = null;
		printLog("ゲームを選択してください。");
		for (int i = 0; i < m_trumpGames.size(); i++) {
			BaseTrumpGame trumpGame = m_trumpGames.get(i);
			printLog((i+1) + " : " + trumpGame.getClass().getSimpleName());

		}
		int selectedNumber = IOUtil.inputNumberUnderMax(m_trumpGames.size());
		selectedTrumpGame = m_trumpGames.get(selectedNumber - 1);
		if (selectedTrumpGame != null) {
			printLog(selectedTrumpGame.getGameName() + "を開始します。");

			List<String> playerNames = new ArrayList<>();
			playerNames.add("player1");
			List<String> CPUNames = new ArrayList<>();
			CPUNames.add("CPU1");
			CPUNames.add("CPU2");
			CPUNames.add("CPU3");
			selectedTrumpGame.runGame(playerNames, CPUNames, true);
		}
	}

	/**
	 * ログ出力
	 * @param log
	 */
	private static void printLog(String log) {
		LogUtil.printLog(GameMaster.class.getSimpleName() + " ", log);
	}

}
