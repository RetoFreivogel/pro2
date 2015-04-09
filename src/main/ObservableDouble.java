package main;

import java.util.Observable;

public class ObservableDouble extends Observable {
	private double value;
	
	public ObservableDouble(double value){
		this.value = value;
	}
	
	public ObservableDouble(ObservableDouble other){
		this.value = other.getValue();
	}
	
	public void setValue(double value){
		this.value = value;
		setChanged();
		notifyObservers();
	}
	
	public double getValue(){
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObservableDouble other = (ObservableDouble) obj;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}
}
