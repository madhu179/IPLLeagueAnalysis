package IPLLeagueAnalysis;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class IPLAnalyserTest {
	
	private final String PLAYER_RUNS_DATA = "C:\\Users\\MADHUBABU\\eclipse-workspace\\IPLLeagueAnalysis\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	
	IPLAnalyser iplAnalyser;
	
	@Before
	void createIplAnalyserObject()
	{
		iplAnalyser = new IPLAnalyser();
	}
	
	@Test
	public void givenCsvDataShouldReturnTopBattingAvg()
	{
		iplAnalyser.loadRunsData(PLAYER_RUNS_DATA);
		double topAvg = iplAnalyser.getTopBattingAvg();
		assertEquals(83.2, topAvg,0.0);
	}

}
