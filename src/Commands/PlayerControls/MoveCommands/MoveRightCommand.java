package Commands.PlayerControls.MoveCommands;

import Commands.Command;
import Controllers.PlayerController;

public class MoveRightCommand implements Command {
    private PlayerController playerController;

    public MoveRightCommand(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void execute() {
        playerController.moveRight();
    }
}
