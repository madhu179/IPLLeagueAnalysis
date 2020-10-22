package IPLLeagueAnalysis;

import com.opencsv.bean.CsvBindByName;

public class Bowler {
	
	@CsvBindByName(column = "POS", required = true)
	public int pos;
	@CsvBindByName(column = "PLAYER", required = true)
	public String player;
	@CsvBindByName(column = "Mat", required = true)
	public int matches;
	@CsvBindByName(column = "Inns", required = true)
	public int innings;
	@CsvBindByName(column = "Ov", required = true)
	public double Overs;
	@CsvBindByName(column = "Runs", required = true)
	public int runs;
	@CsvBindByName(column = "Wkts", required = true)
	public int wickets;
	@CsvBindByName(column = "BBI", required = true)
	public int bbi;
	@CsvBindByName(column = "Avg", required = true)
	public String average;
	@CsvBindByName(column = "Econ", required = true)
	public double economy;
	@CsvBindByName(column = "SR", required = true)
	public String strikeRate;
	@CsvBindByName(column = "4w", required = true)
	public int fourWickets;
	@CsvBindByName(column = "5w", required = true)
	public int fiveWickets;
	
	public Double getAverage()
	{
		if(!average.equals("-"))
		return Double.parseDouble(average);
		return Double.MAX_VALUE;
	}
	
	public Double getStrikeRate()
	{
		if(!strikeRate.equals("-"))
		return Double.parseDouble(strikeRate);
		return Double.MAX_VALUE;
	}

}
