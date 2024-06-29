package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private final MainModel model;
    private final ServiceFactory serviceFactory;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        serviceFactory = new ServiceFactory();
        view.setButtonActionListener(new MenuButtonListener());
    }

    class MenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            model.setServiceFactory(serviceFactory);
            model.setMenuAction(command);
        }
    }

}
