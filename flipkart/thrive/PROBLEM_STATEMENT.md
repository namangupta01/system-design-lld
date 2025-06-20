This problem was part of a first-round machine coding interview for an SDE3 position at **Flipkart** in June 2025. The task involved a 2-hour coding session, which was followed by a 30-minute code review and discussion with the panel.

Description
At Flipkart, we have an internal talent pool platform called Thrive, where Leads can list projects, and Developers can request to work on them. The platform helps Leads find skilled Developers within the organization while giving Developers opportunities to contribute to projects beyond their primary responsibilities.

You have to design and implement a similar platform. The problem statement is mentioned below.

Problem Statement:
The system should be able to onboard new Leads and Developers.

Leads should be able to post projects for developers. Each project will have a predefined category (e.g., Frontend, Backend, DevOps, etc.), and the list of categories is fixed.

Developers should be able to view available projects and request to work on a project. A developer can only work on any one project at a time.

Leads can receive multiple requests from the developers but can approve only one request per project.

Once a request is approved, the project is assigned to that developer.

Leads can cancel a project before it is approved. If a project is canceled, all pending requests should be removed.

Once assigned, the developer can start working on the project and later mark it as completed.

The system should track the status of projects and developers (e.g., Open, Requested, Assigned, In Progress, Completed).

Once a developer starts a project, the Lead cannot cancel it.

If no developer requests a project within a certain period of its creation, it should be automatically canceled. For demo purposes, we can assume some small duration.

Ensure the system is thread-safe and handles all concurrency scenarios properly.

Bonus Features:
Notify Leads and Developers when a project status updates.

Leads should be able to rate developers after a project is completed.

A dashboard to display top developers based on different strategies such as the number of completed projects and ratings.

Guidelines:
Time: 120 mins.

Write modular, clean, and demo-able code (Test cases or runtime execution).

Provide a driver program/main class/test case to test the system with multiple test scenarios.

Take valid assumptions wherever necessary.

Use design patterns wherever applicable.

Handle concurrency appropriately.

Evaluation Criteria: Demoable & functionally correct code, Code readability, Proper entity modeling, Modularity & Extensibility, Separation of concerns, Abstractions, Exception Handling, and Code comments.

Do not use any external databases (like MySQL). Use only in-memory data structures.

No need to create any UX or HTTP API. The system should be a standalone application.

Sample Input and Output:
The input/output need not be exactly in this format but the functionality should remain intact

vbnet
Copy
Edit
register_lead -> name
register_developer -> name
register_project -> lead, project_name, <skills>
get_available_projects
request_project -> developer, project_id
accept_request -> request_id, project_id, lead
get_developer_details -> developer
get_project_details -> project_id
cancel_project -> lead, project_id
complete_project -> developer, project_id

i: input
o: output

i: register_lead -> Lead1
o: Lead1 lead registered

i: register_lead -> Lead2
o: Lead2 lead registered

i: register_developer -> Dev1
o: Dev1 developer registered

i: register_developer -> Dev2
o: Dev2 developer registered

i: register_project -> Lead2, App Development, ["web-development", "design"]
o: App Development project with id project123 registered by Lead2

i: register_project -> Lead1, K8s cluster setup, ["devops"]
o: K8s cluster setup project with id project232 registered by Lead1

i: get_available_projects
o:
project123, App Development, ["web-development", "design"], Lead2  
project232, K8s cluster setup, ["devops"], Lead1

i: request_project -> Dev1, project123
o: Request with id req123 for project123 is registered for Dev1

i: accept_request -> req123, project123, Lead2
o: Dev1 request is accepted to work on project123

i: get_developer_detail -> Dev1
o: Dev1 is working on project123

i: get_developer_details -> Dev2
o: Dev2 has no project assigned

i: get_project_details -> project123
o:
project123, App Development, ["web-development", "design"], Lead2, Assigned, Dev1

i: get_project_details -> project232
o:
project232, K8s cluster setup, ["devops"], Lead1, UnAssigned

i: complete_project -> Dev1, project123
o: project123 is marked completed

i: cancel_project -> Lead1, project232
o: project232 is cancelled 





