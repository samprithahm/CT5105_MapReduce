package lsda;

public class Measurement 
{
	//Setting up class attributes
    private int time;
    private double temperature;

    //Setting up constructor for the MeasurementQ1 object
    public Measurement(int time, double temperature){
        this.time = time;
        this.temperature=temperature;
    }

    //Setting up the setters and getters for the attributes of the object MeasurementQ1
    public void setTime(int time){
        this.time=time;
    }

    public int getTime(){
        return time;        
    }

    public void setTemperature(double temperature){
        this.temperature = temperature;
    }

    public double getTemperature(){
        return temperature;
    }
    @Override
    public String toString() {
        return this.getTime() + " " + this.getTemperature();
    }
}