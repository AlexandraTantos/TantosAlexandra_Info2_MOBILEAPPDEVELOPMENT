import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Notifiable> notifications = new ArrayList<>();

        notifications.add(new SMSNotification("Your package has been shipped!"));
        notifications.add(new EmailNotification("Welcome to our newsletter!"));
        notifications.add(new PushNotification("You have a new friend request!"));

        for (Notifiable notification : notifications) {
            notification.sendNotification();
        }
    }
}