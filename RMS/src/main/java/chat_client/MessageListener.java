package chat_client;

public interface MessageListener {
    public void onMessage(String login, String msg);
}
