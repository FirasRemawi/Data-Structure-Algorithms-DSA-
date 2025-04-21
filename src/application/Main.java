/*package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	// Attributes for driver class
	private int counter;
	private MenuBar menuBar;
	private Menu menu2, menu1;
	private MenuItem openItem, item1, item2, item3, item4, item5, item6, item7, item8, item9, item10;
	private BorderPane bdPane;
	private TextArea txtArea, txtArea2, txtArea3;
	private Martyr[] martyrs;
	private VBox vBox, vBoxMain, vBoxDelete, vBoxSearch, vBoxDisplay;
	private HBox hBox, hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, hBox7, hBox8, hBox9;
	private FileChooser fChooser;
	private Image image;
	private ImageView imageView;
	private Button btInsert, btDelete, btSearch, btClear, btSubmitInsert, btReturnMain, btReturnMainDelete,
			btReturnMainSearch, btReturnMainDisplay, btSubmitDelete, btSubmitSearch, btDisplay, btSubmitDisplay,
			btCheck;
	private Label lblAction, lblActionDelete, lblActionSearch, lblActionDisplay, lblInst, lblInstDelete, lblInstSearch,
			lblInstDisplay, lblName, lblAge, lblEvLoc, lblDate, lblGender, lblNameDelete, lblNameSearch, lblTextDisplay,
			lblChoiceDisplay, lblGenderDisplay, lblMainInst, lblDeleteDate, lblDeleteEv, lblInfo;
	private TextField txtName, txtAge, txtEvLoc, txtDate, txtNameDelete, txtNameSearch, txtTextDisplay, txtDeleteDate,
			txtDeleteEv;
	private GridPane gdPane, gdPane1;
	private RadioButton rbMale, rbFemale, rbUnknown, rbMale2, rbFemale2, rbUnknown2;
	private ToggleGroup group, group2;
	private ComboBox<String> cmBox;
	private Scene sceneMain, sceneInsert, sceneDelete, sceneSearch, sceneDisplay;

	@Override
	public void start(Stage primaryStage) {
		martyrs = new Martyr[16];

		// Image
		image = new Image("https://freepngimg.com/thumb/folder/20-folder-png-image.png");
		imageView = new ImageView(image);
		imageView.setFitWidth(16);
		imageView.setFitHeight(16);

		// Menu
		menuBar = new MenuBar();
		menu1 = new Menu("Color");
		menu2 = new Menu("File");

		// MenuItem
		openItem = new MenuItem("Open");
		openItem.setGraphic(imageView);
		item1 = new MenuItem("Light Green");
		item1.setOnAction(e -> bdPane.setStyle("-fx-background-color: lightgreen"));
		item2 = new MenuItem("Baby Blue");
		item2.setOnAction(e -> bdPane.setStyle("-fx-background-color: lightblue"));
		item3 = new MenuItem("Biege");
		item3.setOnAction(e -> bdPane.setStyle("-fx-background-color: Cornsilk"));
		item4 = new MenuItem("Plum");
		item4.setOnAction(e -> bdPane.setStyle("-fx-background-color: PLUM"));
		item5 = new MenuItem("Salmon");
		item5.setOnAction(e -> bdPane.setStyle("-fx-background-color: salmon"));
		item6 = new MenuItem("Pink");
		item6.setOnAction(e -> bdPane.setStyle("-fx-background-color: lightpink"));
		item7 = new MenuItem("White");
		item7.setOnAction(e -> bdPane.setStyle("-fx-background-color: white"));
		item8 = new MenuItem("Aquamarine");
		item8.setOnAction(e -> bdPane.setStyle("-fx-background-color: AQUAMARINE"));
		item9 = new MenuItem("Deep Sky Blue");
		item9.setOnAction(e -> bdPane.setStyle("-fx-background-color: DEEPSKYBLUE"));
		item10 = new MenuItem("Medium Sea Green");
		item10.setOnAction(e -> bdPane.setStyle("-fx-background-color: MEDIUMSEAGREEN"));
		menu1.getItems().addAll(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10);
		menu2.getItems().addAll(openItem);
		menuBar.getMenus().addAll(menu2, menu1);

		// TextArea
		txtArea = new TextArea();
		txtArea.setPrefHeight(350);
		txtArea.setMaxWidth(700);
		txtArea.setVisible(false);
		txtArea.setEditable(false);
		txtArea.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));

		txtArea2 = new TextArea();
		txtArea2.setPrefHeight(350);
		txtArea2.setMaxWidth(500);
		txtArea2.setVisible(false);
		txtArea2.setEditable(false);
		txtArea2.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));

		txtArea3 = new TextArea();
		txtArea3.setPrefHeight(350);
		txtArea3.setMaxWidth(500);
		txtArea3.setVisible(false);
		txtArea3.setEditable(false);
		txtArea3.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));

		// FileChooser
		fChooser = new FileChooser();

		// Buttons
		btInsert = new Button("Insert");
		btInsert.setStyle("-fx-font-size: 16px;");
		btInsert.setPrefHeight(35);
		btDelete = new Button("Delete");
		btDelete.setMaxWidth(100);
		btDelete.setStyle("-fx-font-size: 16px;");
		btDelete.setPrefHeight(35);
		btSearch = new Button("Search");
		btSearch.setStyle("-fx-font-size: 16px;");
		btSearch.setMaxWidth(200);
		btSearch.setPrefHeight(35);
		btClear = new Button("Clear");
		btSubmitInsert = new Button("Insert");
		btReturnMain = new Button("Return to Main Menu");
		btSubmitDelete = new Button("Delete");
		btSubmitSearch = new Button("Search");
		btSubmitDisplay = new Button("Display");
		btDisplay = new Button("Display number of same age/gender/date of death");
		btDisplay.setStyle("-fx-font-size: 16px;");
		btDisplay.setPrefHeight(35);
		btReturnMainDelete = new Button("Return to Main Menu");
		btReturnMainSearch = new Button("Return to Main Menu");
		btReturnMainDisplay = new Button("Return to Main Menu");
		btCheck = new Button("Check");
		btInsert.setVisible(false);
		btDelete.setVisible(false);
		btSearch.setVisible(false);
		btDisplay.setVisible(false);

		// Labels
		lblInst = new Label();
		lblInst.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lblInstDelete = new Label();
		lblInstDelete.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lblInstSearch = new Label();
		lblInstSearch.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lblInstDisplay = new Label();
		lblInstDisplay.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lblAction = new Label();
		lblAction.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
		lblAction.setStyle(
				"-fx-font-size: 20px; -fx-text-fill: green; -fx-font-weight: bold; -fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 2;");
		lblActionDelete = new Label();
		lblActionDelete.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
		lblActionDelete.setStyle(
				"-fx-font-size: 20px; -fx-text-fill: green; -fx-font-weight: bold; -fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 2;");
		lblActionSearch = new Label();
		lblActionSearch.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
		lblActionSearch.setStyle(
				"-fx-font-size: 20px; -fx-text-fill: green; -fx-font-weight: bold; -fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 2;");
		lblActionDisplay = new Label();
		lblActionDisplay.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
		lblActionDisplay.setStyle(
				"-fx-font-size: 20px; -fx-text-fill: green; -fx-font-weight: bold; -fx-background-color: transparent; -fx-border-color: red; -fx-border-width: 2;");
		lblName = new Label("*Name: ");
		lblName.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblAge = new Label(" Age: ");
		lblAge.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblEvLoc = new Label("*Event Location: ");
		lblEvLoc.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblDate = new Label("*Date of death: ");
		lblDate.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblGender = new Label("*Gender: ");
		lblGender.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblNameDelete = new Label("Name: ");
		lblNameDelete.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblNameSearch = new Label("Name: ");
		lblNameSearch.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblTextDisplay = new Label();
		lblTextDisplay.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblChoiceDisplay = new Label("Display the number of martyrs with the: ");
		lblChoiceDisplay.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lblGenderDisplay = new Label("Gender:    ");
		lblGenderDisplay.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblMainInst = new Label("     Martyr's Data Program\n\nPlease open a file to continue");
		lblMainInst.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
		lblDeleteDate = new Label("Date Of Death: ");
		lblDeleteDate.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblDeleteEv = new Label("Event Location: ");
		lblDeleteEv.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 17));
		lblInfo = new Label("Choose Operation: ");
		lblInfo.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		lblInfo.setVisible(false);

		// TextFields
		txtName = new TextField();
		txtAge = new TextField();
		txtEvLoc = new TextField();
		txtDate = new TextField();
		txtDate.setPromptText("MM/DD/YYYY");
		txtNameDelete = new TextField();
		txtNameSearch = new TextField();
		txtTextDisplay = new TextField();
		txtDeleteDate = new TextField();
		txtDeleteEv = new TextField();

		// Panes
		bdPane = new BorderPane();
		gdPane = new GridPane();
		gdPane.setPadding(new Insets(20, 20, 20, 20));
		gdPane.setAlignment(Pos.CENTER);
		gdPane.setHgap(10);
		gdPane.setVgap(10);
		vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(10, 10, 10, 10));
		hBox = new HBox(30);
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox1 = new HBox(30);
		hBox1.setAlignment(Pos.CENTER);
		vBoxMain = new VBox(20);
		vBoxMain.setAlignment(Pos.CENTER);
		vBoxMain.setPadding(new Insets(10, 10, 10, 10));
		vBoxDelete = new VBox(20);
		vBoxDelete.setAlignment(Pos.CENTER);
		vBoxDelete.setPadding(new Insets(10, 10, 10, 10));
		hBox2 = new HBox(20);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.setPadding(new Insets(10, 10, 10, 10));
		hBox3 = new HBox(20);
		hBox3.setAlignment(Pos.CENTER);
		hBox3.setPadding(new Insets(10, 10, 10, 10));
		vBoxSearch = new VBox(20);
		vBoxSearch.setAlignment(Pos.CENTER);
		vBoxSearch.setPadding(new Insets(10, 10, 10, 10));
		hBox4 = new HBox(20);
		hBox4.setAlignment(Pos.CENTER);
		hBox4.setPadding(new Insets(10, 10, 10, 10));
		hBox5 = new HBox(20);
		hBox5.setAlignment(Pos.CENTER);
		hBox5.setPadding(new Insets(10, 10, 10, 10));
		gdPane1 = new GridPane();
		gdPane1.setPadding(new Insets(20, 20, 20, 20));
		gdPane1.setAlignment(Pos.CENTER);
		gdPane1.setHgap(10);
		gdPane1.setVgap(10);
		hBox6 = new HBox(20);
		hBox6.setAlignment(Pos.CENTER);
		hBox6.setPadding(new Insets(10, 10, 10, 10));
		hBox7 = new HBox(20);
		hBox7.setAlignment(Pos.CENTER);
		hBox7.setPadding(new Insets(10, 10, 10, 10));
		hBox8 = new HBox(20);
		hBox8.setAlignment(Pos.CENTER);
		hBox8.setPadding(new Insets(10, 10, 10, 10));
		vBoxDisplay = new VBox(20);
		vBoxDisplay.setAlignment(Pos.CENTER);
		vBoxDisplay.setPadding(new Insets(10, 10, 10, 10));
		hBox9 = new HBox(20);
		hBox9.setAlignment(Pos.CENTER);
		hBox9.setPadding(new Insets(10, 10, 10, 10));

		// RadioButtons
		group = new ToggleGroup();
		group2 = new ToggleGroup();
		rbMale = new RadioButton("Male");
		rbFemale = new RadioButton("Female");
		rbMale.setToggleGroup(group);
		rbFemale.setToggleGroup(group);
		rbUnknown = new RadioButton("Unknown");
		rbUnknown.setToggleGroup(group);
		rbMale2 = new RadioButton("Male");
		rbFemale2 = new RadioButton("Female");
		rbMale2.setToggleGroup(group2);
		rbFemale2.setToggleGroup(group2);
		rbUnknown2 = new RadioButton("Unknown");
		rbUnknown2.setToggleGroup(group2);

		// ComboBox
		cmBox = new ComboBox<>();
		cmBox.getItems().add("-");
		cmBox.getItems().add("Same Ages");
		cmBox.getItems().add("Same Gender");
		cmBox.getItems().add("Same Date of Death");

		// Event handler for the open MenuItem which reads data from a file using
		// readFile method
		openItem.setOnAction(e -> readFile(primaryStage));

		// Event handler for return to main button from insert window
		btReturnMain.setOnAction(e -> returnMain(primaryStage));

		// Event handler for return to main button from delete window
		btReturnMainDelete.setOnAction(e -> returnMain(primaryStage));

		// Event handler for return to main button from search window
		btReturnMainSearch.setOnAction(e -> returnMain(primaryStage));

		// Event handler for return to main button from display window
		btReturnMainDisplay.setOnAction(e -> returnMain(primaryStage));

		// Event handler for insert button in insert window to add a new martyr to the
		// list using insertToList method
		btSubmitInsert.setOnAction(e -> insertToList());

		// Event handler for insert button in main window to organize the visibility of
		// the nodes
		btInsert.setOnAction(e -> {
			lblAction.setVisible(false);
			txtName.clear();
			txtAge.clear();
			txtEvLoc.clear();
			txtDate.clear();
			rbMale.setSelected(false);
			rbFemale.setSelected(false);
			rbUnknown.setSelected(false);
			// Instruction for the user to know what to do
			lblInst.setText("Please fill the following data to insert: \nNote that (*) means required field!");
			// Switch to the insert window
			primaryStage.setScene(sceneInsert);
			primaryStage.setTitle("Insert Window");
			primaryStage.show();
		});

		// Event handler for the check button in the delete scene to check the existence
		// of the martyr using Check method
		btCheck.setOnAction(e -> {
			txtArea3.setText("");
			// If the name is empty it will inform the user
			if (txtNameDelete.getText().isEmpty()) {
				lblActionDelete.setText("Enter a name first!");
				lblActionDelete.setVisible(true);
			}
			// If the check method returned 1 it will inform the user the the martyr has
			// been found and can be deleted
			else if (Check(txtNameDelete.getText()) == 1) {
				lblActionDelete.setText(txtNameDelete.getText() + " has been found press delete to delete him/her");
				lblActionDelete.setVisible(true);
				txtArea3.setVisible(false);
				btSubmitDelete.setVisible(true);
				btSubmitDelete.setOnAction(e1 -> {
					lblActionDelete.setVisible(true);
					// If the name was empty it will inform the user
					if (txtNameDelete.getText().isEmpty()) {
						lblActionDelete.setText("Name is empty!");
					} else
						// If the name wasn't empty it will be deleted
						delete(txtNameDelete.getText());
				});
			}
			// If the check method returned a number greater than 1 it will inform the user
			// that there's more than one martyr with the same name
			else if (Check(txtNameDelete.getText()) > 1) {
				btSubmitDelete.setVisible(true);
				btSubmitDelete.setOnAction(e1 -> {
					lblActionDelete.setVisible(true);
					if (!txtNameDelete.getText().isEmpty() && !txtNameDelete.getText().isEmpty()
							&& !txtDeleteEv.getText().isEmpty()) {
						delete2(txtNameDelete.getText());
					} else {
						if (txtNameDelete.getText().isEmpty()) {
							lblActionDelete.setText("Name is empty!");
						} else if (txtDeleteDate.getText().isEmpty()) {
							lblActionDelete.setText("You didn't enter the date of death!");
						} else if (txtDeleteEv.getText().isEmpty()) {
							lblActionDelete.setText("You didn't enter the event location!");
						}
					}
				});
			}
			// If the check method returned zero this means that there is no martyrs with
			// the same name in the list
			else if (Check(txtNameDelete.getText()) == 0) {
				txtArea3.setVisible(false);
				lblActionDelete.setText("Can't delete " + txtNameDelete.getText() + " because s/he doesn't exsit!");
				lblActionDelete.setVisible(true);
			}
		});

		// Event handler for the delete button in the main window to organize the nodes
		// and switch to the delete window
		btDelete.setOnAction(e -> {
			// Organizing the nodes to suit the request
			txtDeleteDate.clear();
			txtDeleteEv.clear();
			txtNameDelete.clear();
			lblActionDelete.setVisible(false);
			hBox9.setVisible(false);
			btSubmitDelete.setVisible(false);
			txtArea3.setVisible(false);
			// Instruction to tell the user what to do
			lblInstDelete.setText("Please fill the following data to delete: ");
			// switch to the delete window
			primaryStage.setScene(sceneDelete);
			primaryStage.setTitle("Delete Window");
			primaryStage.show();
		});

		// Event handler for the search button in the search window to search for a
		// martyr in the list using the search method
		btSubmitSearch.setOnAction(e -> {
			txtArea2.setText("");
			// If the name was empty it will inform the user
			if (txtNameSearch.getText().isEmpty()) {
				lblActionSearch.setText("Enter a name first!");
				// Organizing the nodes to suit the request
				lblActionSearch.setVisible(true);
				txtArea2.setVisible(false);
			} else {
				lblActionSearch.setText("");
				lblActionSearch.setVisible(true);
				// The name will be passed to the search method to check its existence
				search(txtNameSearch.getText());
			}
		});

		// Event handler for the search button in the main window to organize the nodes
		// and switch to the search window
		btSearch.setOnAction(e -> {
			// Organizing the nodes to suit the request
			txtNameSearch.clear();
			txtArea2.setVisible(false);
			lblActionSearch.setVisible(false);
			// Instruction to tell the user what to do
			lblInstSearch.setText("Please fill the following data to search: ");
			primaryStage.setScene(sceneSearch);
			primaryStage.setTitle("Search Window");
			primaryStage.show();
		});

		// Event handler for the display button in the main window to organize the nodes
		// and switch to the display window
		btDisplay.setOnAction(e -> {
			// Instruction to tell the user what to do
			lblInstDisplay.setText("Please choose an operation to display: ");
			// Organizing the nodes to suit the request
			hBox6.setVisible(false);
			hBox7.setVisible(false);
			lblActionDisplay.setVisible(false);
			btSubmitDisplay.setVisible(false);
			rbMale.setSelected(false);
			rbFemale.setSelected(false);
			rbUnknown.setSelected(false);
			txtTextDisplay.clear();
			txtTextDisplay.setPromptText("");
			cmBox.getSelectionModel().clearAndSelect(0);
			// Switch to the display window
			primaryStage.setScene(sceneDisplay);
			primaryStage.setTitle("Display Window");
			primaryStage.show();
		});

		// Event handling for the combo box
		cmBox.setOnAction(e -> {
			// String that will store the choice of the comboBox
			String choice = cmBox.getValue();

			// No operation wanted to be done
			if (choice.equals("-")) {
				// Organizing the nodes to suit the request
				hBox6.setVisible(false);
				hBox7.setVisible(false);
				lblActionDisplay.setVisible(false);
				btSubmitDisplay.setVisible(false);
			}
			// If the choice was Same Ages it will organize the nodes to suit that request
			else if (choice.equals("Same Ages")) {
				// Ask the user to enter the age
				lblTextDisplay.setText("Age: ");
				txtTextDisplay.clear();
				txtTextDisplay.setPromptText("");
				// Organizing the nodes to suit the request
				hBox6.setVisible(true);
				hBox7.setVisible(false);
				lblActionDisplay.setVisible(false);
				btSubmitDisplay.setVisible(true);

				// Event Handler for the display button in the display scene to display the
				// number of martyrs with a certain age using the displayNumberAge method
				btSubmitDisplay.setOnAction(e1 -> {
					lblActionDisplay.setVisible(true);
					// If the name was empty it will inform the user
					if (txtTextDisplay.getText().isEmpty()) {
						lblActionDisplay.setText("Enter age first!");
					} else
						// Printing the number of martyrs with a same certain age
						// Try-catch block for handling the possibility of the occurrence of the
						// NumberFormatException
						try {
							lblActionDisplay.setText(
									"The number of martyrs who their age is " + txtTextDisplay.getText() + " is: "
											+ displayNumberAge(Integer.parseInt(txtTextDisplay.getText())) + "");
						} catch (NumberFormatException e3) {
							lblActionDisplay.setText("Please enter an integer for the age not a string or a double!");
						}
				});

			}
			// If the choice was Same Gender it will organize the nodes to suit that request
			else if (choice.equals("Same Gender")) {
				// Organizing the nodes to suit the request
				hBox6.setVisible(false);
				hBox7.setVisible(true);
				lblActionDisplay.setVisible(false);
				btSubmitDisplay.setVisible(true);
				rbMale2.setSelected(false);
				rbFemale2.setSelected(false);
				rbUnknown2.setSelected(false);

				// Event Handler for the display button in the display scene to display the
				// number of martyrs with the same gender using the displayNumberGender method
				btSubmitDisplay.setOnAction(e1 -> {
					lblActionDisplay.setText("");
					lblActionDisplay.setVisible(true);
					// If no gender was selected it will inform the user
					if (!rbMale2.isSelected() && !rbFemale2.isSelected() && !rbUnknown2.isSelected())
						lblActionDisplay.setText("Choose a gender first!");
					else {
						if (rbMale2.isSelected()) {
							lblActionDisplay.setText("The number of martyrs who are " + "males" + " is: "
									+ displayNumberGender('M') + "");
						} else if (rbFemale2.isSelected()) {
							lblActionDisplay.setText("The number of martyrs who are " + "females" + " is: "
									+ displayNumberGender('F') + "");
						} else if (rbUnknown2.isSelected()) {
							lblActionDisplay.setText("The number of martyrs whose gender is unknown are: "
									+ displayNumberGender('N') + "");
						}
					}
				});

			}
			// If the choice was Same Date of Death it will organize the nodes to suit that
			// request
			else if (choice.equals("Same Date of Death")) {
				// Organizing the nodes to suit the request
				txtTextDisplay.clear();
				lblActionDisplay.setText("");
				lblTextDisplay.setText("Date Of Death: ");
				txtTextDisplay.setPromptText("MM/DD/YYYY");
				hBox6.setVisible(true);
				hBox7.setVisible(false);
				lblActionDisplay.setVisible(false);
				btSubmitDisplay.setVisible(true);

				// Event Handler for the display button in the display scene to display the
				// number of martyrs with the same date of death using the displayNumberDate
				// method
				btSubmitDisplay.setOnAction(e1 -> {
					lblActionDisplay.setText("");
					lblActionDisplay.setVisible(true);
					// If the date was empty it will inform the user about it
					if (txtTextDisplay.getText().isEmpty())
						lblActionDisplay.setText("Enter date of death first!");
					else if (CorrectDate(txtTextDisplay.getText()))
						lblActionDisplay
								.setText("The number of martyrs who their date of death is " + txtTextDisplay.getText()
										+ " is: " + displayNumberDate(txtTextDisplay.getText()) + "");
				});
			}
		});

		// Event Handler for the clear button in the insert scene to clear all the
		// textFields and remove the message using the clear method
		btClear.setOnAction(e -> {
			clear();
		});

		// Organizing and adding the nodes on the panes
		hBox.getChildren().addAll(btSubmitInsert, btClear);
		hBox1.getChildren().addAll(rbMale, rbFemale, rbUnknown);

		gdPane.add(lblName, 0, 0);
		gdPane.add(txtName, 1, 0);
		gdPane.add(lblAge, 0, 1);
		gdPane.add(txtAge, 1, 1);
		gdPane.add(lblEvLoc, 0, 2);
		gdPane.add(txtEvLoc, 1, 2);
		gdPane.add(lblDate, 0, 3);
		gdPane.add(txtDate, 1, 3);
		gdPane.add(lblGender, 0, 4);
		gdPane.add(hBox1, 1, 4);

		vBox.getChildren().addAll(lblInst, gdPane, lblAction, hBox, btReturnMain);

		vBoxMain.getChildren().addAll(lblMainInst, txtArea, lblInfo, btInsert, btDelete, btSearch, btDisplay);
		bdPane.setTop(menuBar);
		bdPane.setCenter(vBoxMain);

		hBox2.getChildren().addAll(lblNameDelete, txtNameDelete, hBox3);
		hBox9.getChildren().addAll(lblDeleteDate, txtDeleteDate, lblDeleteEv, txtDeleteEv);
		hBox3.getChildren().addAll(btCheck, btSubmitDelete);
		vBoxDelete.getChildren().addAll(lblInstDelete, hBox2, lblActionDelete, hBox9, txtArea3, btReturnMainDelete);

		hBox4.getChildren().addAll(lblNameSearch, txtNameSearch);
		hBox5.getChildren().addAll(btSubmitSearch);
		vBoxSearch.getChildren().addAll(lblInstSearch, hBox4, lblActionSearch, hBox5, txtArea2, btReturnMainSearch);

		hBox6.getChildren().addAll(lblTextDisplay, txtTextDisplay);
		hBox7.getChildren().addAll(lblGenderDisplay, rbMale2, rbFemale2, rbUnknown2);
		hBox8.getChildren().addAll(lblChoiceDisplay, cmBox);
		vBoxDisplay.getChildren().addAll(lblInstDisplay, hBox8, hBox6, hBox7, btSubmitDisplay, lblActionDisplay,
				btReturnMainDisplay);

		// Adding the panes on the scenes
		sceneMain = new Scene(bdPane, 1000, 800);
		sceneInsert = new Scene(vBox, 1000, 800);
		sceneDelete = new Scene(vBoxDelete, 1000, 800);
		sceneSearch = new Scene(vBoxSearch, 1000, 800);
		sceneDisplay = new Scene(vBoxDisplay, 1000, 800);

		// Setting the scene on the stage and showing it
		primaryStage.setScene(sceneMain);
		primaryStage.setTitle("Main Window");
		primaryStage.show();

	}

	// Method to add new martyr to the list
	public boolean add(Martyr martyr) {
		// This loop checks if the new martyr exists from before or not
		for (int i = 0; i < counter; i++) {
			if (martyrs[i] == null)
				continue;
			if ((martyrs[i].getDateOfDeath().equals(martyr.getDateOfDeath()))
					&& (martyrs[i].getName().trim().equalsIgnoreCase(martyr.getName().trim()))
					&& (martyrs[i].getEventLocation().trim().equalsIgnoreCase(martyr.getEventLocation().trim()))
					&& (martyrs[i].getGender() == martyr.getGender()) && (martyrs[i].getAge() == martyr.getAge())) {
				// If the martyr exists from before but his/her name wasn't unknown it won't be
				// added otherwise it will be added
				if (!martyr.getName().equals("Name unknown to B'Tselem")) {
					txtArea.appendText(martyr.getName() + " already exsits.\n");
					return false;
				}
			}
		}

		// If the counter becomes greater or equal to the martyrs length the array will
		// be resized using the resize method
		if (counter >= martyrs.length)
			resize();

		// The martyr will be added if no false conditions happened from before
		martyrs[counter++] = martyr;
		return true;
	}

	// Method to insert a new martyr to the list
	public boolean insert(String name, int age, String EvLoc, String date, char gender) {
		// Inserting a new martyr using the add method if it returns true
		if (add(new Martyr(name, age, EvLoc, date, gender))) {
			lblAction.setText(name + " has been inserted successfully!");
			return true;
		}
		// If the add method returns false, this means that the martyr wasn't added
		else {
			lblAction.setText(name + " hasn't been inserted because s/he already exsits!");
			return false;
		}

	}

	// Method to resize the list by returning a new copy of the list with different
	// length
	private void resize() {
		// Creating new list
		Martyr[] newList = new Martyr[martyrs.length * 2];
		// Copying the content of the old list on the new list
		for (int i = 0; i < martyrs.length; i++) {
			newList[i] = martyrs[i];
		}
		// Letting the old list referencing on the new list
		martyrs = newList;
	}

	// Method that searches for a martyr in the list using its name
	public void search(String name) {
		String gender = null;
		String agge = null;
		boolean martyrFound = false;

		// Loop that iterates on the martyrs list
		for (int i = 0; i < martyrs.length; i++) {
			// If the martyr was null it will skip and go to the next element in the list
			if (martyrs[i] == null)
				continue;
			// If the martyr was found it will print them
			if (martyrs[i].getName().trim().equalsIgnoreCase(name.trim())) {
				martyrFound = true;
				// If the gender was (M/m) this means that he's a male
				if (martyrs[i].getGender() == 'M' || martyrs[i].getGender() == 'm') {
					gender = "Male";
				}
				// If the gender was (F/f) this means that she's a female
				else if (martyrs[i].getGender() == 'F' || martyrs[i].getGender() == 'f') {
					gender = "Female";
				}
				// If the gender was (N) this means that its gender is unknowns
				else if (martyrs[i].getGender() == 'N') {
					gender = "NA";
				} else
					gender = "!";

				// If the age was less than zero this means that the age is unknown
				if (martyrs[i].getAge() < 0)
					agge = "NA";
				else
					agge = martyrs[i].getAge() + "";

				txtArea2.setVisible(true);
				lblActionSearch.setVisible(false);
				txtArea2.appendText("Name: " + martyrs[i].getName() + "\nAge: " + agge + "\nEvent Location: "
						+ martyrs[i].getEventLocation() + "\nDate Of Death (MM/DD/YYYY): " + martyrs[i].getDateOfDeath()
						+ "\nGender: " + gender + "\n\n");
			}
		}
		// If the martyrs wasn't found it will inform the user
		if (!martyrFound) {
			lblActionSearch.setVisible(true);
			lblActionSearch.setText("This martyr: " + name + " doesn't exsit!");
		}
	}

	// Method to check the existence of martyrs
	private int Check(String name) {
		String gender = null;
		String agge = null;
		int k = 0;

		// Loop that iterates on the martyrs list to check the existence of martyrs
		for (int i = 0; i < counter; i++) {
			// If the martyr was null it will skip and go to the next element in the list
			if (martyrs[i] == null)
				continue;
			// If the martyr exists, the k counter will be incremented
			if (martyrs[i].getName().trim().equalsIgnoreCase(name.trim())) {
				k++;
			}
		}

		// If k was greater than 1 this means that there is more than one martyr with
		// the same name
		if (k > 1) {
			// Organizing the nodes
			hBox9.setVisible(true);
			lblActionDelete.setVisible(true);
			lblActionDelete.setText(
					"Please enter the date of death and the event location of the martyr that you want to delete!");
			txtArea3.setVisible(true);

			// Loop that iterates on the martyrs list to print the martyrs who have the same
			// name
			for (int i = 0; i < counter; i++) {
				// If the martyr was null it will skip and go to the next element in the list
				if (martyrs[i] == null)
					continue;
				if (martyrs[i].getName().trim().equalsIgnoreCase(name.trim())) {
					// If the gender was (M/m) this means that he's a male
					if (martyrs[i].getGender() == 'M' || martyrs[i].getGender() == 'm') {
						gender = "Male";
					}
					// If the gender was (F/f) this means that she's a female
					else if (martyrs[i].getGender() == 'F' || martyrs[i].getGender() == 'f') {
						gender = "Female";
					}
					// This means that the gender is unknown
					else {
						gender = "NA";
					}
					// If the age was less than zero this means that its unknown
					if (martyrs[i].getAge() < 0)
						agge = "NA";
					else
						agge = martyrs[i].getAge() + "";
					txtArea3.appendText("Name: " + martyrs[i].getName() + "\nAge: " + agge + "\nEvent Location: "
							+ martyrs[i].getEventLocation() + "\nDate Of Death: " + martyrs[i].getDateOfDeath()
							+ "\nGender: " + gender + "\n\n");
				}
			}
		}
		return k;
	}

	// Method to read from a file
	private void readFile(Stage stage) {
		File f = fChooser.showOpenDialog(stage);
		txtArea.setText("");
		// Checks if the file was null
		if (f != null) {
			// Informing the user of the file name that was chosen and any error while
			// reading
			lblMainInst.setText("You opened this file: " + f.getName() + "\nReading from file warnings:");

			// Organizing the nodes
			btInsert.setVisible(true);
			btDelete.setVisible(true);
			btSearch.setVisible(true);
			btDisplay.setVisible(true);
			txtArea.setVisible(true);
			lblInfo.setVisible(true);

			// try-catch block to handle any possible exception that might occurs
			try (Scanner read = new Scanner(f)) {
				// Skip the first line
				read.nextLine();
				// Keep reading from the file while it has content
				while (read.hasNextLine()) {
					String s = read.nextLine();
					// Split the line by ","
					String[] line = s.split(",");
					// If the format of the line was wrong
					if (line.length != 5)
						throw new IndexOutOfBoundsException("Incorrect inputs check the format!\n");
					try {
						// The first entry represents the name
						String name = line[0];
						int age;

						// The second entry represents the age
						if (line[1].isEmpty()) {
							age = -1;
							txtArea.appendText(name + " doesn't have an age.\n");
						} else {
							// Checks if the age was a digit or not
							if (isNumeric(line[1]))
								age = Integer.parseInt(line[1]);
							else {
								age = -1;
								txtArea.appendText(name + " has an invalid age.\n");
							}
						}
						// The third entry represents the event location
						String evLoc = line[2];
						// The fourth entry represents the date of death
						String date = line[3];
						if (!CorrectDate(date)) {
							txtArea.appendText(name + " has invalid date of death\n");
						}
						char gender = '?';

						// The fifth entry represents the gender
						if (line[4].equals("NA") || line[4].isEmpty())
							gender = 'N';
						else
							gender = line[4].charAt(0);

						if (gender != 'm' && gender != 'M' && gender != 'F' && gender != 'f' && gender != 'N')
							throw new InputMismatchException(name + ": Gender must only be M/m/F/f\n");

						add(new Martyr(name, age, evLoc, date, gender));

					}
					// Catch blocks to handle exceptions
					catch (InputMismatchException e1) {
						txtArea.appendText(e1.getMessage());
					} catch (IndexOutOfBoundsException e1) {
						txtArea.appendText(e1.getMessage());
					} catch (NumberFormatException e1) {
						txtArea.appendText(e1.getMessage() + "\n");
					} catch (Exception e1) {
						txtArea.appendText(e1.getMessage());
					}
				}
			}
			// Catch blocks to handle exceptions and organize nodes
			catch (FileNotFoundException e1) {
				lblMainInst.setText(e1.getMessage());
				txtArea.setVisible(false);
				lblInfo.setVisible(false);
				btInsert.setVisible(false);
				btDelete.setVisible(false);
				btSearch.setVisible(false);
				btDisplay.setVisible(false);
			} catch (NoSuchElementException e1) {
				lblMainInst.setText(e1.getMessage());
				txtArea.setVisible(false);
				lblInfo.setVisible(false);
				btInsert.setVisible(false);
				btDelete.setVisible(false);
				btSearch.setVisible(false);
				btDisplay.setVisible(false);
			} catch (Exception e1) {
				lblMainInst.setText(e1.getMessage());
			}
		}
	}

	// Method to insert a new martyr to the list
	private void insertToList() {
		lblAction.setVisible(true);
		// try-catch block to handle any exception that might occurs
		try {
			String name, date, event;
			// If all fields were empty it will inform the user
			if (txtName.getText().trim().isEmpty() && txtAge.getText().isEmpty() && txtEvLoc.getText().isEmpty()
					&& txtDate.getText().isEmpty()) {
				throw new InputMismatchException("Fileds are empty");
			}
			// If the name was empty it will inform the user
			if (txtName.getText().trim().isEmpty()) {
				throw new InputMismatchException("Name is empty");
			} else {
				name = txtName.getText();
			}
			int age;
			// If the age was empty it will inform the user
			if (txtAge.getText().trim().isEmpty()) {
				age = -1;
				lblAction.setText("Warning: Age is empty!");
			} else {
				age = Integer.parseInt(txtAge.getText());
				if (age < 0)
					throw new InputMismatchException("Age must be greater or equal to zero!");
			}
			// If the event location was empty it will inform the user
			if (txtEvLoc.getText().trim().isEmpty()) {
				throw new InputMismatchException("Event Location is empty");
			} else {
				event = txtEvLoc.getText();
			}

			date = txtDate.getText();
			// If the date was empty it will inform the user
			if (date.isEmpty())
				throw new InputMismatchException("Date is empty");

			// Checks if the date was in the correct format
			else if (!CorrectDate(date)) {
				throw new MyInvalidInputException(lblAction.getText());
			}
			char gender = '?';

			if (rbMale.isSelected())
				gender = 'M';
			else if (rbFemale.isSelected())
				gender = 'F';
			else if (rbUnknown.isSelected())
				gender = 'N';

			// If all fields were entered correctly it will add the new martyr
			if (!name.isEmpty() && (age >= 0 || age == -1) && !event.isEmpty() && !date.isEmpty()) {
				// If no gender was selected it will throw an exception
				if (gender != 'M' && gender != 'F' && gender != 'N')
					throw new InputMismatchException("Choose a gender!");

				insert(name, age, event, date, gender);

			}
			// If any field was empty it will throw exceptions
			else {
				if (name.isEmpty())
					throw new InputMismatchException("Name is empty");
				if (event.isEmpty())
					throw new InputMismatchException("Event Location is empty");
				if (date.isEmpty())
					throw new InputMismatchException("Date is empty");
				if (gender == '?')
					throw new InputMismatchException("Gender is not chosen");
			}
		}
		// Catch blocks to handle exceptions
		catch (InputMismatchException e1) {
			lblAction.setText(e1.getMessage());
		} catch (NumberFormatException e1) {
			lblAction.setText("Please an integer for the age not a string!");
		} catch (MyInvalidInputException e1) {
			lblAction.setText(e1.getMessage());
		} catch (Exception e1) {
			lblAction.setText(e1.getMessage());
		}
	}

	// Method to delete a martyr from the list
	public boolean delete(String name) {
		// If the martyr exists
		if (Check(name) == 1) {
			// j represents the index of the martyr wanted to be deleted.
			int j = -1;
			for (int i = 0; i < counter; i++) {
				// If the martyr was null it will skip and go to the next element in the list
				if (martyrs[i] == null)
					continue;
				if (martyrs[i].getName().trim().equalsIgnoreCase(name.trim())) {
					// If the martyr was found by its name index will be stored in j
					j = i;
					break;
				}
			}

			// If new index was stored this means that the martyr was found
			if (j != -1) {
				// Shift all elements after the j to the left
				for (int i = j; i < counter - 1; i++) {
					martyrs[i] = martyrs[i + 1];
				}

				// Set the last element to null
				martyrs[counter - 1] = null;
				counter--;
				lblActionDelete.setText(name + " has been deleted!");
				return true;
			}
			// If -1 was stored this means that the martyr wasn't found
			else {
				lblActionDelete.setText("Error! Can't delete this martyr: " + name + " because s/he doesn't exist.");
				return false;
			}
		}
		// If the martyr doesn't exist
		else if (Check(name) == 0) {
			lblActionDelete.setText("Error! Can't delete this martyr: " + name + " because s/he doesn't exist.");
			return false;
		}
		return false;

	}

	// Method to delete a martyr by its name and date of death and event location
	public boolean delete2(String name) {
		// j represents the index of the martyr wanted to be deleted.
		int j = -1;

		for (int i = 0; i < counter; i++) {
			// If the martyr was null it will skip and go to the next element in the list
			if (martyrs[i] == null)
				continue;
			// If the martyr was found by its name and date of death and event location its
			// index will be stored in j
			if (martyrs[i].getName().trim().equalsIgnoreCase(name.trim())
					&& martyrs[i].getDateOfDeath().equals(txtDeleteDate.getText())
					&& martyrs[i].getEventLocation().trim().equals(txtDeleteEv.getText().trim())) {
				j = i;
				break;
			}
		}

		// If new index was stored this means that the martyr was found
		if (j != -1) {
			// Shift all elements after the j to the left
			for (int i = j; i < counter - 1; i++) {
				martyrs[i] = martyrs[i + 1];
			}

			// Set the last element to null
			martyrs[counter - 1] = null;
			counter--;
			lblActionDelete.setText(name + " has been deleted!");
			return true;

		}
		// If -1 was stored this means that the martyr wasn't found
		else {
			lblActionDelete.setText("Error! Couldn't delete this martyr: " + name + " because s/he doesn't exist.");
			return false;
		}
	}

	// Method that displays the number of martyrs with the same age
	public int displayNumberAge(int age) {
		int countMart = 0;

		for (int i = 0; i < counter; i++) {
			// If the martyr was null it will skip and go to the next element in the list
			if (martyrs[i] == null)
				continue;
			if (martyrs[i].getAge() == age) {
				countMart++;
			}
		}
		return countMart;
	}

	// Method that displays the number of martyrs with the same gender
	public int displayNumberGender(char gender) {
		int countMart = 0;

		for (int i = 0; i < counter; i++) {
			// If the martyr was null it will skip and go to the next element in the list
			if (martyrs[i] == null)
				continue;
			if (martyrs[i].getGender() == gender) {
				countMart++;
			}
		}
		return countMart;
	}

	// Method that displays the number of martyrs with the same date of death
	public int displayNumberDate(String date) {
		int countMart = 0;

		// This loop iterates on the list to find martyrs with the same date
		for (int i = 0; i < counter; i++) {
			// If the martyr was null it will skip and go to the next element in the list
			if (martyrs[i] == null)
				continue;
			if (martyrs[i].getDateOfDeath().equals(date)) {
				countMart++;
			}
		}
		return countMart;
	}

	// Method that checks if the date is in the correct format and valid
	private boolean CorrectDate(String date) {
		// If the date contains "-" and doesn't contain "/" it will return false
		if (date.contains("-") || !date.contains("/")) {
			lblAction.setText("Check the date format it should be written (MM/DD/YYYY)");
			lblActionDisplay.setText("Check the date format it should be written (MM/DD/YYYY)");
			return false;
		}

		// If the date didn't contain 3 parts it will return false
		String[] d = date.split("/");
		if (d.length != 3) {
			lblAction.setText("Check the date format it should be written (MM/DD/YYYY)");
			lblActionDisplay.setText("Check the date format it should be written (MM/DD/YYYY)");
			return false;
		}

		int year, month, day;
		try {
			day = Integer.parseInt(d[1]);
			month = Integer.parseInt(d[0]);
			year = Integer.parseInt(d[2]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			return false;
		} catch (Exception e) {
			return false;
		}

		// If the moth was anything except the numbers form 1-12 it will return false
		if (month < 1 || month > 12) {
			lblAction.setText("The date is invalid");
			lblActionDisplay.setText("Check the date format it should be written (MM/DD/YYYY)");
			return false;
		}

		int daysInMonth;
		// February
		if (month == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				// Leap year
				daysInMonth = 29;
			} else {
				// Non-leap year
				daysInMonth = 28;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			// April, June, September, November
			daysInMonth = 30;
		} else {
			// All other months
			daysInMonth = 31;
		}

		// If the day was between 1 and 31 it will return true
		if (day >= 1 && day <= daysInMonth) {
			return true;
		} else {
			lblAction.setText("The date is invalid");
			lblActionDisplay.setText("Check the date format it should be written (MM/DD/YYYY)");
			return false;
		}

	}

	// Method that checks if the string was a digit or not
	private boolean isNumeric(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}
		return true;
	}

	// Method that clears all the textFields and organize the nodes
	private void clear() {
		txtName.clear();
		txtAge.clear();
		txtEvLoc.clear();
		txtDate.clear();
		rbMale.setSelected(false);
		rbFemale.setSelected(false);
		rbUnknown.setSelected(false);
		lblAction.setText("");
		lblAction.setVisible(false);
	}

	// Method that switch the case to the main window
	private void returnMain(Stage stage) {
		bdPane.setStyle("-fx-background-color: whitesmoke");
		stage.setScene(sceneMain);
		stage.setTitle("Main Window");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}
}*/