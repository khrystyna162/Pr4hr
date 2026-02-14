import java.time.LocalDateTime;
import java.util.UUID;


public class CheckInCompletedEvent implements DomainEvent {
    private final UUID eventId;
    private final LocalDateTime occurredOn;
    private final String passengerId;
    private final String flightNumber;
    private final String seatNumber;
    private final LocalDateTime checkInTime;

    public CheckInCompletedEvent(String passengerId, String flightNumber, String seatNumber) {
        this.eventId = UUID.randomUUID();
        this.occurredOn = LocalDateTime.now();
        this.passengerId = passengerId;
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.checkInTime = LocalDateTime.now();
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
        return "CheckInCompleted";
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    @Override
    public String toString() {
        return String.format("CheckInCompletedEvent[eventId=%s, passenger=%s, flight=%s, seat=%s, time=%s]",
                eventId, passengerId, flightNumber, seatNumber, occurredOn);
    }
}