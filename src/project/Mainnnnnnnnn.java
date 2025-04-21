package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Year;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Mainnnnnnnnn extends Application {
	private FileChooser fChooser;
	private File f;
	private Martyr martyr;
	private DoubleLinkedList districtList;
	private LinkedList locationList, martyrList;
	private Location loc;
	private District dis;
	private Node LocationNode, martyrNode;
	private MenuBar menuBar;
	private Menu menu1, menu2, menu3, menu4;
	private MenuItem item1, item21, item22, updateLocation, navigateLocations, addMartyr, updateMartyr, deleteMartyr,
			searchMartyr, addLocation, deleteLocation, addDistrict, updateDistrict, deleteDistrict, navigateDistricts,
			districtMartyrsByDate, saveDistrictToFile;
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
	private int track;
	private Image image;
	private ImageView imageView;
	private ComboBox<Integer> comboBoxDay, comboBoxYear;
	private ComboBox<Byte> comboAges;
	private ComboBox<String> comboDistrics, comboLocations, comboBoxMonth;
	private Labeled lblDate;
	private MenuItem navigateMartyr;

	public void start(Stage primaryStage) {
		// Initialize the data structure
		districtList = new DoubleLinkedList();

		// Initialize Menus and MenuItems
		menuBar = new MenuBar();

		// File Menu
		menu1 = new Menu("File");
		item1 = new MenuItem("Open");
		menu1.getItems().add(item1);

		// Help Center Menu
		menu2 = new Menu("Help center");
		item21 = new MenuItem("About us");
		item22 = new MenuItem("Firas's system");
		menu2.getItems().addAll(item21, item22);

		// Operations Menu for Districts
		menu3 = new Menu("Operations");
		addDistrict = new MenuItem("Add a new District");
		updateDistrict = new MenuItem("Update existing District");
		deleteDistrict = new MenuItem("Delete District");
		navigateDistricts = new MenuItem("Navigate Districts");
		districtMartyrsByDate = new MenuItem("Number of martyrs in a specific date");
		saveDistrictToFile = new MenuItem("Save District in a file");
		menu3.getItems().addAll(addDistrict, updateDistrict, deleteDistrict, navigateDistricts, districtMartyrsByDate,
				saveDistrictToFile);

		// Location Menu
		menu4 = new Menu("Location's screen");
		addLocation = new MenuItem("Add a new Location");
		updateLocation = new MenuItem("Update an existing Location");
		deleteLocation = new MenuItem("Delete Location");
		navigateLocations = new MenuItem("Navigate Locations");
		addMartyr = new MenuItem("Add a new Martyr");
		updateMartyr = new MenuItem("Update martyr");
		deleteMartyr = new MenuItem("Delete martyr");
		searchMartyr = new MenuItem("Search for a martyr");
		navigateMartyr = new MenuItem("Navigate Martyr");
		menu4.getItems().addAll(addLocation, updateLocation, deleteLocation, navigateLocations, addMartyr, updateMartyr,
				deleteMartyr, searchMartyr, navigateMartyr);
		menu3.setDisable(true);
		menu4.setDisable(true);
		menuBar.getMenus().addAll(menu1, menu3, menu4, menu2);
		menuBar.setPadding(new Insets(10, 10, 10, 10));

		// Linking MenuItem actions to methods
		addDistrict.setOnAction(e -> showAddDistrictScreen(primaryStage));
		updateDistrict.setOnAction(e -> showUpdateDistrictScreen(primaryStage));
		deleteDistrict.setOnAction(e -> showDeleteDistrictScreen(primaryStage));
		navigateDistricts.setOnAction(e -> showNavigateDistrictsScreen(primaryStage));
		districtMartyrsByDate.setOnAction(e -> showDistrictMartyrsByDateScreen(primaryStage));
		saveDistrictToFile.setOnAction(e -> saveDistrictToFile());

		// Set actions for menu items related to locations and martyrs
		addLocation.setOnAction(e -> showAddLocationScreen(primaryStage));
		updateLocation.setOnAction(e -> showUpdateLocationScreen(primaryStage));
		deleteLocation.setOnAction(e -> showDeleteLocationScreen(primaryStage));
		navigateLocations.setOnAction(e -> showNavigateLocationsScreen(primaryStage));
		addMartyr.setOnAction(e -> showAddMartyrScreen(primaryStage));
		updateMartyr.setOnAction(e -> showUpdateMartyrScreen(primaryStage));
		deleteMartyr.setOnAction(e -> showDeleteMartyrScreen(primaryStage));
		searchMartyr.setOnAction(e -> showSearchMartyrScreen(primaryStage));
		navigateMartyr.setOnAction(e -> showNavigateMartyrScreen(primaryStage));

		String firas = "";
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

		// Initialize ComboBoxes
		comboBoxDay = new ComboBox<>();
		comboBoxMonth = new ComboBox<>();
		comboBoxYear = new ComboBox<>();
		comboDistrics = new ComboBox<String>();
		comboLocations = new ComboBox<String>();
		comboAges = new ComboBox<>();

		// Populate ComboBoxes
		for (int day = 1; day <= 31; day++) {
			comboBoxDay.getItems().add(day);
		}
		comboBoxMonth.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
		for (int year = Year.now().getValue() - 50; year <= Year.now().getValue(); year++) {
			comboBoxYear.getItems().add(year);
		}
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

		// Set BorderPane as the root and apply CSS stylesheet
		bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(imageView);
		bpLocation = new BorderPane();
		scene = new Scene(bp, 700, 500);
		scene.getStylesheets().add(getClass().getResource("/resource/style.css").toExternalForm());

		// Finalize the stage setup
		primaryStage.setScene(scene);
		primaryStage.setTitle("Main Screen");
		item1.setOnAction(e -> readFromAFile(primaryStage));

		primaryStage.show();

	}

	private void showNavigateMartyrScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		comboDistrics.setVisible(true);
		btOkLocation.setText("Search");
		tfLocation.setPromptText("martyr partial name");
		hbMartyrName.getChildren().clear();
		hbMartyrName.getChildren().addAll(new Label("Martyr's name:"), tfLocation);
		hbLocationInsertButtons.getChildren().clear();
		vbLocationInsert.getChildren().clear();
		hbLocationInsertButtons.getChildren().addAll(btOkLocation, btClearLocation);
		vbMartyr.getChildren().clear();
		vbMartyr.getChildren().addAll(hbMartyrName, comboDistrics, comboLocations, hbLocationInsertButtons, lblResult);
		comboDistrics.setPromptText("Districts");
		comboLocations.setDisable(true);
		vbMartyr.setAlignment(Pos.CENTER);
		hbMartyrName.setAlignment(Pos.CENTER);
		hbLocationInsertButtons.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbMartyr);
		comboDistrics.setOnAction(e -> {
			comboLocations.setDisable(true);
			comboLocations.getItems().clear();
			populateLocationComboBox(comboDistrics.getValue());

		});
		btOkLocation.setOnAction(e -> {
			if (tfLocation.getText().isEmpty()) {
				lblResult.setText("Please make sure to insert the name");
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
			searchMartyrByNamePartInLocation(comboDistrics.getValue(), comboLocations.getValue(), tfLocation.getText());
		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
		});

	}

	private void setupLocationScreen(Stage primaryStage) {
		scene.setRoot(bpLocation);
		bpLocation.setTop(menuBar);
		vbLocationInsert.setVisible(true);
		bpLocation.setCenter(null);
		comboDistrics.getItems().clear();

		DoubleNode current = districtList.getFront();
		while (current != null) {
			comboDistrics.getItems().add(current.getElement().getName());
			current = current.getNext();
		}

	}

	private void showAddLocationScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		btOkLocation.setText("Insert");
		comboDistrics.setVisible(true);

		btOkLocation.setText("Insert");

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
			if (!tfLocation.getText().isEmpty() || comboDistrics.getValue().isEmpty()) {
				DoubleNode dis = districtList.findDistrictNode(comboDistrics.getValue());
				if (dis != null) {
					Node loc = dis.getElement().getLocations().findLocationNode(tfLocation.getText());
					if (loc == null) {
						Location newLocation = new Location(tfLocation.getText());
						dis.getElement().getLocations().insertLocationSorted(newLocation);
						lblResult.setText("Location inserted successfully ✔️");
					} else
						lblResult.setText("Please re-check Location's name");
				} else
					lblResult.setText("Please re-check district's name");
			} else
				lblResult.setText("Please enter a name");

		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
		});

	}

	// Method for updating an existing location
	private void showUpdateLocationScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		btOkLocation.setText("Update");
		comboDistrics.setVisible(true);

		btOkDis.setText("Update");
		hbLocationInsert.getChildren().clear();
		hbLocationInsert.getChildren().addAll(lblLocation, tfLocation, tfLocationUpdate);
		vbLocationInsert.getChildren().clear();
		vbLocationInsert.getChildren().addAll(comboDistrics, hbLocationInsert, hbLocationInsertButtons, lblResult);
		hbLocationInsertButtons.setAlignment(Pos.CENTER);
		vbLocationInsert.setAlignment(Pos.CENTER);
		hbLocationInsert.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbLocationInsert);
		lblLocation.setText("Location's Name");
		tfLocationUpdate.setPromptText("New Location's name");
		tfLocation.setPromptText("old Location's name");

		btOkLocation.setOnAction(e -> {
			if (updateLocationRecord(comboDistrics.getValue(), tfLocation.getText(), tfLocationUpdate.getText()))
				lblResult.setText("Updated");
			else
				lblResult.setText("Not updated, please re-check Location names");
		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			tfLocationUpdate.clear();
			lblResult.setText("");
		});

	}

	// Method for deleting a location
	private void showDeleteLocationScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		btOkLocation.setText("Delete");
		comboDistrics.setVisible(true);

		btOkLocation.setText("Delete");
		hbLocationInsert.getChildren().clear();
		hbLocationInsert.getChildren().addAll(lblLocation, tfLocation);
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
			if (removeLocation(comboDistrics.getValue(), tfLocation.getText()))
				lblResult.setText("Deleted ✔️");
			else
				lblResult.setText("Not Deleted ❌");
		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
		});
	}

	// Method for navigating through locations
	private void showNavigateLocationsScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		vbLocationInsert.getChildren().clear();
		vbLocationNavigation.getChildren().clear();
		vbLocationNavigation.getChildren().addAll(comboDistrics, taLocation, hbNavigationLocation);
		comboDistrics.setVisible(false);
		lblResult.setText("");
		bpLocation.setCenter(vbLocationNavigation);
		vbLocationNavigation.setAlignment(Pos.CENTER);
		hbNavigationLocation.setAlignment(Pos.CENTER);
		taLocation.setEditable(false);
		track = 0;
		taLocation.clear();
		btSelect.setOnAction(e -> {
			comboDistrics.setVisible(true);

		});
		comboDistrics.setOnAction(e -> {
			if (!(comboDistrics.getValue() == null))
				comboDistrics.setVisible(false);
			track = 0;
		});
		btNextLocation.setOnAction(e -> {

			if (comboDistrics.getValue() != null) {
				taLocation.clear();
				String districtName = comboDistrics.getValue();
				DoubleNode dis = districtList.findDistrictNode(districtName);
				if (track >= dis.getElement().getLocations().getSize()) {
					track = 0; // Loop back to the last element if out of bounds
				}

				taLocation.appendText(
						"Location: " + ((Location) dis.getElement().getLocations().get(track)).getName() + "\n");
				updateLocationInfo(dis, (Location) dis.getElement().getLocations().get(track));
				track++;
			} else
				taLocation.setText("Select a Distict");
		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
		});

	}

	// Method for adding a new martyr
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
		vbMartyr.getChildren().addAll(hbMartyrName, hbDatePickers, comboAges, comboDistrics, comboLocations, hbGender,
				hbLocationInsertButtons, lblResult);
		hbGender.setAlignment(Pos.CENTER);
		comboDistrics.setPromptText("Districts");
		comboLocations.setDisable(true);
		vbMartyr.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbMartyr);
		comboDistrics.setOnAction(e -> {
			comboLocations.setDisable(true);
			comboLocations.getItems().clear();
			populateLocationComboBox(comboDistrics.getValue());

		});
		btOkLocation.setOnAction(e -> {
			if (tfFirst.getText().isEmpty() || tfMiddle.getText().isEmpty() || tfSurname.getText().isEmpty()) {
				lblResult.setText("Please make sure to insert all names");
				return;
			}
			if (comboBoxDay.getValue() == null || comboBoxMonth.getValue().isEmpty()
					|| comboBoxYear.getValue() == null) {
				lblResult.setText("Please make sure to fill the martyr's date");
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
					lblResult.setText("Pgeelect a gender");
					return;
				}

			}
			String name = tfFirst.getText() + " " + tfMiddle.getText() + " " + tfSurname.getText();
			String date = comboBoxMonth.getValue() + "/" + comboBoxDay.getValue() + "/" + comboBoxYear.getValue();
			Martyr martyr = new Martyr(name, date, comboAges.getValue(), comboLocations.getValue(),
					comboDistrics.getValue(), 'M');
			if (rbFemale.isSelected())
				martyr.setGender('F');
			insertMartyr(martyr.getDistrict(), martyr.getLocation(), martyr);

		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
		});

	}

	// Method for updating a martyr
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
		hbMartyrName.getChildren().clear();
		hbMartyrName.getChildren().addAll(new Label("Martyr's name:"), tfLocation, tfLocationUpdate);
		hbLocationInsertButtons.getChildren().clear();
		hbLocationInsertButtons.getChildren().addAll(btOkLocation, btClearLocation);
		vbMartyr.getChildren().clear();
		vbLocationInsert.getChildren().clear();
		vbMartyr.getChildren().addAll(hbMartyrName, hbDatePickers, comboAges, comboDistrics, comboLocations, hbGender,
				hbLocationInsertButtons, lblResult);
		hbGender.setAlignment(Pos.CENTER);
		comboDistrics.setPromptText("Districts");
		comboLocations.setDisable(true);
		vbMartyr.setAlignment(Pos.CENTER);
		hbMartyrName.setAlignment(Pos.CENTER);
		hbDatePickers.setAlignment(Pos.CENTER);
		hbGender.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbMartyr);
		comboDistrics.setOnAction(e -> {
			comboLocations.setDisable(true);
			comboLocations.getItems().clear();
			populateLocationComboBox(comboDistrics.getValue());

		});
		btOkLocation.setOnAction(e -> {
			if (tfLocation.getText().isEmpty() || tfLocationUpdate.getText().isEmpty()) {
				lblResult.setText("Please make sure to insert all names");
				return;
			}
			if (comboBoxDay.getValue() == null || comboBoxMonth.getValue().isEmpty()
					|| comboBoxYear.getValue() == null) {
				lblResult.setText("Please make sure to fill the martyr's date");
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
					lblResult.setText("Pgeelect a gender");
					return;
				}

			}
			String name = tfLocationUpdate.getText();
			String date = comboBoxMonth.getValue() + "/" + comboBoxDay.getValue() + "/" + comboBoxYear.getValue();
			Martyr martyr = new Martyr(name, date, comboAges.getValue(), comboLocations.getValue(),
					comboDistrics.getValue(), 'M');
			if (rbFemale.isSelected())
				martyr.setGender('F');

			if (updateMartyr(comboDistrics.getValue(), comboLocations.getValue(), tfLocation.getText(), martyr))
				lblResult.setText("Update went good");
			else
				lblResult.setText("Update went wrong");
		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
		});

	}

	// Method for deleting a martyr
	private void showDeleteMartyrScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		btOkLocation.setText("Delete");
		comboDistrics.setVisible(true);
		tfLocation.setPromptText("martyr name");
		hbMartyrName.getChildren().clear();
		hbMartyrName.getChildren().addAll(new Label("Martyr's name:"), tfLocation);
		hbLocationInsertButtons.getChildren().clear();
		hbLocationInsertButtons.getChildren().addAll(btOkLocation, btClearLocation);
		vbMartyr.getChildren().clear();
		vbLocationInsert.getChildren().clear();

		vbMartyr.getChildren().addAll(hbMartyrName, comboDistrics, comboLocations, hbLocationInsertButtons, lblResult);
		comboDistrics.setPromptText("Districts");
		comboLocations.setDisable(true);
		vbMartyr.setAlignment(Pos.CENTER);
		hbLocationInsertButtons.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbMartyr);
		comboDistrics.setOnAction(e -> {
			comboLocations.setDisable(true);
			comboLocations.getItems().clear();
			populateLocationComboBox(comboDistrics.getValue());

		});
		btOkLocation.setOnAction(e -> {
			if (tfLocation.getText().isEmpty()) {
				lblResult.setText("Please make sure to insert the name");
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
			deleteMartyr(comboDistrics.getValue(), comboLocations.getValue(), tfLocation.getText());
		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
		});

	}

	// Method for searching a martyr
	private void showSearchMartyrScreen(Stage primaryStage) {
		setupLocationScreen(primaryStage);
		tfLocation.clear();
		tfLocationUpdate.clear();
		lblResult.setText("");
		comboDistrics.setVisible(true);
		btOkLocation.setText("Search");
		tfLocation.setPromptText("martyr partial name");
		hbMartyrName.getChildren().clear();
		hbMartyrName.getChildren().addAll(new Label("Martyr's name:"), tfLocation);
		hbLocationInsertButtons.getChildren().clear();
		vbLocationInsert.getChildren().clear();
		hbLocationInsertButtons.getChildren().addAll(btOkLocation, btClearLocation);
		vbMartyr.getChildren().clear();
		vbMartyr.getChildren().addAll(hbMartyrName, comboDistrics, comboLocations, hbLocationInsertButtons, lblResult);
		comboDistrics.setPromptText("Districts");
		comboLocations.setDisable(true);
		vbMartyr.setAlignment(Pos.CENTER);
		hbMartyrName.setAlignment(Pos.CENTER);
		hbLocationInsertButtons.setAlignment(Pos.CENTER);
		bpLocation.setCenter(vbMartyr);
		comboDistrics.setOnAction(e -> {
			comboLocations.setDisable(true);
			comboLocations.getItems().clear();
			populateLocationComboBox(comboDistrics.getValue());

		});
		btOkLocation.setOnAction(e -> {
			if (tfLocation.getText().isEmpty()) {
				lblResult.setText("Please make sure to insert the name");
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
			searchMartyrByNamePartInLocation(comboDistrics.getValue(), comboLocations.getValue(), tfLocation.getText());
		});
		btClearLocation.setOnAction(e -> {
			tfLocation.clear();
			lblResult.setText("");
		});

	}

	private void saveDistrictToFile() {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		tfDistrict.clear();
		tfDistrictUpdate.clear();
		vbPrint.getChildren().clear();
		vbPrint.getChildren().addAll(comboDistrics, btPrint);

		DoubleNode current = districtList.getFront();
		comboDistrics.getItems().clear();
		while (current != null) {
			comboDistrics.getItems().add(current.getElement().getName());
			current = current.getNext();
		}
		comboDistrics.getItems().add("All");
		vbPrint.setAlignment(Pos.CENTER);
		bp.setCenter(vbPrint);
		btPrint.setOnAction(e -> {
			String name = comboDistrics.getValue();
			if (name.equalsIgnoreCase("All")) {
				printDistrictsWithLocationsAndMartyrs();
			} else
				printADistrict(name);
		});

	}

	private void showDistrictMartyrsByDateScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		tfDistrict.clear();
		tfDistrictUpdate.clear();
		vbDistrictDate.getChildren().clear();
		vbDistrictDate.getChildren().addAll(hbDatePickers, submitButton, lblDate);
		bp.setCenter(vbDistrictDate);
		submitButton.setOnAction(e -> {
			String day = String.valueOf(comboBoxDay.getValue());
			String month = comboBoxMonth.getValue();
			String year = String.valueOf(comboBoxYear.getValue());
			String date = month + "/" + day + "/" + year;
			date = date.trim();
			lblDate.setText(numberOfMartyrsInADate(date) + "");
		});

	}

	private void showNavigateDistrictsScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		tfDistrict.clear();
		tfDistrictUpdate.clear();
		vbDistrictNavigation.getChildren().clear();
		vbDistrictNavigation.getChildren().addAll(ta, hbNavigation);
		bp.setCenter(vbDistrictNavigation);
		vbDistrictNavigation.setAlignment(Pos.CENTER);
		hbNavigation.setAlignment(Pos.CENTER);
		ta.setEditable(false);
		track = 0; // Reset track to start from the first element
		ta.clear();
		updateDistrictInfo((DoubleNode) districtList.get(track)); // Initial update

		btPrev.setOnAction(e -> {
			track--;
			ta.clear();
			if (track < 0 || districtList.get(track) == null) {
				track = 0; // Loop back to the start or stay at the first element if out of bounds
			}
			updateDistrictInfo((DoubleNode) districtList.get(track));
		});

		btNext.setOnAction(e -> {
			track++;
			ta.clear();
			if (track >= districtList.size() || districtList.get(track) == null) {
				track = districtList.size() - 1; // Loop back to the last element if out of bounds
			}
			updateDistrictInfo((DoubleNode) districtList.get(track));
		});

	}

	private void showDeleteDistrictScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		btOkDis.setText("Delete");
		tfDistrict.clear();
		tfDistrictUpdate.clear();
		hbDistrictInsert.getChildren().clear();
		hbDistrictInsert.getChildren().addAll(lblDistrict, tfDistrict);
		vbDistrictInsert.getChildren().clear();
		vbDistrictInsert.getChildren().addAll(hbDistrictInsert, hbDistrictInsertButtons, lblSuccess);
		hbDistrictInsertButtons.setAlignment(Pos.CENTER);
		vbDistrictInsert.setAlignment(Pos.CENTER);
		hbDistrictInsert.setAlignment(Pos.CENTER);
		bp.setCenter(vbDistrictInsert);
		tfDistrictUpdate.setPromptText("District name");
		lblDistrict.setText("District Name");
		btOkDis.setOnAction(e -> {
			if (removeDistrict(tfDistrict.getText()))
				lblSuccess.setText("Deleted ✔️");
			else
				lblSuccess.setText("Not Deleted ❌");
		});
		btClearDis.setOnAction(e -> {
			tfDistrict.clear();
			lblSuccess.setText("");
		});

	}

	private void showUpdateDistrictScreen(Stage primaryStage) {
		scene.setRoot(bp);
		bp.setTop(menuBar);
		btOkDis.setText("Update");
		tfDistrict.clear();
		tfDistrictUpdate.clear();
		hbDistrictInsert.getChildren().clear();
		hbDistrictInsert.getChildren().addAll(lblDistrict, tfDistrict, tfDistrictUpdate);
		vbDistrictInsert.getChildren().clear();
		vbDistrictInsert.getChildren().addAll(hbDistrictInsert, hbDistrictInsertButtons, lblSuccess);
		hbDistrictInsertButtons.setAlignment(Pos.CENTER);
		vbDistrictInsert.setAlignment(Pos.CENTER);
		hbDistrictInsert.setAlignment(Pos.CENTER);
		bp.setCenter(vbDistrictInsert);
		lblDistrict.setText("Districts Name");
		tfDistrictUpdate.setPromptText("New District name");
		tfDistrict.setPromptText("old District name");

		btOkDis.setOnAction(e -> {
			if (updateDistrictRecord(tfDistrict.getText(), tfDistrictUpdate.getText()))
				lblSuccess.setText("Updated");
			else
				lblSuccess.setText("Not updated, please re-check district names");
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

		lblDistrict.setText("Districts Name");
		tfDistrict.setPromptText("New District name");
		lblSuccess.setText("");
		bp.setCenter(vbDistrictInsert);
		btOkDis.setOnAction(e -> {
			if (!tfDistrict.getText().isEmpty()) {
				DoubleNode dis = districtList.findDistrictNode(tfDistrict.getText());

				if (dis == null) {
					District district1 = new District(tfDistrict.getText());
					districtList.addSorted(district1);
					lblSuccess.setText("Inserted successfuly");
				} else
					lblSuccess.setText("Please re-check district's name");
			} else
				lblSuccess.setText("Please enter a name");

		});
		btClearDis.setOnAction(e -> {
			tfDistrict.clear();
			lblSuccess.setText("");
		});

	}

	private void populateLocationComboBox(String districtName) {
		DoubleNode dis = districtList.findDistrictNode(districtName);
		if (dis != null) {
			Node loc = dis.getElement().getLocations().getFront();
			while (loc != null) {
				comboLocations.getItems().add(((Location) loc.getElement()).getName());
				loc = loc.getNext();
			}
		}
		comboLocations.setDisable(false);
	}

	private void updateLocationInfo(DoubleNode node, Location loc) {
		if (node != null && node.getElement() != null) {
			District district = node.getElement();

			numberOfMartyrsInLocation(district.getName(), loc.getName());
			maleAndFemaleNumberInLocation(district.getName(), loc.getName());
			calculateAverageAgeOfMartyrsInLocation(district.getName(), loc.getName());
			youngestOldestInLocation(district.getName(), loc.getName());

		}
	}

	private void updateDistrictInfo(DoubleNode node) {
		if (node != null && node.getElement() != null) {
			District district = node.getElement();
			numberOfMartyrsInDistrict(district.getName());
			maleAndFemaleNumberInDistrict(district.getName());
			calculateAverageAgeOfMartyrsInDistrict(district.getName());
			findMostRepeatedDate(node);
		}
	}

	private void readFromAFile(Stage primaryStage) {
		fChooser = new FileChooser();
		fChooser.setInitialDirectory(new File("C:\\Users\\ASUS\\eclipse-workspace\\StructureProjects"));
		// Enable the user to choose the file when the program runs
		f = fChooser.showOpenDialog(null);

		if (f != null) {
			menu3.setDisable(false);
			menu4.setDisable(false);
			showAddDistrictScreen(primaryStage);
			try (Scanner read = new Scanner(f)) {
				while (read.hasNextLine()) {
					String line = read.nextLine().trim();
					String[] parts = line.split(",");

					if (isNumeric(parts[2]) && isChar(parts[5])) {
						Byte age = Byte.parseByte(parts[2]);
						char gender = parts[5].charAt(0);
						martyr = new Martyr(parts[0], parts[1], age, parts[3], parts[4], gender);
						dis = new District(parts[4]);
						DoubleNode d = districtList.findDistrictNode(dis.getName());
						if (d == null) {
							d = new DoubleNode(dis);
							districtList.addSorted(d.getElement());
						}
						loc = new Location(parts[3]);
						LocationNode = d.getElement().getLocations().findLocationNode(loc.getName());
						locationList = d.getElement().getLocations();
						if (LocationNode == null) {
							LocationNode = new Node(loc);
							locationList.insertLocationSorted(loc);
						}
						martyrList = ((Location) LocationNode.getElement()).getMartyrs();
						martyrNode = martyrList.findMartyrNode(martyr.getName());
						if (martyrNode == null) {
							martyrNode = new Node(martyr);
							martyrList.addMartyrSorted(martyr);
						}

					}

				}

			} catch (FileNotFoundException e) {
				System.out.println("File not found: " + f.getAbsolutePath());
				e.printStackTrace();

			}
		}

	}

	public void searchMartyrByNamePartInLocation(String districtName, String locationName, String partialName) {
		LinkedList matchingMartyrs = new LinkedList();
		DoubleNode currentDistrict = districtList.findDistrictNode(districtName);
		StringBuilder s = new StringBuilder();
		if (currentDistrict == null) {
			lblResult.setText("District " + districtName + " not found.");
			return;
		}
		LinkedList locationList = currentDistrict.getElement().getLocations();
		Node currentLocation = locationList.findLocationNode(locationName);
		if (currentLocation == null) {
			lblResult.setText("Location " + locationName + " not found in District " + districtName + ".");
			return;
		}

		// Search through martyrs in the found location.
		LinkedList martyrList = ((Location) currentLocation.getElement()).getMartyrs();
		Node currentMartyr = martyrList.getFront();
		while (currentMartyr != null) {
			Martyr martyr = (Martyr) currentMartyr.getElement();
			if (martyr.getName().toLowerCase().contains(partialName.toLowerCase())) {
				matchingMartyrs.add(martyr); // Add the martyr to the list if their name contains the partial name.
			}
			currentMartyr = currentMartyr.getNext();
		}

		if (matchingMartyrs.isEmpty()) {
			lblResult
					.setText("No matching martyrs found in " + locationName + " within " + districtName + " district.");
		} else {
			s.append("Matching martyrs found in " + locationName + " within " + districtName + " district:\n");
			Node currentNode = matchingMartyrs.getFront();
			while (currentNode != null) {
				s.append("\t- " + ((Martyr) currentNode.getElement()).getName() + "\n");
				currentNode = currentNode.getNext();
			}
			lblResult.setText(s + "");
		}
	}

	public boolean updateMartyr(String districtName, String locationName, String martyrName, Martyr newMartyr) {
		// Check for null parameters.
		if (districtName == null || locationName == null || martyrName == null || newMartyr == null) {
			return false;
		}

		// Find the old district and location nodes.
		DoubleNode oldDistrictNode = districtList.findDistrictNode(districtName);
		if (oldDistrictNode == null) {
			return false;
		}

		Node oldLocationNode = oldDistrictNode.getElement().getLocations().findLocationNode(locationName);
		if (oldLocationNode == null) {
			// Old location not found
			return false;
		}

		// Find the martyr to update.
		LinkedList martyrList = ((Location) oldLocationNode.getElement()).getMartyrs();
		Node martyrNode = martyrList.findMartyrNode(martyrName);
		if (martyrNode == null) {
			// Martyr not found
			return false;
		}

		// Check if the location or district has changed
		if (!districtName.equals(newMartyr.getDistrict()) || !locationName.equals(newMartyr.getLocation())) {
			// If district or location has changed, check if the new district and location
			// exist.
			DoubleNode newDistrictNode = districtList.findDistrictNode(newMartyr.getDistrict());
			if (newDistrictNode == null) {
				return false;
			}

			LinkedList newLocationList = newDistrictNode.getElement().getLocations();
			Node newLocationNode = newLocationList.findLocationNode(newMartyr.getLocation());
			if (newLocationNode == null) {
				return false;
			}

			// Remove martyr from old location
			if (!martyrList.removeMartyr(martyrName)) {
				return false;
			}

			// Add martyr to new location
			((Location) newLocationNode.getElement()).getMartyrs().addMartyrSorted(newMartyr);
		} else {
			Martyr oldMartyr = (Martyr) martyrNode.getElement();
			oldMartyr.setName(newMartyr.getName());
			oldMartyr.setDate(newMartyr.getDate());
			oldMartyr.setAge(newMartyr.getAge());
			oldMartyr.setGender(newMartyr.getGender());
		}
		return true;
	}

	public boolean deleteMartyr(String districtName, String locationName, String martyrName) {
		DoubleNode currentDistrict = districtList.findDistrictNode(districtName);
		if (currentDistrict == null) {
			return false;
		}
		LinkedList locationList = currentDistrict.getElement().getLocations();
		Node currentLocation = locationList.findLocationNode(locationName);
		if (currentLocation == null) {
			return false;
		}
		LinkedList martyrList = ((Location) currentLocation.getElement()).getMartyrs();
		boolean removed = martyrList.removeMartyr(martyrName);
		if (removed) {
			lblResult.setText("Martyr deleted successfully");
		} else {
			lblResult.setText("Martyr not found");
		}
		return removed;
	}

	public void insertMartyr(String districtName, String locationName, Martyr martyr) {
		Node martyrNode = findMartyrNodeInAllDistricts(martyr.getName());

		if (martyrNode != null) {
			lblResult.setText("Martyr is already in our data");
			return;
		}

		DoubleNode currentDistrict = districtList.findDistrictNode(districtName);
		if (currentDistrict == null) {
			lblResult.setText("District " + districtName + " not found.");
			return;
		}

		LinkedList locationList = currentDistrict.getElement().getLocations();
		Node currentLocation = locationList.findLocationNode(locationName);
		if (currentLocation == null) {
			lblResult.setText("Location " + locationName + " not found in District " + districtName + ".");
			return;
		}

		// both the district and location exist, and the martyr
		// does not exist elsewhere.
		Location location = (Location) currentLocation.getElement();
		location.getMartyrs().addMartyrSorted(martyr);
		lblResult.setText("Martyr " + martyr.getName() + " successfully added to Location " + locationName
				+ " in District " + districtName + ".");
	}

	public Node findMartyrNodeInAllDistricts(String martyrName) {
		DoubleNode currentDistrict = districtList.getFront(); // Assuming districtList is accessible
		while (currentDistrict != null) {
			LinkedList locationList = currentDistrict.getElement().getLocations();
			Node currentLocation = locationList.getFront();
			while (currentLocation != null) {
				Location location = (Location) currentLocation.getElement();
				LinkedList martyrList = location.getMartyrs();
				Node foundMartyrNode = martyrList.findMartyrNode(martyrName); // Use the method on each martyr list
				if (foundMartyrNode != null) {
					return foundMartyrNode; // Martyr found
				}
				currentLocation = currentLocation.getNext();
			}
			currentDistrict = currentDistrict.getNext();
		}
		return null; // Martyr not found in any district or location
	}

	public void youngestOldestInLocation(String district, String locationName) {
		DoubleNode currentDistrict = districtList.findDistrictNode(district);
		if (currentDistrict != null) {
			LinkedList locationList = currentDistrict.getElement().getLocations();
			Node locationNode = locationList.findLocationNode(locationName);
			if (locationNode != null) {
				Location location = (Location) locationNode.getElement();
				LinkedList martyrList = location.getMartyrs();
				Node martyrNode = martyrList.getFront();

				if (martyrNode != null) {
					Martyr youngest = (Martyr) martyrNode.getElement();
					Martyr oldest = (Martyr) martyrNode.getElement();

					while (martyrNode != null) {
						Martyr martyr = (Martyr) martyrNode.getElement();
						if (martyr.getAge() < youngest.getAge()) {
							youngest = martyr;
						}
						if (martyr.getAge() > oldest.getAge()) {
							oldest = martyr;
						}
						martyrNode = martyrNode.getNext();
					}
					if (youngest.getName().equalsIgnoreCase(oldest.getName()))
						taLocation.appendText("Youngest and Oldest Martyr: " + youngest.getName() + ", Age: "
								+ youngest.getAge() + "\n");
					else {

						taLocation.appendText(
								"Youngest Martyr: " + youngest.getName() + ", Age: " + youngest.getAge() + "\n");
						taLocation.appendText("Oldest Martyr: " + oldest.getName() + ", Age: " + oldest.getAge());
					}

				} else {
					taLocation.appendText(
							"No martyrs found in " + locationName + " within " + district + " district." + "\n");
				}
			} else {
				taLocation.appendText("Location " + locationName + " not found in " + district + " district." + "\n");
			}
		} else {
			taLocation.appendText("District " + district + " not found." + "\n");
		}
	}

	public void calculateAverageAgeOfMartyrsInLocation(String district, String locationName) {
		int martyrNumber = 0;
		int averageAge = 0;
		DoubleNode currentDistrict = districtList.findDistrictNode(district);
		if (currentDistrict != null) {
			if (currentDistrict.getElement().getName().equalsIgnoreCase(district)) {
				LinkedList locationList = currentDistrict.getElement().getLocations();
				Node currentLocation = locationList.findLocationNode(locationName);
				if (currentLocation != null) {

					Location location = (Location) currentLocation.getElement();
					LinkedList martyrList = location.getMartyrs();
					Node currentMartyr = martyrList.getFront();
					while (currentMartyr != null) {
						martyrNumber++;
						averageAge += martyr.getAge();
						currentMartyr = currentMartyr.getNext();
					}
				}
			}
		}
		if (martyrNumber == 0)
			taLocation.appendText("Average age of martyrs is 0" + "\n");
		taLocation.appendText("Average age of martyrs is: " + (double) averageAge / martyrNumber + "\n");
	}

	public void maleAndFemaleNumberInLocation(String district, String locationName) {
		int maleCounter = 0, femaleCounter = 0, notMentioned = 0;

		DoubleNode currentDistrict = districtList.findDistrictNode(district);
		if (currentDistrict != null) {
			LinkedList locationList = currentDistrict.getElement().getLocations();
			Node currentLocation = locationList.findLocationNode(locationName);
			if (currentLocation != null) {
				Location location = (Location) currentLocation.getElement();
				LinkedList martyrList = location.getMartyrs();
				Node currentMartyr = martyrList.getFront();
				while (currentMartyr != null) {
					if (((Martyr) currentMartyr.getElement()).getGender() == 'M')
						maleCounter++;
					else if (((Martyr) currentMartyr.getElement()).getGender() == 'F')
						femaleCounter++;
					else
						notMentioned++;
					currentMartyr = currentMartyr.getNext();
				}
			} else
				taLocation.appendText("Location isn't found" + "\n");
		} else
			taLocation.appendText("District isn't found" + "\n");

		taLocation.appendText("Male -->" + maleCounter + "\n");
		taLocation.appendText("female -->" + femaleCounter + "\n");
		if (notMentioned > 0)
			taLocation.appendText("others -->" + notMentioned + "\n");

	}

	public void numberOfMartyrsInLocation(String district, String locationName) {
		int counter = 0;
		DoubleNode currentDistrict = districtList.findDistrictNode(district);
		if (currentDistrict != null) {
			if (currentDistrict.getElement().getName().equalsIgnoreCase(district)) {
				LinkedList locationList = currentDistrict.getElement().getLocations();
				Node currentLocation = locationList.findLocationNode(locationName);
				if (currentLocation != null) {
					Location location = (Location) currentLocation.getElement();
					LinkedList martyrList = location.getMartyrs();
					Node currentMartyr = martyrList.getFront();
					while (currentMartyr != null) {
						counter++;
						currentMartyr = currentMartyr.getNext();
					}
				}
			}
		}
		taLocation.appendText("Number of martyrs -> " + counter + "\n");
	}

	public boolean removeLocation(String districtName, String locationName) {
		DoubleNode current = districtList.findDistrictNode(districtName);
		if (current != null) {
			Node targetLocation = current.getElement().getLocations().findLocationNode(locationName);
			if (targetLocation != null) {
				Location location = new Location(locationName);
				location.getMartyrs().clear();
				current.getElement().getLocations().remove(current.getElement().getLocations().find(location));

				return true;
			}
			return false;
		}
		return false;
	}

	public boolean updateLocationRecord(String districtName, String oldName, String newName) {
		if (oldName == newName)
			return false;

		DoubleNode current = districtList.findDistrictNode(districtName);
		if (current == null)
			return false;

		Node locationNode = current.getElement().getLocations().findLocationNode(oldName);
		if (locationNode == null)
			return false;

		Location location = (Location) locationNode.getElement();
		location.getMartyrs().clear();
		current.getElement().getLocations().removeLocation(location); // Remove the old location
		location.setName(newName); // Update the location's name
		current.getElement().getLocations().insertLocationSorted(location);
		return true;
	}

	public void insertLocation(String districtName, String locationName) {

		DoubleNode currentDistrict = districtList.getFront();
		while (currentDistrict != null) {
			if (currentDistrict.getElement().getName().equalsIgnoreCase(districtName)) {
				Location location = new Location(locationName);
				Node locNode = currentDistrict.getElement().getLocations().findLocationNode(loc.getName());
				if (locNode == null) {
					currentDistrict.getElement().getLocations().insertLocationSorted(location);
					break;
				}
				return;
			}
			currentDistrict = currentDistrict.getNext();
		}
	}

	public void printADistrict(String district) {
		File file = new File("Output.txt");

		try (PrintWriter write = new PrintWriter(file)) {
			DoubleNode currentDistrict = districtList.findDistrictNode(district);
			if (currentDistrict != null) {
				write.write("District -> " + currentDistrict.getElement().getName() + "\n");

				Node currentLocation = currentDistrict.getElement().getLocations().getFront();
				while (currentLocation != null) { // Use while loop to iterate through all locations
					write.write("\tLocation -> " + ((Location) currentLocation.getElement()).getName());
					write.write("\n");

					Node currentMartyr = ((Location) currentLocation.getElement()).getMartyrs().getFront();
					while (currentMartyr != null) { // Use while loop to iterate through all martyrs
						write.write("\t\tMartyr -> " + ((Martyr) currentMartyr.getElement()).getName() + "n");
						write.write("\n");
						currentMartyr = currentMartyr.getNext(); // Move to the next martyr within the current location
					}
					currentLocation = currentLocation.getNext(); // Move to the next location within the district
				}
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	public int numberOfMartyrsInADate(String date) {
		int totalNumber = 0;
		DoubleNode currentDistrict = districtList.getFront();
		while (currentDistrict != null) {
			LinkedList locationList = currentDistrict.getElement().getLocations();
			Node currentLocation = locationList.getFront();
			while (currentLocation != null) {
				Location location = (Location) currentLocation.getElement();

				LinkedList martyrList = location.getMartyrs();
				Node currentMartyr = martyrList.getFront();
				while (currentMartyr != null) {
					if (((Martyr) currentMartyr.getElement()).getDate().equalsIgnoreCase(date))
						totalNumber++;
					currentMartyr = currentMartyr.getNext();

				}

				currentLocation = currentLocation.getNext();
			}

			currentDistrict = currentDistrict.getNext();
		}
		return totalNumber;

	}

	public void findMostRepeatedDate(DoubleNode targetNode) {
		String maxDateStr = null;
		int maxCount = 0;

		if (targetNode != null) {
			LinkedList locationList = (LinkedList) targetNode.getElement().getLocations();

			// Iterate through locations within the target district
			Node currentLocation = locationList.getFront();
			while (currentLocation != null) {
				LinkedList martyrList = ((Location) currentLocation.getElement()).getMartyrs();

				// Iterate through martyrs and compare counts
				int currentCount = 0;
				String currentDateStr = null;
				Node currentMartyr = martyrList.getFront();
				while (currentMartyr != null) {
					Martyr martyr = (Martyr) currentMartyr.getElement();
					String dateStr = martyr.getDate();

					if (currentDateStr == null || !dateStr.equals(currentDateStr)) {
						// New date encountered, reset counters
						currentDateStr = dateStr;
						currentCount = 1;
					} else {
						currentCount++;
					}
					if (currentCount > maxCount) {
						maxCount = currentCount;
						maxDateStr = currentDateStr;
					}
					currentMartyr = currentMartyr.getNext();
				}

				currentLocation = currentLocation.getNext();
			}
		}

		ta.appendText("Most repeated date : " + maxDateStr + " with a " + maxCount + " martyrs");
	}

	public void calculateAverageAgeOfMartyrsInDistrict(String district) {
		int martyrNumber = 0;
		int averageAge = 0;
		DoubleNode currentDistrict = districtList.getFront();
		while (currentDistrict != null) {

			if (currentDistrict.getElement().getName().equalsIgnoreCase(district)) {
				LinkedList locationList = currentDistrict.getElement().getLocations();
				Node currentLocation = locationList.getFront();

				while (currentLocation != null) {
					Location location = (Location) currentLocation.getElement();
					LinkedList martyrList = location.getMartyrs();
					Node currentMartyr = martyrList.getFront();

					while (currentMartyr != null) {
						martyrNumber++;
						averageAge += ((Martyr) currentMartyr.getElement()).getAge();
						currentMartyr = currentMartyr.getNext();

					}

					currentLocation = currentLocation.getNext();
				}
				break; // if District is found
			}

			currentDistrict = currentDistrict.getNext();
		}
		if (martyrNumber == 0)
			ta.appendText("Average is 0" + "\n");
		else
			ta.appendText("Average age is: " + (double) averageAge / martyrNumber + "\n");
	}

	public void maleAndFemaleNumberInDistrict(String district) {
		int maleCounter = 0, femaleCounter = 0, notMentioned = 0;

		DoubleNode currentDistrict = districtList.getFront();
		while (currentDistrict != null) {
			if (currentDistrict.getElement().getName().equalsIgnoreCase(district)) {
				LinkedList locationList = currentDistrict.getElement().getLocations();
				Node currentLocation = locationList.getFront();
				while (currentLocation != null) {
					Location location = (Location) currentLocation.getElement();

					LinkedList martyrList = location.getMartyrs();
					Node currentMartyr = martyrList.getFront();
					while (currentMartyr != null) {
						if (((Martyr) currentMartyr.getElement()).getGender() == 'M') {
							maleCounter++;
						} else if (((Martyr) currentMartyr.getElement()).getGender() == 'F') {
							femaleCounter++;
						} else {
							notMentioned++;
						}
						currentMartyr = currentMartyr.getNext();
					}

					currentLocation = currentLocation.getNext();
				}

				// Update the TextArea within the if condition, after the calculations are
				// complete.
				ta.appendText("Male --> " + maleCounter + "\n");
				ta.appendText("Female --> " + femaleCounter + "\n");
				if (notMentioned > 0) {
					ta.appendText("Others --> " + notMentioned + "\n");
				}
				break; // Exit the loop after updating the text area
			} else {
				currentDistrict = currentDistrict.getNext();
			}
		}
	}

	public void numberOfMartyrsInDistrict(String district) {
		int counter = 0;
		DoubleNode currentDistrict = districtList.getFront();
		while (currentDistrict != null) {
			if (currentDistrict.getElement().getName().equalsIgnoreCase(district)) {
				LinkedList locationList = currentDistrict.getElement().getLocations();
				Node currentLocation = locationList.getFront();
				while (currentLocation != null) {
					Location location = (Location) currentLocation.getElement();

					LinkedList martyrList = location.getMartyrs();
					Node currentMartyr = martyrList.getFront();
					while (currentMartyr != null) {
						counter++;
						currentMartyr = currentMartyr.getNext();
					}

					currentLocation = currentLocation.getNext();
				}
				break;
			} else
				currentDistrict = currentDistrict.getNext();
		}
		ta.appendText("Number of martyrs in " + district + ": " + counter + "\n");
	}

	public boolean removeDistrict(String name) {
		if (name.isEmpty())
			return false;
		DoubleNode current = districtList.getFront();
		while (current != null) {
			District district = current.getElement();
			if (district.getName().equalsIgnoreCase(name)) {
				districtList.remove(district);
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	public boolean updateDistrictRecord(String oldName, String newName) {
		if (oldName.equalsIgnoreCase(newName))
			return false;
		if (oldName.isEmpty() || newName.isEmpty())
			return false;
		DoubleNode current = districtList.getFront();
		while (current != null) {
			District district = current.getElement();
			if (district.getName().equalsIgnoreCase(oldName)) {
				district.getLocations().clear();
				districtList.remove(district);
				district.setName(newName);
				districtList.addSorted(district);
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	private boolean isNumeric(String s) {
		if (s == null) {
			return false;
		}
		try {
			Integer.parseInt(s.trim()); // Use Integer.parseInt to support a larger range of numbers
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isChar(String s) {
		if (s == null) {
			return false;
		}
		String trimmed = s.trim();
		// Expecting only one character
		return trimmed.length() == 1 && Character.isLetter(trimmed.charAt(0));
	}

	public void printDistrictsWithLocationsOnly() {

		DoubleNode currentDistrict = districtList.getFront();
		while (currentDistrict != null) {
			String district = currentDistrict.getElement().getName();
			System.out.println("District: " + district);

			// Print locations
			LinkedList locationList = (LinkedList) currentDistrict.getElement().getLocations();
			Node currentLocation = (Node) locationList.getFront();
			while (currentLocation != null) {
				System.out.println("\tLocation: " + ((Location) currentLocation.getElement()).getName());
				currentLocation = currentLocation.getNext();
			}

			currentDistrict = currentDistrict.getNext();
		}

	}

	public void NavigateDistricts(String districtName) {

		DoubleNode currentDistrict = districtList.findDistrictNode(districtName);
		if (currentDistrict != null) {

		}

	}

	public void navigateLocations(String districtName, String locationName) {

		DoubleNode currentDistrict = districtList.findDistrictNode(districtName);
		if (currentDistrict != null) {
			District district = currentDistrict.getElement();
			Node locNode = district.getLocations().findLocationNode(locationName);
			if (locNode != null) {
				numberOfMartyrsInLocation(district.getName(), locationName);
				maleAndFemaleNumberInLocation(district.getName(), locationName);
				calculateAverageAgeOfMartyrsInLocation(district.getName(), locationName);
				youngestOldestInLocation(district.getName(), locationName);
			}
		}

	}

	public void printDistrictsWithLocationsAndMartyrs() {
		File file = new File("Output.txt");

		try (PrintWriter write = new PrintWriter(file)) {
			DoubleNode currentDistrict = districtList.getFront();
			while (currentDistrict != null) {
				String district = currentDistrict.getElement().getName();
				write.write("District -> " + district + "\n");

				LinkedList locationList = currentDistrict.getElement().getLocations();
				Node currentLocation = locationList.getFront();
				while (currentLocation != null) {
					Location location = (Location) currentLocation.getElement();
					write.write("\tLocation -> " + location.getName() + "\n");

					LinkedList martyrList = location.getMartyrs();
					Node currentMartyr = martyrList.getFront();
					while (currentMartyr != null) {
						write.write("\t\tMartyr -> " + ((Martyr) currentMartyr.getElement()).getName() + " \n");
						currentMartyr = currentMartyr.getNext();
					}

					currentLocation = currentLocation.getNext();
				}

				currentDistrict = currentDistrict.getNext();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
