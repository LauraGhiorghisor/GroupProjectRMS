package chat_client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class UserListPane extends JPanel implements UserStatusListener {

    private JList<String> userList;
    private DefaultListModel<String> userListModel;

    public UserListPane(String name) throws IOException {
        ChatClient client = new ChatClient("localhost", 8818);

        client.addUserStatusListener(this);
        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        JButton connectBtn = new JButton("Start chatting!");
        setLayout(new BorderLayout());
        add(new JScrollPane(userList), BorderLayout.CENTER);
        add(connectBtn, BorderLayout.SOUTH);

        userList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() > 1) {
                    String login = userList.getSelectedValue();
                    MessagePane messagePane = new MessagePane(client, login);

                    JFrame f = new JFrame("Message " + login);
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    f.setSize(500,500);
                    f.getContentPane().add(messagePane, BorderLayout.CENTER);
                    f.setVisible(true);
                }
            }
        });

        connectBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                connectBtn.setVisible(false);
                for(int i = 0; i < client.getUsersList().size(); i++) {
                    userListModel.addElement(client.getUsersList().get(i));
                }
            }
        });

        JFrame frame = new JFrame("User List");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 600);
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    client.logoff();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
            try {
                client.login(name);
                System.out.println(client.getUsersList());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    @Override
    public void online(String login) {
        userListModel.addElement(login);
    }

    @Override
    public void offline(String login) {
        userListModel.removeElement(login);
    }
}
