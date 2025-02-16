public class EmailNotification extends Notification implements Notifiable{
    public EmailNotification(String message) {
        super(message);
    }

    @Override
    public void sendNotification() {
        displayNotification();
        System.out.println("Sending Email: " + getMessage());
    }
}
