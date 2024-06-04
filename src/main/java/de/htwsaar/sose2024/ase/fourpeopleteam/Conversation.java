package de.htwsaar.sose2024.ase.fourpeopleteam;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Conversation {
    private ArrayList<Message> messages;

    public Conversation() {
        messages = new ArrayList<>();
    }

    public static Conversation makeStandardConversation() {
        Conversation conv = new Conversation();
        Message firstMessage = new Message("system", "You are a very helpful AI assistant. Please respond very concisely.");

        conv.add(firstMessage);

        return conv;
    }

    public void add(Message message) {
        messages.add(message);
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

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this.getClass() != other.getClass()) {
                return false;
            }
            Message otherMessage = (Message) other;
            return otherMessage.getRole().equals(role) &&
                otherMessage.getContent().equals(content);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Conversation otherConv = (Conversation) other;
        if (messages.size() != otherConv.messages.size()) {
            return false;
        }
        for (int i = 0; i < messages.size(); i++) {
            Message ourMessage = messages.get(i);
            Message theirMessage = otherConv.messages.get(i);
            if (!(ourMessage.equals(theirMessage))) {
                return false;
            }
        }
        return true;
    }
}
