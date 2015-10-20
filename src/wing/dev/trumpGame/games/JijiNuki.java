package wing.dev.trumpGame.games;

import java.util.List;

import wing.dev.common.model.Player;
import wing.dev.common.model.Trump;
import wing.dev.common.util.LogUtil;
import wing.dev.trumpGame.BaseTrumpGame;

/**
 * ジジぬきゲームクラス
 */
public class JijiNuki extends BaseTrumpGame {

	/**
	 * コンストラクタ
	 */
	public JijiNuki() {
		super("ジジぬき");
	}
	public JijiNuki(String gameName) {
		super(gameName);
	}

	/**
	 * プレイヤーと山札を設定
	 * @param playerNames プレイヤーたち
	 * @param CPUNames CPUたち
	 */
	protected void settingYamahuda(List<String> playerNames, List<String> CPUNames) {
		// JOKER2枚入りでその後、1枚トランプを捨てる
		initField(playerNames, CPUNames, 2);
		Trump removedTrump = m_yamahuda.remove(0);
		if (m_testModeFlag == true) {
			printLog(removedTrump + "を最初に抜きました");
		}
	}

	/** ゲームを初期化 */
	@Override
	public void init(List<String> playerNames, List<String> CPUNames) {
		settingYamahuda(playerNames, CPUNames);
		drawAllTrump();
		printLog("トランプを配ります");
		for (Player player : m_players) {
			player.showTehuda();
		}
		printLog("同じ数字のトランプを捨てます");
		for (Player player : m_players) {
			player.removeDuplication();
		}
		printLog("ゲームを開始します");
	}

	/** ゲーム開始時処理 */
	@Override
	public void startGame() {
		int cnt = 1;
		while (true) {
			printLog(cnt + "巡目");
			printNow();
			for (int i = 0; i < m_players.size(); i++) {
				Player player = m_players.get(i);
				if (player.isWinFlag() == false) {
					printLog("【" + player.getName() + "のターン】");
					// 隣の人から手札を引く
					drawTrumpByNextPlayer(player);
					// 手札から重複するトランプを捨てる
					player.removeDuplication();
					// 手札がなくなった人を判定
					judgePlayerWin(player);
					LogUtil.printLog();
				}
				if (judgeGameEnd() == true) {
					break;
				}
			}
			for (int i = 0; i < m_players.size(); i++) {
				Player player = m_players.get(i);
				if (player.isWinFlag() == true) {
					m_players.remove(player);
					m_winPlayers.add(player);
				}
			}
			if (judgeGameEnd() == true) {
				break;
			}
			cnt++;
		}
	}

	/**
	 * 次のプレイヤーからトランプを取得
	 * @param player 現在のプレイヤー
	 * @return トランプを引けたかどうか
	 */
	public boolean drawTrumpByNextPlayer(Player player) {
		Player nextPlayer = getNextPlayer(player);
		if (nextPlayer == null) {
			return false;
		}
		player.showTehudaWithSort();
		printLog(nextPlayer.getName() + "から１枚引きます");
		nextPlayer.showTehuda();

		printLog("引きたいトランプの数字を入力してください");
		int selectIndex = player.selectTrumpNumber(nextPlayer.getTehuda().size());
		Trump drawnTrump = nextPlayer.removeTehuda(selectIndex - 1);
//		printLog(drawnTrump + "を引きました");
		judgePlayerWin(nextPlayer);
		player.addTehuda(drawnTrump);
		player.showTehuda();
		return true;
	}

	/**
	 * 現在の状況を表示
	 */
	private void printNow() {
		printLog("<現在の状況>");
		StringBuilder sb = new StringBuilder();
		for (Player player : m_players) {
			sb.append(player.getName() + ":" + player.getTehuda().size() + "枚, ");
		}
		printLog(sb.toString());
	}

	/**
	 * プレイヤー勝利判定
	 * @param player 判定するプレイヤー
	 */
	@Override
	public void judgePlayerWin(Player player) {
		if (player.isEmptyTehuda() == true) {
			player.setWinFlag(true);
			printLog(player.getName() + "の手札がなくなったので、ゲームから抜けました!");
		}
	}

	/** ゲーム終了判定 */
	@Override
	public boolean judgeGameEnd() {
		int notWinPlayerCnt = 0;
		for (Player player : m_players) {
			if (player.isWinFlag() == false) {
				notWinPlayerCnt++;
			}
		}
		if (notWinPlayerCnt == 1) {
			return true;
		}
		return false;
	}

	/** ゲーム終了時処理 */
	@Override
	public void endGame() {
		LogUtil.printLog();
		printLog("ゲーム終了");
		printLog(m_players.get(0).getName() + "が負けました");
	}
}
