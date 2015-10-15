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

public abstract class Field {
	protected boolean m_testModeFlag = false;
	protected List<Player> m_players = new ArrayList<>();
	protected List<Player> m_winPlayers = new ArrayList<>();
	protected List<Trump> m_yamahuda = new ArrayList<>();
	protected List<Trump> m_sutehuda = new ArrayList<>();

	// abstract
	public abstract void init();
	public abstract void startGame();
	public abstract void judgePlayerWin(Player player);
	public abstract boolean judgeGameEnd();
	public abstract void endGame();

	public void runGame() {
		init();
		startGame();
		endGame();
	}

	// protected
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

	public void drowAllCard() {
		while(true) {
			for (Player player : m_players) {
				if (m_yamahuda.size() == 0) {
					return;
				}
				drowCardByYamahuda(player);
			}
		}
	}

	public void initDrowTehuda(int maxInitCard) {
		for (int i = 0; i < maxInitCard; i++) {
			for (Player player : m_players) {
				drowCardByYamahuda(player);
			}
		}
	}

	public void drowCardByYamahuda(Player player) {
		player.addTehuda(m_yamahuda.remove(0));
	}

//	private Player m_currentPlayer = null;

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

	protected void printLog(String log) {
		LogUtil.printLog("Field", log);
	}
}
