package com.example.finallauncherrefactored;

import com.example.finallauncherrefactored.Projects.AirHockey.PongApp;
import com.example.finallauncherrefactored.Projects.ArmWrestling.ArmWrestlingApp;
import com.example.finallauncherrefactored.Projects.BlackJack.FancyBlackJackApp;
import com.example.finallauncherrefactored.Projects.BlockFight.FIGHT;
import com.example.finallauncherrefactored.Projects.CoreyChase.TileMoverApp;
import com.example.finallauncherrefactored.Projects.JJPaint.MsPaint;
import com.example.finallauncherrefactored.Projects.Lives.LivesApp;
import com.example.finallauncherrefactored.Projects.MP3Player.MP3app;
import com.example.finallauncherrefactored.Projects.MonstersInc.MontersIncApp;
import com.example.finallauncherrefactored.Projects.MuderEscape.MurderGameApp;
import com.example.finallauncherrefactored.Projects.Stories.StoriesApp;
import com.example.finallauncherrefactored.Projects.TetraShot.GameApp;
import com.example.finallauncherrefactored.Projects.TikTakToe.App2;
import com.example.finallauncherrefactored.Projects.Wordle.WordleApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class MainMenuPG2Controller {

    @FXML
    private Button btn_CoreyChase;
    @FXML
    private Button btn_BlackJack;

    @FXML
    private Button btn_Close;

    @FXML
    private Button btn_Settings;

    @FXML
    private Button btn_SignOut;

    @FXML
    private Button btn_backPage;

    @FXML
    private Button btn_nextPage;

    @FXML
    private ImageView imgview_BlackJack;

    @FXML
    private ImageView imgview_PFP;

    @FXML
    private Button btn_aVeryRealRPG;

    @FXML
    private ImageView imgview_AVeryRealRPG;

    @FXML
    private Button btn_ArmWrestling;

    @FXML
    private Button btn_Wordle;

    @FXML
    private Button btn_backPageForPage3;

    @FXML
    private Button btn_nextPageForPage3;

    @FXML
    private Button btn_MonstersInc;

    @FXML
    private Button btn_9Lives;

    @FXML
    private Button btn_JJPaint;


    @FXML
    private Button btn_backPageForPage4;

    @FXML
    private Button btn_nextPageForPage4;

    @FXML
    private Button btn_AirHockey;

    @FXML
    private Button btn_BlockFight;

    @FXML
    private Button btn_TetraShot;

    @FXML
    private Button btn_MurderEscape;

    @FXML
    private Button btn_backPageForPage5;

    @FXML
    private Button btn_nextPageForPage5;

    @FXML
    private Button btn_MP3Player;

    @FXML
    private Button btn_Stories;

    @FXML
    private Button btn_TikTakToe;

    @FXML
    void btn_handleTikTakToe(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TikTakToePreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleStories(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StoriesPreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btn_handleMP3Player(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MP3PlayerPreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleBackPageForPage5(ActionEvent event) {
        Main.updatePFP();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG1.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            Main.resetAppData();
            window.close();
        });
        window.show();
    }

    @FXML
    void btn_handleNextPageForPage5(ActionEvent event) {
        Main.updatePFP();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG1.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            Main.resetAppData();
            window.close();
        });
        window.show();
    }

    @FXML
    void btn_handleMurderEscape(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MurderEscapePreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleTetraShot(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TetraShotPreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleBlockFight(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BlockFightPreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleAirHockey(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AirHockeyPreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleBackPageForPage4(ActionEvent event) {
        Main.updatePFP();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG3.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            Main.resetAppData();
            window.close();
        });
        window.show();
    }

    @FXML
    void btn_handleNextPageForPage4(ActionEvent event) {
        Main.updatePFP();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG5.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            Main.resetAppData();
            window.close();
        });
        window.show();
    }

    @FXML
    void btn_handleJJPaint(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("JJPaintPreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handle9Lives(ActionEvent event) {
        LivesApp app = new LivesApp();
        app.start(event);
    }

    @FXML
    void btn_handleMonstersInc(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MonstersIncPreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btn_handleBackPageForPage3(ActionEvent event) {
        Main.updatePFP();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG2.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            Main.resetAppData();
            window.close();
        });
        window.show();
    }




    @FXML
    void btn_handleNextPageForPage3(ActionEvent event) {
        Main.updatePFP();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG4.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            Main.resetAppData();
            window.close();
        });
        window.show();
    }

    @FXML
    void btn_handleWordle(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WordlePreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleArmWrestling(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ArmWrestlingPreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                Main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleAVeryRealRPG(ActionEvent event) {
        try {
            Main main = new Main();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("VeryRealRPGPreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleBackPage(ActionEvent event) {
        Main.updatePFP();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG1.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            Main.resetAppData();
            window.close();
        });
        window.show();
    }

    @FXML
    void btn_handleBlackJack(ActionEvent event) {
        try {
            Main main = new Main();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BlackJackPreview.fxml")));
            Scene scene = new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setOnCloseRequest(e -> {
                main.resetAppData();
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleClose(ActionEvent event) {
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Main.resetAppData();
        window.close();
    }

    @FXML
    void btn_handleNextPage(ActionEvent event) {
        Main.updatePFP();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenuPG3.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            Main.resetAppData();
            window.close();
        });
        window.show();
    }

    @FXML
    void btn_handleSettings(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SettingsView.fxml")));
            Scene scene = new Scene(root);
            Stage window= new Stage();
            window.initModality(Modality.WINDOW_MODAL);
            window.initOwner(((Node)event.getSource()).getScene().getWindow());
            window.setScene(scene);
            window.setTitle("Phantom: Settings");
            window.setOnCloseRequest(e -> {
                window.close();
            });
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleSignOut(ActionEvent event) {
        Main main = new Main();
        main.resetPFP();
        Main.resetAppData();

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignInView.fxml")));
            Scene scene = new Scene(root);

            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btn_handleCoreyChase(ActionEvent event) {
        TileMoverApp app = new TileMoverApp();
        app.start(event);
    }

}

