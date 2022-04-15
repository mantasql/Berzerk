package Commands.PlayerControls.ShootCommands;

import Commands.Command;
import Controllers.PlayerController;

public class ShootDownCommand implements Command {
    private PlayerController playerController;

    public ShootDownCommand(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void execute() {
        playerController.shootDown();
    }
}
