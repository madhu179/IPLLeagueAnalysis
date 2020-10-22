package IPLLeagueAnalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.capgemini.csvbuilder.BuilderException;
import com.capgemini.csvbuilder.BuilderFactory;
import com.capgemini.csvbuilder.ICSVBuilder;

public class IPLAnalyser {
	
	private List<Batsman> batsmanDataList = null;
	private Comparator<Batsman> batsmanComparator;

	public void loadRunsData(String filePath) throws IPLAnalyserException {
		try(Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			
			ICSVBuilder csvBuilderCustom = BuilderFactory.createBuilder();
			batsmanDataList = csvBuilderCustom.getCSVFileList(reader,Batsman.class);	
           
		} catch (IOException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.Exception.INCORRECT_FILE);
		}catch (BuilderException e) {
			throw new IPLAnalyserException(e.getMessage(),e.type.name());
		}

	}
	
	public String getTopBattingAvg() throws IPLAnalyserException {
		checkForData();
		batsmanComparator = Comparator.comparing(Batsman::getAverage);
		return getBatsmanName();
	}

	public String getTopStrikeRate() throws IPLAnalyserException {
		checkForData();
		batsmanComparator = Comparator.comparing(s->s.strikeRate);
		return getBatsmanName();
	}

	public String getMaximum6sAnd4s() throws IPLAnalyserException {
		checkForData();
		batsmanComparator = Comparator.comparing(s->s.sixes+s.fours);
		return getBatsmanName();
	}
	
	public String getBestStrickRateMaximum6sAnd4s() throws IPLAnalyserException {
		checkForData();
		batsmanComparator = Comparator.comparing(s->s.sixes+s.fours);
		batsmanComparator = batsmanComparator.thenComparing(s->s.strikeRate);
        return getBatsmanName();
	}
	
	public String getGreatAvgwithBestStrickRate() throws IPLAnalyserException{
		checkForData();
		batsmanComparator = Comparator.comparing(Batsman::getAverage).thenComparing(s->s.strikeRate);
        return getBatsmanName();
	}
	
	public String getMaxRunsWithBestAvg() throws IPLAnalyserException{
		checkForData();
		batsmanComparator = Comparator.comparing(s->s.runs);
		batsmanComparator = batsmanComparator.thenComparing(Batsman::getAverage);
        return getBatsmanName();
	}
	
	private String getBatsmanName() {
		this.sortBatsmenData(batsmanComparator);
        Collections.reverse(batsmanDataList);		
        return batsmanDataList.get(0).player;
	}
	
	private void checkForData() throws IPLAnalyserException
	{
		if (batsmanDataList == null || batsmanDataList.size() == 0) {
            throw new IPLAnalyserException("No Census Data",IPLAnalyserException.Exception.NO_CENSUS_DATA);
        }
	}
	
	private void sortBatsmenData(Comparator<Batsman> comparator)
	{
		 for (int i = 0; i < batsmanDataList.size() - 1; i++) {
	            for (int j = 0; j < batsmanDataList.size() - i - 1; j++) {
	            	Batsman census1 = batsmanDataList.get(j);
	            	Batsman census2 = batsmanDataList.get(j + 1);
	                if (comparator.compare(census1, census2) > 0) {
	                	batsmanDataList.set(j, census2);
	                	batsmanDataList.set(j + 1, census1);
	                }
	            }
	        }
	}

}
