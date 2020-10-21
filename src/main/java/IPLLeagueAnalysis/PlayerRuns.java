package IPLLeagueAnalysis;

import com.opencsv.bean.CsvBindByName;

public class PlayerRuns {
	
	
	@CsvBindByName(column = "POS", required = true)
	public int pos;
	@CsvBindByName(column = "PLAYER", required = true)
	public String player;
	@CsvBindByName(column = "Mat", required = true)
	public int matches;
	@CsvBindByName(column = "Inns", required = true)
	public int innings;
	@CsvBindByName(column = "NO", required = true)
	public int notOut;
	@CsvBindByName(column = "Runs", required = true)
	public int runs;
	@CsvBindByName(column = "HS", required = true)
	public String highestScore;
	@CsvBindByName(column = "Avg", required = true)
	public String average;
	@CsvBindByName(column = "BF", required = true)
	public int ballsFaced;
	@CsvBindByName(column = "SR", required = true)
	public double strikeRate;
	@CsvBindByName(column = "100", required = true)
	public int hundreds;
	@CsvBindByName(column = "50", required = true)
	public int fifties;
	@CsvBindByName(column = "4s", required = true)
	public int fours;
	@CsvBindByName(column = "6s", required = true)
	public int sixes;
	
}
