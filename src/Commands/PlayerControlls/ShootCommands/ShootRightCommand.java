package Commands.PlayerControlls.ShootCommands;

import Commands.Command;
import Controllers.PlayerController;

public class ShootRightCommand implements Command {
    private PlayerController playerController;

    public ShootRightCommand(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void execute() {
        playerController.shootRight();
    }
}
