import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AIChatbot {
    private static final Map<String, String> responses = new HashMap<>();

    static {
        responses.put("hello", "Hi there! How can I assist you?");
        responses.put("how are you", "I'm just a bot, but I'm doing great! How about you?");
        responses.put("what is your name", "I'm an AI chatbot created to assist you.");
        responses.put("bye", "Goodbye! Have a great day!");
    }

    public static String getResponse(String input) {
        input = input.toLowerCase();
        for (String key : responses.keySet()) {
            if (input.contains(key)) {
                return responses.get(key);
            }
        }
        return "I'm not sure how to respond to that.";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chatbot: Hello! Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Chatbot: " + getResponse(userInput));
                break;
            }

            System.out.println("Chatbot: " + getResponse(userInput));
        }

        scanner.close();
    }
}