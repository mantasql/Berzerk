package Commands.PlayerControlls.MoveCommands;

import Commands.Command;
import Controllers.PlayerController;

public class MoveLeftCommand implements Command {
    private PlayerController playerController;

    public MoveLeftCommand(PlayerController playerController) {
        this.playerController = playerController;
    }

    @Override
    public void execute() {
        playerController.moveLeft();
    }
}
