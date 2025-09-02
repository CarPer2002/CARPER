# User Stories

## Overview
This document will contain user stories.

## US01: Register a New Customer
**As a** CRM user,
**I want** to register a new customer with contact information,
**So that** I can track and manage customer details effectively.

### Acceptance Criteria
- **Scenario: Successful Customer Registration**
    - **Given** a valid customer name, email, and address details are provided,
    - **When** the system creates a new customer,
    - **Then** the customer is successfully registered with a unique ID and the provided details are stored.
- **Scenario: Invalid Customer Details**
    - **Given** an empty name or email is provided,
    - **When** the system attempts to create a new customer,
    - **Then** an exception is thrown with a message indicating the missing or incorrect information.

