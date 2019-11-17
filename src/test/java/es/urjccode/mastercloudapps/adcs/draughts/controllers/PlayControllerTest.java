package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.GameBuilder;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;

public class PlayControllerTest {

    @Test
    public void givenPlayControllerWhenMovementRequiereCorrectThenNotError() {
        Game game = new Game();
        State state = new State();
        PlayController playController = new PlayController(game, state);
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        assertNull(playController.isCorrect(origin, target));
        playController.move(origin, target);
        assertNull(playController.getPiece(origin));
        assertEquals(playController.getColor(target), Color.WHITE);
    }

    @Test
    public void testGivenGameWhenMovementEatLastPieceThenWinner() {
        Coordinate origin = new Coordinate(4,3);
        Coordinate target = new Coordinate(2,1);
        State state = new State();

        Game game = new GameBuilder(Color.WHITE)
                // 01234567
        /*0*/.row("        ")
        /*1*/.row("        ")
        /*2*/.row("        ")
        /*3*/.row("  n     ")
        /*4*/.row("   b    ")
        /*5*/.row("        ")
        /*6*/.row("        ")
        /*7*/.row("        ")
            .build();
        PlayController playController = new PlayController(game, state);
        playController.move(origin, target);
        assertThat(playController.isBlocked(), is(true));
    }


}