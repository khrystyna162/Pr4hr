public class Passenger {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String passportNumber;

    public Passenger(String id, String firstName, String lastName, String email, String passportNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passportNumber = passportNumber;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public String toString() {
        return String.format("Passenger[id=%s, name=%s %s, email=%s]",
                id, firstName, lastName, email);
    }
}