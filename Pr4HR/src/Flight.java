import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Агрегат: Рейс
 * Відповідає за реєстрацію пасажирів та генерацію посадкових талонів
 */
public class Flight {
    private final String flightNumber;
    private final String departureCity;
    private final String arrivalCity;
    private final LocalDateTime departureTime;
    private final String gate;
    private final Map<String, String> seatAssignments; // passengerId -> seatNumber
    private final DomainEventPublisher eventPublisher;

    public Flight(String flightNumber, String departureCity, String arrivalCity,
                  LocalDateTime departureTime, String gate) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.gate = gate;
        this.seatAssignments = new HashMap<>();
        this.eventPublisher = DomainEventPublisher.getInstance();
    }


    public void checkInPassenger(Passenger passenger, String seatNumber) {

        if (seatAssignments.containsKey(passenger.getId())) {
            throw new IllegalStateException("Пасажир вже зареєстрований на цей рейс");
        }

        if (seatAssignments.containsValue(seatNumber)) {
            throw new IllegalStateException("Місце " + seatNumber + " вже зайняте");
        }


        seatAssignments.put(passenger.getId(), seatNumber);


        CheckInCompletedEvent checkInEvent = new CheckInCompletedEvent(
                passenger.getId(),
                flightNumber,
                seatNumber
        );
        eventPublisher.publish(checkInEvent);


        generateBoardingPass(passenger, seatNumber);
    }


    private void generateBoardingPass(Passenger passenger, String seatNumber) {
        String boardingPassNumber = "BP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        LocalDateTime boardingTime = departureTime.minusMinutes(45); // посадка за 45 хв до вильоту


        BoardingPassGeneratedEvent boardingPassEvent = new BoardingPassGeneratedEvent(
                passenger.getId(),
                boardingPassNumber,
                flightNumber,
                seatNumber,
                gate,
                boardingTime
        );
        eventPublisher.publish(boardingPassEvent);
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public String getGate() {
        return gate;
    }

    public int getCheckedInPassengersCount() {
        return seatAssignments.size();
    }

    @Override
    public String toString() {
        return String.format("Flight[%s: %s -> %s, departure=%s, gate=%s, passengers=%d]",
                flightNumber, departureCity, arrivalCity, departureTime, gate, seatAssignments.size());
    }
}