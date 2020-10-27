package chat_client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class MessagePane extends JPanel implements MessageListener {
    private final ChatClient client;
    private final String login;

    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> messageList = new JList<>(listModel);
    private JTextField inputField = new JTextField();

    public MessagePane(ChatClient client, String login) {
        this.client = client;
        this.login = login;
        setLayout(new BorderLayout());
        add(new JScrollPane(messageList), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        client.addMessageListener(this);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String msg = inputField.getText();
                    client.msg(login, msg);
                    listModel.addElement("You: " + msg);
                    inputField.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onMessage(String fromLogin, String msg) {
        if(login.equalsIgnoreCase(fromLogin)) {
            String line = fromLogin + ": " + msg;
            listModel.addElement(line);
        }
    }
}
