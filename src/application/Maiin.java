package application;

import java.io.File;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Maiin extends Application {
	private Label lblName, lblGender, lblDate, lblEventLocation, lblAge;
	private TextField tfName, tfAge, tfEvent, tfDate;
	private Text txtWelcome;
	private TextArea ta;
	private RadioButton rbFemale, rbMale, rbAge, rbGender, rbDate, rbOption1, rbOption2;
	private HBox hbName, hbAge, hbEvent, hbDate, hbGender, hbOptions;
	private Button bt;
	private VBox vb, vbG;
	private int limit = 16, counter = 0;
	private Martyr[] records = new Martyr[limit];
	private BorderPane bp;
	private StackPane sp;
	private Menu menu1, menu2;
	private MenuItem item1, item2, item3, item4, item5;

	@Override
	public void start(Stage primaryStage) throws Exception {
		bp = new BorderPane();
		sp = new StackPane();
		MenuBar menuBar = new MenuBar();
		menu1 = new Menu("Operation");
		menu2 = new Menu("File");
		item1 = new MenuItem("Insert a new Martyr record into the list");
		item2 = new MenuItem("Delete a martyr using his 'Name'");
		item3 = new MenuItem("Search for a martyr using his 'Name'");
		item4 = new MenuItem("Display the number of martyrs by age/gender/date");
		item5 = new MenuItem("Load a File");

		menu1.getItems().addAll(item1, item2, item3, item4);
		menu2.getItems().add(item5);
		menuBar.getMenus().addAll(menu2, menu1);

		txtWelcome = new Text(
				"Welcome to Martyr system\n" + "By: Firas Remawi #1221868\n" + "Instructor: Dr.Abdallah karakra");
		txtWelcome.setFont(Font.font("Times New Roman", 28));
		txtWelcome.setStyle("-fx-font-weight: bold");

		lblName = new Label("Name");
		tfName = new TextField();
		tfName.setPromptText("e.g. Firas");

		lblAge = new Label("Age");
		tfAge = new TextField();
		tfAge.setPromptText(" '+' numbers");

		lblEventLocation = new Label("Event Location");
		tfEvent = new TextField();

		lblDate = new Label("Date of Death");
		tfDate = new TextField();
		tfDate.setPromptText("mm/dd/yyyy");

		lblGender = new Label("Gender");
		rbMale = new RadioButton("Male");
		rbFemale = new RadioButton("Female");

		ToggleGroup rbGroup = new ToggleGroup();
		rbMale.setToggleGroup(rbGroup);
		rbFemale.setToggleGroup(rbGroup);

		ToggleGroup rbGroup1 = new ToggleGroup();
		rbAge = new RadioButton("Age");
		rbAge.setToggleGroup(rbGroup1);
		rbDate = new RadioButton("Date");
		rbDate.setToggleGroup(rbGroup1);
		rbGender = new RadioButton(" Gender");
		rbGender.setToggleGroup(rbGroup1);

		rbOption1 = new RadioButton("Option 1");
		rbOption2 = new RadioButton("Option 2");

		ToggleGroup rbGroup2 = new ToggleGroup();
		rbOption1.setToggleGroup(rbGroup2);
		rbOption2.setToggleGroup(rbGroup2);
		hbOptions = new HBox(10);
		hbOptions.getChildren().addAll(rbOption1, rbOption2);

		vbG = new VBox(10, hbOptions, rbAge, rbDate, rbGender);
		ta = new TextArea();
		ta.setFont(Font.font("Times New Roman", 30));
		ta.setStyle("-fx-font-weight: bold");
		ta.setEditable(false);
		hbName = new HBox(10, lblName, tfName);
		hbAge = new HBox(10, lblAge, tfAge);
		hbEvent = new HBox(10, lblEventLocation, tfEvent);
		hbDate = new HBox(10, lblDate, tfDate);
		hbGender = new HBox(10, lblGender, rbMale, rbFemale);

		bt = new Button("Confirm");
		vb = new VBox(10, hbName, hbAge, hbEvent, hbDate, hbGender, bt);
		vb.setPadding(new Insets(10));
		sp.getChildren().addAll(vb, txtWelcome);
		mainMenu();
		item1.setOnAction(e -> {
			primaryStage.setTitle("Insert a marytr");
			iitem1();
			bt.setOnAction(e1 -> {
				insertMartyr();
			});
		});

		item2.setOnAction(e -> {
			primaryStage.setTitle("Delete a martyr");
			iitem2();
			bt.setOnAction(e1 -> {
				deleteMartyr();
			});

		});

		item3.setOnAction(e -> {
			primaryStage.setTitle("search for a martyr");
			iitem3();
			ta.setVisible(true);
			bt.setOnAction(e1 -> {
				SearchMartyr();
			});

		});

		item4.setOnAction(e -> {
			primaryStage.setTitle("Statistics");
			iitem4();
			rbOption1.setOnAction(e12 -> {
				iitem4();
				ta.clear();
				rbAge.setOnAction(e1 -> {
					numberByAge();
				});
				rbDate.setOnAction(e1 -> {
					numberByDate();
				});
				rbGender.setOnAction(e1 -> {
					numberByGender();
				});
			});
			rbOption2.setOnAction(e12 -> {

				ta.clear();
				rbAge.setOnAction(e1 -> {
					iitem4();
					hbAge.setVisible(true);
					bt.setVisible(true);
					bt.setOnAction(e122 -> {
						searchForAge();
					});
				});
				rbDate.setOnAction(e1 -> {
					iitem4();
					hbDate.setVisible(true);
					bt.setVisible(true);
					bt.setOnAction(e122 -> {
						searchForDate();
					});
				});
				rbGender.setOnAction(e1 -> {
					iitem4();
					hbGender.setVisible(true);
					bt.setVisible(true);
					bt.setOnAction(e122 -> {
						rbMale.setOnAction(e23 -> {
							searchForMale();
						});
						rbFemale.setOnAction(e23 -> {
							searchForFemale();
						});

					});
				});
			});

		});

		item5.setOnAction(e -> {
			try {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setInitialDirectory(new File("C:\\Users\\ASUS\\eclipse-workspace"));
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					readData(file);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println("Error occurred while opening file chooser: " + ex.getMessage());
			}
		});
		hbName.setAlignment(Pos.CENTER);
		hbGender.setAlignment(Pos.CENTER);
		hbAge.setAlignment(Pos.CENTER);
		hbDate.setAlignment(Pos.CENTER);
		hbGender.setAlignment(Pos.CENTER);
		hbEvent.setAlignment(Pos.CENTER);
		vb.setAlignment(Pos.CENTER);
		bp.setCenter(sp);
		bp.setTop(menuBar);
		Scene scene = new Scene(bp, 800, 500);
		primaryStage.setTitle("Martyr-records system-Main menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void numberByGender() {
		int male = 0, female = 0, notKnown = 0;

		for (Martyr record : records) {
			if (record != null) {
				if (record.getGender().equalsIgnoreCase("M"))
					male++;
				else if (record.getGender().equalsIgnoreCase("F"))
					female++;
				else
					notKnown++;
			}
		}
		ta.clear();
		ta.setText("God have mercy on Them 🙏 \n");
		ta.appendText("Number of male martyrs -->" + male + "\n");
		ta.appendText("Number of female martyrs -->" + female + "\n");
		ta.appendText("Number of not mentioned martyrs -->" + notKnown + "\n");

	}

	private void searchForFemale() {
		int female = 0;

		for (Martyr record : records) {
			if (record != null) {
				if (record.getGender().equalsIgnoreCase("F"))
					female++;
			}
		}
		ta.clear();
		ta.setText("God have mercy on Them 🙏 \n");
		ta.appendText("Number of female martyrs -->" + female + "\n");
	}

	private void searchForMale() {
		int male = 0;

		for (Martyr record : records) {
			if (record != null) {
				if (record.getGender().equalsIgnoreCase("M"))
					male++;
			}
		}
		ta.clear();
		ta.setText("God have mercy on Them 🙏 \n");
		ta.appendText("Number of male martyrs -->" + male + "\n");
	}

	private void searchForAge() {

		ta.clear();

		int counterAge = 0;
		String StringAge = tfAge.getText().trim();
		if (StringAge.isEmpty()) {
			ta.setText("Please enter age");
		}
		if (isNumeric(StringAge)) {
			int targetAge = Integer.parseInt(StringAge);
			for (Martyr Martyr : records) {
				if (Martyr != null) {
					if (targetAge == Martyr.getAge())
						counterAge++;
				}
			}
			if (counterAge > 0) {
				ta.setText("God have mercy on Them 🙏 \n");
				ta.appendText("Number of martyrs with this age ( " + targetAge + " ) are --> " + counterAge);
			}

			else
				ta.setText("No martyrs with this age");
		} else
			ta.setText("Please enter a number, " + tfAge.getText() + " isn't a number");

	}

	private void searchForDate() {

		ta.clear();
		String StringDate = tfDate.getText();
		int counterDate = 0;
		if (correctDate(StringDate)) {
			for (Martyr Martyr : records) {
				if (Martyr != null) {
					if (Martyr.getDateOfDeath().equalsIgnoreCase(StringDate))
						counterDate++;
				}
			}
		} else
			ta.setText("Please make sure that it's : mm/dd/yyyy");

		if (counterDate > 0) {
			ta.setText("God have mercy on Them 🙏 \n");
			ta.appendText("Number of martyrs in this date ( " + StringDate + " ) are --> " + counterDate);
		} else
			ta.setText("No martyrs in this date");
	}

	private void numberByDate() {
		int before2010 = 0, before2020 = 0, before2030 = 0;

		for (Martyr record : records) {

			if (record != null) {
				String line = record.getDateOfDeath();
				String[] parts = line.split("/");
				int date;
				if (isNumeric(parts[2]))
					date = Integer.parseInt(parts[2]);
				else
					date = 0;

				if (date >= 2000 && date < 2010)
					before2010++;
				else if (date >= 2010 && date < 2020)
					before2020++;
				else if (date >= 2020 && date < 2030)
					before2030++;

			}
		}
		ta.clear();
		ta.setText("God have mercy on Them 🙏 \n");
		ta.appendText("Number of martyrs between 2000-2009 -->" + before2010 + "\n");
		ta.appendText("Number of martyrs between 2010-2019 -->" + before2020 + "\n");
		ta.appendText("Number of martyrs between 2020-2029 -->" + before2030 + "\n");

	}

	private void numberByAge() {
		int child = 0, adult = 0, old = 0, notKnown = 0;

		for (Martyr record : records) {
			if (record != null) {
				if (record.getAge() < 16)
					child++;
				else if (record.getAge() >= 16 && record.getAge() < 30)
					adult++;
				else if (record.getAge() > 31)
					old++;
				else
					notKnown++;
			}
		}
		ta.clear();
		ta.setText("God have mercy on Them 🙏 \n");
		ta.appendText("Number of children between 0-16 -->" + child + "\n");
		ta.appendText("Number of adults between 17-30 -->" + adult + "\n");
		ta.appendText("Number of old adults above 30 -->" + old + "\n");
		ta.appendText("Number of not mentioned martyrs -->" + notKnown + "\n");
	}

	private void SearchMartyr() {

		String targetName = tfName.getText();
		ta.clear();
		ta.setVisible(true);
		for (int i = 0; i < records.length; i++) {
			if (records[i] != null) {
				if (targetName.equalsIgnoreCase(records[i].getName())) {
					bt.setText(":)");
					tfName.setText(records[i].getName());
					ta.appendText("Name: " + records[i].getName() + "\n");
					tfAge.setText(records[i].getAge() + "");
					ta.appendText("Age: " + records[i].getAge() + "\n");
					tfEvent.setText(records[i].getEventLocation());
					ta.appendText("Event Location: " + records[i].getEventLocation() + "\n");
					tfDate.setText(records[i].getDateOfDeath());
					ta.appendText("Date of Death: " + records[i].getDateOfDeath() + "\n");
					ta.appendText("Gender: " + records[i].getGender());
					break;

				} else {
					tfName.clear();
					tfAge.clear();
					tfEvent.clear();
					tfDate.clear();
					// ta.setText("No martyr found with this name: " + tfName.getText());

				}
			}
		}
	}

	private void deleteMartyr() {
		String targetName = tfName.getText();
		Martyr[] newRecords = new Martyr[limit];
		for (int i = 0; i < records.length; i++) {
			if (records[i] != null) {
				if (!targetName.equalsIgnoreCase(records[i].getName()))
					newRecords[i] = records[i];
			}
		}
		records = newRecords;

	}

	private void insertMartyr() {
		ta.clear();
		if (counter == limit) {
			doubleArraySize();
		}
		if (!(tfName.getText().isEmpty() || tfAge.getText().isEmpty() || tfDate.getText().isEmpty()
				|| tfEvent.getText().isEmpty())) {
			if (correctDate(tfDate.getText())) {
				String g;
				if (rbMale.isSelected()) {
					ta.clear();
					g = "M";
				} else {
					ta.clear();
					g = "F";
				}
				int age = 0;
				if (isNumeric(tfAge.getText())) {
					age = Integer.parseInt(tfAge.getText());
				}

				records[counter++] = new Martyr(tfName.getText(), age, tfEvent.getText(), tfDate.getText(), 'g');
				ta.setText("Inserted successfuly ✔️\n");
				ta.appendText("Have a nice day ! ^_~");
			}
		} else {
			ta.setText("Hey ! please make sure all martyr's details are mentiond\n");
			ta.appendText("Have a nice day ! ^_~");
		}

	}

	private void readData(File file) {
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				if (parts.length == 5) {
					String name = parts[0];
					String stringAge = parts[1].trim();
					int age = 0;
					if (isNumeric(stringAge)) {
						age = Integer.parseInt(stringAge);
					}
					String eventLocation = parts[2];
					String dateOfDeath = parts[3];
					String gender = parts[4];
					if (counter == limit) {
						doubleArraySize();
					}
					records[counter++] = new Martyr(name, age, eventLocation, dateOfDeath, 'c');
				}
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean isNumeric(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean correctDate(String s) {
		String StringDate = s.trim();
		String[] parts = StringDate.split("/");
		if (parts.length == 3) {
			if (isNumeric(parts[0]) && isNumeric(parts[1]) && isNumeric(parts[2])) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	private void doubleArraySize() {
		limit = limit * 2;
		Martyr[] newRecords = new Martyr[limit];
		for (int i = 0; i < counter; i++)
			newRecords[i] = records[i];

		records = newRecords;
	}

	private void mainMenu() {
		hbAge.setVisible(false);
		hbGender.setVisible(false);
		hbEvent.setVisible(false);
		hbDate.setVisible(false);
		hbName.setVisible(false);
		bt.setVisible(false);
		ta.setVisible(true);
		vbG.setVisible(false);
		bp.setBottom(ta);
		bp.setRight(vbG);
	}

	private void iitem4() {
		hbAge.setVisible(false);
		hbGender.setVisible(false);
		hbEvent.setVisible(false);
		hbDate.setVisible(false);
		hbName.setVisible(false);
		bt.setVisible(false);
		ta.setVisible(true);
		vbG.setVisible(true);
		bp.setBottom(ta);
		ta.clear();
		bp.setRight(vbG);
		txtWelcome.setVisible(false);
	}

	private void iitem3() {
		hbName.setVisible(true);
		hbAge.setVisible(true);
		hbGender.setVisible(true);
		hbEvent.setVisible(true);
		hbDate.setVisible(true);
		bt.setVisible(true);
		ta.setVisible(true);
		vbG.setVisible(false);
		ta.clear();
		txtWelcome.setVisible(false);
		bt.setText("Search for a Martyr");
	}

	private void iitem2() {
		bt.setVisible(true);
		bt.setText("Delete Martyr");
		hbName.setVisible(true);
		hbAge.setVisible(false);
		hbGender.setVisible(false);
		hbEvent.setVisible(false);
		hbDate.setVisible(false);
		ta.setVisible(false);
		vbG.setVisible(false);
		ta.clear();
		txtWelcome.setVisible(false);
	}

	private void iitem1() {
		bp.setBottom(ta);
		bt.setVisible(true);
		bt.setText("Insert");
		hbAge.setVisible(true);
		hbGender.setVisible(true);
		rbMale.setSelected(false);
		rbFemale.setSelected(false);
		hbEvent.setVisible(true);
		hbDate.setVisible(true);
		hbName.setVisible(true);
		ta.setVisible(true);
		vbG.setVisible(false);
		ta.clear();
		txtWelcome.setVisible(false);
	}

	public static void main(String[] args) {
		try {
			launch(args);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.println("Null pointer exception :(");
		} catch (NumberFormatException e) {
			System.out.println("Number Format exception :(");
		} catch (Exception e) {
			System.out.println("Exception :(");
		}
	}
}
