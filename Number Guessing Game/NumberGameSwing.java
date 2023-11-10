import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGameSwing extends JFrame {
    private int targetNumber;
    private int attempts;
    Container c;
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel feedbackLabel;
    private JTextField guessField;
    private JButton submitButton;

    public NumberGameSwing() {
        targetNumber = new Random().nextInt(100) + 1;
        attempts = 0;

        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,400);
        frame.setLayout(new FlowLayout());
          c=getContentPane();
        c.setLayout(null);
    Font f=new Font("Times new Roman",Font.BOLD,30);
        titleLabel = new JLabel("Guess the number between 1 and 100:");
        titleLabel.setFont(f);
        feedbackLabel = new JLabel("");
        feedbackLabel.setFont(f);
        guessField = new JTextField(10);
        guessField.setFont(f);
        submitButton = new JButton("Submit");
        submitButton.setFont(f);
      feedbackLabel.setBounds(340,80,40,20);
      guessField.setBounds(320,40,400,40);
     submitButton.setBounds(360,60,400,40);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        frame.add(titleLabel);
        frame.add(guessField);
        frame.add(submitButton);
        frame.add(feedbackLabel);

        frame.setVisible(true);
    }

    private void checkGuess() {
        String guessText = guessField.getText();
        try {
            int userGuess = Integer.parseInt(guessText);
            attempts++;

            if (userGuess < targetNumber) {
                feedbackLabel.setText("Too low. Try again.");
            } else if (userGuess > targetNumber) {
                feedbackLabel.setText("Too high. Try again.");
            } else {
                feedbackLabel.setText("Congratulations! You guessed the number " + targetNumber + " in " + attempts + " attempts.");
                guessField.setEditable(false);
                submitButton.setEnabled(false);
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGameSwing();
            }
        });
    }
}
