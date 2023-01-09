import {Button, Card, Col, Form, InputGroup, ListGroup, Row} from "react-bootstrap";
import React, { useState } from "react";
import {useSelector} from "react-redux";
import Corousel from "../Images/Corousel";
import UploadComponent from "../Images/Upload";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faList, faSave, faUndo} from "@fortawesome/free-solid-svg-icons";



export const Profile = (user) => {
    console.log(user.user)
    const [up, setUp] = useState(false);
    const [data, setData] = useState(user.user.login);
    const auth = useSelector((state) => state.auth);

    function userChangeA(event){
        user.user.adventurer[event.target.name] = event.target.value
        setUp(!up)
    }
    function userChange(event){
        user.user[event.target.name] = event.target.value
        setUp(!up)
    }

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
                            Rank: {user.user.adventurer.rank}
                        </Card.Text>
                    </ListGroup.Item> : ''}
                {user.user.change?
                    <div className={"margin-top"}>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridReward">
                                <Form.Label>Firstname</Form.Label>
                                <Form.Control
                                    // required
                                    // autoComplete="off"
                                    type="text"
                                    name="firstname"
                                    value={user.user.firstname}
                                    onChange={userChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter User Firstname"
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridReward">
                                <Form.Label>Surname</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="text"
                                    name="surname"
                                    value={user.user.surname}
                                    onChange={userChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter User Surname"
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridReward">
                                <Form.Label>Gender</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="text"
                                    name="gender"
                                    value={user.user.gender}
                                    onChange={userChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter User Gender"
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridReward">
                                <Form.Label>Birthday</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="date"
                                    name="birthday"
                                    value={user.user.birthday}
                                    onChange={userChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter User Birthday"
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridReward">
                                <Form.Label>Email</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="text"
                                    name="email"
                                    value={user.user.email}
                                    onChange={userChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter User Email"
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridReward">
                                <Form.Label>Phone</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="text"
                                    name="phoneNumber"
                                    value={user.user.phoneNumber}
                                    onChange={userChange}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter User Phone"
                                />
                            </Form.Group>
                        </Form.Row>
                    </div>
                    :
                    <div>
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
                        {user.user.phoneNumber !== null?
                            <ListGroup.Item className={"border-List bg-dark text-white w-100"}>
                                <Card.Text>
                                    Phone: {user.user.phoneNumber}
                                </Card.Text>
                            </ListGroup.Item> : ""}
                    </div>
                }
                {user.rate === 1?
                    <div className={"border-white bg-dark"}>
                        <Form.Row className={"text-success bg-dark"}>
                            <Form.Group as={Col} controlId="formGridRank">
                                <Form.Label>Rank</Form.Label>
                                <Form.Control as="select"
                                              required
                                              name="rank"
                                              value={user.user.adventurer.rank}
                                              onChange={userChangeA}
                                >
                                    <option value="F">F</option>
                                    <option value="E">E</option>
                                    <option value="D">D</option>
                                    <option value="B">B</option>
                                    <option value="A">A</option>
                                    <option value="A+">A+</option>
                                    <option value="A++">A++</option>
                                    <option value="S">S</option>
                                    <option value="SS">SS</option>
                                </Form.Control>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridRole">
                                <Form.Label>Role</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="text"
                                    name="role"
                                    value={user.user.adventurer.role}
                                    onChange={userChangeA}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter Adventurer Role"
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridReward">
                                <Form.Label>Weapon</Form.Label>
                                <Form.Control
                                    required
                                    autoComplete="off"
                                    type="text"
                                    name="weapon"
                                    value={user.user.adventurer.weapon}
                                    onChange={userChangeA}
                                    className={"bg-dark text-white"}
                                    placeholder="Enter Adventurer Weapon"
                                />
                            </Form.Group>
                        </Form.Row>
                    </div>:""}

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