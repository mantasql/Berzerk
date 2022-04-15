package Commands.PlayerControls;

import Commands.Command;
import Commands.PlayerControls.MoveCommands.MoveDownCommand;
import Commands.PlayerControls.MoveCommands.MoveLeftCommand;
import Commands.PlayerControls.MoveCommands.MoveRightCommand;
import Commands.PlayerControls.MoveCommands.MoveUpCommand;
import Commands.PlayerControls.ShootCommands.ShootDownCommand;
import Commands.PlayerControls.ShootCommands.ShootLeftCommand;
import Commands.PlayerControls.ShootCommands.ShootRightCommand;
import Commands.PlayerControls.ShootCommands.ShootUpCommand;
import Controllers.PlayerController;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerCommandFactory {

    public static Command getCommand(InputEvent event, PlayerController playerController){
        boolean moveLeft = ((KeyEvent)event).getCode() == KeyCode.A;
        boolean moveUp = ((KeyEvent)event).getCode() == KeyCode.W;
        boolean moveDown = ((KeyEvent)event).getCode() == KeyCode.S;
        boolean moveRight = ((KeyEvent)event).getCode() == KeyCode.D;

        boolean shootRight = ((KeyEvent)event).getCode() == KeyCode.RIGHT;
        boolean shootLeft = ((KeyEvent)event).getCode() == KeyCode.LEFT;
        boolean shootUp = ((KeyEvent)event).getCode() == KeyCode.UP;
        boolean shootDown = ((KeyEvent)event).getCode() == KeyCode.DOWN;

        if(moveLeft){
            return new MoveLeftCommand(playerController);
        }
        else if(moveRight){
            return new MoveRightCommand(playerController);
        }
        else if(moveUp){
            return new MoveUpCommand(playerController);
        }
        else if(moveDown){
            return new MoveDownCommand(playerController);
        }

        if(shootRight){
            return new ShootRightCommand(playerController);
        }
        else if(shootLeft){
            return new ShootLeftCommand(playerController);
        }
        else if(shootDown){
            return new ShootDownCommand(playerController);
        }
        else if(shootUp){
            return new ShootUpCommand(playerController);
        }

        return new DoNothingCommand();
    }
}
