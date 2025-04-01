import cards from "../../assets/Kotlin_Alias_cover.svg";
import {GameState} from "../GameScreen";
import { useEffect, useState } from "react";

type StartScreenProps = {
    gameStateSetter: (gs: GameState) => void
}

export default function StartScreen({ gameStateSetter }: StartScreenProps) {
    const [hasSavedGame, setHasSavedGame] = useState(false);

    // Fetch whether a saved game exists
    useEffect(() => {
        fetch("/game_state.json")
            .then(response => {
                if (!response.ok) {
                    console.warn("Game state file not found. Starting a new game.");
                    setHasSavedGame(false);
                    return null;
                }
                return response.json();
            })
            .then(data => {
                if (data) {
                    console.log("Game state fetched successfully:", data);
                    const hasGameData = Object.keys(data.teams || {}).length > 0 || (data.gameHistory || []).length > 0;
                    setHasSavedGame(hasGameData);
                }
            })
            .catch(error => {
                console.error("Error fetching game state:", error);
                setHasSavedGame(false);
            });
    }, []);

    return (
        <div className="App-cards-container">
            <img src={cards} className="App-cards" alt="cards"/>
            <p className="App-big-name font-link-bold">Alias Game</p>
            <p className="App-small-name font-link-base">by Kotlin Course</p>
            <div className="App-buttons-container App-display-flex">
                {/* Resume Button - Only appears if a saved game exists */}
                {hasSavedGame && (
                    <button
                        className="App-button-base App-button-resume"
                        onClick={() => gameStateSetter(GameState.RESUME)}
                    >
                        Resume Game
                    </button>
                )}

                {/* Start New Game Button */}
                <button
                    className="App-button-base App-button-start"
                    onClick={() => gameStateSetter(GameState.TEAMS)}
                >
                    Start New Game
                </button>

                {/* Previous Games Button */}
                <button className="App-button-base App-stat-button App-cursor-none">
                    <p className="font-link-light App-no-margin App-stat-button-p App-cursor-pointer"
                       onClick={() => gameStateSetter(GameState.STAT)}>
                        Previous games results
                    </p>
                </button>
            </div>
        </div>
    );
}
