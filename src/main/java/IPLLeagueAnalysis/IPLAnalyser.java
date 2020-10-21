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
		if (playerRunsList == null || playerRunsList.size() == 0) {
            throw new IPLAnalyserException("No Census Data",IPLAnalyserException.Exception.NO_CENSUS_DATA);
        }
		double max = playerRunsList.stream().filter(s->!s.average.equals("-")).map(s->Double.parseDouble(s.average)).max(Double::compare).get();
		List<PlayerRuns> player = playerRunsList.stream().filter(s->s.average.equals(Double.toString(max))).collect(Collectors.toList());
		return player.get(0).player;		
	}
	

}
