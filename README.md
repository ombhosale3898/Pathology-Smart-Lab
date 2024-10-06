
# Baneshwar Smart Pathology Lab

Welcome to the Baneshwar Smart Pathology Lab project! This is a web application developed to manage various aspects of a pathology lab, including patient management, doctor appointments, lab tests, and administrative functions. The application is built using React for the front end and Java for the back end, with a RESTful API for communication between them.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

### Admin

- Manage doctors and lab technicians (add, update, delete).
- View and manage appointments.
- Access all functionalities available to other roles (doctor, lab technician, patient).

### Doctor

- Manage appointments (confirm or cancel).
- View and manage patient test reports.
- Book tests for patients.

### Lab Technician

- View test orders by doctor or patient.
- Fill in test details and manage test orders.

### Patient

- Book appointments with doctors.
- Book lab tests and view test reports.
- Update personal profiles.

## Technologies Used

- **Frontend:** React, HTML, CSS, JavaScript
- **Backend:** Java, Spring Boot
- **Database:** MySQL
- **API:** RESTful API
- **Authentication:** JWT (JSON Web Tokens)
- **Tools:** Axios, Postman

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- Node.js (v14 or higher)
- npm (v6 or higher)
- Java JDK (v11 or higher)
- MySQL

### Installation

1. **Clone the repository:**

    \`\`\`bash
    git clone https://github.com/your-username/baneshwar-smart-pathology-lab.git
    cd baneshwar-smart-pathology-lab
    \`\`\`

2. **Set up the backend:**

   - Navigate to the backend directory:
   
     \`\`\`bash
     cd backend
     \`\`\`
   
   - Configure your `application.properties` file with your database credentials:
   
     \`\`\`properties
     spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     \`\`\`
   
   - Install dependencies and build the project:
   
     \`\`\`bash
     mvn clean install
     \`\`\`

3. **Set up the frontend:**

   - Navigate to the frontend directory:
   
     \`\`\`bash
     cd frontend
     \`\`\`
   
   - Install the necessary dependencies:
   
     \`\`\`bash
     npm install
     \`\`\`

### Running the Application

1. **Start the backend server:**

    \`\`\`bash
    cd backend
    mvn spring-boot:run
    \`\`\`

2. **Start the frontend server:**

    \`\`\`bash
    cd frontend
    npm start
    \`\`\`

3. Open your browser and go to `http://localhost:3000`.

## Project Structure

### Backend

- `src/main/java/com/baneshwar/pathologylab`: Main backend codebase, including controllers, services, repositories, and models.
- `src/main/resources/application.properties`: Configuration file for database and other settings.
- `pom.xml`: Maven configuration file with dependencies.

### Frontend

- `src/components`: React components for different roles (Admin, Doctor, Lab Technician, Patient).
- `src/services`: Axios services for API calls.
- `src/App.js`: Main React application file.
- `src/index.js`: Entry point for the React application.

## API Endpoints

- **Authentication:**
  - `POST /login`: Log in to the application.
  - `POST /register`: Register a new user.

- **Admin:**
  - `GET /admin/doctors`: Retrieve all doctors.
  - `POST /admin/doctors`: Add a new doctor.
  - `PUT /admin/doctors/{id}`: Update a doctor.
  - `DELETE /admin/doctors/{id}`: Delete a doctor.

- **Doctor:**
  - `GET /doctor/appointments`: Retrieve all appointments.
  - `POST /doctor/appointments`: Book a new appointment.

- **Lab Technician:**
  - `GET /labtech/orders`: Retrieve all lab test orders.
  - `POST /labtech/orders`: Update lab test details.

- **Patient:**
  - `GET /patient/tests`: Retrieve all booked tests.
  - `POST /patient/tests`: Book a new test.

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request for any enhancements or bug fixes.


