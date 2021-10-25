package Commands.PlayerControlls.MoveCommands;

import Commands.Command;
import Controllers.PlayerController;

public class MoveUpCommand implements Command {
    private PlayerController playerController;

    public MoveUpCommand(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void execute() {
        playerController.moveUp();
    }
}
