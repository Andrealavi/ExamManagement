# Exam Management System

A comprehensive Java-based desktop application for managing and analyzing university examination records. This project was developed as part of the Object-Oriented Programming course 2023/24.

Complete project documentation is available in the `/docs` folder of the project. The documentation includes detailed information about the implementation, class descriptions, and usage guidelines. You can access it by opening `/docs/index.html` in your web browser.

## Project Overview

This application provides professors with an intuitive tool to manage and track student examinations, offering features for both simple and composite exam types, along with statistical analysis capabilities.

## Core Features

### Exam Management
- **Dual Exam Types Support**:
  - Simple Exams: Single-assessment based final grade
  - Composite Exams: Multiple assessments with weighted average calculation
- **Comprehensive Exam Records**:
  - Student Name
  - Course Name
  - Final Grade (18-30 range)
  - Honors (Yes/No)
  - Credit Points
- **Data Manipulation**:
  - Add new exam records
  - Modify existing entries
  - Delete exam records
  - View detailed breakdown of composite exam components

### Advanced Filtering and Statistics
- **Smart Filtering System**:
  - Filter by student name
  - Filter by course name
  - View weighted average grades for filtered results
- **Statistical Visualization**:
  - Frequency histogram for exam grades
  - Statistical analysis based on selected filters
- **Intelligent Course Name Handling**:
  - Smart matching for course names with different capitalization

### Data Persistence
- **File Operations**:
  - Save exam records to file
  - Load exam records from file
  - File overwrite protection with user confirmation
- **Automatic Backup**:
  - Periodic auto-save feature (every 2 minutes)
  - Temporary file backup system
- **Change Tracking**:
  - Unsaved changes detection
  - Save prompts before closing

### Additional Features
- **Table Operations**:
  - Print functionality for exam tables
  - Export capabilities
- **User Interface**:
  - Intuitive graphical interface
  - Context-sensitive controls
  - Keyboard shortcuts for common operations

## Technical Implementation

### Prerequisites
- Java Runtime Environment (JRE) 8 or higher
- Java Development Kit (JDK) 8 or higher (for development)
- Swing and AWT libraries (included in standard JDK)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Andrealavi/ExamManagement
   ```

2. Navigate to the project directory:
   ```bash
   cd gestione-esami
   ```

3. Compile the project:
   ```bash
   javac -d bin src/**/*.java
   ```

4. Run the application:
   ```bash
   java -cp bin app.Main
   ```

### Keyboard Shortcuts
- **Alt + S**: Add simple exam
- **Alt + C**: Add composite exam
- **Alt + F**: Filter exams
- **Ctrl + L**: Load file
- **Ctrl + S**: Save file
- **Ctrl + Alt + P**: Save file as
- **Ctrl + P**: Print table
- **Backspace**: Remove selected entry

## Documentation

Complete documentation of the project is available in the `/docs` folder. This documentation is generated using Javadoc and includes:
- Detailed class and method descriptions
- Package hierarchies
- Implementation choices and design patterns
- Complete API reference
- Usage examples and guidelines

You can access the documentation by opening `/docs/index.html` in your web browser.

## Project Structure

```
src/
├── app/
│   ├── Main.java                 # Application entry point
│   └── package-info.java
├── controllers/
│   ├── listeners/                # Event listeners for UI interactions
│   │   ├── add/                  # Exam addition handlers
│   │   ├── filter/              # Filtering functionality
│   │   ├── io/                  # File operations
│   │   ├── modify/              # Exam modification handlers
│   │   ├── partials/            # Partial exam management
│   │   └── remove/              # Exam removal handlers
│   ├── AutoSaveThread.java      # Automatic backup functionality
│   └── Controller.java          # Main application controller
├── models/
│   ├── exam/                    # Exam-related classes
│   │   ├── AbstractExam.java    # Base exam class
│   │   ├── ComposedExam.java    # Composite exam implementation
│   │   └── SimpleExam.java      # Simple exam implementation
│   ├── ExamIO.java             # File operations handler
│   └── ExamsTableModel.java    # Data model for exam table
└── views/
    ├── dialogs/                # UI dialog windows
    ├── panels/                 # UI panels
    └── AppFrame.java          # Main application window
```
