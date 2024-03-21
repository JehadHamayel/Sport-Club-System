//Jehad Hamayel 1200348	
//Lama Naser 1200190
//Arwa Doha 1190324
package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	private static String dbUsername = "root";
	private static String dbPasswoerd = "jojo00";
	private static String URL = "localhost";
	private static String port = "3306";
	private static String dbName = "sport_club_project";

	private static Connection con;
	static boolean wrongDate = false;
	static int ID = 0;// old value
	static boolean error = false;

	@Override
	public void start(Stage primaryStage) {
		// Start the program and print a welcome message and note
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("welcome message");
		alert.setHeaderText("Hello to Our Project\nJehad Hamayel-1200348\nLama Naser-1200190\nArwa Doha-1190324\n");
		alert.setContentText("Project Name: Sport club ");
		Scene mainScene = new Scene(new BorderPane());
		primaryStage.setScene(mainScene);
		primaryStage.setMaximized(true);
		try {
			mainPage(primaryStage, mainScene);
			alert.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void mainPage(Stage primaryStage, Scene maineSene) throws IOException {
		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 18);
		// Main Pane
		HBox root = new HBox(200);
		primaryStage.setTitle("Main Page");
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		// Buttons's Pane
		VBox buttons1 = new VBox(50);
		buttons1.setMinSize(250, 50);
		buttons1.setAlignment(Pos.CENTER);
		VBox buttons2 = new VBox(50);
		buttons2.setMinSize(250, 50);
		buttons2.setAlignment(Pos.CENTER);
		HBox buttons = new HBox(10);
		buttons.setPadding(new Insets(10));
		buttons.setAlignment(Pos.CENTER);
		// image Pane
		VBox ima = new VBox(20);
		ima.setAlignment(Pos.CENTER);
		// Setting the image for the home page
		Image image = new Image("MainIm.jpg");
		ImageView imageView = new ImageView(image);
		Group imag = new Group(imageView);
		Label sportClub = new Label("Sport Club System");
		Font myfont = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 30);
		sportClub.setFont(myfont);
//********************************************************************************************
		Image image2 = new Image("Members.png");
		ImageView memim = new ImageView(image2);
		memim.setFitHeight(130);
		memim.setFitWidth(130);
		Button membersBut = new Button("Members", memim);
		membersBut.setContentDisplay(ContentDisplay.TOP);
		membersBut.setOnAction(e -> {
			try {
				members(primaryStage, maineSene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		membersBut.setMinSize(200, 100);
		membersBut.setStyle("-fx-background-color:lightblue");
		membersBut.setFont(myfont2);
		DropShadow shadow = new DropShadow();
		membersBut.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			membersBut.setEffect(shadow);
		});
		membersBut.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			membersBut.setEffect(null);
		});
		// ********************************************************************************************

		Image image3 = new Image("program.png");
		ImageView program = new ImageView(image3);
		program.setFitHeight(130);
		program.setFitWidth(130);
		Button sportProgBut = new Button("Sport Programs", program);
		// Action for Media Button
		sportProgBut.setOnAction(e -> {
			try {
				programSport(primaryStage, maineSene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		sportProgBut.setMinSize(200, 100);
		sportProgBut.setStyle("-fx-background-color:lightblue");
		sportProgBut.setFont(myfont2);
		sportProgBut.setContentDisplay(ContentDisplay.TOP);
		// Effect for Button
		sportProgBut.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			sportProgBut.setEffect(shadow);
		});
		sportProgBut.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			sportProgBut.setEffect(null);
		});
//********************************************************************************************
		Image image4 = new Image("place.png");
		ImageView place = new ImageView(image4);
		place.setFitHeight(130);
		place.setFitWidth(130);
		Button placeBut = new Button("Place", place);

		placeBut.setOnAction(e -> {
			place(primaryStage, maineSene,root);
		});
		placeBut.setMinSize(200, 100);
		placeBut.setStyle("-fx-background-color:lightblue");
		placeBut.setFont(myfont2);
		placeBut.setContentDisplay(ContentDisplay.TOP);
		// Effect for Button
		placeBut.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			placeBut.setEffect(shadow);
		});
		placeBut.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			placeBut.setEffect(null);
		});
		// ********************************************************************************************
		Image image5 = new Image("coach.png");
		ImageView coach = new ImageView(image5);
		coach.setFitHeight(130);
		coach.setFitWidth(130);
		Button coachesBut = new Button("Coaches", coach);
		coachesBut.setContentDisplay(ContentDisplay.TOP);
		coachesBut.setOnAction(e -> {
			 coaches(primaryStage, maineSene,root);
		});
		coachesBut.setMinSize(200, 100);
		coachesBut.setStyle("-fx-background-color:lightblue");
		coachesBut.setFont(myfont2);
		coachesBut.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			coachesBut.setEffect(shadow);
		});
		coachesBut.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			coachesBut.setEffect(null);
		});

		Image image6 = new Image("eq.png");
		ImageView eq = new ImageView(image6);
		eq.setFitHeight(130);
		eq.setFitWidth(130);
		// Rent Button
		Button equipmentBut = new Button("Equipments", eq);
		// Action for Rent Button
		equipmentBut.setOnAction(e -> {
			 equipment(primaryStage, maineSene,root);
		});
		equipmentBut.setMinSize(200, 100);
		equipmentBut.setStyle("-fx-background-color:lightblue");
		equipmentBut.setFont(myfont2);
		equipmentBut.setContentDisplay(ContentDisplay.TOP);
		// Effect for Button
		equipmentBut.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			equipmentBut.setEffect(shadow);
		});
		equipmentBut.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			equipmentBut.setEffect(null);
		});

		Image image7 = new Image("quere.png");
		ImageView quere = new ImageView(image7);
		quere.setFitHeight(130);
		quere.setFitWidth(130);
		// Rent Button
		Button queriesBut = new Button("Queries", quere);
		// Action for Rent Button
		queriesBut.setOnAction(e -> {
			 queries(primaryStage, maineSene,root);
		});
		queriesBut.setMinSize(200, 100);
		queriesBut.setStyle("-fx-background-color:lightblue");
		queriesBut.setFont(myfont2);
		queriesBut.setContentDisplay(ContentDisplay.TOP);
		// Effect for Button
		queriesBut.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			queriesBut.setEffect(shadow);
		});
		queriesBut.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			queriesBut.setEffect(null);
		});
		// Screen Format
		buttons1.getChildren().addAll(membersBut, sportProgBut, placeBut);
		buttons2.getChildren().addAll(coachesBut, equipmentBut, queriesBut);
		buttons.getChildren().addAll(buttons1, buttons2);
		ima.getChildren().addAll(imag, sportClub);
		root.getChildren().addAll(buttons, ima);
		// View content
		maineSene.setRoot(root);
		primaryStage.show();
	}
    // in this project we have 6 queries that includes a variety of topics
	public static void queries(Stage stage, Scene scene, HBox root) {
		Label header = new Label("Welcome To Our Queries information");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		VBox pane = new VBox();
		Button b1 = new Button("The name of members who registered\nin a specific place with a specific choach");
		b1.setFont(Font.font(b1.getText(), FontWeight.BOLD, 20));
		Button b2 = new Button("the name of sport programs which are\ntrained by a specific choach");
		b2.setFont(Font.font(b2.getText(), FontWeight.BOLD, 20));
		Button b3 = new Button("The name of sport programs which they\nuse an equipment with specific type and brand");
		b3.setFont(Font.font(b3.getText(), FontWeight.BOLD, 20));
		Button b4 = new Button("the name and ID of equipments which are\nussed in a specific place");
		b4.setFont(Font.font(b4.getText(), FontWeight.BOLD, 20));
		Button b5 = new Button("the ID of places which have members with\nspecific ages");
		b5.setFont(Font.font(b5.getText(), FontWeight.BOLD, 20));
		Button b6 = new Button("the name and ID of choaches who train on\nspecific days and times");
		b6.setFont(Font.font(b6.getText(), FontWeight.BOLD, 20));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		GridPane buttons1 = new GridPane();
		buttons1.setAlignment(Pos.CENTER);
		buttons1.add(b1, 0, 0);
		buttons1.add(b2, 0, 1);
		buttons1.add(b3, 0, 2);
		buttons1.setVgap(30);
		GridPane buttons2 = new GridPane();
		buttons2.setAlignment(Pos.CENTER);
		buttons2.add(b4, 0, 0);
		buttons2.add(b5, 0, 1);
		buttons2.add(b6, 0, 2);
		buttons2.setVgap(30);
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(80);
		buttons.getChildren().addAll(buttons1, buttons2);
		pane.getChildren().addAll(header, buttons, back);
		pane.setSpacing(80);
		pane.setAlignment(Pos.CENTER);
		stage.setMaximized(true);

		scene.setRoot(pane);
		b1.setOnAction(e -> {
			try {
				querie1(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		b2.setOnAction(e -> {
			try {
				querie2(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		b3.setOnAction(e -> {
			try {
				querie3(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		b4.setOnAction(e -> {
			try {
				querie4(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		b5.setOnAction(e -> {
			try {
				querie5(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		b6.setOnAction(e -> {
			try {
				querie6(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		back.setOnAction(e -> {
			scene.setRoot(root);
		});

		stage.setScene(scene);
		stage.show();
	}
	//This querie return The name of members who registered in a specific place with a specific choach
	public static void querie1(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our Querie #1");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("find", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label cL = new Label("The name of the choach ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		Label pL = new Label("The ID of the place");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of members");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		nT.setVisible(false);
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.add(cL, 0, 1);
		root.add(cT, 1, 1);
		root.add(nL, 0, 2);
		root.add(nT, 1, 2);

		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(120);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (pT.getText().isEmpty() || cT.getText().isEmpty())
					error("Please fill all the texts");
				else {
					String SQL = "select m.Name "
							+ "from members m, sport_programs_runningin s, registeration r,coaches c,train t"
							+ " where r.SSN_me = m.SSN and" + "	r.SPID_SP = s.SPID and" + "	t.SPID_t = s.SPID and"
							+ "	t.SSN_c = c.SSN and" + "	s.PlaceID_runningin = " + pT.getText() + " and"
							+ "	c.Name = '" + cT.getText() + "';";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					String ns = "";
					if (rs.next()) {
						ns += rs.getString(1) + ",";
						while (rs.next()) {
							ns += rs.getString(1) + ",";
						}
						nT.setVisible(true);
						nT.setText(ns);
					} else
						error("No members are trained in this place with this coach");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This querie return the name of sport programs which are trained by a specific choach
	public static void querie2(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {
		Label header = new Label("Welcome To Our Querie #2");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("find", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label cL = new Label("The name of the choach ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of sport programs ");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		nT.setVisible(false);
		root.add(cL, 0, 0);
		root.add(cT, 1, 0);
		root.add(nL, 0, 1);
		root.add(nT, 1, 1);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(150);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (cT.getText().isEmpty())
					error("Please fill all the text with coach name");
				else {
					String SQL = "select s.Name" + " from sport_programs_runningin s,coaches c,train t"
							+ " where t.SPID_t = s.SPID and" + "	t.SSN_c = c.SSN and" + "	c.Name = '"
							+ cT.getText() + "';";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					String ns = "";
					if (rs.next()) {
						ns += rs.getString(1);
						while (rs.next()) {
							ns += rs.getString(1);
						}
						nT.setVisible(true);
						nT.setText(ns);
					} else
						error("No coaches with this name");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This querie return the name of sport programs which they use an equipment with specific type and brand
	public static void querie3(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our Querie #3");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("find", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label cL = new Label("The equipment's sport type ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		Label pL = new Label("The equipment's brand");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of sport programs");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		nT.setVisible(false);
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.add(cL, 0, 1);
		root.add(cT, 1, 1);
		root.add(nL, 0, 2);
		root.add(nT, 1, 2);

		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(140);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (pT.getText().isEmpty() || cT.getText().isEmpty())
					error("Please fill all the texts");
				else {
					String SQL = "select distinct s.* " + " from sport_programs_runningin s,place p, equipment_has e"
							+ " where p.PlaceID = s.PlaceID_runningin and" + "	e.PlaceID = p.PlaceID and"
							+ "	e.SportType = '" + cT.getText() + "' and" + "	e.brand	    = '" + pT.getText() + "';";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					String ns = "";
					if (rs.next()) {
						ns += rs.getString(3) + ",";
						while (rs.next()) {
							ns += rs.getString(3) + ",";
						}
						nT.setVisible(true);
						nT.setText(ns);
					} else
						error("No equipment with this sport type and this brand");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This querie return the name and ID of equipments which are used in a specific place
	public static void querie4(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {
		Label header = new Label("Welcome To Our Querie #4");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("find", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label pL = new Label("The ID of the place");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		Label eL = new Label("The ID of the equipment");
		eL.setFont(Font.font(eL.getText(), FontWeight.BOLD, 22));
		TextField eT = new TextField();
		eT.setFont(Font.font(eT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of the equipment");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		eT.setVisible(false);
		nT.setVisible(false);
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.add(eL, 0, 1);
		root.add(eT, 1, 1);
		root.add(nL, 0, 2);
		root.add(nT, 1, 2);

		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(120);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (pT.getText().isEmpty())
					error("Please fill all the text with place ID");
				else {
					String SQL = "select e.EID , e.EName" + " from equipment_has e" + " where e.PlaceID = "
							+ Integer.parseInt(pT.getText()) + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					String ns = "";
					String es = "";
					if (rs.next()) {
						es += rs.getString(1) + ",";
						ns += rs.getString(2) + ",";
						while (rs.next()) {
							es += rs.getString(1) + ",";
							ns += rs.getString(2) + ",";
						}
						nT.setVisible(true);
						nT.setText(ns);
						eT.setVisible(true);
						eT.setText(es);
					} else
						error("No equipment this place, check ID of the place");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This querie return the ID of places which have members with specific ages
	public static void querie5(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {
		Label header = new Label("Welcome To Our Querie #5");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("find", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label mL = new Label("Minimom member's age ");
		mL.setFont(Font.font(mL.getText(), FontWeight.BOLD, 22));
		TextField mT = new TextField();
		mT.setFont(Font.font(mT.getText(), FontWeight.BOLD, 22));
		Label mxL = new Label("Maximom member's age");
		mxL.setFont(Font.font(mxL.getText(), FontWeight.BOLD, 22));
		TextField mxT = new TextField();
		mxT.setFont(Font.font(mxT.getText(), FontWeight.BOLD, 22));
		Label pL = new Label("The ID of places");
		pL.setFont(Font.font(mxL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		pT.setVisible(false);
		root.add(mL, 0, 0);
		root.add(mT, 1, 0);
		root.add(mxL, 0, 1);
		root.add(mxT, 1, 1);
		root.add(pL, 0, 2);
		root.add(pT, 1, 2);

		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(120);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (mT.getText().isEmpty() || mxT.getText().isEmpty())
					error("Please fill all the texts");
				else {
					String SQL = "select distinct s.PlaceID_runningin"
							+ " from sport_programs_runningin s,registeration r,members m"
							+ " where r.SSN_me  = m.SSN and" + " r.SPID_SP = s.SPID and" + " m.age >= "
							+ Integer.parseInt(mT.getText()) + " and" + " m.age <= " + Integer.parseInt(mxT.getText())
							+ ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					String ps = "";
					if (rs.next()) {
						ps += rs.getString(1) + ",";
						while (rs.next()) {
							ps += rs.getString(1) + ",";
						}
						pT.setVisible(true);
						pT.setText(ps);
					} else
						error("No place have members with this rang of age");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This querie return the name and ID of choaches who train on specific days and times
	public static void querie6(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {
		Label header = new Label("Welcome To Our Querie #6");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("find", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label sL = new Label("start date (eg: 2022-01-14) ");
		sL.setFont(Font.font(sL.getText(), FontWeight.BOLD, 22));
		TextField sT = new TextField();
		sT.setFont(Font.font(sT.getText(), FontWeight.BOLD, 22));
		Label eL = new Label("end date (eg: 2022-02-14) ");
		eL.setFont(Font.font(eL.getText(), FontWeight.BOLD, 22));
		TextField eT = new TextField();
		eT.setFont(Font.font(eT.getText(), FontWeight.BOLD, 22));
		Label dL = new Label("Training times and days (eg: T,R,12:30-14:50)");
		dL.setFont(Font.font(dL.getText(), FontWeight.BOLD, 22));
		TextField dT = new TextField();
		dT.setFont(Font.font(dT.getText(), FontWeight.BOLD, 22));
		Label ssnL = new Label("The SSN of the coach");
		ssnL.setFont(Font.font(ssnL.getText(), FontWeight.BOLD, 22));
		TextField ssnT = new TextField();
		ssnT.setFont(Font.font(ssnT.getText(), FontWeight.BOLD, 22));
		ssnT.setVisible(false);
		Label nL = new Label("The name of the coach");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		nT.setVisible(false);
		root.add(sL, 0, 0);
		root.add(sT, 1, 0);
		root.add(eL, 0, 1);
		root.add(eT, 1, 1);
		root.add(dL, 0, 2);
		root.add(dT, 1, 2);
		root.add(ssnL, 0, 3);
		root.add(ssnT, 1, 3);
		root.add(nL, 0, 4);
		root.add(nT, 1, 4);

		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(120);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (sT.getText().isEmpty() || eT.getText().isEmpty() || dT.getText().isEmpty())
					error("Please fill all the texts");
				else {
					String start[] = sT.getText().split("-");
					String end[] = eT.getText().split("-");
					if (Integer.parseInt(start[0]) < Integer.parseInt(end[0])
							|| (Integer.parseInt(start[0]) == Integer.parseInt(end[0])
									&& Integer.parseInt(start[1]) < Integer.parseInt(end[1]))
							|| (Integer.parseInt(start[0]) == Integer.parseInt(end[0])
									&& Integer.parseInt(start[1]) == Integer.parseInt(end[1])
									&& Integer.parseInt(start[2]) < Integer.parseInt(end[2]))) {
						String SQL = "select  c.SSN, c.Name" + " from coaches c, train t, sport_programs_runningin s"
								+ " where t.SSN_c = c.SSN and" + " t.SPID_t = s.SPID and" + " s.StartDate = '"
								+ sT.getText() + "' and" + " s.EndDate = '" + eT.getText() + "' and"
								+ " s.TimeAndDays = '" + dT.getText() + "';";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						String sns = "";
						String ns = "";
						if (rs.next()) {
							sns += rs.getString(1) + ",";
							ns += rs.getString(2) + ",";
							while (rs.next()) {
								sns += rs.getString(1) + ",";
								ns += rs.getString(2) + ",";
							}
							ssnT.setVisible(true);
							nT.setVisible(true);
							nT.setText(ns);
							ssnT.setText(sns);
						} else
							error("No coach train on this date or these days");
						rs.close();
						stmt.close();
						con.close();
					} else
						error("Start date must be before the end date");

				}

			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function have the main coaches interface, which call Add,Update,Delete and search for a Choach
	public static void coaches(Stage stage, Scene scene, HBox root) {
		Label header = new Label("Welcome To Our Choaches information");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		VBox pane = new VBox();
		Button reg = new Button("Add new Choach");
		reg.setFont(Font.font(reg.getText(), FontWeight.BOLD, 30));
		Button update = new Button("Update Choach information");
		update.setFont(Font.font(update.getText(), FontWeight.BOLD, 30));
		Button delete = new Button("Delete a Choach");
		delete.setFont(Font.font(delete.getText(), FontWeight.BOLD, 30));
		Button search = new Button("search for a Choach");
		search.setFont(Font.font(search.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		VBox buttons = new VBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(50);
		buttons.getChildren().addAll(reg, update, delete, search, back);
		pane.getChildren().addAll(header, buttons);
		pane.setSpacing(80);
		pane.setAlignment(Pos.CENTER);
		stage.setMaximized(true);

		scene.setRoot(pane);
		reg.setOnAction(e -> {
			try {
				addChoach(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		update.setOnAction(e -> {
			try {
				updateChoach(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		delete.setOnAction(e -> {
			try {
				deleteChoach(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		search.setOnAction(e -> {
			searchChoach(stage, scene, pane);
		});

		back.setOnAction(e -> {
			scene.setRoot(root);
		});

		stage.setScene(scene);
		stage.show();
	}
    // This function have the interface of add new Choach to the system, by add SSN,name,phone and email address 
	public static void addChoach(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our coaches Registeration");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://www6.0zz0.com/2022/01/18/18/500192871.png");
		imageSave.setFitWidth(40);
		Button save = new Button("Add", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label cL = new Label("The ssn of the coach ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of the coach ");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		Label pL = new Label("The phone numbers (eg:0595484,0295628)");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		Label eL = new Label("The email of the coach ");
		eL.setFont(Font.font(eL.getText(), FontWeight.BOLD, 22));
		TextField eT = new TextField();
		eT.setFont(Font.font(eT.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		root.add(cL, 0, 0);
		root.add(cT, 1, 0);
		root.add(nL, 0, 1);
		root.add(nT, 1, 1);
		root.add(eL, 0, 2);
		root.add(eT, 1, 2);
		root.add(pL, 0, 3);
		root.add(pT, 1, 3);

		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(70);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (eT.getText().isEmpty() || pT.getText().isEmpty() || nT.getText().isEmpty()
						|| cT.getText().isEmpty())
					error("Please fill all the texts");
				else {
					String SQL = "SELECT * FROM coaches WHERE SSN = " + Integer.parseInt(cT.getText()) + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					if (rs.next()) {
						error("This coach is already exist in the data base");
					} else {
						SQL = "insert into coaches (SSN,Name,email) values (" + Integer.parseInt(cT.getText()) + ",'"
								+ nT.getText() + "','" + eT.getText() + "');";
						stmt = con.prepareStatement(SQL);
						stmt.executeUpdate();
						String T[] = pT.getText().split(",");
						for (int i = 0; i < T.length; i++) {
							SQL = "insert into phone_number_coaches(PhoneNumber,SSN_coa) values" + " (" + T[i] + ","
									+ cT.getText() + ");";
							stmt = con.prepareStatement(SQL);
							stmt.executeUpdate();
						}
						sucess("Coach has been added successfully");
					}
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function have interface of update Choach name,phone and email by select SSN couch. 
	public static void updateChoach(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our choaches updating");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView(
				"data:image/jpeg;base64,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");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("update", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label cL = new Label("The ssn of the choach ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The new name of the choach ");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		Label pL = new Label("The new phone numbers (eg:0595484,0295628)");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		Label eL = new Label("The new email of the choach ");
		eL.setFont(Font.font(eL.getText(), FontWeight.BOLD, 22));
		TextField eT = new TextField();
		eT.setFont(Font.font(eT.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		root.add(cL, 0, 0);
		root.add(cT, 1, 0);
		root.add(nL, 0, 1);
		root.add(nT, 1, 1);
		root.add(eL, 0, 2);
		root.add(eT, 1, 2);
		root.add(pL, 0, 3);
		root.add(pT, 1, 3);

		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(70);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (eT.getText().isEmpty() || pT.getText().isEmpty() || nT.getText().isEmpty()
						|| cT.getText().isEmpty())
					error("Please fill all the texts");
				else {
					String SQL = "SELECT * FROM coaches WHERE SSN = " + Integer.parseInt(cT.getText()) + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					if (rs.next()) {
						SQL = "update coaches set Name = '" + nT.getText() + "', email = '" + eT.getText()
								+ "' where SSN = " + Integer.parseInt(cT.getText()) + ";";
						System.out.println(SQL);
						stmt = con.prepareStatement(SQL);
						stmt.executeUpdate();
						String T[] = pT.getText().split(",");
						SQL = "DELETE FROM phone_number_member WHERE SSN =" + cT.getText() + ";";
						stmt = con.prepareStatement(SQL);
						stmt.executeUpdate();
						for (int i = 0; i < T.length; i++) {
							SQL = "INSERT INTO phone_number_member (Phone_number, SSN) VALUES (" + T[i] + ", "
									+ cT.getText() + ");;";
							stmt = con.prepareStatement(SQL);
							stmt.executeUpdate();
						}
						sucess("Coach has been updated successfully");
					} else
						error("This coach does not exist in the data base");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
	// This function have interface of delete a Choach by insert SSN couch
	public static void deleteChoach(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our Choaches deleting");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView(
				"https://www.pngfind.com/pngs/m/268-2681876_png-file-delete-icon-transparent-png-png-download.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("Delete", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label pL = new Label("The ssn of the choach ");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(100);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (pT.getText().isEmpty())
					error("Please fill the text with an id number");
				else {
					String SQL = "SELECT * FROM coaches WHERE SSN = " + Integer.parseInt(pT.getText()) + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					if (rs.next()) {
						SQL = "SELECT * FROM train WHERE SSN_c = " + Integer.parseInt(pT.getText()) + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						if (!rs.next()) {
							SQL = "delete from coaches where SSN = " + Integer.parseInt(pT.getText()) + ";";
							System.out.println(SQL);
							stmt = con.prepareStatement(SQL);
							stmt.executeUpdate();
							sucess("Choach has been deleted successfully");
						} else
							error("This coach can't be deleted since\nhe is train in a sport program");
					} else
						error("This coach does not exist in the data base");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function have interface of search about a choach by ssn and couch name 
	public static void searchChoach(Stage stage, Scene scene, VBox pane1) {
		Label header = new Label("Welcome To Our Choaches seaching");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		VBox pane = new VBox();
		Button ssn = new Button("search by the ssn");
		ssn.setFont(Font.font(ssn.getText(), FontWeight.BOLD, 30));
		Button name = new Button("search by the name");
		name.setFont(Font.font(name.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		VBox buttons = new VBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(20);
		buttons.getChildren().addAll(ssn, name, back);
		pane.getChildren().addAll(header, buttons);
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(120);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		ssn.setOnAction(e -> {
			try {
				searchChoachSSN(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		name.setOnAction(e -> {
			try {
				searchChoachName(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function search about a choach by ssn , which return name,phone and email address of the couch
	public static void searchChoachSSN(Stage stage, Scene scene, VBox pane1)
			throws SQLException, ClassNotFoundException {
		Label header = new Label("Welcome To Our Choach search by SSN");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button search = new Button("search", imageSave);
		search.setFont(Font.font(search.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(search, back);
		Label cL = new Label("The ssn of the Coach ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of the Coach ");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		Label eL = new Label("The email of the Coach ");
		eL.setFont(Font.font(eL.getText(), FontWeight.BOLD, 22));
		TextField eT = new TextField();
		eT.setFont(Font.font(eT.getText(), FontWeight.BOLD, 22));
		Label pL = new Label("The number phones of the Coach ");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		nT.setVisible(false);
		eT.setVisible(false);
		pT.setVisible(false);
		root.add(cL, 0, 0);
		root.add(cT, 1, 0);
		root.add(nL, 0, 1);
		root.add(nT, 1, 1);
		root.add(eL, 0, 2);
		root.add(eT, 1, 2);
		root.add(pL, 0, 3);
		root.add(pT, 1, 3);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(100);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		search.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (cT.getText().isEmpty())
					error("Please fill the text with ssn number");
				else {
					String SQL = "SELECT * FROM coaches WHERE SSN = " + Integer.parseInt(cT.getText()) + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					if (rs.next()) {
						nT.setVisible(true);
						eT.setVisible(true);
						pT.setVisible(true);
						nT.setText(rs.getString(2));
						eT.setText(rs.getString(3));
						SQL = "SELECT * FROM phone_number_coaches WHERE SSN_coa = " + Integer.parseInt(cT.getText())
								+ ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						String s = "";
						while (rs.next()) {
							s += rs.getString(1) + ", ";
						}
						pT.setText(s);

					} else
						error("This coach does not exis in the data base");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function search about a choach by Name , which return name,phone and email address of the couch
	public static void searchChoachName(Stage stage, Scene scene, VBox pane1)
			throws SQLException, ClassNotFoundException {
		Label header = new Label("Welcome To Our Choach search by name");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("search", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label cL = new Label("The ssn of the Coach ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of the Coach ");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		Label eL = new Label("The email of the Coach ");
		eL.setFont(Font.font(eL.getText(), FontWeight.BOLD, 22));
		TextField eT = new TextField();
		eT.setFont(Font.font(eT.getText(), FontWeight.BOLD, 22));
		Label pL = new Label("The number phones of the Coach ");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		cT.setVisible(false);
		eT.setVisible(false);
		pT.setVisible(false);
		root.add(nL, 0, 0);
		root.add(nT, 1, 0);
		root.add(cL, 0, 1);
		root.add(cT, 1, 1);
		root.add(eL, 0, 2);
		root.add(eT, 1, 2);
		root.add(pL, 0, 3);
		root.add(pT, 1, 3);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(100);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null, rs1;
				if (nT.getText().isEmpty())
					error("Please fill the text with a name");
				else {
					String SQL = "SELECT * FROM coaches WHERE Name = '" + nT.getText() + "';";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					if (rs.next()) {
						SQL = "SELECT * FROM coaches WHERE Name = '" + nT.getText() + "';";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						cT.setVisible(true);
						eT.setVisible(true);
						pT.setVisible(true);
						String ss = "";
						String es = "";
						String s = "";
						while (rs.next()) {
							ss += rs.getString(1) + ",";
							es += rs.getString(3) + ",";
							SQL = "SELECT * FROM phone_number_coaches WHERE SSN_coa = "
									+ Integer.parseInt(rs.getString(1)) + ";";
							stmt = con.prepareStatement(SQL);
							rs1 = stmt.executeQuery();
							while (rs1.next()) {
								s += rs1.getString(1) + ", ";
							}
							s += "/";
						}
						cT.setText(ss);
						eT.setText(es);
						pT.setText(s);

					} else
						error("This coach does not exis in the data base");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function have the equipment interface,which call Add,Update,Delete and search for a equipment
	public static void equipment(Stage stage, Scene scene, HBox root) {

		Label header = new Label("Welcome To Our Equipments information");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		VBox pane = new VBox();
		Button reg = new Button("Add new equipment");
		reg.setFont(Font.font(reg.getText(), FontWeight.BOLD, 30));
		Button update = new Button("Update equipment information");
		update.setFont(Font.font(update.getText(), FontWeight.BOLD, 30));
		Button delete = new Button("Delete an equipment");
		delete.setFont(Font.font(delete.getText(), FontWeight.BOLD, 30));
		Button search = new Button("search for an equipment");
		search.setFont(Font.font(search.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		VBox buttons = new VBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(50);
		buttons.getChildren().addAll(reg, update, delete, search, back);
		pane.getChildren().addAll(header, buttons);
		pane.setSpacing(80);
		pane.setAlignment(Pos.CENTER);
		stage.setMaximized(true);

		scene.setRoot(pane);
		reg.setOnAction(e -> {
			addEquipment(stage, scene, pane);
		});

		update.setOnAction(e -> {
			try {
				updateEquipment(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		delete.setOnAction(e -> {
			try {
				deleteEquipment(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		search.setOnAction(e -> {
			try {
				searchEquipment(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		back.setOnAction(e -> {
			scene.setRoot(root);
		});

		stage.setScene(scene);
		stage.show();
	}
    // This function have the interface of add new equipment to the system, by add equipment Id,name,sport type,brand and placeID
	public static void addEquipment(Stage stage, Scene scene, VBox pane1) {// done
		Label header = new Label("Welcome To Our equipment Registeration");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://www6.0zz0.com/2022/01/18/18/500192871.png");
		imageSave.setFitWidth(40);
		Button add = new Button("Add", imageSave);
		add.setFont(Font.font(add.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(add, back);
		Label eL = new Label("The Id of the equipment ");
		eL.setFont(Font.font(eL.getText(), FontWeight.BOLD, 22));
		TextField eT = new TextField();
		eT.setFont(Font.font(eT.getText(), FontWeight.BOLD, 22));
		Label pL = new Label("The Id of the place must be in");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of the equipment ");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		Label cL = new Label("The sport type of the equipment ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		Label bL = new Label("The brand of the equipment ");
		bL.setFont(Font.font(bL.getText(), FontWeight.BOLD, 22));
		TextField bT = new TextField();
		bT.setFont(Font.font(bT.getText(), FontWeight.BOLD, 22));
		root.add(eL, 0, 0);
		root.add(eT, 1, 0);
		root.add(pL, 0, 1);
		root.add(pT, 1, 1);
		root.add(nL, 0, 2);
		root.add(nT, 1, 2);
		root.add(cL, 0, 3);
		root.add(cT, 1, 3);
		root.add(bL, 0, 4);
		root.add(bT, 1, 4);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(70);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		add.setOnAction(e -> {
			if (eT.getText().isEmpty() || pT.getText().isEmpty() || nT.getText().isEmpty() || cT.getText().isEmpty()
					|| bT.getText().isEmpty())
				error("Please fill all the texts");
			else {
				try {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					PreparedStatement stmt = null;
					ResultSet rs = null;

					String SQL = "SELECT * FROM equipment_has WHERE EID = " + Integer.parseInt(eT.getText()) + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					if (rs.next()) {
						error("This ID is already exist in database");
					} else {
						SQL = "SELECT * FROM place WHERE PlaceID = " + Integer.parseInt(pT.getText()) + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						if (!rs.next()) {
							error(" PlaceID is doesnt exist in database");
						} else {
							SQL = "insert into equipment_has values (" + Integer.parseInt(eT.getText()) + ","
									+ Integer.parseInt(pT.getText()) + ",'" + nT.getText() + "','" + bT.getText()
									+ "','" + cT.getText() + "');";
							stmt = con.prepareStatement(SQL);
							stmt.executeUpdate();
							sucess("Equipment has been added successfully");
						}
					}
					rs.close();
					stmt.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}

		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function update equipment information by insert equipment ID 
	public static void updateEquipment(Stage stage, Scene s, VBox pane1) throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our equipment updating");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView(
				"data:image/jpeg;base64,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");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("update", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label eL = new Label("The Id of the equipment ");
		eL.setFont(Font.font(eL.getText(), FontWeight.BOLD, 22));
		TextField eT = new TextField();
		eT.setFont(Font.font(eT.getText(), FontWeight.BOLD, 22));
		Label pL = new Label("The new Id of the place must be in");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The new name of the equipment ");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		Label cL = new Label("The new sport type of the equipment ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		Label bL = new Label("The new brand of the equipment ");
		bL.setFont(Font.font(bL.getText(), FontWeight.BOLD, 22));
		TextField bT = new TextField();
		bT.setFont(Font.font(bT.getText(), FontWeight.BOLD, 22));
		root.add(eL, 0, 0);
		root.add(eT, 1, 0);
		root.add(pL, 0, 1);
		root.add(pT, 1, 1);
		root.add(nL, 0, 2);
		root.add(nT, 1, 2);
		root.add(cL, 0, 3);
		root.add(cT, 1, 3);
		root.add(bL, 0, 4);
		root.add(bT, 1, 4);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(70);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			s.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (eT.getText().isEmpty() || pT.getText().isEmpty() || nT.getText().isEmpty() || cT.getText().isEmpty()
						|| bT.getText().isEmpty())
					error("Please fill all the texts");
				else {
					String SQL = "SELECT * FROM equipment_has WHERE EID = " + Integer.parseInt(eT.getText()) + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();

					if (!rs.next()) {
						error(" ID does not exist in database");
					} else {
						SQL = "SELECT * FROM place WHERE PlaceID = " + Integer.parseInt(pT.getText()) + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						if (rs.next()) {
							SQL = "UPDATE equipment_has SET EID = " + Integer.parseInt(eT.getText()) + ", PlaceID = "
									+ Integer.parseInt(pT.getText()) + ", Ename = '" + nT.getText() + "', brand = '"
									+ bT.getText() + "', SportType = '" + cT.getText() + "' WHERE EID = "
									+ Integer.parseInt(eT.getText()) + ";";
							stmt = con.prepareStatement(SQL);
							stmt.executeUpdate();
							sucess("Equipment has been updated successfully");
						} else
							error("This place ID does not exist in the data base");
					}
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		s.setRoot(pane);
		stage.setScene(s);
		stage.show();
	}
    // This function is delete a Equipment by equipment ID
	public static void deleteEquipment(Stage stage, Scene scene, VBox pane1)
			throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our Equipment deleting");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView(
				"https://www.pngfind.com/pngs/m/268-2681876_png-file-delete-icon-transparent-png-png-download.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("Delete", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label pL = new Label("The Id of the Equipment ");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(100);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (pT.getText().isEmpty())
					error("Please fill the text with an id number");
				else {
					String SQL = "SELECT * FROM equipment_has WHERE EID = " + Integer.parseInt(pT.getText()) + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					if (rs.next()) {
						String SQL1 = "delete from equipment_has where EID = " + Integer.parseInt(pT.getText()) + ";";
						stmt = con.prepareStatement(SQL1);
						stmt.executeUpdate();
						sucess("Equipment has been deleted successfully");
					} else
						error("This equipment ID does not exist in the data base");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function search about a choach by Name, which return all choach information
	public static void searchEquipment(Stage stage, Scene scene, VBox pane1)
			throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our Equipment Searching");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("search", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label eL = new Label("The Id of the equipment ");
		eL.setFont(Font.font(eL.getText(), FontWeight.BOLD, 22));
		TextField eT = new TextField();
		eT.setFont(Font.font(eT.getText(), FontWeight.BOLD, 22));
		eT.setVisible(false);
		Label pL = new Label("The Id of the place must be in");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		pT.setVisible(false);
		Label nL = new Label("The name of the equipment ");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		Label cL = new Label("The sport type of the equipment ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		cT.setVisible(false);
		Label bL = new Label("The brand of the equipment ");
		bL.setFont(Font.font(bL.getText(), FontWeight.BOLD, 22));
		TextField bT = new TextField();
		bT.setFont(Font.font(bT.getText(), FontWeight.BOLD, 22));
		bT.setVisible(false);
		root.add(nL, 0, 0);
		root.add(nT, 1, 0);
		root.add(eL, 0, 1);
		root.add(eT, 1, 1);
		root.add(pL, 0, 2);
		root.add(pT, 1, 2);
		root.add(cL, 0, 3);
		root.add(cT, 1, 3);
		root.add(bL, 0, 4);
		root.add(bT, 1, 4);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(70);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (nT.getText().isEmpty())
					error("Please fill the text with an equipment name");
				else {
					String SQL = "select * from equipment_has where Ename= '" + nT.getText() + "';";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();

					boolean Existing = false;
					if (rs.next()) {
						Existing = true;
					}

					if (Existing) {
						eT.setVisible(true);

						pT.setVisible(true);
						cT.setVisible(true);
						bT.setVisible(true);
						String SQL1 = "select * from equipment_has where Ename= '" + nT.getText() + "';";
						stmt = con.prepareStatement(SQL1);
						rs = stmt.executeQuery();
						String pt = "", ct = "", bt = "", et = "";
						while (rs.next()) {
							et += rs.getString(1) + ",";
							pt += rs.getString(2) + ",";
							ct += rs.getString(5) + ",";
							bt += rs.getString(4) + ",";
						}
						eT.setText(et);
						pT.setText(pt);
						cT.setText(ct);
						bT.setText(bt);
					} else
						error("This equipment ID does not exist in the data base");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function have the place interface,which call Add,Update,Delete and search for a place
	public static void place(Stage stage, Scene scene, HBox root) {// done
		Label header = new Label("Welcome To Our Places information");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		VBox pane = new VBox();
		Button reg = new Button("Add new Place");
		reg.setFont(Font.font(reg.getText(), FontWeight.BOLD, 30));
		Button update = new Button("Update Place information");
		update.setFont(Font.font(update.getText(), FontWeight.BOLD, 30));
		Button delete = new Button("Delete a Place");
		delete.setFont(Font.font(delete.getText(), FontWeight.BOLD, 30));
		Button search = new Button("search for a Place");
		search.setFont(Font.font(search.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		VBox buttons = new VBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(50);
		buttons.getChildren().addAll(reg, update, delete, search, back);
		pane.getChildren().addAll(header, buttons);
		pane.setSpacing(80);
		pane.setAlignment(Pos.CENTER);
		stage.setMaximized(true);

		scene.setRoot(pane);
		reg.setOnAction(e -> {
			try {
				regPlace(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		update.setOnAction(e -> {
			try {
				updatePlace(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		delete.setOnAction(e -> {
			try {
				deletePlace(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		search.setOnAction(e -> {
			searchPlace(stage, scene, pane);
		});

		back.setOnAction(e -> {
			scene.setRoot(root);
		});

		stage.setScene(scene);
		stage.show();
	}
    // This function have the interface of add new place to the system by insert place id,name and capacity.
	public static void regPlace(Stage stage, Scene s, VBox pane1) throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our Place Registeration");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://www6.0zz0.com/2022/01/18/18/500192871.png");
		imageSave.setFitWidth(40);
		Button save = new Button("Add", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label pL = new Label("The Id of the Place ");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of the place ");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		Label cL = new Label("The capacity of the Place ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.add(nL, 0, 1);
		root.add(nT, 1, 1);
		root.add(cL, 0, 2);
		root.add(cT, 1, 2);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(70);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			s.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (pT.getText().isEmpty() || nT.getText().isEmpty() || cT.getText().isEmpty())
					error("Please fill all the texts");
				else {
					int ID = 0, cap = 0;
					error = false;
					try {
						ID = Integer.parseInt(pT.getText());
						cap = Integer.parseInt(cT.getText());
					} catch (Exception e1) {
						error = true;
						error("Please Enter Vaild v");
					}
					String SQL = "select * from place where PlaceID = " + ID + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					if (!rs.next() && error == false) {
						//

						String SQL1 = "insert into place values (" + ID + ",'" + nT.getText() + "'," + cap + ");";
						stmt = con.prepareStatement(SQL1);
						stmt.executeUpdate();
						sucess("Place added successfully");
					} else if (error == false)
						error("This place ID is already exist");

				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		s.setRoot(pane);
		stage.setScene(s);
		stage.show();

	}
    // This function for updatePlace information by ask for Place ID.
	public static void updatePlace(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our Place update information");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView(
				"data:image/jpeg;base64,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");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("update", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label pL = new Label("The Id of the Place ");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of the place ");
		nL.setFont(Font.font(nL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		Label cL = new Label("The capacity of the Place ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.add(nL, 0, 1);
		root.add(nT, 1, 1);
		root.add(cL, 0, 2);
		root.add(cT, 1, 2);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(70);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				int ID = 0, cap = 0;
				error = false;
				try {
					ID = Integer.parseInt(pT.getText());
					cap = Integer.parseInt(cT.getText());
				} catch (Exception e1) {
					error = true;
					error("Please Enter Vaild v");
				}
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (pT.getText().isEmpty() || nT.getText().isEmpty() || cT.getText().isEmpty())
					error("Please fill all the texts");
				else {
					String SQL = "select * from place where PlaceID = " + ID + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					if (rs.next() && error == false) {
						String SQL1 = "update place set PlaceID = " + ID + ", Name = '" + nT.getText()
								+ "', Place_Capacity = '" + cap + "' where PlaceID = " + ID + ";";
						stmt = con.prepareStatement(SQL1);
						stmt.executeUpdate();
						sucess("Place updated successfully");
					} else if (error == false)
						error("This place ID does not exist exist");
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function is delete a Place by place ID
	public static void deletePlace(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our Place deleting");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView(
				"https://www.pngfind.com/pngs/m/268-2681876_png-file-delete-icon-transparent-png-png-download.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("Delete", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label pL = new Label("The Id of the Place ");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(100);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (pT.getText().isEmpty())
					error("Please fill the text with an ID");
				else {
					int ID = 0;
					error = false;
					try {
						ID = Integer.parseInt(pT.getText());

					} catch (Exception e1) {
						error = true;
						error("Please Enter Vaild v");
					}
					if (error == false) {
						String SQL = "select * from place where PlaceID = " + ID + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						if (rs.next()) {
							SQL = "select * from equipment_has where PlaceID = " + ID + ";";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							if (!rs.next()) {
								SQL = "select * from sport_programs_runningin where PlaceID_runningIn = " + ID + ";";
								stmt = con.prepareStatement(SQL);
								rs = stmt.executeQuery();
								if (!rs.next()) {
									String SQL1 = "delete from place where PlaceID = " + ID + ";";
									stmt = con.prepareStatement(SQL1);
									stmt.executeUpdate();
									sucess("Place updated successfully");
								} else
									error("You can't delete this place since it have some sport programs");
							} else
								error("You can't delete this place since it have some equipments");
						} else
							error("This place ID does not exist exist");
					}
				}
				if (error == false) {
					rs.close();
					stmt.close();
					con.close();
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function have the main interface of searchPlace by place ID or place capacity
	public static void searchPlace(Stage stage, Scene scene, VBox pane1) {// done
		Label header = new Label("Welcome To Our Places seaching");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		VBox pane = new VBox();
		Button id = new Button("search by the ID");
		id.setFont(Font.font(id.getText(), FontWeight.BOLD, 30));
		Button cap = new Button("search by the capacity");
		cap.setFont(Font.font(cap.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		VBox buttons = new VBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(20);
		buttons.getChildren().addAll(id, cap, back);
		pane.getChildren().addAll(header, buttons);
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(120);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		id.setOnAction(e -> {
			try {
				searchPlaceID(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		cap.setOnAction(e -> {
			try {
				searchPlaceCap(stage, scene, pane);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function search about a place by ID , which return place name and capacity.
	public static void searchPlaceID(Stage stage, Scene scene, VBox pane1) throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our Place search ID");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button search = new Button("search", imageSave);
		search.setFont(Font.font(search.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(search, back);
		Label pL = new Label("The Id of the Place ");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		Label nL = new Label("The name of the Place ");
		nL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		nT.setVisible(false);
		Label cL = new Label("The capacity of the Place ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		cT.setVisible(false);
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.add(nL, 0, 1);
		root.add(nT, 1, 1);
		root.add(cL, 0, 2);
		root.add(cT, 1, 2);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(100);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		search.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (pT.getText().isEmpty())
					error("Please fill the text with an ID");
				else {
					int ID = 0;
					error = false;
					try {
						ID = Integer.parseInt(pT.getText());

					} catch (Exception e1) {
						error = true;
						error("Please Enter Vaild v");
					}
					if (error == false) {
						String SQL = "select * from place where PlaceID = " + ID + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						if (rs.next()) {
							String SQL1 = "select * from place where PlaceID = " + ID + ";";
							stmt = con.prepareStatement(SQL1);
							rs = stmt.executeQuery();
							nT.setVisible(true);
							cT.setVisible(true);
							if (rs.next()) {
								nT.setText(rs.getString(2));
								cT.setText(rs.getString(3));
							}
						} else
							error("This place ID does not exist exist");
					}
				}
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This function search about a place by place capacity , which return place ID and name.
	public static void searchPlaceCap(Stage stage, Scene scene, VBox pane1)
			throws SQLException, ClassNotFoundException {// done
		Label header = new Label("Welcome To Our Place search by Capacity");
		header.setFont(Font.font(header.getText(), FontWeight.BOLD, 40));
		GridPane root = new GridPane();
		VBox pane = new VBox();
		ImageView imageSave = new ImageView("https://cdn-icons-png.flaticon.com/512/122/122932.png");
		imageSave.setFitWidth(40);
		imageSave.setFitHeight(40);
		Button save = new Button("search", imageSave);
		save.setFont(Font.font(save.getText(), FontWeight.BOLD, 30));
		ImageView imageBack = new ImageView("https://cdn.pixabay.com/photo/2013/07/13/11/42/back-158491_640.png");
		imageBack.setFitWidth(40);
		imageBack.setFitHeight(40);
		Button back = new Button("Back", imageBack);
		back.setFont(Font.font(back.getText(), FontWeight.BOLD, 30));
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(200);
		buttons.getChildren().addAll(save, back);
		Label pL = new Label("The capacity of the Place ");
		pL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField pT = new TextField();
		pT.setFont(Font.font(pT.getText(), FontWeight.BOLD, 22));
		Label cL = new Label("The ID of the Place ");
		cL.setFont(Font.font(cL.getText(), FontWeight.BOLD, 22));
		TextField cT = new TextField();
		cT.setFont(Font.font(cT.getText(), FontWeight.BOLD, 22));
		cT.setVisible(false);
		Label nL = new Label("The name of the Place ");
		nL.setFont(Font.font(pL.getText(), FontWeight.BOLD, 22));
		TextField nT = new TextField();
		nT.setFont(Font.font(nT.getText(), FontWeight.BOLD, 22));
		nT.setVisible(false);
		root.add(pL, 0, 0);
		root.add(pT, 1, 0);
		root.add(cL, 0, 1);
		root.add(cT, 1, 1);
		root.add(nL, 0, 2);
		root.add(nT, 1, 2);
		root.setHgap(120);
		root.setVgap(15);
		root.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(header, root, buttons);
		pane.setSpacing(100);
		pane.setAlignment(Pos.CENTER);

		back.setOnAction(e -> {
			scene.setRoot(pane1);
		});

		save.setOnAction(e -> {
			try {
				DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
				con = a.connectDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				if (pT.getText().isEmpty())
					error("Please fill the text with capacity number");
				else {
					int cap = 0;
					error = false;
					try {
						cap = Integer.parseInt(pT.getText());

					} catch (Exception e1) {
						error = true;
						error("Please Enter Vaild v");
					}
					if (error == false) {
						String SQL = "select * from place where Place_Capacity" + " = " + cap + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						if (rs.next()) {
							String SQL1 = "select * from place where Place_Capacity = " + cap + ";";
							stmt = con.prepareStatement(SQL1);
							rs = stmt.executeQuery();
							nT.setVisible(true);
							cT.setVisible(true);
							String s1 = "";
							String s2 = "";
							while (rs.next()) {
								s1 += rs.getString(1) + ", ";
								s2 += rs.getString(2) + ", ";
							}
							nT.setText(s2);
							cT.setText(s1);
						} else
							error("There is no place with this capacity in the data base");
					}
				}
				if (error == false) {
					rs.close();
					stmt.close();
					con.close();
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}
    // This funcion return an error massages 
	public static void error(String s) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);
		alert.setContentText(s);
		alert.showAndWait();
	}
	// This funcion return an sucess massages 
	public static void sucess(String s) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);
		alert.setContentText(s);
		alert.showAndWait();
	}
    //This function have the members interface,which call Add,Update,Delete and search for a member
	public static void members(Stage memb, Scene membScene) throws IOException {
		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 18);
		memb.setTitle("Member Page");
		HBox root = new HBox(100);
		VBox buttons = new VBox(50);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(40));
		// Add new Member Button
		Button addNM = new Button("Add new Member");
		DropShadow shadow = new DropShadow();
		// Effect for Button
		addNM.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			addNM.setEffect(shadow);
		});
		addNM.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			addNM.setEffect(null);
		});
		// Action for Add new Member Button
		addNM.setOnAction(e -> {
			try {
				addMember(memb, membScene);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		addNM.setMinSize(200, 100);
		addNM.setStyle("-fx-background-color:lightblue");
		// Delete Member Button
		Button deleteMem = new Button("Delete Member");
		// Effect for Button
		deleteMem.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			deleteMem.setEffect(shadow);
		});
		deleteMem.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			deleteMem.setEffect(null);
		});
		// Action for Delete Member Button
		deleteMem.setOnAction(e -> {
			try {
				deleteMember(memb, membScene);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		deleteMem.setMinSize(200, 100);
		deleteMem.setStyle("-fx-background-color:lightblue");

		// Update Information about Member Button
		Button updatInfoMem = new Button("Update Information \nabout Member");
		// Effect for Button
		updatInfoMem.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			updatInfoMem.setEffect(shadow);
		});
		updatInfoMem.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			updatInfoMem.setEffect(null);
		});
		// Action for Update Information about Member Button
		updatInfoMem.setOnAction(e -> {
			try {
				updateMember(memb, membScene);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		updatInfoMem.setMinSize(200, 100);
		updatInfoMem.setStyle("-fx-background-color:lightblue");
		// Search a Member by id Button
		Button searchMem = new Button("Search a Member \n by id and Name");
		// Effect for Button
		searchMem.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			searchMem.setEffect(shadow);
		});
		searchMem.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			searchMem.setEffect(null);
		});
		// Action for Search a Member by id Button
		searchMem.setOnAction(e -> {
			try {
				findMember(memb, membScene);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		searchMem.setMinSize(200, 100);
		searchMem.setStyle("-fx-background-color:lightblue");
		// Return to main page Button
		Button returnToMainPage = new Button("Return to main page");
		// Effect for Button
		returnToMainPage.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			returnToMainPage.setEffect(shadow);
		});
		returnToMainPage.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			returnToMainPage.setEffect(null);
		});
		// Action for Return to main page Button
		returnToMainPage.setOnAction(e -> {
			try {
				mainPage(memb, membScene);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		returnToMainPage.setMinSize(200, 100);
		returnToMainPage.setStyle("-fx-background-color:lightblue");
		// Screen Format
		addNM.setFont(myfont2);
		deleteMem.setFont(myfont2);
		updatInfoMem.setFont(myfont2);
		searchMem.setFont(myfont2);
		returnToMainPage.setFont(myfont2);
		buttons.getChildren().addAll(addNM, deleteMem, updatInfoMem, searchMem);
		root.getChildren().addAll(buttons, returnToMainPage);
		// View content
		membScene.setRoot(root);
		memb.show();
	}
	// This function have the Sport Program interface,which call Add,Update,Delete and search for a Sport Program
	public static void programSport(Stage spoProgb, Scene spoProgScene) throws IOException {
		spoProgb.setTitle("Sport Program Page");
		HBox root = new HBox(100);
		VBox buttons = new VBox(50);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(40));
		// Add new Member Button
		Button addNSP = new Button("Add new Sport Program");
		DropShadow shadow = new DropShadow();
		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 18);
		
		// Effect for Button
		addNSP.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			addNSP.setEffect(shadow);
		});
		addNSP.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			addNSP.setEffect(null);
		});
		// Action for Add new Member Button
		addNSP.setOnAction(e -> {
			try {
				addSportProg(spoProgb, spoProgScene);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		addNSP.setMinSize(250, 100);
		addNSP.setStyle("-fx-background-color:lightblue");
		// Delete Member Button
		Button deleteSP = new Button("Delete Sport Program");
		// Effect for Button
		deleteSP.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			deleteSP.setEffect(shadow);
		});
		deleteSP.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			deleteSP.setEffect(null);
		});
		// Action for Delete Member Button
		deleteSP.setOnAction(e -> {
			try {
				DeletSportProg(spoProgb, spoProgScene);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		deleteSP.setMinSize(250, 100);
		deleteSP.setStyle("-fx-background-color:lightblue");

		// Update Information about Member Button
		Button updatInfoSP = new Button("Update Information \nabout Sport Program");
		// Effect for Button
		updatInfoSP.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			updatInfoSP.setEffect(shadow);
		});
		updatInfoSP.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			updatInfoSP.setEffect(null);
		});
		// Action for Update Information about Member Button
		updatInfoSP.setOnAction(e -> {
			try {
				UpdateSportProg(spoProgb, spoProgScene);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		updatInfoSP.setMinSize(250, 100);
		updatInfoSP.setStyle("-fx-background-color:lightblue");
		// Search a Member by id Button
		Button searchSP = new Button("Search a Sport Programs");
		// Effect for Button
		searchSP.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			searchSP.setEffect(shadow);
		});
		searchSP.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			searchSP.setEffect(null);
		});
		// Action for Search a Member by id Button
		searchSP.setOnAction(e -> {
			try {
				findSportProg(spoProgb, spoProgScene);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		searchSP.setMinSize(250, 100);
		searchSP.setStyle("-fx-background-color:lightblue");
		// Return to main page Button
		Button returnToMainPage = new Button("Return to main page");
		// Effect for Button
		returnToMainPage.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			returnToMainPage.setEffect(shadow);
		});
		returnToMainPage.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			returnToMainPage.setEffect(null);
		});
		// Action for Return to main page Button
		returnToMainPage.setOnAction(e -> {
			try {
				mainPage(spoProgb, spoProgScene);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		returnToMainPage.setMinSize(200, 100);
		returnToMainPage.setStyle("-fx-background-color:lightblue");
		// Screen Format
		addNSP.setFont(myfont2);
		deleteSP.setFont(myfont2);
		updatInfoSP.setFont(myfont2);
		searchSP.setFont(myfont2);
		returnToMainPage.setFont(myfont2);
		buttons.getChildren().addAll(addNSP, deleteSP, updatInfoSP, searchSP);
		root.getChildren().addAll(buttons, returnToMainPage);
		// View content
		spoProgScene.setRoot(root);
		spoProgb.show();

	}
    // main
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		launch(args);
		// addMember();
		// addRegMember();
		// deleteMember(c,s);
		// findMember(c,s);
		// updateMember(c,s);
		// addSportProg(c,s);
		// DeletSportProg();
		// findSportProg();
		// UpdateSportProg();
	}
    // This function for add new Member in one sport program
	public static void addMember(Stage memAd, Scene memAdScene) throws SQLException, ClassNotFoundException {// done
		VBox membA = new VBox(40);
		membA.setAlignment(Pos.CENTER);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);
		HBox hc1 = new HBox(120);
		HBox hc2 = new HBox(120);
		HBox hc3 = new HBox(120);
		HBox hc4 = new HBox(120);
		HBox hc5 = new HBox(120);
		HBox hc6 = new HBox(120);
		HBox hc7 = new HBox(80);

		hc1.setAlignment(Pos.CENTER);
		hc2.setAlignment(Pos.CENTER);
		hc3.setAlignment(Pos.CENTER);
		hc4.setAlignment(Pos.CENTER);
		hc5.setAlignment(Pos.CENTER);
		hc6.setAlignment(Pos.CENTER);
		hc7.setAlignment(Pos.CENTER);
		membA.setPadding(new Insets(30));

		// Customer Labels
		Label lc1 = new Label("Member ID:");
		Label lc2 = new Label("Member Name:");
		Label lc3 = new Label("Member Age:");
		Label lc4 = new Label("Member Emeil:");
		Label lc5 = new Label("Member Phone Numbers(split by ','):");
		Label lc6 = new Label("Id Of sport program of Member:");

		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);

		lc1.setFont(myfont2);
		lc2.setFont(myfont2);
		lc3.setFont(myfont2);
		lc4.setFont(myfont2);
		lc5.setFont(myfont2);
		lc6.setFont(myfont2);

		lc1.setMinSize(400, 30);
		lc2.setMinSize(400, 30);
		lc3.setMinSize(400, 30);
		lc4.setMinSize(400, 30);
		lc5.setMinSize(400, 30);
		lc6.setMinSize(400, 30);

		TextField tc1 = new TextField();
		TextField tc2 = new TextField();
		TextField tc3 = new TextField();
		TextField tc4 = new TextField();
		TextField tc5 = new TextField();
		TextField tc6 = new TextField();

		Image image2 = new Image("add.png");
		ImageView add = new ImageView(image2);
		add.setFitHeight(30);
		add.setFitWidth(30);
		// Add Button
		Button bc1 = new Button("Add",add);
		bc1.setContentDisplay(ContentDisplay.LEFT);
		DropShadow shadow = new DropShadow();
		// Effect for Button
		bc1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bc1.setEffect(shadow);
		});
		bc1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bc1.setEffect(null);
		});

		tc2.setEditable(false);
		tc3.setEditable(false);
		tc4.setEditable(false);
		tc5.setEditable(false);
		tc6.setEditable(false);

		tc1.setOnKeyTyped(e2 -> {
			// Check if the text is not empty
			if (tc1.getText() != "") {
				tc2.setEditable(true);
				tc2.setOnKeyTyped(e3 -> {
					if (tc2.getText() != "")
						tc3.setEditable(true);
					tc3.setOnKeyTyped(e4 -> {
						if (tc3.getText() != "")
							tc4.setEditable(true);
						tc4.setOnKeyTyped(e5 -> {
							if (tc4.getText() != "")
								tc5.setEditable(true);
							tc5.setOnKeyTyped(e6 -> {
								if (tc5.getText() != "")
									tc6.setEditable(true);
							});
						});
					});
				});
			}
		});
		// Action for Add Button

		bc1.setOnAction(e1 -> {
			wrongDate = false;
			if (tc1.getText() != "" && tc2.getText() != "" && tc3.getText() != "" && tc4.getText() != ""
					&& tc5.getText() != "" && tc6.getText() != "") {
				try {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					PreparedStatement stmt;
					ResultSet rs;
					String SQL;
					SQL = "select * from members;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					int Id = 0;
					String Name = null, Emeil = null, PhoneNums = null;
					String[] PhoneNumS = null;
					int sportProgId = 0;
					double Age = 0;
					try {
						Id = Integer.parseInt(tc1.getText());

						Name = tc2.getText();

						Age = Double.parseDouble(tc3.getText());

						Emeil = tc4.getText();

						PhoneNums = tc5.getText();

						sportProgId = Integer.parseInt(tc6.getText());

						PhoneNumS = PhoneNums.split(",");
						for (int y = 0; y < PhoneNumS.length; y++)
							PhoneNumS[y] = PhoneNumS[y].trim();
					} catch (Exception e) {
						wrongDate = true;
						alert.setContentText("Please enter valid Information");
						alert.showAndWait();

					}
					boolean Existing = false;
					boolean SPexisting = false;
					while (rs.next()) {
						if (Integer.parseInt(rs.getString(1)) == Id) {
							Existing = true;
							break;
						}
					}
					if (Existing == true) {
						alert.setContentText("Please Enter another Id becouse this id used");
						alert.showAndWait();
					} else {
						SQL = "select SPID,PC from sport_programs_runningin;";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						while (rs.next()) {
							if (Integer.parseInt(rs.getString(1)) == sportProgId) {
								SPexisting = true;
								break;
							}
						}
						if (SPexisting == true) {
							SQL = "select count(*) from registeration r where r.SPID_SP=" + sportProgId + ";";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							rs.next();
							int numberOfmemberInSP = Integer.parseInt(rs.getString(1));
							SQL = "select s.PC from sport_programs_runningin s where s.SPID=" + sportProgId + ";";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							rs.next();
							int ProgramCap = Integer.parseInt(rs.getString(1));
							if (numberOfmemberInSP < ProgramCap) {
								SQL = "insert into members(SSN,Name,age,email) values (" + Id + ",'" + Name + "'," + Age
										+ ",'" + Emeil + "');";
								stmt = con.prepareStatement(SQL);
								stmt.executeUpdate();
								SQL = "insert into registeration(SPID_SP,SSN_me) values (" + sportProgId + "," + Id
										+ ");";
								stmt = con.prepareStatement(SQL);
								stmt.executeUpdate();
								for (int i = 0; i < PhoneNumS.length; i++) {
									SQL = "select count(*) from phone_number_member p where Phone_number="
											+ PhoneNumS[i] + " and SSN=" + Id + ";";
									stmt = con.prepareStatement(SQL);
									rs = stmt.executeQuery();
									rs.next();
									if (Integer.parseInt(rs.getString(1)) == 0) {
										SQL = "insert into phone_number_member(Phone_number,SSN) values ("
												+ PhoneNumS[i] + "," + Id + ");";
										stmt = con.prepareStatement(SQL);
										stmt.executeUpdate();
										
									}
								}
								alert.setContentText("The insertion succeeded");
								alert.showAndWait();
							} else {
								alert.setContentText("you can't Enter this member becouse ther is no capasity");
								alert.showAndWait();
							}
						} else if (wrongDate == false) {
							alert.setContentText(
									"you can't insert this member because the sport programs does not exist");
							alert.showAndWait();
						}
					}

					rs.close();
					stmt.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			} else {
				alert.setContentText("Please enter all valid Information");
				alert.showAndWait();
			}
		});
		
		Image image3 = new Image("register.png");
		ImageView reg = new ImageView(image3);
		reg.setFitHeight(30);
		reg.setFitWidth(30);
		// Add Button
		Button bm3 = new Button("Regestir",reg);
		bm3.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bm3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bm3.setEffect(shadow);
		});
		bm3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bm3.setEffect(null);
		});
		bm3.setOnAction(e1 -> {
			try {
				addRegMember(memAd, memAdScene);
			} catch (ClassNotFoundException | SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		});

		Image image = new Image("back.png");
		ImageView back = new ImageView(image);
		back.setFitHeight(30);
		back.setFitWidth(30);
		// Back Button
		Button bm2 = new Button("Back",back);
		bm2.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bm2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bm2.setEffect(shadow);
		});
		bm2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bm2.setEffect(null);
		});
		// Action for Back Button
		bm2.setOnAction(e -> {
			try {
				members(memAd, memAdScene);
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		});

		bc1.setMinSize(200, 30);
		bm2.setMinSize(200, 30);
		bm3.setMinSize(200, 30);

		tc1.setMinSize(200, 30);
		tc2.setMinSize(200, 30);
		tc3.setMinSize(200, 30);
		tc4.setMinSize(200, 30);
		tc5.setMinSize(200, 30);
		tc6.setMinSize(200, 30);

		// Screen Format
		hc1.getChildren().addAll(lc1, tc1);
		hc2.getChildren().addAll(lc2, tc2);
		hc3.getChildren().addAll(lc3, tc3);
		hc4.getChildren().addAll(lc4, tc4);
		hc5.getChildren().addAll(lc5, tc5);
		hc6.getChildren().addAll(lc6, tc6);
		hc7.getChildren().addAll(bm3, bc1, bm2);

		membA.getChildren().addAll(hc1, hc2, hc3, hc4, hc5, hc6, hc7);
		// View content
		memAdScene.setRoot(membA);
		memAd.setTitle("Add new Member");
		memAd.show();
		alert.setContentText(
				"Note: Please enter the information in order and complete, otherwise the Add button will not work");
		alert.showAndWait();

	}
    // This function for member participation in more than one sport program
	public static void addRegMember(Stage memReg, Scene memRegScene) throws SQLException, ClassNotFoundException {// done
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);
		memReg.setTitle("Add Register and Remove Register");
		// Rent Add, Remove and Process Panes
		GridPane AddRemo = new GridPane();
		AddRemo.setVgap(20);
		AddRemo.setHgap(20);
		AddRemo.setPadding(new Insets(20));

		Font myfont = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);

		Label lR1 = new Label("Member ID:");

		lR1.setFont(myfont);

		TextField tRf1 = new TextField();
		TextArea tRa1 = new TextArea();
		tRa1.setEditable(false);
		tRf1.setOnAction(e -> {
			try {
				if (tRf1.getText() != "") {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL, info;
					wrongDate = false;
					int id = 0, existMem;
					try {
						id = Integer.parseInt(tRf1.getText());
					} catch (Exception e1) {
						wrongDate = true;
						alert.setContentText("Please enter valid Member ID Information");
						alert.showAndWait();
					}
					if (wrongDate == false) {
						SQL = "SELECT count(*) FROM members where SSN=" + id + " ;";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						existMem = Integer.parseInt(rs.getString(1));
						if (existMem == 1) {
							SQL = "SELECT * FROM members where SSN=" + id + " ;";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							rs.next();
							info = "the SSN: " + rs.getString(1) + "\n";
							info = info + "the Name: " + rs.getString(2) + "\n";
							info = info + "the age: " + rs.getString(3) + "\n";
							info = info + "the email: " + rs.getString(4) + "\n";
							SQL = "SELECT pm.phone_number FROM members m,phone_number_member pm where m.SSN=pm.SSN and m.SSN="
									+ id + ";";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							info = info + "the phone numbers:";

							while (rs.next()) {
								info = info + "(" + rs.getString(1) + ")" + " ";
							}
							info = info + "\n";
							SQL = "select s.Name from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and m.SSN="
									+ id + ";";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							info = info + "the Name Of Sports Programe:";

							while (rs.next()) {
								info = info + "(" + rs.getString(1) + ")" + " ";
							}
							tRa1.setText(info);
							rs.close();
							stmt.close();
							con.close();
						}
						else {
							alert.setContentText("This Member does not exist!!");
							alert.showAndWait();
						}
					}
				} else {
					alert.setContentText("Please enter valid Information");
					alert.showAndWait();
				}
			} catch (ClassNotFoundException | SQLException e3) {
				e3.printStackTrace();
			}
		});

		Label lR2 = new Label("Sport Program ID:");

		lR2.setFont(myfont);

		TextField tRf2 = new TextField();
		TextArea tRa2 = new TextArea();
		tRa2.setEditable(false);
		tRf2.setOnAction(e -> {
			try {
				if (tRf2.getText() != "") {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL, info = null;
					wrongDate = false;
					int id = 0;
					try {
						id = Integer.parseInt(tRf2.getText());
					} catch (Exception e1) {
						wrongDate = true;
						alert.setContentText("Please enter valid Member ID Information");
						alert.showAndWait();
					}
					if (wrongDate == false) {
						SQL = "SELECT count(*) FROM sport_programs_runningin where SPID=" + id + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						if (Integer.parseInt(rs.getString(1)) != 0) {
							System.out.print("What");
							SQL = "SELECT * FROM sport_programs_runningin where SPID=" + id + ";";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							rs.next();
							info = "the ID of the sport program: " + rs.getString(1) + "\n";
							info = info + "the Name of the sport program: " + rs.getString(3) + "\n";
							info = info + "the Start Date of the sport program:" + rs.getString(4) + "\n";
							info = info + "the End Date of the sport program:" + rs.getString(5) + "\n";
							info = info + "the Time and Days of the sport program:" + rs.getString(7) + "\n";
							info = info + "the capacity of the sport program:" + rs.getString(8) + "\n";
							info = info + "the price of the sport program:" + rs.getString(6) + "\n";
							info = info + "the Place ID of the sport program:" + rs.getString(2) + "\n";

							SQL = "select count(*) from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and s.SPID="
									+ id + ";";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							rs.next();
							if (Integer.parseInt(rs.getString(1)) != 0) {
								SQL = "select m.SSN,m.Name from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and s.SPID="
										+ id + ";";
								stmt = con.prepareStatement(SQL);
								rs = stmt.executeQuery();
								info = info + "the members in the sport program:\n";
								while (rs.next()) {
									info = info + "ID: " + rs.getString(1) + "  Name: " + rs.getString(2) + "\n";
								}
							}
						}
						else {
							alert.setContentText("This Sport Program does not exist!!");
							alert.showAndWait();
						}
					}

					tRa2.setText(info);
					rs.close();
					stmt.close();
					con.close();
				} else {
					alert.setContentText("Please enter valid Information");
					alert.showAndWait();
				}
			} catch (ClassNotFoundException | SQLException e3) {
				e3.printStackTrace();
			}
		});

		Image image2 = new Image("add.png");
		ImageView add = new ImageView(image2);
		add.setFitHeight(30);
		add.setFitWidth(30);
		Image image3 = new Image("register.png");
		ImageView reg = new ImageView(image3);
		reg.setFitHeight(30);
		reg.setFitWidth(30);
		Image image = new Image("back.png");
		ImageView backi = new ImageView(image);
		backi.setFitHeight(30);
		backi.setFitWidth(30);
		// Add To Cart Button
		Button addReg = new Button("Add Register",reg);
		addReg.setContentDisplay(ContentDisplay.LEFT);
		DropShadow shadow = new DropShadow();
		// Effect for Button
		addReg.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			addReg.setEffect(shadow);
		});
		addReg.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			addReg.setEffect(null);
		});

		addReg.setOnAction(e -> {
			try {
				if (tRf2.getText() != "" && tRf1.getText() != "") {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;
					int Id = 0;
					int IdPro = 0;
					try {
						Id = Integer.parseInt(tRf1.getText());

						IdPro = Integer.parseInt(tRf2.getText());
					} catch (Exception e1) {
						alert.setContentText("Please enter valid Information");
						alert.showAndWait();
					}
					boolean Existing = false;
					boolean SPexisting = false;
					SQL = "select * from members;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					while (rs.next()) {
						if (Integer.parseInt(rs.getString(1)) == Id) {
							Existing = true;
							break;
						}
					}
					SQL = "select * from sport_programs_runningin;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					while (rs.next()) {
						if (Integer.parseInt(rs.getString(1)) == IdPro) {
							SPexisting = true;
							break;
						}
					}
					if (Existing == true && SPexisting == true) {
						SQL = "select count(*) from registeration r where r.SPID_SP=" + IdPro + " and r.SSN_me=" + Id
								+ ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();

						if (Integer.parseInt(rs.getString(1)) == 0) {
							SQL = "select s.StartDate,s.EndDate,s.TimeAndDays from sport_programs_runningin s where s.SPID="
									+ IdPro + ";";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							rs.next();
							String[] dateStart1 = rs.getString(1).split("-");
							int yearStart1 = Integer.parseInt(dateStart1[0]);
							int manthStart1 = Integer.parseInt(dateStart1[1]);
							int dayStart1 = Integer.parseInt(dateStart1[2]);
							String[] dateEnd1 = rs.getString(2).split("-");
							int yearEnd1 = Integer.parseInt(dateEnd1[0]);
							int manthEnd1 = Integer.parseInt(dateEnd1[1]);
							int dayEnd1 = Integer.parseInt(dateEnd1[2]);
							String[] Days1 = rs.getString(3).split(",");
							String[] Times1 = Days1[(Days1.length - 1)].split("-");
							String[] TimeStart1 = Times1[0].split(":");
							String[] TimeEnd1 = Times1[1].split(":");
							int HoursStart1 = Integer.parseInt(TimeStart1[0]);
							int HoursEnd1 = Integer.parseInt(TimeEnd1[0]);

							int MinStart1 = Integer.parseInt(TimeStart1[1]);
							int MinEnd1 = Integer.parseInt(TimeEnd1[1]);
							SQL = "select m.SSN,s.SPID,s.StartDate,s.EndDate,s.TimeAndDays from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and m.SSN="
									+ Id + ";";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							String[] dateStart2;
							int yearStart2;
							int manthStart2;
							int dayStart2;
							String[] dateEnd2;
							int yearEnd2;
							int manthEnd2;
							int dayEnd2;
							String[] Days2;
							String[] Times2;
							String[] TimeStart2;
							String[] TimeEnd2;
							int HoursStart2;
							int HoursEnd2;
							int MinStart2;
							int MinEnd2;

							boolean existCommon = true;

							while (rs.next()) {
								dateStart2 = rs.getString(3).split("-");
								yearStart2 = Integer.parseInt(dateStart2[0]);
								manthStart2 = Integer.parseInt(dateStart2[1]);
								dayStart2 = Integer.parseInt(dateStart2[2]);
								dateEnd2 = rs.getString(4).split("-");
								yearEnd2 = Integer.parseInt(dateEnd2[0]);
								manthEnd2 = Integer.parseInt(dateEnd2[1]);
								dayEnd2 = Integer.parseInt(dateEnd2[2]);
								Days2 = rs.getString(5).split(",");
								Times2 = Days2[(Days2.length - 1)].split("-");
								TimeStart2 = Times2[0].split(":");
								TimeEnd2 = Times2[1].split(":");
								HoursStart2 = Integer.parseInt(TimeStart2[0]);
								HoursEnd2 = Integer.parseInt(TimeEnd2[0]);
								MinStart2 = Integer.parseInt(TimeStart2[1]);
								MinEnd2 = Integer.parseInt(TimeEnd2[1]); //
								if ((yearEnd2 <= yearStart1 || yearEnd1 <= yearStart2)
										&& (yearStart1 != yearStart2 && yearEnd1 != yearEnd2)) {
									existCommon = false;

								} else if ((yearStart2 < yearEnd1 && yearStart1 < yearStart2)
										|| (yearStart1 < yearEnd2 && yearStart2 < yearStart1)
										|| (yearStart1 == yearStart2 && yearEnd1 == yearEnd2)
										|| (yearStart2 < yearStart1 && yearStart2 < yearEnd1 && yearEnd2 > yearEnd1
												&& yearEnd2 > yearStart1)
										|| (yearStart1 < yearStart2 && yearStart1 < yearEnd2 && yearEnd1 > yearEnd2
												&& yearEnd1 > yearStart2)
										|| (yearStart1 == yearStart2 && yearEnd1 < yearEnd2)
										|| (yearStart1 == yearStart2 && yearEnd2 < yearEnd1)
										|| (yearStart1 < yearStart2 && yearEnd1 == yearEnd2)
										|| (yearStart2 < yearStart1 && yearEnd1 == yearEnd2)) {
									if ((manthEnd2 <= manthStart1 || manthEnd1 <= manthStart2)
											&& (manthStart1 != manthStart2 && manthEnd1 != manthEnd2)) {
										existCommon = false;

									} else if ((manthStart2 < manthEnd1 && manthStart1 < manthStart2)
											|| (manthStart1 < manthEnd2 && manthStart2 < manthStart1)
											|| (manthStart1 == manthStart2 && manthEnd1 == manthEnd2)
											|| (manthStart2 < manthStart1 && manthStart2 < manthEnd1
													&& manthEnd2 > manthEnd1 && manthEnd2 > manthStart1)
											|| (manthStart1 < manthStart2 && manthStart1 < manthEnd2
													&& manthEnd1 > manthEnd2 && manthEnd1 > manthStart2)
											|| (manthStart1 == manthStart2 && manthEnd1 < manthEnd2)
											|| (manthStart1 == manthStart2 && manthEnd2 < manthEnd1)
											|| (manthStart1 < manthStart2 && manthEnd1 == manthEnd2)
											|| (manthStart2 < manthStart1 && manthEnd1 == manthEnd2)) {
										if ((dayEnd2 <= dayStart1 || dayEnd1 <= dayStart2)
												&& (dayStart1 != dayStart2 && dayEnd1 != dayEnd2)) {

											existCommon = false;
										} else if ((dayStart2 < dayEnd1 && dayStart1 < dayStart2)
												|| (dayStart1 < dayEnd2 && dayStart2 < dayStart1)
												|| (dayStart1 == dayStart2 && dayEnd1 == dayEnd2)
												|| (dayStart2 < dayStart1 && dayStart2 < dayEnd1 && dayEnd2 > dayEnd1
														&& dayEnd2 > dayStart1)
												|| (dayStart1 < dayStart2 && dayStart1 < dayEnd2 && dayEnd1 > dayEnd2
														&& dayEnd1 > dayStart2)
												|| (dayStart1 == dayStart2 && dayEnd1 < dayEnd2)
												|| (dayStart1 == dayStart2 && dayEnd2 < dayEnd1)
												|| (dayStart1 < dayStart2 && dayEnd1 == dayEnd2)
												|| (dayStart2 < dayStart1 && dayEnd1 == dayEnd2)) {
											boolean flagexisCom = false;

											for (int y = 0; y < (Days1.length - 1); y++) {
												for (int i = 0; i < (Days2.length - 1); i++) {
													if (Days1[y].equalsIgnoreCase(Days2[i])) {
														flagexisCom = true;
														break;
													}
													if (flagexisCom == true) {
														break;
													}
												}
											}
											if (flagexisCom == true) {

												if ((HoursEnd2 <= HoursStart1 || HoursEnd1 <= HoursStart2)
														&& (HoursStart1 != HoursStart2 && HoursEnd1 != HoursEnd2)) {
													existCommon = false;
												} else if ((HoursStart2 < HoursEnd1 && HoursStart1 < HoursStart2)
														|| (HoursStart1 < HoursEnd2 && HoursStart2 < HoursStart1)
														|| (HoursStart1 == HoursStart2 && HoursEnd1 == HoursEnd2)
														|| (HoursStart2 < HoursStart1 && HoursStart2 < HoursEnd1
																&& HoursEnd2 > HoursEnd1 && HoursEnd2 > HoursStart1)
														|| (HoursStart1 < HoursStart2 && HoursStart1 < HoursEnd2
																&& HoursEnd1 > HoursEnd2 && HoursEnd1 > HoursStart2)
														|| (HoursStart1 == HoursStart2 && HoursEnd1 < HoursEnd2)
														|| (HoursStart1 == HoursStart2 && HoursEnd2 < HoursEnd1)
														|| (HoursStart1 < HoursStart2 && HoursEnd1 == HoursEnd2)
														|| (HoursStart2 < HoursStart1 && HoursEnd1 == HoursEnd2)) {

													if ((MinEnd2 <= MinStart1 || MinEnd1 <= MinStart2)
															&& (MinStart1 != MinStart2 && MinEnd1 != MinEnd2)) {

														existCommon = false;
													} else if ((MinStart2 < MinEnd1 && MinStart1 < MinStart2)
															|| (MinStart1 < MinEnd2 && MinStart2 < MinStart1)
															|| (MinStart1 == MinStart2 && MinEnd1 == MinEnd2)
															|| (MinStart2 < MinStart1 && MinStart2 < MinEnd1
																	&& MinEnd2 > MinEnd1 && MinEnd2 > MinStart1)
															|| (MinStart1 < MinStart2 && MinStart1 < MinEnd2
																	&& MinEnd1 > MinEnd2 && MinEnd1 > MinStart2)
															|| (MinStart1 == MinStart2 && MinEnd1 < MinEnd2)
															|| (MinStart1 == MinStart2 && MinEnd2 < MinEnd1)
															|| (MinStart1 < MinStart2 && MinEnd1 == MinEnd2)
															|| (MinStart2 < MinStart1 && MinEnd1 == MinEnd2)) {

														existCommon = true;
														break;
													}
												}
											} else {
												existCommon = false;
												continue;
											}
										}
									}
								}
							}
							if (existCommon == false) {
								SQL = "select count(*) from registeration r where r.SPID_SP=" + IdPro + ";";
								stmt = con.prepareStatement(SQL);
								rs = stmt.executeQuery();
								rs.next();
								int numberOfmemberInSP = Integer.parseInt(rs.getString(1));
								SQL = "select s.PC from sport_programs_runningin s where s.SPID=" + IdPro + ";";
								stmt = con.prepareStatement(SQL);
								rs = stmt.executeQuery();
								rs.next();
								int ProgramCap = Integer.parseInt(rs.getString(1));
								if (numberOfmemberInSP < ProgramCap) {
									SQL = "insert into registeration(SPID_SP,SSN_me) values (" + IdPro + "," + Id
											+ ");";
									stmt = con.prepareStatement(SQL);
									stmt.executeUpdate();
									alert.setContentText("the registration done");
									alert.showAndWait();
								} else {
									alert.setContentText("you can't reg becouse there is no capasity");
									alert.showAndWait();
								}
							} else {
								alert.setContentText("there is problem in time");
								alert.showAndWait();
							}

						} else {
							alert.setContentText("the member had reg this prog before");
							alert.showAndWait();
						}

					} else {
						if (Existing == false) {
							alert.setContentText("the member does not exict");
							alert.showAndWait();
						}
						if (SPexisting == false) {
							alert.setContentText("the sport programe does not exict");
							alert.showAndWait();
						}
					}

					rs.close();
					stmt.close();
					con.close();
				} else {
					alert.setContentText("Please enter all valid Information");
					alert.showAndWait();
				}
			} catch (ClassNotFoundException | SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		});

		
		Image image4 = new Image("remove.png");
		ImageView remove = new ImageView(image4);
		remove.setFitHeight(30);
		remove.setFitWidth(30);
		// Remove From Cart Button
		Button removeReg = new Button("Remove Register",remove);
		removeReg.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		removeReg.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			removeReg.setEffect(shadow);
		});
		removeReg.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			removeReg.setEffect(null);
		});
		removeReg.setOnAction(e -> {
			try {
				if (tRf2.getText() != "" && tRf1.getText() != "") {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;
					int Id = 0;
					int IdPro = 0;
					try {
						Id = Integer.parseInt(tRf1.getText());
						IdPro = Integer.parseInt(tRf2.getText());
					} catch (Exception e1) {
						alert.setContentText("Please enter valid Information");
						alert.showAndWait();
					}
					boolean Existing = false;
					boolean SPexisting = false;
					SQL = "select * from members;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					while (rs.next()) {
						if (Integer.parseInt(rs.getString(1)) == Id) {
							Existing = true;
							break;
						}
					}
					SQL = "select * from sport_programs_runningin;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					while (rs.next()) {
						if (Integer.parseInt(rs.getString(1)) == IdPro) {
							SPexisting = true;
							break;
						}
					}
					if (Existing == true && SPexisting == true) {
						SQL = "select count(*) from registeration r where r.SSN_me=" + Id + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						int numOfregFormem = Integer.parseInt(rs.getString(1));
						SQL = "select count(*) from registeration r where r.SSN_me=" + Id + " and r.SPID_SP=" + IdPro
								+ ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						int isreg = Integer.parseInt(rs.getString(1));
						if (isreg >= 1) {
							if (numOfregFormem > 1) {
								SQL = "DELETE FROM registeration WHERE SPID_SP =" + IdPro + " and SSN_me=" + Id + " ;";
								stmt = con.prepareStatement(SQL);
								stmt.executeUpdate();
								alert.setContentText("The deletion succeeded");
								alert.showAndWait();
							} else {
								alert.setContentText(
										"You can't delete this reg because there is total partispation between member and sport program");
								alert.showAndWait();
							}

						} else {
							alert.setContentText("this member has not reg this prog");
							alert.showAndWait();
						}
					} else {
						if (Existing == false) {
							alert.setContentText("the member does not exict");
							alert.showAndWait();
						}
						if (SPexisting == false) {
							alert.setContentText("the sport programe does not exict");
							alert.showAndWait();
						}
					}

					rs.close();
					stmt.close();
					con.close();
				} else {
					alert.setContentText("Please enter all valid Information");
					alert.showAndWait();
				}
			} catch (ClassNotFoundException | SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		});

		// Back Button
		Button back = new Button("Back",backi);
		back.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		back.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e1) -> {
			back.setEffect(shadow);
		});
		back.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e1) -> {
			back.setEffect(null);
		});
		// Action for Back Button
		back.setOnAction(e -> {
			try {
				addMember(memReg, memRegScene);
			} catch (ClassNotFoundException | SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});

		AddRemo.add(lR1, 0, 0);
		AddRemo.add(tRf1, 1, 0);
		AddRemo.add(tRa1, 1, 1);
		AddRemo.add(lR2, 0, 2);
		AddRemo.add(tRf2, 1, 2);
		AddRemo.add(tRa2, 1, 3);
		AddRemo.add(addReg, 0, 5);
		AddRemo.add(removeReg, 1, 5);
		AddRemo.add(back, 4, 5);
		// View content
		memRegScene.setRoot(AddRemo);
		memReg.show();
		alert.setContentText(
				"Note: To perform the operation correctly, please enter the Member ID, then press the Enter button\nThen enter the Sport Program ID, then the Enter button, and then the rest of the buttons");
		alert.showAndWait();

	}
    // This function is delete a member by member SSN
	public static void deleteMember(Stage membRe, Scene membReScene) throws SQLException, ClassNotFoundException {// done
		VBox membR = new VBox(40);
		membR.setAlignment(Pos.CENTER);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);
		
		Image image2 = new Image("remove.png");
		ImageView remove = new ImageView(image2);
		remove.setFitHeight(30);
		remove.setFitWidth(30);
		Image image3 = new Image("search.png");
		ImageView search = new ImageView(image3);
		search.setFitHeight(30);
		search.setFitWidth(30);
		Image image4 = new Image("back.png");
		ImageView backi = new ImageView(image4);
		backi.setFitHeight(30);
		backi.setFitWidth(30);

		HBox hr1 = new HBox(40);
		HBox hr2 = new HBox(40);
		HBox hr3 = new HBox(40);
		HBox hr4 = new HBox(40);
		HBox hr5 = new HBox(40);
		HBox hr6 = new HBox(40);
		HBox hr7 = new HBox(40);
		hr1.setAlignment(Pos.CENTER);
		hr2.setAlignment(Pos.CENTER);
		hr3.setAlignment(Pos.CENTER);
		hr4.setAlignment(Pos.CENTER);
		hr5.setAlignment(Pos.CENTER);
		hr6.setAlignment(Pos.CENTER);
		hr7.setAlignment(Pos.CENTER);
		membR.setPadding(new Insets(30));

		Label lr1 = new Label("Member ID:");
		Label lr2 = new Label("Member Name:");
		Label lr3 = new Label("Member Age:");
		Label lr4 = new Label("Member Emeil:");
		Label lr5 = new Label("Member Phone Numbers:");
		Label lr6 = new Label("the Name Of Sports Programe:");
		// Customer TextField
		TextField tr1 = new TextField();
		TextArea tr2 = new TextArea();
		TextArea tr3 = new TextArea();
		TextArea tr4 = new TextArea();
		TextArea tr5 = new TextArea();
		TextArea tr6 = new TextArea();

		tr2.setMaxSize(65, 25);
		tr3.setMaxSize(65, 25);
		tr4.setMaxSize(65, 25);
		tr5.setMaxSize(65, 25);
		tr6.setMaxSize(65, 25);
		// Make TextArea ineffective
		tr2.setEditable(false);
		tr3.setEditable(false);
		tr4.setEditable(false);
		tr5.setEditable(false);
		tr6.setEditable(false);
		/*
		 * Image image = new Image("remove.png"); ImageView cusim = new
		 * ImageView(image); cusim.setFitHeight(30); cusim.setFitWidth(30);
		 */
		// Remove Button
		Button br1 = new Button("Remove",remove);
		DropShadow shadow = new DropShadow();
		br1.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		br1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			br1.setEffect(shadow);
		});
		br1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			br1.setEffect(null);
		});
		br1.setOnAction(e -> {
			if (tr1.getText() != "") {
				try {
					wrongDate = false;
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;
					int Id = 0;
					try {
						Id = Integer.parseInt(tr1.getText());
					} catch (Exception e1) {
						wrongDate = true;
						alert.setContentText("Please Enter valid ID");
						alert.showAndWait();
					}
					SQL = "SELECT count(*) FROM members where SSN=" + Id + " ;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					rs.next();
					int existMem = Integer.parseInt(rs.getString(1));
					if (existMem != 0) {
						SQL = "DELETE FROM members WHERE SSN=" + Id + ";";
						stmt = con.prepareStatement(SQL);
						stmt.executeUpdate();

						tr1.setText("");
						tr2.setText("");
						tr3.setText("");
						tr4.setText("");
						tr5.setText("");
						tr6.setText("");
						alert.setContentText("The deletion succeeded");
						alert.showAndWait();
						rs.close();
						stmt.close();
						con.close();

					} else {
						if (existMem == 0 && wrongDate != true) {
							alert.setContentText("No member by the Id");
							alert.showAndWait();
						}
					}

				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}

			} else {
				alert.setContentText("Please Enter the ID");
				alert.showAndWait();
			}
		});
		// Back Button
		Button br2 = new Button("Back",backi);
		br2.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		br2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			br2.setEffect(shadow);
		});
		br2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			br2.setEffect(null);
		});
		// Action for Back Button
		br2.setOnAction(e -> {
			try {
				members(membRe, membReScene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		/*
		 * Image image3 = new Image("search.png"); ImageView cusim3 = new
		 * ImageView(image3); cusim3.setFitHeight(30); cusim3.setFitWidth(30);
		 */
		// Find Button
		Button br3 = new Button("Find",search);
		br3.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		br3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			br3.setEffect(shadow);
		});
		br3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			br3.setEffect(null);
		});

		br3.setOnAction(e -> {
			if (tr1.getText() != "") {
				try {
					wrongDate = false;
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;
					int Id = 0;
					try {
						Id = Integer.parseInt(tr1.getText());
					} catch (Exception e1) {
						wrongDate = true;
						alert.setContentText("Please Enter valid ID");
						alert.showAndWait();
					}
					SQL = "SELECT count(*) FROM members where SSN=" + Id + " ;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					rs.next();
					int existMem = Integer.parseInt(rs.getString(1));
					if (existMem != 0) {
						SQL = "SELECT * FROM members where SSN=" + Id + " ;";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						tr2.setText(rs.getString(2));
						tr3.setText(rs.getString(3));
						tr4.setText(rs.getString(4));

						SQL = "SELECT pm.phone_number FROM members m,phone_number_member pm where m.SSN=pm.SSN and m.SSN="
								+ Id + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						String info = "";
						while (rs.next()) {
							info = info + "(" + rs.getString(1) + ")" + " ";
						}
						tr5.setText(info);

						SQL = "select s.Name from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and m.SSN="
								+ Id + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						info = "";
						while (rs.next()) {
							info = info + "(" + rs.getString(1) + ")" + " ";
						}
						tr6.setText(info);
						rs.close();
						stmt.close();
						con.close();

					} else {
						if (existMem == 0 && wrongDate != true) {
							alert.setContentText("No member by the Id");
							alert.showAndWait();
						}
					}

				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}

			} else {
				alert.setContentText("Please Enter the ID");
				alert.showAndWait();
			}
		});
		br1.setMinSize(200, 30);
		br2.setMinSize(200, 30);
		br3.setMinSize(200, 30);

		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);

		lr1.setFont(myfont2);
		lr2.setFont(myfont2);
		lr3.setFont(myfont2);
		lr4.setFont(myfont2);
		lr5.setFont(myfont2);
		lr6.setFont(myfont2);

		lr1.setMinSize(300, 30);
		lr2.setMinSize(300, 30);
		lr3.setMinSize(300, 30);
		lr4.setMinSize(300, 30);
		lr5.setMinSize(300, 30);
		lr6.setMinSize(300, 30);

		tr1.setMinSize(300, 30);
		tr2.setMinSize(300, 30);
		tr3.setMinSize(300, 30);
		tr4.setMinSize(300, 30);
		tr5.setMinSize(300, 30);
		tr6.setMinSize(300, 30);
		// Screen Format
		hr1.getChildren().addAll(lr1, tr1);
		hr2.getChildren().addAll(lr2, tr2);
		hr3.getChildren().addAll(lr3, tr3);
		hr4.getChildren().addAll(lr4, tr4);
		hr5.getChildren().addAll(lr5, tr5);
		hr6.getChildren().addAll(lr6, tr6);
		hr7.getChildren().addAll(br3, br1, br2);

		membR.getChildren().addAll(hr1, hr2, hr3, hr4, hr5, hr6, hr7);
		// View content
		membReScene.setRoot(membR);
		membRe.setTitle("Delete Customer");
		membRe.show();
		alert.setContentText(
				"Note: For Search Enter the id and press Find button\nFor Remove Enter the id and press Remove button");
		alert.showAndWait();

	}
    // This function for find a member by SSN, or by SSN and Name
	public static void findMember(Stage membSe, Scene membSeScene) throws SQLException, ClassNotFoundException {// done

		VBox custS = new VBox(40);
		custS.setAlignment(Pos.CENTER);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);

		Image image3 = new Image("search.png");
		ImageView search = new ImageView(image3);
		search.setFitHeight(30);
		search.setFitWidth(30);
		Image image4 = new Image("back.png");
		ImageView backi = new ImageView(image4);
		backi.setFitHeight(30);
		backi.setFitWidth(30);
		
		
		HBox hs1 = new HBox(40);
		HBox hs2 = new HBox(40);
		HBox hs3 = new HBox(40);
		HBox hs4 = new HBox(40);
		HBox hs5 = new HBox(40);
		HBox hs6 = new HBox(40);
		HBox hs7 = new HBox(40);

		hs1.setAlignment(Pos.CENTER);
		hs2.setAlignment(Pos.CENTER);
		hs3.setAlignment(Pos.CENTER);
		hs4.setAlignment(Pos.CENTER);
		hs5.setAlignment(Pos.CENTER);
		hs6.setAlignment(Pos.CENTER);
		hs7.setAlignment(Pos.CENTER);
		custS.setPadding(new Insets(50));

		Label ls1 = new Label("Member ID:");
		Label ls2 = new Label("Member Name:");
		Label ls3 = new Label("Member age:");
		Label ls4 = new Label("Member email:");
		Label ls5 = new Label("Member phone numbers:");
		Label ls6 = new Label("the Name Of Sports Programe:");

		TextField ts1 = new TextField();
		TextArea ts2 = new TextArea();
		TextArea ts3 = new TextArea();
		TextArea ts4 = new TextArea();
		TextArea ts5 = new TextArea();
		TextArea ts6 = new TextArea();

		ts2.setMaxSize(65, 25);
		ts3.setMaxSize(65, 25);
		ts4.setMaxSize(65, 25);
		ts5.setMaxSize(65, 25);
		ts6.setMaxSize(65, 25);
		// Make TextArea ineffective
		ts3.setEditable(false);
		ts4.setEditable(false);
		ts5.setEditable(false);
		ts6.setEditable(false);

		// Search Button
		Button bs1 = new Button("Search",search);
		bs1.setContentDisplay(ContentDisplay.LEFT);
		DropShadow shadow = new DropShadow();
		// Effect for Button
		bs1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bs1.setEffect(shadow);
		});
		bs1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bs1.setEffect(null);
		});

		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);

		bs1.setOnAction(e -> {
			wrongDate = false;
			if (ts1.getText() != "" || ts2.getText() != "") {
				try {
					wrongDate = false;
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;

					int Id = 0;
					if (ts1.getText() != "")
						try {
							Id = Integer.parseInt(ts1.getText());
						} catch (Exception e1) {
							wrongDate = true;
							alert.setContentText("Enter Vaild ID");
							alert.showAndWait();
						}
					String name = ts2.getText();

					int existMem = 0;
					if (ts1.getText() != "") {
						SQL = "SELECT count(*) FROM members where SSN=" + Id + " ;";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						existMem = Integer.parseInt(rs.getString(1));
					}
					int existMemN = 0;
					if (ts2.getText() != "") {
						SQL = "SELECT count(*) FROM members where Name='" + ts2.getText() + "' ;";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						existMemN = Integer.parseInt(rs.getString(1));
					}
					boolean both = false;
					boolean single = false;
					if (name != "" && ts1.getText() != "" && existMem != 0 && existMemN > 0) {
						both = true;
						SQL = "SELECT * FROM members where SSN=" + Id + " and Name='" + name + "';";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						String info = "";
						ts1.setText(rs.getString(1));
						ts2.setText(rs.getString(2));
						ts3.setText(rs.getString(3));
						ts4.setText(rs.getString(4));
						SQL = "SELECT pm.phone_number FROM members m,phone_number_member pm where m.SSN=pm.SSN and m.SSN="
								+ Id + " and m.Name='" + name + "';";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();

						while (rs.next()) {
							info = info + "(" + rs.getString(1) + ")" + " ";
						}
						ts5.setText(info);
						SQL = "select s.Name from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and m.SSN="
								+ Id + " and m.Name='" + name + "';";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						info = "";
						while (rs.next()) {
							info = info + "(" + rs.getString(1) + ")" + " ";
						}
						ts6.setText(info);
						rs.close();
						stmt.close();
						con.close();
					} else if (ts1.getText() != "" && existMem != 0 && name == "") {
						single = true;
						SQL = "SELECT * FROM members where SSN=" + Id + " ;";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						String info = "";
						ts1.setText(rs.getString(1));
						ts2.setText(rs.getString(2));
						ts3.setText(rs.getString(3));
						ts4.setText(rs.getString(4));
						SQL = "SELECT pm.phone_number FROM members m,phone_number_member pm where m.SSN=pm.SSN and m.SSN="
								+ Id + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						System.out.print("the phone numbers:");
						while (rs.next()) {
							info = info + "(" + rs.getString(1) + ")" + " ";
						}
						ts5.setText(info);
						info = "";
						SQL = "select s.Name from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and m.SSN="
								+ Id + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();

						while (rs.next()) {
							info = info + "(" + rs.getString(1) + ")" + " ";
						}
						ts6.setText(info);
						rs.close();
						stmt.close();
						con.close();

					} else {
						if (both != true && single == false && name != "" && ts1.getText() != ""
								&& wrongDate == false) {
							alert.setContentText("No member by these Id and Name");
							alert.showAndWait();
							ts1.setText("");
							ts2.setText("");
							ts3.setText("");
							ts4.setText("");
							ts5.setText("");
							ts6.setText("");

						} else if (single != true && both == false && name == "" && ts1.getText() != ""
								&& wrongDate == false) {
							alert.setContentText("No member by this Id");
							alert.showAndWait();
							ts1.setText("");
							ts2.setText("");
							ts3.setText("");
							ts4.setText("");
							ts5.setText("");
							ts6.setText("");
						} else {
							if (wrongDate == false) {
								alert.setContentText("Please Enter ID and Name or ID only to search");
								alert.showAndWait();
								ts1.setText("");
								ts2.setText("");
								ts3.setText("");
								ts4.setText("");
								ts5.setText("");
								ts6.setText("");
							}
						}

					}

				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}
			} else {
				alert.setContentText("Please Enter ID and Name or ID only to search");
				alert.showAndWait();
				ts1.setText("");
				ts2.setText("");
				ts3.setText("");
				ts4.setText("");
				ts5.setText("");
				ts6.setText("");
			}
		});

		// Back Button
		Button bs2 = new Button("Back",backi);
		bs2.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bs2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bs2.setEffect(shadow);
		});
		bs2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bs2.setEffect(null);
		});
		// Action for Back Button
		bs2.setOnAction(e -> {
			try {
				members(membSe, membSeScene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		bs1.setMinSize(200, 30);
		bs2.setMinSize(200, 30);

		ls1.setFont(myfont2);
		ls2.setFont(myfont2);
		ls3.setFont(myfont2);
		ls4.setFont(myfont2);
		ls5.setFont(myfont2);
		ls6.setFont(myfont2);

		ls1.setMinSize(300, 30);
		ls2.setMinSize(300, 30);
		ls3.setMinSize(300, 30);
		ls4.setMinSize(300, 30);
		ls5.setMinSize(300, 30);
		ls6.setMinSize(300, 30);

		ts1.setMinSize(300, 30);
		ts2.setMinSize(300, 30);
		ts3.setMinSize(300, 30);
		ts4.setMinSize(300, 30);
		ts5.setMinSize(300, 30);
		ts6.setMinSize(300, 30);
		// Screen Format

		hs1.getChildren().addAll(ls1, ts1);
		hs2.getChildren().addAll(ls2, ts2);
		hs3.getChildren().addAll(ls3, ts3);
		hs4.getChildren().addAll(ls4, ts4);
		hs5.getChildren().addAll(ls5, ts5);
		hs6.getChildren().addAll(ls6, ts6);
		hs7.getChildren().addAll(bs1, bs2);

		custS.getChildren().addAll(hs1, hs2, hs3, hs4, hs5, hs6, hs7);
		membSeScene.setRoot(custS);
		membSe.setTitle("Search a Customer by id");
		// View content
		membSe.show();
		alert.setContentText("Note:For Search Enter the Id or Id with Name and press Find button");
		alert.showAndWait();

	}
    // This function for update Member information by select SSN
	public static void updateMember(Stage membUp, Scene membUpScene) throws SQLException, ClassNotFoundException {// done
		VBox membU = new VBox(40);
		membU.setAlignment(Pos.CENTER);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);
		
		Image image2 = new Image("update.png");
		ImageView update = new ImageView(image2);
		update.setFitHeight(30);
		update.setFitWidth(30);
		Image image3 = new Image("search.png");
		ImageView search = new ImageView(image3);
		search.setFitHeight(30);
		search.setFitWidth(30);
		Image image4 = new Image("back.png");
		ImageView backi = new ImageView(image4);
		backi.setFitHeight(30);
		backi.setFitWidth(30);

		HBox hu1 = new HBox(40);
		HBox hu2 = new HBox(40);
		HBox hu3 = new HBox(40);
		HBox hu4 = new HBox(40);
		HBox hu5 = new HBox(40);
		HBox hu6 = new HBox(40);

		hu1.setAlignment(Pos.CENTER);
		hu2.setAlignment(Pos.CENTER);
		hu3.setAlignment(Pos.CENTER);
		hu4.setAlignment(Pos.CENTER);
		hu5.setAlignment(Pos.CENTER);
		hu6.setAlignment(Pos.CENTER);
		membU.setPadding(new Insets(30));
		// Member Update Labels
		Label lu1 = new Label("Member ID:");
		Label lu2 = new Label("Member Name:");
		Label lu3 = new Label("Member age:");
		Label lu4 = new Label("Member email:");
		Label lu5 = new Label("Member phone numbers(split by ','):");
		// Member Update TextField and ComboBox
		TextField tu1 = new TextField();
		TextField tu2 = new TextField();
		TextField tu3 = new TextField();
		TextField tu4 = new TextField();
		TextField tu5 = new TextField();

		// Back Button
		Button bu2 = new Button("Back",backi);
		DropShadow shadow = new DropShadow();
		bu2.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bu2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bu2.setEffect(shadow);
		});
		bu2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bu2.setEffect(null);
		});
		// Action for Back Button
		bu2.setOnAction(e -> {
			try {
				members(membUp, membUpScene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		Button bu3 = new Button("Find",search);
		bu3.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bu3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bu3.setEffect(shadow);
		});
		bu3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bu3.setEffect(null);
		});
		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);

		tu2.setEditable(false);
		tu3.setEditable(false);
		tu4.setEditable(false);
		tu5.setEditable(false);
		bu3.setOnAction(e -> {
			if (tu1.getText() != "") {
				try {
					wrongDate = false;
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;
					int Id = 0;
					try {
						Id = Integer.parseInt(tu1.getText());
						ID = Id;
					} catch (Exception e1) {
						wrongDate = true;
						alert.setContentText("Please Enter valid ID");
						alert.showAndWait();
					}
					SQL = "SELECT count(*) FROM members where SSN=" + Id + " ;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					rs.next();
					int existMem = Integer.parseInt(rs.getString(1));
					if (existMem != 0) {
						SQL = "SELECT * FROM members where SSN=" + Id + " ;";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();

						tu2.setText(rs.getString(2));
						tu3.setText(rs.getString(3));
						tu4.setText(rs.getString(4));

						SQL = "SELECT pm.phone_number FROM members m,phone_number_member pm where m.SSN=pm.SSN and m.SSN="
								+ Id + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						String info = "";
						while (rs.next()) {
							info = info + rs.getString(1) + ",";
						}

						tu5.setText(info);
						tu2.setEditable(true);
						tu3.setEditable(true);
						tu4.setEditable(true);
						tu5.setEditable(true);
						rs.close();
						stmt.close();
						con.close();

					} else {
						if (existMem == 0 && wrongDate != true) {
							alert.setContentText("No member by the Id");
							alert.showAndWait();
						}
					}

				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}

			} else {
				alert.setContentText("Please Enter the ID");
				alert.showAndWait();
			}
		});

		// Update Button
		Button bu1 = new Button("Update",update);
		bu1.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bu1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bu1.setEffect(shadow);
		});
		bu1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bu1.setEffect(null);
		});

		bu1.setOnAction(e -> {
			if (tu1.getText() != "" && tu2.getText() != "" && tu3.getText() != "" && tu4.getText() != ""
					&& tu5.getText() != "") {
				try {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt;
					ResultSet rs;
					String SQL;

					int Id = 0;
					try {
						Id = Integer.parseInt(tu1.getText());
					} catch (Exception e1) {
						alert.setContentText("Please Enter valid ID");
						alert.showAndWait();
					}

					boolean cantUp = false;

					SQL = "select * from members;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					while (rs.next()) {
						if (Integer.parseInt(rs.getString(1)) == Id && Id != ID) {

							cantUp = true;
							break;
						}
					}

					if (cantUp == false) {
						String Name = tu2.getText();
						double Age = Double.parseDouble(tu3.getText());
						String Emeil = tu4.getText();
						System.out.print("Enter Phone Numbers Of member(split by ',') to update:");
						String PhoneNums = tu5.getText();
						String[] PhoneNumS = PhoneNums.split(",");
						for (int y = 0; y < PhoneNumS.length; y++)
							PhoneNumS[y] = PhoneNumS[y].trim();
						SQL = "UPDATE members SET SSN =" + Id + ", Name = '" + Name + "', age = " + Age + ", email = '"
								+ Emeil + "' WHERE SSN = " + ID + ";";
						stmt = con.prepareStatement(SQL);
						stmt.executeUpdate();
						SQL = "DELETE FROM phone_number_member WHERE  SSN=" + Id + ";";
						stmt = con.prepareStatement(SQL);
						stmt.executeUpdate();
						for (int i = 0; i < PhoneNumS.length; i++) {
							SQL = "select count(*) from phone_number_member p where Phone_number=" + PhoneNumS[i]
									+ " and SSN=" + Id + ";";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							rs.next();
							if (Integer.parseInt(rs.getString(1)) == 0) {
								SQL = "insert into phone_number_member(Phone_number,SSN) values (" + PhoneNumS[i] + ","
										+ Id + ");";
								stmt = con.prepareStatement(SQL);
								stmt.executeUpdate();
							}
						}
						alert.setContentText("Update done");
						alert.showAndWait();

					} else {
						alert.setContentText("you can't update because the ID has used");
						alert.showAndWait();
					}

					rs.close();
					stmt.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}
			} else {
				alert.setContentText("search first Or fill all valid value after searching");
				alert.showAndWait();
			}

		});

		bu1.setMinSize(200, 30);
		bu2.setMinSize(200, 30);
		bu3.setMinSize(200, 30);

		lu1.setFont(myfont2);
		lu2.setFont(myfont2);
		lu3.setFont(myfont2);
		lu4.setFont(myfont2);
		lu5.setFont(myfont2);

		lu1.setMinSize(450, 30);
		lu2.setMinSize(450, 30);
		lu3.setMinSize(450, 30);
		lu4.setMinSize(450, 30);
		lu5.setMinSize(450, 30);

		tu1.setMinSize(300, 30);
		tu2.setMinSize(300, 30);
		tu3.setMinSize(300, 30);
		tu4.setMinSize(300, 30);
		tu5.setMinSize(300, 30);
		// Screen Format
		hu1.getChildren().addAll(lu1, tu1);
		hu2.getChildren().addAll(lu2, tu2);
		hu3.getChildren().addAll(lu3, tu3);
		hu4.getChildren().addAll(lu4, tu4);
		hu5.getChildren().addAll(lu5, tu5);
		hu6.getChildren().addAll(bu3, bu1, bu2);

		membU.getChildren().addAll(hu1, hu2, hu3, hu4, hu5, hu6);
		// View content
		membUpScene.setRoot(membU);
		membUp.setTitle("Update Information about ");
		membUp.show();

		alert.setContentText(
				"Note:For Search Enter the id and press Find button \n Please enter the information in order and complete, otherwise the Update button will not work");
		alert.showAndWait();

	}
    // This function for add new Sport Program by insert id,name,Capacity,Place ID,Start and end Date,Time And Days,price,and id of couches.
	public static void addSportProg(Stage SpAd, Scene SpAdScene) throws SQLException, ClassNotFoundException {// done

		VBox spA = new VBox(40);
		spA.setAlignment(Pos.CENTER);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);
		HBox hm1 = new HBox(120);
		HBox hm2 = new HBox(120);
		HBox hm3 = new HBox(120);
		HBox hm4 = new HBox(120);
		HBox hm5 = new HBox(120);
		HBox hm6 = new HBox(120);
		HBox hm7 = new HBox(120);
		HBox hm8 = new HBox(120);
		HBox hm9 = new HBox(120);
		HBox hm10 = new HBox(120);
		
		Image image2 = new Image("add.png");
		ImageView add = new ImageView(image2);
		add.setFitHeight(30);
		add.setFitWidth(30);
		Image image4 = new Image("back.png");
		ImageView backi = new ImageView(image4);
		backi.setFitHeight(30);
		backi.setFitWidth(30);

		hm1.setAlignment(Pos.CENTER);
		hm2.setAlignment(Pos.CENTER);
		hm3.setAlignment(Pos.CENTER);
		hm4.setAlignment(Pos.CENTER);
		hm5.setAlignment(Pos.CENTER);
		hm6.setAlignment(Pos.CENTER);
		hm7.setAlignment(Pos.CENTER);
		hm8.setAlignment(Pos.CENTER);
		hm9.setAlignment(Pos.CENTER);
		hm10.setAlignment(Pos.CENTER);
		spA.setPadding(new Insets(30));

		Label lm1 = new Label("Id Of Sport Program:");
		Label lm2 = new Label("Name Of Sport Program:");
		Label lm3 = new Label("Capacity Of Sport Program:");
		Label lm4 = new Label("Place ID Of Sport Program:");
		Label lm5 = new Label("Start Date Of Sport Program(e.g.: 2020-5-20 ):");
		Label lm6 = new Label("End Date Of Sport Program(e.g.: 2020-10-20 )");
		Label lm7 = new Label("Price Of Sport Program:");
		Label lm8 = new Label("Time And Days Of Sport Program(e.g.: T,R,1:30-2:20 ):");
		Label lm9 = new Label("Id Of coache:");

		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);

		lm1.setFont(myfont2);
		lm2.setFont(myfont2);
		lm3.setFont(myfont2);
		lm4.setFont(myfont2);
		lm5.setFont(myfont2);
		lm6.setFont(myfont2);
		lm7.setFont(myfont2);
		lm8.setFont(myfont2);
		lm9.setFont(myfont2);

		lm1.setMinSize(520, 30);
		lm2.setMinSize(520, 30);
		lm3.setMinSize(520, 30);
		lm4.setMinSize(520, 30);
		lm5.setMinSize(520, 30);
		lm6.setMinSize(520, 30);
		lm7.setMinSize(520, 30);
		lm8.setMinSize(520, 30);
		lm9.setMinSize(520, 30);

		TextField tm1 = new TextField();
		TextField tm2 = new TextField();
		TextField tm3 = new TextField();
		TextField tm4 = new TextField();
		TextField tm5 = new TextField();
		TextField tm6 = new TextField();
		TextField tm7 = new TextField();
		TextField tm8 = new TextField();
		TextField tm9 = new TextField();

		// Add Button
		Button bm1 = new Button("Add",add);
		bm1.setContentDisplay(ContentDisplay.LEFT);
		DropShadow shadow = new DropShadow();
		// Effect for Button
		bm1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bm1.setEffect(shadow);
		});
		bm1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bm1.setEffect(null);
		});

		tm2.setEditable(false);
		tm3.setEditable(false);
		tm4.setEditable(false);
		tm5.setEditable(false);
		tm6.setEditable(false);
		tm7.setEditable(false);
		tm8.setEditable(false);
		tm9.setEditable(false);

		tm1.setOnKeyTyped(e2 -> {
			// Check if the text is not empty
			if (tm1.getText() != "") {
				tm2.setEditable(true);
				tm2.setOnKeyTyped(e3 -> {
					if (tm2.getText() != "")
						tm3.setEditable(true);
					tm3.setOnKeyTyped(e4 -> {
						if (tm3.getText() != "")
							tm4.setEditable(true);
						tm4.setOnKeyTyped(e5 -> {
							if (tm4.getText() != "")
								tm5.setEditable(true);
							tm5.setOnKeyTyped(e6 -> {
								if (tm5.getText() != "")
									tm6.setEditable(true);
								tm6.setOnKeyTyped(e7 -> {
									if (tm6.getText() != "")
										tm7.setEditable(true);
									tm7.setOnKeyTyped(e8 -> {
										if (tm7.getText() != "")
											tm8.setEditable(true);
										tm8.setOnKeyTyped(e9 -> {
											if (tm8.getText() != "")
												tm9.setEditable(true);
										});
									});
								});
							});
						});
					});
				});
			}
		});

		// Action for Add Button
		bm1.setOnAction(e1 -> {
			wrongDate = false;
			if (tm1.getText() != "" && tm2.getText() != "" && tm3.getText() != "" && tm4.getText() != ""
					&& tm5.getText() != "" && tm6.getText() != "" && tm7.getText() != "" && tm8.getText() != ""
					&& tm9.getText() != "") {
				try {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					String SQL2 = null, Name = null, StartDate = null, EndDate = null, TimeAndDays = null;
					int Id = 0, cap = 0, coache = 0, placeId = 0;
					double price = 0;
					try {

						Id = Integer.parseInt(tm1.getText());

						Name = tm2.getText();

						cap = Integer.parseInt(tm3.getText());

						placeId = Integer.parseInt(tm4.getText());

						StartDate = tm5.getText();

						EndDate = tm6.getText();

						price = Double.parseDouble(tm7.getText());

						TimeAndDays = tm8.getText();

						coache = Integer.parseInt(tm9.getText());
					} catch (Exception e) {
						alert.setContentText("Please Enter Vaild values");
						alert.showAndWait();
					}
					String[] dateStart1 = StartDate.split("-");
					int yearStart1 = Integer.parseInt(dateStart1[0]);
					int manthStart1 = Integer.parseInt(dateStart1[1]);
					int dayStart1 = Integer.parseInt(dateStart1[2]);

					String[] dateEnd1 = EndDate.split("-");
					int yearEnd1 = Integer.parseInt(dateEnd1[0]);
					int manthEnd1 = Integer.parseInt(dateEnd1[1]);
					int dayEnd1 = Integer.parseInt(dateEnd1[2]);

					String[] Days1 = TimeAndDays.split(",");
					String[] Times1 = Days1[(Days1.length - 1)].split("-");
					String[] TimeStart1 = Times1[0].split(":");
					String[] TimeEnd1 = Times1[1].split(":");
					int HoursStart1 = Integer.parseInt(TimeStart1[0]);
					int HoursEnd1 = Integer.parseInt(TimeEnd1[0]);

					int MinStart1 = Integer.parseInt(TimeStart1[1]);
					int MinEnd1 = Integer.parseInt(TimeEnd1[1]);
					String[] dateStart2;
					int yearStart2;
					int manthStart2;
					int dayStart2;
					String[] dateEnd2;
					int yearEnd2;
					int manthEnd2;
					int dayEnd2;
					String[] Days2;
					String[] Times2;
					String[] TimeStart2;
					String[] TimeEnd2;
					int HoursStart2;
					int HoursEnd2;
					int MinStart2;
					int MinEnd2;

					int existPlace = 0;
					int existCoache = 0;
					boolean notEx=false;
					SQL = "SELECT count(*) FROM place where PlaceId=" + placeId + " ;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					rs.next();
					existPlace = Integer.parseInt(rs.getString(1));

					SQL = "SELECT count(*) FROM coaches where SSN=" + coache + " ;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					rs.next();
					existCoache = Integer.parseInt(rs.getString(1));
					boolean existCommenP = false;
					boolean existCommenC = false;
					boolean noCap = false;
					SQL = "SELECT count(*) FROM sport_programs_runningin s where s.SPID=" + Id + " ;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					rs.next();
					if (Integer.parseInt(rs.getString(1)) == 0) {
						if (existPlace == 1 && existCoache == 1) {
							SQL = "SELECT PlaceID_runningIn,StartDate,EndDate,TimeAndDays,PC FROM sport_programs_runningin;";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							while (rs.next()) {
								if (cap > Integer.parseInt(rs.getString(5))) {
									noCap = true;
									existCommenP = true;
									existCommenC = true;
									continue;
								}
								if (placeId != Integer.parseInt(rs.getString(1))) {
									existCommenP = false;
									continue;
								}
								dateStart2 = rs.getString(2).split("-");
								yearStart2 = Integer.parseInt(dateStart2[0]);
								manthStart2 = Integer.parseInt(dateStart2[1]);
								dayStart2 = Integer.parseInt(dateStart2[2]);
								dateEnd2 = rs.getString(3).split("-");
								yearEnd2 = Integer.parseInt(dateEnd2[0]);
								manthEnd2 = Integer.parseInt(dateEnd2[1]);
								dayEnd2 = Integer.parseInt(dateEnd2[2]);
								Days2 = rs.getString(4).split(",");
								Times2 = Days2[(Days2.length - 1)].split("-");
								TimeStart2 = Times2[0].split(":");
								TimeEnd2 = Times2[1].split(":");
								HoursStart2 = Integer.parseInt(TimeStart2[0]);
								HoursEnd2 = Integer.parseInt(TimeEnd2[0]);
								MinStart2 = Integer.parseInt(TimeStart2[1]);
								MinEnd2 = Integer.parseInt(TimeEnd2[1]);
								if ((yearEnd2 <= yearStart1 || yearEnd1 <= yearStart2)
										&& (yearStart1 != yearStart2 && yearEnd1 != yearEnd2)) {
									existCommenP = false;

								} else if ((yearStart2 < yearEnd1 && yearStart1 < yearStart2)
										|| (yearStart1 < yearEnd2 && yearStart2 < yearStart1)
										|| (yearStart1 == yearStart2 && yearEnd1 == yearEnd2)
										|| (yearStart2 < yearStart1 && yearStart2 < yearEnd1 && yearEnd2 > yearEnd1
												&& yearEnd2 > yearStart1)
										|| (yearStart1 < yearStart2 && yearStart1 < yearEnd2 && yearEnd1 > yearEnd2
												&& yearEnd1 > yearStart2)
										|| (yearStart1 == yearStart2 && yearEnd1 < yearEnd2)
										|| (yearStart1 == yearStart2 && yearEnd2 < yearEnd1)
										|| (yearStart1 < yearStart2 && yearEnd1 == yearEnd2)
										|| (yearStart2 < yearStart1 && yearEnd1 == yearEnd2)) {
									if ((manthEnd2 <= manthStart1 || manthEnd1 <= manthStart2)
											&& (manthStart1 != manthStart2 && manthEnd1 != manthEnd2)) {
										existCommenP = false;

									} else if ((manthStart2 < manthEnd1 && manthStart1 < manthStart2)
											|| (manthStart1 < manthEnd2 && manthStart2 < manthStart1)
											|| (manthStart1 == manthStart2 && manthEnd1 == manthEnd2)
											|| (manthStart2 < manthStart1 && manthStart2 < manthEnd1
													&& manthEnd2 > manthEnd1 && manthEnd2 > manthStart1)
											|| (manthStart1 < manthStart2 && manthStart1 < manthEnd2
													&& manthEnd1 > manthEnd2 && manthEnd1 > manthStart2)
											|| (manthStart1 == manthStart2 && manthEnd1 < manthEnd2)
											|| (manthStart1 == manthStart2 && manthEnd2 < manthEnd1)
											|| (manthStart1 < manthStart2 && manthEnd1 == manthEnd2)
											|| (manthStart2 < manthStart1 && manthEnd1 == manthEnd2)) {
										if ((dayEnd2 <= dayStart1 || dayEnd1 <= dayStart2)
												&& (dayStart1 != dayStart2 && dayEnd1 != dayEnd2)) {

											existCommenP = false;
										} else if ((dayStart2 < dayEnd1 && dayStart1 < dayStart2)
												|| (dayStart1 < dayEnd2 && dayStart2 < dayStart1)
												|| (dayStart1 == dayStart2 && dayEnd1 == dayEnd2)
												|| (dayStart2 < dayStart1 && dayStart2 < dayEnd1 && dayEnd2 > dayEnd1
														&& dayEnd2 > dayStart1)
												|| (dayStart1 < dayStart2 && dayStart1 < dayEnd2 && dayEnd1 > dayEnd2
														&& dayEnd1 > dayStart2)
												|| (dayStart1 == dayStart2 && dayEnd1 < dayEnd2)
												|| (dayStart1 == dayStart2 && dayEnd2 < dayEnd1)
												|| (dayStart1 < dayStart2 && dayEnd1 == dayEnd2)
												|| (dayStart2 < dayStart1 && dayEnd1 == dayEnd2)) {
											boolean flagexisCom = false;

											for (int y = 0; y < (Days1.length - 1); y++) {
												for (int i = 0; i < (Days2.length - 1); i++) {
													if (Days1[y].equalsIgnoreCase(Days2[i])) {
														flagexisCom = true;
														break;
													}
													if (flagexisCom == true) {
														break;
													}
												}
											}
											if (flagexisCom == true) {

												if ((HoursEnd2 <= HoursStart1 || HoursEnd1 <= HoursStart2)
														&& (HoursStart1 != HoursStart2 && HoursEnd1 != HoursEnd2)) {
													existCommenP = false;
												} else if ((HoursStart2 < HoursEnd1 && HoursStart1 < HoursStart2)
														|| (HoursStart1 < HoursEnd2 && HoursStart2 < HoursStart1)
														|| (HoursStart1 == HoursStart2 && HoursEnd1 == HoursEnd2)
														|| (HoursStart2 < HoursStart1 && HoursStart2 < HoursEnd1
																&& HoursEnd2 > HoursEnd1 && HoursEnd2 > HoursStart1)
														|| (HoursStart1 < HoursStart2 && HoursStart1 < HoursEnd2
																&& HoursEnd1 > HoursEnd2 && HoursEnd1 > HoursStart2)
														|| (HoursStart1 == HoursStart2 && HoursEnd1 < HoursEnd2)
														|| (HoursStart1 == HoursStart2 && HoursEnd2 < HoursEnd1)
														|| (HoursStart1 < HoursStart2 && HoursEnd1 == HoursEnd2)
														|| (HoursStart2 < HoursStart1 && HoursEnd1 == HoursEnd2)) {

													if ((MinEnd2 <= MinStart1 || MinEnd1 <= MinStart2)
															&& (MinStart1 != MinStart2 && MinEnd1 != MinEnd2)) {

														existCommenP = false;
													} else if ((MinStart2 < MinEnd1 && MinStart1 < MinStart2)
															|| (MinStart1 < MinEnd2 && MinStart2 < MinStart1)
															|| (MinStart1 == MinStart2 && MinEnd1 == MinEnd2)
															|| (MinStart2 < MinStart1 && MinStart2 < MinEnd1
																	&& MinEnd2 > MinEnd1 && MinEnd2 > MinStart1)
															|| (MinStart1 < MinStart2 && MinStart1 < MinEnd2
																	&& MinEnd1 > MinEnd2 && MinEnd1 > MinStart2)
															|| (MinStart1 == MinStart2 && MinEnd1 < MinEnd2)
															|| (MinStart1 == MinStart2 && MinEnd2 < MinEnd1)
															|| (MinStart1 < MinStart2 && MinEnd1 == MinEnd2)
															|| (MinStart2 < MinStart1 && MinEnd1 == MinEnd2)) {

														existCommenP = true;
														break;
													}
												}
											} else {
												existCommenP = false;
											}
										}
									}
								}
								SQL2 = "SELECT count(*) FROM train where SSN_c=" + coache + " ;";
								stmt2 = con.prepareStatement(SQL2);
								rs2 = stmt2.executeQuery();
								rs2.next();
								if (Integer.parseInt(rs2.getString(1)) > 0) {
									SQL2 = "select s.StartDate,s.EndDate,s.TimeAndDays from sport_programs_runningin s,train t where t.SPID_t=s.SPID and t.SSN_c="
											+ coache + " ;";
									stmt2 = con.prepareStatement(SQL2);
									rs2 = stmt2.executeQuery();
									rs2.next();
									dateStart2 = rs2.getString(1).split("-");
									yearStart2 = Integer.parseInt(dateStart2[0]);
									manthStart2 = Integer.parseInt(dateStart2[1]);
									dayStart2 = Integer.parseInt(dateStart2[2]);
									dateEnd2 = rs2.getString(2).split("-");
									yearEnd2 = Integer.parseInt(dateEnd2[0]);
									manthEnd2 = Integer.parseInt(dateEnd2[1]);
									dayEnd2 = Integer.parseInt(dateEnd2[2]);
									Days2 = rs2.getString(3).split(",");
									Times2 = Days2[(Days2.length - 1)].split("-");
									TimeStart2 = Times2[0].split(":");
									TimeEnd2 = Times2[1].split(":");
									HoursStart2 = Integer.parseInt(TimeStart2[0]);
									HoursEnd2 = Integer.parseInt(TimeEnd2[0]);
									MinStart2 = Integer.parseInt(TimeStart2[1]);
									MinEnd2 = Integer.parseInt(TimeEnd2[1]);
									if ((yearEnd2 <= yearStart1 || yearEnd1 <= yearStart2)
											&& (yearStart1 != yearStart2 && yearEnd1 != yearEnd2)) {
										existCommenC = false;

									} else if ((yearStart2 < yearEnd1 && yearStart1 < yearStart2)
											|| (yearStart1 < yearEnd2 && yearStart2 < yearStart1)
											|| (yearStart1 == yearStart2 && yearEnd1 == yearEnd2)
											|| (yearStart2 < yearStart1 && yearStart2 < yearEnd1 && yearEnd2 > yearEnd1
													&& yearEnd2 > yearStart1)
											|| (yearStart1 < yearStart2 && yearStart1 < yearEnd2 && yearEnd1 > yearEnd2
													&& yearEnd1 > yearStart2)
											|| (yearStart1 == yearStart2 && yearEnd1 < yearEnd2)
											|| (yearStart1 == yearStart2 && yearEnd2 < yearEnd1)
											|| (yearStart1 < yearStart2 && yearEnd1 == yearEnd2)
											|| (yearStart2 < yearStart1 && yearEnd1 == yearEnd2)) {
										if ((manthEnd2 <= manthStart1 || manthEnd1 <= manthStart2)
												&& (manthStart1 != manthStart2 && manthEnd1 != manthEnd2)) {
											existCommenC = false;

										} else if ((manthStart2 < manthEnd1 && manthStart1 < manthStart2)
												|| (manthStart1 < manthEnd2 && manthStart2 < manthStart1)
												|| (manthStart1 == manthStart2 && manthEnd1 == manthEnd2)
												|| (manthStart2 < manthStart1 && manthStart2 < manthEnd1
														&& manthEnd2 > manthEnd1 && manthEnd2 > manthStart1)
												|| (manthStart1 < manthStart2 && manthStart1 < manthEnd2
														&& manthEnd1 > manthEnd2 && manthEnd1 > manthStart2)
												|| (manthStart1 == manthStart2 && manthEnd1 < manthEnd2)
												|| (manthStart1 == manthStart2 && manthEnd2 < manthEnd1)
												|| (manthStart1 < manthStart2 && manthEnd1 == manthEnd2)
												|| (manthStart2 < manthStart1 && manthEnd1 == manthEnd2)) {
											if ((dayEnd2 <= dayStart1 || dayEnd1 <= dayStart2)
													&& (dayStart1 != dayStart2 && dayEnd1 != dayEnd2)) {

												existCommenC = false;
											} else if ((dayStart2 < dayEnd1 && dayStart1 < dayStart2)
													|| (dayStart1 < dayEnd2 && dayStart2 < dayStart1)
													|| (dayStart1 == dayStart2 && dayEnd1 == dayEnd2)
													|| (dayStart2 < dayStart1 && dayStart2 < dayEnd1
															&& dayEnd2 > dayEnd1 && dayEnd2 > dayStart1)
													|| (dayStart1 < dayStart2 && dayStart1 < dayEnd2
															&& dayEnd1 > dayEnd2 && dayEnd1 > dayStart2)
													|| (dayStart1 == dayStart2 && dayEnd1 < dayEnd2)
													|| (dayStart1 == dayStart2 && dayEnd2 < dayEnd1)
													|| (dayStart1 < dayStart2 && dayEnd1 == dayEnd2)
													|| (dayStart2 < dayStart1 && dayEnd1 == dayEnd2)) {
												boolean flagexisCom = false;

												for (int y = 0; y < (Days1.length - 1); y++) {
													for (int i = 0; i < (Days2.length - 1); i++) {
														if (Days1[y].equalsIgnoreCase(Days2[i])) {
															flagexisCom = true;
															break;
														}
														if (flagexisCom == true) {
															break;
														}
													}
												}
												if (flagexisCom == true) {

													if ((HoursEnd2 <= HoursStart1 || HoursEnd1 <= HoursStart2)
															&& (HoursStart1 != HoursStart2 && HoursEnd1 != HoursEnd2)) {
														existCommenC = false;
													} else if ((HoursStart2 < HoursEnd1 && HoursStart1 < HoursStart2)
															|| (HoursStart1 < HoursEnd2 && HoursStart2 < HoursStart1)
															|| (HoursStart1 == HoursStart2 && HoursEnd1 == HoursEnd2)
															|| (HoursStart2 < HoursStart1 && HoursStart2 < HoursEnd1
																	&& HoursEnd2 > HoursEnd1 && HoursEnd2 > HoursStart1)
															|| (HoursStart1 < HoursStart2 && HoursStart1 < HoursEnd2
																	&& HoursEnd1 > HoursEnd2 && HoursEnd1 > HoursStart2)
															|| (HoursStart1 == HoursStart2 && HoursEnd1 < HoursEnd2)
															|| (HoursStart1 == HoursStart2 && HoursEnd2 < HoursEnd1)
															|| (HoursStart1 < HoursStart2 && HoursEnd1 == HoursEnd2)
															|| (HoursStart2 < HoursStart1 && HoursEnd1 == HoursEnd2)) {

														if ((MinEnd2 <= MinStart1 || MinEnd1 <= MinStart2)
																&& (MinStart1 != MinStart2 && MinEnd1 != MinEnd2)) {

															existCommenC = false;
														} else if ((MinStart2 < MinEnd1 && MinStart1 < MinStart2)
																|| (MinStart1 < MinEnd2 && MinStart2 < MinStart1)
																|| (MinStart1 == MinStart2 && MinEnd1 == MinEnd2)
																|| (MinStart2 < MinStart1 && MinStart2 < MinEnd1
																		&& MinEnd2 > MinEnd1 && MinEnd2 > MinStart1)
																|| (MinStart1 < MinStart2 && MinStart1 < MinEnd2
																		&& MinEnd1 > MinEnd2 && MinEnd1 > MinStart2)
																|| (MinStart1 == MinStart2 && MinEnd1 < MinEnd2)
																|| (MinStart1 == MinStart2 && MinEnd2 < MinEnd1)
																|| (MinStart1 < MinStart2 && MinEnd1 == MinEnd2)
																|| (MinStart2 < MinStart1 && MinEnd1 == MinEnd2)) {

															existCommenC = true;
															break;
														}
													}
												} else {
													existCommenC = false;
												}
											}
										}
									}
								} else
									existCommenC = false;
							}
						} else {
							alert.setContentText("Place Or Coache not found");
							alert.showAndWait();
							existCommenP = true;
							existCommenC = true;
						}
					} else {
						alert.setContentText("The sport program ID Excist try another ID");
						notEx=true;
						alert.showAndWait();
						existCommenP = true;
						existCommenC = true;
					}
					
					if (noCap == true) {
						existCommenP = true;
						existCommenC = true;
						alert.setContentText("No Capasity");
						alert.showAndWait();
					}
					if (existCommenP == false && existCommenC == false) {
						SQL = "INSERT INTO sport_programs_runningin (SPID, PlaceID_runningIn, Name, StartDate, EndDate, Price, TimeAndDays, PC) VALUES ("
								+ Id + ", " + placeId + ", '" + Name + "', '" + StartDate + "', '" + EndDate + "', "
								+ price + ", '" + TimeAndDays + "', " + cap + ");";
						stmt = con.prepareStatement(SQL);
						stmt.executeUpdate();
						SQL = "INSERT INTO train (SPID_t,SSN_c) VALUES (" + Id + ", " + coache + ");";
						stmt = con.prepareStatement(SQL);
						stmt.executeUpdate();
						alert.setContentText("The Insertion sucsses");
						alert.showAndWait();
					} else {
						if(notEx!=true) {
						alert.setContentText("There is Common Date");
						alert.showAndWait();
						}
					}

					rs.close();
					stmt.close();
					if (rs2 != null)
						rs2.close();
					if (stmt2 != null)
						stmt2.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			} else {
				alert.setContentText("Please enter all valid Information");
				alert.showAndWait();
			}

		});

		// Setting the image for the back Button

		// Back Button
		Button bm2 = new Button("Back",backi);
		bm2.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bm2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bm2.setEffect(shadow);
		});
		bm2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bm2.setEffect(null);
		});
		// Action for Back Button
		bm2.setOnAction(e -> {
			try {
				programSport(SpAd, SpAdScene);
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		});

		bm1.setMinSize(200, 30);
		bm2.setMinSize(200, 30);

		tm1.setMinSize(400, 30);
		tm2.setMinSize(400, 30);
		tm3.setMinSize(400, 30);
		tm4.setMinSize(400, 30);
		tm5.setMinSize(400, 30);
		tm6.setMinSize(400, 30);
		tm7.setMinSize(400, 30);
		tm8.setMinSize(400, 30);
		tm9.setMinSize(400, 30);

		// Screen Format
		hm1.getChildren().addAll(lm1, tm1);
		hm2.getChildren().addAll(lm2, tm2);
		hm3.getChildren().addAll(lm3, tm3);
		hm4.getChildren().addAll(lm4, tm4);
		hm5.getChildren().addAll(lm5, tm5);
		hm6.getChildren().addAll(lm6, tm6);
		hm7.getChildren().addAll(lm7, tm7);
		hm8.getChildren().addAll(lm8, tm8);
		hm9.getChildren().addAll(lm9, tm9);
		hm10.getChildren().addAll(bm1, bm2);

		spA.getChildren().addAll(hm1, hm2, hm3, hm4, hm5, hm6, hm7, hm8, hm9, hm10);
		// View content
		SpAdScene.setRoot(spA);
		SpAd.setTitle("Add new Member");
		SpAd.show();
		alert.setContentText(
				"Note: Please enter the information in order and complete, otherwise the Add button will not work");
		alert.showAndWait();

	}
	// This function is delete a SportProg by member ID
	public static void DeletSportProg(Stage SpRe, Scene SpReScene) throws SQLException, ClassNotFoundException {// done
		VBox SpR = new VBox(40);
		SpR.setAlignment(Pos.CENTER);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);
		
		Image image2 = new Image("remove.png");
		ImageView remove = new ImageView(image2);
		remove.setFitHeight(30);
		remove.setFitWidth(30);
		Image image3 = new Image("search.png");
		ImageView search = new ImageView(image3);
		search.setFitHeight(30);
		search.setFitWidth(30);
		Image image4 = new Image("back.png");
		ImageView backi = new ImageView(image4);
		backi.setFitHeight(30);
		backi.setFitWidth(30);

		HBox hr1 = new HBox(40);
		HBox hr2 = new HBox(40);
		HBox hr3 = new HBox(40);
		HBox hr4 = new HBox(40);
		HBox hr5 = new HBox(40);
		HBox hr6 = new HBox(40);
		HBox hr7 = new HBox(40);
		HBox hr8 = new HBox(40);
		HBox hr9 = new HBox(40);
		HBox hr10 = new HBox(40);
		hr1.setAlignment(Pos.CENTER);
		hr2.setAlignment(Pos.CENTER);
		hr3.setAlignment(Pos.CENTER);
		hr4.setAlignment(Pos.CENTER);
		hr5.setAlignment(Pos.CENTER);
		hr6.setAlignment(Pos.CENTER);
		hr7.setAlignment(Pos.CENTER);
		hr8.setAlignment(Pos.CENTER);
		hr9.setAlignment(Pos.CENTER);
		hr10.setAlignment(Pos.CENTER);
		SpR.setPadding(new Insets(30));

		Label lr1 = new Label("Id Of Sport Program:");
		Label lr2 = new Label("Name Of Sport Program:");
		Label lr3 = new Label("Capacity Of Sport Program:");
		Label lr4 = new Label("Place ID Of Sport Program:");
		Label lr5 = new Label("Start Date Of Sport Program:");
		Label lr6 = new Label("End Date Of Sport Program");
		Label lr7 = new Label("Price Of Sport Program:");
		Label lr8 = new Label("Time And Days Of Sport Program:");
		Label lr9 = new Label("Id and Name Of coache:");
		// Customer TextField
		TextField tr1 = new TextField();
		TextArea tr2 = new TextArea();
		TextArea tr3 = new TextArea();
		TextArea tr4 = new TextArea();
		TextArea tr5 = new TextArea();
		TextArea tr6 = new TextArea();
		TextArea tr7 = new TextArea();
		TextArea tr8 = new TextArea();
		TextArea tr9 = new TextArea();

		tr2.setMaxSize(65, 25);
		tr3.setMaxSize(65, 25);
		tr4.setMaxSize(65, 25);
		tr5.setMaxSize(65, 25);
		tr6.setMaxSize(65, 25);
		tr7.setMaxSize(65, 25);
		tr8.setMaxSize(65, 25);
		tr9.setMaxSize(65, 25);
		// Make TextArea ineffective
		tr2.setEditable(false);
		tr3.setEditable(false);
		tr4.setEditable(false);
		tr5.setEditable(false);
		tr6.setEditable(false);
		tr7.setEditable(false);
		tr8.setEditable(false);
		tr9.setEditable(false);
		// Remove Button
		Button br1 = new Button("Remove",remove);
		DropShadow shadow = new DropShadow();
		br1.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		br1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			br1.setEffect(shadow);
		});
		br1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			br1.setEffect(null);
		});
		br1.setOnAction(e -> {
			if (tr1.getText() != "") {
				wrongDate = false;
				try {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;
					int Id = 0;
					try {

						Id = Integer.parseInt(tr1.getText());
					} catch (Exception e1) {
						wrongDate = true;
						alert.setContentText("Please Enter Vaild ID");
						alert.showAndWait();
					}
					SQL = "SELECT count(*) FROM sport_programs_runningin where SPID=" + Id + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					rs.next();
					int isExist = Integer.parseInt(rs.getString(1));
					SQL = "select count(*) from registeration r,sport_programs_runningin s where  s.SPID=r.SPID_SP and s.SPID="
							+ Id + " ;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					rs.next();
					int regExist = Integer.parseInt(rs.getString(1));

					if (isExist != 0 && regExist == 0) {
						SQL = "DELETE FROM sport_programs_runningin WHERE SPID=" + Id + ";";
						stmt = con.prepareStatement(SQL);
						stmt.executeUpdate();
						tr1.setText("");
						tr2.setText("");
						tr3.setText("");
						tr4.setText("");
						tr5.setText("");
						tr6.setText("");
						tr7.setText("");
						tr8.setText("");
						tr9.setText("");
						alert.setContentText("The Deletion succsesed");
						alert.showAndWait();
					} else if (isExist == 0) {
						if (wrongDate == false) {
							alert.setContentText("This sport program does not exict");
							alert.showAndWait();
						}
					} else {
						alert.setContentText(
								"there is members has reg on this sport programe you can't delete the program");
						alert.showAndWait();
					}

					rs.close();
					stmt.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}
			} else {
				alert.setContentText("Please Enter ID");
				alert.showAndWait();
			}
		});


		// Back Button
		Button br2 = new Button("Back",backi);
		br2.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		br2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			br2.setEffect(shadow);
		});
		br2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			br2.setEffect(null);
		});
		// Action for Back Button
		br2.setOnAction(e -> {
			try {
				programSport(SpRe, SpReScene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});


		// Find Button
		Button br3 = new Button("Find",search);
		br3.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		br3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			br3.setEffect(shadow);
		});
		br3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			br3.setEffect(null);
		});

		br3.setOnAction(e -> {
			wrongDate = false;
			if (tr1.getText() != "") {
				try {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					String SQL2;
					int Id = 0;

					try {
						Id = Integer.parseInt(tr1.getText());
					} catch (Exception e1) {
						wrongDate = true;
						alert.setContentText("Please enter valid ID");
						alert.showAndWait();
					}
					SQL = "SELECT count(*) FROM sport_programs_runningin where SPID=" + Id + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					rs.next();
					String IdSP;
					if (Integer.parseInt(rs.getString(1)) > 0) {
						SQL = "SELECT * FROM sport_programs_runningin where SPID=" + Id + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						tr1.setText(rs.getString(1));
						IdSP = rs.getString(1);

						tr2.setText(rs.getString(3));
						tr3.setText(rs.getString(8));
						tr4.setText(rs.getString(2));
						tr5.setText(rs.getString(4));
						tr6.setText(rs.getString(5));
						tr7.setText(rs.getString(6));
						tr8.setText(rs.getString(7));
						SQL2 = "select c.SSN,c.Name from sport_programs_runningin s,train t,coaches c where t.SPID_t=s.SPID and t.SSN_c=c.SSN and s.SPID="
								+ IdSP + ";";
						stmt2 = con.prepareStatement(SQL2);
						rs2 = stmt2.executeQuery();
						rs2.next();
						tr9.setText("ID: " + rs2.getString(1) + "  Name: " + rs2.getString(2));

					} else {
						if (wrongDate == false) {
							alert.setContentText("there is no sport programs with this ID");
							alert.showAndWait();
						}
					}
				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}
			} else {
				alert.setContentText("Please Enter ID");
				alert.showAndWait();
			}
		});
		br1.setMinSize(200, 30);
		br2.setMinSize(200, 30);
		br3.setMinSize(200, 30);

		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);

		lr1.setFont(myfont2);
		lr2.setFont(myfont2);
		lr3.setFont(myfont2);
		lr4.setFont(myfont2);
		lr5.setFont(myfont2);
		lr6.setFont(myfont2);
		lr7.setFont(myfont2);
		lr8.setFont(myfont2);
		lr9.setFont(myfont2);

		lr1.setMinSize(320, 30);
		lr2.setMinSize(320, 30);
		lr3.setMinSize(320, 30);
		lr4.setMinSize(320, 30);
		lr5.setMinSize(320, 30);
		lr6.setMinSize(320, 30);
		lr7.setMinSize(320, 30);
		lr8.setMinSize(320, 30);
		lr9.setMinSize(320, 30);

		tr1.setMinSize(300, 30);
		tr2.setMinSize(300, 30);
		tr3.setMinSize(300, 30);
		tr4.setMinSize(300, 30);
		tr5.setMinSize(300, 30);
		tr6.setMinSize(300, 30);
		tr7.setMinSize(300, 30);
		tr8.setMinSize(300, 30);
		tr9.setMinSize(300, 30);
		// Screen Format
		hr1.getChildren().addAll(lr1, tr1);
		hr2.getChildren().addAll(lr2, tr2);
		hr3.getChildren().addAll(lr3, tr3);
		hr4.getChildren().addAll(lr4, tr4);
		hr5.getChildren().addAll(lr5, tr5);
		hr6.getChildren().addAll(lr6, tr6);
		hr7.getChildren().addAll(lr7, tr7);
		hr8.getChildren().addAll(lr8, tr8);
		hr9.getChildren().addAll(lr9, tr9);
		hr10.getChildren().addAll(br3, br1, br2);

		SpR.getChildren().addAll(hr1, hr2, hr3, hr4, hr5, hr6, hr7, hr8, hr9, hr10);
		// View content
		SpReScene.setRoot(SpR);
		SpRe.setTitle("Delete Customer");
		SpRe.show();
		alert.setContentText(
				"Note: For Search Enter the id and press Find button\nFor Remove Enter the id and press Remove button");
		alert.showAndWait();

	}
	// This function for find a Sport Program by name of Sport Program, or by Start and end Date.
	public static void findSportProg(Stage SpSe, Scene SpSeScene) throws SQLException, ClassNotFoundException {// done
		VBox SpS = new VBox(40);
		SpS.setAlignment(Pos.CENTER);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);
		
		Image image3 = new Image("search.png");
		ImageView search = new ImageView(image3);
		search.setFitHeight(30);
		search.setFitWidth(30);
		Image image4 = new Image("back.png");
		ImageView backi = new ImageView(image4);
		backi.setFitHeight(30);
		backi.setFitWidth(30);

		HBox hs1 = new HBox(40);
		HBox hs2 = new HBox(40);

		HBox hs10 = new HBox(40);
		HBox hs11 = new HBox(10);
		VBox Vs1 = new VBox(40);
		VBox Vs2 = new VBox(40);
		VBox Vs3 = new VBox(40);
		VBox Vs4 = new VBox(40);

		hs1.setAlignment(Pos.CENTER);
		hs2.setAlignment(Pos.CENTER);
		hs10.setAlignment(Pos.CENTER);
		hs11.setAlignment(Pos.CENTER);

		SpS.setPadding(new Insets(50));
		Vs1.setPadding(new Insets(10));
		Vs2.setPadding(new Insets(10));
		Vs3.setPadding(new Insets(10));
		Vs4.setPadding(new Insets(10));

		Label ls1 = new Label("Name Of Sport Program:");
		Label ls2 = new Label("Start Date Of Sport Program(e.g.: 2020-5-20 ):");
		Label ls3 = new Label("End Date Of Sport Program(e.g.: 2021-5-20 ):");
		// Customer Labels
		TextField ts1 = new TextField();
		TextField ts2 = new TextField();
		TextField ts3 = new TextField();
		TextArea tAs2 = new TextArea();

		// Make TextArea ineffective
		tAs2.setEditable(false);

		// Search Button
		Button bs1 = new Button("Search",search);
		bs1.setContentDisplay(ContentDisplay.LEFT);
		DropShadow shadow = new DropShadow();
		// Effect for Button
		bs1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bs1.setEffect(shadow);
		});
		bs1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bs1.setEffect(null);
		});

		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);

		bs1.setOnAction(e -> {
			wrongDate = false;
			String info = "";
			if (ts1.getText() != "" || (ts2.getText() != "" && ts3.getText() != "")) {
				try {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;
					String IdSP;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					String SQL2;
					String Name = ts1.getText();

					String StartDate = ts2.getText();
					boolean used = false;
					String EndDate = ts3.getText();
					if (ts1.getText() != "") {
						used = true;
						SQL = "SELECT count(*) FROM sport_programs_runningin where Name='" + Name + "';";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						info = "By Name: " + Name + "\nn";
						if (Integer.parseInt(rs.getString(1)) > 0) {
							SQL = "SELECT * FROM sport_programs_runningin where Name='" + Name + "';";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();

							while (rs.next()) {

								info = info + "the ID of the sport program:" + rs.getString(1) + "\n";
								info = info + "the Name of the sport program:" + rs.getString(3) + "\n";
								info = info + "the Start Date of the sport program:" + rs.getString(4) + "\n";
								info = info + "the End Date of the sport program:" + rs.getString(5) + "\n";
								info = info + "the Time and Days of the sport program:" + rs.getString(7) + "\n";
								info = info + "the capacity of the sport program:" + rs.getString(8) + "\n";
								info = info + "the price of the sport program:" + rs.getString(6) + "\n";
								info = info + "the Place ID of the sport program:" + rs.getString(2) + "\n";
								IdSP = rs.getString(1);

								SQL2 = "select count(*) from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and s.Name='"
										+ Name + "' and s.SPID=" + IdSP + ";";
								stmt2 = con.prepareStatement(SQL2);
								rs2 = stmt2.executeQuery();
								rs2.next();
								if (Integer.parseInt(rs2.getString(1)) > 0) {
									SQL2 = "select m.SSN,m.Name from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and s.Name='"
											+ Name + "'and s.SPID=" + IdSP + ";";
									stmt2 = con.prepareStatement(SQL2);
									rs2 = stmt2.executeQuery();
									info = info + "the members in the sport program:\n";
									while (rs2.next()) {
										info = info + "ID: " + rs2.getString(1) + "  Name: " + rs2.getString(2) + "\n";
									}

								} else {
									info = info + "the members in the sport program: no members" + "\n";

								}
								info = info + "the coache of this program: ";
								SQL2 = "select c.SSN,c.Name from sport_programs_runningin s,train t,coaches c where t.SPID_t=s.SPID and t.SSN_c=c.SSN and s.Name='"
										+ Name + "' and s.SPID=" + IdSP + ";";
								stmt2 = con.prepareStatement(SQL2);
								rs2 = stmt2.executeQuery();
								rs2.next();
								info = info + "ID: " + rs2.getString(1) + "  Name: " + rs2.getString(2) + "\n";
								info = info + "\n\n";
							}
						} else
							info = info + "there is no sport programs with this name: " + Name + "\n\n";
					}

					if (ts2.getText() != "" && ts3.getText() != "") {
						used = true;
						info = info + "Start Date: " + StartDate + " End Date: " + EndDate + "\nn";
						SQL = "SELECT count(*) FROM sport_programs_runningin where StartDate='" + StartDate
								+ "'and EndDate='" + EndDate + "';";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						if (Integer.parseInt(rs.getString(1)) > 0) {
							SQL = "SELECT * FROM sport_programs_runningin where StartDate='" + StartDate
									+ "'and EndDate='" + EndDate + "';";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							while (rs.next()) {
								info = info + "the ID of the sport program:" + rs.getString(1) + "\n";
								info = info + "the Name of the sport program:" + rs.getString(3) + "\n";
								info = info + "the Start Date of the sport program:" + rs.getString(4) + "\n";
								info = info + "the End Date of the sport program:" + rs.getString(5) + "\n";
								info = info + "the Time and Days of the sport program:" + rs.getString(7) + "\n";
								info = info + "the capacity of the sport program:" + rs.getString(8) + "\n";
								info = info + "the price of the sport program:" + rs.getString(6) + "\n";
								info = info + "the Place ID of the sport program:" + rs.getString(2) + "\n";

								IdSP = rs.getString(1);

								SQL2 = "select count(*) from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and s.StartDate='"
										+ StartDate + "' and s.EndDate='" + EndDate + "' and s.SPID=" + IdSP + " ;";
								stmt2 = con.prepareStatement(SQL2);
								rs2 = stmt2.executeQuery();
								rs2.next();
								if (Integer.parseInt(rs2.getString(1)) > 0) {
									SQL2 = "select m.SSN,m.Name from registeration r,sport_programs_runningin s , members m where r.SSN_me=m.SSN and s.SPID=r.SPID_SP and s.StartDate='"
											+ StartDate + "' and s.EndDate='" + EndDate + "' and s.SPID=" + IdSP + " ;";
									stmt2 = con.prepareStatement(SQL2);
									rs2 = stmt2.executeQuery();
									info = info + "the members in the sport program: \n";

									while (rs2.next()) {
										info = info + "ID: " + rs2.getString(1) + "  Name: " + rs2.getString(2) + "\n";
									}

								} else {
									info = info + "the members in the sport program: no members\n";

								}
								info = info + "the coache of this program: ";

								SQL2 = "select c.SSN,c.Name from sport_programs_runningin s,train t,coaches c where t.SPID_t=s.SPID and t.SSN_c=c.SSN and s.StartDate='"
										+ StartDate + "' and s.EndDate='" + EndDate + "' and s.SPID=" + IdSP + ";";
								stmt2 = con.prepareStatement(SQL2);
								rs2 = stmt2.executeQuery();
								rs2.next();
								info = info + "ID: " + rs2.getString(1) + "  Name: " + rs2.getString(2) + "\n\n";

							}
						} else
							info = info + "there is no sport programs with this Start Date:" + StartDate
									+ " and End Date:" + EndDate + "\n\n";

					} else if (ts2.getText() == "" || ts3.getText() != "") {
						alert.setContentText("Please Enter Start Date with End Date");
						alert.showAndWait();
					}
					tAs2.setText(info);
					if (used == true) {
						rs.close();
						stmt.close();
						rs2.close();
						stmt2.close();
					}
					con.close();

				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}
			} else {
				alert.setContentText("Please Enter Name Or Start Date with End Date");
				alert.showAndWait();
			}
		});


		// Back Button
		Button bs2 = new Button("Back",backi);
		bs2.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bs2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bs2.setEffect(shadow);
		});
		bs2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bs2.setEffect(null);
		});
		// Action for Back Button
		bs2.setOnAction(e -> {
			try {
				programSport(SpSe, SpSeScene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		bs1.setMinSize(200, 30);
		bs2.setMinSize(200, 30);

		ls1.setFont(myfont2);
		ls2.setFont(myfont2);
		ls3.setFont(myfont2);

		ls1.setMinSize(200, 30);
		ls2.setMinSize(200, 30);
		ls3.setMinSize(200, 30);

		ts1.setMinSize(200, 30);
		ts2.setMinSize(200, 30);
		ts3.setMinSize(200, 30);
		tAs2.setMinSize(200, 400);

		// Screen Format

		Vs2.getChildren().addAll(ls1, ts1);
		Vs3.getChildren().addAll(ls2, ts2);
		Vs4.getChildren().addAll(ls3, ts3);
		hs1.getChildren().addAll(Vs2, Vs3, Vs4);
		hs10.getChildren().addAll(bs1, bs2);

		Vs1.getChildren().addAll(hs1, tAs2, hs10);

		SpS.getChildren().addAll(Vs1);
		SpSeScene.setRoot(SpS);
		SpSe.setTitle("Search a Customer by id");
		// View content
		SpSe.show();
		alert.setContentText("Note:For Search Enter the id and press Find button");
		alert.showAndWait();

	}
    // This function for update Sport Program information by select SPID
	public static void UpdateSportProg(Stage SpUp, Scene SpUpScene) throws SQLException, ClassNotFoundException {
		VBox spU = new VBox(40);
		spU.setAlignment(Pos.CENTER);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("alerts");
		alert.setHeaderText(null);
		
		Image image2 = new Image("update.png");
		ImageView update = new ImageView(image2);
		update.setFitHeight(30);
		update.setFitWidth(30);
		Image image3 = new Image("search.png");
		ImageView search = new ImageView(image3);
		search.setFitHeight(30);
		search.setFitWidth(30);
		Image image4 = new Image("back.png");
		ImageView backi = new ImageView(image4);
		backi.setFitHeight(30);
		backi.setFitWidth(30);

		HBox hu1 = new HBox(40);
		HBox hu2 = new HBox(40);
		HBox hu3 = new HBox(40);
		HBox hu4 = new HBox(40);
		HBox hu5 = new HBox(40);
		HBox hu6 = new HBox(40);
		HBox hu7 = new HBox(40);
		HBox hu8 = new HBox(40);
		HBox hu9 = new HBox(40);
		HBox hu10 = new HBox(40);

		hu1.setAlignment(Pos.CENTER);
		hu2.setAlignment(Pos.CENTER);
		hu3.setAlignment(Pos.CENTER);
		hu4.setAlignment(Pos.CENTER);
		hu5.setAlignment(Pos.CENTER);
		hu6.setAlignment(Pos.CENTER);
		hu7.setAlignment(Pos.CENTER);
		hu8.setAlignment(Pos.CENTER);
		hu9.setAlignment(Pos.CENTER);
		hu10.setAlignment(Pos.CENTER);
		spU.setPadding(new Insets(30));
		// Member Update Labels
		Label lu1 = new Label("Id Of Sport Program:");
		Label lu2 = new Label("Name Of Sport Program:");
		Label lu3 = new Label("Capacity Of Sport Program:");
		Label lu4 = new Label("Place ID Of Sport Program:");
		Label lu5 = new Label("Start Date Of Sport Program(e.g.: 2020-5-20 ):");
		Label lu6 = new Label("End Date Of Sport Program(e.g.: 2020-10-20 )");
		Label lu7 = new Label("Price Of Sport Program:");
		Label lu8 = new Label("Time And Days Of Sport Program(e.g.: T,R,1:30-2:20 ):");
		Label lu9 = new Label("Id Of coache:");
		// Member Update TextField and ComboBox
		TextField tu1 = new TextField();
		TextField tu2 = new TextField();
		TextField tu3 = new TextField();
		TextField tu4 = new TextField();
		TextField tu5 = new TextField();
		TextField tu6 = new TextField();
		TextField tu7 = new TextField();
		TextField tu8 = new TextField();
		TextField tu9 = new TextField();


		// Back Button
		Button bu2 = new Button("Back",backi);
		DropShadow shadow = new DropShadow();
		bu2.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bu2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bu2.setEffect(shadow);
		});
		bu2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bu2.setEffect(null);
		});
		// Action for Back Button
		bu2.setOnAction(e -> {
			try {
				programSport(SpUp, SpUpScene);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});


		Button bu3 = new Button("Find",search);
		bu3.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bu3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bu3.setEffect(shadow);
		});
		bu3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bu3.setEffect(null);
		});
		Font myfont2 = Font.font("Time new Roman", FontWeight.BOLD, FontPosture.REGULAR, 20);
		bu3.setOnAction(e -> {
			if (tu1.getText() != "") {
				try {
					wrongDate = false;
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt = null;
					ResultSet rs = null;
					String SQL;
					PreparedStatement stmt2 = null;
					ResultSet rs2 = null;
					String SQL2;
					int Id = 0;

					try {
						Id = Integer.parseInt(tu1.getText());
						ID = Id;
					} catch (Exception e1) {
						wrongDate = true;
						alert.setContentText("Please enter valid ID");
						alert.showAndWait();
					}
					SQL = "SELECT count(*) FROM sport_programs_runningin where SPID=" + Id + ";";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();
					rs.next();
					String IdSP;
					if (Integer.parseInt(rs.getString(1)) > 0) {
						SQL = "SELECT * FROM sport_programs_runningin where SPID=" + Id + ";";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						rs.next();
						tu1.setText(rs.getString(1));
						IdSP = rs.getString(1);

						tu2.setText(rs.getString(3));
						tu3.setText(rs.getString(8));
						tu4.setText(rs.getString(2));
						tu5.setText(rs.getString(4));
						tu6.setText(rs.getString(5));
						tu7.setText(rs.getString(6));
						tu8.setText(rs.getString(7));
						SQL2 = "select c.SSN,c.Name from sport_programs_runningin s,train t,coaches c where t.SPID_t=s.SPID and t.SSN_c=c.SSN and s.SPID="
								+ IdSP + ";";
						stmt2 = con.prepareStatement(SQL2);
						rs2 = stmt2.executeQuery();
						rs2.next();
						tu9.setText(rs2.getString(1));

					} else {
						if (wrongDate == false) {
							alert.setContentText("there is no sport programs with this ID");
							alert.showAndWait();
						}
					}
				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}

			} else {
				alert.setContentText("Please Enter the ID");
				alert.showAndWait();
			}
		});

	
		
		// Update Button
		Button bu1 = new Button("Update",update);
		bu1.setContentDisplay(ContentDisplay.LEFT);
		// Effect for Button
		bu1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			bu1.setEffect(shadow);
		});
		bu1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			bu1.setEffect(null);
		});

		bu1.setOnAction(e -> {
			if (tu1.getText() != "" && tu2.getText() != "" && tu3.getText() != "" && tu4.getText() != ""
					&& tu5.getText() != "" && tu6.getText() != "" && tu7.getText() != "" && tu8.getText() != ""
					&& tu9.getText() != "") {
				try {
					DBConn a = new DBConn(URL, port, dbName, dbUsername, dbPasswoerd);
					con = a.connectDB();
					System.out.println("Connection established");
					PreparedStatement stmt;
					ResultSet rs;
					String SQL;
					PreparedStatement stmt2;
					ResultSet rs2;
					String SQL2;

					int Id = 0;
					try {
						Id = Integer.parseInt(tu1.getText());
					} catch (Exception e1) {
						alert.setContentText("Please Enter Valid ID");
						alert.showAndWait();
					}
					SQL = "SELECT * FROM sport_programs_runningin;";
					stmt = con.prepareStatement(SQL);
					rs = stmt.executeQuery();

					boolean cantUp = false;
					{
						SQL = "select * from sport_programs_runningin;";
						stmt = con.prepareStatement(SQL);
						rs = stmt.executeQuery();
						while (rs.next()) {
							if (Integer.parseInt(rs.getString(1)) == Id && Id != ID) {
								cantUp = true;
								break;
							}
						}

						if (cantUp == false) {
							String name = tu2.getText();

							String StartDate = tu5.getText();

							String EndDate = tu6.getText();

							String TimeAndDays = tu8.getText();

							int cap = Integer.parseInt(tu3.getText());

							double price = Double.parseDouble(tu7.getText());

							int placeId = Integer.parseInt(tu4.getText());

							int coache = Integer.parseInt(tu9.getText());

							String[] dateStart1 = StartDate.split("-");
							int yearStart1 = Integer.parseInt(dateStart1[0]);
							int manthStart1 = Integer.parseInt(dateStart1[1]);
							int dayStart1 = Integer.parseInt(dateStart1[2]);

							String[] dateEnd1 = EndDate.split("-");
							int yearEnd1 = Integer.parseInt(dateEnd1[0]);
							int manthEnd1 = Integer.parseInt(dateEnd1[1]);
							int dayEnd1 = Integer.parseInt(dateEnd1[2]);

							String[] Days1 = TimeAndDays.split(",");
							String[] Times1 = Days1[(Days1.length - 1)].split("-");
							String[] TimeStart1 = Times1[0].split(":");
							String[] TimeEnd1 = Times1[1].split(":");
							int HoursStart1 = Integer.parseInt(TimeStart1[0]);
							int HoursEnd1 = Integer.parseInt(TimeEnd1[0]);

							int MinStart1 = Integer.parseInt(TimeStart1[1]);
							int MinEnd1 = Integer.parseInt(TimeEnd1[1]);
							String[] dateStart2;
							int yearStart2;
							int manthStart2;
							int dayStart2;
							String[] dateEnd2;
							int yearEnd2;
							int manthEnd2;
							int dayEnd2;
							String[] Days2;
							String[] Times2;
							String[] TimeStart2;
							String[] TimeEnd2;
							int HoursStart2;
							int HoursEnd2;
							int MinStart2;
							int MinEnd2;

							int existPlace = 0;
							int existCoache = 0;
							SQL = "SELECT count(*) FROM place where PlaceId=" + placeId + " ;";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							rs.next();
							existPlace = Integer.parseInt(rs.getString(1));

							SQL = "SELECT count(*) FROM coaches where SSN=" + coache + " ;";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							rs.next();
							existCoache = Integer.parseInt(rs.getString(1));
							boolean existCommenP = false;
							boolean existCommenC = false;
							boolean noCap = false;
							SQL = "SELECT count(*) FROM sport_programs_runningin s where s.SPID=" + Id
									+ " and s.SPID != " + ID + " ;";
							stmt = con.prepareStatement(SQL);
							rs = stmt.executeQuery();
							rs.next();
							if (Integer.parseInt(rs.getString(1)) == 0) {
								if (existPlace == 1 && existCoache == 1) {
									SQL = "SELECT s.PlaceID_runningIn,s.StartDate,s.EndDate,s.TimeAndDays,s.PC,c.Name FROM sport_programs_runningin s,train t,coaches c where t.SPID_t=s.SPID and c.SSN=t.SSN_c and s.SPID != "
											+ ID + ";";
									stmt = con.prepareStatement(SQL);
									rs = stmt.executeQuery();
									while (rs.next()) {
										if (cap > Integer.parseInt(rs.getString(5))) {
											noCap = true;
											existCommenP = true;
											existCommenC = true;
											continue;
										}
										if (placeId != Integer.parseInt(rs.getString(1))) {
											existCommenP = false;
											continue;
										}
										dateStart2 = rs.getString(2).split("-");
										yearStart2 = Integer.parseInt(dateStart2[0]);
										manthStart2 = Integer.parseInt(dateStart2[1]);
										dayStart2 = Integer.parseInt(dateStart2[2]);
										dateEnd2 = rs.getString(3).split("-");
										yearEnd2 = Integer.parseInt(dateEnd2[0]);
										manthEnd2 = Integer.parseInt(dateEnd2[1]);
										dayEnd2 = Integer.parseInt(dateEnd2[2]);
										Days2 = rs.getString(4).split(",");
										Times2 = Days2[(Days2.length - 1)].split("-");
										TimeStart2 = Times2[0].split(":");
										TimeEnd2 = Times2[1].split(":");
										HoursStart2 = Integer.parseInt(TimeStart2[0]);
										HoursEnd2 = Integer.parseInt(TimeEnd2[0]);
										MinStart2 = Integer.parseInt(TimeStart2[1]);
										MinEnd2 = Integer.parseInt(TimeEnd2[1]);
										if ((yearEnd2 <= yearStart1 || yearEnd1 <= yearStart2)
												&& (yearStart1 != yearStart2 && yearEnd1 != yearEnd2)) {
											existCommenP = false;

										} else if ((yearStart2 < yearEnd1 && yearStart1 < yearStart2)
												|| (yearStart1 < yearEnd2 && yearStart2 < yearStart1)
												|| (yearStart1 == yearStart2 && yearEnd1 == yearEnd2)
												|| (yearStart2 < yearStart1 && yearStart2 < yearEnd1
														&& yearEnd2 > yearEnd1 && yearEnd2 > yearStart1)
												|| (yearStart1 < yearStart2 && yearStart1 < yearEnd2
														&& yearEnd1 > yearEnd2 && yearEnd1 > yearStart2)
												|| (yearStart1 == yearStart2 && yearEnd1 < yearEnd2)
												|| (yearStart1 == yearStart2 && yearEnd2 < yearEnd1)
												|| (yearStart1 < yearStart2 && yearEnd1 == yearEnd2)
												|| (yearStart2 < yearStart1 && yearEnd1 == yearEnd2)) {
											if ((manthEnd2 <= manthStart1 || manthEnd1 <= manthStart2)
													&& (manthStart1 != manthStart2 && manthEnd1 != manthEnd2)) {
												existCommenP = false;

											} else if ((manthStart2 < manthEnd1 && manthStart1 < manthStart2)
													|| (manthStart1 < manthEnd2 && manthStart2 < manthStart1)
													|| (manthStart1 == manthStart2 && manthEnd1 == manthEnd2)
													|| (manthStart2 < manthStart1 && manthStart2 < manthEnd1
															&& manthEnd2 > manthEnd1 && manthEnd2 > manthStart1)
													|| (manthStart1 < manthStart2 && manthStart1 < manthEnd2
															&& manthEnd1 > manthEnd2 && manthEnd1 > manthStart2)
													|| (manthStart1 == manthStart2 && manthEnd1 < manthEnd2)
													|| (manthStart1 == manthStart2 && manthEnd2 < manthEnd1)
													|| (manthStart1 < manthStart2 && manthEnd1 == manthEnd2)
													|| (manthStart2 < manthStart1 && manthEnd1 == manthEnd2)) {
												if ((dayEnd2 <= dayStart1 || dayEnd1 <= dayStart2)
														&& (dayStart1 != dayStart2 && dayEnd1 != dayEnd2)) {

													existCommenP = false;
												} else if ((dayStart2 < dayEnd1 && dayStart1 < dayStart2)
														|| (dayStart1 < dayEnd2 && dayStart2 < dayStart1)
														|| (dayStart1 == dayStart2 && dayEnd1 == dayEnd2)
														|| (dayStart2 < dayStart1 && dayStart2 < dayEnd1
																&& dayEnd2 > dayEnd1 && dayEnd2 > dayStart1)
														|| (dayStart1 < dayStart2 && dayStart1 < dayEnd2
																&& dayEnd1 > dayEnd2 && dayEnd1 > dayStart2)
														|| (dayStart1 == dayStart2 && dayEnd1 < dayEnd2)
														|| (dayStart1 == dayStart2 && dayEnd2 < dayEnd1)
														|| (dayStart1 < dayStart2 && dayEnd1 == dayEnd2)
														|| (dayStart2 < dayStart1 && dayEnd1 == dayEnd2)) {
													boolean flagexisCom = false;

													for (int y = 0; y < (Days1.length - 1); y++) {
														for (int i = 0; i < (Days2.length - 1); i++) {
															if (Days1[y].equalsIgnoreCase(Days2[i])) {
																flagexisCom = true;
																break;
															}
															if (flagexisCom == true) {
																break;
															}
														}
													}
													if (flagexisCom == true) {

														if ((HoursEnd2 <= HoursStart1 || HoursEnd1 <= HoursStart2)
																&& (HoursStart1 != HoursStart2
																		&& HoursEnd1 != HoursEnd2)) {
															existCommenP = false;
														} else if ((HoursStart2 < HoursEnd1
																&& HoursStart1 < HoursStart2)
																|| (HoursStart1 < HoursEnd2
																		&& HoursStart2 < HoursStart1)
																|| (HoursStart1 == HoursStart2
																		&& HoursEnd1 == HoursEnd2)
																|| (HoursStart2 < HoursStart1 && HoursStart2 < HoursEnd1
																		&& HoursEnd2 > HoursEnd1
																		&& HoursEnd2 > HoursStart1)
																|| (HoursStart1 < HoursStart2 && HoursStart1 < HoursEnd2
																		&& HoursEnd1 > HoursEnd2
																		&& HoursEnd1 > HoursStart2)
																|| (HoursStart1 == HoursStart2 && HoursEnd1 < HoursEnd2)
																|| (HoursStart1 == HoursStart2 && HoursEnd2 < HoursEnd1)
																|| (HoursStart1 < HoursStart2 && HoursEnd1 == HoursEnd2)
																|| (HoursStart2 < HoursStart1
																		&& HoursEnd1 == HoursEnd2)) {

															if ((MinEnd2 <= MinStart1 || MinEnd1 <= MinStart2)
																	&& (MinStart1 != MinStart2 && MinEnd1 != MinEnd2)) {

																existCommenP = false;
															} else if ((MinStart2 < MinEnd1 && MinStart1 < MinStart2)
																	|| (MinStart1 < MinEnd2 && MinStart2 < MinStart1)
																	|| (MinStart1 == MinStart2 && MinEnd1 == MinEnd2)
																	|| (MinStart2 < MinStart1 && MinStart2 < MinEnd1
																			&& MinEnd2 > MinEnd1 && MinEnd2 > MinStart1)
																	|| (MinStart1 < MinStart2 && MinStart1 < MinEnd2
																			&& MinEnd1 > MinEnd2 && MinEnd1 > MinStart2)
																	|| (MinStart1 == MinStart2 && MinEnd1 < MinEnd2)
																	|| (MinStart1 == MinStart2 && MinEnd2 < MinEnd1)
																	|| (MinStart1 < MinStart2 && MinEnd1 == MinEnd2)
																	|| (MinStart2 < MinStart1 && MinEnd1 == MinEnd2)) {

																existCommenP = true;
																break;
															}
														}
													} else {
														existCommenP = false;
													}
												}
											}
										}
										SQL2 = "SELECT count(*) FROM train where SSN_c=" + coache + " and SSN_c!="
												+ rs.getString(5) + " ;";
										stmt2 = con.prepareStatement(SQL2);
										rs2 = stmt2.executeQuery();
										rs2.next();
										if (Integer.parseInt(rs2.getString(1)) > 0) {
											SQL2 = "select s.StartDate,s.EndDate,s.TimeAndDays from sport_programs_runningin s,train t where t.SPID_t=s.SPID and t.SSN_c="
													+ coache + " and  t.SSN_c!=" + rs.getString(5) + ";";
											stmt2 = con.prepareStatement(SQL2);
											rs2 = stmt2.executeQuery();
											rs2.next();
											dateStart2 = rs2.getString(1).split("-");
											yearStart2 = Integer.parseInt(dateStart2[0]);
											manthStart2 = Integer.parseInt(dateStart2[1]);
											dayStart2 = Integer.parseInt(dateStart2[2]);
											dateEnd2 = rs2.getString(2).split("-");
											yearEnd2 = Integer.parseInt(dateEnd2[0]);
											manthEnd2 = Integer.parseInt(dateEnd2[1]);
											dayEnd2 = Integer.parseInt(dateEnd2[2]);
											Days2 = rs2.getString(3).split(",");
											Times2 = Days2[(Days2.length - 1)].split("-");
											TimeStart2 = Times2[0].split(":");
											TimeEnd2 = Times2[1].split(":");
											HoursStart2 = Integer.parseInt(TimeStart2[0]);
											HoursEnd2 = Integer.parseInt(TimeEnd2[0]);
											MinStart2 = Integer.parseInt(TimeStart2[1]);
											MinEnd2 = Integer.parseInt(TimeEnd2[1]);
											if ((yearEnd2 <= yearStart1 || yearEnd1 <= yearStart2)
													&& (yearStart1 != yearStart2 && yearEnd1 != yearEnd2)) {
												existCommenC = false;

											} else if ((yearStart2 < yearEnd1 && yearStart1 < yearStart2)
													|| (yearStart1 < yearEnd2 && yearStart2 < yearStart1)
													|| (yearStart1 == yearStart2 && yearEnd1 == yearEnd2)
													|| (yearStart2 < yearStart1 && yearStart2 < yearEnd1
															&& yearEnd2 > yearEnd1 && yearEnd2 > yearStart1)
													|| (yearStart1 < yearStart2 && yearStart1 < yearEnd2
															&& yearEnd1 > yearEnd2 && yearEnd1 > yearStart2)
													|| (yearStart1 == yearStart2 && yearEnd1 < yearEnd2)
													|| (yearStart1 == yearStart2 && yearEnd2 < yearEnd1)
													|| (yearStart1 < yearStart2 && yearEnd1 == yearEnd2)
													|| (yearStart2 < yearStart1 && yearEnd1 == yearEnd2)) {
												if ((manthEnd2 <= manthStart1 || manthEnd1 <= manthStart2)
														&& (manthStart1 != manthStart2 && manthEnd1 != manthEnd2)) {
													existCommenC = false;

												} else if ((manthStart2 < manthEnd1 && manthStart1 < manthStart2)
														|| (manthStart1 < manthEnd2 && manthStart2 < manthStart1)
														|| (manthStart1 == manthStart2 && manthEnd1 == manthEnd2)
														|| (manthStart2 < manthStart1 && manthStart2 < manthEnd1
																&& manthEnd2 > manthEnd1 && manthEnd2 > manthStart1)
														|| (manthStart1 < manthStart2 && manthStart1 < manthEnd2
																&& manthEnd1 > manthEnd2 && manthEnd1 > manthStart2)
														|| (manthStart1 == manthStart2 && manthEnd1 < manthEnd2)
														|| (manthStart1 == manthStart2 && manthEnd2 < manthEnd1)
														|| (manthStart1 < manthStart2 && manthEnd1 == manthEnd2)
														|| (manthStart2 < manthStart1 && manthEnd1 == manthEnd2)) {
													if ((dayEnd2 <= dayStart1 || dayEnd1 <= dayStart2)
															&& (dayStart1 != dayStart2 && dayEnd1 != dayEnd2)) {

														existCommenC = false;
													} else if ((dayStart2 < dayEnd1 && dayStart1 < dayStart2)
															|| (dayStart1 < dayEnd2 && dayStart2 < dayStart1)
															|| (dayStart1 == dayStart2 && dayEnd1 == dayEnd2)
															|| (dayStart2 < dayStart1 && dayStart2 < dayEnd1
																	&& dayEnd2 > dayEnd1 && dayEnd2 > dayStart1)
															|| (dayStart1 < dayStart2 && dayStart1 < dayEnd2
																	&& dayEnd1 > dayEnd2 && dayEnd1 > dayStart2)
															|| (dayStart1 == dayStart2 && dayEnd1 < dayEnd2)
															|| (dayStart1 == dayStart2 && dayEnd2 < dayEnd1)
															|| (dayStart1 < dayStart2 && dayEnd1 == dayEnd2)
															|| (dayStart2 < dayStart1 && dayEnd1 == dayEnd2)) {
														boolean flagexisCom = false;

														for (int y = 0; y < (Days1.length - 1); y++) {
															for (int i = 0; i < (Days2.length - 1); i++) {
																if (Days1[y].equalsIgnoreCase(Days2[i])) {
																	flagexisCom = true;
																	break;
																}
																if (flagexisCom == true) {
																	break;
																}
															}
														}
														if (flagexisCom == true) {

															if ((HoursEnd2 <= HoursStart1 || HoursEnd1 <= HoursStart2)
																	&& (HoursStart1 != HoursStart2
																			&& HoursEnd1 != HoursEnd2)) {
																existCommenC = false;
															} else if ((HoursStart2 < HoursEnd1
																	&& HoursStart1 < HoursStart2)
																	|| (HoursStart1 < HoursEnd2
																			&& HoursStart2 < HoursStart1)
																	|| (HoursStart1 == HoursStart2
																			&& HoursEnd1 == HoursEnd2)
																	|| (HoursStart2 < HoursStart1
																			&& HoursStart2 < HoursEnd1
																			&& HoursEnd2 > HoursEnd1
																			&& HoursEnd2 > HoursStart1)
																	|| (HoursStart1 < HoursStart2
																			&& HoursStart1 < HoursEnd2
																			&& HoursEnd1 > HoursEnd2
																			&& HoursEnd1 > HoursStart2)
																	|| (HoursStart1 == HoursStart2
																			&& HoursEnd1 < HoursEnd2)
																	|| (HoursStart1 == HoursStart2
																			&& HoursEnd2 < HoursEnd1)
																	|| (HoursStart1 < HoursStart2
																			&& HoursEnd1 == HoursEnd2)
																	|| (HoursStart2 < HoursStart1
																			&& HoursEnd1 == HoursEnd2)) {

																if ((MinEnd2 <= MinStart1 || MinEnd1 <= MinStart2)
																		&& (MinStart1 != MinStart2
																				&& MinEnd1 != MinEnd2)) {

																	existCommenC = false;
																} else if ((MinStart2 < MinEnd1
																		&& MinStart1 < MinStart2)
																		|| (MinStart1 < MinEnd2
																				&& MinStart2 < MinStart1)
																		|| (MinStart1 == MinStart2
																				&& MinEnd1 == MinEnd2)
																		|| (MinStart2 < MinStart1 && MinStart2 < MinEnd1
																				&& MinEnd2 > MinEnd1
																				&& MinEnd2 > MinStart1)
																		|| (MinStart1 < MinStart2 && MinStart1 < MinEnd2
																				&& MinEnd1 > MinEnd2
																				&& MinEnd1 > MinStart2)
																		|| (MinStart1 == MinStart2 && MinEnd1 < MinEnd2)
																		|| (MinStart1 == MinStart2 && MinEnd2 < MinEnd1)
																		|| (MinStart1 < MinStart2 && MinEnd1 == MinEnd2)
																		|| (MinStart2 < MinStart1
																				&& MinEnd1 == MinEnd2)) {

																	existCommenC = true;
																	break;
																}
															}
														} else {
															existCommenC = false;
														}
													}
												}
											}
										} else
											existCommenC = false;
									}
								} else {
									alert.setContentText("Place Or Coache not found");
									alert.showAndWait();
									existCommenP = true;
									existCommenC = true;
								}
							} else {
								alert.setContentText("The sport program ID Excist try another ID");
								alert.showAndWait();
								existCommenP = true;
								existCommenC = true;
							}
							if (noCap == true) {
								existCommenP = true;
								existCommenC = true;
								alert.setContentText("No capasity you can't update");
								alert.showAndWait();
							}

							if (existCommenP == false && existCommenC == false) {
								SQL = "UPDATE sport_programs_runningin SET SPID = " + Id + ", PlaceID_runningIn = "
										+ placeId + ", Name = '" + name + "', StartDate = '" + StartDate
										+ "', EndDate = '" + EndDate + "', Price = " + price + ", TimeAndDays = '"
										+ TimeAndDays + "', PC = " + cap + " WHERE SPID = " + ID + ";";
								stmt = con.prepareStatement(SQL);
								stmt.executeUpdate();
								SQL = "UPDATE train SET SSN_c =" + coache + " WHERE SPID_t = " + Id + ";";
								stmt = con.prepareStatement(SQL);
								stmt.executeUpdate();
								alert.setContentText("The Update successed");
								alert.showAndWait();
							} else {
								alert.setContentText("there is Common Date you can't update");
								alert.showAndWait();
							}

						}
					}

				} catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}
			} else {
				alert.setContentText("Please enter all valid Information");
				alert.showAndWait();
			}
		});

		bu1.setMinSize(200, 30);
		bu2.setMinSize(200, 30);
		bu3.setMinSize(200, 30);

		lu1.setFont(myfont2);
		lu2.setFont(myfont2);
		lu3.setFont(myfont2);
		lu4.setFont(myfont2);
		lu5.setFont(myfont2);
		lu6.setFont(myfont2);
		lu7.setFont(myfont2);
		lu8.setFont(myfont2);
		lu9.setFont(myfont2);

		lu1.setMinSize(520, 30);
		lu2.setMinSize(520, 30);
		lu3.setMinSize(520, 30);
		lu4.setMinSize(520, 30);
		lu5.setMinSize(520, 30);
		lu6.setMinSize(520, 30);
		lu7.setMinSize(520, 30);
		lu8.setMinSize(520, 30);
		lu9.setMinSize(520, 30);

		tu1.setMinSize(520, 30);
		tu2.setMinSize(520, 30);
		tu3.setMinSize(520, 30);
		tu4.setMinSize(520, 30);
		tu5.setMinSize(520, 30);
		tu6.setMinSize(520, 30);
		tu7.setMinSize(520, 30);
		tu8.setMinSize(520, 30);
		tu9.setMinSize(520, 30);

		// Screen Format
		hu1.getChildren().addAll(lu1, tu1);
		hu2.getChildren().addAll(lu2, tu2);
		hu3.getChildren().addAll(lu3, tu3);
		hu4.getChildren().addAll(lu4, tu4);
		hu5.getChildren().addAll(lu5, tu5);
		hu6.getChildren().addAll(lu6, tu6);
		hu7.getChildren().addAll(lu7, tu7);
		hu8.getChildren().addAll(lu8, tu8);
		hu9.getChildren().addAll(lu9, tu9);
		hu10.getChildren().addAll(bu3, bu1, bu2);

		spU.getChildren().addAll(hu1, hu2, hu3, hu4, hu5, hu6, hu7, hu8, hu9, hu10);
		// View content
		SpUpScene.setRoot(spU);
		SpUp.setTitle("Update Information about ");
		SpUp.show();

		alert.setContentText(
				"Note:For Search Enter the id and press Find button \n Please enter the information in order and complete, otherwise the Update button will not work");
		alert.showAndWait();

	}

}

class DBConn {
	private Connection con;
	private String dbURL;
	private String dbUsername;
	private String dbPassword;
	private String URL;
	private String port;
	private String dbName;

	DBConn(String URL, String port, String dbName, String dbUsername, String dbPassword) {
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.URL = URL;
		this.port = port;
		this.dbName = dbName;
	}

	public Connection connectDB() throws ClassNotFoundException, SQLException {
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName;
		con = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		return con;

	}

}
