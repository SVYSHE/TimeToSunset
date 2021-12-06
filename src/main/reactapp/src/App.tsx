import React from 'react';
import './App.css';


function App() {
    // States
    const [timeToSunset, setTimeToSunset] = React.useState("");
    const [city, setCity] = React.useState("");

    // Event handler
    const buttonHandler = async (event: React.MouseEvent<HTMLButtonElement>) => {
        setTimeToSunset(await fetchTimeToSunset(city));
    }

    const inputHandler = (event: React.KeyboardEvent<HTMLInputElement>) => {
        setCity(event.currentTarget.value);
    }

    // Functions
    async function fetchTimeToSunset(city: string): Promise<string> {
            let returnValue: string = "";
            await fetch("http://localhost:9001/timetosunset/" + city).then(x => x.json()).then(ttss => returnValue = ttss.timeToSunset);
            return returnValue;


    }

    // Render
    return (
        <div className="App">
            <h1>{city}: {timeToSunset}</h1>
            <input placeholder={"Enter a city name.."} onInput={inputHandler}/>
            <button onClick={buttonHandler}>Click me</button>
        </div>
    );
}

export default App;
