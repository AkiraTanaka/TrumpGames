package wing.dev.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import wing.dev.common.IAttribute.TrumpNo;
import wing.dev.common.IAttribute.TrumpType;
import wing.dev.common.control.PlayerComparator;
import wing.dev.common.model.CPU;
import wing.dev.common.model.Player;
import wing.dev.common.model.Trump;
import wing.dev.common.util.LogUtil;

/**
 * フィールドクラス
 */
public abstract class Field {
	protected String m_gameName = "";
	protected boolean m_testModeFlag = false;
	protected List<Player> m_players = new ArrayList<>();
	protected List<Player> m_winPlayers = new ArrayList<>();
	protected List<Trump> m_yamahuda = new ArrayList<>();
	protected List<Trump> m_sutehuda = new ArrayList<>();

	// abstract
	/** ゲームを初期化 */
	public abstract void init(List<String> playerNames, List<String> CPUNames);
	/** ゲーム開始時処理 */
	public abstract void startGame();
	/**
	 * プレイヤー勝利判定
	 * @param player 判定するプレイヤー
	 */
	public abstract void judgePlayerWin(Player player);
	/** ゲーム終了判定 */
	public abstract boolean judgeGameEnd();
	/** ゲーム終了時処理 */
	public abstract void endGame();

	/**
	 * ゲームを開始
	 */
	public void runGame(List<String> playerNames, List<String> CPUNames) {
		runGame(playerNames, CPUNames, false);
	}
	public void runGame(List<String> playerNames, List<String> CPUNames, boolean isTestMode) {
		m_testModeFlag = isTestMode;
		init(playerNames, CPUNames);
		startGame();
		endGame();
	}

	// protected
	/**
	 * フィールドを初期化
	 * @param playerNames プレイヤーの名前リスト
	 * @param CPUNames CPUの名前リスト
	 * @param maxJoker Jokerの数
	 */
	protected void initField(List<String> playerNames, List<String> CPUNames,int maxJoker) {
		m_players = new ArrayList<>();
		int no = 0;
		// player追加
		for (int i = 0; i < playerNames.size(); i++, no++) {
			String name = playerNames.get(i);
			m_players.add(new Player(no, name, false, m_testModeFlag));
		}
		// CPU追加
		for (int i = 0; i < CPUNames.size(); i++, no++) {
			String name = CPUNames.get(i);
			m_players.add(new CPU(no, name, m_testModeFlag));
		}
		m_yamahuda = initYamahuda(maxJoker);
	}

	/**
	 * 山札を初期化
	 * @param maxJoker Jokerの数
	 * @return 初期化した山札
	 */
	private List<Trump> initYamahuda(int maxJoker) {
		List<Trump> yama = new ArrayList<>();
		for (TrumpType type : TrumpType.values()) {
			if (type != TrumpType.OTHER) {
				for (TrumpNo no : TrumpNo.values()) {
					if (no != TrumpNo.JOKER) {
						yama.add(new Trump(type, no));
					}
				}
			}
		}
		// Add JOKER
		for (int i = 0; i < maxJoker; i++) {
			yama.add(new Trump(TrumpType.OTHER, TrumpNo.JOKER));
		}
		Collections.shuffle(yama);
		return yama;
	}

	/**
	 * すべてのトランプを引く
	 */
	public void drawAllTrump() {
		while(true) {
			for (Player player : m_players) {
				if (m_yamahuda.size() == 0) {
					return;
				}
				drawTrumpByYamahuda(player);
			}
		}
	}

	/**
	 * 山札から最初に各プレイヤーに配る
	 * @param maxInitTrump 最初に配るトランプの数
	 */
	public void initDrawTehuda(int maxInitTrump) {
		for (int i = 0; i < maxInitTrump; i++) {
			for (Player player : m_players) {
				drawTrumpByYamahuda(player);
			}
		}
	}

	/**
	 * 山札からトランプを取得
	 * @param player 取得するプレイヤー
	 */
	public void drawTrumpByYamahuda(Player player) {
		player.addTehuda(m_yamahuda.remove(0));
	}

	/**
	 * 現在のプレイヤーの次のプレイヤーを取得
	 * @param currentPlayer 現座のプレイヤー
	 * @return 次のプレイヤー
	 */
	public Player getNextPlayer(Player currentPlayer) {
		Player nextPlayer = null;
		Collections.sort(m_players, new PlayerComparator());
		int playersCnt = m_players.size();
		if (playersCnt > 1) {
			for (int i = 0; i < playersCnt; i++) {
				Player player = m_players.get(i);
				if (player.isWinFlag() == false && currentPlayer.getNo() == player.getNo()) {
					// playerの数が今のリスト番号より大きかったら次
					if (playersCnt > i + 1) {
						nextPlayer = m_players.get(i+1);
					} else {
						nextPlayer = m_players.get(0);
					}
					break;
				}
			}
		}
		return nextPlayer;
	}

	/**
	 * ゲーム名を取得
	 * @return ゲーム名
	 */
	public String getGameName() {
		return m_gameName;
	}

	/**
	 * ログ出力
	 * @param log
	 */
	protected void printLog(String log) {
		LogUtil.printLog("Field", log);
	}
}
