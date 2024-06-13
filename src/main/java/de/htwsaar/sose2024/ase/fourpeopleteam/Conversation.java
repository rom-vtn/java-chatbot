package de.htwsaar.sose2024.ase.fourpeopleteam;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public JSONArray toJson() {
        JSONArray arr = new JSONArray();
        for (Message m : messages) {
            arr.put(m.toJson());
        }
        return arr;
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

        public JSONObject toJson() {
            JSONObject obj = new JSONObject();
            obj.put("role", this.role);
            obj.put("content", this.content);
            return obj;
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
