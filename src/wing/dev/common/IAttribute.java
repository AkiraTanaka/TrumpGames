package wing.dev.common;

public class IAttribute {
	public enum TrumpType {
		HEARTS(0, "♡"),
		DIAMONDS(1, "♢"),
		CLUBS(2, "♣︎"),
		SPADES(3, "♠︎"),
		OTHER(99, "");

		TrumpType(int no, String name){
			this.no = no;
			this.name= name;
		}

		private int no;
		private String name;
		public int getNo(){
			return this.no;
		}
		public String getName(){
			return this.name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}
	public enum TrumpNo {
		ONE(1, "A"),
		TWO(2, "2"),
		THREE(3, "3"),
		FOUR(4, "4"),
		FIVE(5, "5"),
		SIX(6, "6"),
		SEVEN(7, "7"),
		EIGHT(8, "8"),
		NINE(9, "9"),
		TEN(10, "10"),
		JACK(11, "J"),
		QUEEN(12, "Q"),
		KING(13, "K"),
		JOKER(99, "JOKER");

		TrumpNo(int no, String name){
			this.no = no;
			this.name= name;
		}

		private int no;
		private String name;
		public int getNo(){
			return this.no;
		}
		public String getName(){
			return this.name;
		}
		@Override
		public String toString() {
			return this.name;
		}
	}

}
