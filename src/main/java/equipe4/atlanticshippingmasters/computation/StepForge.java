package equipe4.atlanticshippingmasters.computation;

import java.util.ArrayList;

import equipe4.atlanticshippingmasters.model.Port;
import equipe4.atlanticshippingmasters.model.Step;

public class StepForge {
	private int distance;
	private Port departurePort;
	private Port arrivalPort;

	// Je créé une class nested coordinates qui stocke les lagitudes et longitude en
	// degrés avec conversion des minutes et des secondes d'angle
	private class Coordinates {
		private double latitude;
		private double longitude;

		// Constructeur Coordinates
		public Coordinates(String coordinates) {
			ArrayList<Double> coorArray = parse(coordinates);
			// Conversion des minutes et secondes d'angle en degrés. Puis stockage dans
			// l'objet coordonnées.
			this.latitude = coorArray.get(0) + coorArray.get(1) / 60 + coorArray.get(2) / 3600;
			this.longitude = coorArray.get(3) + coorArray.get(4) / 60 + coorArray.get(5) / 3600;
		}
		// Convertisseur des coordonnées en un tableau d'entiers pour le constructeur
		// coordinates ArrayList<Integer>
		private ArrayList<Double> parse(String coordinatesString) {
			// On a pas le droit de faire """ donc "\"".
			String[] cooArr = coordinatesString.replace("\"", "").replace(" ", "").split("[^0-9.\s*]");
			// Conversion de l'array de String en array de double pour pouvoir fare des
			// opérations mathématiques.
			ArrayList<Double> convertToDouble = new ArrayList<>();
			for (String n : cooArr) {
				convertToDouble.add(Double.valueOf(n));
			}
			return convertToDouble;
		}
	}

	
	// Le constructeur
	public StepForge(Port departure, Port arrival) {
		this.departurePort = departure;
		this.arrivalPort = arrival;
		this.distance = computeDistance();
	}

	// La fonction calcule la distance en fonction des deux champs coordonnées de
	// l'instance
	private int computeDistance() {
		
		Coordinates departure = new Coordinates(departurePort.getCoordinates());
		Coordinates arrival = new Coordinates(arrivalPort.getCoordinates());
		
		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(arrival.latitude - departure.latitude);
		double lonDistance = Math.toRadians(arrival.longitude - departure.longitude);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(departure.latitude))
				* Math.cos(Math.toRadians(arrival.latitude)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		return (int) distance / 1000;
	}
	
	public Step buildStep(int journeyId, int order) {
		Step step = new Step();
		step.setJourneyOrder(order);
		step.setIdJourneyFk(journeyId);
		step.setIdPortDeparture(this.getDeparturePort());
		step.setIdPortArrival(this.getArrivalPort());
		step.setDistance(this.getDistance());
		return step;
	}
	
	// Le getter pour distance
		public int getDistance() {
			return distance;
		}
		
		public Port getDeparturePort() {
			return departurePort;
		}

		public Port getArrivalPort() {
			return arrivalPort;
		}
	
}
