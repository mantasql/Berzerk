package Commands.PlayerControls.ShootCommands;

import Commands.Command;
import Controllers.PlayerController;

public class ShootLeftCommand implements Command {
    private PlayerController playerController;

    public ShootLeftCommand(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void execute() {
        playerController.shootLeft();
    }
}
