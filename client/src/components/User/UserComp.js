import React from "react";
import { Link } from "react-router-dom";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Corousel from "../Images/Corousel";

export const UserComp = ({user}) => {
    var photos = user.photos.map((item) => item.location)
    return (
        <div className="align-content-center content">
            <Container>
                <Row>
                    <Col md = {4}>
                        <Corousel images = {photos}/>
                    </Col>
                    <Col md = {8}>
                        <Link to={"user/" + user.id} style={{color: 'white'}} className="nav-link h-100" preventScrollReset={true}>
                            <Row>
                                <h3>{user.login}</h3>
                            </Row>
                            <Row>
                                <h4>{user.firstname} {user.surname}</h4>
                            </Row>
                            {user.adventurer?
                                <Row>
                                    <h5>Rank: {user.adventurer.rank}</h5>
                                </Row>
                                :""}
                            <Row>
                                <h5>Gender: {user.gender}</h5>
                            </Row>
                            <Row>
                                <h5>Birthday: {user.birthday}</h5>
                            </Row>
                            <Row>
                                <h5>Phone: {user.phoneNumber}</h5>
                            </Row>
                            <Row>
                                <h5>Email: {user.email}</h5>
                            </Row>
                        </Link>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}
