import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class ExBot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        // TODO
        return "StumbleGuys_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "6221856055:AAH46_Xpwk5bQdN3bR3KdnAMAN7xjEAh3mE";
    }

    private static final Map<String, Sentence> Words = new HashMap<>();
    static {
        Words.put("help", new Sentence("for help "));
        Words.put("location", new Sentence("current location"));
        Words.put("details", new Sentence(" details"));

        // Add more lecturers here with their respective staff IDs
    }
    private static String selectedWord = "";
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            if (messageText.equals("hello")) {
                String response = "Welcome to my bots! How can I assist you?\n " +
                        "Please type help or location or details";

                SendMessage Message = new SendMessage();
                Message.setChatId(update.getMessage().getChatId().toString());
                Message.setText(response);


                try {
                    execute(Message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if  (Words.containsKey(messageText.toLowerCase())) {
                selectedWord = messageText.toLowerCase();
                Sentence word = Words.get(selectedWord);

                String response = "Are you looking for " + word.getName() + "?\n" +
                        "1. Yes\n" +
                        "2. No";

                SendMessage Message = new SendMessage();
                Message.setChatId(update.getMessage().getChatId().toString());
                Message.setText(response);

                try {
                    execute(Message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (messageText.equals("Yes")) {
                if (Words.containsKey(selectedWord)) {
                    Sentence word = Words.get(selectedWord);

                    String response = "Looking for " + word.getName() + "\n";

                    if (selectedWord.equals("help")) {
                        response += "looking for Agents ?\n" +
                                "Here is the live chat with agent  \n" + "https://t.me/+4cgcbcvoMHc2MDhl";
                    } else if (selectedWord.equals("location")) {
                        response += "your current location now \n" +
                                "https://maps.app.goo.gl/g4o2kCbfyccntBis5\n";
                    } else if (selectedWord.equals("details")) {
                        response += "Hello User\n"+
                                "My Name is Daniel Lee Mun Weng. Im very passionate to be programmer and i would like to be involve in big company \n "+" Thank you for testing my teleBot" ;
                    }

                    SendMessage Message = new SendMessage();
                    Message.setChatId(update.getMessage().getChatId());
                    Message.setText(response);

                    try {
                        execute(Message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }

            else {
                String response = "Please type help or location or details" ;


                SendMessage Message = new SendMessage();
                Message.setChatId(update.getMessage().getChatId().toString());
                Message.setText(response);

                try {
                    execute(Message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


        }


    }
}

