# Simple MCQ Exam Portal in Java - Documentation

## Introduction:

The Simple MCQ Exam Portal in Java is a straightforward multiple-choice question (MCQ) exam application developed using Java and Swing. This application enables users to answer a set of questions, navigate through them, bookmark questions for review, and view the result at the end.

## Components:

1. **JFrame:** The main graphical user interface (GUI) window for the application.

2. **JLabel (l):** Displays the current question.

3. **JRadioButton (jb[]):** Array of radio buttons representing answer options.

4. **JButton (NEXT, BOOKMARK, PREVIOUS):** Buttons for navigating through questions, bookmarking, and moving to the previous question.

5. **ButtonGroup (bg):** Groups radio buttons to ensure only one option is selected at a time.

6. **int variables (count, current, x, y, now, m[]):** Used to keep track of the exam progress and bookmarks.

## GUI Styling:

1. **Background Color:** The JFrame and components have a black background for a visually appealing interface.

2. **Foreground Color:** Text and components are set to white for better visibility against the black background.

3. **Buttons:** NEXT, BOOKMARK, and PREVIOUS buttons are provided for navigation and interaction.

## Workflow:

1. **Initialization:**
   - JFrame is created with appropriate size and location.
   - Components (labels, radio buttons, buttons) are initialized and added to the JFrame.
   - The initial question and answer options are set using the `set()` method.

2. **Answering Questions:**
   - Users can select one answer option using radio buttons.
   - Clicking the NEXT button moves to the next question.
   - Clicking the PREVIOUS button moves to the previous question.

3. **Bookmarking Questions:**
   - Clicking the BOOKMARK button bookmarks the current question.
   - Bookmarked questions are stored in an array for reference.
   - Users can navigate to bookmarked questions using the "Bookmark" buttons.

4. **Result Display:**
   - After answering all questions, the NEXT button transforms into a RESULT button.
   - Clicking the RESULT button displays a dialog with the number of correct answers.

5. **GUI Styling:**
   - The black background and white text create a visually appealing interface.
   - Radio buttons and buttons are styled for better visibility.

## Code Organization:

1. **Class Structure:**
   - The code is organized into a single class named `Mcq`.
   - Each method is responsible for a specific task, promoting modularity.

2. **Event Handling:**
   - Action listeners are used to handle button clicks and user interactions.
   - Actions include moving to the next/previous question, bookmarking, and displaying the result.

3. **Variable Management:**
   - Variables are appropriately named to enhance code readability.
   - Arrays (`jb[]`, `m[]`) are used to manage answer options and bookmarked questions.

## Future Improvements:

1. **Database Integration:**
   - Implement a database to store questions, options, and correct answers.

2. **User Authentication:**
   - Add user authentication to track individual user progress.

3. **Scoring System:**
   - Implement a scoring system to calculate and display the user's score.

## Conclusion:

The Simple MCQ Exam Portal in Java provides a basic framework for an MCQ exam application. It can be extended and enhanced with additional features and improvements based on specific requirements. This documentation serves as a guide for understanding the application's structure, workflow, and potential areas for expansion.