package main.store;

import java.awt.*;
import java.io.IOException;

public class app {

    public static void main(String[] args) {
        DBConnection db = new DBConnection("main\\db\\store.db");

        try {
            new GuiComponent(db);
        } catch (IOException | FontFormatException er) {
            er.printStackTrace();
        }
    }

}
