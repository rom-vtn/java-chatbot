package de.htwsaar.sose2024.ase.fourpeopleteam;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Conversation {
    private ArrayList<Message> messages;

    public Conversation() {
        messages = new ArrayList<>();
    }

    public String toJson() {
        StringJoiner sj = new StringJoiner(",");
        for (Message m : messages) {
            sj.add(m.toJson());
        }
        return "[" + sj.toString() + "]";
    }

    public static class Message {
        private String role;
        private String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public static Message makeUserMessage(String content) {
            return new Message("user", content);
        }

        public static Message makeAssistantMessage(String content) {
            return new Message("assistant", content);
        } 

        public String toJson() {
            return "{\"role\":\"" + role + "\",\"content\":\"" + content + "\"}";
        }
    }
}
