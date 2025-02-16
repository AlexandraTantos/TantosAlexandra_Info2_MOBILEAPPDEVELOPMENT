public class PushNotification extends Notification implements Notifiable{
    public PushNotification(String message) {
        super(message);
    }

    @Override
    public void sendNotification() {
        displayNotification();
        System.out.println("Sending Push Notification: " + getMessage());
    }
}
