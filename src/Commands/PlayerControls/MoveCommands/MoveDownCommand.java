package Commands.PlayerControls.MoveCommands;

import Commands.Command;
import Controllers.PlayerController;

public class MoveDownCommand implements Command {
    private PlayerController playerController;

    public MoveDownCommand(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void execute() {
        playerController.moveDown();
    }
}
