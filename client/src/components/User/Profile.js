import {Card, ListGroup, Row} from "react-bootstrap";
import React, { useState } from "react";
import {useSelector} from "react-redux";

export const Profile = (user) => {
    const [data, setData] = useState(user.user.login);
    const auth = useSelector((state) => state.auth);
    return (
        <Row style={{marginTop: "20px"}}className={"margin-top: 20px"}>
            <ListGroup className={"bg-dark text-white w-100"}>
                <ListGroup.Item className={"border-List  bg-dark text-white w-100"}>
                    <Card.Text>
                        Login: {user.user.login}
                    </Card.Text>
                </ListGroup.Item>
                {user.user.adventurer !== null?
                    <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                        <Card.Text>
                            Rank: {user.user.login}
                        </Card.Text>
                    </ListGroup.Item> : ''}
                {user.user.firstname !== null & user.user.surname !== null ?
                    <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                        <Card.Text>
                            Firstname and Surname: {user.user.firstname} {user.user.surname}
                        </Card.Text>
                    </ListGroup.Item> : ""}
                {user.user.gender !== null?
                    <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                        <Card.Text>
                            Gender: {user.user.gender}
                        </Card.Text>
                    </ListGroup.Item> : ""}
                {user.user.birthday !== null?
                    <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                        <Card.Text>
                            Birthday: {user.user.birthday}
                        </Card.Text>
                    </ListGroup.Item> : ""}
                {user.user.email !== null?
                    <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                        <Card.Text>
                            Email: {user.user.email}
                        </Card.Text>
                    </ListGroup.Item> : ""}
                {user.user.phone_number !== null?
                    <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                        <Card.Text>
                            Phone: {user.user.phone_number}
                        </Card.Text>
                    </ListGroup.Item> : ""}
                {user.user.balance !== null & user.user.login === auth.username?
                    <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                        <Card.Text>
                            Balance: {user.user.balance}
                        </Card.Text>
                    </ListGroup.Item> : ""}
            </ListGroup>
        </Row>
    )
}