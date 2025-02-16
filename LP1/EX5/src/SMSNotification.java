public class SMSNotification extends Notification implements Notifiable{
    public SMSNotification(String message) {
        super(message);
    }

    @Override
    public void sendNotification() {
        displayNotification();
        System.out.println("Sending SMS: " + getMessage());
    }
}
