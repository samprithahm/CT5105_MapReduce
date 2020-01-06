package lsda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class WeatherStation 
{
	//Setting up class attributes
	private String city;
	private List<Measurement> measurements;
	public static List<WeatherStation> stations= new ArrayList<>();


	//Setting up constructor for the WeatherStation object
	public WeatherStation(String city, List<Measurement> measurements/*,List<String> stations*/){
		this.city = city;
		this.measurements = measurements;
	}

	//Setting up the setters and getters for the attributes of the object WeatherStation
	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;        
	}

	public void setMeasurements(List<Measurement> measurements){
		this.measurements = measurements;
	}

	public List<Measurement> getMeasurements(){
		return measurements;
	}

	//MaxTemperature function to return highest temperature of a given time range
	public void maxTemperature(int startTime, int endTime){

		//Creates a list of the Measurement object in the selected time range
		List<Measurement> tempList = this.getMeasurements().stream()
				.filter(e -> e.getTime()>=startTime && e.getTime()<=endTime)
				.collect(Collectors.toList());

		//Finding the Measurement with the higher temperature in the filtered list
		Measurement maxTemp = tempList.stream()
				.max(Comparator.comparing(Measurement::getTemperature))
				.orElseThrow(NoSuchElementException::new);

		//Display results
		System.out.println("The maximum temperature measured between "+startTime+ " and "+endTime+" is: "+maxTemp.getTemperature());
	}

	
	public static List<KeyValue> countTemperature(double t1, double t2, double r)
	{
		List<KeyValue> tempMap = new ArrayList<>();

		// Adding temperatures to a map which falls between the range t1-r to t1+r and t2-r to t2+r
		stations.stream().flatMap(x -> x.measurements.parallelStream())
		.forEach(x -> {
			if ((x.getTemperature() >= t1 - r) && (x.getTemperature() <= t1 + r)) {	tempMap.add(new KeyValue(t1, 1)); }
			if ((x.getTemperature() >= t2 - r) && (x.getTemperature() <= t2 + r)) { tempMap.add(new KeyValue(t2, 1)); }
		});

		
		Map<Double, List<Integer>> shuffle = new LinkedHashMap<>();
		
		// Grouping data as key value pairs
		tempMap.parallelStream()
		.forEach(x -> {
			List<Integer> values = shuffle.get(x.getKey());
			if(values == null) {
				shuffle.put(x.getKey(), new ArrayList<>(Arrays.asList(x.getValue())));
			}
			else {
				values.add(x.getValue());
			}
		});
		
		// Reducing by taking sum of all occurrence for each temperature in shuffle list
		List<KeyValue> result =	shuffle.entrySet().parallelStream()
				.map(x -> new KeyValue(x.getKey(), x.getValue()
						.parallelStream().mapToInt(Integer::intValue).sum()))
				.collect(Collectors.toList());

		return result;
	}
}

// Class to return values in Key Value pair
class KeyValue
{
	double key;
	int value;

	public KeyValue(double key, int value)
	{
		this.key = key;
		this.value = value;
	}

	public double getKey() {
		return key;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString()
	{
		return "("+key+","+value+")";
	}
}