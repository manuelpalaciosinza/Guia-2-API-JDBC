import Entities.AlumnosEntity;
import Entities.DireccionEntity;
import Services.AlumnosService;
import Services.DireccionesService;
import UI.UserInterface;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserInterface.getInstanceOf();
        UserInterface.verMenu();
    }
}

