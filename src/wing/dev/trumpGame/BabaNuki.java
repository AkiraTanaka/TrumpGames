package wing.dev.trumpGame;

import java.util.ArrayList;
import java.util.List;

import wing.dev.common.Field;
import wing.dev.common.model.Player;
import wing.dev.common.model.Trump;
import wing.dev.common.util.LogUtil;


public class BabaNuki extends Field{

	public static void main(String[] args) {
		BabaNuki babaNuki = new BabaNuki();
//		babaNuki.isTestMode = true; // test用
		babaNuki.runGame();
	}

	public void init() {
		List<String> playerNames = new ArrayList<>();
		playerNames.add("player1");
		List<String> CPUNames = new ArrayList<>();
		CPUNames.add("CPU1");
		CPUNames.add("CPU2");
		CPUNames.add("CPU3");

		initField(playerNames, CPUNames, 1);
		drowAllCard();
		printLog("トランプを配ります");
		for (Player player : m_players) {
			player.showTehuda();
		}
		printLog("同じ数字のトランプを捨てます");
		for (Player player : m_players) {
			player.deleteDuplication();
		}
		printLog("ゲームを開始します");
	}

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
					drowCardByNextPlayer(player);
					// 手札から重複するトランプを捨てる
					player.deleteDuplication();
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

	public boolean drowCardByNextPlayer(Player player) {
		Player nextPlayer = getNextPlayer(player);
		if (nextPlayer == null) {
			return false;
		}
		player.showTehudaWithSort();
		printLog(nextPlayer.getName() + "から１枚引きます");
		nextPlayer.showTehuda();

		printLog("引きたいトランプの数字を入力してください");
		int selectIndex = player.selectTrump(nextPlayer.getTehuda());
		Trump drowedTrump = nextPlayer.removeTehuda(selectIndex - 1);
//		printLog(drowedTrump + "を引きました");
		judgePlayerWin(nextPlayer);
		player.addTehuda(drowedTrump);
		player.showTehuda();
		return true;
	}

	private void printNow() {
		printLog("<現在の状況>");
		StringBuilder sb = new StringBuilder();
		for (Player player : m_players) {
			sb.append(player.getName() + ":" + player.getTehuda().size() + "枚, ");
		}
		printLog(sb.toString());
	}

	@Override
	public void judgePlayerWin(Player player) {
		if (player.isEmptyTehuda() == true) {
			player.setWinFlag(true);
			printLog(player.getName() + "の手札がなくなったので、ゲームから抜けました!");
		}
	}

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

	@Override
	public void endGame() {
		LogUtil.printLog();
		printLog("ゲーム終了");
		printLog(m_players.get(0).getName() + "が負けました");
	}
}
