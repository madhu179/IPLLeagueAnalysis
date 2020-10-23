package IPLLeagueAnalysis;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class IPLAnalyserTest {

	private final String PLAYER_RUNS_DATA = "C:\\Users\\MADHUBABU\\eclipse-workspace\\IPLLeagueAnalysis\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private final String PLAYER_WKTS_DATA = "C:\\Users\\MADHUBABU\\eclipse-workspace\\IPLLeagueAnalysis\\WP DP Data_02 IPL2019FactsheetMostWkts.csv";
	
	IPLAnalyser iplAnalyser = null;

	@Before
	public void createIplAnalyserObject() {
		iplAnalyser = new IPLAnalyser();
	}

	@Test
	public void givenCsvDataShouldReturnTopBattingAvg() {
		try {
			iplAnalyser.loadRunsData(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.getTopBattingAvg();
			assertEquals("MS Dhoni", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenCsvDataShouldReturnTopStrikeRatePlayer() {
		try {
			iplAnalyser.loadRunsData(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.getTopStrikeRate();
			assertEquals("Ishant Sharma", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenCsvDataShouldReturnPlayerwithMax6sand4s() {
		try {
			iplAnalyser.loadRunsData(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.getMaximum6sAnd4s();
			assertEquals("Andre Russell", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenCsvDataShouldReturnPlayerwithBestStrikeRateWithMax6sand4s() {
		try {
			iplAnalyser.loadRunsData(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.getBestStrickRateMaximum6sAnd4s();
			assertEquals("Andre Russell", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenCsvDataShouldReturnPlayewithGreatAvgrwithBestStrikeRate() {
		try {
			iplAnalyser.loadRunsData(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.getGreatAvgwithBestStrickRate();
			assertEquals("MS Dhoni", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenCsvDataShouldReturnPlayewithMaxRunsrWithBestAvg() {
		try {
			iplAnalyser.loadRunsData(PLAYER_RUNS_DATA);
			String playerName = iplAnalyser.getMaxRunsWithBestAvg();
			assertEquals("David Warner", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenWktsCsvDataShouldReturnTopBowlingAvg() {
		try {
			iplAnalyser.loadWktsData(PLAYER_WKTS_DATA);
			String playerName = iplAnalyser.getTopBowlingAvg();
			assertEquals("Anukul Roy", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenWktsCsvDataShouldReturnTopStrikeRate() {
		try {
			iplAnalyser.loadWktsData(PLAYER_WKTS_DATA);
			String playerName = iplAnalyser.getTopBowlingStrakeRate();
			assertEquals("Alzarri Joseph", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenWktsCsvDataShouldReturnBestEconomy() {
		try {
			iplAnalyser.loadWktsData(PLAYER_WKTS_DATA);
			String playerName = iplAnalyser.getBestEconomy();
			assertEquals("Shivam Dube", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenWktsCsvDataShouldReturnBestStrikeRateWith4W5W() {
		try {
			iplAnalyser.loadWktsData(PLAYER_WKTS_DATA);
			String playerName = iplAnalyser.getBestStrikeRateWith4w5w();
			assertEquals("Kagiso Rabada", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenWktsCsvDataShouldReturnGreatAvgWithBestStrikeRate() {
		try {
			iplAnalyser.loadWktsData(PLAYER_WKTS_DATA);
			String playerName = iplAnalyser.getGreatAvgWithBestStrikeRate();
			assertEquals("Anukul Roy", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenWktsCsvDataShouldReturnMaxWktsWithBestBowlingAvg() {
		try {
			iplAnalyser.loadWktsData(PLAYER_WKTS_DATA);
			String playerName = iplAnalyser.getMaxWktsWithBestAvg();
			assertEquals("Kagiso Rabada", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenWktsCsvDataShouldReturnBestBattingAvgBestBowlingAvg() {
		try {
			iplAnalyser.loadRunsData(PLAYER_RUNS_DATA);
			iplAnalyser.loadWktsData(PLAYER_WKTS_DATA);
			String playerName = iplAnalyser.getBestBattingAvgBestBowlingAvg();
			assertEquals("Andre Russell", playerName);
		} catch (IPLAnalyserException e) {
			e.printStackTrace();
		}
	}

}
