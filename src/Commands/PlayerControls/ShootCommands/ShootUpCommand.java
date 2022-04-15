package Commands.PlayerControls.ShootCommands;

import Commands.Command;
import Controllers.PlayerController;

public class ShootUpCommand implements Command {
    private PlayerController playerController;

    public ShootUpCommand(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void execute() {
        playerController.shootUp();
    }
}
