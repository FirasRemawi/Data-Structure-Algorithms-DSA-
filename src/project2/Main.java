package project2;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Optional;
import java.util.Scanner;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;

public class Main extends Application {
	private FileChooser fChooser;
	private File f;
	private District dis;
	private MenuBar menuBar;
	private Menu menu1, menu2, menu3, menu4, menu5;
	private MenuItem item1, item21, item22, updateLocation, navigateLocations, addMartyr, updateMartyr, searchMartyr,
			addLocation, deleteLocation, addDistrict, updateDistrict, deleteDistrict, navigateDistricts, itemSave;
	private BorderPane bp, bpLocation;
	private Label lblDistrict, lblSuccess, lblLocation, lblResult;
	private TextField tfDistrict, tfDistrictUpdate, tfLocation, tfLocationUpdate, tfFirst, tfMiddle, tfSurname;
	private VBox vbDistrictInsert, vbDistrictNavigation, vbLocationInsert, vbLocationNavigation, vbMartyr, vbPrint,
			vbDistrictDate;
	private HBox hbDistrictInsert, hbDistrictInsertButtons, hbLocationInsert, hbLocationInsertButtons, hbNavigation,
			hbNavigationLocation, hbDatePickers, hbMartyrName, hbGender;;
	private Button btOkDis, btClearDis, btPrev, btNext, submitButton, btOkLocation, btClearLocation, btNextLocation,
			btSelect, btPrint;
	private TextArea ta, taLocation;
	private RadioButton rbMale, rbFemale;
	private ToggleGroup tg;
	private Scene scene;
	private Image image;
	private ImageView imageView;
	private ComboBox<Integer> comboBoxDay, comboBoxYear;
	private ComboBox<Byte> comboAges;
	private ComboBox<String> comboDistrics, comboLocations, comboBoxMonth;
	private Labeled lblDate;
	private DistrictTree districtTree;
	private MenuItem navigateMartyrs;
	private DataManager manger;
	private Button btnLoad;
	private boolean lastStep = false;
	private String lastStepvalue;
	private DateStatistics date;
	private DistrictNode disNode;
	private DatePicker datePicker = new DatePicker();
	private HBox hbMartyrSelection;
	private ComboBox<String> comboMartyrs;
	private LinkedList matchedMartyrs;
	private TableView<Martyr> tableView;
	private TableColumn<Martyr, String> nameColumn, dateColumn, locationColumn, genderColumn, districtColumn, ageColumn;
	private Location nextLocation;
	private Location prevLocation;
	private LocationNode locationNode;
	private Date firstDate;
	private String lastStepDisvalue;
	private Date prevDate;
	private Date nextDate;
	private LinkedList matchedLinkedList;
	String partOfName;

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

	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) {
		// Initialize the data structure
		districtTree = new DistrictTree();

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
		menu3 = new Menu("District screen");
		addDistrict = new MenuItem("Add a new District");
		updateDistrict = new MenuItem("Update existing District");
		deleteDistrict = new MenuItem("Delete District");
		navigateDistricts = new MenuItem("Navigate Districts");
		menu3.getItems().addAll(addDistrict, updateDistrict, deleteDistrict, navigateDistricts);

		// Location Menu
		menu4 = new Menu("Location's screen");
		addLocation = new MenuItem("Add a new Location");
		updateLocation = new MenuItem("Update an existing Location");
		deleteLocation = new MenuItem("Delete Location");
		navigateLocations = new MenuItem("Navigate Locations");
		menu4.getItems().addAll(addLocation, updateLocation, deleteLocation, navigateLocations);

		menu5 = new Menu("Martyr's screen");
		addMartyr = new MenuItem("Add a new Martyr");
		updateMartyr = new MenuItem("Update/Delete martyr");
		searchMartyr = new MenuItem("Search for a martyr");
		navigateMartyrs = new MenuItem("Navigate Martyrs");
		menu5.getItems().addAll(addMartyr, updateMartyr, searchMartyr, navigateMartyrs);

		menuBar.getMenus().addAll(menu1, menu3, menu4, menu5, menu2);
		menuBar.setPadding(new Insets(10, 10, 10, 10));
		try {
			// Linking MenuItem actions to methods
			addDistrict.setOnAction(e -> showAddDistrictScreen(primaryStage));
			updateDistrict.setOnAction(e -> showUpdateDistrictScreen(primaryStage));
			deleteDistrict.setOnAction(e -> showDeleteDistrictScreen(primaryStage));
			navigateDistricts.setOnAction(e -> showNavigateDistrictsScreen(primaryStage));
			itemSave.setOnAction(e -> saveDistrictToFile());

			// Set actions for menu items related to locations and martyrs
			addLocation.setOnAction(e -> showAddLocationScreen(primaryStage));
			updateLocation.setOnAction(e -> showUpdateLocationScreen(primaryStage));
			deleteLocation.setOnAction(e -> showDeleteLocationScreen(primaryStage));
			navigateLocations.setOnAction(e -> showNavigateLocationsScreen(primaryStage));
			addMartyr.setOnAction(e -> showAddMartyrScreen(primaryStage));
			updateMartyr.setOnAction(e -> showUpdateMartyrScreen(primaryStage));
			searchMartyr.setOnAction(e -> showSearchMartyrScreen(primaryStage));
			navigateMartyrs.setOnAction(e -> showNavigateMartyrScreen(primaryStage));
		} catch (Exception e) {

		}

		// Initialize RadioButtons and ToggleGroup
		tg = new ToggleGroup();

		rbMale = new RadioButton("Male");
		rbFemale = new RadioButton("Female");
		rbMale.setToggleGroup(tg);
		rbFemale.setToggleGroup(tg);

		// Initialize Labels
		lblDistrict = new Label("District's name");
		lblSuccess = new Label("");
		lblDate = new Label();
		lblLocation = new Label("Location's Name");
		lblResult = new Label();

		// Initialize TextFields
		tfDistrict = new TextField();
		tfDistrictUpdate = new TextField();
		ta = new TextArea();
		ta.setWrapText(true);
		tfLocation = new TextField();
		tfLocationUpdate = new TextField();
		taLocation = new TextArea();
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
		btNextLocation = new Button("Next⏭️");
		btOkLocation = new Button("Insert");
		btClearLocation = new Button("Clear");
		btSelect = new Button("Select District");
		btnLoad = new Button("Load");

		// Initialize ComboBoxes
		comboBoxDay = new ComboBox<>();
		comboBoxMonth = new ComboBox<>();
		comboBoxYear = new ComboBox<>();
		comboDistrics = new ComboBox<String>();
		comboLocations = new ComboBox<String>();
		comboMartyrs = new ComboBox<String>();
		comboAges = new ComboBox<>();
		comboDistrics.setOnAction(e -> {
			if (comboDistrics.getValue() != null) {
				District selectedDistrict = new District(comboDistrics.getValue());
				DistrictNode districtNode = districtTree.find(selectedDistrict);
				if (districtNode != null) {
					populateLocationComboBox(districtNode);
				} else {
					comboLocations.getItems().clear();
					comboLocations.setDisable(true);
				}
			}
		});
		for (Byte age = 1; age <= 60; age++) {
			comboAges.getItems().add(age);
		}
		// Initialize Layout Panes
		hbDistrictInsert = new HBox(16, lblDistrict, tfDistrict);
		hbDistrictInsertButtons = new HBox(16, btOkDis, btClearDis);
		hbNavigation = new HBox(16, btPrev, btNext);
		hbDatePickers = new HBox(10, new Label("Month:"), comboBoxMonth, new Label("Day:"), comboBoxDay,
				new Label("Year:"), comboBoxYear);
		hbDatePickers.setAlignment(Pos.CENTER);

		hbLocationInsert = new HBox(16, lblLocation, tfLocation);
		hbLocationInsertButtons = new HBox(16, btOkLocation, btClearLocation);
		hbNavigationLocation = new HBox(16, btNextLocation, btSelect);

		hbMartyrName = new HBox(16);
		hbGender = new HBox(rbMale, rbFemale);
		// VBox for different sections
		vbDistrictInsert = new VBox(16, hbDistrictInsert, hbDistrictInsertButtons, lblSuccess);
		vbDistrictNavigation = new VBox(16, ta, hbNavigation);
		vbDistrictDate = new VBox(16, hbDatePickers, submitButton, lblDate);
		vbPrint = new VBox(16, comboDistrics, btPrint);

		vbLocationInsert = new VBox(16, hbLocationInsert, hbLocationInsertButtons, lblResult);
		vbLocationNavigation = new VBox(16, ta, hbNavigationLocation);

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
		vbLocationInsert.setAlignment(Pos.CENTER);
		vbLocationNavigation.setAlignment(Pos.CENTER);
		taLocation.setEditable(false);
		taLocation.setStyle("-fx-alignment: center;\r\n" + "-fx-font-size: 18;-fx-font-weight: bold;");
		tableView = new TableView<>();
		nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setPrefWidth(200);

		dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		locationColumn = new TableColumn<>("Location");
		locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

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

		tableView.getColumns().addAll(nameColumn, dateColumn, locationColumn, districtColumn, ageColumn, genderColumn);

		// Set BorderPane as the root and apply CSS stylesheet
		bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(imageView);
		bpLocation = new BorderPane();
		scene = new Scene(bp, 900, 600);
		scene.getStylesheets().add(getClass().getResource("/resource/style.css").toExternalForm());

		// Finalize the stage setup
		primaryStage.setScene(scene);
		primaryStage.setTitle("Main Screen");
		item1.setOnAction(e -> readFile(primaryStage));

		primaryStage.show();

	}

	private void saveDistrictToFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save District Data");
		File file = fileChooser.showSaveDialog(new Stage());
		if (file != null) {
			districtTree.getRoot().writeOnFile(file.getAbsolutePath());
		}
	}

	private void showNavigateMartyrScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		ta.clear();
		vbDistrictNavigation.getChildren().clear();
		vbDistrictNavigation.getChildren().addAll(comboDistrics, comboLocations, lblResult, tableView, hbNavigation);
		hbNavigation.setAlignment(Pos.CENTER);
		bp.setCenter(vbDistrictNavigation);
		tableView.setPrefHeight(200);
		manger = new DataManager(districtTree);

		ensureDistrictComboBoxPopulation();

		if (lastStep && lastStepvalue != null && lastStepDisvalue != null) {
			comboDistrics.setValue(lastStepDisvalue);
			comboLocations.setValue(lastStepvalue);
			disNode = districtTree.find(new District(lastStepDisvalue));
			manger.initializeLocationTraversal(districtTree.find(disNode.getElement()));
			nextLocation = manger.nextLocation();
			locationNode = disNode.getHead().find(new Location(lastStepvalue));
			manger.initializeDateTraversal(locationNode);
			firstDate = manger.nextDate();
			if (firstDate != null) {
				showDate(firstDate);
			}
			btPrev.setOnAction(e1 -> navigateToPreviousDate());
			btNext.setOnAction(e1 -> navigateToNextDate());
			lastStepvalue = null;
			lastStepDisvalue = null;
		} else {
			comboDistrics.setValue(null);
			comboLocations.setValue(null);
		}

		comboDistrics.setOnAction(e -> {
			String selectedDistrict = comboDistrics.getValue();
			if (selectedDistrict != null) {
				disNode = districtTree.find(new District(selectedDistrict));
				if (disNode != null) {
					populateLocationComboBox(disNode);
					comboLocations.setOnAction(e1 -> {
						String selectedLocation = comboLocations.getValue();
						if (selectedLocation != null) {
							LocationNode locNode = disNode.getHead().find(new Location(selectedLocation));
							if (locNode != null) {
								manger.initializeDateTraversal(locNode);
								firstDate = manger.nextDate();
								if (firstDate != null) {
									ObservableList<Martyr> matched = FXCollections.observableArrayList();
									disNode = districtTree.find(new District(comboDistrics.getValue()));
									locationNode = disNode.getHead().find(new Location(comboLocations.getValue()));
									matchedLinkedList = searchMartyrs(disNode, locationNode, firstDate);
									MartyrNode current = matchedLinkedList.getFront();
									while (current != null) {
										matched.add((Martyr) current.getElement());
										current = current.getNext();
									}
									tableView.setItems(matched);
									showDate(firstDate);
								}
								btPrev.setOnAction(e2 -> navigateToPreviousDate());
								btNext.setOnAction(e2 -> navigateToNextDate());
							}
						}
					});
				}
			}
		});
	}

	private void navigateToPreviousDate() {
		prevDate = manger.previousDate();
		if (prevDate != null) {
			ObservableList<Martyr> matched = FXCollections.observableArrayList();
			disNode = districtTree.find(new District(comboDistrics.getValue()));
			locationNode = disNode.getHead().find(new Location(comboLocations.getValue()));
			matchedLinkedList = searchMartyrs(disNode, locationNode, prevDate);
			MartyrNode current = matchedLinkedList.getFront();
			while (current != null) {
				matched.add((Martyr) current.getElement());
				current = current.getNext();
			}
			tableView.setItems(matched);
			showDate(prevDate);
		}
	}

	private void showDate(Date date) {
		if (date != null) {
			lblResult.setText(manger.getStr() + "");
			matchedLinkedList = searchMartyrs(disNode, locationNode, prevDate);
			MartyrNode current = matchedLinkedList.getFront();
			while (current != null) {
				current = current.getNext();
			}
		}
	}

	private void navigateToNextDate() {
		nextDate = manger.nextDate();
		if (nextDate != null) {
			ObservableList<Martyr> matched = FXCollections.observableArrayList();
			disNode = districtTree.find(new District(comboDistrics.getValue()));
			locationNode = disNode.getHead().find(new Location(comboLocations.getValue()));
			matchedLinkedList = searchMartyrs(disNode, locationNode, nextDate);
			MartyrNode current = matchedLinkedList.getFront();
			while (current != null) {
				matched.add((Martyr) current.getElement());
				current = current.getNext();
			}
			tableView.setItems(matched);
			showDate(nextDate);
		}
	}

	public LinkedList searchMartyrs(String partOfName) {
		matchedMartyrs = new LinkedList();
		searchMartyrsInDistrict(districtTree.getRoot(), partOfName.toLowerCase(), matchedMartyrs);
		return matchedMartyrs;
	}

	public LinkedList searchMartyrs(DistrictNode districtNode, LocationNode locationNode, Date date) {
		matchedMartyrs = new LinkedList();
		if (districtNode == null)
			return matchedMartyrs;
		if (locationNode == null)
			return matchedMartyrs;
		if (date == null)
			return matchedMartyrs;

		DateNode dateNode = locationNode.getHead().find(date);
		if (dateNode == null)
			return matchedLinkedList;
		MartyrNode martyrNode = dateNode.getMartyrs().getFront();
		while (martyrNode != null) {
			Martyr martyr = (Martyr) martyrNode.getElement();
			matchedMartyrs.addMartyrNameSorted(martyr);
			martyrNode = martyrNode.getNext();
		}
		return matchedMartyrs;
	}

	private void searchMartyrsInDistrict(DistrictNode node, String partOfName, LinkedList matchedMartyrs) {
		if (node == null) {
			return;
		}

		searchMartyrsInLocation(node.getHead().getRoot(), partOfName, matchedMartyrs);
		searchMartyrsInDistrict(node.getLeft(), partOfName, matchedMartyrs);
		searchMartyrsInDistrict(node.getRight(), partOfName, matchedMartyrs);
	}

	private void searchMartyrsInLocation(LocationNode location, String partOfName, LinkedList matchedMartyrs) {
		if (location == null) {
			return;
		}

		searchMartyrsInLocation(location.getLeft(), partOfName, matchedMartyrs);
		searchMartyrsInLocation(location.getRight(), partOfName, matchedMartyrs);
		searchMartyrsOnDate(location.getHead().getRoot(), partOfName, matchedMartyrs);
	}

	// For searching a part on name
	private void searchMartyrsOnDate(DateNode dateNode, String partOfName, LinkedList matchedMartyrs) {
		if (dateNode == null) {
			return;
		}

		MartyrNode martyrNode = dateNode.getMartyrs().getFront();
		while (martyrNode != null) {
			Martyr martyr = (Martyr) martyrNode.getElement();
			if (martyr.getName().toLowerCase().contains(partOfName)) {
				matchedMartyrs.addMartyrNameSorted(martyr);
			}
			martyrNode = martyrNode.getNext();
		}

		searchMartyrsOnDate(dateNode.getLeft(), partOfName, matchedMartyrs);
		searchMartyrsOnDate(dateNode.getRight(), partOfName, matchedMartyrs);
	}

	private void showSearchMartyrScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		comboDistrics.setVisible(true);
		datePicker.setValue(null);

		btOkLocation.setText("Search");
		tfLocation.setPromptText("martyr partial name");
		hbMartyrName.getChildren().clear();
		hbMartyrName.getChildren().addAll(new Label("Martyr's name:"), tfLocation);
		hbLocationInsertButtons.getChildren().clear();
		vbLocationInsert.getChildren().clear();
		hbLocationInsertButtons.getChildren().addAll(btOkLocation, btClearLocation);
		vbMartyr.getChildren().clear();
		vbMartyr.getChildren().addAll(hbMartyrName, hbLocationInsertButtons, tableView);
		comboDistrics.setPromptText("Districts");
		comboLocations.setDisable(true);
		vbMartyr.setAlignment(Pos.CENTER);
		hbMartyrName.setAlignment(Pos.CENTER);
		hbLocationInsertButtons.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbMartyr);
		comboDistrics.setOnAction(e -> {
			comboLocations.setDisable(true);
			comboLocations.getItems().clear();
			if (comboDistrics.getValue() != null)
				populateLocationComboBox(districtTree.find(new District(comboDistrics.getValue())));

		});
		btOkLocation.setOnAction(e -> {
			if (tfLocation.getText().isEmpty()) {
				lblResult.setText("Please make sure to insert the name");
				return;
			}
			partOfName = tfLocation.getText();
			ObservableList<Martyr> matched = FXCollections.observableArrayList();
			LinkedList matchedLinkedList = searchMartyrs(tfLocation.getText());
			MartyrNode current = matchedLinkedList.getFront();
			while (current != null) {
				matched.add((Martyr) current.getElement());
				current = current.getNext();
			}
			tableView.setItems(matched);

		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
		});

	}

	private void showUpdateMartyrScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		comboDistrics.setVisible(true);
		comboAges.setVisible(true);
		btOkLocation.setText("Update");
		comboAges.setPromptText("Age");
		tfLocation.setPromptText("Old martyr name");
		tfLocationUpdate.setPromptText("New martyr name");
		comboMartyrs = new ComboBox<>();
		hbMartyrSelection = new HBox(10, comboMartyrs);
		hbMartyrName.getChildren().clear();
		hbMartyrName.getChildren().addAll(new Label("Martyr's name:"), hbMartyrSelection, tfLocationUpdate);
		hbLocationInsertButtons.getChildren().clear();
		hbLocationInsertButtons.getChildren().addAll(btOkLocation, btClearLocation);
		vbMartyr.getChildren().clear();
		vbLocationInsert.getChildren().clear();
		populateDistrictComboBox();
		vbMartyr.getChildren().addAll(comboDistrics, comboLocations, datePicker, hbMartyrName, comboAges, hbGender,
				hbLocationInsertButtons, lblResult);
		hbGender.setAlignment(Pos.CENTER);
		comboDistrics.setPromptText("Districts");
		comboLocations.setDisable(true);
		vbMartyr.setAlignment(Pos.CENTER);
		datePicker.setValue(null);
		hbMartyrName.setAlignment(Pos.CENTER);
		hbDatePickers.setAlignment(Pos.CENTER);
		hbGender.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbMartyr);

		comboDistrics.setOnAction(e -> {
			comboLocations.setDisable(true);
			comboLocations.getItems().clear();
			if (comboDistrics.getValue() != null) {
				populateLocationComboBox(districtTree.find(new District(comboDistrics.getValue())));
			}
		});

		comboLocations.setOnAction(e -> {
			if (comboLocations.getValue() != null) {
				datePicker.setValue(null);
			}
		});

		datePicker.setOnAction(e -> {
			if (comboLocations.getValue() != null && comboDistrics.getValue() != null
					&& datePicker.getValue() != null) {
				String date = datePicker.getValue().getMonthValue() + "/" + datePicker.getValue().getDayOfMonth() + "/"
						+ datePicker.getValue().getYear();
				populateMartyrsComboBox(comboDistrics.getValue(), comboLocations.getValue(), date);
			}
		});

		btOkLocation.setOnAction(e -> {
			String selectedMartyr = comboMartyrs.getValue();
			if (selectedMartyr != null && !selectedMartyr.isEmpty()) {
				if (tfLocationUpdate.getText().isEmpty()) {
					boolean deleteConfirmed = confirmDeletion();
					if (deleteConfirmed) {
						String date = datePicker.getValue().getMonthValue() + "/"
								+ datePicker.getValue().getDayOfMonth() + "/" + datePicker.getValue().getYear();
						if (deleteMartyr(comboDistrics.getValue(), comboLocations.getValue(), date, selectedMartyr)) {
							lblResult.setText("Martyr deleted successfully.");
							populateMartyrsComboBox(comboDistrics.getValue(), comboLocations.getValue(), date);
						} else {
							lblResult.setText("Failed to delete martyr.");
						}
						return; // Exit the event handler
					}
				}
				if (comboAges.getValue() == null) {
					lblResult.setText("Please make sure to select age");
					return;
				}
				if (comboDistrics.getValue() == null || comboDistrics.getValue().equalsIgnoreCase("Districts")) {
					lblResult.setText("Please make sure to select a District");
					return;
				}
				if (comboLocations.getValue() == null) {
					lblResult.setText("Please make sure to select a Location");
					return;
				}
				if (!rbMale.isSelected() && !rbFemale.isSelected()) {
					lblResult.setText("Please make sure to select a gender");
					return;
				}
				String name = tfLocationUpdate.getText();
				String date = datePicker.getValue().getMonthValue() + "/" + datePicker.getValue().getDayOfMonth() + "/"
						+ datePicker.getValue().getYear();
				Martyr martyr = new Martyr(name, date, comboAges.getValue(), comboLocations.getValue(),
						comboDistrics.getValue(), rbMale.isSelected() ? 'M' : 'F');
				if (comboLocations.getValue() != null && comboDistrics.getValue() != null
						&& datePicker.getValue() != null && comboMartyrs.getValue() != null) {
					boolean updateConfirm = confirmUpdate();
					if (updateConfirm) {
						if (updateMartyr(comboDistrics.getValue(), comboLocations.getValue(), comboMartyrs.getValue(),
								martyr)) {
							populateMartyrsComboBox(comboDistrics.getValue(), comboLocations.getValue(), date);
							lblResult.setText("Update went good");
						} else {
							lblResult.setText("Update went wrong");
						}
					}
				}
			}
		});

		btClearLocation.setOnAction(e -> {
			tfLocationUpdate.clear();
			lblResult.setText("");
			rbMale.setSelected(false);
			rbFemale.setSelected(false);
			comboAges.setValue(null);
		});
	}

	private boolean deleteMartyr(String districtName, String locationName, String date, String oldMartyrName) {

		DistrictNode districtNode = districtTree.find(new District(districtName));
		if (districtNode == null) {
			return false;
		}

		LocationTree locationTree = districtNode.getHead();
		LocationNode locationNode = locationTree.find(new Location(locationName));
		if (locationNode == null) {
			return false;
		}

		DateTree martyrDateTree = locationNode.getHead();
		Date martDate = new Date(date);
		DateNode martDateNode = martyrDateTree.find(martDate);

		if (martDateNode == null) {
			return false;
		}

		LinkedList martyrList = martDateNode.getHead();
		MartyrNode martNode = martyrList.findMartyrNode(oldMartyrName);

		if (martNode == null) {
			return false;
		} else {

			martyrList.removeMartyr(oldMartyrName);
		}
		return true;
	}

	private boolean updateMartyr(String districtName, String locationName, String oldMartyrName, Martyr newMartyr) {
		DistrictNode districtNode = districtTree.find(new District(districtName));
		if (districtNode == null) {
			return false;
		}

		LocationTree locationTree = districtNode.getHead();
		LocationNode locationNode = locationTree.find(new Location(locationName));
		if (locationNode == null) {
			return false;
		}

		DateTree martyrDateTree = locationNode.getHead();
		Date martDate = new Date(newMartyr.getDate());
		DateNode martDateNode = martyrDateTree.find(martDate);

		if (martDateNode == null) {
			// Date not found, create new date node and martyr list
			martDateNode = new DateNode(martDate);
			martyrDateTree.insert(martDateNode.getDate());
			martDateNode.setHead(martDate.getMartyrList());
		}

		LinkedList martyrList = martDateNode.getHead();
		MartyrNode martNode = martyrList.findMartyrNode(oldMartyrName);

		if (martNode != null) {
			// Martyr found, update it
			martNode.setElement(newMartyr);
		} else {
			// Martyr not found, add new
			martyrList.addMartyrSorted(newMartyr);
		}
		return true;
	}

	private void populateMartyrsComboBox(String districtName, String locationName, String date) {
		if (comboMartyrs.getItems() != null)
			comboMartyrs.getItems().clear();

		DistrictNode districtNode = districtTree.find(new District(districtName));
		if (districtNode != null) {
			LocationNode locationNode = districtNode.getHead().find(new Location(locationName));
			if (locationNode != null) {
				DateNode dateNode = locationNode.getHead().find(new Date(date));
				if (dateNode != null) {
					LinkedList martyrList = dateNode.getMartyrs();
					for (MartyrNode martyrNode = martyrList.getFront(); martyrNode != null; martyrNode = martyrNode
							.getNext()) {
						comboMartyrs.getItems().add(((Martyr) martyrNode.getElement()).getName());
					}
					if (!comboMartyrs.getItems().isEmpty()) {
						comboMartyrs.setValue(comboMartyrs.getItems().get(0)); // Optionally set the first item as
																				// selected
					}
				}
			}
		}

		if (comboMartyrs.getItems().isEmpty()) {
			comboMartyrs.setDisable(true); // Disable if no martyrs are available
		} else {
			comboMartyrs.setDisable(false);
		}
	}

	private void showAddMartyrScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		comboDistrics.setVisible(true);
		comboAges.setVisible(true);
		btOkLocation.setText("Insert");
		comboAges.setPromptText("Age");
		hbMartyrName.getChildren().clear();
		hbMartyrName.getChildren().addAll(new Label("Martyr's name:"), tfFirst, tfMiddle, tfSurname);
		vbMartyr.getChildren().clear();
		hbLocationInsertButtons.getChildren().clear();
		vbLocationInsert.getChildren().clear();
		hbLocationInsertButtons.getChildren().addAll(btOkLocation, btClearLocation);
		vbMartyr.getChildren().addAll(hbMartyrName, datePicker, comboAges, comboDistrics, comboLocations, hbGender,
				hbLocationInsertButtons, lblResult);
		hbGender.setAlignment(Pos.CENTER);
		comboDistrics.setPromptText("Districts");
		comboLocations.setDisable(true);
		vbMartyr.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbMartyr);
		comboDistrics.setOnAction(e -> {
			comboLocations.setDisable(true);
			comboLocations.getItems().clear();
			if (comboDistrics.getValue() != null) {
				dis = new District(comboDistrics.getValue());
				disNode = districtTree.find(dis);
				populateLocationComboBox(disNode);
			}

		});
		btOkLocation.setOnAction(e -> {
			if (tfFirst.getText().isEmpty() || tfMiddle.getText().isEmpty() || tfSurname.getText().isEmpty()) {
				lblResult.setText("Please make sure to insert all names");
				return;
			}

			if (comboAges.getValue() == null) {
				lblResult.setText("Please make sure to select age");
				return;
			}
			if (comboDistrics.getValue() == null || comboDistrics.getValue().equalsIgnoreCase("Districts")) {
				lblResult.setText("Please make sure to select a District");
				return;
			}
			if (comboLocations.getValue() == null) {
				lblResult.setText("Please make sure to select a Location");
				return;
			}
			if (!rbMale.isSelected()) {
				lblResult.setText("Please make sure to select a gender");
				if (!rbFemale.isSelected()) {
					lblResult.setText("Select a gender");
					return;
				}

			}
			String name = tfFirst.getText() + " " + tfMiddle.getText() + " " + tfSurname.getText();
			String date = datePicker.getValue().getMonthValue() + "/" + datePicker.getValue().getDayOfMonth() + "/"
					+ datePicker.getValue().getYear();
			Martyr martyr = new Martyr(name, date, comboAges.getValue(), comboLocations.getValue(),
					comboDistrics.getValue(), 'M');
			if (rbFemale.isSelected())
				martyr.setGender('F');
			if (insertMartyr(martyr.getDistrict(), martyr.getLocation(), martyr)) {
				populateLocationComboBox(districtTree.find(new District(martyr.getDistrict())));

				lblResult.setText("Inserted");
			}

			else
				lblResult.setText("Not inserted");
			return;

		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
		});

	}

	private boolean insertMartyr(String districtName, String locationName, Martyr martyr) {
		// Find or create the district node
		DistrictNode districtNode = districtTree.find(new District(districtName));
		if (districtNode == null) {
			districtNode = new DistrictNode(new District(districtName));
			districtTree.insert(districtNode.getElement());
		}

		// Find or create the location node within the district
		LocationTree locationTree = districtNode.getHead();
		LocationNode locationNode = locationTree.find(new Location(locationName));
		if (locationNode == null) {
			locationNode = new LocationNode(new Location(locationName));
			locationTree.insert(locationNode.getElement());
		}

		DateTree martyrDateTree = locationNode.getHead();
		if (martyrDateTree == null) {
			martyrDateTree = new DateTree();
			locationNode.setHead(martyrDateTree);
		}

		Date martDate = new Date(martyr.getDate());
		DateNode martDateNode = martyrDateTree.find(martDate);
		if (martDateNode == null) {
			martDateNode = new DateNode(martDate);
			martyrDateTree.insert(martDate);
			martDateNode.setHead(martDate.getMartyrList());
		}

		// Add the martyr to the martyr list of the date node
		LinkedList martyrList = martDateNode.getHead();
		martyrList.addMartyrSorted(martyr);

		return true;
	}

	private void showNavigateLocationsScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		ta.clear();
		vbDistrictNavigation.getChildren().clear();
		vbDistrictNavigation.getChildren().addAll(comboDistrics, ta, hbNavigation, btnLoad);
		hbNavigation.setAlignment(Pos.CENTER);
		bp.setCenter(vbDistrictNavigation);

		// Single initialization of DataManager
		manger = new DataManager(districtTree);

		ensureDistrictComboBoxPopulation();

		if (lastStep && lastStepvalue != null) {
			comboDistrics.setValue(lastStepvalue);
			disNode = districtTree.find(new District(lastStepvalue));
			manger.initializeLocationTraversal(districtTree.find(disNode.getElement()));
			nextLocation = manger.nextLocation();
			updateLocationInfo(true);
			btnLoad.setOnAction(e1 -> {
				lastStep = true;
				lastStepDisvalue = comboDistrics.getValue();
				lastStepvalue = nextLocation != null ? nextLocation.getName() : null;
				showNavigateMartyrScreen(primaryStage);
				return;

			});
		} else {
			comboDistrics.setValue(null);
			comboLocations.setValue(null);
		}

		// Set up actions for ComboBox
		comboDistrics.setOnAction(e -> {
			String selectedDistrict = comboDistrics.getValue();
			if (selectedDistrict != null) {
				disNode = districtTree.find(new District(selectedDistrict));
				if (disNode != null) {
					manger.initializeLocationTraversal(disNode);
					nextLocation = manger.nextLocation();
					updateLocationInfo(true);
				}
			}

		});// Set up previous and next location navigation
		btPrev.setOnAction(e -> {
			navigateToPreviousLocation();
			btnLoad.setOnAction(e1 -> {
				lastStep = true;
				lastStepDisvalue = comboDistrics.getValue();
				lastStepvalue = prevLocation != null ? prevLocation.getName() : null;
				showNavigateMartyrScreen(primaryStage);
				return;

			});
		});
		btNext.setOnAction(e -> {
			navigateToNextLocation();
			btnLoad.setOnAction(e1 -> {
				lastStep = true;
				lastStepDisvalue = comboDistrics.getValue();
				lastStepvalue = nextLocation != null ? nextLocation.getName() : null;
				showNavigateMartyrScreen(primaryStage);
				return;

			});
		});

	}

	private void updateLocationInfo(boolean next) {
		if (disNode == null) {
			return; // Stop if disNode is not set
		}
		if (next) {
			if (nextLocation != null) {
				showLocation(nextLocation);
				date = DateStatistics.calculateForLocation(disNode.getHead().find(nextLocation));
				if (date != null) {
					date.display(ta);
				}
			}
		} else {
			if (prevLocation != null) {
				showLocation(prevLocation);
				date = DateStatistics.calculateForLocation(disNode.getHead().find(prevLocation));
				if (date != null) {
					date.display(ta);
				}
			}
		}
	}

	private void navigateToPreviousLocation() {
		prevLocation = manger.previousLocation();
		if (prevLocation != null) {
			showLocation(prevLocation);
			updateLocationInfo(false);
		}
	}

	private void navigateToNextLocation() {
		nextLocation = manger.nextLocation();
		if (nextLocation != null) {
			showLocation(nextLocation);
			updateLocationInfo(true);
		}
	}

	private void showLocation(Location location) {
		if (location != null) {
			ta.setText("Current Location: " + location.getName());
		}
	}

	private void showDeleteLocationScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		btOkLocation.setText("Delete");
		comboDistrics.setVisible(true);
		comboDistrics.setValue(null);
		comboLocations.setValue(null);
		btOkLocation.setText("Delete");
		hbLocationInsert.getChildren().clear();
		hbLocationInsert.getChildren().addAll(lblLocation, comboLocations);
		vbLocationInsert.getChildren().clear();
		hbLocationInsertButtons.getChildren().clear();
		hbLocationInsertButtons.getChildren().addAll(btOkLocation, btClearLocation);
		vbLocationInsert.getChildren().addAll(comboDistrics, hbLocationInsert, hbLocationInsertButtons, lblResult);
		hbLocationInsertButtons.setAlignment(Pos.CENTER);
		vbLocationInsert.setAlignment(Pos.CENTER);
		hbLocationInsert.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbLocationInsert);
		lblLocation.setText("Location Name");
		btOkLocation.setOnAction(e -> {
			boolean Confirm = confirmDeletion();
			if (Confirm) {
				if (removeLocation(comboDistrics.getValue(), comboLocations.getValue())) {
					comboLocations.setDisable(true);
					lblResult.setText("Deleted ✔️");
				} else
					lblResult.setText("Not Deleted ❌");
			}

		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
			comboDistrics.setValue(null);
		});
		comboDistrics.setOnAction(e -> {
			if (comboDistrics.getValue() != null) {
				District selectedDistrict = new District(comboDistrics.getValue());
				DistrictNode districtNode = districtTree.find(selectedDistrict);
				if (districtNode != null) {
					populateLocationComboBox(districtNode);
				} else {
					comboLocations.getItems().clear();
					comboLocations.setDisable(true);
				}
			}
		});

	}

	private boolean removeLocation(String districtName, String oldLocationName) {
		if (districtName == null || oldLocationName == null) {
			return false;
		}

		DistrictNode districtNode = districtTree.find(new District(districtName));
		if (districtNode == null) {
			return false;
		}

		LocationTree locationTree = districtNode.getHead();
		LocationNode locationNode = locationTree.find(new Location(oldLocationName));
		if (locationNode == null) {
			return false;
		}

		locationTree.remove(locationNode);

		return true;
	}

	private void showUpdateLocationScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		btOkLocation.setText("Update");
		comboDistrics.setVisible(true);
		comboLocations.setDisable(true);
		btOkDis.setText("Update");
		hbLocationInsert.getChildren().clear();
		hbLocationInsert.getChildren().addAll(lblLocation, comboLocations, tfLocationUpdate);
		vbLocationInsert.getChildren().clear();
		vbLocationInsert.getChildren().addAll(comboDistrics, hbLocationInsert, hbLocationInsertButtons, lblResult);
		hbLocationInsertButtons.setAlignment(Pos.CENTER);
		vbLocationInsert.setAlignment(Pos.CENTER);
		hbLocationInsert.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbLocationInsert);
		lblLocation.setText("Location's Name");
		tfLocationUpdate.setPromptText("New Location's name");
		tfLocation.setPromptText("old Location's name");
		comboDistrics.setValue(null);
		comboLocations.setValue(null);
		btOkLocation.setOnAction(e -> {
			boolean Confirm = confirmUpdate();
			if (Confirm) {
				if (comboDistrics.getValue() != null && comboLocations.getValue() != null)
					if (updateLocation(comboDistrics.getValue().trim(), comboLocations.getValue(),
							tfLocationUpdate.getText())) {

						lblResult.setText("Updated");

					} else
						lblResult.setText("Not updated, please re-check Location names");
			}

		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			tfLocationUpdate.clear();
			lblResult.setText("");
			comboDistrics.setValue(null);
		});
		comboDistrics.setOnAction(e -> {
			if (comboDistrics.getValue() != null) {
				District selectedDistrict = new District(comboDistrics.getValue());
				DistrictNode districtNode = districtTree.find(selectedDistrict);
				if (districtNode != null) {
					populateLocationComboBox(districtNode);
				} else {
					comboLocations.getItems().clear();
					comboLocations.setDisable(true);
				}
			}
		});

	}

	public boolean updateLocation(String districtName, String oldLocationName, String newLocationName) {
		if (districtName == null || oldLocationName == null || newLocationName == null) {
			return false;
		}
		newLocationName = newLocationName.replaceAll("[^a-zA-Z]", "");
		if (newLocationName.isEmpty())
			return false;
		DistrictNode districtNode = districtTree.find(new District(districtName));
		if (districtNode == null) {
			return false;
		}

		LocationTree locationTree = districtNode.getHead();
		LocationNode locationNode = locationTree.find(new Location(oldLocationName));
		if (locationNode == null) {
			return false;
		}

		locationTree.remove(locationNode);
		Location updatedLocation = new Location(newLocationName);
		locationTree.insert(updatedLocation);

		return true;
	}

	private void showAddLocationScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		btOkLocation.setText("Insert");
		comboDistrics.setValue(null);
		comboLocations.setValue(null);
		hbLocationInsert.getChildren().clear();
		hbLocationInsert.getChildren().addAll(lblLocation, tfLocation);
		vbLocationInsert.getChildren().clear();
		vbLocationInsert.getChildren().addAll(comboDistrics, hbLocationInsert, hbLocationInsertButtons, lblResult);
		hbLocationInsertButtons.setAlignment(Pos.CENTER);
		vbLocationInsert.setAlignment(Pos.CENTER);
		hbLocationInsert.setAlignment(Pos.CENTER);

		lblLocation.setText("Location's Name");
		tfLocation.setPromptText("New Location's name");
		lblResult.setText("");
		bpLocation.setCenter(vbLocationInsert);

		btOkLocation.setOnAction(e -> {
			if (comboDistrics.getValue() != null && !tfLocation.getText().isEmpty()) {
				if (addLocation(comboDistrics.getValue(), tfLocation.getText())) {
					lblResult.setText("Inserted");
					populateLocationComboBox(districtTree.find(new District(comboDistrics.getValue()))); // Refresh the
																											// locations
																											// list
				} else {
					lblResult.setText("Not inserted");
				}
			} else
				lblResult.setText("Complete the insert requirments !");
		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
			comboDistrics.setValue(null);
		});
	}

	public boolean addLocation(String districtName, String locationName) {
		locationName = locationName.replaceAll("[^a-zA-Z]", "");
		if (locationName.isEmpty())
			return false;
		DistrictNode districtNode = districtTree.find(new District(districtName));
		if (districtNode == null) {
			return false;
		}
		LocationTree locationTree = districtNode.getHead();
		if (locationTree.find(new Location(locationName)) != null) {
			return false;
		}
		locationTree.insert(new Location(locationName));
		return true;
	}

	private void showNavigateDistrictsScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		vbDistrictNavigation.getChildren().clear();
		vbDistrictNavigation.getChildren().addAll(ta, hbNavigation, btnLoad);
		hbNavigation.setAlignment(Pos.CENTER);
		bp.setCenter(vbDistrictNavigation);
		manger = new DataManager(districtTree);
		ta.setEditable(false);
		ensureDistrictComboBoxPopulation();
		comboDistrics.setValue(null);
		comboLocations.setValue(null);
		District firstDistrict = manger.nextDistrict();
		if (firstDistrict != null) {
			showDistrict(firstDistrict);
			btnLoad.setOnAction(e1 -> {
				lastStep = true;
				lastStepvalue = firstDistrict != null ? firstDistrict.getName() : null;
				showNavigateLocationsScreen(primaryStage);
				return;

			});
		}

		btPrev.setOnAction(e -> {
			District prevDistrict = manger.previousDistrict();

			if (prevDistrict != null) {
				showDistrict(prevDistrict);
				btnLoad.setOnAction(e1 -> {
					lastStep = true;
					lastStepvalue = prevDistrict != null ? prevDistrict.getName() : null;
					showNavigateLocationsScreen(primaryStage);
					return;

				});

			}

		});

		btNext.setOnAction(e -> {
			District nextDistrict = manger.nextDistrict();
			if (nextDistrict != null) {
				showDistrict(nextDistrict);
				btnLoad.setOnAction(e1 -> {
					lastStep = true;
					lastStepvalue = nextDistrict != null ? nextDistrict.getName() : null;

					showNavigateLocationsScreen(primaryStage);

				});
			}
		});
	}

	private void showDistrict(District district) {
		if (district != null) {
			ta.setText("Current District: " + district.getName());
			ta.appendText("\nNumber of martyrs: " + calculateDistrictMartyrs(districtTree.find(district)));
		}
	}

	// Calculate total martyrs for a district node recursively
	private int calculateDistrictMartyrs(DistrictNode districtNode) {
		if (districtNode == null) {
			return 0; // Base case: No district (empty tree)
		}

		int totalMartyrs = 0;
		// Recursively count martyrs in all locations of this district
		totalMartyrs += calculateLocationMartyrsRecursively(districtNode.getHead().getRoot());
		return totalMartyrs;
	}

	// Recursively calculate total martyrs for all locations in a location tree
	private int calculateLocationMartyrsRecursively(LocationNode locationNode) {
		if (locationNode == null) {
			return 0; // Base case: No location (empty tree)
		}

		int totalMartyrs = calculateDateMartyrsRecursively(locationNode.getHead().getRoot()); // Martyrs at this
																								// location
		totalMartyrs += calculateLocationMartyrsRecursively(locationNode.getLeft()); // Martyrs in left subtree
		totalMartyrs += calculateLocationMartyrsRecursively(locationNode.getRight()); // Martyrs in right subtree
		return totalMartyrs;
	}

	// Recursively calculate total martyrs for all dates in a date tree
	private int calculateDateMartyrsRecursively(DateNode dateNode) {
		if (dateNode == null) {
			return 0; // Base case: No date (empty tree)
		}

		int totalMartyrs = dateNode.getMartyrs().size(); // Count martyrs at this date
		totalMartyrs += calculateDateMartyrsRecursively(dateNode.getLeft()); // Martyrs in left subtree
		totalMartyrs += calculateDateMartyrsRecursively(dateNode.getRight()); // Martyrs in right subtree
		return totalMartyrs;
	}

	private void populateLocationComboBox(DistrictNode districtNode) {
		comboLocations.getItems().clear();

		if (districtNode == null) {
			comboLocations.setDisable(true);
			return;
		}

		LocationTree locationTree = districtNode.getHead();
		if (locationTree.getRoot() == null) {
			comboLocations.setDisable(true);
			return;
		}

		populateLocationComboBoxRecursively(locationTree.getRoot());
		comboLocations.setDisable(false); // Enable combo box if locations are available
	}

	private void populateLocationComboBoxRecursively(LocationNode node) {
		if (node != null) {
			populateLocationComboBoxRecursively(node.getLeft());
			comboLocations.getItems().add(node.getLocation().getName());
			populateLocationComboBoxRecursively(node.getRight());
		}
	}

	private void populateDistrictComboBox() {
		comboDistrics.getItems().clear(); // Clear existing items to avoid duplicates
		populateDistrictComboBoxRecursively(districtTree.getRoot()); // Start from the root
	}

	private void populateDistrictComboBoxRecursively(DistrictNode node) {
		if (node != null) {
			populateDistrictComboBoxRecursively(node.getLeft()); // Visit left child
			comboDistrics.getItems().add(node.getDistrictName().getName()); // Visit node
			populateDistrictComboBoxRecursively(node.getRight()); // Visit right child
		}
	}

	private void showDeleteDistrictScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		btOkDis.setText("Delete");
		tfDistrict.clear();
		tfDistrictUpdate.clear();
		hbDistrictInsert.getChildren().clear();
		hbDistrictInsert.getChildren().addAll(lblDistrict, comboDistrics);
		populateDistrictComboBox();
		manger = new DataManager(districtTree);
		vbDistrictInsert.getChildren().clear();
		vbDistrictInsert.getChildren().addAll(hbDistrictInsert, hbDistrictInsertButtons, lblSuccess);
		hbDistrictInsertButtons.setAlignment(Pos.CENTER);
		vbDistrictInsert.setAlignment(Pos.CENTER);
		hbDistrictInsert.setAlignment(Pos.CENTER);
		bp.setCenter(vbDistrictInsert);
		tfDistrictUpdate.setPromptText("District name");
		lblDistrict.setText("District Name");
		ensureDistrictComboBoxPopulation();
		btOkDis.setOnAction(e -> {
			boolean Confirm = confirmDeletion();
			if (Confirm) {
				if (manger.remove(comboDistrics.getValue()))
					lblSuccess.setText("Deleted ✔️");
				else
					lblSuccess.setText("Not Deleted ❌");
			}

		});
		btClearDis.setOnAction(e -> {
			tfDistrict.clear();
			lblSuccess.setText("");
			comboDistrics.setValue(null);
		});

	}

	private void showUpdateDistrictScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		btOkDis.setText("Update");
		tfDistrict.clear();
		tfDistrictUpdate.clear();
		hbDistrictInsert.getChildren().clear();
		vbPrint.getChildren().clear();
		populateDistrictComboBox();
		manger = new DataManager(districtTree);
		hbDistrictInsert.getChildren().addAll(lblDistrict, comboDistrics, tfDistrictUpdate);
		vbDistrictInsert.getChildren().clear();
		vbDistrictInsert.getChildren().addAll(hbDistrictInsert, hbDistrictInsertButtons, lblSuccess);
		hbDistrictInsertButtons.setAlignment(Pos.CENTER);
		vbDistrictInsert.setAlignment(Pos.CENTER);
		hbDistrictInsert.setAlignment(Pos.CENTER);
		bp.setCenter(vbDistrictInsert);
		lblDistrict.setText("Districts Name");
		tfDistrictUpdate.setPromptText("New District name");
		tfDistrict.setPromptText("old District name");
		ensureDistrictComboBoxPopulation();
		btOkDis.setOnAction(e -> {
			boolean Confirm = confirmUpdate();
			if (Confirm) {
				if (manger.updateDistrictRecord(comboDistrics.getValue().trim(), tfDistrictUpdate.getText()))
					lblSuccess.setText("Updated");
				else
					lblSuccess.setText("Not updated, please re-check district names");
			}

		});
		btClearDis.setOnAction(e -> {
			tfDistrict.clear();
			tfDistrictUpdate.clear();
			lblSuccess.setText("");
		});
	}

	private void showAddDistrictScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		vbDistrictInsert.setVisible(true);
		btOkDis.setText("Insert");
		tfDistrict.clear();
		tfDistrictUpdate.clear();
		hbDistrictInsert.getChildren().clear();
		hbDistrictInsert.getChildren().addAll(lblDistrict, tfDistrict);
		vbDistrictInsert.getChildren().clear();
		vbDistrictInsert.getChildren().addAll(hbDistrictInsert, hbDistrictInsertButtons, lblSuccess);
		hbDistrictInsertButtons.setAlignment(Pos.CENTER);
		vbDistrictInsert.setAlignment(Pos.CENTER);
		hbDistrictInsert.setAlignment(Pos.CENTER);
		manger = new DataManager(districtTree);
		ensureDistrictComboBoxPopulation();
		lblDistrict.setText("Districts Name");
		tfDistrict.setPromptText("New District name");
		lblSuccess.setText("");
		bp.setCenter(vbDistrictInsert);
		btOkDis.setOnAction(e -> {
			if (manger.addDistrict(tfDistrict.getText()))
				lblSuccess.setText("Inserted");
			else
				lblSuccess.setText("No");
		});
		btClearDis.setOnAction(e -> {
			tfDistrict.clear();
			lblSuccess.setText("");
			comboDistrics.setValue(null);
		});

	}

	private void setupLocationScreen(Stage primaryStage) {
		scene.setRoot(bpLocation);
		bpLocation.setTop(menuBar);
		vbLocationInsert.setVisible(true);
		bpLocation.setCenter(null);
		comboDistrics.getItems().clear();
		ensureDistrictComboBoxPopulation();
	}

	private void readFile(Stage primaryStage) {
		fChooser = new FileChooser();
		fChooser.setInitialDirectory(new File("C:\\Users\\ASUS\\eclipse-workspace\\StructureProjects"));
		f = fChooser.showOpenDialog(null);

		if (f != null) {
			try (Scanner read = new Scanner(f)) {
				read.nextLine(); // Skip the first line
				while (read.hasNextLine()) {
					String s = read.nextLine();
					String[] line = s.split(",");
					try {
						if (line.length != 6)
							read.nextLine();
						String name = line[0];
						if (isNumeric(name))
							read.nextLine();
						String date = line[1];
						byte age = line[2].isEmpty() ? (byte) -1 : Byte.parseByte(line[2]);

						String location = line[3];
						if (isNumeric(location))
							read.nextLine();
						String district = line[4];
						if (isNumeric(district))
							read.nextLine();
						char gender = line[5].isEmpty() || line[5].equals("NA") ? 'N' : line[5].charAt(0);
						if ("MFmfN".indexOf(gender) == -1)
							read.nextLine();
						District dis = new District(district);
						DistrictNode districtNode = districtTree.find(dis);
						if (districtNode == null) {
							districtNode = new DistrictNode(dis);
							districtTree.insert(dis);
							districtNode.setHead(dis.getLocationTree());
						}

						LocationTree locationTree = districtNode.getHead();
						if (locationTree == null) {
							locationTree = new LocationTree();
							districtNode.setHead(locationTree);
						}

						Location loc = new Location(location);
						LocationNode locationNode = locationTree.find(loc);
						if (locationNode == null) {
							locationNode = new LocationNode(loc);
							locationTree.insert(loc);
							locationNode.setHead(loc.getDateTree());
						}

						DateTree martyrDateTree = locationNode.getHead();
						if (martyrDateTree == null) {
							martyrDateTree = new DateTree();
							locationNode.setHead(martyrDateTree);
						}

						Date martDate = new Date(date);
						DateNode martDateNode = martyrDateTree.find(martDate);
						if (martDateNode == null) {
							martDateNode = new DateNode(martDate);
							martyrDateTree.insert(martDate);
							martDateNode.setHead(martDate.getMartyrList());
						}

						LinkedList martyrList = martDateNode.getHead();
						if (martyrList == null) {
							martyrList = new LinkedList();
							martDateNode.setHead(martyrList);
						}

						Martyr martyr = new Martyr(name, date, age, location, district, gender);
						martyrList.addMartyrSorted(martyr);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			populateDistrictComboBox();
		}
	}

	private void ensureDistrictComboBoxPopulation() {
		if (comboDistrics.getItems().isEmpty()) {
			populateDistrictComboBox();
		}
	}

	public void printTreeInOrder(DistrictNode node) {
		if (node != null) {
			printTreeInOrder(node.getLeft());
			System.out.println(node.getDistrictName().getName());
			printTreeInOrder(node.getRight());
		}
	}

	private boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

//	private boolean isValidDate(String date) {
//		return date.matches("\\d{1}/\\d{1}/\\d{4}") || date.matches("\\d{1}/\\d{2}/\\d{4}")
//				|| date.matches("\\d{2}/\\d{1}/\\d{4}") || date.matches("\\d{2}/\\d{2}/\\d{4}");
//	}

	public void cleanup() {
		districtTree.clear();
		System.gc(); // Suggest garbage collection after clearing a large list
	}

	public static void main(String[] args) {
		launch(args);
	}

}
