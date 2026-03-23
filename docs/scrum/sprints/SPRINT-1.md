# Sprint 1

## Definition of Done
- All Acceptance Criteria are met and manually tested
- Code compiles and runs without errors or exceptions
- Code is committed and pushed to GitHub

---

## US-01: As a User, I want to log in with a username and password so that I can access my role's dashboard.

### Acceptance Criteria
- [x] AC 1: Valid credentials route an Admin to the Admin Dashboard.
- [x] AC 2: Valid credentials route a Coordinator to the Coordinator Dashboard.
- [x] AC 3: Invalid credentials show a visible error message.
- [x] AC 4: The window title reflects the logged-in user's name.
- [x] AC 5: The dashboard displays a welcome message with the user's name and role.
- [x] AC 6: A logout button on each dashboard returns the user to the login screen.

### Tasks
- [x] Task 1 [BE] Create `User` class (id, username, password, role).
- [x] Task 2 [DAL] Create `MockUserDAO` with hardcoded users and `authenticate(username, password)`.
- [x] Task 3 [BLL] Create `UserManager` delegating to the DAO.
- [x] Task 4 [GUI] Create `Login.fxml` and `LoginController` with role-based routing on login.
- [x] Task 5 [GUI] Create `AdminDashboard.fxml` and `EventCoordinatorDashboard.fxml`.
- [x] Task 6 [GUI] Add a welcome label and logout button to both dashboards.

---

## US-02: As an Admin, I want to create user accounts so that new staff can access the system.

### Acceptance Criteria
- [x] AC 1: Admin can enter a username, password, and select a role (Admin / Coordinator).
- [x] AC 2: Clicking "Create" adds the user to the system and clears the input fields.
- [x] AC 3: The user list updates immediately to show the new user.
- [x] AC 4: A duplicate username is rejected with an error message.

### Tasks
- [x] Task 1 [DAL/BLL] Add `createUser(User)` and `getAllUsers()` to `MockUserDAO` and `UserManager`.
- [x] Task 2 [GUI] Create `UserModel` holding an `ObservableList<User>` as the bridge between UI and BLL.
- [x] Task 3 [GUI] Design the Admin Dashboard with a `TableView`, input fields, role `ComboBox`, and a Create button.
- [x] Task 4 [GUI] Implement create logic in `AdminDashboardController` including duplicate username validation.

---

## US-03: As an Admin, I want to manage user accounts so that I can keep staff access up to date.

### Acceptance Criteria
**Edit**
- [x] AC 1: Selecting a user populates the input fields with their current data.
- [x] AC 2: Admin can update the username, password, or role and click "Update" to save.
- [x] AC 3: The user list updates immediately to reflect the changes.
- [x] AC 4: Admin cannot change their own role.

**Delete**
- [x] AC 5: Clicking "Delete" shows a confirmation dialog before removing the user.
- [x] AC 6: The user list updates immediately after deletion.
- [x] AC 7: Admin cannot delete their own account.
- [x] AC 8: The last Admin account cannot be deleted.

**Filter**
- [x] AC 9: The user list can be filtered in real time by username via a search field.
- [x] AC 10: The user list can be filtered by role via a dropdown.

### Tasks
- [x] Task 1 [DAL/BLL] Add `updateUser(User)` and `deleteUser(User)` to `MockUserDAO` and `UserManager`.
- [x] Task 2 [GUI] On row selection, populate input fields with the selected user's data.
- [x] Task 3 [GUI] Implement update logic with a self-role-change guard.
- [x] Task 4 [GUI] Implement delete logic with a confirmation dialog, self-delete guard, and last-admin guard.
- [x] Task 5 [GUI] Add a real-time search field and role filter `ComboBox` above the `TableView`.
