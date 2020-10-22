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
	
	private List<Bowler> bowlerDataList = null;
	private Comparator<Bowler> bowlerComparator;

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
	
	public void loadWktsData(String filePath) throws IPLAnalyserException {
		try(Reader reader = Files.newBufferedReader(Paths.get(filePath));){
			new BuilderFactory();
			ICSVBuilder csvBuilderCustom = BuilderFactory.createBuilder();

			bowlerDataList = csvBuilderCustom.getCSVFileList(reader,Bowler.class);	
           
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
	
	public String getTopBowlingAvg() throws IPLAnalyserException {
		checkForBowlerData();
		bowlerComparator = Comparator.comparing(Bowler::getAverage);
		return getBowlerName();
	}
	
	public String getTopBowlingStrakeRate() throws IPLAnalyserException {
		checkForBowlerData();
		bowlerComparator = Comparator.comparing(Bowler::getStrikeRate);
		return getBowlerName();
	}
	
	public String getBestEconomy() throws IPLAnalyserException {
		checkForBowlerData();
		bowlerComparator = Comparator.comparing(s->s.economy);
		return getBowlerName();
	}
	
	public String getBestStrikeRateWith4w5w() throws IPLAnalyserException {
		checkForBowlerData();
		bowlerComparator = Comparator.comparing(s->s.fourWickets+s.fiveWickets);
		bowlerComparator = bowlerComparator.reversed();
		bowlerComparator = bowlerComparator.thenComparing(Bowler::getStrikeRate);
		return getBowlerName();
	}
	
	public String getGreatAvgWithBestStrikeRate() throws IPLAnalyserException {
		checkForBowlerData();
		bowlerComparator = Comparator.comparing(Bowler::getAverage);
		bowlerComparator = bowlerComparator.thenComparing(Bowler::getStrikeRate);
		return getBowlerName();
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
	
	private String getBowlerName() {
		this.sortBowlerData(bowlerComparator);	
        return bowlerDataList.get(0).player;
	}
	
	private void checkForBowlerData() throws IPLAnalyserException
	{
		if (bowlerDataList == null || bowlerDataList.size() == 0) {
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
	
	private void sortBowlerData(Comparator<Bowler> comparator)
	{
		 for (int i = 0; i < bowlerDataList.size() - 1; i++) {
	            for (int j = 0; j < bowlerDataList.size() - i - 1; j++) {
	            	Bowler census1 = bowlerDataList.get(j);
	            	Bowler census2 = bowlerDataList.get(j + 1);
	                if (comparator.compare(census1, census2) > 0) {
	                	bowlerDataList.set(j, census2);
	                	bowlerDataList.set(j + 1, census1);
	                }
	            }
	        }
	}

}
