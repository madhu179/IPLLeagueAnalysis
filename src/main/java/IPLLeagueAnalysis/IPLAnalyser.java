package IPLLeagueAnalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.capgemini.csvbuilder.BuilderException;
import com.capgemini.csvbuilder.BuilderFactory;
import com.capgemini.csvbuilder.ICSVBuilder;

public class IPLAnalyser {
	
	List<PlayerRuns> playerRunsList = null;

	public void loadRunsData(String filePath) throws IPLAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			new BuilderFactory();
			ICSVBuilder csvBuilderCustom = BuilderFactory.createBuilder();

			playerRunsList = csvBuilderCustom.getCSVFileList(reader,PlayerRuns.class);	
           
		} catch (IOException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.Exception.INCORRECT_FILE);
		}catch (BuilderException e) {
			throw new IPLAnalyserException(e.getMessage(),e.type.name());
		}

	}
	
	public String getTopBattingAvg() throws IPLAnalyserException {
		checkForData();
		Comparator<PlayerRuns> censusComparator = Comparator.comparing(PlayerRuns::getAverage);
        this.sortStateData(censusComparator);
        Collections.reverse(playerRunsList);		
        return playerRunsList.get(0).player;
	}

	public String getTopStrikeRate() throws IPLAnalyserException {
		if (playerRunsList == null || playerRunsList.size() == 0) {
            throw new IPLAnalyserException("No Census Data",IPLAnalyserException.Exception.NO_CENSUS_DATA);
        }
		double maxStrikeRate = playerRunsList.stream().map(s->s.strikeRate).max(Double::compare).get();
		List<PlayerRuns> player = playerRunsList.stream().filter(s->s.strikeRate==maxStrikeRate).collect(Collectors.toList());
		return player.get(0).player;
	}

	public String getMaximum6sAnd4s() throws IPLAnalyserException {
		if (playerRunsList == null || playerRunsList.size() == 0) {
            throw new IPLAnalyserException("No Census Data",IPLAnalyserException.Exception.NO_CENSUS_DATA);
        }
		int maxSixesAndFours = playerRunsList.stream().map(s->s.sixes+s.fours).max(Integer::compare).get();
		List<PlayerRuns> player = playerRunsList.stream().filter(s->s.sixes+s.fours==maxSixesAndFours).collect(Collectors.toList());
		return player.get(0).player;
	}
	
	public void checkForData() throws IPLAnalyserException
	{
		if (playerRunsList == null || playerRunsList.size() == 0) {
            throw new IPLAnalyserException("No Census Data",IPLAnalyserException.Exception.NO_CENSUS_DATA);
        }
	}
	
	private void sortStateData(Comparator<PlayerRuns> comparator)
	{
		 for (int i = 0; i < playerRunsList.size() - 1; i++) {
	            for (int j = 0; j < playerRunsList.size() - i - 1; j++) {
	            	PlayerRuns census1 = playerRunsList.get(j);
	            	PlayerRuns census2 = playerRunsList.get(j + 1);
	                if (comparator.compare(census1, census2) > 0) {
	                	playerRunsList.set(j, census2);
	                	playerRunsList.set(j + 1, census1);
	                }
	            }
	        }
	}
	

}
