package Project3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIIIIIIIIIIIIIIIIIIIIIIIII extends Application {
	private HashMap hash = new HashMap(11); // Initial size of 11

	private FileChooser fChooser;
	private File f;
	private MenuBar menuBar;
	private Menu menu1, menu2, menu3, menu5;
	private MenuItem item1, item21, item22, addMartyr, updateMartyr, searchMartyr, addDate, updateDate, deleteDate,
			itemSave;
	private BorderPane bp;
	private Label lblSuccess, lblResult;
	private TextField tfDate, tfDistrictUpdate, tfLocation, tfLocationUpdate, tfFirst, tfMiddle, tfSurname;
	private VBox vbDistrictInsert, vbDistrictNavigation, vbMartyr, vbPrint, vbDistrictDate;
	private HBox hbDistrictInsert, hbDistrictInsertButtons, hbLocationInsertButtons, hbNavigation, hbDatePickers,
			hbMartyrName, hbGender;
	private Button btOkDis, btClearDis, btPrev, btNext, submitButton, btOkLocation, btClearLocation, btPrint;
	private TextArea ta;
	private RadioButton rbMale, rbFemale;
	private ToggleGroup tg;
	private Scene scene;
	private Image image;
	private ImageView imageView;
	private ComboBox<Integer> comboBoxDay, comboBoxYear;
	private ComboBox<Byte> comboAges;
	private ComboBox<String> comboDate, comboBoxMonth;
	private ComboBox<District> comboDistricts;
	private Labeled lblDate;
	private MenuItem navigateMartyrs;
	private ObservableList<Martyr> martyrs = FXCollections.observableArrayList();

	private DatePicker datePicker = new DatePicker();
	private HBox hbMartyrSelection;
	private ComboBox<String> comboMartyrs;
	private TableView<Martyr> tableView;
	private TableColumn<Martyr, String> nameColumn, dateColumn, locationColumn, genderColumn, districtColumn, ageColumn;
	private int currentDateIndex = 0; // Keeps track of the current date index in the hash table

	String partOfName;

	private MenuItem navigateDate;

	private BorderPane bpLocation;

	private ComboBox<String> comboLocations;

	private Stack districtStack;
	private TableView<Martyr> martyrsTable = new TableView<>();

	private MenuItem sortAgeItem;

	private TableRow<Martyr> row;

	private Martyr rowData;

	private Martyr selectedMartyr;

	private boolean confirmDeletion() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Deletion");
		alert.setHeaderText(" Deletion Confirmation");
		alert.setContentText("Are you sure you want to delete?");

		Optional<ButtonType> result = alert.showAndWait();
		return result.isPresent() && result.get() == ButtonType.OK;
	}

	private boolean confirmUpdate() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Update");
		alert.setHeaderText(" Update Confirmation");
		alert.setContentText("Are you sure you want to update?");

		Optional<ButtonType> result = alert.showAndWait();
		return result.isPresent() && result.get() == ButtonType.OK;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) {

		try {
			// Initialize Menus and MenuItems
			menuBar = new MenuBar();

			// File Menu
			menu1 = new Menu("File");
			item1 = new MenuItem("Open");
			itemSave = new MenuItem("Save");
			menu1.getItems().add(item1);
			menu1.getItems().add(itemSave);

			// Help Center Menu
			menu2 = new Menu("Help center");
			item21 = new MenuItem("About us");
			item22 = new MenuItem("Firas's system");
			menu2.getItems().addAll(item21, item22);

			// Operations Menu for Districts
			menu3 = new Menu("Date screen");
			addDate = new MenuItem("Add a new Date");
			updateDate = new MenuItem("update existing Date");
			deleteDate = new MenuItem("remove Date");
			navigateDate = new MenuItem("Navigate Date");
			menu3.getItems().addAll(addDate, updateDate, deleteDate, navigateDate);

			menu5 = new Menu("Martyr's screen");
			addMartyr = new MenuItem("Add a new Martyr");
			updateMartyr = new MenuItem("Update/Delete martyr");
			searchMartyr = new MenuItem("Search for a martyr");
			navigateMartyrs = new MenuItem("Navigate Martyrs");
			sortAgeItem = new MenuItem("Sort by Age");

			menu5.getItems().addAll(addMartyr, updateMartyr, searchMartyr, sortAgeItem);

			menuBar.getMenus().addAll(menu1, menu3, menu5, menu2);
			menuBar.setPadding(new Insets(10, 10, 10, 10));
			try {
				// Linking MenuItem actions to methods
				addDate.setOnAction(e -> showAddDateScreen(primaryStage));
				updateDate.setOnAction(e -> showUpdateDateScreen(primaryStage));
				deleteDate.setOnAction(e -> showDeleteDateScreen(primaryStage));
				navigateDate.setOnAction(e -> showNavigateDatesScreen(primaryStage));

				// Set actions for menu items related to locations and martyrs
				addMartyr.setOnAction(e -> showAddMartyrScreen(primaryStage));
				updateMartyr.setOnAction(e -> showUpdateMartyrScreen(primaryStage));
				searchMartyr.setOnAction(e -> printTreeScreen(primaryStage));
				navigateMartyrs.setOnAction(e -> showNavigateMartyrScreen(primaryStage));
				sortAgeItem.setOnAction(e -> showSortAge(primaryStage));

			} catch (Exception e) {
				showAlert(e.getMessage(), AlertType.ERROR);
			}

			// Initialize RadioButtons and ToggleGroup
			tg = new ToggleGroup();

			rbMale = new RadioButton("Male");
			rbFemale = new RadioButton("Female");
			rbMale.setToggleGroup(tg);
			rbFemale.setToggleGroup(tg);

			// Initialize Labels
			lblDate = new Label("District's name");
			lblSuccess = new Label("");
			lblDate = new Label();
			lblResult = new Label();

			// Initialize TextFields
			tfDate = new TextField();
			tfDistrictUpdate = new TextField();
			ta = new TextArea();
			ta.setWrapText(true);
			ta.setPrefHeight(350);
			ta.setPrefWidth(350);
			districtStack = new Stack();
			tfLocation = new TextField();
			tfLocationUpdate = new TextField();
			tfFirst = new TextField();
			tfFirst.setPromptText("First Name");
			tfMiddle = new TextField();
			tfMiddle.setPromptText("Middle Name");
			tfSurname = new TextField();
			tfSurname.setPromptText("Surname Name");

			// Initialize Buttons
			btPrev = new Button("⏮️Previous");
			btNext = new Button("Next⏭️");
			btOkDis = new Button("Insert");
			btClearDis = new Button("Clear");
			submitButton = new Button("Submit");
			btPrint = new Button("Print");
			btOkLocation = new Button("Insert");
			btClearLocation = new Button("Clear");

			// Initialize ComboBoxes
			comboBoxDay = new ComboBox<>();
			comboBoxMonth = new ComboBox<>();
			comboBoxYear = new ComboBox<>();
			comboDate = new ComboBox<>();
			comboLocations = new ComboBox<>();
			comboDistricts = new ComboBox<>();

			comboMartyrs = new ComboBox<>();
			comboAges = new ComboBox<>();
			comboDate.setOnAction(e -> {

			});
			for (Byte age = 1; age <= 60; age++) {
				comboAges.getItems().add(age);
			}
			// Initialize Layout Panes
			hbDistrictInsert = new HBox(16, lblDate, tfDate);
			hbDistrictInsertButtons = new HBox(16, btOkDis, btClearDis);
			hbNavigation = new HBox(16, btPrev, btNext);
			hbDatePickers = new HBox(10, new Label("Month:"), comboBoxMonth, new Label("Day:"), comboBoxDay,
					new Label("Year:"), comboBoxYear);
			hbDatePickers.setAlignment(Pos.CENTER);

			hbLocationInsertButtons = new HBox(16, btOkLocation, btClearLocation);

			hbMartyrName = new HBox(16);
			hbGender = new HBox(rbMale, rbFemale);
			// VBox for different sections
			vbDistrictInsert = new VBox(16, hbDistrictInsert, hbDistrictInsertButtons, lblSuccess);
			vbDistrictNavigation = new VBox(16, ta, hbNavigation);
			vbDistrictDate = new VBox(16, hbDatePickers, submitButton, lblDate);
			vbPrint = new VBox(16, comboDate, btPrint);

			vbMartyr = new VBox(16);

			image = new Image("birzeit-university-logo.png");
			imageView = new ImageView(image);

			// Set alignments
			hbDistrictInsertButtons.setAlignment(Pos.CENTER);
			vbDistrictInsert.setAlignment(Pos.CENTER);
			vbDistrictNavigation.setAlignment(Pos.CENTER);
			vbDistrictDate.setAlignment(Pos.CENTER);
			vbPrint.setAlignment(Pos.CENTER);
			ta.setEditable(false);
			ta.setVisible(true);
			ta.setStyle("-fx-alignment: center;\r\n" + "-fx-font-size: 18;-fx-font-weight: bold;");
			hbLocationInsertButtons.setAlignment(Pos.CENTER);

			tableView = new TableView<>();
			nameColumn = new TableColumn<>("Name");
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			nameColumn.setPrefWidth(200);

			dateColumn = new TableColumn<>("Date");
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

			locationColumn = new TableColumn<>("Location");
			locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
			locationColumn.setPrefWidth(150);
			districtColumn = new TableColumn<>("District");
			districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));

			ageColumn = new TableColumn<>("Age");
			ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
			// Set a custom cell factory to handle the display of age values
			ageColumn.setCellValueFactory(cellData -> {
				Martyr martyr = cellData.getValue();
				if (martyr.getAge() == -1) {
					return new SimpleStringProperty(""); // Returns an empty string if age is -1
				} else {
					return new SimpleStringProperty(Integer.toString(martyr.getAge())); // Returns age as String
				}
			});
			genderColumn = new TableColumn<>("Gender");
			genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

			tableView.getColumns().addAll(nameColumn, dateColumn, locationColumn, districtColumn, ageColumn,
					genderColumn);

			// Set BorderPane as the root and apply CSS stylesheet
			bpLocation = new BorderPane();
			bp = new BorderPane();
			bp.setTop(menuBar);
			bp.setCenter(imageView);
			scene = new Scene(bp, 900, 600);
			scene.getStylesheets().add(getClass().getResource("/resource/style.css").toExternalForm());

			// Finalize the stage setup
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main Screen");
			item1.setOnAction(e -> readFile(primaryStage));
			itemSave.setOnAction(e -> saveFile(primaryStage));

			primaryStage.show();
		} catch (Exception e) {
			showAlert(e.getMessage(), AlertType.ERROR);
		}

	}

	private void showSortAge(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		setupSearchComponents(); // Set up the search components

		// Create a local TableView for sorting
		TableView<Martyr> localTableView = new TableView<>();
		configureColumns(localTableView); // Configures columns similar to the main TableView

		// Clear any previous data from text fields and reset UI components
		clearUIComponents();

		// Perform sorting of martyrs by age using a heap and display
		sortAndDisplayMartyrsByAge(primaryStage, localTableView);
	}

	@SuppressWarnings("unchecked")
	private void configureColumns(TableView<Martyr> tableView) {
		TableColumn<Martyr, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setPrefWidth(200); // Wider column for names

		TableColumn<Martyr, Integer> ageColumn = new TableColumn<>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		ageColumn.setPrefWidth(100);

		TableColumn<Martyr, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateColumn.setPrefWidth(150); // Width for date

		TableColumn<Martyr, String> districtColumn = new TableColumn<>("District");
		districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));
		districtColumn.setPrefWidth(150); // Width for district

		TableColumn<Martyr, String> locationColumn = new TableColumn<>("Location");
		locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
		locationColumn.setPrefWidth(150); // Width for location

		TableColumn<Martyr, String> genderColumn = new TableColumn<>("Gender");
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
		genderColumn.setPrefWidth(100); // Width for gender

		// Add all configured columns to the TableView
		tableView.getColumns().addAll(nameColumn, dateColumn, ageColumn, locationColumn, districtColumn, genderColumn);
	}

	private void clearUIComponents() {
		// Reset text fields, labels, combo boxes, etc.
		comboAges.setValue(null);
		datePicker.setValue(null);
	}

	private void sortAndDisplayMartyrsByAge(Stage primaryStage, TableView<Martyr> localTableView) {
		if (martyrs.isEmpty()) {
			showAlert("No martyrs data available to sort.", AlertType.INFORMATION);
			return;
		}

		MaxHeap heap = new MaxHeap(martyrs.size());
		heap.populateHeap(martyrs);

		List<Martyr> sortedMartyrs = new ArrayList<>();
		while (!heap.isEmpty()) {
			sortedMartyrs.add(heap.extractMax());
		}

		ObservableList<Martyr> sortedList = FXCollections.observableArrayList(sortedMartyrs);
		localTableView.setItems(sortedList);

		// Display the localTableView in the GUI
		VBox layout = new VBox(10);
		layout.getChildren().addAll(new Label("Sorted Martyrs by Age"), localTableView);
		bp.setTop(menuBar);
		bp.setCenter(layout);
		// Scene scene = new Scene(layout, 400, 400);
		scene.setRoot(bp);
	}

	public void sortAndDisplayMartyrsByAge() {
		clearAndResetTableView(); // Clear and reset before sorting
		if (martyrs.isEmpty()) {
			// Load data or show error/alert
			showAlert("No martyrs data available to sort.", AlertType.INFORMATION);
			return;
		}

		MaxHeap heap = new MaxHeap(martyrs.size());
		heap.populateHeap(martyrs); // Load all current martyrs into the heap

		List<Martyr> sortedMartyrs = new ArrayList<>();
		while (!heap.isEmpty()) {
			sortedMartyrs.add(heap.extractMax()); // Extract martyrs from the heap in sorted order
		}

		// Update the TableView
		ObservableList<Martyr> sortedList = FXCollections.observableArrayList(sortedMartyrs);
		tableView.setItems(sortedList);
		tableView.refresh(); // Make sure the TableView reflects the new sorted data
	}

	private void showNavigateDatesScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		vbDistrictNavigation.getChildren().clear();

		Button btnNextDate = new Button("Next Date");
		Button btnPrevDate = new Button("Previous Date");
		Button btnLoad = new Button("Load");

		HBox hbDateNavigation = new HBox(10, btnPrevDate, btnNextDate, btnLoad);
		hbDateNavigation.setAlignment(Pos.CENTER);

		vbDistrictNavigation.getChildren().addAll(ta, hbDateNavigation);
		bp.setCenter(vbDistrictNavigation);
		currentDateIndex = 0;
		try {
			// Display the first date's details
			displayDateDetails(currentDateIndex);

			btnNextDate.setOnAction(e -> {
				currentDateIndex = navigateDate(currentDateIndex, 1); // Navigate forward
				displayDateDetails(currentDateIndex);
			});

			btnPrevDate.setOnAction(e -> {
				currentDateIndex = navigateDate(currentDateIndex, -1); // Navigate backward
				displayDateDetails(currentDateIndex);
			});

			btnLoad.setOnAction(e -> {
				showDateDetailsWindow(primaryStage, currentDateIndex);
			});
		} catch (Exception e) {
			showAlert("Error loading date details: " + e.getMessage(), AlertType.ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	private void showDateDetailsWindow(Stage primaryStage, int dateIndex) {
		// Create a new stage (window)
		Stage detailsStage = new Stage();
		detailsStage.setTitle("Date Details");

		// Create layout and add components
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(20));
		vbox.setSpacing(10);

		// Create the TableView
		TableView<Martyr> tableView = new TableView<>();
		TableColumn<Martyr, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setPrefWidth(200);

		TableColumn<Martyr, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		TableColumn<Martyr, String> locationColumn = new TableColumn<>("Location");
		locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
		locationColumn.setPrefWidth(150);

		TableColumn<Martyr, String> districtColumn = new TableColumn<>("District");
		districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));

		TableColumn<Martyr, Integer> ageColumn = new TableColumn<>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		ageColumn.setPrefWidth(100);

		TableColumn<Martyr, String> genderColumn = new TableColumn<>("Gender");
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

		tableView.getColumns().addAll(nameColumn, dateColumn, locationColumn, districtColumn, ageColumn, genderColumn);

		// Populate the TableView with the martyrs associated with the current date
		HashEntry entry = hash.getTable()[dateIndex];
		if (entry != null && entry.getStatus() == 'F') {
			ObservableList<Martyr> martyrs = FXCollections.observableArrayList();
			collectMartyrs(entry.getAvlTree().getRoot(), martyrs);
			tableView.setItems(martyrs);
		}

		// Add the TableView to the VBox
		vbox.getChildren().add(tableView);

		// Set scene and stage
		Scene scene = new Scene(vbox, 600, 400);
		detailsStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("/resource/style.css").toExternalForm());

		// Show the new window
		detailsStage.initModality(Modality.APPLICATION_MODAL); // Block input events with other windows
		detailsStage.show(); // Show the new window
	}

	private int navigateDate(int currentIndex, int direction) {
		int nextIndex = currentIndex + direction;
		if (nextIndex >= 0 && nextIndex < hash.getTableSize() && hash.getTable()[nextIndex] == null) {
			nextIndex += direction;
		}
		if (nextIndex < 0 || nextIndex >= hash.getTableSize()) {
			nextIndex = currentIndex; // Do not allow index to go out of bounds
		}
		return nextIndex;
	}

	private void displayDateDetails(int index) {
		try {
			if (index >= 0 && index < hash.getTableSize() && hash.getTable()[index] != null
					&& hash.getTable()[index].getStatus() == 'F') {
				HashEntry entry = hash.getTable()[index];
				int totalMartyrs = hash.martyrsTotal(entry.getDate());
				double averageAge = hash.martyrsAverage(entry.getDate());
				District maxMartyrDistrict = hash.districtWithMaxMartyrs(entry);
				Location maxMartyrLocation = maxMartyrDistrict.findMaxMartyrLocation();

				StringBuilder details = new StringBuilder();
				details.append("Date: ").append(entry.getDate()).append("\n");
				details.append("Total Martyrs: ").append(totalMartyrs).append("\n");
				details.append("Average Age: ").append(String.format("%.2f", averageAge)).append("\n");
				details.append("District with Most Martyrs: ")
						.append(maxMartyrDistrict.getName() + " " + maxMartyrDistrict.getNumberOfMartyrs())
						.append("\n");
				details.append("Location with Most Martyrs: ")
						.append(maxMartyrLocation.getName() + " " + maxMartyrLocation.getNumberOfMartyrs())
						.append("\n");

				ta.setText(details.toString());
			} else
				currentDateIndex++;
		} catch (Exception e) {
			showAlert(e.getMessage(), AlertType.ERROR);
		}
	}

	private void showNavigateMartyrScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);

		ta.clear();
		vbDistrictNavigation.getChildren().clear();
		vbDistrictNavigation.getChildren().addAll(comboDate, lblResult, tableView, hbNavigation);
		hbNavigation.setAlignment(Pos.CENTER);
		bp.setCenter(vbDistrictNavigation);
		tableView.setPrefHeight(200);

	}

	private void printTreeScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage); // Assuming this sets up the location or resets the GUI
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");

		hbMartyrName.getChildren().clear(); // Clearing any previous settings in hbMartyrName if used elsewhere
		hbLocationInsertButtons.getChildren().clear(); // Clearing buttons if used elsewhere

		comboDate.setVisible(true);
		datePicker.setValue(null);
		comboDate.setPromptText("Select Date");

		// Clear and setup VBox for this screen
		vbMartyr.getChildren().clear();
		vbMartyr.getChildren().addAll(new Label("Select a date to display the tree:"), comboDate, ta);

		// Set action on comboDate to populate the TextArea with the AVL tree
		comboDate.setOnAction(e -> {
			String selectedDate = comboDate.getValue();
			if (selectedDate != null) {
				HashEntry entry = hash.find(selectedDate);
				if (entry != null && entry.getStatus() == 'F') {
					AVLNode root = entry.getAvlTree().getRoot();
					printTreeRightToLeft(root);
				}
			}
		});

		// Adjust alignment and appearance
		vbMartyr.setAlignment(Pos.CENTER);
		primaryStage.setTitle("Print Tree Screen");

		primaryStage.show();
	}

	public void printTreeRightToLeft(AVLNode root) {
		if (root == null) {
			ta.setText("No data available.");
			return;
		}

		Queue queue = new Queue(); // Use your custom Queue class
		queue.enQueue(root);
		StringBuilder sb = new StringBuilder();

		while (!queue.isEmpty()) {
			int levelSize = queue.getSize(); // Get the current size of the queue (number of elements at this level)

			for (int i = 0; i < levelSize; i++) {
				AVLNode currentNode = (AVLNode) queue.deQueue();
				// Add the current node's details to the StringBuilder
				// Start from the rightmost element
				if (i == 0) { // This ensures we start a new line at the beginning of each level
					sb.append("\n");
				}
				sb.append(currentNode.getMartyr().getName() + " - ");

				// Enqueue child nodes from right to left
				if (currentNode.getRight() != null) {
					queue.enQueue(currentNode.getRight());
				}
				if (currentNode.getLeft() != null) {
					queue.enQueue(currentNode.getLeft());
				}
			}
		}

		ta.setText(sb.toString()); // Set the text of the TextArea to the built string
	}

	@SuppressWarnings("unchecked")
	private void setupMartyrsTable() {
		nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setPrefWidth(200);
		ageColumn = new TableColumn<>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		ageColumn.setPrefWidth(100);

		districtColumn = new TableColumn<>("District");
		districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));
		districtColumn.setPrefWidth(150);

		martyrsTable.getColumns().addAll(nameColumn, ageColumn, districtColumn);
		comboDate.setOnAction(event -> {
			if (comboDate.getValue() != null) {
				String selectedDate = comboDate.getValue();
				populateMartyrsTable(selectedDate);
			}

		});

		martyrsTable.setRowFactory(tv -> {
			row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					rowData = row.getItem();
					showUpdateDeleteDialog(rowData);
				}
			});
			return row;
		});
		tableView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1 && tableView.getSelectionModel().getSelectedItem() != null) {
				selectedMartyr = tableView.getSelectionModel().getSelectedItem();
				showUpdateDeleteDialog(selectedMartyr);
			}
		});
	}

	private void openMartyrDetailsWindow(Martyr selectedMartyr) {
		// Create a new stage (window)
		Stage detailsStage = new Stage();
		detailsStage.setTitle("Edit Martyr Details");

		// Create layout and add components
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20));
		grid.setVgap(10);
		grid.setHgap(10);

		// Name field
		Label nameLabel = new Label("Name:");
		TextField nameField = new TextField(selectedMartyr.getName());
		grid.add(nameLabel, 0, 0);
		grid.add(nameField, 1, 0);

		// District field
		Label districtLabel = new Label("District:");
		grid.add(districtLabel, 0, 1);
		grid.add(comboDistricts, 1, 1);

		// Location field
		Label locationLabel = new Label("Location:");
		grid.add(locationLabel, 0, 2);
		grid.add(comboLocations, 1, 2);

		Label ageLabel = new Label("Age:");
		grid.add(ageLabel, 0, 3);
		grid.add(comboAges, 1, 3);
		grid.add(new Label("Date"), 0, 4);
		grid.add(datePicker, 1, 4); // OK button to save changes
		Button okButton = new Button("OK");

		comboDistricts.getItems().clear();
		comboLocations.getItems().clear();
		populateDistrict();
		populateDistrictComboBox(districtStack);
		comboDistricts.setOnAction(e -> {
			if (comboDistricts.getValue() != null) {
				District dis = comboDistricts.getValue();
				populateLocationComboBox(dis);
			}
		});

		okButton.setOnAction(e -> {
			// Update martyr details with new inputs
			nameField.getText().replaceAll("[^A-Za-z ]", "");
			if (nameField.getText().isEmpty()) {
				showAlert("Please don't enter numbers", AlertType.ERROR);
				return;
			}
			try {
				String date = datePicker.getValue().getMonthValue() + "/" + datePicker.getValue().getDayOfMonth() + "/"
						+ datePicker.getValue().getYear();
				Martyr martyr = new Martyr(nameField.getText(), date, comboAges.getValue(), comboLocations.getValue(),
						comboDistricts.getValue().getName(), rbMale.isSelected() ? 'M' : 'F');
				HashEntry entry = hash.find(date);
				if (entry == null) {
					showAlert("Date is inserted to Hash Table", AlertType.INFORMATION);
					hash.insert(date).insert(martyr);
					populateDateComboBox(); // Update the ComboBox with the new date
				} else {
					if (entry.getAvlTree().find(martyr) == null) {
						entry.getAvlTree().insert(martyr);
						lblResult.setText("Inserted");
					} else {
						showAlert("Martyr is already in our Date base", AlertType.ERROR);
					}
				}
				detailsStage.close();
				tableView.refresh();
			} catch (Exception e1) {
				showAlert("Update failed: " + e1.getMessage(), AlertType.ERROR);
			}
		});

		// Close button
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e -> detailsStage.close());

		HBox buttons = new HBox(10, okButton, closeButton);
		buttons.setAlignment(Pos.CENTER);
		grid.add(buttons, 0, 5, 2, 1);

		// Set scene and stage
		Scene scene = new Scene(grid, 500, 500);
		detailsStage.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("/resource/style.css").toExternalForm());

		// Show the new window
		detailsStage.initModality(Modality.APPLICATION_MODAL); // Block input events with other windows
		detailsStage.showAndWait(); // Show and wait until it is closed
	}

	private void showUpdateDeleteDialog(Martyr martyr) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Update or Delete Martyr");
		alert.setHeaderText("Select an option for " + martyr.getName());
		alert.setContentText("Choose your option:");

		ButtonType buttonTypeUpdate = new ButtonType("Update");
		ButtonType buttonTypeDelete = new ButtonType("Delete");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeUpdate, buttonTypeDelete, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == buttonTypeUpdate) {
			openMartyrDetailsWindow(martyr);
		} else if (result.isPresent() && result.get() == buttonTypeDelete) {
			if (confirmDeletion()) {
				HashEntry entry = hash.find(martyr.getDate());
				if (entry != null && entry.getAvlTree() != null) {
					if (entry.getAvlTree().remove(martyr)) {
						martyrs.remove(martyr);
						showAlert("Martyr removed successfully.", Alert.AlertType.INFORMATION);
					} else {
						showAlert("Failed to delete the martyr. Martyr not found in AVL Tree or AVL Tree is empty.",
								Alert.AlertType.ERROR);
					}
				} else {
					showAlert("No such entry found or AVL tree is empty.", Alert.AlertType.ERROR);
				}
				tableView.refresh();

			}
		}
	}

	private void populateMartyrsTable(String date) {
		HashEntry entry = hash.find(date);
		if (entry != null && entry.getAvlTree() != null) {
			showAlert("The Tree's size: " + entry.getAvlTree().getSize() + " Height: " + entry.getAvlTree().getHeight(),
					AlertType.INFORMATION);
			tableView.getItems().clear();
			collectMartyrs(entry.getAvlTree().getRoot(), martyrs);
			tableView.setItems(martyrs);

		} else {
			tableView.setItems(martyrs); // clear table if no data
		}
	}

	private void collectMartyrs(AVLNode node, ObservableList<Martyr> list) {
		if (node != null) {
			collectMartyrs(node.getLeft(), list);
			list.add(node.getMartyr());
			collectMartyrs(node.getRight(), list);
		}
	}

	private void clearAndResetTableView() {
		tableView.getItems().clear(); // Clear existing data
		tableView.refresh(); // Refresh the view to display the changes
		// Optionally reset any filters or settings related to the TableView
	}

	private void showUpdateMartyrScreen(Stage primaryStage) {
		clearAndResetTableView();
		tableView.getItems().clear();
		setupLocationScreen(primaryStage);
		setupMartyrsTable();
		setupSearchComponents(); // Set up the search components

		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		comboDate.setVisible(true);
		comboAges.setVisible(true);
		btOkLocation.setText("Update");

		comboAges.setPromptText("Age");

		comboMartyrs = new ComboBox<>();
		hbMartyrSelection = new HBox(10, comboMartyrs);
		hbMartyrName.getChildren().clear();
		hbMartyrName.getChildren().addAll(hbMartyrSelection, tfLocationUpdate);
		hbLocationInsertButtons.getChildren().clear();
		hbLocationInsertButtons.getChildren().addAll(btOkLocation, btClearLocation);

		vbMartyr.getChildren().clear();
		Button refresh = new Button("Refresh table");
		HBox searchBox = new HBox(10, comboDate, tfLocation, refresh);
		refresh.setOnAction(e -> {
			showUpdateMartyrScreen(primaryStage);
		});
		searchBox.setAlignment(Pos.CENTER);

		vbMartyr.getChildren().addAll(searchBox, tableView);
		hbGender.setAlignment(Pos.CENTER);
		vbMartyr.setAlignment(Pos.CENTER);
		datePicker.setValue(null);
		hbMartyrName.setAlignment(Pos.CENTER);
		hbDatePickers.setAlignment(Pos.CENTER);
		hbGender.setAlignment(Pos.CENTER);

		comboDate.setPromptText("Dates");
		comboDistricts.setPromptText("Districts");
		comboLocations.setPromptText("Locations");
		comboMartyrs.setPromptText("Martyrs");

		tfLocationUpdate.clear();
		lblResult.setText("");
		rbMale.setSelected(false);
		rbFemale.setSelected(false);
		comboAges.setValue(null);
		comboDistricts.setValue(null);
		comboLocations.setValue(null);
		datePicker.setValue(null);
		tableView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1 && tableView.getSelectionModel().getSelectedItem() != null) {
				Martyr selectedMartyr = tableView.getSelectionModel().getSelectedItem();
				showUpdateDeleteDialog(selectedMartyr);
				tableView.refresh();
			}
		});

		btOkLocation.setOnAction(e -> {
			try {
				if (comboDistricts.getValue() == null) {
					showAlert("Please make sure to select District", AlertType.ERROR);
					return;
				}
				if (comboLocations.getValue() == null) {
					showAlert("Please make sure to select Location", AlertType.ERROR);
					return;
				}
				if (datePicker.getValue() == null) {
					showAlert("Please make sure to select Date", AlertType.ERROR);
					return;
				}
				if (tfLocationUpdate.getText().isEmpty()) {
					showAlert("Please make sure to Insert a name", AlertType.ERROR);
					return;
				}
				if (comboAges.getValue() == null) {
					showAlert("Please make sure to select age", AlertType.ERROR);
					return;
				}
				if (!rbMale.isSelected() && !rbFemale.isSelected()) {
					showAlert("Please make sure to select a gender", AlertType.ERROR);
					return;
				}
				String name = tfLocationUpdate.getText();
				String date = datePicker.getValue().getMonthValue() + "/" + datePicker.getValue().getDayOfMonth() + "/"
						+ datePicker.getValue().getYear();
				Martyr martyr = new Martyr(name, date, comboAges.getValue(), comboLocations.getValue(),
						comboDistricts.getValue().getName(), rbMale.isSelected() ? 'M' : 'F');
				HashEntry entry = hash.find(date);
				if (entry == null) {
					showAlert("Date is inserted to Hash Table", AlertType.INFORMATION);
					hash.insert(date).insert(martyr);
				} else {
					if (entry.getAvlTree().find(martyr) == null) {
						entry.getAvlTree().insert(martyr);
						lblResult.setText("Inserted");
					} else {
						showAlert("Martyr is already in our Date base", AlertType.ERROR);
					}
				}
			} catch (Exception e1) {
				showAlert(e1.getMessage(), AlertType.ERROR);
			}
		});

		btClearLocation.setOnAction(e -> {
			tfLocationUpdate.clear();
			lblResult.setText("");
			rbMale.setSelected(false);
			rbFemale.setSelected(false);
			comboAges.setValue(null);
			comboDistricts.setValue(null);
			comboLocations.setValue(null);
			datePicker.setValue(null);
		});
	}

	private void clearMartyrsTable() {
		tableView.getItems().clear();
	}

	private void setupSearchComponents() {
		tfLocation.setPromptText("Enter martyr's name");
		populateDateComboBox(); // Ensure this is called here to fill with dates

		tfLocation.textProperty().addListener((observable, oldValue, newValue) -> {
			filterMartyrsByNameAcrossAllDates(newValue);
			tfLocation.setPrefWidth(150);
		});

		comboDate.valueProperty().addListener((obs, oldVal, newVal) -> {
			filterMartyrsByDate(newVal);
		});

		HBox.setHgrow(tfLocation, Priority.ALWAYS); // Allows the text field to grow to fill available horizontal space
		HBox searchBox = new HBox(10, comboDate, tfLocation);
		searchBox.setAlignment(Pos.CENTER);

		vbMartyr.getChildren().add(0, searchBox);
	}

	private void filterMartyrsByNameAcrossAllDates(String partOfName) {
		ObservableList<Martyr> filteredList = FXCollections.observableArrayList();
		for (int i = 0; i < hash.getTableSize(); i++) {
			HashEntry entry = hash.getTable()[i];
			if (entry != null && entry.getStatus() == 'F') {
				collectMartyrs(entry.getAvlTree().getRoot(), filteredList, partOfName.toLowerCase());
			}
		}
		tableView.setItems(filteredList);
	}

	private void collectMartyrs(AVLNode node, ObservableList<Martyr> list, String partOfName) {
		if (node != null) {
			collectMartyrs(node.getLeft(), list, partOfName);
			if (node.getMartyr().getName().toLowerCase().contains(partOfName)) {
				list.add(node.getMartyr());
			}
			collectMartyrs(node.getRight(), list, partOfName);
		}
	}

	private void filterMartyrsByDate(String date) {
		ObservableList<Martyr> filteredList = FXCollections.observableArrayList();
		for (int i = 0; i < hash.getTableSize(); i++) {
			HashEntry entry = hash.getTable()[i];
			if (entry != null && entry.getStatus() == 'F') {
				for (Martyr martyr : collectMartyrsFromTree(entry.getAvlTree().getRoot())) {
					if (martyr.getDate().equals(date)) {
						filteredList.add(martyr);
					}
				}
			}
		}
		tableView.setItems(filteredList);
	}

	private ObservableList<Martyr> collectMartyrsFromTree(AVLNode node) {
		ObservableList<Martyr> list = FXCollections.observableArrayList();
		collectMartyrs(node, list, "");
		return list;
	}

	private void showAddMartyrScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		comboDate.setVisible(true);
		comboAges.setVisible(true);
		btOkLocation.setText("Insert");
		comboAges.setPromptText("Age");
		tfLocation.setPromptText("Old martyr name");
		tfLocationUpdate.setPromptText("New martyr name");
		comboMartyrs = new ComboBox<>();
		hbMartyrSelection = new HBox(10, comboMartyrs);
		hbMartyrName.getChildren().clear();
		hbMartyrName.getChildren().addAll(new Label("Martyr's name:"), tfLocationUpdate);
		hbLocationInsertButtons.getChildren().clear();
		hbLocationInsertButtons.getChildren().addAll(btOkLocation, btClearLocation);
		vbMartyr.getChildren().clear();

		vbMartyr.getChildren().addAll(comboDistricts, comboLocations, datePicker, hbMartyrName, comboAges, hbGender,
				hbLocationInsertButtons, lblResult);
		hbGender.setAlignment(Pos.CENTER);
		vbMartyr.setAlignment(Pos.CENTER);
		datePicker.setValue(null);
		hbMartyrName.setAlignment(Pos.CENTER);
		hbDatePickers.setAlignment(Pos.CENTER);
		hbGender.setAlignment(Pos.CENTER);
		comboDistricts.setPromptText("Districts ");
		comboLocations.setPromptText("Locations");
		comboDistricts.getItems().clear();
		comboLocations.getItems().clear();
		populateDistrict();
		populateDistrictComboBox(districtStack);
		comboDistricts.setDisable(false);
		comboLocations.setDisable(false);
		comboMartyrs.setDisable(false);
		tfLocationUpdate.clear();
		lblResult.setText("");
		rbMale.setSelected(false);
		rbFemale.setSelected(false);
		comboAges.setValue(null);
		comboDistricts.setValue(null);
		comboLocations.setValue(null);
		datePicker.setValue(null);

		comboDistricts.setOnAction(e -> {
			if (comboDistricts.getValue() != null) {
				District dis = comboDistricts.getValue();
				populateLocationComboBox(dis);
			}

		});

		btOkLocation.setOnAction(e -> {

			try {

				if (comboDistricts.getValue() == null) {
					showAlert("Please make sure to select District", AlertType.ERROR);
					return;
				}
				if (comboLocations.getValue() == null) {
					showAlert("Please make sure to select Location", AlertType.ERROR);
					return;
				}

				if (datePicker.getValue() == null) {
					showAlert("Please make sure to select Date", AlertType.ERROR);
					return;
				}
				if (tfLocationUpdate.getText().isEmpty()) {
					showAlert("Please make sure to Insert a name", AlertType.ERROR);
					return;
				}
				if (comboAges.getValue() == null) {
					showAlert("Please make sure to select age", AlertType.ERROR);
					return;
				}
				if (!rbMale.isSelected() && !rbFemale.isSelected()) {
					showAlert("Please make sure to select a gender", AlertType.ERROR);
					return;
				}
				String name = tfLocationUpdate.getText();
				String date = datePicker.getValue().getMonthValue() + "/" + datePicker.getValue().getDayOfMonth() + "/"
						+ datePicker.getValue().getYear();
				Martyr martyr = new Martyr(name, date, comboAges.getValue(), comboLocations.getValue(),
						comboDistricts.getValue().getName(), rbMale.isSelected() ? 'M' : 'F');
				HashEntry entry = hash.find(date);
				if (entry == null) {
					showAlert("Date is inserted to Hash Table", AlertType.INFORMATION);
					hash.insert(date).insert(martyr);
					lblResult.setText("Inserted");

				} else {
					if (entry.getAvlTree().find(martyr) == null) {
						entry.getAvlTree().insert(martyr);
						lblResult.setText("Inserted");
					} else {
						showAlert("Martyr is already in our Date base", AlertType.ERROR);
					}
					return;
				}

			} catch (Exception e1) {
				showAlert(e1.getMessage(), AlertType.ERROR);
			}

		});

		btClearLocation.setOnAction(e -> {
			tfLocationUpdate.clear();
			lblResult.setText("");
			rbMale.setSelected(false);
			rbFemale.setSelected(false);
			comboAges.setValue(null);
			comboDistricts.setValue(null);
			comboLocations.setValue(null);
			datePicker.setValue(null);
		});
	}

	private void populateLocationComboBox(District district) {
		comboLocations.getItems().clear(); // Clear previous items

		Stack locationStack = district.getLocationStack();
		Stack tempStack = new Stack(); // Temporary stack to preserve location stack

		// Transfer all elements to the temp stack to preserve the original order
		while (!locationStack.isEmpty()) {
			Location location = (Location) locationStack.pop();
			comboLocations.getItems().add(location.getName());
			tempStack.push(location);
		}

		// Restore the original location stack from the temporary stack
		while (!tempStack.isEmpty()) {
			locationStack.push(tempStack.pop());
		}
	}

	private void populateDistrictComboBox(Stack districtStack) {
		if (!comboDistricts.getItems().isEmpty()) {
			return;
		}
		Stack tempStack = new Stack();
		while (!districtStack.isEmpty()) {
			District district = (District) districtStack.pop();
			comboDistricts.getItems().add(district);
			tempStack.push(district);
		}

		// Restore the original stack by pushing items back from the temporary stack
		while (!tempStack.isEmpty()) {
			districtStack.push(tempStack.pop());
		}
	}

	public void populateDistrict() {
		for (int i = 0; i < hash.getTableSize(); i++) {
			HashEntry entry = hash.getTable()[i];
			if (entry != null) {
				if (entry.getStatus() == 'F') {
					treeToStack(entry);
				}
			}
		}

	}

	public Stack treeToStack(HashEntry entry) {

		AVLNode root = entry.getAvlTree().getRoot();
		inOrderTraversal(root, districtStack);
		return districtStack;
	}

	private void inOrderTraversal(AVLNode node, Stack districtStack) {
		if (node == null) {
			return;
		}

		inOrderTraversal(node.getLeft(), districtStack);

		Martyr martyr = node.getMartyr();
		String districtName = martyr.getDistrict();
		District district = districtStack.find(districtName);

		if (district == null) {
			district = new District(districtName);
			districtStack.sortedPush(district);
		}

		district.addOrUpdateLocation(martyr.getLocation());

		inOrderTraversal(node.getRight(), districtStack);
	}

	private void showDeleteDateScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		btOkDis.setText("Delete");
		hbDistrictInsert.getChildren().clear();
		populateDateComboBox();
		hbDistrictInsert.getChildren().addAll(lblDate, comboDate);
		vbDistrictInsert.getChildren().clear();
		vbDistrictInsert.getChildren().addAll(hbDistrictInsert, hbDistrictInsertButtons, lblSuccess);
		hbDistrictInsertButtons.setAlignment(Pos.CENTER);
		vbDistrictInsert.setAlignment(Pos.CENTER);
		hbDistrictInsert.setAlignment(Pos.CENTER);
		bp.setCenter(vbDistrictInsert);
		lblDate.setText("Date");
		btOkDis.setOnAction(e -> {
			boolean Confirm = confirmDeletion();
			if (Confirm) {

				try {
					if (hash.removeDate(comboDate.getValue())) {
						lblSuccess.setText("Removed");
						populateDateComboBox();
					} else {
						lblSuccess.setText("Not removed");
					}
				} catch (Exception e1) {
					showAlert("Please choose a Date", AlertType.ERROR);
				}

			}
		});
		btClearDis.setOnAction(e -> {
			comboDate.setValue(null);
			lblSuccess.setText("");
		});
	}

	private void showUpdateDateScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		btOkDis.setText("Update");
		hbDistrictInsert.getChildren().clear();
		populateDateComboBox();
		hbDistrictInsert.getChildren().addAll(lblDate, comboDate, datePicker);
		vbDistrictInsert.getChildren().clear();
		vbDistrictInsert.getChildren().addAll(hbDistrictInsert, hbDistrictInsertButtons, lblSuccess);
		hbDistrictInsertButtons.setAlignment(Pos.CENTER);
		vbDistrictInsert.setAlignment(Pos.CENTER);
		hbDistrictInsert.setAlignment(Pos.CENTER);
		bp.setCenter(vbDistrictInsert);
		lblDate.setText("Date");
		btOkDis.setOnAction(e -> {
			boolean Confirm = confirmUpdate();
			if (Confirm) {
				try {
					String date = datePicker.getValue().getMonthValue() + "/" + datePicker.getValue().getDayOfMonth()
							+ "/" + datePicker.getValue().getYear();
					if (hash.find(date) == null) {
						if (hash.updateDate(comboDate.getValue(), date)) {
							lblSuccess.setText("Updated");
							populateDateComboBox();
						} else {
							lblSuccess.setText("Not updated");
						}
					} else {
						lblSuccess.setText("Not updated");
					}
				} catch (Exception e1) {
					showAlert("Please re-check selected dates", AlertType.ERROR);
				}
			}
		});
		btClearDis.setOnAction(e -> {
			comboDate.setValue(null);
			datePicker.setValue(null);
			lblSuccess.setText("");
		});
	}

	private void populateDateComboBox() {
		comboDate.getItems().clear(); // Clear existing items
		if (hash != null && hash.getTable() != null) {
			for (int i = 0; i < hash.getTableSize(); i++) {
				HashEntry entry = hash.getTable()[i];
				if (entry != null && entry.getStatus() == 'F') {
					comboDate.getItems().add(entry.getDate());
				}
			}
		}
	}

	private void setupLocationScreen(Stage primaryStage) {
		scene.setRoot(bpLocation);
		bpLocation.setTop(menuBar);
		bpLocation.setCenter(vbMartyr);
		comboDate.getItems().clear();
		populateDateComboBox();
	}

	private void showAddDateScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		vbDistrictInsert.setVisible(true);
		btOkDis.setText("Insert");
		tfDate.clear();
		tfDistrictUpdate.clear();
		hbDistrictInsert.getChildren().clear();
		hbDistrictInsert.getChildren().addAll(lblDate, datePicker);
		vbDistrictInsert.getChildren().clear();
		vbDistrictInsert.getChildren().addAll(hbDistrictInsert, hbDistrictInsertButtons, lblSuccess);
		hbDistrictInsertButtons.setAlignment(Pos.CENTER);
		vbDistrictInsert.setAlignment(Pos.CENTER);
		hbDistrictInsert.setAlignment(Pos.CENTER);
		lblDate.setText("Date ");
		tfDate.setPromptText("New Date");
		lblSuccess.setText("");
		bp.setCenter(vbDistrictInsert);
		btOkDis.setOnAction(e -> {
			try {
				String date = datePicker.getValue().getMonthValue() + "/" + datePicker.getValue().getDayOfMonth() + "/"
						+ datePicker.getValue().getYear();
				LocalDate selectedDate = datePicker.getValue();
				LocalDate today = LocalDate.now();
				if (selectedDate.isAfter(today)) {
					showAlert("You can't add a date after: " + today, AlertType.ERROR);
					return;
				}
				if (hash.find(date) == null) {
					hash.insert(date);
					lblSuccess.setText("Date inserted successfuly");
					return;
				} else {
					lblSuccess.setText("Date is already inserted");
				}
			} catch (Exception e1) {
				showAlert("Pick a Date please", AlertType.ERROR);
			}

		});
		btClearDis.setOnAction(e -> {
			datePicker.setValue(null);
			lblSuccess.setText(" ");

		});

	}

	private void saveFile(Stage primaryStage) {
		// Options for the choice dialog
		List<String> choices = Arrays.asList("All Dates", "Choose a Date");

		ChoiceDialog<String> dialog = new ChoiceDialog<>("All Dates", choices);
		dialog.setTitle("Save Data");
		dialog.setHeaderText("Select save option");
		dialog.setContentText("Choose your option:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(choice -> {
			if (choice.equals("All Dates")) {
				saveAllData();
			} else {
				selectDateAndSave();
			}
		});
	}

	private void saveAllData() {
		FileChooser fChooser = new FileChooser();
		fChooser.setInitialDirectory(new File("C:\\Users\\ASUS\\eclipse-workspace\\StructureProjects"));
		File file = fChooser.showSaveDialog(null);

		if (file != null) {
			try (PrintWriter write = new PrintWriter(file)) {
				write.append("Name,Date,Age,Location,District,Gender\n");
				for (Martyr martyr : martyrs) {
					write.append(martyr.getName() + "," + martyr.getDate() + "," + martyr.getAge() + ","
							+ martyr.getLocation() + "," + martyr.getDistrict() + "," + martyr.getGender() + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void selectDateAndSave() {
		// Create a dialog with a ComboBox for date selection
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Select Date");
		dialog.setHeaderText("Choose a date to save the data for:");

		// Set the button types.
		ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setItems(FXCollections.observableArrayList(collectAllDates()));
		comboBox.getSelectionModel().selectFirst();

		dialog.getDialogPane().setContent(comboBox);

		// Convert the result to a date when the save button is clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == saveButtonType) {
				return comboBox.getSelectionModel().getSelectedItem();
			}
			return null;
		});

		Optional<String> result = dialog.showAndWait();
		result.ifPresent(date -> saveDataForDate(date));
	}

	private void saveDataForDate(String date) {
		FileChooser fChooser = new FileChooser();
		fChooser.setInitialDirectory(new File("C:\\Users\\ASUS\\eclipse-workspace\\StructureProjects"));
		File file = fChooser.showSaveDialog(null);

		if (file != null) {
			try (PrintWriter write = new PrintWriter(file)) {
				write.append("Name,Date,Age,Location,District,Gender\n");
				for (Martyr martyr : martyrs) {
					if (martyr.getDate().equals(date)) {
						write.append(martyr.getName() + "," + martyr.getDate() + "," + martyr.getAge() + ","
								+ martyr.getLocation() + "," + martyr.getDistrict() + "," + martyr.getGender() + "\n");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Set<String> collectAllDates() {
		Set<String> dates = new HashSet<>();
		for (Martyr martyr : martyrs) {
			dates.add(martyr.getDate());
		}
		return dates;
	}

	private void readFile(Stage primaryStage) {
		fChooser = new FileChooser();
		fChooser.setInitialDirectory(new File("C:\\Users\\ASUS\\eclipse-workspace\\StructureProjects"));
		f = fChooser.showOpenDialog(null);

		if (f != null) {
			try (Scanner read = new Scanner(f)) {
				read.nextLine(); // Skip the first line; header
				while (read.hasNextLine()) {
					String line = read.nextLine();
					String[] values = line.split(",");
					if (values.length < 6) { // Ensure that all required fields are present
						continue;
					}
					byte age = values[2].isEmpty() ? (byte) -1 : Byte.parseByte(values[2]);
					AVL tree = hash.insert(values[1]); // Insert the date and get the AVL tree (either new or existing)
					if (tree != null) {
						Martyr martyr = new Martyr(values[0], values[1], age, values[3], values[4],
								values[5].charAt(0));
						tree.insert(martyr);
						martyrs.add(martyr);

					} else {
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		populateDateComboBox();
	}

	private void showAlert(String message, Alert.AlertType type) {
		Alert alert = new Alert(type, message, ButtonType.OK);
		alert.showAndWait();
	}

//	private boolean isNumeric(String str) {
//		try {
//			Integer.parseInt(str);
//			return true;
//		} catch (NumberFormatException e) {
//			return false;
//		}
//	}

	public static void main(String[] args) {
		launch(args);
	}

}
