package com.notification;

public class Email extends Notification {
    // Method to send email notification
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Email: " + message);
    }
}