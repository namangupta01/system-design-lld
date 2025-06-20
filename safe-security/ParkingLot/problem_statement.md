# Problem Statement: Parking Lot System Design

## 1. Core Requirements

Design a flexible and extensible parking lot system with the following characteristics:

*   The parking lot consists of multiple floors.
*   Each floor has numerous parking slots of various sizes.
*   The system must support different types of vehicles, starting with **Two-Wheelers** and **Four-Wheelers**.
*   Each parking slot is designated for a specific vehicle type (e.g., a two-wheeler slot or a four-wheeler slot).

## 2. Key Design Considerations

### 2.1. Pluggable Slot Selection Strategy

The mechanism for selecting a parking slot for an incoming vehicle should be highly modular. The system design should allow for different slot allocation strategies to be implemented and switched with ease, without altering the core parking logic. This suggests decoupling the slot selection algorithm from the main parking service.

For instance, one strategy could be to always find the slot nearest to the entrance, while another might prioritize filling one floor before starting with the next.

### 2.2. Dynamic Parking Rules (Weekday vs. Weekend)

The system must be able to handle parking rules that change based on varying conditions, such as the day of the week. A specific scenario to be implemented is:

*   **On Weekdays:** A slot designated for a four-wheeler must be reserved exclusively for a single four-wheeler vehicle.
*   **On Weekends:** A slot designated for a four-wheeler can be flexibly utilized to park up to two two-wheeler vehicles.

This highlights the need for a design where parking policies are not static and can be dynamically applied.

## 3. Expected System Operations

The system should provide the following functionalities:

*   **Initialization:** Set up the parking lot with a specific configuration of floors and slots for different vehicle types.
*   **Park Vehicle:** Assign a suitable slot to an incoming vehicle and issue a ticket containing parking details.
*   **Un-park Vehicle:** Vacate a slot using the vehicle's ticket information.
*   **Search Vehicle:** Locate a parked vehicle within the lot.

---

## Interview Context

*   **Company:** Safe Security
*   **Role:** SDE3 / Tech Lead
*   **Date:** June 2024
*   **Round:** Low-Level Design (LLD), 1 hour
*   **Expectation:** Present a working code solution demonstrating appropriate design patterns within approximately 40 minutes. 