package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainController class is responsible for handling the actions performed on the MainView.
 * It uses the ServiceFactory to access various services and the MainModel to handle the game logic.
 */
public class MainController {
    private final MainModel model;
    private final ServiceFactory serviceFactory;

    /**
     * Constructor for MainController.
     * Initializes the model and serviceFactory, and sets the action listener for the view.
     *
     * @param model an instance of MainModel
     * @param view an instance of MainView
     */
    public MainController(MainModel model, MainView view) {
        this.model = model;
        serviceFactory = new ServiceFactory();
        view.setButtonActionListener(new MenuButtonListener());
    }

    /**
     * MenuButtonListener class implements ActionListener.
     * It handles the actions performed on the menu buttons.
     */
    class MenuButtonListener implements ActionListener {
        /**
         * actionPerformed method is called when a menu button is clicked.
         * It sets the serviceFactory for the model and calls the model's setMenuAction method with the action command.
         *
         * @param e an instance of ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            model.setServiceFactory(serviceFactory);
            model.setMenuAction(command);
        }
    }

}