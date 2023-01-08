import {Button} from "react-bootstrap";
import React, {useState} from "react";


export const UserTest = (adventurer) => {
    const [flag, setFlag] = useState(adventurer.adventurer);
    console.log(flag)
    return (
        <div>
            {!flag?
                <Button className={"w-100"}
                        onClick={() => setFlag(true)}>
                    Пройти тестирование
                </Button>
                :""}
        </div>
    )
}