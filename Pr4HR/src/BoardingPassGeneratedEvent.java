import java.time.LocalDateTime;
import java.util.UUID;


public class BoardingPassGeneratedEvent implements DomainEvent {
    private final UUID eventId;
    private final LocalDateTime occurredOn;
    private final String passengerId;
    private final String boardingPassNumber;
    private final String flightNumber;
    private final String seatNumber;
    private final String gate;
    private final LocalDateTime boardingTime;

    public BoardingPassGeneratedEvent(String passengerId, String boardingPassNumber,
                                      String flightNumber, String seatNumber,
                                      String gate, LocalDateTime boardingTime) {
        this.eventId = UUID.randomUUID();
        this.occurredOn = LocalDateTime.now();
        this.passengerId = passengerId;
        this.boardingPassNumber = boardingPassNumber;
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.gate = gate;
        this.boardingTime = boardingTime;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String getEventType() {
        return "BoardingPassGenerated";
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getBoardingPassNumber() {
        return boardingPassNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getGate() {
        return gate;
    }

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    @Override
    public String toString() {
        return String.format("BoardingPassGeneratedEvent[eventId=%s, passenger=%s, boardingPass=%s, flight=%s, seat=%s, gate=%s]",
                eventId, passengerId, boardingPassNumber, flightNumber, seatNumber, gate);
    }
}