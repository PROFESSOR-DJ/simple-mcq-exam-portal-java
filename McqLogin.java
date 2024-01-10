import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

class McqLogin extends JFrame implements ActionListener {
    JTextField emailField;
    JPasswordField passwordField;
    JButton loginButton;

    private Mcq mcq;

    McqLogin() {
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");

        emailField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        emailLabel.setBounds(50, 20, 80, 30);
        passwordLabel.setBounds(50, 70, 80, 30);

        emailField.setBounds(150, 20, 200, 30);
        passwordField.setBounds(150, 70, 200, 30);

        loginButton.setBounds(150, 120, 100, 30);
        loginButton.addActionListener(this);

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        setLayout(null);
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Check credentials against XML file
        if (validateCredentials(email, password)) {
            mcq = new Mcq("MCQ for Data Science", email);
            dispose(); // Close the login window after successful login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        }
    }

    private boolean validateCredentials(String email, String password) {
        try {
            File xmlFile = new File("students.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("student");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String storedEmail = element.getElementsByTagName("email").item(0).getTextContent();
                    String storedPassword = element.getElementsByTagName("password").item(0).getTextContent();

                    if (email.equals(storedEmail) && password.equals(storedPassword)) {
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String s[]) {
        new McqLogin();
    }
}
