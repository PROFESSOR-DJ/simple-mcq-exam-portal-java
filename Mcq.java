import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

class Mcq extends JFrame implements ActionListener {
    JLabel l;
    JRadioButton jb[] = new JRadioButton[5];
    JButton NEXT, BOOKMARK, PREVIOUS;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    private Element rootElement;
    private String studentName;
    private String studentEmail;
    private int marks;

    Mcq(String s, String email) {
        super(s);
        l = new JLabel();
        l.setBackground(Color.BLACK);
        l.setForeground(Color.WHITE);
        add(l);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            jb[i].setBackground(Color.BLACK);
            jb[i].setForeground(Color.WHITE);
            bg.add(jb[i]);
        }
        NEXT = new JButton("Next");
        BOOKMARK = new JButton("Bookmark");
        PREVIOUS = new JButton("Previous");
        NEXT.addActionListener(this);
        BOOKMARK.addActionListener(this);
        PREVIOUS.addActionListener(this);
        add(NEXT);
        add(BOOKMARK);
        add(PREVIOUS);
        set();
        l.setBounds(30, 40, 450, 20);
        jb[0].setBounds(50, 80, 100, 20);
        jb[1].setBounds(50, 110, 100, 20);
        jb[2].setBounds(50, 140, 100, 20);
        jb[3].setBounds(50, 170, 100, 20);
        NEXT.setBounds(250, 240, 100, 30);
        BOOKMARK.setBounds(400, 240, 100, 30);
        PREVIOUS.setBounds(100, 240, 100, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 600);
        getContentPane().setBackground(Color.BLACK);

        // Retrieve student details based on email
        retrieveStudentDetails(email);
    }

    private void retrieveStudentDetails(String email) {
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
                    if (email.equals(storedEmail)) {
                        studentName = element.getElementsByTagName("name").item(0).getTextContent();
                        studentEmail = storedEmail;
                        break;
                    }
                }
            }

            // Initialize rootElement here
            rootElement = doc.getDocumentElement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateResultInXml() {
        try {
            File xmlFile = new File("results.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            if (xmlFile.exists()) {
                doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();
            } else {
                doc = dBuilder.newDocument();
                Element rootElement = doc.createElement("results");
                doc.appendChild(rootElement);
            }

            Element resultElement = doc.createElement("result");
            rootElement.appendChild(resultElement);

            Element nameElement = doc.createElement("name");
            nameElement.appendChild(doc.createTextNode(studentName));
            resultElement.appendChild(nameElement);

            Element emailElement = doc.createElement("email");
            emailElement.appendChild(doc.createTextNode(studentEmail));
            resultElement.appendChild(emailElement);

            Element marksElement = doc.createElement("marks");
            marksElement.appendChild(doc.createTextNode(String.valueOf(marks)));
            resultElement.appendChild(marksElement);

            // Write the content into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void set() {
        jb[4].setSelected(true);
        if (current == 0) {
            l.setText("1) Which of the following is a part of Data Science?");
            jb[0].setText("Data Collection");
            jb[1].setText("Data Analysis");
            jb[2].setText("Data Visualization");
            jb[3].setText("Data Cleaning");
        }
        if (current == 1) {
            l.setText("2) Which action is followed by a data scientist after collecting the data?");
            jb[0].setText("Data Storage");
            jb[1].setText("Data Cleaning");
            jb[2].setText("Data Visualization");
            jb[3].setText("Data Preprocessing");
        }
        if (current == 2) {
            l.setText("3) Which of the following is NOT a data science application?");
            jb[0].setText("Predicting Stock Prices");
            jb[1].setText("Image Recognition");
            jb[2].setText("Generating Random Numbers");
            jb[3].setText("Fraud Detection");
        }
        if (current == 3) {
            l.setText("4) Which model is frequently used as the benchmark for data analysis?");
            jb[0].setText("Support Vector Machine");
            jb[1].setText("Decision Tree");
            jb[2].setText("Linear Regression");
            jb[3].setText("Random Forest");
        }
        l.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            jb[j].setBounds(50, 80 + i, 200, 20);
        if (current >= 0 && current <= 3 && jb[current].getText().startsWith("Selected: Yes")) {
            jb[current].setSelected(true);
        }
        jb[4].setText("Selected: No");
        if (current >= 0 && current <= 3 && jb[current].isSelected()) {
            jb[4].setText("Selected: Yes");
        }
    }

    boolean check() {
        if (current == 0)
            return (jb[1].isSelected());
        if (current == 1)
            return (jb[3].isSelected());
        if (current == 2)
            return (jb[2].isSelected());
        if (current == 3)
            return (jb[2].isSelected());
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == NEXT) {
            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 4) {
                NEXT.setEnabled(false);
                BOOKMARK.setText("Result");
                calculateMarks();
                updateResultInXml();
            }
        }
        if (e.getSource() == PREVIOUS) {
            if (check())
                count = count + 1;
            current--;
            set();
        }
        if (e.getActionCommand().equals("Bookmark")) {
            JButton bk = new JButton("Bookmark" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 4)
                BOOKMARK.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Bookmark" + y)) {
                if (check())
                    count = count + 1;
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }
        }
        if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            current++;
            JOptionPane.showMessageDialog(this, "Correct answers: " + count);
            System.exit(0);
        }
    }

    private void calculateMarks() {
        // Each correct answer gives 4 marks
        marks = count * 4;
    }

    public static void main(String s[]) {
        // Not needed as the instance is created in McqLogin
    }
}
